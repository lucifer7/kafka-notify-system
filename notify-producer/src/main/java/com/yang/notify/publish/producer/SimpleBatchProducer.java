package com.yang.notify.publish.producer;

import com.google.common.collect.Lists;
import com.yang.common.JsonUtil;
import com.yang.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/5
 **/
@Slf4j
public class SimpleBatchProducer {
    private final KafkaProducer<String, String> producer;
    private final String topic;

    private Callback sendHandler = (rmd, exception) -> {
        if (null == exception) {
            log.info("<Topic = {}, partition = {}, offset = {}, value size(bytes) = {}>",
                    rmd.topic(), rmd.partition(), rmd.offset(), rmd.serializedValueSize());
        } else {
            log.error("<Exception when send message>", exception);
        }
    };;

    public SimpleBatchProducer(Properties props, String topic) {
        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    // initial: check metadata, topic

    public void publishForget(List<NotificationDto> notifications) {
        List<ProducerRecord<String, String>> messages = notifications.stream()
                .map(dto -> {
                    String key = dto.getReceiveUserId() + '_' + dto.getServiceName();
                    return new ProducerRecord<>(topic, key, JsonUtil.toJson(dto));
                }).collect(Collectors.toList());

        for (ProducerRecord<String, String> msg : messages) {
            producer.send(msg);     // Fire and forget result
        }
    }

    public void publishAsync(List<NotificationDto> notifications) {
        List<ProducerRecord<String, String>> messages = notifications.stream()
                .map(dto -> {
                    String key = dto.getReceiveUserId() + '_' + dto.getServiceName();
                    return new ProducerRecord<>(topic, key, JsonUtil.toJson(dto));
                }).collect(Collectors.toList());

        for (ProducerRecord<String, String> msg : messages) {
            producer.send(msg, sendHandler);     // Aync send with callback handler
        }
    }

    public void publishSync(List<NotificationDto> notifications) {
        List<ProducerRecord<String, String>> messages = notifications.stream()
                .map(dto -> {
                    String key = dto.getReceiveUserId() + '_' + dto.getServiceName();
                    return new ProducerRecord<>(topic, key, JsonUtil.toJson(dto));
                }).collect(Collectors.toList());

        List<Future<RecordMetadata>> results = Lists.newArrayListWithCapacity(messages.size());
        for (ProducerRecord<String, String> msg : messages) {
            results.add(producer.send(msg));
        }

        results.forEach(future -> {
            try {
                RecordMetadata rmd = future.get();      // Wait for result
                log.info("<Topic = {}, partition = {}, offset = {}, value size(bytes) = {}>",
                        rmd.topic(), rmd.partition(), rmd.offset(), rmd.serializedValueSize());
            } catch (InterruptedException | ExecutionException e) {
                log.error("<Unable to get record metadata>", e);
            }
        });
    }
}
