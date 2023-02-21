package com.autowired;

import java.util.List;
import java.util.Map;

import com.springCore.AutoAppConfig;
import com.springCore.discount.DiscountPolicy;
import com.springCore.member.Grade;
import com.springCore.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

    @Test
    void findAllBean() {

        // DiscountService에서 스프링 빈을 가져오도록 했다.
        // 하지만, 아무것도 없다.
        // ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class);

        // 그래서 AutoAppConfig를 통해서도 스프링 빈을 가져올 수 있도록 했다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // map과 list의 출력 결과
        // policyMap = {fixDiscountPolicy=com.springCore.discount.FixDiscountPolicy@7fe7c640, rateDiscountPolicy=com.springCore.discount.RateDiscountPolicy@4c4748bf}
        // policyList = [com.springCore.discount.FixDiscountPolicy@7fe7c640, com.springCore.discount.RateDiscountPolicy@4c4748bf]

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member memberA = new Member(1L, "userA", Grade.VIP);

        // 할인 정책으로 fixDiscountPolicy를 적용했음
        int discountPrice = discountService.discount(memberA, 10000, "fixDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        // 할인 정책으로 rateDiscountPolicy를 적용했음
        int rateDiscount = discountService.discount(memberA, 20000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscount).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }


}