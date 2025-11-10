package reflectionAPI.lesson1;

import java.util.Arrays;

public class ReflectionAPIExample {
    static void main() {
        User user1 = new User(25L, "Dima");
        Class<? extends User> user1Class = user1.getClass();
        Class<User> userClass = User.class; //same result

        System.out.println(user1.getClass());
        System.out.println(User.class);

        System.out.println(userClass.getName()); // -> full name of class with packages: reflectionAPI.lesson1.User
        System.out.println(userClass.getSimpleName()); //->name of class: User
        System.out.println(userClass.getSuperclass()); //class reflectionAPI.lesson1.Person
        System.out.println(Arrays.toString(userClass.getSuperclass().getDeclaredFields()));
        System.out.println(Arrays.toString(userClass.getMethods())); //wszystkie metody dostnepne (t.z. metody z Object)
        System.out.println(Arrays.toString(userClass.getDeclaredMethods())); //metody wewnatrz clasy
        System.out.println(Arrays.toString(userClass.getInterfaces()));
        System.out.println(userClass.getGenericInterfaces()[1]); //java.lang.Comparable<reflectionAPI.lesson1.User>
        System.out.println(userClass.getModifiers()); //1
        System.out.println(userClass.getPackageName()); //reflectionAPI.lesson1

    }
}
