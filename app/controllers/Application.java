package controllers;

import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;

import play.*;
import play.libs.Yaml;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}
	
	public static Result preflight(String path) {

	    return ok("");

	}
	
	public static Result loadData() {
		@SuppressWarnings("unchecked")
		Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
				.load("initial-data.yml");

		// Insert books first
		Ebean.save(all.get("books"));

		Ebean.save(all.get("chapters"));

		// Insert paragraphs
		Ebean.save(all.get("paragraphs"));

		// Insert sentences
		Ebean.save(all.get("sentences"));
		
		return ok("");
	}

}
