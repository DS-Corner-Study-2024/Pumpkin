package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

// @Component   // 직접 Bean 등록 하였으므로 @Component 어노테이션 삭제.
@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.hello_spring..*(..)) && !target(hello.hello_spring.SpringConfig)")   // hello_spring 패키지 하위에 모두 적용. SpringConfig 순환참조 제거.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
