package com.springCore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
        // 컴포넌트 스캔(@Component가 붙은 클래스)을 위해 붙이는 어노테이션
        // 여기서는 @Configuration이 붙은 클래스를 제외한
        // @Component가 붙은 클래스를 스캔할 것이다.

        // 수동으로 설정한 설정 정보들을 스캔하지 않도록 하기 위해서 적음
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {



}
