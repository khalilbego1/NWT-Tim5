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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserManagementControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private UserManagementController userManagementController;
    @Mock
    private UserService userService;
    @Mock
    private UserResourceAssembler userResourceAssembler;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userManagementController)
                .build();
        when(userService.getAll()).thenReturn(mockUsers());
        when(userService.getById(1)).thenReturn(mockUsers().get(0));
        when(userService.getById(2)).thenReturn(mockUsers().get(1));
        when(userResourceAssembler.toResource(mockUsers().get(0))).thenReturn(new Resource<>(mockUsers().get(0)));
        when(userResourceAssembler.toResource(mockUsers().get(1))).thenReturn(new Resource<>(mockUsers().get(1)));
    }

    @Test
    public void canGetAllUsers() throws Exception {
        mockMvc.perform(get("http://localhost:8080/travelAgency/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.userEntities", hasSize(2)))
                .andExpect(jsonPath("$._embedded.userEntities[0].id", is(1)))
                .andExpect(jsonPath("$._embedded.userEntities[1].id", is(2)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost:8080/travelAgency/users")));

        verify(userService, times(1)).getAll();
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
    private Stream<UserEntity> mockUsersAsStream() {
        return Stream.of(
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
