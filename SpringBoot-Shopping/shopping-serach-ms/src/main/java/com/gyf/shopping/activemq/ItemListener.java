package com.gyf.shopping.activemq;

import com.alibaba.fastjson.JSON;
import com.gyf.shopping.pojo.Item;
import com.gyf.shopping.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.List;

@Service
public class ItemListener {

    @Autowired
    private ItemSearchService itemSearchService;

    @JmsListener(destination = JmsConfig.TOPIC_SOLR, containerFactory = "jmsListenerContainerTopic")
    public void onItemSearch(Message message) {
        TextMessage textMessage=(TextMessage)message;
        try {
            String text = textMessage.getText();//json字符串
            System.out.println("监听到消息:"+text);// text: json数组

            List<Item> itemList = JSON.parseArray(text, Item.class);
            itemSearchService.importList(itemList);
            System.out.println("导入到solr索引库成功!");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

  /*  @JmsListener(destination = JmsConfig.QUEUE_DELETE, containerFactory = "jmsListenerContainerQueue")
    public void onItemDelete(Message message) {
        System.out.println("进入onItemDelete监听");
        ObjectMessage objectMessage =(ObjectMessage)message;
        try {
            Long[] goodsIds= (Long[]) objectMessage.getObject();
            System.out.println("监听获取到消息："+goodsIds);
            itemSearchService.deleteByGoodsIds(Arrays.asList(goodsIds));
            System.out.println("执行索引库删除");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }*/

}
