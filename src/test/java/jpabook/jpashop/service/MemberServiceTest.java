package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

//    @Autowired
//    EntityManager em;

    @Test
    @DisplayName("회원가입")
    @Rollback(false)
    public void join() {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
//        em.flush();
        assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }

    @Test
    @DisplayName("중복 회원 예외")
    public void validateDuplicateMember() {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when & then
        assertThatThrownBy(() -> {
            memberService.join(member1);
            memberService.join(member2);
        }).isInstanceOf(IllegalStateException.class);
    }
}