package com.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 1번째 호출
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        // 2번째 호출
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUserPrototype() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        assertThat(count2).isEqualTo(1);

        // 아래와 같이 각각의 요청에 대해
        // 서로 다른 프로토타입 빈을 반환한 걸 알 수 있다.

        // PrototypeBean.init com.scope.SingletonWithPrototypeTest1$PrototypeBean@2f62ea70
        // PrototypeBean.init com.scope.SingletonWithPrototypeTest1$PrototypeBean@2a76840c

    }

    @Scope("singleton")
    static class ClientBean {

        // 생성자 주입으로 해도 된다.
        // 여기서는 간단한 테스트를 위해서 필드 주입으로 실행했다.

        // ObjectProvider를 쓴 경우
        // @Autowired
        // private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        // javax.inject.Provider를 쓴 경우
        @Autowired
        private Provider<PrototypeBean> prototypeBeanObjectProvider;

        public int logic() {

            // ObjectProvider를 쓴 경우
            // PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();

            // javax.inject.Provider를 쓴 경우
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean  {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
