package qms.repository.authorization.privilege;

import qms.repository.base.BaseRepository;

public interface ApplicationPrivilegeRepository extends BaseRepository<ApplicationPrivilege, Long> {

	ApplicationPrivilege findByPrivilege(String privilege);

}
