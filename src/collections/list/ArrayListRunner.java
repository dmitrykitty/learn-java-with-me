package collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListRunner {
    static void main() {
        //jako refererencje do obiektu trzeba uzywac interfejsy
        //cap = sz = 0 dopoki nie dodano pierwszego elementu, pozniej cap = 10


        // --- 1. TWORZENIE (Konstruktor) ---
        // Najczęściej tworzymy ArrayList, przypisując ją do interfejsu List
        List<String> owoce = new ArrayList<>();

        // Lista jest pusta. Jej rozmiar (size) to 0.
        // owoce: []
        System.out.println("1. Nowa lista: " + owoce);


        // --- 2. DODAWANIE ELEMENTÓW (add) ---
        // Metoda 'add(element)' dodaje element na koniec listy.
        owoce.add("Jabłko");
        // owoce: [Jabłko]
        System.out.println("2. Po add(\"Jabłko\"): " + owoce);

        owoce.add("Banan");
        // owoce: [Jabłko, Banan]
        System.out.println("   Po add(\"Banan\"): " + owoce);


        // --- 3. DODAWANIE ELEMENTU NA INDEKSIE (add) ---
        // Metoda 'add(index, element)' "wpycha" element na daną pozycję,
        // przesuwając resztę elementów w prawo.
        owoce.add(1, "Pomarańcza");
        // owoce: [Jabłko, Pomarańcza, Banan]
        System.out.println("3. Po add(1, \"Pomarańcza\"): " + owoce);


        // --- 4. POBIERANIE ELEMENTU (get) ---
        // Metoda 'get(index)' zwraca element na podanej pozycji.
        // Pamiętaj, że indeksy liczymy od 0.
        String pierwszyOwoc = owoce.get(0); //lub mozna getFirst
        System.out.println("4. Element na indeksie 0 (get(0)): " + pierwszyOwoc); // Jabłko


        // --- 5. ZASTĘPOWANIE ELEMENTU (set) ---
        // Metoda 'set(index, element)' podmienia element na danej pozycji.
        // Nic się nie przesuwa.
        owoce.set(2, "Mango"); // Zastępuje "Banan" na "Mango"
        // owoce: [Jabłko, Pomarańcza, Mango]
        System.out.println("5. Po set(2, \"Mango\"): " + owoce);


        // --- 6. SPRAWDZANIE ROZMIARU (size) ---
        // Metoda 'size()' zwraca liczbę elementów na liście.
        int rozmiar = owoce.size();
        System.out.println("6. Rozmiar listy (size()): " + rozmiar); // 3


        // --- 7. USUWANIE ELEMENTU PRZEZ INDEKS (remove) ---
        // Metoda 'remove(index)' usuwa element na danej pozycji.
        // Elementy po prawej przesuwają się w lewo, aby wypełnić lukę.
        owoce.remove(0); // Usuwa "Jabłko" lub removeFirst
        // owoce: [Pomarańcza, Mango]
        System.out.println("7. Po remove(0): " + owoce);


        // --- 8. USUWANIE ELEMENTU PRZEZ WARTOŚĆ (remove) ---
        // Metoda 'remove(Object)' usuwa *pierwsze* napotkane wystąpienie danego elementu.
        owoce.remove("Mango");
        // owoce: [Pomarańcza]
        System.out.println("8. Po remove(\"Mango\"): " + owoce);


        // --- 9. SPRAWDZANIE ZAWARTOŚCI (contains) ---
        // Metoda 'contains(Object)' zwraca true/false w zależności,
        // czy dany element znajduje się na liście.
        boolean czyJestMango = owoce.contains("Mango");
        System.out.println("9. Czy lista zawiera \"Mango\" (contains): " + czyJestMango); // false

        boolean czyJestPomarancza = owoce.contains("Pomarańcza");
        System.out.println("   Czy lista zawiera \"Pomarańcza\": " + czyJestPomarancza); // true


        // --- 10. ZNAJDOWANIE POZYCJI (indexOf) ---
        // Metoda 'indexOf(Object)' zwraca indeks pierwszego wystąpienia elementu
        // lub -1, jeśli elementu nie ma na liście.
        owoce.add("Banan");
        owoce.add("Mango");
        // owoce: [Pomarańcza, Banan, Mango]
        System.out.println("10. Lista przed indexOf: " + owoce);
        int pozycjaBanana = owoce.indexOf("Banan");
        System.out.println("    Pozycja \"Banan\" (indexOf): " + pozycjaBanana); // 1
        int pozycjaSliwki = owoce.indexOf("Śliwka");
        System.out.println("    Pozycja \"Śliwka\": " + pozycjaSliwki); // -1


        // --- 11. SPRAWDZANIE CZY PUSTA (isEmpty) ---
        // Metoda 'isEmpty()' zwraca true, jeśli lista nie ma żadnych elementów (size == 0).
        System.out.println("11. Czy lista jest pusta (isEmpty): " + owoce.isEmpty()); // false


        // --- 12. CZYSZCZENIE LISTY (clear) ---
        // Metoda 'clear()' usuwa WSZYSTKIE elementy z listy.
        owoce.clear();
        // owoce: []
        System.out.println("12. Po clear(): " + owoce);
        System.out.println("    Czy lista jest pusta po clear(): " + owoce.isEmpty()); // true


        // --- 13. DODAWANIE KOLEKCJI (addAll) ---
        // Metoda 'addAll(Kolekcja)' dodaje wszystkie elementy z innej kolekcji
        // na koniec naszej listy.
        List<String> noweOwoce = Arrays.asList("Truskawka", "Malina", "Borówka");
        owoce.addAll(noweOwoce);
        // owoce: [Truskawka, Malina, Borówka]
        System.out.println("13. Po addAll(noweOwoce): " + owoce);

        // --- TWORZENIE (List.of) ---
        // Po prostu przekazujesz elementy jako argumenty
        List<String> kolory = List.of("Czerwony", "Zielony", "Niebieski");

        // Lista jest od razu zapełniona.
        // kolory: [Czerwony, Zielony, Niebieski]
        System.out.println("Lista stworzona za pomocą List.of(): " + kolory);


        // --- WAŻNA CECHA: NIEMODYFIKOWALNOŚĆ (IMMUTABILITY) ---

        // Każda próba zmiany tej listy (dodanie, usunięcie, zmiana elementu)
        // zakończy się błędem w czasie wykonania (UnsupportedOperationException).
    }
}
