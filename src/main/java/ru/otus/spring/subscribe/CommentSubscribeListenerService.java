/*
 * Created by DQCodegen
 */
package ru.otus.spring.subscribe;

import org.springframework.messaging.Message;
import ru.otus.spring.domain.Comment;


public interface CommentSubscribeListenerService {

    void comment(Message<Comment> msg);

}