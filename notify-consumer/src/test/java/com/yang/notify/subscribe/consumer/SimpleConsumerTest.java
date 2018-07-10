package com.yang.notify.subscribe.consumer;

import com.yang.dto.NotificationDto;
import com.yang.notify.subscribe.config.ConsumerConfigLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/10
 **/
@Slf4j
public class SimpleConsumerTest {
    SimpleConsumer<NotificationDto> simpleConsumer;
    NotificationProcessor processor;

    @Before
    public void setUp() throws Exception {
        BlockingQueue<NotificationDto> queue = new ArrayBlockingQueue<>(500);
        Properties props = ConsumerConfigLoader.loadProps();
        simpleConsumer = new SimpleConsumer<>(props,
                props.getProperty("topic"), queue, NotificationDto.class);
        processor = new NotificationProcessor(queue, 500);
    }

    @Test
    public void run() throws InterruptedException {
        new Thread(simpleConsumer).start();
        new Thread(processor).start();
        TimeUnit.SECONDS.sleep(5);
    }

    @After
    public void clean() throws InterruptedException {
        try {
            simpleConsumer.close();
            processor.close();
        } catch (IOException e) {
            log.error("<Failed to close consumer and processor>", e);
        }
        TimeUnit.SECONDS.sleep(5);
    }
}
