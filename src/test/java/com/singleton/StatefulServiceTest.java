package com.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // A 사용자 -> 만원 주문
        statefulService1.order("userA", 10000);

        // B 사용자 ==> 2만원 주문
        statefulService2.order("userB", 20000);

        // A 사용자 주문 금액 조회
        // 원래라면 10000원을 조회해줘야 한다.
        // 하지만, 2만원으로 조회된다. 중간에 B가 바꿔버렸기 때문에.
        int price = statefulService1.getPrice();

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        // 공유필드를 꼭 조심하자.
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
