package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mariathomas on 8/18/17.
 */
public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingMemberSetsId() throws Exception {
        Member member = setUpNewMember();
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId,member.getId());
    }

    @Test
    public void existingMembersCanBeFoundById() throws Exception {
        Member member = setUpNewMember();
        memberDao.add(member); //add to dao (takes care of saving)
        Member foundMember = memberDao.findById(member.getId()); //retrieve
        assertEquals(member, foundMember); //should be the same
    }

    @Test
    public void addedMembersAreReturnedFromgetAll() {
        Member member = setUpNewMember();
        Member otherMember = new Member( "Eric",1);
        memberDao.add(member);
        memberDao.add(otherMember);
        assertEquals(2, memberDao.getAll().size());
    }

    @Test
    public void noMembersReturnsEmptyList() throws Exception {
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void updateChangesMemberContent() throws Exception {
        Member member = setUpNewMember();
        memberDao.add(member);
        memberDao.update(member.getId(), "Eric",1);
        Member updatedMember = memberDao.findById(member.getId());
        assertNotEquals(member, updatedMember);
    }

    @Test
    public void deleteById_DeletesCorrectMember_0() throws Exception {
        Member member = setUpNewMember();
        memberDao.add(member);
        memberDao.deleteById(member.getId());
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void clearAll_DeletesAll_0() {
        Member member = setUpNewMember();
        Member otherMember = new Member( "Eric",1);
        memberDao.add(member);
        memberDao.add(otherMember);
        memberDao.clearAllMembers();
        assertEquals(0, memberDao.getAll().size());
    }

    public Member setUpNewMember(){
        return new Member("Kevin",1);
    }
}