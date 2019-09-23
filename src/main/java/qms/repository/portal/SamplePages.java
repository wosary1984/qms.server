package qms.repository.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SamplePages {

    @Autowired
    HomeMenu home;
    @Autowired
    AdminMenu admin;
    @Autowired
    SemiMenu semi;
    @Autowired
    DataMenu data;

    public void init() {
        home.setHome();
        admin.setAdmin();
        semi.setSemi();
        data.setData();
    }
}