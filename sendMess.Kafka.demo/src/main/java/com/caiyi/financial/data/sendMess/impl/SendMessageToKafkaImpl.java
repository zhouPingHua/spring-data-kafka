package com.caiyi.financial.data.sendMess.impl;

import com.caiyi.financial.data.kafka.KafkaService;
import com.caiyi.financial.data.sendMess.SendMessageToKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zph  Date：2017/8/8.
 */
@Service
public class SendMessageToKafkaImpl implements SendMessageToKafka {

    @Autowired
    private KafkaService kafkaService;

    @Override
    public void sendMess(String topic, String message) {
        kafkaService.sendMessage(topic,message);
    }

    @Override
    public void sendMess(String message) {
        kafkaService.sendMessage(message);
    }
}
