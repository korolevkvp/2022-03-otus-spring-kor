package ru.otus.commentgenerator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.commentgenerator.domain.Comment;
import ru.otus.commentgenerator.publish.CommentPublishGateway;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CommentGeneratorServicePrimary implements CommentGeneratorService {

    final CommentPublishGateway commentPublishGateway;

    @Override
    public Comment generate() {
        commentPublishGateway.comment(MessageBuilder.withPayload(Comment.builder()
                        .author("Generator&Kafka")
                        .content(new Random().nextBoolean() ? "KafkaBad" : "KafkaGood")
                        .build())
                .build());
        return Comment.builder()
                .author("Generator")
                .content(new Random().nextBoolean() ? "Bad" : "Good")
                .build();
    }
}
