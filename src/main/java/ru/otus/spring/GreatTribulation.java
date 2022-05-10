package ru.otus.spring;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.domain.Human;
import ru.otus.spring.domain.Judgment;

@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class GreatTribulation {

    private static final String[] humanNames = {"Matthew", "Mark", "Luke", "John", "Peter", "Job", "Benjamin", "David", "Ruth", "Esther", "Mary"};

    @Bean
    public QueueChannel humanChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel judgmentChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers
                .fixedRate(10)
                .maxMessagesPerPoll(2)
                .get();
    }

    @Bean
    public IntegrationFlow judgeFlow() {
        return IntegrationFlows.from("humanChannel")
                .handle("jesusJudgeService", "judge")
                .channel("judgmentChannel")
                .get();
    }

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(GreatTribulation.class);

        Jesus jesus = context.getBean(Jesus.class);

        while (true) {
            Thread.sleep(1000);

            Human human = generateHuman();
            System.out.println(human.getName() + ", you are " +  (human.getIsRighteous() ? "righteous" : "unrighteous") + ".");
            Judgment judgment = jesus.judge(human);
            System.out.println(judgment.getHumanName() + ", your judgment is " + judgment.getDecision());
        }
    }

    private static Human generateHuman() {
        return new Human(humanNames[RandomUtils.nextInt(1, humanNames.length)], RandomUtils.nextBoolean());
    }

}
