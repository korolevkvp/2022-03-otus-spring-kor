/*
 * Created by DQCodegen
 */
package ru.otus.spring.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.otus.spring.domain.Comment;

@Configuration
public class CommentSubscribeListener {

    @Autowired
    private CommentSubscribeListenerService listenerService;

    @StreamListener(CommentSubscribeChannelConstants.COMMENT)
    void comment(Message<Comment> msg) {
        listenerService.comment(msg);
    }

}