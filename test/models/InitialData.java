package models;

import java.util.List;
import java.util.Map;

import play.libs.Yaml;

import com.avaje.ebean.Ebean;

public class InitialData {

	public static void BasicData1() {
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
	}

	public static void UserData() {
		// And user data
		@SuppressWarnings("unchecked")
		List<Object> all = (List<Object>) Yaml.load("initial-user-data.yml");

		Ebean.save(all);
	}
}
