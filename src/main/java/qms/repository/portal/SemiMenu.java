package qms.repository.portal;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SemiMenu {

    @Autowired
    private ApplicationPageRepository applicationPageRepository;

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

    public void setSemi() {
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
}