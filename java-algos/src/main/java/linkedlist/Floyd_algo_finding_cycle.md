Algorytm Floyda, znany powszechnie jako **algorytm żółwia i zająca**, to jeden z najbardziej eleganckich algorytmów w informatyce, służący do wykrywania cykli w strukturach danych, w których każdy element wskazuje na dokładnie jeden następny element (np. listy jednokierunkowe, skończone automaty deterministyczne).

Jego główną zaletą jest niezwykle niska złożoność pamięciowa: , przy zachowaniu liniowej złożoności czasowej: .

---

### 1. Faza I: Wykrywanie obecności cyklu

Algorytm wykorzystuje dwa wskaźniki, które startują z tego samego punktu (początku struktury):

* **Żółw (tortoise):** przesuwa się o jeden krok na iterację ().
* **Zając (hare):** przesuwa się o dwa kroki na iterację ().

**Mechanizm działania:**
Jeśli cykl nie istnieje, zając pierwszy dotrze do końca struktury (wskaźnik `null`). Jeśli jednak cykl istnieje, zając w pewnym momencie "wejdzie" w pętlę i zacznie ją gonić. Ponieważ zając porusza się o 1 węzeł szybciej niż żółw w każdej iteracji, dystans między nimi wewnątrz cyklu będzie się zmniejszał o 1, aż w końcu zając "zdubluje" żółwia.

**Punkt spotkania:** Moment, w którym `tortoise == hare`.

---

### 2. Faza II: Znalezienie punktu startowego cyklu

To najbardziej interesująca matematycznie część algorytmu. Aby znaleźć węzeł, w którym zaczyna się cykl, po ich spotkaniu w Fazie I musimy wykonać następujące kroki:

1. Zostawiamy jeden wskaźnik (np. zająca) w punkcie spotkania.
2. Drugi wskaźnik (żółwia) przenosimy z powrotem na sam początek listy.
3. Przesuwamy oba wskaźniki z **tą samą prędkością** (jeden krok na iterację).
4. Miejsce, w którym ponownie się spotkają, jest **początkiem cyklu**.

#### Dowód matematyczny:

Załóżmy następujące oznaczenia:

* : dystans od początku listy do wejścia do cyklu.
* : długość cyklu.
* : dystans od wejścia do cyklu do punktu pierwszego spotkania.

W momencie pierwszego spotkania:

* Dystans przebyty przez żółwia: .
* Dystans przebyty przez zająca:  (gdzie  to liczba pełnych okrążeń cyklu wykonanych przez zająca).

Wiemy, że zając jest dwa razy szybszy:


Równanie  mówi nam, że dystans od początku listy do wejścia do cyklu () jest dokładnie taki sam, jak dystans z punktu spotkania do wejścia do cyklu (wykonując  okrążeń i cofając się o ). Dlatego, gdy oba wskaźniki poruszają się z tą samą prędkością, muszą spotkać się na wejściu do cyklu.

---

### 3. Faza III: Obliczanie długości cyklu

Gdy już znamy punkt startowy cyklu lub mamy punkt spotkania z Fazy I, wyznaczenie długości  jest trywialne:

1. Unieruchamiamy jeden wskaźnik w dowolnym miejscu wewnątrz cyklu.
2. Drugim wskaźnikiem wykonujemy obieg pętli, aż wróci do unieruchomionego wskaźnika.
3. Liczba kroków wykonanych podczas tego obiegu to długość cyklu .

---

### Podsumowanie parametrów algorytmu

| Cecha | Wartość | Opis |
| --- | --- | --- |
| **Złożoność czasowa** |  | W najgorszym przypadku przechodzimy listę ok. 2-3 razy. |
| **Złożoność pamięciowa** |  | Przechowujemy tylko dwa wskaźniki i licznik. |
| **Zastosowanie** | Listy, Grafy funkcyjne | Wykrywanie zapętleń w strukturach wskaźnikowych. |

### Przykład implementacji (pseudokod)

```python
def find_cycle_details(head):
    # Faza 1: Detekcja
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            break
    else:
        return "Brak cyklu"

    # Faza 2: Start cyklu
    slow = head
    while slow != fast:
        slow = slow.next
        fast = fast.next
    start_node = slow

    # Faza 3: Długość cyklu
    length = 0
    current = start_node
    while True:
        current = current.next
        length += 1
        if current == start_node:
            break
            
    return start_node, length

```

Czy chciałbyś, abym przeanalizował zachowanie tego algorytmu na konkretnym przykładzie numerycznym lub w kontekście generatorów liczb pseudolosowych (np. algorytm  Pollarda)?