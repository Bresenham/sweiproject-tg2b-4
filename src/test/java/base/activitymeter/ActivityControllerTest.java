package base.activitymeter;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActivityControllerTest {

	private static int id = 0;
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void listAll() throws Exception {
    mockMvc.perform(get("/activity")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string("[]"));
  }

  @Test
  public void listAllValid() throws Exception {
    mockMvc.perform(get("/activity/allValid/false")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string("[]"));
  }

  @Test
  public void createOne() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"key\":12345,\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    id ++;
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
  }

  @Test
  public void createMultiple() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response1 = mockMvc
        .perform(post("/activity")
            .content("{\"key\":\"12345\",\"title\":\"Test1\",\"text\":\"test test1\",\"tags\":\"test1\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    mockMvc.perform(post("/activity/test/addKey/12346"))
	  .andExpect(status().isOk());
  MockHttpServletResponse response2 = mockMvc
        .perform(post("/activity")
            .content("{\"key\":\"12346\",\"title\":\"Test2\",\"text\":\"test test2\",\"tags\":\"test2\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();

    id ++;
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    id ++;
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
  }

  @Test
  public void findOne() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"key\":\"12345\",\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    id ++;
    mockMvc.perform(get("/activity/" + id)).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
  }
  
  @Test
  public void findOneByAdminkey() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"key\":12345,\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    id ++;
    mockMvc.perform(get("/activity/" + id + "/999999")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
  }

  @Test
  public void findOneFailed() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"key\":12345,\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    id ++;
    mockMvc.perform(get("/activity/0")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
  }

  @Test
  public void deleteOne() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"key\":12345,\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    id ++;
    mockMvc.perform(delete("/activity/" + id + "/999999")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/test/removeAll"));
  }

  @Test
  public void updateOne() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"key\":12345,\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    id ++;
    mockMvc.perform(put("/activity/" + id)
        .content("{\"key\":999999,\"title\":\"TestTest\",\"text\":\"test test\",\"tags\":\"test\"}")
        .contentType("application/json")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
  }

  @Test
  public void updateOneFailed() throws Exception {
    mockMvc.perform(put("/activity/" + 0)
        .content("{\"key\":999999,\"title\":\"TestTest\",\"text\":\"test test\",\"tags\":\"test\"}")
        .contentType("application/json")).andExpect(status().isOk());
  }

  @Test
  public void findByTag() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"key\":12345,\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    id ++;
    mockMvc.perform(get("/activity/listByTag/test")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
  }

  @Test
  public void findByCategory() throws Exception {
	  mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"key\":12345,\"title\":\"Test\",\"text\":\"test test\",\"category\":\"Students\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    id ++;
    mockMvc.perform(get("/activity/listByCategory/Students")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
  }

  @Test
  public void getCategories() throws Exception {
    mockMvc.perform(get("/activity/getCategories"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string("[\"Students\",\"Teachers\",\"Travelling\",\"Business\",\"Enterpreneurship\"]"));
  }
  
	@Test
	public void testSend() throws Exception {
      String json = "\"x@y.z\"";
      mockMvc.perform(post("/SendMail").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk());  
	}

	@Test
	public void testAdd1() throws Exception {
      String json = "{\"key\": 12345,\"text\": \"hallo\",\"tags\": [\"t1\",\"t2\",\"t3\"],\"title\": \"titel\"}";
      mockMvc.perform(post("/activity/test/addKey/12345"))
	  .andExpect(status().isOk());
      MockHttpServletResponse response = mockMvc.perform(post("/activity")
    		  .contentType(MediaType.APPLICATION_JSON)
    		  .content(json))
    		  .andExpect(status().isOk())
    		  .andReturn().getResponse();
      id ++;
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeAll"));
	}

}
