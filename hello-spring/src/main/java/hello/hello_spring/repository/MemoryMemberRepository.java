package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();       // 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, 사용 고려
    private static long sequence = 0L;  // 키값 생성 (0, 1, 2, ...)  // 동시성 문제가 고려되어 있지 않음, 실무에서는 AtomicLong 사용 고려

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // null일 가능성이 있는 값을 객체로 감쌈
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    // 파라미터의 name과 일치하는지 확인
                .findAny(); // filter에서 조건을 만족하는 값을 하나라도 찾으면 Optional 반환, 없으면 null을 포함한 Optional 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
