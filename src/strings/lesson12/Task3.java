package strings.lesson12;

public class Task3 {
    static void main() {
        System.out.println(getFIO("Dzmitry", "Nikitisn", "olegovich"));
        System.out.println(getFIO2("Dzmitry", "Nikitisn", "olegovich"));

    }
    static String getFIO(String name, String surname, String patronymic){
        return surname.toUpperCase().charAt(0) +
                ". " + name.toUpperCase().charAt(0) +
                ". " + patronymic.toUpperCase().charAt(0) +
                ".";
    }

    static String getFIO2(String name, String surname, String patronymic){
        return Character.toUpperCase(surname.charAt(0)) +
                "." + Character.toUpperCase(name.charAt(0)) +
                "." + Character.toUpperCase(patronymic.charAt(0)) +
                ".";
    }
}

