### notify-producer
This module publishes notification to Kafka, using Apache Kafka client.    
SimpleBatchProducer is a single-thread producer, publishing notification by default partitioner(key: receiverId + serviceName). This guarantees the local order of messages on each partition.

3 ways to handler result of sending message:
- Fire and Forget
- Wait for result(by Future.get())
- Callback handler

### notify-consumer
This module subscribes notification and send to user. Max parallelism is limited by partition number. The message polling and processing should be synchronous, to make sure the order of receiving notification.

SimpleConsumer will subscribe to the topic and put received messages to a blocking queue.  
NotificationProcessor will drain messages from queue and process them(simplify by print to log).  
Using single thread to assure the process order.

For multiple consumer, may use multi-thread, refer to [Create Multi-threaded Apache Kafka Consumer](https://howtoprogram.xyz/2016/05/29/create-multi-threaded-apache-kafka-consumer/)