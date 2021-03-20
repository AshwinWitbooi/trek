package za.co.ashtech.trek;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@WebAppConfiguration
class TrekApplicationTests {
	
//	private static final String testUsername ="test_user";
//	private static final String testPassword ="test_user";
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;


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

}
