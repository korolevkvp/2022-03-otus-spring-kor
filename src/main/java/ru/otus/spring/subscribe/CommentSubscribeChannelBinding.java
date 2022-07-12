/*
 * Created by DQCodegen
 */
package ru.otus.spring.subscribe;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(CommentSubscribeChannel.class)
public class CommentSubscribeChannelBinding {
}