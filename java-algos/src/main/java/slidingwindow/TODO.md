## Zaawansowane techniki Sliding Window

### 1. Monotonic Queue / Deque (Okno z ekstremum)

W Twoich zadaniach stanem okna była suma lub liczność. Istnieje jednak klasa problemów, gdzie w każdym kroku okna musisz znać jego wartość maksymalną lub minimalną.

* **Problem:** Naiwne znalezienie max w oknie o rozmiarze  zajmuje , co daje całkowitą złożoność .
* **Technika:** Używamy kolejki dwukierunkowej (`Deque`), która przechowuje indeksy elementów w sposób monotoniczny (malejący lub rosnący).
* **Dlaczego to działa:** Usuwamy z tyłu kolejki wszystkie elementy mniejsze od obecnie wstawianego, ponieważ nigdy nie będą one już "maksimum" w żadnym przyszłym oknie. Dzięki temu operacja odczytu ekstremum to zawsze .

### 2. Optymalizacja tablicą częstotliwości (Frequency Array)

W zadaniu `LT904` użyłeś `HashMap<Integer, Integer>` do śledzenia typów owoców.

* **Haczyk:** Jeśli zbiór możliwych wartości jest ograniczony (np. tylko małe litery alfabetu lub kody ASCII), `HashMap` jest nieefektywna ze względu na narzut pamięciowy i czasowy (haszowanie).
* **Technika:** Użyj tablicy `int[26]` lub `int[128]`. Indeks to kod znaku, a wartość to licznik wystąpień. To krytyczne w zadaniach typu "Longest Substring" lub "Anagrams".

### 3. Trick "Exactly K = At Most K - At Most K-1"

To najbardziej elegancka technika matematyczna w sliding window, często wymagana na poziomie Senior/Staff Engineer.

* **Problem:** Sliding window naturalnie radzi sobie z warunkami typu "nie więcej niż " (at most), ponieważ okno jest strukturą monotoniczną (powiększanie end nigdy nie naprawi naruszonego warunku "więcej niż "). Trudniej jest jednak utrzymać warunek "dokładnie ".
* **Technika:** Zamiast bezpośrednio szukać "dokładnie  podtablic", obliczamy funkcję dla "maksymalnie " i odejmujemy od niej wynik dla "maksymalnie ".
* **Matematycznie:** .

---

## Rekomendowane zadania LeetCode (Must-have)

Oto lista zadań, które systematyzują powyższe techniki i są najczęściej spotykane na rozmowach:

### 1. Sliding Window Maximum

**Zadanie:** [LeetCode 239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)

* **Technika:** Monotonic Queue ().
* **Dlaczego warto:** To fundamentalne zadanie uczące, jak zarządzać oknem, w którym stanem nie jest prosta suma, a relacja między elementami.

### 2. Longest Substring Without Repeating Characters

**Zadanie:** [LeetCode 3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

* **Technika:** Variable Window + Frequency Map/Array.
* **Dlaczego warto:** Absolutny klasyk. Uczy optymalnego przesuwania wskaźnika `begin` bezpośrednio za indeks powtórzonego znaku zamiast przesuwania go o jeden w pętli `while`.

### 3. Minimum Window Substring

**Zadanie:** [LeetCode 76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) (Poziom: Hard)

* **Technika:** Dwa wskaźniki + licznik dopasowań (Need vs Have).
* **Dlaczego warto:** To zadanie sprawdza biegłość w operowaniu stanem okna. Musisz znaleźć najkrótszy fragment tekstu , który zawiera wszystkie znaki z tekstu .

### 4. Permutation in String

**Zadanie:** [LeetCode 567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)

* **Technika:** Fixed Window + Frequency Array Comparison.
* **Dlaczego warto:** Zamiast porównywać tablice częstotliwości w każdym kroku (), uczysz się aktualizować zmienną `matches`, która mówi Ci, ile znaków ma już poprawną liczność.

### 5. Subarrays with K Different Integers

**Zadanie:** [LeetCode 992. Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) (Poziom: Hard)

* **Technika:** Trick "Exactly K".
* **Dlaczego warto:** To zadanie pokazuje, jak rozbić trudny problem na dwa prostsze problemy sliding window (At Most K).

---

## Co jeszcze warto przerobić?

1. **Sliding Window na strukturach nieciągłych:** Choć sliding window zazwyczaj kojarzy się z tablicami, warto zastanowić się, jak zaimplementować je na liście jednokierunkowej (wymaga to dodatkowej uwagi przy usuwaniu z `begin`, bo nie masz dostępu swobodnego).
2. **Prefix Sums vs Sliding Window:** Naucz się odróżniać, kiedy użyć okna, a kiedy sum prefiksowych.
* Jeśli tablica ma liczby ujemne, suma wewnątrz okna nie jest już monotoniczna (powiększenie okna może zmniejszyć sumę). Wtedy sliding window **nie zadziała** i musisz użyć `Prefix Sums` + `HashMap`.

3. **Optymalizacja czasu rzeczywistego:** Zastanów się, jak sliding window wpływa na algorytmy strumieniowe (np. obliczanie średniej kroczącej w systemach monitoringu).

