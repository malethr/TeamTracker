package models;

import java.util.ArrayList;

/**
 * Created by mariathomas on 8/11/17.
 */
public class Team {

    private String teamName;
    private static ArrayList<Team> instances = new ArrayList<>();
    private int id;

    public Team (String teamName){
        this.teamName = teamName;
        instances.add(this);
        this.id = instances.size();
    }

    public String getTeamName() {
        return teamName;
    }

    public static ArrayList<Team> getAll() {
        return instances;
    }

    public static void clearAllTeams(){
        instances.clear();
    }

    public int getId() {
        return id;
    }




}
