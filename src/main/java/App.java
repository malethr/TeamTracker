import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

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
    }
}
