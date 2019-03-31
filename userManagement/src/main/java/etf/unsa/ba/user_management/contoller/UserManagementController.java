package etf.unsa.ba.user_management.contoller;

import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.service.UserManagementService;
import etf.unsa.ba.user_management.service.UserResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/travelAgency")
public class UserManagementController {
    private UserManagementService userManagementService;
    private final UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserManagementController(UserManagementService userManagementService, UserResourceAssembler userResourceAssembler) {
        this.userManagementService = userManagementService;
        this.userResourceAssembler = userResourceAssembler;
    }

    @GetMapping("/users")
    public Resources<Resource<UserEntity>> allUsers() {
        List<Resource<UserEntity>> users = userManagementService.allUsers()
                .stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserManagementController.class).allUsers()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public Resource<UserEntity> oneUser(@PathVariable int id) {
        UserEntity found = userManagementService.oneUser(id);
        return userResourceAssembler.toResource(found);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserEntity user) throws URISyntaxException {
        Resource<UserEntity> resource = userResourceAssembler.toResource(
                userManagementService.register(user)
        );
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> editUser(@RequestBody UserEntity user, @PathVariable int id) throws URISyntaxException {
        user.setId(id);
        Resource<UserEntity> resource = userResourceAssembler.toResource(
                userManagementService.register(user)
        );
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        userManagementService.deleteUser(user);

        return ResponseEntity.noContent().build();
    }

    /*TODO:
    create role routes:
        /roles
        /roles{id}
        /roles/users
    create user login route:
        /login
    add validation and proper exceptions
     */
}
