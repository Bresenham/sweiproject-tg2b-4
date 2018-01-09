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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActivityControllerTest {

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
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    char id = response.getContentAsString().charAt(6);
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
  }

  @Test
  public void createMultiple() throws Exception {
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response1 = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test1\",\"text\":\"test test1\",\"tags\":\"test1\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    mockMvc.perform(post("activity/test/addKey/12346"))
	  .andExpect(status().isOk());
  MockHttpServletResponse response2 = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test2\",\"text\":\"test test2\",\"tags\":\"test2\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();

    char id1 = response1.getContentAsString().charAt(6);
    char id2 = response2.getContentAsString().charAt(6);
    mockMvc.perform(delete("/activity/" + id1 + "/999999"));
    mockMvc.perform(delete("/activity/" + id2 + "/999999"));
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
    mockMvc.perform(delete("/activity/test/removeKey/12346"));
  }

  @Test
  public void findOne() throws Exception {
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    char id = response.getContentAsString().charAt(6);
    mockMvc.perform(get("/activity/" + id)).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
  }
  
  @Test
  public void findOneByAdminkey() throws Exception {
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    char id = response.getContentAsString().charAt(6);
    mockMvc.perform(get("/activity/" + id + "/999999")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
  }

  @Test
  public void findOneFailed() throws Exception {
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    char id = response.getContentAsString().charAt(6);
    mockMvc.perform(get("/activity/0")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
  }

  @Test
  public void deleteOne() throws Exception {
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    char id = response.getContentAsString().charAt(6);
    mockMvc.perform(delete("/activity/" + id + "/999999")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
  }

  @Test
  public void updateOne() throws Exception {
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    char id = response.getContentAsString().charAt(6);
    mockMvc.perform(put("/activity/" + id)
        .content("{\"key\":\"999999\",\"title\":\"TestTest\",\"text\":\"test test\",\"tags\":\"test\"}")
        .contentType("application/json")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
  }

  @Test
  public void updateOneFailed() throws Exception {
    mockMvc.perform(put("/activity/" + 0)
        .content("{\"key\":\"999999\",\"title\":\"TestTest\",\"text\":\"test test\",\"tags\":\"test\"}")
        .contentType("application/json")).andExpect(status().isOk());
  }

  @Test
  public void findByTag() throws Exception {
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test\",\"text\":\"test test\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    char id = response.getContentAsString().charAt(6);
    mockMvc.perform(get("/activity/listByTag/test")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
  }

  @Test
  public void findByCategory() throws Exception {
	  mockMvc.perform(post("activity/test/addKey/12345"))
	  .andExpect(status().isOk());
    MockHttpServletResponse response = mockMvc
        .perform(post("/activity")
            .content("{\"title\":\"Test\",\"text\":\"test test\",\"category\":\"Students\",\"tags\":\"test\"}")
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn().getResponse();
    char id = response.getContentAsString().charAt(6);
    mockMvc.perform(get("/activity/listByCategory/Students")).andExpect(status().isOk());
    mockMvc.perform(delete("/activity/" + id + "/999999"));
    mockMvc.perform(delete("/activity/test/removeKey/12345"));
  }

  @Test
  public void getCategories() throws Exception {
    mockMvc.perform(get("/activity/getCategories"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string("[\"Students\",\"Teachers\",\"Travelling\",\"Business\",\"Enterpreneurship\"]"));
  }

}
