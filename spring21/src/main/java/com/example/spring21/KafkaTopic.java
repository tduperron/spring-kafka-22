package com.example.spring21;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaTopic {

    String TOPIC_22 = "my-little-topic_22";
    String TOPIC_21 = "my-little-topic_21";

    @Input(TOPIC_22)
    SubscribableChannel input();

    @Output(TOPIC_21)
    MessageChannel output();

}