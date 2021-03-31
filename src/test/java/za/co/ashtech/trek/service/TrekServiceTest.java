package za.co.ashtech.trek.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.ashtech.trek.db.repository.TrailDBRepository;
import za.co.ashtech.trek.model.Trail;
import za.co.ashtech.trek.model.User;
import za.co.ashtech.trek.util.TestDataUtil;
import za.co.ashtech.trek.util.TrekException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
 class TrekServiceTest {
	
	@Autowired
	TrekService service;	
	@Autowired
	TrailDBRepository trekDBRepository;
	static String trailName = null;
	
	@BeforeAll
	public static void setUp() {
		trailName = TestDataUtil.getTrailname();
	}
	
	@BeforeEach
	void validate() {
		assertNotNull(service);
	}
	
	@Test
	@Order(1) 
	void getRandomTrailTest() throws TrekException{				
		assertNotNull(service.getRandomHikeTrail());
	}
	
	@Test
	@Order(2) 
	void getLocationTrailsTest() throws TrekException{				
		assertNotNull(service.searchTrail("Bellville"));
	}
	
	@Test
	@Order(3) 
	void addTrailTest() throws TrekException{		
		Trail trail = new Trail();
		trail.setDescription("Flat road");
		trail.setLength("8KM");
		trail.setLevel("Walker");
		trail.setLocation("Bellville");
		trail.setName(trailName);
		trail.setStatus("O");
		
		service.addTrail(trail);
		
		assertNotNull(trekDBRepository.findByName(trailName));
	}
	
	@Test
	@Order(4) 
	void editTrailTest() throws TrekException{		
		
		Trail trail = service.getRandomHikeTrail();
		service.editTrail(trail.getId(), "location", trailName);
		
		assertEquals(trailName, trekDBRepository.findByName(trail.getName()).getLocation());
	}
	
	
	@Test
	@Order(5) 
	void deleteTrailTest() throws TrekException{		
		
		String id = service.getRandomHikeTrail().getId();
		service.deleteTrail(id);
		assertEquals(Optional.empty(),trekDBRepository.findById(new Long(id)));
	}
	
	@Test
	@Order(6) 
	void createUserTest() throws TrekException{		
		
		User user = new User();
		user.setUsername(TestDataUtil.getUsername());
		user.setPassword("password");
		user.setConfirmPassword("password");
		
		service.createUser(user);
	}
	
}
