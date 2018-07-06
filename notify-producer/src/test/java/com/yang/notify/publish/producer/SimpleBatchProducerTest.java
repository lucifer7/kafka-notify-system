package com.yang.notify.publish.producer;

import com.google.common.collect.Lists;
import com.yang.notify.publish.dto.NotificationDto;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.List;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/6
 **/
public class SimpleBatchProducerTest {

    @Test
    public void publish() {
        List<NotificationDto>  messages = Lists.newArrayList();
        messages.add(new NotificationDto.Builder(RandomUtils.nextLong(), "KafkaTransactionManager ", "used with normal Spring transaction support (@Transactional, TransactionTemplate etc).").build());
        messages.add(new NotificationDto.Builder(RandomUtils.nextLong(), "KafkaTemplate ", "You can use the KafkaTemplate to execute a series of operations within a local transaction.").build());
        messages.add(new NotificationDto.Builder(RandomUtils.nextLong(), "ChainedKafkaTransactionManager ", "The ChainedKafkaTransactionManager was introduced in version 2.1.3. This is a subclass of ChainedTransactionManager that can have exactly one KafkaTransactionManager. ").build());

        ProducerFactory.simpleBatchProducer().publish(messages);
    }
}