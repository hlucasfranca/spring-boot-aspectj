package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Aspect
@ConfigurationProperties("interceptor")
@Component
public class Interceptor {

	/**
	 * Message to print on startup
	 */
	private String message = "Started";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Around("within(com.example..*)")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("Before aroundAdvice method:"+proceedingJoinPoint.getSignature());
		long methodStaringSystemTime = System.currentTimeMillis();
		Object object = null;


		Object[] signatureArgs = proceedingJoinPoint.getArgs();
		for (Object signatureArg: signatureArgs) {
			System.out.println("Arg: " + signatureArg);
		}

		try {
			object = proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			System.out.println("In around advice method:"+throwable.getMessage());
		}
		System.out.println("After aroundAdvice method:"+proceedingJoinPoint.getSignature());
		long methodEndingSystemTime = System.currentTimeMillis();
		System.out.println("Total execution time of aroundAdvice method is:"+(methodEndingSystemTime-methodStaringSystemTime)+" ms");
		return object;
	}

//	@Around("execution(* *.*(..)) && within(com.example..*)")
//	public Object another(ProceedingJoinPoint joinPoint) throws Throwable {
//		Object result = joinPoint.proceed();
//		System.err.println(joinPoint.toShortString() + ": " + result);
//		return result;
//	}

//	@EventListener
//	public void started(ContextRefreshedEvent event) {
//		System.err.println(message + ": " + event);
//	}

}
