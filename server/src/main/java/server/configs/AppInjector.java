package server.configs;

import com.google.inject.AbstractModule;
import server.db.RepositoryImpl;
import server.db.Repository;

/**
 * Class for configuring Dependency injections
 */
public class AppInjector extends AbstractModule {

    @Override
    protected void configure(){

        bind(Repository.class).to(RepositoryImpl.class);

    }

}
