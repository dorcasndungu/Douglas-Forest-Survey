import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //calls registering a ranger form
        get("/ranger/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        //for saving registered ranger
        post("/ranger", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName");
            String kwsPin = request.queryParams(" kwsPin");
            Sighting newRanger=new Sighting(rangerName,kwsPin);
            newRanger.save();
            response.redirect("/animal/new");
            return null;
        }, new HandlebarsTemplateEngine());

        //form for recording an animal
        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers",Sighting.all());//for ranger list
            return new ModelAndView(model, "animals-form.hbs");
        }, new HandlebarsTemplateEngine());

        //for saving recorded animal
        post("/animal", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            String age = request.queryParams("age");
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String dangerStatus = request.queryParams("dangerStatus");
            animal newAnimal = new animal(name, rangerId, age, location, health, dangerStatus);
            newAnimal.save();
            response.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers",Sighting.all());
            model.put("animals",animal.all());
            return new ModelAndView(model, "sighting-list.hbs");
        }, new HandlebarsTemplateEngine());





    }
}
