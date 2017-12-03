package base.hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
@Test
public void testSend() throws Exception {
        String json = "\"fabian.gierer@web.de\"";
        this.mockMvc.perform(post("/SendMail").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk());
        
}



@Test
public void testAdd1() throws Exception {
        String json = "{\"key\": 123456,\"text\": \"hallo\",\"tags\": [\"t1\",\"t2\",\"t3\"],\"title\": \"titel\"}";
        this.mockMvc.perform(post("/activity").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk());
        
}




}