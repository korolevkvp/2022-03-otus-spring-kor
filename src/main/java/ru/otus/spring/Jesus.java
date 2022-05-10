package ru.otus.spring;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.domain.Human;
import ru.otus.spring.domain.Judgment;

@MessagingGateway
public interface Jesus {

    @Gateway(requestChannel = "humanChannel", replyChannel = "judgmentChannel")
    Judgment judge(Human human);

}
