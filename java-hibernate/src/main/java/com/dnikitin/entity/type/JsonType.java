package com.dnikitin.entity.type;

import com.dnikitin.entity.user.User;
import org.hibernate.usertype.UserType;

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
