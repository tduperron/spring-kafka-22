package com.example.spring22;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.stream.annotation.EnableBinding;

@RequiredArgsConstructor
@RestController
@EnableBinding({KafkaTopic.class})
public class PublishController {

    private final KafkaTopic topic;

    @GetMapping("/pony")
    public ResponseEntity<Pony> pony() {
        Pony pony = new Pony("Rainbow Dash 2.2");
        final Message<Pony> message = MessageBuilder
                .withPayload(pony)
                .setHeader("messageClass", "messageClass")
                .build();
        topic.output().send(message);
        return ResponseEntity.ok(pony);
    }
}
