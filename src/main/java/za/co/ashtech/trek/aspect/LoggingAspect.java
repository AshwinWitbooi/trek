package za.co.ashtech.trek.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;

@Aspect
@Configuration
public class LoggingAspect {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* za.co.ashtech.trek.controller.*.*(..))")
	public void aroundControllerLayerPC() {}
	
	@Around("aroundControllerLayerPC()")
	public Object aroundServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("CONTROLLER LAYER AROUND ADVICE");
		
		Object response = null;
		HttpServletRequest request = null;
		String httpMethod = null;
				
		try {

			request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			
			if(request != null) {
				 httpMethod = request.getMethod();
			}
											
			ObjectMapper objectMapper = new ObjectMapper();

			if(httpMethod != null && httpMethod.equalsIgnoreCase("POST")) {
				Object body = joinPoint.getArgs()[0];
				
				logger.info("REQUEST: "+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));			
			}
			
			response = joinPoint.proceed();	
			
			logger.info("RESPONSE: "+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));	

		} catch (JsonProcessingException | NullPointerException e) {
			logger.error(e.getMessage());
		}		
		
		
		return response;

	}

}
