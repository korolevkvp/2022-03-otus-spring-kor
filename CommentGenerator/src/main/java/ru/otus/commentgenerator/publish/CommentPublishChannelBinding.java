/*
 * Created by DQCodegen
 */
package ru.otus.commentgenerator.publish;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBinding(CommentPublishChannel.class)
public class CommentPublishChannelBinding {
}