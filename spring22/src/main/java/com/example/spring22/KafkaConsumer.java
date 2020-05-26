package com.example.spring22;

import static com.example.spring22.KafkaTopic.TOPIC_21;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@EnableBinding(KafkaTopic.class)
@Service
public class KafkaConsumer {

    private final  Logger log = getLogger(this.getClass());

    @StreamListener(value = TOPIC_21)
    public void listenCategory(Message<Pony> message) {
        log.info("Headers: ");
        message.getHeaders().forEach((k,v)-> log.info("    " + k + ": " + v));
        log.info("Body: ");
        log.info(message.getPayload().toString());
    }
}