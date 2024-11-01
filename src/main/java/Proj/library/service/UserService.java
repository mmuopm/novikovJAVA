package Proj.library.service;

import Proj.library.dto.RoleDTO;
import Proj.library.dto.UserDTO;
import Proj.library.mapper.GenericMapper;
import Proj.library.model.User;
import Proj.library.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
        extends GenericService<User, UserDTO> {
    public UserService(GenericRepository<User> repository,
                       GenericMapper<User, UserDTO> mapper) {
        super(repository, mapper);
    }

    @Override
    public UserDTO create(UserDTO newObject) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }
}
