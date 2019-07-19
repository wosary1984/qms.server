package qms.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qms.repository.base.BaseRepository;

public interface ApplicationUserRepository extends BaseRepository<ApplicationUser, Long> {

	ApplicationUser findByUsername(String username);

	ApplicationUser findByUserid(Long userid);

	@Query(value = "select u from  ApplicationUser u where u.username != :username")
	public List<ApplicationUser> getAllUsersExceptMe(@Param("username") String username);

}
