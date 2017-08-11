import models.Team;
import models.TeamMember;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by mariathomas on 8/11/17.
 */

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> teams = Team.getAll();
            model.put("teams",teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (req,res)->{
           Map<String, Object> model = new HashMap<>();
           String teamName = req.queryParams("team-name");
           Team team = new Team(teamName);
           model.put("team",team);
           return new ModelAndView(model,"team-success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/members/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "teamMembers-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/members/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            String memberName = req.queryParams("member-name");
            TeamMember newTeamMember = new TeamMember(memberName);
            model.put("teamMember",newTeamMember);
            return new ModelAndView(model,"teamMembers-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int idToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = Team.findById(idToFind);
            TeamMember teamMembers = TeamMember.findMemberById(idToFind);
            model.put("team", foundTeam);
            model.put("teamMember", teamMembers);
            return new ModelAndView(model,"team-detail.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
