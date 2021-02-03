package com.sai.demo.kafka.tool;

import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.Executor;

public class SaiKafkaThreadPool {

    private Executor consumerExecutor = new SimpleAsyncTaskExecutor();

    private Executor processConsumerMsgExecutor;

    private Executor producerEexecutor;

    public void initConsumer(){

    }

    public void initProducer(){

    }

    public Executor getConsumerExecutor(){
        return consumerExecutor;
    }

    public Executor getProcessConsumerMsgExecutor() {
        return processConsumerMsgExecutor;
    }

    public Executor getProducerEexecutor() {
        return producerEexecutor;
    }

}
