package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class LogPrintBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(
			Method method // add()
			, Object[] args	// 3, 5
			, Object target	// 핵심 기능을 구현한 실제 객체
			) throws Throwable {
		
		String methodName = method.getName();
		Log log = LogFactory.getLog(this.getClass());
		// 인증 처리 부분 코딩
		log.info(">>" + methodName + "() : LogPrintBeforeAdvice 호출");
		
	} // before

} //  class
