package com.yang.notify.subscribe.consumer;

import com.yang.common.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/10
 **/
@Slf4j
public class SimpleConsumer<T> implements Runnable, Closeable {
    private Properties props;
    private String topic;
    private BlockingQueue<T> queue;
    private Class<T> dtoClz;
    private boolean flag = true;

    public SimpleConsumer(Properties props, String topic, BlockingQueue<T> queue, Class<T> dtoClz) {
        this.props = props;
        this.topic = topic;
        this.queue = queue;
        this.dtoClz = dtoClz;
    }

    @Override
    public void run() {
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton(topic));

        try {
            while (flag) {
                final ConsumerRecords<String, String> records = consumer.poll(2000);
                if (!records.isEmpty()) {
                    records.forEach(record -> {
                        try {
                            queue.put(JsonUtil.fromJson(record.value(), dtoClz));
                        } catch (InterruptedException e) {
                            log.error("<Put record to queue failed>", e);
                        }
                    });
                    consumer.commitAsync();
                }
            }
        } finally {
            consumer.commitSync();
            consumer.close();
        }

    }

    @Override
    public void close() throws IOException {
        this.flag = false;      // Will close after current messages committed and consumer shutdown
    }
}
