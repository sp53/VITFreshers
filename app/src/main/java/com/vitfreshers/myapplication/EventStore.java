package com.vitfreshers.myapplication;

public class EventStore {

    String name;
    String description;
    String venue;
    String time;
    String date;
    String coord;
    String contact;



    public EventStore(String name, String description, String venue,String time,String date,String coord,String contact) {
        this.name = name;
        this.description=description;
        this.venue = venue;
        this.time=time;
        this.date=date;
        this.coord=coord;
        this.contact=contact;
    }

    public EventStore() {
        this.name = " NAME";
        this.description="Description";
        this.venue="Venue";
        this.time="10:00 AM";
        this.date="15th August 2019";
        this.coord="Faculty Coordinator";
        this.contact="9876543210";
    }
}
