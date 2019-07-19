package qms.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ContextCheckAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("@annotation(feng.app.annotation.MethodContextCheck)")
	private void checkPoint(){}

	@Before(value="checkPoint()")
    public void before(){
        System.out.println("前置通知....");
    }
	
	@Around(value="checkPoint()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		//获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();
        //获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();
        
	    logger.info("Around Before:{},{}",clazzName, methodName);
	    //执行目标函数
	    Object obj= (Object) joinPoint.proceed();
	    logger.info("Around After:{},{}",clazzName,methodName);
	    return obj;
	}
}
