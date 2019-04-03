package etf.unsa.ba.user_management.contoller;

import etf.unsa.ba.user_management.model.LoginInput;
import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.service.assembler.RoleResourceAssembler;
import etf.unsa.ba.user_management.service.assembler.UserResourceAssembler;
import etf.unsa.ba.user_management.service.data.RoleService;
import etf.unsa.ba.user_management.service.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/travelAgency")
public class UserManagementController {
    private final UserService userService;
    private final UserResourceAssembler userResourceAssembler;
    private final RoleResourceAssembler roleResourceAssembler;
    private RoleService roleDataService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserManagementController(UserService userService,
                                    UserResourceAssembler userResourceAssembler,
                                    RoleResourceAssembler roleResourceAssembler,
                                    RoleService roleDataService,
                                    BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userResourceAssembler = userResourceAssembler;
        this.roleResourceAssembler = roleResourceAssembler;
        this.userService = userService;
        this.roleDataService = roleDataService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInput loginInput) {
        UserEntity foundUser = userService.getByUsername(loginInput.getUsername());
        if (foundUser == null)
            return new ResponseEntity<String>("Username or password are not correct", HttpStatus.UNAUTHORIZED);
        else {
            if (bCryptPasswordEncoder.matches(loginInput.getPassword(),foundUser.getPassword()))
                return new ResponseEntity<String>("Login successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Username or password are not correct", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/users")
    public Resources<Resource<UserEntity>> allUsers() {
        List<Resource<UserEntity>> users = userService.getAll()
                .stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserManagementController.class).allUsers()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public Resource<UserEntity> oneUser(@PathVariable int id) {
        UserEntity found = userService.getById(id);
        return userResourceAssembler.toResource(found);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserEntity user) throws URISyntaxException {
        if (userService.getByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("User with the same username already exists", HttpStatus.CONFLICT);
        }
        Resource<UserEntity> resource = userResourceAssembler.toResource(
                userService.insert(user)
        );
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserEntity user, @PathVariable int id) throws URISyntaxException {
        user.setId(id);
        Resource<UserEntity> resource = userResourceAssembler.toResource(
                userService.update(user)
        );
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        userService.delete(user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roles")
    public Resources<Resource<RoleEntity>> allRoles() {
        List<Resource<RoleEntity>> roles = roleDataService.getAll()
                .stream()
                .map(roleResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(roles,
                linkTo(methodOn(UserManagementController.class).allRoles()).withSelfRel());
    }

    @GetMapping("/roles/{id}")
    public Resource<RoleEntity> oneRole(@PathVariable int id) {
        RoleEntity found = roleDataService.getById(id);
        return roleResourceAssembler.toResource(found);
    }

    @GetMapping("/roles/{id}/users")
    public Resources<Resource<UserEntity>> usersForRole(@PathVariable int id) {
        RoleEntity found = roleDataService.getById(id);
        List<Resource<UserEntity>> users = userService.getUsersForRoleId(found)
                .stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserManagementController.class).usersForRole(id)).withSelfRel());
    }

    @PostMapping("/roles")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleEntity role) throws URISyntaxException {
        Resource<RoleEntity> resource = roleResourceAssembler.toResource(
                roleDataService.insert(role)
        );
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> editRole(@Valid @RequestBody RoleEntity role, @PathVariable int id) throws URISyntaxException {
        role.setId(id);
        Resource<RoleEntity> resource = roleResourceAssembler.toResource(
                roleDataService.update(role)
        );
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        RoleEntity role = new RoleEntity();
        role.setId(id);
        roleDataService.delete(role);

        return ResponseEntity.noContent().build();
    }
}
