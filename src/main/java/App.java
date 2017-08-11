import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

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
           return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
