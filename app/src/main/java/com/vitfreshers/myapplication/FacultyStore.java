package com.vitfreshers.myapplication;

public class FacultyStore {

     String name;
     String venue;
     String id;
     String school;
     String email;
     String intercom;



    public FacultyStore(String name, String venue, String id,String school,String email,String intercom) {
        this.name = name;
        this.venue = venue;
        this.id = id;
        this.school=school;
        this.email=email;
        this.intercom=intercom;
    }

    // For Testing
    public FacultyStore() {
        this.name = " NAME";
        this.venue = "VENUE: AB1";
        this.id = "01234";
        this.school="SCSE";
        this.email="@gmail.com";
        this.intercom="2580";
    }
}
