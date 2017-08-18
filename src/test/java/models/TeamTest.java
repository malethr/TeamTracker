package models;

import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mariathomas on 8/11/17.
 */
public class TeamTest {

    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() throws Exception {
        Team team = new Team("DreamTeam");
        assertEquals(true, team instanceof Team);
    }

    @Test
    public void TeamInstantiatesWithTeamName_DreamTeam() throws Exception {
        Team team = new Team("DreamTeam");
        assertEquals("DreamTeam",team.getTeamName());
    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }

    public Team setupNewTeam(){
        return new Team("DreamTeam");
    }

    @Test
    public void AllTeamCorrectlyReturned_2() {
        Team team = setupNewTeam();
        Team otherTeam = new Team ("AwesomeTeam");
        assertEquals(2, Team.getAll().size());
    }

    @Test
    public void AllTeamsContainsAllTeams_true() {
        Team team = setupNewTeam();
        Team otherTeam = new Team ("AwesomeTeam");
        assertTrue(Team.getAll().contains(team));
        assertTrue(Team.getAll().contains(otherTeam));
    }

    @Test
    public void clearAllTeams_checkIfClearsData_0() {
        Team team = setupNewTeam();
        Team otherTeam = new Team ("AwesomeTeam");
        Team.clearAllTeams();
        assertEquals(0,Team.getAll().size());
    }

    @Test
    public void getId_teamsInstantiateWithAnID_1() {
        Team team = setupNewTeam();
        Team otherTeam = new Team ("AwesomeTeam");
        assertEquals(1, team.getId());
        assertEquals(2, otherTeam.getId());
    }

    @Test
    public void findById_getTeamInstance_otherTeam(){
        Team team = setupNewTeam();
        Team otherTeam = new Team ("AwesomeTeam");
        assertEquals(otherTeam, Team.findById(otherTeam.getId()));
    }

    @Test
    public void addMember_memberAddedToTeam_true() {
        Team team = setupNewTeam();
        Member member = new Member("Jane Doe");
        team.addTeamMembers(member);
        Team otherTeam = new Team ("AwesomeTeam");
        Member otherMember = new Member("John Smith");
        otherTeam.addTeamMembers(otherMember);
        assertEquals(true, team.getTeamMembers().contains(member));
        assertEquals(true, otherTeam.getTeamMembers().contains(otherMember));
    }
}