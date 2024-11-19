package org.doit.ik.aop2.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class LogPrintAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		// long start = System.nanoTime(); 동일하다.
		StopWatch sw = new StopWatch();
		sw.start();
		
		Log log = LogFactory.getLog(this.getClass());
		String methodName = method.getMethod().getName();
		log.info(">" + methodName + "() start");
		// 어떤걸 반환할지 몰라 오브젝트다.
		Object result = method.proceed(); // 실제 핵심 기능들이 처리된다.
		
		log.info(">" + methodName + "() end");
		
		sw.stop();
		log.info(">" + methodName + "() 처리 시간 : " + sw.getTotalTimeMillis() + "ms");
		
		return result;
	}

} //  class
