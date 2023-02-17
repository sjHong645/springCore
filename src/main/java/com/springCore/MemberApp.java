package com.springCore;

import com.springCore.member.Grade;
import com.springCore.member.Member;
import com.springCore.member.MemberService;
import com.springCore.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        // MemberService memberService = new MemberServiceImpl();

        // appConfig의 메소드 memberService를 통해서...
        // new MemberServiceImpl(new MemoryMemberRepository())가 return 되도록 함

        // MemoryMemberRepository를 MemberServiceImpl에 주입한 상태에서
        // MemberServiceImple의 기능을 사용할 수 있게 되었다

        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());

    }
}
