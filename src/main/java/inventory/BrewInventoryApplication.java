package inventory;

import inventory.auth.UserAuthenticator;
import inventory.db.HopDAO;
import inventory.db.MaltDAO;
import inventory.db.YeastDAO;
import inventory.model.Hop;
import inventory.model.Malt;
import inventory.model.User;
import inventory.model.Yeast;
import inventory.resources.HopResource;
import inventory.resources.MaltResource;
import inventory.resources.YeastResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BrewInventoryApplication extends Application<InventoryConfiguration> {

    private final HibernateBundle<InventoryConfiguration> hibernate =
            new HibernateBundle<InventoryConfiguration>(Hop.class, Yeast.class, Malt.class) {
        public PooledDataSourceFactory getDataSourceFactory(InventoryConfiguration conf) {
            return conf.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new BrewInventoryApplication().run(args);
    }

    @Override
    public String getName() {
        return "brew-inventory";
    }

    @Override
    public void initialize(Bootstrap<InventoryConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(InventoryConfiguration conf, Environment env) throws Exception {

        env.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new UserAuthenticator(conf.getLogin(), conf.getPassword()))
                .setRealm("Brew Inventory Authentication")
                .buildAuthFilter()
        ));

        HopDAO hopDAO = new HopDAO(hibernate.getSessionFactory());
        YeastDAO yeastDAO = new YeastDAO(hibernate.getSessionFactory());
        MaltDAO maltDAO = new MaltDAO(hibernate.getSessionFactory());
        env.jersey().register(new HopResource(hopDAO));
        env.jersey().register(new YeastResource(yeastDAO));
        env.jersey().register(new MaltResource(maltDAO));
    }
}
