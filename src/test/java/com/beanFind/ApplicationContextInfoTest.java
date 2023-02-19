package com.beanFind;

import com.springCore.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() { // 내가 등록한 스프링 빈이 제대로 등록되었느지 확인하는 test

        // ApplicationContext를 통해 모든 빈의 이름을 배열로 저장함
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for(String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 bean 만 출력")
    void findApplicationBean() { // 내가 등록한 스프링 빈이 제대로 등록되었느지 확인하는 test

        // ApplicationContext를 통해 모든 빈의 이름을 배열로 저장함
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for(String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈

            // 내가 직접 등록하거나 외부에서 등록한 Bean 애플리케이션 인 경우
            // 내용을 출력하도록 했음
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }

        }
    }

}
