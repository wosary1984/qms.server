package qms.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

	ApplicationUser findByUsername(String username);

	ApplicationUser findByUserid(Long userid);

	@Query(value = "select u from  ApplicationUser u where u.username != :username")
	public List<ApplicationUser> getAllUsersExceptMe(@Param("username") String username);

}
