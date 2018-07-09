package com.yang.notify.publish.producer;

/**
 * Usage: <b> </b>
 *
 * @author Jingyi.Yang
 * Date 2018/7/6
 **/
public class HashPartitioner extends Partitioner {
    private final int partitionCount;

    public HashPartitioner(int partitionCount) {
        this.partitionCount = partitionCount;
    }

    @Override
    public int partition(String key) {
        return null == key ? 0 : key.hashCode() % partitionCount;
    }
}
