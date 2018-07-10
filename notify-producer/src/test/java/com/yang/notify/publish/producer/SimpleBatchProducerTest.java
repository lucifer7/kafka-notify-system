package com.yang.notify.publish.producer;

import com.google.common.collect.Lists;
import com.yang.dto.NotificationDto;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/6
 **/
public class SimpleBatchProducerTest {
    List<NotificationDto> messages = Lists.newArrayList();

    @Before
    public void setup() {
        messages.add(new NotificationDto.Builder(RandomUtils.nextLong(), "KafkaTransactionManager ", "used with normal Spring transaction support (@Transactional, TransactionTemplate etc).").build());
        messages.add(new NotificationDto.Builder(RandomUtils.nextLong(), "KafkaTemplate ", "You can use the KafkaTemplate to execute a series of operations within a local transaction.").build());
        messages.add(new NotificationDto.Builder(RandomUtils.nextLong(), "ChainedKafkaTransactionManager ", "The ChainedKafkaTransactionManager was introduced in version 2.1.3. This is a subclass of ChainedTransactionManager that can have exactly one KafkaTransactionManager. ").build());
        messages.add(new NotificationDto.Builder(RandomUtils.nextLong(), "sendDefault", "The sendDefault API requires that a default topic has been provided to the template.").build());
    }

    @Test
    public void publishForget() throws InterruptedException {
        ProducerFactory.simpleBatchProducer().publishForget(messages);
        TimeUnit.SECONDS.sleep(15);
    }

    @Test
    public void publishAsync() throws InterruptedException {
        ProducerFactory.simpleBatchProducer().publishAsync(messages);
        TimeUnit.SECONDS.sleep(15);
    }

    @Test
    public void publishSync() {
        ProducerFactory.simpleBatchProducer().publishSync(messages);
    }
}