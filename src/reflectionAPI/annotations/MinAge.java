package reflectionAPI.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinAge {

    int age() default 18; //jezeli nic nie wpiszemy do pola anotacji - 18 lat
}
//note: uzycie w reflectionAPI.lesson1.User
