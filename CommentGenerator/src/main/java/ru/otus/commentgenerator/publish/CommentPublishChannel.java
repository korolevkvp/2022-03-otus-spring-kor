/*
 * Created by DQCodegen
 */
package ru.otus.commentgenerator.publish;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


public interface CommentPublishChannel {

    @Output(CommentPublishChannelConstants.COMMENT)
    MessageChannel Comment();

}