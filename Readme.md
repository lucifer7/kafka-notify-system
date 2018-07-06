### notify-producer
This module publishes notification to Kafka, using Apache Kafka client.    
SimpleBatchProducer is a single-thread producer, publishing notification by default partitioner(key: receiverId + serviceName). This guarantees the local order of messages on each partition.

### notify-consumer(tbc)
This module subscribes notification and send to user. Max parallelism is limited by partition number. The message polling and processing should be synchronous, to make sure the order of receiving notification.