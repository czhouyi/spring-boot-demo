package me.czhouyi.demo.start.interceptor;

import lombok.extern.slf4j.Slf4j;
import me.czhouyi.demo.application.basic.dto.ResultData;
import me.czhouyi.demo.domain.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 拦截器
 *
 * @author czhouyi@gmail.com
 */
@Aspect
@Component
@Slf4j
public class ControllerInterceptor {

    /**
     * 定义拦截规则：
     * 拦截controller包下面的所有类中有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* me.czhouyi.demo.start.controller..*(..)) " +
            "&& @within(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器:
     * 记录请求日志
     * 拦截异常
     *
     * @param pjp 参数
     * @return Object 被拦截方法的执行结果
     */
    @Around("controllerMethodPointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();

        Object retObj;
        try {
            retObj = pjp.proceed();
        } catch (Throwable e) {
            if (e instanceof BusinessException) {
                retObj = ResultData.fail(e.getMessage());
                log.error(className + "#" + methodName + "请求业务异常，错误：" + e.getMessage());
            } else {
                log.error(className + "#" + methodName + "请求异常，错误：" + e.getMessage(), e);
                retObj = ResultData.fail(e.getMessage());
            }
        }

        long costMs = System.currentTimeMillis() - beginTime;
        log.info("{}#{}请求结束，耗时：{}ms", className, methodName, costMs);

        return retObj;
    }
}