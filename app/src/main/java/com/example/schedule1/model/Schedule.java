package com.example.schedule1.model;

import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

public class Schedule extends AppCompatActivity {

    private int id ;
    private String description;
    private int color;

    public Schedule (int id, String description, int color){
        this.id = id;
        this.description = description;
        this.color = color;
    }

    public Schedule (int id, String description){
        this.id = id;
        this.description = description;
        this.color = Color.BLACK;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }

    @Override
    public String toString(){
        return description;
    }



}// end class
