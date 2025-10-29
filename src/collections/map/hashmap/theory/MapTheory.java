package collections.map.hashmap.theory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTheory {

    public static void main(String[] args) {

        // --- 1. TWORZENIE (Konstruktor) ---
        // Najczęściej tworzymy HashMap, przypisując ją do interfejsu Map
        // Musimy określić typ Klucza (K) i Wartości (V)
        Map<String, Integer> punktyGraczy = new HashMap<>();

        // Mapa jest pusta.
        // punktyGraczy: {}
        System.out.println("1. Nowa mapa: " + punktyGraczy);


        // --- 2. DODAWANIE / AKTUALIZACJA (put) ---
        // Metoda 'put(Klucz, Wartość)' dodaje parę.
        // Klucze MUSZĄ być unikalne.
        punktyGraczy.put("Anna", 100);
        // punktyGraczy: {Anna=100}
        System.out.println("2. Po put(\"Anna\", 100): " + punktyGraczy);

        punktyGraczy.put("Piotr", 150);
        // punktyGraczy: {Anna=100, Piotr=150} (kolejność nie jest gwarantowana)
        System.out.println("   Po put(\"Piotr\", 150): " + punktyGraczy);

        // Jeśli klucz już istnieje, 'put' ZAKTUALIZUJE jego wartość.
        // Zwraca również starą wartość (lub null, jeśli jej nie było).
        Integer staraWartosc = punktyGraczy.put("Anna", 120);
        // punktyGraczy: {Anna=120, Piotr=150}
        System.out.println("   Po put(\"Anna\", 120): " + punktyGraczy);
        System.out.println("   Zwrócona (stara) wartość: " + staraWartosc); // 100


        // --- 3. POBIERANIE WARTOŚCI (get) ---
        // Metoda 'get(Klucz)' zwraca wartość powiązaną z kluczem.
        Integer punktyPiotra = punktyGraczy.get("Piotr");
        System.out.println("3. Wartość dla klucza \"Piotr\" (get): " + punktyPiotra); // 150

        // Jeśli klucza nie ma w mapie, 'get' zwraca 'null'.
        Integer punktyMarka = punktyGraczy.get("Marek");
        System.out.println("   Wartość dla \"Marek\" (nieistniejący): " + punktyMarka); // null


        // --- 4. POBIERANIE Z WARTOŚCIĄ DOMYŚLNĄ (getOrDefault) ---
        // Bardzo przydatna metoda (od Javy 8). Działa jak 'get',
        // ale jeśli klucza nie ma, zwraca podaną wartość domyślną zamiast 'null'.
        Integer punktyMarkaBezNulla = punktyGraczy.getOrDefault("Marek", 0);
        System.out.println("4. Wartość dla \"Marek\" (getOrDefault): " + punktyMarkaBezNulla); // 0


        // --- 5. SPRAWDZANIE ZAWARTOŚCI (containsKey / containsValue) ---

        // 'containsKey(Klucz)' - bardzo szybka operacja
        boolean czyJestAnna = punktyGraczy.containsKey("Anna");
        System.out.println("5. Czy jest klucz \"Anna\" (containsKey): " + czyJestAnna); // true

        // 'containsValue(Wartość)' - wolniejsza operacja (musi przeszukać wartości)
        boolean czyKtosMa150 = punktyGraczy.containsValue(150);
        System.out.println("   Czy jest wartość 150 (containsValue): " + czyKtosMa150); // true


        // --- 6. SPRAWDZANIE ROZMIARU (size) ---
        // Metoda 'size()' zwraca liczbę PAR klucz-wartość.
        int rozmiar = punktyGraczy.size();
        System.out.println("6. Rozmiar mapy (size()): " + rozmiar); // 2


        // --- 7. USUWANIE PARY (remove) ---
        // Metoda 'remove(Klucz)' usuwa całą parę (klucz i wartość) powiązaną z kluczem.
        // Zwraca usuniętą wartość (lub null, jeśli klucza nie było).
        Integer usunietePunktyPiotra = punktyGraczy.remove("Piotr");
        // punktyGraczy: {Anna=120}
        System.out.println("7. Po remove(\"Piotr\"): " + punktyGraczy);
        System.out.println("   Usunięta wartość: " + usunietePunktyPiotra); // 150


        // --- 8. DODAWANIE JEŚLI BRAK (putIfAbsent) ---
        // Dodaje parę tylko wtedy, gdy klucz jeszcze nie istnieje.
        punktyGraczy.putIfAbsent("Anna", 500); // Nie zadziała, "Anna" już jest
        punktyGraczy.putIfAbsent("Marek", 200); // Zadziała, "Marek" nie istniał
        // punktyGraczy: {Anna=120, Marek=200}
        System.out.println("8. Po putIfAbsent: " + punktyGraczy);


        // --- 9. CZYSZCZENIE MAPY (clear / isEmpty) ---
        System.out.println("9. Czy mapa jest pusta (isEmpty): " + punktyGraczy.isEmpty()); // false

        punktyGraczy.clear();
        // punktyGraczy: {}
        System.out.println("   Po clear(): " + punktyGraczy);
        System.out.println("   Czy mapa jest pusta po clear(): " + punktyGraczy.isEmpty()); // true


        // --- 10. ITEROWANIE (3 SPOSOBY) ---
        // Nie da się iterować bezpośrednio po mapie, trzeba pobrać
        // widok kluczy, wartości lub wpisów.

        punktyGraczy.put("Gracz1", 10);
        punktyGraczy.put("Gracz2", 20);
        punktyGraczy.put("Gracz3", 30);
        // punktyGraczy: {Gracz1=10, Gracz2=20, Gracz3=30} (kolejność przypadkowa)

        System.out.println("\n10. Iterowanie:");

        // Sposób A: Iterowanie po kluczach (keySet)
        Set<String> klucze = punktyGraczy.keySet();
        System.out.println("  Iteracja po kluczach (keySet):");
        for (String klucz : klucze) {
            System.out.println("    - Klucz: " + klucz);
        }

        // Sposób B: Iterowanie po wartościach (values)
        Collection<Integer> wartosci = punktyGraczy.values();
        System.out.println("  Iteracja po wartościach (values):");
        for (Integer wartosc : wartosci) {
            System.out.println("    - Wartość: " + wartosc);
        }

        // Sposób C: Iterowanie po parach Klucz-Wartość (entrySet) - NAJLEPSZY
        Set<Map.Entry<String, Integer>> wpisy = punktyGraczy.entrySet();
        System.out.println("  Iteracja po wpisach (entrySet):");
        for (Map.Entry<String, Integer> wpis : wpisy) {
            System.out.println("    - Klucz: " + wpis.getKey() + " -> Wartość: " + wpis.getValue());
        }
    }
}
