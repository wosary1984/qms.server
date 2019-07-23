package qms.repository.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qms.repository.authorization.privilege.ApplicationPrivilege;
import qms.repository.authorization.privilege.ApplicationPrivilegeRepository;
import qms.repository.person.Person;

@Component
public class PersonPrivilege {

    @Autowired
    private ApplicationPrivilegeRepository privilegeRepository;

    public static final String read = "PERSON_READ_PRIVILEGE";
    public static final String write = "PERSON_WRITE_PRIVILEGE";

    public void init() {

        if (privilegeRepository.findByPrivilege(read) == null) {
            ApplicationPrivilege r = new ApplicationPrivilege();
            r.setPrivilege(read);
            r.setObject(Person.class.getSimpleName());
            privilegeRepository.save(r);
        }

        if (privilegeRepository.findByPrivilege(write) == null) {
            ApplicationPrivilege w = new ApplicationPrivilege();
            w.setPrivilege(write);
            w.setObject(Person.class.getSimpleName());
            privilegeRepository.save(w);
        }

    }
}