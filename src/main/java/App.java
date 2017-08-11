import models.Team;
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


        get("/team/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/team/new", (req,res)->{
           Map<String, Object> model = new HashMap<>();
           String teamName = req.queryParams("team-name");
           Team newTeam = new Team(teamName);
           model.put("team",newTeam);
           return new ModelAndView(model,"team-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> teams = Team.getAll();
            model.put("teams",teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//        get("/team/member/new", (req,res)->{
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "teamMembers-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/team/member/new", (req,res)->{
//            Map<String, Object> model = new HashMap<>();
//            String memberName = req.queryParams("member-name");
//            TeamMember newTeamMember = new TeamMember(memberName);
//            model.put("team",newTeamMember);
//            return new ModelAndView(model,"teamMembers-success.hbs");
//        }, new HandlebarsTemplateEngine());
    }
}
