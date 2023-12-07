package rj;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {
//    @Before("execution(* rj.daoes..*(..))") // 切入点表达式，匹配 UserService 的 getUserById 方法
//    public void logDaoBefore(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//        System.out.println("Method Name: " + methodName);
//        System.out.println("Arguments:");
//
//        for (Object arg : args) {
//            System.out.println(arg);
//        }
        // 这里可以将日志记录到文件、数据库或其他目的地
//    }
}
