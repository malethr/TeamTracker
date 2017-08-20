import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import dao.TeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by mariathomas on 8/11/17.
 */

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/teamtracker.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "","");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);


        //get: list all team listed
        get("/", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: list all member listed per team
        get("/teams/:id/members", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int idToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = teamDao.findById(idToFind);
            List<Member> members = teamDao.getAllMembersByTeam(idToFind);
            model.put("team", foundTeam);
            model.put("members", members);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: display team form
        get("/teams/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: reset all data
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            memberDao.clearAllMembers();
            List<Team> teams = teamDao.getAll();
            List<Member> members = memberDao.getAll();
            model.put("teams",teams);
            model.put("members",members);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new team form
        post("/teams/new", (req,res)->{
           Map<String, Object> model = new HashMap<>();
           String teamName = req.queryParams("team-name");
           Team team = new Team(teamName);
           model.put("team",team);
           return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

//
//        get("/teams/members/new", (req,res)->{
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "teamMembers-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/teams/members/new", (req,res)->{
//            Map<String, Object> model = new HashMap<>();
//            String memberName = req.queryParams("member-name");
//            Member newMember = new Member(memberName);
//            model.put("teamMember", newMember);
//            return new ModelAndView(model,"teamMembers-success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/teams/:id", (req,res)->{
//            Map<String, Object> model = new HashMap<>();
//            int idToFind = Integer.parseInt(req.params("id"));
//            Team foundTeam = Team.findById(idToFind);
//            Member members = Member.findMemberById(idToFind);
//            model.put("team", foundTeam);
//            model.put("teamMember", members);
//            return new ModelAndView(model,"team-detail.hbs");
//        }, new HandlebarsTemplateEngine());
    }
}
