/*
 * Created by DQCodegen
 */
package ru.otus.spring.subscribe;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.CommentRepositoryJpa;

@Service
@RequiredArgsConstructor
public class CommentSubscribeListenerServiceImpl implements CommentSubscribeListenerService {

    final CommentRepositoryJpa commentRepositoryJpa;
    Logger logger = LogManager.getLogger(CommentSubscribeListenerServiceImpl.class);

    @Override
    public void comment(Message<Comment> msg) {
        if (logger.isInfoEnabled()) {
            logger.info("CommentSubscribeListenerServiceImpl.comment: {}", msg);
        }
        commentRepositoryJpa.save(msg.getPayload());
    }

}