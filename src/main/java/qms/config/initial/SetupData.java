package qms.config.initial;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qms.repository.authorization.privilege.ApplicationPrivilege;
import qms.repository.authorization.privilege.ApplicationPrivilegeRepository;
import qms.repository.person.PersonPrivilege;
import qms.repository.portal.SetupPages;
import qms.repository.user.AdminPrivilege;
import qms.repository.user.ApplicationUser;
import qms.repository.user.ApplicationUserRepository;

@Component
public class SetupData {

    @Autowired
    private ApplicationUserRepository userRepository;
    @Autowired
    private ApplicationPrivilegeRepository privilegeRepository;
    @Autowired
    PersonPrivilege personPrivilege;
    @Autowired
    AdminPrivilege adminPrivilege;

    @Autowired
    SetupPages setupPages;

    

    @PostConstruct
    public void init() {
        
        personPrivilege.init();
        adminPrivilege.init();

        setupPages.init();

        initUsers();
    }

    private void initUsers() {
        ApplicationPrivilege privilege1 = privilegeRepository.findByPrivilege("PERSON_READ_PRIVILEGE");
        ApplicationPrivilege privilege2 = privilegeRepository.findByPrivilege("PERSON_WRITE_PRIVILEGE");

        ApplicationUser user1 = new ApplicationUser();
        user1.setUsername("feng");
        user1.setPassword("$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O");
        user1.setLocked(false);
        user1.setPrivileges(new HashSet<ApplicationPrivilege>(Arrays.asList(privilege1)));
        userRepository.save(user1);

        ApplicationUser user2 = new ApplicationUser();
        user2.setUsername("user");
        user2.setPassword("$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O");
        user2.setPrivileges(new HashSet<ApplicationPrivilege>(Arrays.asList(privilege1, privilege2)));
        userRepository.save(user2);
    }
}