package models;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mariathomas on 8/11/17.
 */
public class TeamTest {

    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() throws Exception {
        Team team = new Team("Team 1: AwesomeTeam");
        assertEquals(true, team instanceof Team);
    }
}