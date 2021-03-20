package za.co.ashtech.trek.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ch.qos.logback.classic.Logger;
import za.co.ashtech.trek.db.entity.TxLogEntity;
import za.co.ashtech.trek.db.repository.TxLogDBRepository;

@Aspect
@Configuration
public class TxLogAspect {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(TxLogAspect.class);
	
	@Autowired
	private TxLogDBRepository txLogDBRepository;
	private String signature = null;
	
	@Pointcut("execution(* za.co.ashtech.trek.service.TrekServiceImpl.*(..))")
	public void aroundServiceLayerPC() {}
	
	@Around("aroundServiceLayerPC()")
	public Object aroundServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("SERVICE LAYER AROUND ADVICE");
		
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		 String username = null;
//		if (principal instanceof UserDetails) {
//		  username = ((UserDetails)principal).getUsername();
//		} else {
//		  username = principal.toString();
//		}
		
		Object response = null;
		
		this.signature =  joinPoint.getSignature().toShortString().replace("TrekServiceImpl.", "").replace("(..)", "").replace("()", "");

		
		response = joinPoint.proceed();		
		
		TxLogEntity logEntity = new TxLogEntity();
		logEntity.setAction(this.getAction(this.signature));
		logEntity.setActionDate(new Date());
		logEntity.setActionResult("S");
		logEntity.setUsername("username");
		
		txLogDBRepository.save(logEntity);
		
		return response;

	}
	
	@AfterThrowing (pointcut = "aroundServiceLayerPC()", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
		logger.info("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
        
		TxLogEntity logEntity = new TxLogEntity();
		logEntity.setAction(this.getAction(this.signature));
		logEntity.setActionDate(new Date());
		logEntity.setActionResult("F");
		logEntity.setUsername("ash@ashtech.co.za");
		
		txLogDBRepository.save(logEntity);
		
		throw ex;
    }
	
	String getAction(String methodSignature){
		
		switch(methodSignature) {
		  case "getRandomHikeTrail":
		    return "RAN";
		  case "searchTrail":
			    return "SEL";	    
		  default:
		   return "ANO";
		}
		
	}


}
