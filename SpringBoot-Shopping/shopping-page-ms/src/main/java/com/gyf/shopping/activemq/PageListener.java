package com.gyf.shopping.activemq;

import com.gyf.shopping.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
public class PageListener {
    @Autowired
    private ItemPageService itemPageService;

    @JmsListener(destination=JmsConfig.TOPIC_HTML,containerFactory = "jmsListenerContainerTopic")
    public void onPageCreated(Message message) {
        TextMessage textMessage=(TextMessage)message;
        try {
            String text = textMessage.getText();
            System.out.println("接收到消息："+text);//商品ID
            boolean b = itemPageService.genItemHtml(Long.parseLong(text));
            System.out.println("网页生成结果："+b);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }






}
