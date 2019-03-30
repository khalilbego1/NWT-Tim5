package etf.unsa.ba.user_management_web.service;

import etf.unsa.ba.user_management_service.model.entity.UserEntity;
import etf.unsa.ba.user_management_web.contoller.UserManagementController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceAssembler implements ResourceAssembler<UserEntity, Resource<UserEntity>> {

    @Override
    public Resource<UserEntity> toResource(UserEntity user) {

        return new Resource<>(user,
                linkTo(methodOn(UserManagementController.class).oneUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserManagementController.class).allUsers()).withRel("users"));
    }
}