### 1. Punkt przecięcia dwóch list (Intersection of Two Linked Lists)

Problem polega na znalezieniu węzła, w którym dwie różne listy zaczynają współdzielić tę samą strukturę (tworzą kształt litery Y).

* **Technika:** Najbardziej eleganckie rozwiązanie wykorzystuje dwa wskaźniki. Wskaźnik A przechodzi listę 1, a po jej zakończeniu przeskakuje na początek listy 2. Wskaźnik B przechodzi listę 2, a po jej zakończeniu przeskakuje na początek listy 1.
* **Dlaczego to działa:** Jeśli listy mają długości odpowiednio  i , to po przeskoku oba wskaźniki pokonają dystans . Dzięki temu zrównują różnicę w długościach i spotkają się dokładnie w punkcie przecięcia. Złożoność czasowa to , a pamięciowa .

### 2. Kopiowanie listy z losowymi wskaźnikami (Copy List with Random Pointer)

Masz listę, w której każdy węzeł oprócz `next` ma wskaźnik `random` wskazujący na dowolny inny węzeł lub `null`.

* **Technika "Interweaving":** Zamiast używać mapy (Hash Map) do mapowania starych węzłów na nowe (co zajmuje  pamięci), możesz wstawić kopię każdego węzła bezpośrednio za oryginałem (np. ).
* **Mechanizm:** W pierwszym przejściu tworzysz kopie i wstawiasz je w strukturę. W drugim przejściu ustawiasz wskaźniki `random` dla kopii (będą to `curr.next.random = curr.random.next`). W trzecim przejściu rozdzielasz listy. Pozwala to na osiągnięcie  dodatkowej pamięci.

### 3. Sortowanie listy (Sort List)

Wymagane jest zazwyczaj posortowanie listy w czasie  przy  dodatkowej pamięci (nie licząc stosu rekurencji).

* **Technika:** Najlepiej sprawdza się tutaj **Merge Sort**.
* **Powiązanie z Twoimi zadaniami:** Wykorzystujesz `LT876_MiddleOfTheLinkedList` do dzielenia listy na pół oraz `LT21_MergeTwoSortedLists` do łączenia posortowanych części. Jest to świetny przykład na to, jak proste techniki składają się na złożony algorytm.

### 4. Reorder List

Zadanie polega na przekształceniu listy  w listę .

* **Technika:** Jest to kombinacja trzech Twoich implementacji:
1. Znalezienie środka (Slow/Fast).
2. Odwrócenie drugiej połowy listy.
3. Naprzemienne łączenie (merging) obu list.



### 5. Partycjonowanie listy (Partition List)

Masz wartość  i musisz przeorganizować listę tak, aby wszystkie węzły o wartości mniejszej niż  znajdowały się przed węzłami o wartości większej lub równej , zachowując ich pierwotną kolejność.

* **Technika:** Użycie dwóch węzłów atrapowych (Dummy Nodes) – jednego dla listy "mniejszych" i jednego dla "większych". Po przejściu całej listy głównej, po prostu łączysz koniec pierwszej listy z początkiem drugiej.

### 6. Dodawanie dwóch liczb (Add Two Numbers)

Liczby reprezentowane są jako listy (cyfry w odwrotnej kolejności). Musisz zwrócić sumę jako nową listę.

* **Haczyk:** Kluczowe jest poprawne zarządzanie przeniesieniem (carry) oraz obsługa sytuacji, gdy listy mają różną długość lub suma na ostatnim miejscu generuje nowe przeniesienie (np. ).

### 7. Listy dwukierunkowe (Doubly Linked List) i LRU Cache

W Twoich plikach skupiłeś się na listach jednokierunkowych. Na rozmowach o architekturę systemów (System Design) lub bardziej zaawansowane struktury danych, pojawia się implementacja **LRU Cache (Least Recently Used)**.

* **Mechanizm:** Wymaga ona połączenia `HashMap` (dla dostępu ) oraz `Doubly Linked List` (dla usuwania i dodawania elementów w czasie  w dowolnym miejscu). Warto zaimplementować przynajmniej podstawowe usuwanie węzła z listy dwukierunkowej, ponieważ różni się ono od podejścia `slowPred` stosowanego w listach jednokierunkowych.

Oto zestawienie konkretnych zadań z platformy LeetCode, które najlepiej reprezentują zaawansowane techniki pracy z listami jednokierunkowymi (i dwukierunkowymi), o których wspomniałem. Opanowanie tych 7 zadań, obok tych, które już zaimplementowałeś, da Ci pełny obraz manipulacji wskaźnikami wymagany na poziomie akademickim i rekrutacyjnym.

