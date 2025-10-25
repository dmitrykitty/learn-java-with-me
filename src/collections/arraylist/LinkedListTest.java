package collections.arraylist;

import java.util.List;
import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {

        // --- 1. TWORZENIE ---
        // Zazwyczaj tworzymy LinkedList, przypisując ją do interfejsu List...
        List<String> list = new LinkedList<>();

        // ALE! Aby uzyskać dostęp do specjalnych metod (np. addFirst),
        // musimy zadeklarować ją bezpośrednio jako LinkedList.
        LinkedList<String> pociag = new LinkedList<>();

        // pociag: []
        System.out.println("1. Nowa lista: " + pociag);


        // --- 2. METODY ZWYKŁE (Takie jak w ArrayList) ---

        // --- 2a. add(element) ---
        // Działa tak samo - dodaje na koniec.
        pociag.add("Lokomotywa");
        // pociag: [Lokomotywa]
        pociag.add("Wagon 1");
        // pociag: [Lokomotywa, Wagon 1]
        System.out.println("2a. Po add: " + pociag);

        // --- 2b. add(index, element) ---
        // "Wpycha" element na indeks. W LinkedList jest to WOLNIEJSZE
        // niż w ArrayList, jeśli indeks jest daleko od początku/końca.
        pociag.add(1, "Wagon Jadalny");
        // pociag: [Lokomotywa, Wagon Jadalny, Wagon 1]
        System.out.println("2b. Po add(1, ...): " + pociag);

        // --- 2c. get(index) ---
        // Pobiera element. To jest NAJWIĘKSZA WADA LinkedList.
        // Operacja jest WOLNA (O(n)), bo lista musi iterować od początku lub końca.
        String drugi = pociag.get(1);
        System.out.println("2c. Element na get(1): " + drugi);

        // --- 2d. remove(index) ---
        // Usuwa element z indeksu. Również WOLNE (O(n)).
        pociag.remove(1);
        // pociag: [Lokomotywa, Wagon 1]
        System.out.println("2d. Po remove(1): " + pociag);


        // =================================================================
        // --- 3. SPECJALNE METODY (Siła LinkedList - interfejs Deque) ---
        // Te metody są BŁYSKAWICZNE (O(1)) i to dla nich wybiera się LinkedList.
        // =================================================================

        // --- 3a. Dodawanie na początek (addFirst) ---
        // Dodaje element jako nową "głowę" listy.
        pociag.addFirst("Zderzak");
        // pociag: [Zderzak, Lokomotywa, Wagon 1]
        System.out.println("3a. Po addFirst(\"Zderzak\"): " + pociag);


        // --- 3b. Dodawanie na koniec (addLast) ---
        // Działa identycznie jak zwykłe 'add()'.
        pociag.addLast("Wagon Sypialny");
        // pociag: [Zderzak, Lokomotywa, Wagon 1, Wagon Sypialny]
        System.out.println("3b. Po addLast(...): " + pociag);


        // --- 3c. Pobieranie pierwszego elementu (getFirst / peekFirst) ---
        // 'getFirst()' pobiera element, ale go NIE usuwa.
        // Jeśli lista jest pusta, rzuca wyjątek (błąd).
        String pierwszy = pociag.getFirst();
        System.out.println("3c. Pierwszy element (getFirst): " + pierwszy);

        // 'peekFirst()' (lub po prostu 'peek()') jest bezpieczniejsze.
        // Robi to samo, ale jeśli lista jest pusta, zwraca null.
        String pierwszyBezpiecznie = pociag.peekFirst();
        System.out.println("    Pierwszy element (peekFirst): " + pierwszyBezpiecznie);


        // --- 3d. Pobieranie ostatniego elementu (getLast / peekLast) ---
        // Analogicznie do getFirst/peekFirst, ale dla końca listy.
        String ostatni = pociag.getLast();
        System.out.println("3d. Ostatni element (getLast): " + ostatni);


        // --- 3e. Usuwanie pierwszego elementu (removeFirst / pollFirst) ---
        // 'removeFirst()' (lub po prostu 'remove()') pobiera I USUWA element z początku.
        // Rzuca wyjątek, jeśli lista jest pusta.
        String usunietyPierwszy = pociag.removeFirst();
        // pociag: [Lokomotywa, Wagon 1, Wagon Sypialny]
        System.out.println("3e. Usunięto z początku: " + usunietyPierwszy);
        System.out.println("    Lista po removeFirst(): " + pociag);

        // 'pollFirst()' (lub po prostu 'poll()') jest bezpieczniejsze.
        // Robi to samo, ale jeśli lista jest pusta, zwraca null.
        String usunietyBezpiecznie = pociag.pollFirst();
        // pociag: [Wagon 1, Wagon Sypialny]
        System.out.println("    Usunięto z początku (pollFirst): " + usunietyBezpiecznie);
        System.out.println("    Lista po pollFirst(): " + pociag);


        // --- 3f. Usuwanie ostatniego elementu (removeLast / pollLast) ---
        // Analogicznie do removeFirst/pollFirst, ale dla końca listy.
        String usunietyOstatni = pociag.removeLast();
        // pociag: [Wagon 1]
        System.out.println("3f. Usunięto z końca: " + usunietyOstatni);
        System.out.println("    Lista po removeLast(): " + pociag);


        // --- 4. Zastosowanie jako Stos (Stack) ---
        // LinkedList jest też idealna jako stos (LIFO - Last In, First Out)

        // 'push(element)' - dodaje element na początek (jak addFirst)
        pociag.push("Nowy Pierwszy");
        pociag.push("Jeszcze Nowszy");
        // pociag: [Jeszcze Nowszy, Nowy Pierwszy, Wagon 1]
        System.out.println("4a. Lista po dwóch push(): " + pociag);

        // 'pop()' - usuwa i zwraca element z początku (jak removeFirst)
        String zdjetyZeStosu = pociag.pop();
        // pociag: [Nowy Pierwszy, Wagon 1]
        System.out.println("4b. Element zdjęty ze stosu (pop): " + zdjetyZeStosu);
        System.out.println("    Lista po pop(): " + pociag);
    }
}
