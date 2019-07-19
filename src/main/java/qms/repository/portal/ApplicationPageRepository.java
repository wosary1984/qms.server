package qms.repository.portal;

import java.util.List;

import qms.repository.base.BaseRepository;

public interface ApplicationPageRepository extends BaseRepository<ApplicationPage, Long> {

	public List<ApplicationPage> findByPagename(String pagename);

	public List<ApplicationPage> findByPrivilege(String privilege);

	public List<ApplicationPage> findAll();

}
