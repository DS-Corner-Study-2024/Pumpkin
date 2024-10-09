package hello.hello_spring.controller;

public class MemberForm {
    private String name;    // name 속성 값이 name인 input 태그와 연결

    public String getName() {
        return name;
    }

    public void setName(String name) {  // spring이 setName() 호출
        this.name = name;
    }
}