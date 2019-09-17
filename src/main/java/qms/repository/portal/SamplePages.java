package qms.repository.portal;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qms.repository.person.PersonPrivilege;

@Component
public class SamplePages {

    private interface ADMIN_PAGE {
        final String name = "Admin";
        final String path = "/admin";
        final String icon = "fa fa-cog";
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

    private interface HOME_PAGE {
        final String name = "Home";
        final String path = "/home";
        final String icon = "fa fa-home text-red";
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    private void setHome() {
        // HOME
        if (applicationPageRepository.findByPagename(HOME_PAGE.name).size() == 0) {
            ApplicationPage home = new ApplicationPage();
            home.setPagename(HOME_PAGE.name);
            home.setHasDeleted(HOME_PAGE.hasDeleted);
            home.setPath(HOME_PAGE.path);
            home.setIcon(HOME_PAGE.icon);
            home.setHasChild(HOME_PAGE.hasChild);
            applicationPageRepository.save(home);
        }
    }

    private void setAdmin() {
        // ADMIN
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

                // action
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
                adminPerson.setActions(new HashSet<>(Arrays.asList(action1, action2)));

                admin.setChilds(Arrays.asList(adminPerson));
                // applicationPageRepository.save(adminPerson);
            }
            applicationPageRepository.save(admin);
        }
    }

    private interface SEMI_PAGE {
        final String name = "Semi";
        final String path = "/semi";
        final String icon = "fa fa-microchip";
        final boolean hasChild = true;
        final boolean hasDeleted = false;
    }

    private interface SEMI_WAFER_PAGE {
        final String name = "Wafer";
        final String path = SEMI_PAGE.path + "/wafer";
        // final String icon = "fa fa-braille";
        final String icon = "";
        final String parent = SEMI_PAGE.name;
        // final String privilege = PersonPrivilege.write;
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    private void setSemi() {
        // SEMI
        if (applicationPageRepository.findByPagename(SEMI_PAGE.name).size() == 0) {
            ApplicationPage semi = new ApplicationPage();
            ApplicationPage wafer = new ApplicationPage();
            semi.setPagename(SEMI_PAGE.name);
            semi.setHasDeleted(SEMI_PAGE.hasDeleted);
            semi.setPath(SEMI_PAGE.path);
            semi.setIcon(SEMI_PAGE.icon);
            semi.setHasChild(SEMI_PAGE.hasChild);
            // applicationPageRepository.save(semi);
            if (applicationPageRepository.findByPagename(SEMI_WAFER_PAGE.name).size() == 0) {
                wafer.setPagename(SEMI_WAFER_PAGE.name);
                wafer.setHasDeleted(SEMI_WAFER_PAGE.hasDeleted);
                wafer.setHasChild(SEMI_WAFER_PAGE.hasChild);
                wafer.setIcon(SEMI_WAFER_PAGE.icon);
                wafer.setPath(SEMI_WAFER_PAGE.path);
                // waferPerson.setPrivilege(SEMI_WAFER_PAGE.privilege);
                wafer.setParentPageName(SEMI_WAFER_PAGE.parent);

                semi.setChilds(Arrays.asList(wafer));
            }
            applicationPageRepository.save(semi);
        }
    }


    private interface DATA_PAGE {
        final String name = "Data";
        final String path = "/event";
        final String icon = "fa fa-database";
        final boolean hasChild = true;
        final boolean hasDeleted = false;
    }

    private interface DATA_MAIN_EVENT_PAGE {
        final String name = "Event";
        final String path = DATA_PAGE.path + "/main";
        final String icon = "fa fa-history";
        //final String icon = "";
        final String parent = DATA_PAGE.name;
        // final String privilege = PersonPrivilege.write;
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }
    private void setData() {
        // SEMI
        if (applicationPageRepository.findByPagename(DATA_PAGE.name).size() == 0) {
            ApplicationPage data = new ApplicationPage();
            ApplicationPage event = new ApplicationPage();
            data.setPagename(DATA_PAGE.name);
            data.setHasDeleted(DATA_PAGE.hasDeleted);
            data.setPath(DATA_PAGE.path);
            data.setIcon(DATA_PAGE.icon);
            data.setHasChild(DATA_PAGE.hasChild);
            // applicationPageRepository.save(semi);
            if (applicationPageRepository.findByPagename(DATA_MAIN_EVENT_PAGE.name).size() == 0) {
                event.setPagename(DATA_MAIN_EVENT_PAGE.name);
                event.setHasDeleted(DATA_MAIN_EVENT_PAGE.hasDeleted);
                event.setHasChild(DATA_MAIN_EVENT_PAGE.hasChild);
                event.setIcon(DATA_MAIN_EVENT_PAGE.icon);
                event.setPath(DATA_MAIN_EVENT_PAGE.path);
                // waferPerson.setPrivilege(SEMI_WAFER_PAGE.privilege);
                event.setParentPageName(DATA_MAIN_EVENT_PAGE.parent);

                data.setChilds(Arrays.asList(event));
            }
            applicationPageRepository.save(data);
        }
    }

    public void init() {
        this.setHome();
        this.setAdmin();
        this.setSemi();
        this.setData();
    }
}