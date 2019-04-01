package etf.unsa.ba.user_management.contoller;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import etf.unsa.ba.user_management.service.assembler.RoleResourceAssembler;
import etf.unsa.ba.user_management.service.assembler.UserResourceAssembler;
import etf.unsa.ba.user_management.service.data.RoleDataService;
import etf.unsa.ba.user_management.service.data.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/travelAgency")
public class UserManagementController {
    private final UserDataService userDataService;
    private final UserResourceAssembler userResourceAssembler;
    private final RoleResourceAssembler roleResourceAssembler;
    private RoleDataService roleDataService;

    @Autowired
    public UserManagementController(UserDataService userDataService,
                                    UserResourceAssembler userResourceAssembler,
                                    RoleResourceAssembler roleResourceAssembler,
                                    RoleDataService roleDataService) {
        this.userResourceAssembler = userResourceAssembler;
        this.roleResourceAssembler = roleResourceAssembler;
        this.userDataService = userDataService;
        this.roleDataService = roleDataService;
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

    @GetMapping("/users")
    public Resources<Resource<UserEntity>> allUsers() {
        List<Resource<UserEntity>> users = userDataService.getAll()
                .stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserManagementController.class).allUsers()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public Resource<UserEntity> oneUser(@PathVariable int id) {
        UserEntity found = userDataService.getById(id);
        return userResourceAssembler.toResource(found);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserEntity user) throws URISyntaxException {
        if (userDataService.getByUsername(user.getUsername()) != null) {
        }

        Resource<UserEntity> resource = userResourceAssembler.toResource(
                userDataService.insert(user)
        );
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> editUser(@RequestBody UserEntity user, @PathVariable int id) throws URISyntaxException {
        user.setId(id);
        Resource<UserEntity> resource = userResourceAssembler.toResource(
                userDataService.update(user)
        );
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        userDataService.delete(user);

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
        List<Resource<UserEntity>> users = userDataService.getUsersForRoleId(found)
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
    public ResponseEntity<?> editRole(@RequestBody RoleEntity role, @PathVariable int id) throws URISyntaxException {
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
