package com.gyf.shopping.activemq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JmsConfig {

    public final static String TOPIC_HTML = "pingyougou.topic.createhtml";
    public final static String TOPIC_HTML_DELETE = "pingyougou.topic.deletehtml";

    public final static String QUEUE = "pingyougou.queue.solr";
    public final static String QUEUE_DELETE = "pingyougou.queue.solr.delete";

    public final static String TOPIC_SOLR = "pingyougou.topic.solr";

    // topic模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
       //topic模式
        bean.setPubSubDomain(true);
        
        return bean;
    }

    // queue模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
    
}
