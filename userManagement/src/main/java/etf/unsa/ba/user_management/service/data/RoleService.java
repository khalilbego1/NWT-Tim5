package etf.unsa.ba.user_management.service.data;

import etf.unsa.ba.user_management.model.entity.RoleEntity;
import etf.unsa.ba.user_management.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity insert(RoleEntity role) {
        return roleRepository.save(role);
    }

    public RoleEntity update(RoleEntity role) {
        return roleRepository.save(role);
    }

    public List<RoleEntity> getAll() {
        return roleRepository.findAll();
    }

    public RoleEntity getById(Integer Id) {
        if (roleRepository.findById(Id).isPresent())
            return roleRepository.findById(Id).get();
        return null;
    }

    public void delete(RoleEntity role) {
        roleRepository.deleteById(role.getId());
    }
}