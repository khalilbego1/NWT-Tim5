package etf.unsa.ba.user_management;

import etf.unsa.ba.user_management.contoller.UserManagementController;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class UserManagementControllerIntegrationTest {
    @Autowired
    UserManagementController userManagementController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String user = "{\n" +
                "\t\"firstName\": \"Lejla\",\n" +
                "\t\"lastName\": \"Solak\",\n" +
                "\t\"username\": \"lsolak\",\n" +
                "\t\"password\": \"lsolak123\",\n" +
                "\t\"role\": {\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"ADMIN\",\n" +
                "\t\t\"description\": null\n" +
                "\t}\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/travelAgency/registerUser")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8));
    }

    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
        String user = "{\n" +
                "\t\"firstName\": \"\",\n" +
                "\t\"lastName\": \"Solak\",\n" +
                "\t\"username\": \"lsolak\",\n" +
                "\t\"password\": \"lsolak123\",\n" +
                "\t\"role\": {\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"ADMIN\",\n" +
                "\t\t\"description\": null\n" +
                "\t}\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("travelAgency/users")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.is("First name can 't be blank")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
