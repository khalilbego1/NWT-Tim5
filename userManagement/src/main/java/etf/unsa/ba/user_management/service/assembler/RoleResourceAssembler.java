package etf.unsa.ba.user_management.service.assembler;

import etf.unsa.ba.user_management.contoller.UserManagementController;
import etf.unsa.ba.user_management.model.entity.RoleEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RoleResourceAssembler implements ResourceAssembler<RoleEntity, Resource<RoleEntity>> {
    @Override
    public Resource<RoleEntity> toResource(RoleEntity role) {
        return new Resource<>(role,
                linkTo(methodOn(UserManagementController.class).oneRole(role.getId())).withSelfRel(),
                linkTo(methodOn(UserManagementController.class).usersForRole(role.getId())).withRel("users"),
                linkTo(methodOn(UserManagementController.class).allRoles()).withRel("roles"));
    }
}
