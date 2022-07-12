/*
 * Created by DQCodegen
 */
package ru.otus.spring.subscribe;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;


public interface CommentSubscribeChannel {

    @Input(CommentSubscribeChannelConstants.COMMENT)
    SubscribableChannel comment();

}