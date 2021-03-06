package etf.unsa.ba.user_management.controller;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.service.data.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementControllerTest {
    private static final String URL_TEMPLATE_USERS = "http://localhost:8000/users";
    private static final String URL_TEMPLATE_USER = "http://localhost:8000/users/1";
    @MockBean
    private UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        //given
        when(userService.getAll()).thenReturn(mockUsers());

        //when
        mockMvc.perform(get(URL_TEMPLATE_USERS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.userEntities", hasSize(2)))
                .andExpect(jsonPath("$._embedded.userEntities[0].id", is(1)))
                .andExpect(jsonPath("$._embedded.userEntities[1].id", is(2)))
                .andExpect(jsonPath("$._links.self.href", is(URL_TEMPLATE_USERS)));

        //then
        verify(userService, times(1)).getAll();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getOneExistingUserTest() throws Exception {
        //given
        when(userService.getById(1)).thenReturn(mockUser(1));

        //when
        mockMvc.perform(get(URL_TEMPLATE_USER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Lejla")))
                .andExpect(jsonPath("$.lastName", is("Solak")))
                .andExpect(jsonPath("$.username", is("lsolak1")))
                .andExpect(jsonPath("$.password", is("Password1")))
                .andExpect(jsonPath("$.email", is("lsolak1@etf.unsa.ba")))
                .andExpect(jsonPath("$.dateOfBirth", is("1996-09-15")))
                .andExpect(jsonPath("$.role.id", is(1)))
                .andExpect(jsonPath("$.role.type", is("ADMIN")))
                .andExpect(jsonPath("$._links.self.href", is(URL_TEMPLATE_USER)))
                .andExpect(jsonPath("$._links.users.href", is(URL_TEMPLATE_USERS)));

        //then
        verify(userService, times(1)).getById(1);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getOneNonExistingUserTest() throws Exception {
        //given
        when(userService.getById(1)).thenReturn(null);

        //when
        mockMvc.perform(get(URL_TEMPLATE_USER))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message", is("User not found")))
                .andExpect(jsonPath("$.errors", hasSize(1)));

        //then
        verify(userService, times(1)).getById(1);
        verifyNoMoreInteractions(userService);
    }

    @NotNull
    private List<UserEntity> mockUsers() {
        return Arrays.asList(mockUser(1), mockUser(2));
    }

    @NotNull
    private UserEntity mockUser(int id) {
        return new UserEntity(id,
                "Lejla",
                "Solak",
                "lsolak" + id,
                "Password" + id,
                "lsolak" + id + "@etf.unsa.ba",
                LocalDate.of(1996, 9, 15),
                new RoleEntity(id, RoleEntity.Type.ADMIN, null, new HashSet<>())
        );
    }
}
