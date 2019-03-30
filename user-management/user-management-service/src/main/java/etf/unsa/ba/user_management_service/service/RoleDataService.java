package etf.unsa.ba.user_management_service.service;

import etf.unsa.ba.user_management_service.model.entity.RoleEntity;
import etf.unsa.ba.user_management_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDataService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleDataService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity getRoleById(Integer Id) {
        if (roleRepository.findById(Id).isPresent()) {
            return roleRepository.findById(Id).get();
        }
        return null;
    }

    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    List<RoleEntity> getAll() {
        return roleRepository.findAll();
    }

    RoleEntity getById(Integer Id) {
        if (roleRepository.findById(Id).isPresent()) {
            return roleRepository.findById(Id).get();
        }
        return null;
    }

    void delete(RoleEntity role) {
        roleRepository.deleteById(role.getId());
    }
}
