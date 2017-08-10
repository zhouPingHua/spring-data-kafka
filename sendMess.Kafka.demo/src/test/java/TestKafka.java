import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by zph  Date：2017/8/9.
 */
public class TestKafka {


    @Test
    public void test(){
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put("bootstrap.servers", "127.0.0.1:9092");
        producerConfig.put("metadata.fetch.timeout.ms", "3000");
        producerConfig.put("request.timeout.ms", "3000");
        ByteArraySerializer serializer = new ByteArraySerializer();
        KafkaProducer<byte[], byte[]> kafkaProducer = new KafkaProducer<>(producerConfig, serializer, serializer);
        String topic = "test";
        String message = "test11";
        ProducerRecord<byte[], byte[]> record = new ProducerRecord(topic, message.getBytes());
        try {
            kafkaProducer.send(record).get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }


    @Test
    public void test2(){
        try {
            Properties props = new Properties();
            //指定代理服务器的地址
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put("heartbeat.interval.ms", "500");
            props.put("session.timeout.ms", "1000");
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "10000");

            String topic = "test";
            Producer<String, String> producer = new KafkaProducer<String, String>(props);
            for (int i = 2; i < 10; i++) {
                producer.send(new ProducerRecord<String, String>(topic, "test"+i));
            }
            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void sendKafkaMsg() {

        try {
            Properties props = new Properties();
            //指定代理服务器的地址
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
//            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put("heartbeat.interval.ms", "500");
            props.put("session.timeout.ms", "1000");
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "10000");

            String topic = "test";
            Producer<String, String> producer = new KafkaProducer<String, String>(props);
            for (int i = 2; i < 10; i++) {
                producer.send(new ProducerRecord<String, String>(topic, "test"+i));
            }
            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
