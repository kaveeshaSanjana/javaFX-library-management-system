package util;



import entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {
    private static SessionFactory session = createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistry registryBuilder = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata build = new MetadataSources(registryBuilder)
                .addAnnotatedClass(BookEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return build.getSessionFactoryBuilder().build();

    }
    public static Session getSession() {
        return session.openSession();
    }
}