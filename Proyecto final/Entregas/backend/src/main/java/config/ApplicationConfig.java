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
        classes.add(services.ClientStateServices.class);
        classes.add(services.CityServices.class);
        classes.add(services.PlanServices.class);
        classes.add(services.MembershipServices.class);
        classes.add(services.VenueServices.class);
        return classes;
    }

}
