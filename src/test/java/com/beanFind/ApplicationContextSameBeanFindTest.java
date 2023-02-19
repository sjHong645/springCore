package com.beanFind;

import java.util.Map;

import com.springCore.AppConfig;
import com.springCore.member.Member;
import com.springCore.member.MemberRepository;
import com.springCore.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    // 기존에 설정한 AppConfig에는 중복된 타입이 없는 상태이다.
    // 그래서, 중복된 타입으로 조회할 때 어떻게 될 지 알기 위해서 AppConfig를 수정해야 한다.

    // 그 작업은 귀찮으니까 별도의 Config 클래스를 만들기로 했다.
    // 그래서 ac에서 매개변수에 아무것도 전달하지 않음
    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }


    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류 발생")
    void findBeanByTypeDuplicate() {

        // MemberRepository 타입의 Bean을 조회하려 한다.
        // 하지만, 설정 클래스를 보면 중복된 걸 알 수 있다.
        // 그래서 실행하면 오류가 발생한다.

        // MemberRepository bean = ac.getBean(MemberRepository.class);

        // 예외가 발생하면 테스트가 성공한다.
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));

    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름으로 지정하면 된다.")
    void findBeanByName() {

        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);

        assertThat(memberRepository).isInstanceOf(MemberRepository.class);

    }

    @Test
    @DisplayName("특정 타입 모두 조회하기")
    void findAllBeanByType() {

        // MemberRepository 타입을 갖는 bean을 모두 조회
        // 여기서는 2개의 값이 나올 것이다.
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        for(String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }

        System.out.println("beansOfType = " + beansOfType);

        assertThat(beansOfType.size()).isEqualTo(2);
    }


}
