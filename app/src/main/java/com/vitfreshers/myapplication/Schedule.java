package com.vitfreshers.myapplication;

public class Schedule {

    private String program_name;
    private String venue;
    private String timing;
    private String date;
    private String person;


    public Schedule(String program_name, String venue, String timing,String d,String p) {
        this.program_name = program_name;
        this.venue = venue;
        this.timing = timing;
        this.date=d;
        this.person=p;
    }

    // For Testing
    public Schedule() {
        this.program_name = "PROGRAM NAME";
        this.venue = "VENUE: AB1";
        this.timing = "TIMING: 0.1:00 PM";
        this.date="5 July 2019";
        this.person="Resource Person..";
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String d) {
        this.date = d;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String p) {
        this.person = p;
    }
}
