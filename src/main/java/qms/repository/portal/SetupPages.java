package qms.repository.portal;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qms.repository.person.PersonPrivilege;

@Component
public class SetupPages {


    private interface Home{
        final String name = "Home";
        final String path = "/starter";
        final String icon = "fa fa-circle-o text-red";
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    private interface Admin{
        final String name = "Admin";
        final String icon = "fa fa-dashboard";
        final boolean hasChild = true;
        final boolean hasDeleted = false;
    }

    private interface Person{
        final String name = "Person";
        final String path = "/person";
        final String icon = "fa fa-dashboard";
        final String parent = Admin.name;
        final String privilege = PersonPrivilege.write;
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    @Autowired
    private ApplicationPageRepository applicationPageRepository;

    public void init() {

        if (applicationPageRepository.findByPagename(Home.name).size() == 0) {
            ApplicationPage home = new ApplicationPage();
            home.setPagename(Home.name);
            home.setHasDeleted(Home.hasDeleted);
            home.setPath(Home.path);
            home.setIcon(Home.icon);
            home.setHasChild(Home.hasChild);
            applicationPageRepository.save(home);
        }

        if (applicationPageRepository.findByPagename(Admin.name).size() == 0) {
            ApplicationPage admin = new ApplicationPage();
            ApplicationPage adminPerson = new ApplicationPage();
            admin.setPagename(Admin.name);
            admin.setHasDeleted(Admin.hasDeleted);
            admin.setHasChild(Admin.hasChild);
            admin.setIcon(Admin.icon);
            //admin = applicationPageRepository.save(admin);

            if (applicationPageRepository.findByPagename(Person.name).size() == 0) {
                adminPerson.setPagename(Person.name);
                adminPerson.setHasDeleted(Person.hasDeleted);
                adminPerson.setHasChild(Person.hasChild);
                adminPerson.setIcon(Person.icon);
                adminPerson.setPath(Person.path);
                adminPerson.setPrivilege(Person.privilege);
                adminPerson.setParentPageName(Person.parent);
                admin.setChilds(Arrays.asList(adminPerson));
                //applicationPageRepository.save(adminPerson);
            }
            applicationPageRepository.save(admin);
        }

    }
}