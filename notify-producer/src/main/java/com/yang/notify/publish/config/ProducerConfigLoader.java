package com.yang.notify.publish.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/5
 **/
@Slf4j
public class ProducerConfigLoader {
    public static Properties loadProps() {
        Properties props = new Properties();
        try {
            props.load(ProducerConfigLoader.class.getResourceAsStream("/kafka-producer.properties"));
        } catch (IOException e) {
            log.error("<Load kafka-producer.properties failed>", e);
        }
        return props;
    }
}
