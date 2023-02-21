package com.autowired;

import java.util.Optional;

import com.springCore.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {

    @Test
    void AutowiredOption() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        // 여기서 Member는 스프링 빈 컨테이너에 저장되지 않는 클래스이다.

        // 그래서 Autowired를 통해 의존관계 주입을 실행하려고 하면 주입할 대상이 없는 상황이다.

        // 1번째
        // required = false로 해줌으로써 아예 메소드를 호출하지 않기
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 2번째
        // @Nullable를 덧붙여서 null값으로 입력하기
       @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 3번째
        // Optional로 감싸서 주입대상이 없다면 Optional.empty가 입력되도록 하기
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
