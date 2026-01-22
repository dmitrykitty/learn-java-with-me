package com.dnikitin.util;

import com.dnikitin.entity.converter.BirthdayConverter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public SessionFactory buildSessionFactory() {
        //================================CONFIG=====================================

        Configuration configuration = new Configuration().configure();
        configuration.configure(); //by default hibernate.cfg.xml\
        //configuration.addAnnotatedClass(User.class);
        //configuration.setPhysicalNamingStrategy(new PhysicalNamingStrategySnakeCaseImpl()); we can set a type of name mapping

        configuration.addAttributeConverter(new BirthdayConverter(), true); //no need to set autoapplay=true
        // if we have it above the converterClass

        return new Configuration().configure().buildSessionFactory();
    }
}
