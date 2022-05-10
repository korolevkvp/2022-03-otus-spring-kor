package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Human;
import ru.otus.spring.domain.Judgment;

@Service
public class JesusJudgeService {

    public Judgment judge(Human human) throws Exception {
        System.out.println("Judgment in process...");
        Thread.sleep(3000);
        String decision;
        if (human.getIsRighteous()) decision = "Everlasting life";
        else decision = "Death";
        return new Judgment(human.getName(), decision);
    }
}
