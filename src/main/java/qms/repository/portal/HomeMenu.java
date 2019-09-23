package qms.repository.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeMenu {

    @Autowired
    private ApplicationPageRepository applicationPageRepository;

    private interface HOME_PAGE {
        final String name = "Home";
        final String path = "/home";
        final String icon = "fa fa-home text-red";
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    public void setHome() {
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
}