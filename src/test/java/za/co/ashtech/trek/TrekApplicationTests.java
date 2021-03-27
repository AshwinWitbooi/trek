package za.co.ashtech.trek;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.ashtech.trek.model.Trail;
import za.co.ashtech.trek.service.TrekService;
import za.co.ashtech.trek.util.TestDataUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@WebAppConfiguration
class TrekApplicationTests {
	
//	private static final String testUsername ="test_user";
//	private static final String testPassword ="test_user";
	@Autowired
	private WebApplicationContext context;
	@Autowired
	TrekService service;
	
	private MockMvc mvc;
	static String trailName = null;
	
	@BeforeAll
	public static void setUp() {
		trailName = TestDataUtil.getTrailname();
	}


	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				/* only applicable for security tests */ 
//				.apply(springSecurity())
				.build();
	}
	
	@Test
	@Order(1)
	/* only applicable for security tests */
//	@WithMockUser(username = "test_user", password = "test_user")
	void getRandomHikeTrailTest() throws Exception {
		
		assertNotNull(mvc.perform(get("/v1/trail/random")
		           .contentType(MediaType.APPLICATION_JSON)
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk()));
	}
	
	@Test
	@Order(2)
	/* only applicable for security tests */
//	@WithMockUser(username = "test_user", password = "test_user")
	void getSearchHikeTrailTest() throws Exception {
		
		assertNotNull(mvc.perform(get("/v1/trail/Bellville")
		           .contentType(MediaType.APPLICATION_JSON)
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk()));
	}
	
	@Test
	@Order(3)
	/* only applicable for security tests */
//	@WithMockUser(username = "test_user", password = "test_user")
	void addTrailTest() throws Exception {
		
		Trail trail = new Trail();
		trail.setDescription("Flat road");
		trail.setLength("8KM");
		trail.setLevel("Walker");
		trail.setLocation("Bellville");
		trail.setName(trailName);
		trail.setStatus("O");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		assertNotNull(mvc.perform(post("/v1/admin/trail")
					.content(objectMapper.writeValueAsString(trail))
		           .contentType(MediaType.APPLICATION_JSON)
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isCreated()));
	}
	
	@Test
	@Order(4)
	/* only applicable for security tests */
//	@WithMockUser(username = "test_user", password = "test_user")
	void editTrailTest() throws Exception {	
		
		mvc.perform(patch("/v1/admin/update/trail/{id}",service.getRandomHikeTrail().getId())
			   .queryParam("name", "location")
			   .queryParam("value", "new location")
	           .contentType(MediaType.APPLICATION_JSON)
	           .accept(MediaType.APPLICATION_JSON))
	           .andExpect(status().isNoContent());
	}
	
	@Test
	@Order(4)
	/* only applicable for security tests */
//	@WithMockUser(username = "test_user", password = "test_user")
	void deleteTrailTest() throws Exception {
		mvc.perform(delete("/v1/admin/del/trail/{id}",service.getRandomHikeTrail().getId()))
	           .andExpect(status().isNoContent());
	}


}
