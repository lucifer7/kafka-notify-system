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
    private static SimpleBatchProducer simpleBatchProducer = new SimpleBatchProducer(props, props.getProperty("topic"));

    public static SimpleBatchProducer simpleBatchProducer() {
        return simpleBatchProducer;
    }
}
