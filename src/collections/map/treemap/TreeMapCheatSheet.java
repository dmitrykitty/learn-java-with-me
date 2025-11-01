package collections.map.treemap;

import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Ściąga (Cheat Sheet) z najważniejszych metod TreeMap.
 * Zwróć uwagę na gwarantowane sortowanie kluczy!
 */
public class TreeMapCheatSheet {
    public static void main(String[] args) {

        // --- 1. TWORZENIE (Dwa sposoby) ---

        // Sposób A: Domyślny, z sortowaniem "naturalnym"
        // Klucze (np. String) muszą implementować interfejs 'Comparable'.
        NavigableMap<String, Integer> wyniki = new TreeMap<>();

        // Sposób B: Z własnym sortowaniem (np. odwrotnie)
        // Map<String, Integer> wyniki = new TreeMap<>(Comparator.reverseOrder());


        // --- 2. DODAWANIE (put) ---
        // 'put' jest wolniejszy niż w HashMap (O(log n)),
        // ponieważ każde dodanie musi utrzymać drzewo w równowadze i posortowaniu.
        wyniki.put("Bartek", 80);
        wyniki.put("Anna", 95);
        wyniki.put("Cyprian", 70);

        // KOLEJNOŚĆ! Mapa jest już posortowana alfabetycznie wg kluczy.
        // wyniki: {Anna=95, Bartek=80, Cyprian=70}
        System.out.println("1. Mapa jest zawsze posortowana: " + wyniki);

        // --- 3. STANDARDOWE METODY (działają tak samo) ---
        System.out.println("2. get(\"Anna\"): " + wyniki.get("Anna")); // 95
        System.out.println("3. containsKey(\"Dawid\"): " + wyniki.containsKey("Dawid")); // false


        // ================================================================
        // --- 4. METODY WYRÓŻNIAJĄCE (Interfejs NavigableMap) ---
        // Tych metod nie ma w HashMap ani LinkedHashMap.
        // ================================================================

        // --- 4a. Dostęp do pierwszego/ostatniego (najmniejszego/największego klucza) ---
        Map.Entry<String, Integer> pierwszyWpis = wyniki.firstEntry();
        System.out.println("4a. Pierwszy wpis (firstEntry): " + pierwszyWpis); // Anna=95

        String ostatniKlucz = wyniki.lastKey();
        System.out.println("    Ostatni klucz (lastKey): " + ostatniKlucz); // Cyprian


        // --- 4b. Usuwanie pierwszego/ostatniego ---
        // Idealne do implementacji kolejek priorytetowych
        Map.Entry<String, Integer> usunietyPierwszy = wyniki.pollFirstEntry();
        // wyniki: {Bartek=80, Cyprian=70}
        System.out.println("4b. Usunięto pierwszy (pollFirstEntry): " + usunietyPierwszy);
        System.out.println("    Mapa po usunięciu: " + wyniki);

        // Wróćmy do pełnej mapy
        wyniki.put("Anna", 95);
        // wyniki: {Anna=95, Bartek=80, Cyprian=70}


        // --- 4c. Wyszukiwanie "najbliższych" kluczy ---

        // ceilingKey (sufit): Znajdź klucz >= podanego.
        // Jaki jest pierwszy klucz, który jest "Adam" lub alfabetycznie po nim?
        System.out.println("4c. ceilingKey(\"Adam\"): " + wyniki.ceilingKey("Adam")); // Anna

        // floorKey (podłoga): Znajdź klucz <= podanego.
        // Jaki jest ostatni klucz, który jest "Basia" lub alfabetycznie przed nią?
        System.out.println("    floorKey(\"Basia\"): " + wyniki.floorKey("Basia")); // Bartek

        // higherKey (wyższy): Znajdź klucz > podanego (ściśle większy).
        System.out.println("    higherKey(\"Bartek\"): " + wyniki.higherKey("Bartek")); // Cyprian

        // lowerKey (niższy): Znajdź klucz < podanego (ściśle mniejszy).
        System.out.println("    lowerKey(\"Bartek\"): " + wyniki.lowerKey("Bartek")); // Anna


        // --- 4d. Pobieranie "widoku" fragmentu mapy (subMap) ---
        // Stwórz mapę zawierającą klucze od "Anna" (włącznie) do "Cyprian" (wyłącznie).
        Map<String, Integer> podMapa = wyniki.subMap("Anna", true, "Cyprian", false);
        // podMapa: {Anna=95, Bartek=80}
        System.out.println("4d. podMapa (subMap): " + podMapa);


        // --- 4e. Pobieranie "widoku" w odwrotnej kolejności (descendingMap) ---
        Map<String, Integer> odwroconaMapa = wyniki.descendingMap();
        // odwroconaMapa: {Cyprian=70, Bartek=80, Anna=95}
        System.out.println("4e. Odwrócona mapa (descendingMap): " + odwroconaMapa);
    }
}
