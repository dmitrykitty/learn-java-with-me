```java
private void backtrack(StanGlobalny, StanLokalny) {
    // 1. Warunek stopu (Base Case)
    if (czyMamyWynik) {
        wynikGlobalny.add(new ArrayList<>(stanLokalny));
        return;
    }

    // 2. Iteracja po możliwościach (Choice Space)
    for (opcja : dostepneOpcje) {
        if (czyOpcjaJestPrawidlowa) {
            
            // 3. Wybór (Make Choice)
            stanLokalny.add(opcja);
            zaktualizujPomocniczeStruktury();

            // 4. Rekurencja (Explore)
            backtrack(wynikGlobalny, stanLokalny);

            // 5. Cofnięcie (Undo Choice) - KLUCZOWY MOMENT
            zdejmijZPomocniczychStruktur();
            stanLokalny.remove(stanLokalny.size() - 1);
        }
    }
}
```