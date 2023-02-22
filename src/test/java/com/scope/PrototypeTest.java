package com.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    // find prototypeBean1
    // PrototypeBean.init - 이때, 새로운 객체가 생성되면서 init이 호출됨

    // find prototypeBean2
    // PrototypeBean.init - 이때, 새로운 객체가 생성되면서 init이 호출됨

    // 서로 다른 주소값이 나온다.
    // prototypeBean1 = com.scope.PrototypeTest$PrototypeBean@4b41dd5c
    // prototypeBean2 = com.scope.PrototypeTest$PrototypeBean@3b96c42e

    // org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@6253c26, started on Wed Feb 22 14:05:09 KST 2023

    // 그리고 destroy도 호출되지 않았다.

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);


        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
