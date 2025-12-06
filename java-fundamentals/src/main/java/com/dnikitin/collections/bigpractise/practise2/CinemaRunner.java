package com.dnikitin.collections.bigpractise.practise2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CinemaRunner {

    public static void main(String[] args) {

        // --- 1. Konfiguracja Danych ---
        // Aktorzy są tworzeni z pustymi listami filmów.
        // Relacje dodamy za pomocą nowych metod.
        Actor keanu = new Actor("Keanu Reeves", 58, new ArrayList<>());
        Actor carrie = new Actor("Carrie-Anne Moss", 55, new ArrayList<>());
        Actor tomH = new Actor("Tom Hanks", 66, new ArrayList<>());
        Actor robin = new Actor("Robin Wright", 56, new ArrayList<>());
        Actor john = new Actor("John Travolta", 68, new ArrayList<>());
        Actor samuel = new Actor("Samuel L. Jackson", 74, new ArrayList<>());

        // Filmy są tworzone z pustymi listami aktorów.
        Film matrix = new Film(
                1, 1999, "Matrix",
                Set.of("Sci-Fi", "Akcja"), 136, 9,
                new ArrayList<>()
        );

        Film pulpFiction = new Film(
                2, 1994, "Pulp Fiction",
                Set.of("Kryminał", "Dramat"), 154, 9,
                new ArrayList<>()
        );

        Film forrestGump = new Film(
                3, 1994, "Forrest Gump",
                Set.of("Komedia", "Dramat"), 142, 8,
                new ArrayList<>()
        );

        Film matrixReloaded = new Film(
                4, 2003, "Matrix Reloaded",
                Set.of("Sci-Fi", "Akcja"), 138, 7,
                new ArrayList<>()
        );

        // --- 2. Łączenie relacji (NOWY, POPRAWNY SPOSÓB) ---
        // Używamy metod 'addActor' / 'addFilm' do synchronizacji
        matrix.addActor(keanu);
        matrix.addActor(carrie);
        matrixReloaded.addActor(keanu);
        matrixReloaded.addActor(carrie);

        pulpFiction.addActor(john);
        pulpFiction.addActor(samuel);

        // Możemy też zrobić to z drugiej strony, wynik będzie ten sam:
        tomH.addFilm(forrestGump);
        robin.addFilm(forrestGump);


        // --- 3. Tworzenie Kina i dodawanie filmów ---
        Cinema cinema = new Cinema();
        cinema.addFilm(matrix);
        cinema.addFilm(pulpFiction);
        cinema.addFilm(forrestGump);
        cinema.addFilm(matrixReloaded);

        // --- 4. Testowanie poprawionej logiki (wiele filmów na rok) ---
        Film skazaniNaShawshank = new Film(
                5, 1994, "Skazani na Shawshank",
                Set.of("Dramat"), 142, 10,
                new ArrayList<>()
        );
        cinema.addFilm(skazaniNaShawshank);

        System.out.println("--- Testowanie filmów z 1994 (POPRAWIONE) ---");
        List<Film> filmy1994 = cinema.getFilmsByYear(1994);
        System.out.println("Liczba filmów z 1994: " + filmy1994.size()); // Powinno być 3!
        for (Film f : filmy1994) {
            System.out.println("  - " + f.getTitle());
        }

        // --- 5. Testowanie pozostałych metod ---
        System.out.println("\n--- Testowanie metod get... ---");

        // Test getFilmsByTitle
        List<Film> znalezionePoTytule = cinema.getFilmsByTitle("Matrix");
        System.out.println("Znaleziono po tytule 'Matrix': " + znalezionePoTytule.get(0).getTitle());

        // Test getFilmsByGenre
        List<Film> znalezionePoGatunku = cinema.getFilmsByGenre("Dramat");
        System.out.println("Znaleziono " + znalezionePoGatunku.size() + " filmy 'Dramat':");
        for (Film f : znalezionePoGatunku) {
            System.out.println("  - " + f.getTitle());
        }

        // Test getFilmsByActors
        List<Film> filmyKeanuICarrie = cinema.getFilmsByActors(List.of(keanu, carrie));
        System.out.println("Filmy z Keanu i Carrie: " + filmyKeanuICarrie.size()); // Powinno być 2
        for (Film f : filmyKeanuICarrie) {
            System.out.println("  - " + f.getTitle());
        }

        // Test getTop10Films
        System.out.println("\n--- Top 10 filmów (posortowane wg rankingu) ---");
        List<Film> top10 = cinema.getTop10Films();
        for (Film f : top10) {
            System.out.println("  - " + f.getTitle() + " (Rating: " + f.getRating() + ")");
        }

        // Test wydrukowania obiektu (po poprawce toString)
        System.out.println("\n--- Test toString() ---");
        System.out.println(matrix);
        System.out.println(keanu);
    }
}