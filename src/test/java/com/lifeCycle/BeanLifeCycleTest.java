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
