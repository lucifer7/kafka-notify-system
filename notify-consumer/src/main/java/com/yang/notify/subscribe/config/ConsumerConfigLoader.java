package com.yang.notify.subscribe.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/10
 **/
@Slf4j
public class ConsumerConfigLoader {
    public static Properties loadProps() {
        Properties props = new Properties();
        try {
            props.load(ConsumerConfigLoader.class.getResourceAsStream("/kafka-consumer.properties"));
        } catch (IOException e) {
            log.error("<Load kafka-consumer.properties failed>", e);
        }
        return props;
    }
}
