package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariathomas on 8/11/17.
 */
public class Team {

    private String teamName;
    private static ArrayList<Team> instances = new ArrayList<>();
    private int id;
    private List<TeamMember> teamMembers;

    public Team (String teamName){
        this.teamName = teamName;
        instances.add(this);
        this.id = instances.size();
        teamMembers=new ArrayList<TeamMember>();
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

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void addTeamMembers(TeamMember teamMemberName){
        teamMembers.add(teamMemberName);
    }
}
