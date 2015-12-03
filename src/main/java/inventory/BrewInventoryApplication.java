package inventory;

import inventory.auth.UserAuthenticator;
import inventory.db.IngredientDao;
import inventory.model.Ingredient;
import inventory.model.User;
import inventory.resources.IngredientResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BrewInventoryApplication extends Application<InventoryConfiguration> {

    private final HibernateBundle<InventoryConfiguration> hibernate = new HibernateBundle<InventoryConfiguration>(Ingredient.class) {
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

        IngredientDao dao = new IngredientDao(hibernate.getSessionFactory());
        env.jersey().register(new IngredientResource(dao));

    }
}
