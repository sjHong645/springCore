package com.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        // 컴포넌트 스캔 대상에서
        // BeanA를 포함하도록 어노테이션을 붙였다. @MyIncludeComponent를 붙임
        // 그래서 ComponentFilterAppConfig 클래스에서 스캔되기 때문에 정상적으로 getBean이 동작함
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        // 컴포넌트 스캔 대상에서
        // BeanB는 제외하도록 어노테이션을 붙였다. @MyExcludeComponent를 붙임
        // 그래서 ComponentFilterAppConfig 클래스에서 스캔되지 않았기 때문에
        // getBean을 하면 예외가 발생함

        // BeanB beanB = ac.getBean("beanB", BeanB.class);
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));
    }


    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {
        // 내가 정의한 컴포넌트 스캔
    }

}