---

### 1. Intersection of Two Linked Lists

**Zadanie:** [LeetCode 160. Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/)

* **Dlaczego to zadanie:** To klasyk sprawdzający umiejętność synchronizacji dwóch wskaźników poruszających się po strukturach o różnej długości.
* **Klucz techniczny:** Rozwiązanie z "przeskokiem" wskaźników na początek drugiej listy po dotarciu do końca pierwszej. Jest to najbardziej elegancki sposób na zniwelowanie różnicy długości (różnica ) bez jawnego liczenia elementów.

### 2. Copy List with Random Pointer

**Zadanie:** [LeetCode 138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)

* **Dlaczego to zadanie:** Uczy, jak kopiować złożone struktury grafowe oparte na listach bez użycia dodatkowej pamięci na mapowanie węzłów.
* **Klucz techniczny:** Technika "Interweaving" (przeplatania). Tworzysz nowy węzeł bezpośrednio za starym, co pozwala na dostęp do wskaźnika `random` kopii poprzez `original.random.next`. To zadanie jest bardzo często zadawane w Google i Amazonie.

### 3. Sort List (Merge Sort)

**Zadanie:** [LeetCode 148. Sort List](https://leetcode.com/problems/sort-list/)

* **Dlaczego to zadanie:** Wymusza zastosowanie algorytmu **Merge Sort** na liście. W tablicach Quick Sort jest częstszy, ale w listach to Merge Sort dominuje ze względu na sekwencyjny dostęp do danych.
* **Klucz techniczny:** Musisz połączyć mechanizm znajdowania środka (Twój `LT876`) z rekurencyjnym dzieleniem i scalaniem (Twój `LT21`). To doskonały sprawdzian modularności kodu.

### 4. Reorder List

**Zadanie:** [LeetCode 143. Reorder List](https://leetcode.com/problems/reorder-list/)

* **Dlaczego to zadanie:** To zadanie "wszystko w jednym". Nie da się go rozwiązać optymalnie bez znajomości trzech innych technik.
* **Klucz techniczny:** Proces składa się z:
1. Znalezienia środka (Slow/Fast).
2. Odwrócenia drugiej połowy listy (In-place reversal).
3. Scalenia dwóch list w specyficzny, naprzemienny sposób.



### 5. Partition List

**Zadanie:** [LeetCode 86. Partition List](https://leetcode.com/problems/partition-list/)

* **Dlaczego to zadanie:** Uczy zarządzania dwoma niezależnymi strumieniami danych (węzłów) i ich bezpiecznego łączenia.
* **Klucz techniczny:** Wykorzystanie dwóch węzłów atrapowych (`lessDummy` i `greaterDummy`). Musisz uważać na "cykle" – na końcu należy ustawić `greater.next = null`, inaczej lista może stać się nieskończona, jeśli ostatni element był mniejszy od pivotu.

### 6. Add Two Numbers

**Zadanie:** [LeetCode 2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)

* **Dlaczego to zadanie:** Podstawowe zadanie na symulację arytmetyki na strukturach wskaźnikowych.
* **Klucz techniczny:** Obsługa przeniesienia (carry) i warunków brzegowych (różne długości list, przeniesienie na samym końcu tworzące nowy węzeł). Choć wydaje się proste, diabeł tkwi w czystości implementacji pętli `while`.

### 7. LRU Cache

**Zadanie:** [LeetCode 146. LRU Cache](https://leetcode.com/problems/lru-cache/)

* **Dlaczego to zadanie:** To jedno z najważniejszych zadań z pogranicza struktur danych i projektowania systemów.
* **Klucz techniczny:** Implementacja listy dwukierunkowej (**Doubly Linked List**) zintegrowanej z `HashMap`. Musisz zarządzać węzłami tak, aby operacje `get` i `put` działy w czasie . To zadanie nauczy Cię, dlaczego listy dwukierunkowe są niezbędne tam, gdzie potrzebujemy usuwać elementy ze środka w stałym czasie (mając wskaźnik na węzeł).

---

### Podsumowanie dla studenta CS

Zauważ, że Twoje obecne pliki pokrywają już mechanikę potrzebną do większości tych zadań:

* `LT876_MiddleOfTheLinkedList` jest fundamentem dla zadań 3 i 4.
* `LT206_ReverseLinkedList` jest fundamentem dla zadania 4.
* `LT21_MergeTwoSortedLists` jest fundamentem dla zadania 3.
* `MyLinkedListWithDummyNode` nauczyło Cię bezpiecznej manipulacji wskaźnikami, co jest kluczowe w zadaniach 5 i 7.
