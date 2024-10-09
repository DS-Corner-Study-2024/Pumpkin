package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // @Component 어노테이션이 등록 되어있음
public class MemberController {

    // @Autowired   // 필드 주입
    private final MemberService memberService;

    @Autowired  // 컴포넌트 스캔과 자동 의존관계 설정: 컨테이너에 있는 Service 주입
    public MemberController(MemberService memberService) {  // 생성자 주입
        this.memberService = memberService;
    }

//    @Autowired
//    public void setMemberService(MemberService memberService) {   // Setter 주입
//        this.memberService = memberService;
//    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";    // home으로 리다이렉트
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}