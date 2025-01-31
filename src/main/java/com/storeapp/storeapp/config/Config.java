package com.storeapp.storeapp.config;

import com.storeapp.storeapp.entity.Country;
import com.storeapp.storeapp.entity.Product;
import com.storeapp.storeapp.entity.ProductCategory;
import com.storeapp.storeapp.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class Config implements RepositoryRestConfigurer {

   private EntityManager entityManager;

   @Autowired
   public Config(EntityManager theEntityManager){
       entityManager = theEntityManager;
   }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] theUnsupportedAction = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        disableHttpMethods(Product.class, config, theUnsupportedAction);
        disableHttpMethods(ProductCategory.class, config, theUnsupportedAction);
        disableHttpMethods(Country.class, config, theUnsupportedAction);
        disableHttpMethods(State.class, config, theUnsupportedAction);
        // call an internal helper method
        exposeIds(config);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
    private void exposeIds(RepositoryRestConfiguration config) {

        //expose entity ids
        //

        // -get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // -get the types of the entities
        for (EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        // -expose the entity ids for the array of the entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
