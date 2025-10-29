package collections.map.linkedhashmap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Cheat Sheet z najważniejszych metod LinkedHashMap.
 */
public class LinkedHashMapCheatSheet {

    public static void main(String[] args) {

        // --- 1. TWORZENIE (Kolejność Wstawiania) ---
        // Domyślnie, LinkedHashMap pamięta kolejność wstawiania (insertion-order)
        Map<String, String> stolice = new LinkedHashMap<>();

        // stolice: {}
        System.out.println("1. Nowa mapa: " + stolice);


        // --- 2. DODAWANIE (put) ---
        // Metody 'put' i 'get' są tak samo szybkie jak w HashMap,
        // ale KOLEJNOŚĆ jest zachowana.
        stolice.put("Polska", "Warszawa");
        // stolice: {Polska=Warszawa}
        System.out.println("2. Po put(\"Polska\"): " + stolice);

        stolice.put("Niemcy", "Berlin");
        // stolice: {Polska=Warszawa, Niemcy=Berlin}
        System.out.println("   Po put(\"Niemcy\"): " + stolice);

        stolice.put("Francja", "Paryż");
        // stolice: {Polska=Warszawa, Niemcy=Berlin, Francja=Paryż}
        System.out.println("   Po put(\"Francja\"): " + stolice);


        // --- 3. AKTUALIZACJA (put) ---
        // Aktualizacja wartości (gdy klucz już istnieje)
        // NIE ZMIENIA kolejności tego klucza!
        stolice.put("Niemcy", "Berlin (stolica)");
        // stolice: {Polska=Warszawa, Niemcy=Berlin (stolica), Francja=Paryż}
        System.out.println("3. Po aktualizacji \"Niemcy\": " + stolice);


        // --- 4. USUWANIE (remove) ---
        // Usunięcie elementu również nie psuje kolejności pozostałych.
        stolice.remove("Niemcy");
        // stolice: {Polska=Warszawa, Francja=Paryż}
        System.out.println("4. Po remove(\"Niemcy\"): " + stolice);


        // --- 5. ITEROWANIE (Główna zaleta) ---
        // Iteracja ZAWSZE odbywa się w kolejności wstawiania.

        System.out.println("\n5. Iteracja po kluczach (keySet):");
        // Klucze wyjdą w kolejności: Polska, Francja
        for (String kraj : stolice.keySet()) {
            System.out.println("   - " + kraj);
        }

        System.out.println("   Iteracja po wpisach (entrySet):");
        // Wpisy wyjdą w kolejności: Polska, Francja
        for (Map.Entry<String, String> wpis : stolice.entrySet()) {
            System.out.println("   - " + wpis.getKey() + " -> " + wpis.getValue());
        }


        // --- 6. SUPERMOC: KOLEJNOŚĆ DOSTĘPU (Access-Order) ---
        // Tworzymy specjalną mapę dla LRU Cache
        // true = włącz tryb 'accessOrder'
        Map<Integer, String> lruCache = new LinkedHashMap<>(16, 0.75f, true);

        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        // lruCache: {1=A, 2=B, 3=C} (Kolejność wstawiania)
        System.out.println("\n6. Mapa w trybie AccessOrder: " + lruCache);

        // Teraz "dotykamy" elementu o kluczu 2
        System.out.println("   Pobieram get(2)...");
        lruCache.get(2); // Nie robimy nic z wynikiem

        // Element "B" został przeniesiony na KONIEC listy
        // lruCache: {1=A, 3=C, 2=B}
        System.out.println("   Mapa po get(2): " + lruCache);

        // Najstarszym (najdawniej używanym) elementem jest teraz {1=A}
    }
}