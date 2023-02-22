package com.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {

        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        // close 메소드를 호출하기 위해서
        // applicationContext를 닫아야 하는데 닫는 메소드를 잘 사용하지 않음
        // 그래서 ac의 자료형을 AnnotationConfigApplicationContext로 바꾸거나
        // ConfigurableApplicationContext로 바꿔야 함

        // 보통 applicationContext를 사용할 때 close할 일이 없어서
        // 기본적인 인터페이스에서는 지원해주지 않는다. 하위까지 내려가야 한다.
        ac.close();


        // 맨 처음에 출력해봤더니 아래와 같은 결과가 나왔다.
        // 생성자 호출, url = null
        // connect:  null
        // call: null message = 초기화 연결 메시지

        // 당연한 결과다.
        // 객체 생성만 이뤄진 상태에서 출력된 결과이기 때문에
        // 아무런 url로 주입되지 않았다.

        // 인터페이스 설정한 후에
        /*
        생성자 호출, url = null - 생성자를 통한 객체 생성
        NetworkClient.afterPropertiesSet - 의존관계 주입에서 일어날 동작들
        connect:  http://hello-spring.dev -
        call: http://hello-spring.dev message = 초기화 연결 메시지

        10:21:24.978 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@49049a04, started on Wed Feb 22 10:21:24 KST 2023
           ==> closing 호출

        NetworkClient.destroy - 빈이 종료될 때 일어날 동작
        close:  http://hello-spring.dev

        이후에 스프링이 종료된다.
         */





    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
