package qms.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qms.repository.base.BaseRepository;

public interface ApplicationPageRepository extends BaseRepository<ApplicationPage, Long> {

	public List<ApplicationPage> findByPagename(String pagename);

	public List<ApplicationPage> findByPrivilege(String privilege);

	@Query(value = "SELECT * FROM T_PAGE WHERE c_has_deleted = FALSE AND c_parent_page_name is null "
			+ "AND (c_privilege is null or c_privilege IN (:privileges))", nativeQuery = true)
	public List<ApplicationPage> findByPrivileges(@Param("privileges") List<String> privileges);

	@Query(value = "SELECT * FROM T_PAGE WHERE c_has_deleted = FALSE AND c_page_name = :pagename "
			+ "AND (c_privilege is null or c_privilege IN (:privileges))", nativeQuery = true)
	public ApplicationPage findPage(@Param("pagename") String pagename,
			@Param("privileges") List<String> privileges);

	@Query(value = "SELECT * FROM T_PAGE WHERE c_has_deleted = FALSE and c_privilege IS NULL", nativeQuery = true)
	public List<ApplicationPage> findByPrivileges2();

	public List<ApplicationPage> findAll();

}
