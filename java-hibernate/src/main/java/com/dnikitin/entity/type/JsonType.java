package com.dnikitin.entity.type;

import com.dnikitin.entity.User;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.metamodel.Type;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonType implements UserType<User> {
    //implement for difficult custom classes


    @Override
    public int getSqlType() {
        return 0;
    }

    @Override
    public Class<User> returnedClass() {
        return null;
    }

    @Override
    public User deepCopy(User user) {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }
}
