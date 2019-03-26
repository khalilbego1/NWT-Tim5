package etf.unsa.ba.user_management_service.service;

import etf.unsa.ba.user_management_service.model.Role;
import etf.unsa.ba.user_management_service.model.entity.RoleEntity;
import etf.unsa.ba.user_management_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDataService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleDataService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Integer Id) {
        if (roleRepository.findById(Id).isPresent()) {
            RoleEntity roleEntity = roleRepository.findById(Id).get();
            return new Role(
                    roleEntity.getName(),
                    roleEntity.getDescription()
            );
        }
        return null;
    }

    public void saveRole(Role role) {
        roleRepository.save(new RoleEntity(
                0,
                role.getName(),
                role.getDescription()
        ));
    }
}
