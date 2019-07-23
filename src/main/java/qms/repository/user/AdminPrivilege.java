package qms.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qms.repository.authorization.privilege.ApplicationPrivilege;
import qms.repository.authorization.privilege.ApplicationPrivilegeRepository;

@Component
public class AdminPrivilege {

    @Autowired
    private ApplicationPrivilegeRepository privilegeRepository;

    public static final String read = "ADMIN_READ_PRIVILEGE";
    public static final String write = "ADMIN_WRITE_PRIVILEGE";

    public void init() {

        if (privilegeRepository.findByPrivilege(read) == null) {
            ApplicationPrivilege r = new ApplicationPrivilege();
            r.setPrivilege(read);
            //r.setObject(Person.class.getSimpleName());
            privilegeRepository.save(r);
        }

        if (privilegeRepository.findByPrivilege(write) == null) {
            ApplicationPrivilege w = new ApplicationPrivilege();
            w.setPrivilege(write);
            //w.setObject(Person.class.getSimpleName());
            privilegeRepository.save(w);
        }

    }
}