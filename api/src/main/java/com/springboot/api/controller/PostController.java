package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api") // 공통 URL 설정
public class PostController {

    // @RequestMapping 이용
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample(){
        return "Hello Post API";
    }

    // @RequestBody 이용, HTTP의 Body 내용을 Map 객체에 매핑
    // http://localhost:8080/api/v1/post-api/member
    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    /*
    param.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));
    */

    // DTO 객체 이용
    // http://localhost:8080/api/v1/post-api/member2
    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody MemberDto memberDTO) {
        return memberDTO.toString();
    }

}
