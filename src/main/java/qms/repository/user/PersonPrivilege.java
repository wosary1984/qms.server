package qms.repository.user;

import qms.repository.authorization.privilege.ApplicationPrivilege;
import qms.repository.authorization.privilege.ApplicationPrivilegeRepository;

public class PersonPrivilege {
 
    public static void init(ApplicationPrivilegeRepository privilegeRepository) {
        ApplicationPrivilege readPersonPrivilege = new ApplicationPrivilege();
        readPersonPrivilege.setPrivilege("PERSON_READ_PRIVILEGE");
        privilegeRepository.save(readPersonPrivilege);
     
        ApplicationPrivilege writePersonPrivilege = new ApplicationPrivilege();
        writePersonPrivilege.setPrivilege("PERSON_WRITE_PRIVILEGE");
        privilegeRepository.save(writePersonPrivilege);
    }
}