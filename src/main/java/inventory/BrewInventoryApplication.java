package inventory;

import inventory.db.IngredientDao;
import inventory.model.Ingredient;
import inventory.resources.IngredientResource;
import io.dropwizard.Application;
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
        IngredientDao dao = new IngredientDao(hibernate.getSessionFactory());
        env.jersey().register(new IngredientResource(dao));
    }
}
