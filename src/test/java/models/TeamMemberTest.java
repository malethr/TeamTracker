package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mariathomas on 8/11/17.
 */
public class TeamMemberTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() throws Exception {
        TeamMember teamMember = new TeamMember("Jane Doe");
        assertEquals(true, teamMember instanceof TeamMember);
    }

    @Test
    public void TeamMemberInstantiatesWithTeamMemberName_JaneDoe() throws Exception {
        TeamMember teamMember = new TeamMember("Jane Doe");
        assertEquals("Jane Doe",teamMember.getTeamMemberName());
    }

    @After
    public void tearDown() throws Exception {
        TeamMember.clearAllTeamMembers();
    }

    public TeamMember setUpNewTeamMember(){
        return new TeamMember("Jane Doe");
    }

    @Test
    public void AllTeamMembersCorrectlyReturned_2() {
        TeamMember teamMember = setUpNewTeamMember();
        TeamMember otherTeam = new TeamMember ("John Smith");
        assertEquals(2, TeamMember.getAllTeamMembers().size());
    }

    @Test
    public void AllTeamMembersContainsAllTeamMemberNames_true() {
        TeamMember teamMember = setUpNewTeamMember();
        TeamMember otherTeamMember = new TeamMember ("John Smith");
        assertTrue(TeamMember.getAllTeamMembers().contains(teamMember));
        assertTrue(TeamMember.getAllTeamMembers().contains(otherTeamMember));
    }

    @Test
    public void clearAllTeamMembers_checkIfClearsData_0() {
        TeamMember teamMember = setUpNewTeamMember();
        TeamMember otherTeamMember = new TeamMember ("John Smith");
        TeamMember.clearAllTeamMembers();
        assertEquals(0,TeamMember.getAllTeamMembers().size());
    }

    @Test
    public void getMemberId_teamMemberInstantiateWithAnID_1() {
        TeamMember teamMember = setUpNewTeamMember();
        TeamMember otherTeamMember = new TeamMember ("John Smith");
        assertEquals(1, teamMember.getMemberId());
        assertEquals(2, otherTeamMember.getMemberId());
    }

    @Test
    public void findByMemberId_getTeamMemberInstance_otherTeamMember(){
        TeamMember teamMember = setUpNewTeamMember();
        Team otherTeam = new Team ("John Smith");
        assertEquals(otherTeam, Team.findById(otherTeam.getId()));
    }

}