package com.example.spring21;

import static com.example.spring21.KafkaTopic.TOPIC_22;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@EnableBinding(KafkaTopic.class)
@Service
public class KafkaConsumer {

    private final  Logger log = getLogger(this.getClass());

    @StreamListener(value = TOPIC_22, condition = "headers['messageClass']=='messageClass'")
    public void listenCategory(Message<Pony> message) {
        log.info("Headers: ");
        message.getHeaders().forEach((k,v)-> log.info("    " + k + ": " + v));
        log.info("Body: ");
        log.info(message.getPayload().toString());
    }

    @StreamListener(value = TOPIC_22)
    public void listenBadHeaders(Message<Pony> message) {
        log.info("Bad headers: ");
        final MessageBuilder<Pony> decoded = MessageBuilder
                .withPayload(message.getPayload());
        message.getHeaders().forEach((k,v)-> {
            if (v instanceof byte[]) {
                decoded.setHeader(k, new String((byte[]) v));
            } else {
                decoded.setHeader(k,v);
            }
        });
        listenCategory(decoded.build());
    }
}