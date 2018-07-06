package com.yang.notify.publish.producer;

import com.yang.notify.publish.common.JsonUtil;
import com.yang.notify.publish.dto.NotificationDto;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/5
 **/
public class NotificationProducer {
    private final KafkaProducer<String, String> producer;
    private final String topic;

    public NotificationProducer(Properties props, String topic) {
        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    // initial: check metadata, topic

    public void publish(List<NotificationDto> notifications) {
        List<ProducerRecord<String, String>> messages = notifications.stream()
                .map(dto -> {
                    String key = dto.getReceiveUserId() + '_' + dto.getServiceName();
                    return new ProducerRecord<>(topic, key, JsonUtil.toJson(dto));
                }).collect(Collectors.toList());

        List<>
        for (ProducerRecord<String, String> msg : messages) {
            Future<RecordMetadata> result = producer.send(msg);
        }
    }
}
