package collections.bigpractise.practise2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Actor {
    final private String name;
    private int age;
    private List<Film> films;


    public Actor(String name, int age, List<Film> films) {
        this.name = name;
        this.age = age;
        this.films = new ArrayList<>(films);;
    }

    @Override
    public String toString() {
        // Zamiast drukować całe obiekty Film, drukujemy tylko ich tytuły
        String filmTitles = films.stream()
                .map(Film::getTitle)
                .collect(Collectors.joining(", "));

        return "Actor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", films=[" + filmTitles + "]" +
                '}';
    }

    public void addFilm(Film film) {
        if (!this.films.contains(film)) {
            this.films.add(film);
            film.addActor(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Film> getFilms() {
        return films;
    }
}
