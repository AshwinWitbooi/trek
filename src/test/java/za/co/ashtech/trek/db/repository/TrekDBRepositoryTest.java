package za.co.ashtech.trek.db.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.ashtech.trek.db.entity.TrailEntity;
import za.co.ashtech.trek.util.TestDataUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
 class TrekDBRepositoryTest {
	
	@Autowired
	TrailDBRepository trekDBRepository;
	static String trailName = null;
	
	@BeforeAll
	public static void setUp() {
		trailName = TestDataUtil.getTrailname();
	}
	
	@BeforeEach
	void validate() {
		assertNotNull(trekDBRepository);
	}
	
	@Test
	@Order(1) 
	void getRandomTrailTest() throws Exception{		
		TrailEntity te = new TrailEntity();
		te.setDecription("decription");
		te.setLength("5KM");
		te.setLevel("Beginner");
		te.setLocation("Bellville");
		te.setName(trailName);
		te.setStatus("O");

		trekDBRepository.save(te);
		
		assertNotNull(trekDBRepository.findByName(trailName));
	}
	
	@Test
	@Order(2) 
	void getTrailsByLocationTest() throws Exception{
		assertNotNull(trekDBRepository.findByLocation("Bellville"));
	}
}
