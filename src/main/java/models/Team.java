package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariathomas on 8/11/17.
 */
public class Team {

    private String teamName;
    private int id;
    private int teamMemberId;

    public Team (String teamName){
        this.teamName = teamName;
        this.teamMemberId = id;
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

    public static Team findById(int id){
        return instances.get(id-1);
    }

    public List<Member> getTeamMembers() {
        return teamMembers;
    }

    public void addTeamMembers(Member memberName){
        teamMembers.add(memberName);
    }
}
