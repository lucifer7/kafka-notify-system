package com.yang.notify.subscribe.consumer;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/10
 **/
@Slf4j
public class NotificationProcessor<T> implements Runnable, Closeable {
    private BlockingQueue<T> queue;
    private int batchSize;
    private boolean flag = true;

    public NotificationProcessor(BlockingQueue<T> queue, int batchSize) {
        this.queue = queue;
        this.batchSize = batchSize;
    }

    @Override
    public void run() {
        while (flag || queue.size() > 0) {
            List<T> messages = Lists.newArrayListWithCapacity(batchSize);
            queue.drainTo(messages, batchSize);
            messages.forEach(msg -> log.info("<Process message: {}>", msg));
        }
        log.info("<Notification processor has been closed>");
    }

    @Override
    public void close() throws IOException {
        this.flag = false;
    }
}
