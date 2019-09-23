package qms.repository.portal;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataMenu {

    @Autowired
    private ApplicationPageRepository applicationPageRepository;

    private interface DATA_PAGE {
        final String name = "Data";
        final String path = "/factor";
        final String icon = "fa fa-database";
        final boolean hasChild = true;
        final boolean hasDeleted = false;
    }

    private interface DATA_MAIN_EVENT_PAGE {
        final String name = "Factor List";
        final String path = DATA_PAGE.path ;
        final String icon = "fa fa-history";
        // final String icon = "";
        final String parent = DATA_PAGE.name;
        // final String privilege = PersonPrivilege.write;
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    public void setData() {
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
}