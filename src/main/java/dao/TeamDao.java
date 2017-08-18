package dao;

import models.Member;
import models.Team;

import java.util.List;

/**
 * Created by mariathomas on 8/18/17.
 */
public interface TeamDao {
    void add(Team team);  // Create
    List<Team> getAll();     // Read
    List<Member> getAllMembersByTeam(int teamId);
    Team findById(int id);   // Find team by id
    void update(int id, String name); // Update
    void deleteById(int id);    // Delete
    void clearAllTeams();     // Delete all
}
