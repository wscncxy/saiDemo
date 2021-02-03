package com.sai.demo.kafka.tool;

import com.sai.demo.kafka.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

public abstract class SaiKafkaConsumer<MSGKEY,MSGVAL> {

    private String configPrefix;
    private SaiKafkaThreadPool saiKafkaThreadPool = new SaiKafkaThreadPool();
    private KafkaConsumer<MSGKEY, MSGVAL> consumer;
    private boolean running = false;
    private long pauseTime = 100;
    private boolean serialProcessMsg = true;
    private long pollDuration = 100;
    @Autowired
    private Environment env;


    public SaiKafkaConsumer(String configPrefix) {
        this.configPrefix = configPrefix + ".";
    }

    @PostConstruct
    public void init() {
        if (running) {
            return;
        }
        synchronized (this) {
            running = true;
            Properties props = constructConfig(env);
            consumer = new KafkaConsumer<>(props);
            // 指定订阅 topic 名称
            String topic = env.getProperty(configPrefix+Constants.CONSUMER_CONFIG_SUBSCRIBE_TOPIC);
            if (topic.indexOf(",") > 0) {
                consumer.subscribe(Arrays.asList(topic.split(",")));
            } else {
                consumer.subscribe(Arrays.asList(topic));
            }
            if (!serialProcessMsg) {
                saiKafkaThreadPool = new SaiKafkaThreadPool();
            }
            start();
        }
    }

    public void start() {
        saiKafkaThreadPool.initConsumer();
        saiKafkaThreadPool.getConsumerExecutor().execute(
                new processRunnable()
        );
    }

    public abstract void processMessage(ConsumerRecord<MSGKEY, MSGVAL> record);

    public boolean checkNeedPause() {
        return false;
    }

    private class processRunnable implements Runnable {
        @Override
        public void run() {
            while (running) {
                try {
                    if (checkNeedPause()) {
                        Thread.sleep(pauseTime);
                    }
                    ConsumerRecords<MSGKEY, MSGVAL> recordList = consumer.poll(Duration.ofMillis(pollDuration));
                    if (recordList == null || recordList.isEmpty()) {
                        continue;
                    }
                    Iterator<ConsumerRecord<MSGKEY, MSGVAL>> iterator = recordList.iterator();
                    while (iterator.hasNext()) {
                        ConsumerRecord<MSGKEY, MSGVAL> record = iterator.next();
                        if (serialProcessMsg) {
                            processMessage(record);
                        } else {
                            mutilProcessMsg(record);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }
    }

    private void mutilProcessMsg(ConsumerRecord<MSGKEY, MSGVAL> record) {
        saiKafkaThreadPool.getProcessConsumerMsgExecutor().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        processMessage(record);
                    }
                }
        );
    }

    private Properties constructConfig(Environment env) {
        Properties configProp = new Properties();
        Constants.consumerConfigKeyList.stream().forEach(configKey -> {
            String value = env.getProperty(configPrefix + configKey);
            if(value.indexOf("$")>0){

            }
            configProp.put(configKey, value);

        });
        return configProp;
    }
}
