package com.sai.demo.kafka;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String CONSUMER_CONFIG_SUBSCRIBE_TOPIC = "topic";
    public static final String CONSUMER_CONFIG_BOOTSTRAP_SERVERS = "bootstrap.servers";
    public static final String CONSUMER_CONFIG_GROUP_ID = "group.id";
    public static final String CONSUMER_CONFIG_AUTO_COMMIT = "enable.auto.commit";
    public static final String CONSUMER_CONFIG_AUTO_COMMIT_INTERVAL_MS = "auto.commit.interval.ms";
    public static final String CONSUMER_CONFIG_KEY_DESERIALIZER = "key.deserializer";
    public static final String CONSUMER_CONFIG_VALUE_DESERIALIZER = "value.deserializer";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";
//    public static final String CONSUMER_CONFIG_ = "";

    public static List<String> consumerConfigKeyList= new ArrayList<>();
    static{
        consumerConfigKeyList.add(CONSUMER_CONFIG_BOOTSTRAP_SERVERS);
        consumerConfigKeyList.add(CONSUMER_CONFIG_GROUP_ID);
        consumerConfigKeyList.add(CONSUMER_CONFIG_AUTO_COMMIT);
        consumerConfigKeyList.add(CONSUMER_CONFIG_AUTO_COMMIT_INTERVAL_MS);
        consumerConfigKeyList.add(CONSUMER_CONFIG_KEY_DESERIALIZER);
        consumerConfigKeyList.add(CONSUMER_CONFIG_VALUE_DESERIALIZER);
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
//        consumerConfigList.add();
    }


}
