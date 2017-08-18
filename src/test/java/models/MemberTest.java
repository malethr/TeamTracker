package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mariathomas on 8/11/17.
 */
public class MemberTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() throws Exception {
        Member member = new Member("Jane Doe");
        assertEquals(true, member instanceof Member);
    }

    @Test
    public void TeamMemberInstantiatesWithTeamMemberName_JaneDoe() throws Exception {
        Member member = new Member("Jane Doe");
        assertEquals("Jane Doe", member.getTeamMemberName());
    }

    @After
    public void tearDown() throws Exception {
        Member.clearAllMembers();
    }

    public Member setUpNewTeamMember(){
        return new Member("Jane Doe");
    }

    @Test
    public void AllTeamMembersCorrectlyReturned_2() {
        Member member = setUpNewTeamMember();
        Member otherTeam = new Member("John Smith");
        assertEquals(2, Member.getAllTeamMembers().size());
    }

    @Test
    public void AllTeamMembersContainsAllTeamMemberNames_true() {
        Member member = setUpNewTeamMember();
        Member otherMember = new Member("John Smith");
        assertTrue(Member.getAllTeamMembers().contains(member));
        assertTrue(Member.getAllTeamMembers().contains(otherMember));
    }

    @Test
    public void clearAllTeamMembers_checkIfClearsData_0() {
        Member member = setUpNewTeamMember();
        Member otherMember = new Member("John Smith");
        Member.clearAllTeamMembers();
        assertEquals(0, Member.getAllTeamMembers().size());
    }

    @Test
    public void getMemberId_teamMemberInstantiateWithAnID_1() {
        Member member = setUpNewTeamMember();
        Member otherMember = new Member("John Smith");
        assertEquals(1, member.getMemberId());
        assertEquals(2, otherMember.getMemberId());
    }

    @Test
    public void findByMemberId_getTeamMemberInstance_otherTeamMember(){
        Member member = setUpNewTeamMember();
        Team otherTeam = new Team ("John Smith");
        assertEquals(otherTeam, Team.findById(otherTeam.getId()));
    }

}