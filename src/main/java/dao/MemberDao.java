package dao;

import models.Member;

import java.util.List;

/**
 * Created by mariathomas on 8/18/17.
 */
public interface MemberDao {
    void add(Member member);    // Create
    List<Member> getAll();      // Read
    Member findById(int id);    // Find member by id
    void update(int id, String name, int memberId); // Update
    void deleteById(int id);    // Delete
    void clearAllMembers();     // Delete all
}
