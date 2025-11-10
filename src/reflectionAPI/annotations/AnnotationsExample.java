package reflectionAPI.annotations;

import reflectionAPI.lesson1.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

public class AnnotationsExample {
    static void main() throws NoSuchFieldException {
//        @Target(ElementType.METHOD) - gdzie moze byc stosowana anotacja
//        @Retention(RetentionPolicy.SOURCE) -optional,by default class. gdzie bedzier widoczna anotacja
        // - source - tylko w napisanym kodzie, po kompilacji jej nie bedzie
        // - runtime - widoczne podczas wykonania programu
//        public @interface Override {
//        }

        User user1 = new User(1, "Dima", 23);
        System.out.println(User.class.getDeclaredField("age")); //private int reflectionAPI.lesson1.User.age
        System.out.println(Arrays.toString(User.class.getDeclaredField("age").getAnnotations())); //[@reflectionAPI.annotations.MinAge(age=18)]
        System.out.println(User.class.getDeclaredField("age").getAnnotation(MinAge.class).age()); //dostalismy 18

        User user = new User(2, "Kristik", 16);
        try {
            AnnotationValidator.validateAnnotation(user);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
