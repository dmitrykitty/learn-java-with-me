package reflectionAPI.constructors;

import reflectionAPI.lesson1.User;

import java.lang.reflect.*;


public class TestConstructors {
    static void main() {

        User user1 = new User(25L, "Dima", 32);
        Class<User> userClass = User.class;

        try {
            testConstructor();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            testFields(user1);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            testMethods(user1);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testConstructor() throws
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        Constructor<User> constructor = User.class.getConstructor(long.class, String.class); //konstrukotr, przyjmujacy long i string
        User kris = constructor.newInstance(12L, "Kris");
        System.out.println(kris);
    }

    private static void testFields(User user) throws IllegalAccessException {
        //rzuca exception, jezelie fialds are private
        Field[] declaredFields = User.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true); //aby miec dostep d private fields
            Object value = declaredField.get(user);
            System.out.println(declaredField.getModifiers());
            //zwraca bit version np, pierwszy bit odpowiada za publuc, drugi za final, trzeci za static...
            //[00000010] = 2 -> dostajemy info
            System.out.println(Modifier.isPrivate(declaredField.getModifiers())); //Modifier - finall class to get info about modifiers
            System.out.println(value);
        }
    }

    private static void testMethods(User user) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodGetName = user.getClass().getDeclaredMethod("getName"); //->dostajemy method getName()
        System.out.println(methodGetName.invoke(user)); //->wywołujemy ten method przekazujac mu object method.invoke(obj)

        Method methodSetName = User.class.getDeclaredMethod("setName", String.class);
        methodSetName.invoke(user, "Krisik");
        System.out.println(methodGetName.invoke(user));

    }

}
