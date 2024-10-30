package Proj.library.repository;

import Proj.library.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends GenericRepository<User> {
}
