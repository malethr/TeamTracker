import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
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

        //get: display team form
        get("/teams/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams",teams);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new team form
        post("/teams/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            String teamName = req.queryParams("team-name");
            Team team = new Team(teamName);
            teamDao.add(team);
            List<Team> teams = teamDao.getAll();
            model.put("teams",teams);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: display reset confirmation
        get("/teams/reset", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "reset.hbs");
        }, new HandlebarsTemplateEngine());

        //get: reset all data
        get("/teams/reset/success", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            memberDao.clearAllMembers();
            List<Team> teams = teamDao.getAll();
            List<Member> members = memberDao.getAll();
            model.put("teams",teams);
            model.put("members",members);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //get: display member form
        get("/members/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            List<Member> members = memberDao.getAll();
            model.put("teams",teams);
            model.put("members",members);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process member team form
        post("/members/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            String teamChoice= req.queryParams("team");
            int teamId = Integer.parseInt(teamChoice);
            Team foundTeam = teamDao.findById(teamId);
            String memberName = req.queryParams("member-name");
            Member newMember = new Member(memberName, foundTeam.getId());
            memberDao.add(newMember);
            List<Member> members = memberDao.getAll();
            List<Team> teams = teamDao.getAll();
            model.put("members", members);
            model.put("teams", teams);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: list all team listed
        get("/", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            List<Member> members = memberDao.getAll();
            model.put("teams",teams);
            model.put("members",members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: list all member listed per team
        get("/teams/:id", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            Team foundTeam = teamDao.findById(Integer.parseInt(req.params("id")));
            List<Member> members = teamDao.getAllMembersByTeam(foundTeam.getId());
            model.put("teams", foundTeam);
            model.put("members", members);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a team and its members
        get("/teams/:id/delete", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params("id"));
            List<Member> teamMembers =teamDao.getAllMembersByTeam(id);
            teamDao.deleteById(id);
            teamMembers.clear();
            List<Team> teams = teamDao.getAll();
            List<Member> members = memberDao.getAll();
            model.put("teams", teams);
            model.put("members", members);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: display team form
        get("/teams/:id/edit", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            Team editTeam = teamDao.findById(Integer.parseInt(req.params("id")));
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: update the team
        post("/teams/:id/edit", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            String teamName = req.queryParams("team-name");
            int id = Integer.parseInt(req.params("id"));
            teamDao.update(id,teamName);
            List<Team> teams = teamDao.getAll();
            List<Member> members = memberDao.getAll();
            model.put("teams", teams);
            model.put("members", members);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: display member form
        get("/teams/:teamid/members/:id/edit", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            Member editMember = memberDao.findById(Integer.parseInt(req.params("id")));
            Team memberTeam = teamDao.findById(editMember.getTeamId());
            model.put("editMember", editMember);
            model.put("memberTeam", memberTeam);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: update the member
        post("/teams/:teamid/members/:id/edit", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            String memberName = req.queryParams("member-name");
            int id = Integer.parseInt(req.params("id"));
            int teamId = Integer.parseInt(req.params("teamid"));
            memberDao.update(id, memberName, teamId);
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a member
        get("/teams/:teamid/members/:id/delete", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            memberDao.deleteById(Integer.parseInt(req.params("id")));
            List<Team> teams = teamDao.getAll();
            List<Member> members = memberDao.getAll();
            model.put("teams", teams);
            model.put("members", members);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
