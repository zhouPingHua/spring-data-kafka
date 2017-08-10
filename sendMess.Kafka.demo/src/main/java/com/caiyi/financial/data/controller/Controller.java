package com.caiyi.financial.data.controller;

import com.caiyi.financial.data.sendMess.SendMessageToKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zph  Date：2017/8/8.
 */
@RestController
public class Controller {

    @Autowired
    private SendMessageToKafka sendMessageToKafka;

    @RequestMapping(value="/send",method = RequestMethod.GET)
    public void send(@RequestParam(required = true)String topic, @RequestParam(required = true)String message){
        sendMessageToKafka.sendMess(topic,message);
    }

    @RequestMapping(value="/send1",method = RequestMethod.GET)
    public void send( @RequestParam(required = true)String message){
        sendMessageToKafka.sendMess(message);
    }

}
