package qms.repository.portal;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qms.repository.person.PersonPrivilege;

@Component
public class SetupPages {

    private interface HOME_PAGE {
        final String name = "Home";
        final String path = "/home";
        final String icon = "fa fa-home text-red";
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    private interface ADMIN_PAGE {
        final String name = "Admin";
        final String path = "/admin";
        final String icon = "fa fa-gears";
        final boolean hasChild = true;
        final boolean hasDeleted = false;
    }

    private interface ADMIN_PERSON_PAGE {
        final String name = "Person";
        final String path = ADMIN_PAGE.path + "/person";
        final String icon = "fa fa-user";
        final String parent = ADMIN_PAGE.name;
        final String privilege = PersonPrivilege.write;
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    private interface ADMIN_PERSON_PAGE_ACTION1 {
        final String action = "refresh";
        final int sequenceNumber = 1;
        final String icon = "fa fa-refresh";
    }
    private interface ADMIN_PERSON_PAGE_ACTION2 {
        final String action = "create";
        final int sequenceNumber = 2;
        final String icon = "fa fa-plus";
        final String privilege = PersonPrivilege.write;
    }

    @Autowired
    private ApplicationPageRepository applicationPageRepository;

    public void init() {

        if (applicationPageRepository.findByPagename(HOME_PAGE.name).size() == 0) {
            ApplicationPage home = new ApplicationPage();
            home.setPagename(HOME_PAGE.name);
            home.setHasDeleted(HOME_PAGE.hasDeleted);
            home.setPath(HOME_PAGE.path);
            home.setIcon(HOME_PAGE.icon);
            home.setHasChild(HOME_PAGE.hasChild);
            applicationPageRepository.save(home);
        }

        if (applicationPageRepository.findByPagename(ADMIN_PAGE.name).size() == 0) {
            ApplicationPage admin = new ApplicationPage();
            ApplicationPage adminPerson = new ApplicationPage();
            admin.setPagename(ADMIN_PAGE.name);
            admin.setHasDeleted(ADMIN_PAGE.hasDeleted);
            admin.setPath(ADMIN_PAGE.path);
            admin.setHasChild(ADMIN_PAGE.hasChild);
            admin.setIcon(ADMIN_PAGE.icon);
            // admin = applicationPageRepository.save(admin);

            if (applicationPageRepository.findByPagename(ADMIN_PERSON_PAGE.name).size() == 0) {
                adminPerson.setPagename(ADMIN_PERSON_PAGE.name);
                adminPerson.setHasDeleted(ADMIN_PERSON_PAGE.hasDeleted);
                adminPerson.setHasChild(ADMIN_PERSON_PAGE.hasChild);
                adminPerson.setIcon(ADMIN_PERSON_PAGE.icon);
                adminPerson.setPath(ADMIN_PERSON_PAGE.path);
                adminPerson.setPrivilege(ADMIN_PERSON_PAGE.privilege);
                adminPerson.setParentPageName(ADMIN_PERSON_PAGE.parent);

                //action
                ApplicationPageAction action1 = new ApplicationPageAction();
                action1.setAction(ADMIN_PERSON_PAGE_ACTION1.action);
                action1.setIcon(ADMIN_PERSON_PAGE_ACTION1.icon);
                action1.setSequenceNumber(ADMIN_PERSON_PAGE_ACTION1.sequenceNumber);
                action1.setPage(adminPerson);
                ApplicationPageAction action2 = new ApplicationPageAction();
                action2.setAction(ADMIN_PERSON_PAGE_ACTION2.action);
                action2.setIcon(ADMIN_PERSON_PAGE_ACTION2.icon);
                action2.setSequenceNumber(ADMIN_PERSON_PAGE_ACTION2.sequenceNumber);
                action2.setPrivilege(ADMIN_PERSON_PAGE_ACTION2.privilege);
                action2.setPage(adminPerson);

                adminPerson.setActions(new HashSet<>(Arrays.asList(action1,action2)));

                admin.setChilds(Arrays.asList(adminPerson));
                // applicationPageRepository.save(adminPerson);
            }
            applicationPageRepository.save(admin);
        }

    }
}