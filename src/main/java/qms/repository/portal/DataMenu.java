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

    private interface DATA_MAIN_PAGE {
        final String name = "Factor List";
        final String path = DATA_PAGE.path;
        final String icon = "fa fa-history";
        // final String icon = "";
        final String parent = DATA_PAGE.name;
        // final String privilege = PersonPrivilege.write;
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    private interface DATA_TAG_PAGE {
        final String name = "Tags";
        final String path = "/factor/tags";
        final String icon = "fa fa-tags";
        // final String icon = "";
        final String parent = DATA_PAGE.name;
        // final String privilege = PersonPrivilege.write;
        final boolean hasChild = false;
        final boolean hasDeleted = false;
    }

    public void setData() {
        ApplicationPage data;
        if (applicationPageRepository.findByPagename(DATA_PAGE.name).size() > 0) {
            data = applicationPageRepository.findByPagename(DATA_PAGE.name).get(0);
        } else {
            data = new ApplicationPage();
            data.setPagename(DATA_PAGE.name);
            data.setHasDeleted(DATA_PAGE.hasDeleted);
            data.setPath(DATA_PAGE.path);
            data.setIcon(DATA_PAGE.icon);
            data.setHasChild(DATA_PAGE.hasChild);
        }

        ApplicationPage factor;
        if (applicationPageRepository.findByPagename(DATA_MAIN_PAGE.name).size() == 0) {
            factor = new ApplicationPage();

        } else {
            factor = applicationPageRepository.findByPagename(DATA_MAIN_PAGE.name).get(0);
        }
        factor.setPagename(DATA_MAIN_PAGE.name);
        factor.setHasDeleted(DATA_MAIN_PAGE.hasDeleted);
        factor.setHasChild(DATA_MAIN_PAGE.hasChild);
        factor.setIcon(DATA_MAIN_PAGE.icon);
        factor.setPath(DATA_MAIN_PAGE.path);
        factor.setParentPageName(DATA_MAIN_PAGE.parent);

        ApplicationPage tag;
        if (applicationPageRepository.findByPagename(DATA_TAG_PAGE.name).size() == 0) {
            tag = new ApplicationPage();

        } else {
            tag = applicationPageRepository.findByPagename(DATA_TAG_PAGE.name).get(0);
        }
        tag.setPagename(DATA_TAG_PAGE.name);
        tag.setHasDeleted(DATA_TAG_PAGE.hasDeleted);
        tag.setHasChild(DATA_TAG_PAGE.hasChild);
        tag.setIcon(DATA_TAG_PAGE.icon);
        tag.setPath(DATA_TAG_PAGE.path);
        tag.setParentPageName(DATA_TAG_PAGE.parent);

        data.setChilds(Arrays.asList(factor, tag));
        applicationPageRepository.save(data);
    }
}