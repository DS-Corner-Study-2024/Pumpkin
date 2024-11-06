package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA가 SpringDataJpaMemberRepository를 스프링 빈으로 자동 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL: select m from Member m where m.name = ?
    Optional<Member> findByName(String name);
}
