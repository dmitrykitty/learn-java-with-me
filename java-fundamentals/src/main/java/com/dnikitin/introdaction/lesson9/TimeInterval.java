package com.dnikitin.introdaction.lesson9;

public class TimeInterval {
    private int hours;
    private int minutes;
    private int seconds;

    public TimeInterval(int hours, int minutes, int seconds){
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    public TimeInterval(int totalSeconds){
        this.hours = totalSeconds / 3600;
        this.minutes = (totalSeconds % 3600) / 60;
        this.seconds = totalSeconds % 60;
    }

    public int getTotalSeconds(){
        return hours * 3600 + minutes * 60 + seconds;
    }

    public void printTime(){
        System.out.println(hours + ":" + minutes + ":" + seconds);
    }

    public TimeInterval sum(TimeInterval ti1, TimeInterval ti2){
        return new TimeInterval(ti1.getTotalSeconds() + ti2.getTotalSeconds());
    }
}
