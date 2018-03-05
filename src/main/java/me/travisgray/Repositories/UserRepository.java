package me.travisgray.Repositories;

import me.travisgray.Models.Item;
import me.travisgray.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
 User findByUsername(String username);
 User findByEmail(String email);
 Long countByEmail(String email);
 Long countByUsername(String email);

}
