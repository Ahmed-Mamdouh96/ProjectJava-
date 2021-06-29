/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectfinalprep;

/**
 *
 * @author lenovo
 */
public class Wazzaf_Data {
    
    private String title;
    private String company;
    private String location;

    public Wazzaf_Data(String title, String company, String location) {
        this.title = title;
        this.company = company;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
    public String toString() {
        return "{" +
                "title=" + title +
                ", company=" + company +
                ", location='" + location + '\'' +
               
                '}'+"\n";
    }
}
    

