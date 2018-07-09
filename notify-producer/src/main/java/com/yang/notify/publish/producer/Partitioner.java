package com.yang.notify.publish.producer;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/6
 **/
public abstract class Partitioner {
    public abstract int partition(String key);
}
