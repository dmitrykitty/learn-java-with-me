package com.dnikitin.collections.bigpractise.practise2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Film {
    private final int id;
    private final int year;
    private final String title;
    private Set<String> genres;
    private final int duration;
    private int rating;
    private List<Actor> actors;

    public Film(int id, int year, String title, Set<String> genres, int duration, int rating, List<Actor> actors) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.genres = new HashSet<>(genres);
        this.duration = duration;
        this.rating = rating;
        this.actors = new ArrayList<>(actors);
    }

    public void addActor(Actor actor) {
        if (!this.actors.contains(actor)) {
            this.actors.add(actor);
            actor.addFilm(this);
        }
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override

    public String toString() {
        // Zamiast drukować całe obiekty Actor, drukujemy tylko ich imiona
        String actorNames = actors.stream()
                .map(Actor::getName)
                .collect(Collectors.joining(", "));

        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", actors=[" + actorNames + "]" +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public int getDuration() {
        return duration;
    }

    public int getRating() {
        return rating;
    }

    public List<Actor> getActors() {
        return actors;
    }
}
