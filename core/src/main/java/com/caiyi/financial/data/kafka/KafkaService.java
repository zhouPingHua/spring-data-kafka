package com.caiyi.financial.data.kafka;

import com.caiyi.financial.data.config.ExtendProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zph  Date：2017/8/8.
 * 发送kafka消息类
 */
public class  KafkaService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static ExtendProperties extendProperties;
    private String brokers;

    public KafkaService(String brokers) {
        this.brokers = brokers;
    }

    private KafkaProducer<byte[], byte[]> createProducer(String brokers) {
        logger.info("kafka brokers   " + brokers);
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put("bootstrap.servers", brokers);
        producerConfig.put("metadata.fetch.timeout.ms", "3000");
        producerConfig.put("request.timeout.ms", "3000");
        ByteArraySerializer serializer = new ByteArraySerializer();
        KafkaProducer<byte[], byte[]> kafkaProducer = new KafkaProducer<>(producerConfig, serializer, serializer);
        return kafkaProducer;
    }

    public static KafkaService create() {
        init();
        return new KafkaService(getBrokers());
    }

    private static void init() {
        extendProperties = new ExtendProperties();
        try {
            extendProperties.load( KafkaService.class.getClassLoader().getResourceAsStream("kafkaConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param:
     * @Description:获取kafka host地址
     */
    private static String getBrokers() {
        String brokers = extendProperties.getProperty("kafka.brokers");
        return brokers;
    }


    /**
     * @param:topic 消息主题
     * @param:message 消息内容
     * @Description:发送消息到kafka
     */
    public boolean sendMessage(String topic, String message) {
        logger.info("send message to kafka: "+brokers);
        KafkaProducer<byte[],byte[]> kafkaProducer = createProducer(brokers);
        try {
            ProducerRecord<byte[], byte[]> record = new ProducerRecord(topic, message.getBytes());
            kafkaProducer.send(record).get(3, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            kafkaProducer.close();
        }
        return false;
    }

    /**
     * @param:message 消息内容
     * @Description:发送消息到kafka
     */
    public boolean sendMessage(String message) {
        logger.info("send message to kafka: "+brokers);
        KafkaProducer<byte[],byte[]> kafkaProducer = createProducer(brokers);
        try {
            String topic = extendProperties.getProperty("kafka.topic1");
            ProducerRecord<byte[], byte[]> record = new ProducerRecord(topic, message.getBytes());
            kafkaProducer.send(record).get(3, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            kafkaProducer.close();
        }
        return false;
    }


}
