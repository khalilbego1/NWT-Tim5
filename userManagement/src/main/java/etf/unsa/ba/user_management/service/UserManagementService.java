package etf.unsa.ba.user_management.service;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserManagementService {
    private RoleDataService roleDataService;
    private UserDataService userDataService;

    @Autowired
    public UserManagementService(UserDataService userDataService, RoleDataService roleDataService) {
        this.userDataService = userDataService;
        this.roleDataService = roleDataService;
    }

    public UserEntity login(UserEntity user) {
        return userDataService.getForLogin(user.getUsername(), user.getPassword());
    }

    public UserEntity register(UserEntity user) {
        return userDataService.insert(user);
    }

    public List<UserEntity> allUsers() {
        return userDataService.getAll();
    }

    public UserEntity oneUser(int id) {
        return userDataService.getById(id);
    }

    public void deleteUser(UserEntity user) {
        userDataService.delete(user);
    }

    public List<RoleEntity> allRoles() {
        return roleDataService.getAll();
    }

    public RoleEntity oneRole(int id) {
        return roleDataService.getById(id);
    }

    public void deleteRole(RoleEntity role) {
        roleDataService.delete(role);
    }
}
