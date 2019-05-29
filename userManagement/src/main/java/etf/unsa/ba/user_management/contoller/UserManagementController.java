package etf.unsa.ba.user_management.contoller;

import etf.unsa.ba.user_management.model.LoginInput;
import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.service.assembler.RoleResourceAssembler;
import etf.unsa.ba.user_management.service.assembler.UserResourceAssembler;
import etf.unsa.ba.user_management.service.data.RoleService;
import etf.unsa.ba.user_management.service.data.UserService;
import etf.unsa.ba.user_management.service.event.Sender;
import etf.unsa.ba.user_management.service.exception.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@SuppressWarnings("ALL")
@RestController
public class UserManagementController {
    private final UserService userService;
    private final UserResourceAssembler userResourceAssembler;
    private final RoleResourceAssembler roleResourceAssembler;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Sender sender;

    @Autowired
    public UserManagementController(UserService userService,
                                    UserResourceAssembler userResourceAssembler,
                                    RoleResourceAssembler roleResourceAssembler,
                                    RoleService roleService,
                                    BCryptPasswordEncoder bCryptPasswordEncoder,
                                    Sender sender) {
        this.userResourceAssembler = userResourceAssembler;
        this.roleResourceAssembler = roleResourceAssembler;
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sender = sender;
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInput loginInput) {
        UserEntity foundUser = userService.getByUsername(loginInput.getUsername());
        ApiError apiError = null;
        if (foundUser == null)
            apiError = new ApiError("Login failed", "Username or password are not correct");
        else if (bCryptPasswordEncoder.matches(loginInput.getPassword(), bCryptPasswordEncoder.encode(foundUser.getPassword())))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }
    @CrossOrigin
    @GetMapping("/users")
    public Resources<Resource<UserEntity>> allUsers() {
        List<Resource<UserEntity>> users = userService.getAll()
                .stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserManagementController.class).allUsers()).withSelfRel());
    }
    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<?> oneUser(@PathVariable int id) {
        UserEntity found = userService.getById(id);
        if (found == null)
            return new ResponseEntity<>(new ApiError("User not found", "User with id " + id + " doesn't exist"),
                    HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userResourceAssembler.toResource(found), HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserEntity user) throws URISyntaxException {
        if (userService.getByUsername(user.getUsername()) != null) {
            new ResponseEntity<>(new ApiError("Registration failed", "User with the same username already exists"),
                    HttpStatus.CONFLICT);
        }
        Resource<UserEntity> resource = userResourceAssembler.toResource(userService.insert(user));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }
    @CrossOrigin
    @PutMapping("/users/{id}")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserEntity user, @PathVariable int id) throws URISyntaxException {
        user.setId(id);
        Resource<UserEntity> resource = userResourceAssembler.toResource(userService.update(user));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        UserEntity found = userService.getById(id);
        if (found == null) {
            return new ResponseEntity<>(new ApiError("User not found", "User with id " + id + " doesn't exist"),
                    HttpStatus.NOT_FOUND);
        } else {
            userService.delete(found);
            sender.send("user.delete", Integer.toString(id) + ";delete");
        }
        return ResponseEntity.noContent().build();
    }
    @CrossOrigin
    @GetMapping("/roles")
    public Resources<Resource<RoleEntity>> allRoles() {
        List<Resource<RoleEntity>> roles = roleService.getAll()
                .stream()
                .map(roleResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(roles,
                linkTo(methodOn(UserManagementController.class).allRoles()).withSelfRel());
    }
    @CrossOrigin
    @GetMapping("/roles/{id}")
    public ResponseEntity<?> oneRole(@PathVariable int id) {
        RoleEntity found = roleService.getById(id);
        if (found == null)
            return new ResponseEntity<>(new ApiError("Role not found", "Role with id " + id + " doesn't exist"),
                    HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(roleResourceAssembler.toResource(found), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/roles/{id}/users")
    public Resources<Resource<UserEntity>> usersForRole(@PathVariable int id) {
        RoleEntity found = roleService.getById(id);
        List<Resource<UserEntity>> users = userService.getUsersForRoleId(found)
                .stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserManagementController.class).usersForRole(id)).withSelfRel());
    }
    @CrossOrigin
    @PostMapping("/roles")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleEntity role) throws URISyntaxException {
        Resource<RoleEntity> resource = roleResourceAssembler.toResource(roleService.insert(role));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }
    @CrossOrigin
    @PutMapping("/roles/{id}")
    public ResponseEntity<?> editRole(@Valid @RequestBody RoleEntity role, @PathVariable int id) throws URISyntaxException {
        role.setId(id);
        Resource<RoleEntity> resource = roleResourceAssembler.toResource(roleService.update(role));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }
    @CrossOrigin
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        RoleEntity found = roleService.getById(id);
        if (found == null)
            return new ResponseEntity<>(new ApiError("Role not found", "Role with id " + id + " doesn't exist"),
                    HttpStatus.NOT_FOUND);
        else roleService.delete(found);
        return ResponseEntity.noContent().build();
    }
}
