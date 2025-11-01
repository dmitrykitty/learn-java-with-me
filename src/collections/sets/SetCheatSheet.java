package collections.sets;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.List;

public class SetCheatSheet {
    public static void main(String[] args) {

        // --- 1. TWORZENIE (Użyjemy HashSet do podstawowych operacji) ---
        Set<String> przedmioty = new HashSet<>();

        // przedmioty: []
        System.out.println("1. Nowy zbiór: " + przedmioty);


        // --- 2. DODAWANIE (add) ---
        // Metoda 'add()' dodaje element. Zwraca 'true', jeśli się udało.
        przedmioty.add("Klucze");
        przedmioty.add("Telefon");
        // przedmioty: [Telefon, Klucze] (kolejność w HashSet nie jest gwarantowana!)
        System.out.println("2. Po dodaniu dwóch: " + przedmioty);

        // --- 3. IGNOROWANIE DUPLIKATÓW (add) ---
        // Próba dodania "Telefon" ponownie.
        boolean czyDodano = przedmioty.add("Telefon");

        // przedmioty: [Telefon, Klucze] (NIC SIĘ NIE ZMIENIŁO)
        System.out.println("3. Po próbie dodania duplikatu: " + przedmioty);
        System.out.println("   Czy element został dodany? " + czyDodano); // false


        // --- 4. SPRAWDZANIE ZAWARTOŚCI (contains) ---
        // To jest główna zaleta HashSet - operacja jest błyskawiczna (O(1)).
        boolean czyMaKlucze = przedmioty.contains("Klucze");
        System.out.println("4. Czy zawiera \"Klucze\" (contains): " + czyMaKlucze); // true


        // --- 5. USUWANIE (remove) ---
        // Usuwa element, jeśli istnieje. Zwraca 'true', jeśli się udało.
        boolean czyUsunieto = przedmioty.remove("Portfel"); // Nie ma "Portfel"
        System.out.println("5. Próba usunięcia \"Portfel\": " + czyUsunieto); // false

        przedmioty.remove("Telefon");
        // przedmioty: [Klucze]
        System.out.println("   Po remove(\"Telefon\"): " + przedmioty);


        // --- 6. ROZMIAR i CZYSZCZENIE (size, isEmpty, clear) ---
        System.out.println("6. Rozmiar (size): " + przedmioty.size()); // 1
        System.out.println("   Czy pusty (isEmpty): " + przedmioty.isEmpty()); // false

        przedmioty.clear();
        // przedmioty: []
        System.out.println("   Po clear(): " + przedmioty);
        System.out.println("   Czy pusty po clear(): " + przedmioty.isEmpty()); // true


        // ================================================================
        // --- 7. RÓŻNICA w implementacjach: KOLEJNOŚĆ ---
        // ================================================================

        // Stwórzmy listę wejściową, aby pokazać różnice
        List<Integer> liczbyWejsciowe = List.of(50, 10, 30, 10); // Celowy duplikat "10"

        // --- 7a. HashSet (Bez kolejności) ---
        Set<Integer> hashSet = new HashSet<>(liczbyWejsciowe);
        // hashSet: [50, 10, 30] (Kolejność jest przypadkowa, duplikat '10' zniknął)
        System.out.println("\n7a. HashSet (bez kolejności): " + hashSet);

        // --- 7b. LinkedHashSet (Kolejność wstawiania) ---
        Set<Integer> linkedHashSet = new LinkedHashSet<>(liczbyWejsciowe);
        // linkedHashSet: [50, 10, 30] (Dokładnie taka kolejność, jak były wstawiane)
        System.out.println("7b. LinkedHashSet (kolejność wstawiania): " + linkedHashSet);

        // --- 7c. TreeSet (Kolejność posortowana) ---
        Set<Integer> treeSet = new TreeSet<>(liczbyWejsciowe);
        // treeSet: [10, 30, 50] (Elementy zostały automatycznie posortowane)
        System.out.println("7c. TreeSet (kolejność naturalna/sortowania): " + treeSet);


        // ================================================================
        // --- 8. OPERACJE ZBIORÓW (Wspólne dla wszystkich Set) ---
        // ================================================================

        Set<Integer> setA = new HashSet<>(List.of(1, 2, 3));
        Set<Integer> setB = new HashSet<>(List.of(3, 4, 5));

        // --- 8a. Unia (addAll) ---
        // Dodaje do setA wszystkie elementy z setB (duplikaty są ignorowane)
        // setA.addAll(setB); // setA teraz to [1, 2, 3, 4, 5]

        // --- 8b. Różnica (removeAll) ---
        // Usuwa z setA te elementy, które są też w setB
        // setA.removeAll(setB); // setA teraz to [1, 2]

        // --- 8c. Część wspólna (retainAll) ---
        // Zostawia w setA tylko te elementy, które są też w setB
        setA.retainAll(setB); // setA teraz to [3]
        System.out.println("\n8. Część wspólna (retainAll): " + setA);
    }

}
