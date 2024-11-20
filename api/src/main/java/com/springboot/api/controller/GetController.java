package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/get-api")  // 공통 URL 설정
public class GetController {

    // Logger 선언
    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    // @RequestMapping 이용 GET 요청
    // http://localhost:8080/api/v1/get-api/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        // 예제 5.27
        LOGGER.info("getHello 메소드가 호출되었습니다.");
        return "Hello World";
    }

    // 매개변수 없는 요청
    // http://localhost:8080/api/v1/get-api/name
    @GetMapping(value = "/name")
    public String getName() {
        // 로그 출력 코드
        LOGGER.info("getName 메소드가 호출되었습니다.");
        return "Flature";
    }

    // @PathVariable 이용, URL에 매개변수 받음
    // http://localhost:8080/api/v1/get-api/variable1/{String 값}
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        // 변수 값을 로그에 출력
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

    // @PathVariable에 변수명 매핑
    // http://localhost:8080/api/v1/get-api/variable2/{String 값}
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    // @RequestParam 이용, URL ? 우측에 {키}={값} 형태로 전달
    // http://localhost:8080/api/v1/get-api/request2?name=flature&email=thinkground.flature@gmail.com&organization=thinkground
    @ApiOperation(value = "GET 메소드 예제", notes = "@RequestParam을 활용한 GET Method")    // Swagger - @ApiOperation: API 설명
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @ApiParam(value = "이름", required = true) @RequestParam String name,     // Swagger - @ApiParam: 매개변수 설명 및 설정
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    // @RequestParam 이용, 매개변수 항목이 일정하지 않을 때 Map 객체 이용
    // http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // DTO 객체 이용
    // http://localhost:8080/api/v1/get-api/request3?name=value1&email=value2&organization=value3
    @ApiOperation(value = "GET 메소드 예제", notes = "DTO를 활용한 GET Method")
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDTO) {
        return memberDTO.toString();
    }
}