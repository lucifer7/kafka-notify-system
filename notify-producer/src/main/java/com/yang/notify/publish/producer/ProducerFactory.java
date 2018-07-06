package com.yang.notify.publish.producer;

import com.yang.notify.publish.config.ProducerConfigLoader;

import java.util.Properties;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/6
 **/
public class ProducerFactory {
    private static Properties props = ProducerConfigLoader.loadProps();

    public static SimpleBatchProducer simpleBatchProducer() {
        return new SimpleBatchProducer(props, props.getProperty("topic"));
    }
}
