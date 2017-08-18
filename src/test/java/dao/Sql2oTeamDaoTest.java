package dao;

import models.Member;
import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mariathomas on 8/18/17.
 */
public class Sql2oTeamDaoTest {

    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Team team = setUpNewTeam();
        int originalTeamId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @Test
    public void existingTeamsCanBeFoundById() throws Exception {
        Team team = setUpNewTeam();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }

    @Test
    public void addedTeamsAreReturnedFromgetAll() throws Exception {
        Team team = setUpNewTeam();
        teamDao.add(team);
        assertEquals(1, teamDao.getAll().size());
    }

    @Test
    public void noTeamsReturnsEmptyList() throws Exception {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void updateChangesTeamContent() throws Exception {
        String initialName = "AlphaTeam";
        Team team = new Team (initialName);
        teamDao.add(team);

        teamDao.update(team.getId(),"BetaTeam");
        Team updatedTeam = teamDao.findById(team.getId());
        assertNotEquals(initialName, updatedTeam.getName());
    }

    @Test
    public void deleteById_DeletesCorrectTeam_0() throws Exception {
        Team team = setUpNewTeam();
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void clearAll_DeletesAll_0() {
        Team team = setUpNewTeam();
        Team otherTeam = new Team("otherTeam");
        teamDao.add(team);
        teamDao.add(otherTeam);
        teamDao.clearAllTeams();
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void getAllMembersByTeamReturnsMembersCorrectly() throws Exception {
        Team team = setUpNewTeam();
        int teamId = team.getId();
        Member memberOne = new Member("Kevin", teamId);
        Member memberTwo = new Member("Eric", teamId);
        Member memberThree = new Member("Jane", teamId);
        memberDao.add(memberOne);
        memberDao.add(memberTwo);
        assertTrue(teamDao.getAllMembersByTeam(teamId).size() == 2);
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(memberOne));
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(memberTwo));
        assertFalse(teamDao.getAllMembersByTeam(teamId).contains(memberThree));
    }

    public Team setUpNewTeam(){
        return new Team("Awesome");
    }
}