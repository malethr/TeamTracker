package dao;

import java.util.List;

/**
 * Created by mariathomas on 8/18/17.
 */
public interface MemberDao {
    void add(Member member);    // Create
    List<Member> getAll();      // Read
    Member findById(int id);    // Find client by id
    void update(int id, String name, String address, int phone, int stylistId); // Update
    void deleteById(int id);    // Delete
    void clearAllClients();     // Delete all
}
