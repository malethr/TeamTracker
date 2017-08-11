package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariathomas on 8/11/17.
 */
public class TeamMember {

    private String teamMemberName;
    private int memberId;
    private static List<TeamMember> instances = new ArrayList<TeamMember>();


    public TeamMember (String teamMemberName){
        this.teamMemberName = teamMemberName;
        instances.add(this);
        this.memberId=instances.size();
    }

    public String getTeamMemberName() {
        return teamMemberName;
    }

    public int getMemberId() {
        return memberId;
    }

    public static List<TeamMember> getAllTeamMembers() {
        return instances;
    }

    public static void clearAllTeamMembers() {
        instances.clear();
    }

    public static TeamMember findMemberById(int memberId){
        return instances.get(memberId-1);
    }
}
