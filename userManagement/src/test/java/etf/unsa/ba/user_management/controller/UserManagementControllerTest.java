package etf.unsa.ba.user_management.controller;

import etf.unsa.ba.user_management.contoller.UserManagementController;
import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.service.assembler.UserResourceAssembler;
import etf.unsa.ba.user_management.service.data.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagementControllerTest {
    @InjectMocks
    private UserManagementController userManagementController;
    @Mock
    private UserService userService;
    @Spy
    private UserResourceAssembler userResourceAssembler;

    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userManagementController)
                .build();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        //given
        when(userService.getAll()).thenReturn(mockUsers());

        //when
        mockMvc.perform(get("http://localhost:8080/travelAgency/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.userEntities", hasSize(2)))
                .andExpect(jsonPath("$._embedded.userEntities[0].id", is(1)))
                .andExpect(jsonPath("$._embedded.userEntities[1].id", is(2)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost:8080/travelAgency/users")));

        //then
        verify(userService, times(1)).getAll();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getOneExistingUserTest() throws Exception {
        //given
        when(userService.getById(1)).thenReturn(mockUser(1));

        //when
        mockMvc.perform(get("http://localhost:8080/travelAgency/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost:8080/travelAgency/users/1")))
                .andExpect(jsonPath("$._links.users.href", is("http://localhost:8080/travelAgency/users")));

        //then
        verify(userService, times(1)).getById(1);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getOneNonExistingUserTest() throws Exception {
        //given
        when(userService.getById(1)).thenReturn(null);

        //when
        mockMvc.perform(get("http://localhost:8080/travelAgency/users/1"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
                .andExpect(jsonPath("$.message", is("User not found")))
                .andExpect(jsonPath("$.errors", hasSize(1)));

        //then
        verify(userService, times(1)).getById(1);
        verifyNoMoreInteractions(userService);
    }

    @NotNull
    private List<UserEntity> mockUsers() {
        return Arrays.asList(
                mockUser(1),
                mockUser(2)
        );
    }

    @NotNull
    private UserEntity mockUser(int id) {
        return new UserEntity(
                id, "Lejla", "Solak", "lsolak" + id, "Password" + id,
                new RoleEntity(id, RoleEntity.Type.ADMIN, null, new HashSet<>()),
                "lsolak" + id + "@etf.unsa.ba", LocalDate.of(1996, 9, 15)
        );
    }
}
