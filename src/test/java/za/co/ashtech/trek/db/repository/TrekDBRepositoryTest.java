package za.co.ashtech.trek.db.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import za.co.ashtech.trek.db.entity.TrailEntity;
import za.co.ashtech.trek.db.entity.UserEntity;
import za.co.ashtech.trek.db.entity.UserRoleEntity;
import za.co.ashtech.trek.util.TestDataUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
 class TrekDBRepositoryTest {
	
	@Autowired
	TrailDBRepository trekDBRepository;
	@Autowired
	UserDBRepository userDBRepository;
	static String updateName = null;
	static String trailName = null;
	
	@BeforeAll
	public static void setUp() {
		trailName = TestDataUtil.getTrailname();
		updateName = TestDataUtil.getTrailname();
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
	
	@Test
	@Order(3) 
	void editTrailTest() throws Exception{
		TrailEntity trailEntity = trekDBRepository.findByName(trailName);
		trailEntity.setName(updateName);
		
		trekDBRepository.save(trailEntity);
		
		assertNotNull(trekDBRepository.findByName(updateName));
	}
	
	@Test
	@Order(4) 
	void deleteTrailTest() throws Exception{
		TrailEntity trailEntity = trekDBRepository.findByName(updateName);
		
		trekDBRepository.delete(trailEntity);
		
		assertNull(trekDBRepository.findByName(updateName));
	}
	
	@Test
	@Order(5) 
	void addUserTest() throws Exception{
		BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder(10, SecureRandom.getInstanceStrong());
		UserEntity user = new UserEntity();
		user.setEnabled(new Byte("1"));
		user.setUsername(TestDataUtil.getUsername());
		user.setPassword(BCryptPasswordEncoder.encode("ADMIN"));
		
		List<UserRoleEntity> roles = new ArrayList<>();
		UserRoleEntity roleEntity = new UserRoleEntity();
		roleEntity.setAuthority("ADMIN");
		roleEntity.setTrekUser(user);
		user.setTrekRoles(roles);
		
		userDBRepository.save(user);
		
		assertNull(trekDBRepository.findByName(updateName));
	}
}
