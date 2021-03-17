package za.co.ashtech.trek.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
 class TrekServiceTest {
	
	@Autowired
	TrekService service;
	
	@BeforeEach
	void validate() {
		assertNotNull(service);
	}
	
	@Test
	@Order(1) 
	void getTrailTest() throws Exception{				
		assertNotNull(service.getRandomHikeTrail());
	}
	
}
