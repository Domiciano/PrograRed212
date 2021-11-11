package config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet <>();
        classes.add(services.UserServices.class);
        classes.add(services.ClientServices.class);
        classes.add(services.RoleServices.class);
        classes.add(services.CityServices.class);
        return classes;
    }

}
