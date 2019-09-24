package qms.config.initial;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qms.repository.authorization.privilege.ApplicationPrivilege;
import qms.repository.authorization.privilege.ApplicationPrivilegeRepository;
import qms.repository.person.PersonPrivilege;
import qms.repository.portal.SamplePages;
import qms.repository.user.AdminPrivilege;
import qms.repository.user.ApplicationUser;
import qms.repository.user.ApplicationUserRepository;
import qms.repository.wafer.SampleWaferData;

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
    SamplePages samplePages;

    @Autowired
    SampleWaferData sampleWaferData;

    @PostConstruct
    public void init() {
        personPrivilege.init();
        adminPrivilege.init();
        createInitalUsers();
        samplePages.init();
        // sampleWaferData.generateSampleData("waferid");
        sampleWaferData.generateSampleData2("w1", 20, 5);
        sampleWaferData.generateSampleData2("w2", 50, 5);
        sampleWaferData.generateSampleData2("w3", 100, 5);
    }

    private void createInitalUsers() {
        ApplicationPrivilege privilege1 = privilegeRepository.findByPrivilege("PERSON_READ_PRIVILEGE");
        ApplicationPrivilege privilege2 = privilegeRepository.findByPrivilege("PERSON_WRITE_PRIVILEGE");

        if (userRepository.findByUsername("feng") == null) {
            ApplicationUser user1 = new ApplicationUser();
            user1.setUsername("feng");
            user1.setPassword("$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O");
            user1.setLocked(false);
            user1.setPrivileges(new HashSet<ApplicationPrivilege>(Arrays.asList(privilege1)));
            userRepository.save(user1);
        }

        if (userRepository.findByUsername("user") == null) {
            ApplicationUser user2 = new ApplicationUser();
            user2.setUsername("user");
            user2.setPassword("$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O");
            user2.setPrivileges(new HashSet<ApplicationPrivilege>(Arrays.asList(privilege1, privilege2)));
            userRepository.save(user2);
        }

    }
}