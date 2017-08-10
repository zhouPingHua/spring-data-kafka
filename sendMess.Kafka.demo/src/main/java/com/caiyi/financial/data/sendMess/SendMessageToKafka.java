package com.caiyi.financial.data.sendMess;

import org.springframework.stereotype.Component;

/**
 * Created by zph  Date：2017/8/8.
 */

public interface SendMessageToKafka {

    void sendMess(String topic,String message);

    void sendMess(String message);

}
