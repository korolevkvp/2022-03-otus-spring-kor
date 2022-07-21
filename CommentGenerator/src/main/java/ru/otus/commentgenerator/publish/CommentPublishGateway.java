/*
 * Created by DQCodegen
 */
package ru.otus.commentgenerator.publish;

import lombok.Generated;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import ru.otus.commentgenerator.domain.Comment;

@MessagingGateway
@Generated
public interface CommentPublishGateway {

    @Gateway(requestChannel = CommentPublishChannelConstants.COMMENT)
    void comment(Message<Comment> msg);

}

