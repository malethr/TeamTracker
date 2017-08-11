package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
    }

}