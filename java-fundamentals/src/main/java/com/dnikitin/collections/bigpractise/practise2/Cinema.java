package com.dnikitin.collections.bigpractise.practise2;

import java.util.*;

public class Cinema {

    private final Map<Integer, Film> films = new HashMap<>();;

    public void addFilm(Film film) {
        films.put(film.getId(), film);
    }

    public List<Film> getFilmsByYear(int year) {
        List<Film> result = new ArrayList<>();
        for (Film film : films.values()) {
            if (film.getYear() == year) {
                result.add(film);
            }
        }
        return result;
    }

    public List<Film> getFilmsByTitle(String title) {
        List<Film> result = new ArrayList<>();
        for (Map.Entry<Integer, Film> entry : films.entrySet()) {
            if (entry.getValue().getTitle().equals(title)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public List<Film> getFilmsByGenre(String genre) {
        List<Film> result = new ArrayList<>();
        for (Map.Entry<Integer, Film> entry : films.entrySet()) {
            if (entry.getValue().getGenres().contains(genre)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public List<Film> getTop10Films(){
        List<Film> result = new ArrayList<>(films.values());
        result.sort(Comparator.comparing(Film::getRating).reversed());
        return result.subList(0, Math.min(10, result.size()));
    }

    public List<Film> getFilmsByActors(List<Actor> actors){
        Set<Film> result = new HashSet<>();
        for (Film film : films.values()) {
            if(new HashSet<>(film.getActors()).containsAll(actors)){
                result.add(film);
            }
        }
        return new ArrayList<>(result);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "films=" + films +
                '}';
    }
}
