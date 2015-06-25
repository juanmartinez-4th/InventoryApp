package mx.com.mindbits.argos.inventory.webapp.logging;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	private Logger LOGGER = Logger.getLogger(LoggingAspect.class);

	public LoggingAspect() {
		LOGGER.info("Starting Logging Aspect");
	}
	
	@Before("execution(* mx.com.mindbits.argos.inventory.bsn.impl.*.*(..))")
	public void logServiceCall(JoinPoint joinPoint) throws Throwable {
		String serviceName = joinPoint.getSignature().toShortString();
		if(serviceName.endsWith("(..)")) {
			serviceName = serviceName.substring(0, serviceName.indexOf("(..)"));
		}
		
		LOGGER.info("EXECUTE " + serviceName + "(" + getArgumentsString(joinPoint) + ")");
	}
	
	@AfterReturning(pointcut = "execution(* mx.com.mindbits.argos.inventory.bsn.impl.*.*(..))",
			returning = "result")
	public void logServiceReturn(JoinPoint joinPoint, Object result) throws Throwable {
		String serviceName = joinPoint.getSignature().toShortString();
		if(serviceName.endsWith("(..)")) {
			serviceName = serviceName.substring(0, serviceName.indexOf("(..)"));
		}

		String logInfo = "RETURN from " + serviceName;
		if(result != null ) {
			if(result instanceof List) {
				List<?> resultList = (List<?>)result;
				
				if(!resultList.isEmpty()) {
					LOGGER.info(logInfo + ", Results found : " + resultList.size());
				}else {
					LOGGER.info(logInfo + ", no results found");
				}
			}else {
				LOGGER.info(logInfo + ", Result : " + result.toString());
			}
		}else {
			LOGGER.info(logInfo + ", no return value");
		}
		
	}

	@AfterThrowing(pointcut = "within(mx.com.mindbits.argos.inventory.bsn.impl.*)", 
			throwing = "exception")
	public void logServiceCallExceptions(JoinPoint joinPoint, Throwable exception) {
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();

		LOGGER.error("ERROR invoking service : " + className + "." + methodName + " : " + exception.getMessage(), exception);
	}
		
	private String getArgumentsString(JoinPoint joinPoint) {
		String arguments = "";
		Object[] args = joinPoint.getArgs();
		
		if(args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				arguments += args[i] + ", ";
			}
			
			arguments = arguments.substring(0, arguments.length() - 2);
		}
		
		return arguments;
	}
}
