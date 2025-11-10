package reflectionAPI.annotations;

import java.lang.reflect.Field;

public class AnnotationValidator {
    public static void validateAnnotation(Object obj) throws IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(MinAge.class)) {
                int minAge = field.getAnnotation(MinAge.class).age();

                field.setAccessible(true);
                int age = (int) field.get(obj);
                if (age < minAge)
                    throw new IllegalArgumentException
                            ("Your age (" + age + ") is less then minimal age (" + minAge + ")");
            }
        }
    }
}
