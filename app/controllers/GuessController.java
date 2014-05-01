package controllers;

import java.util.List;

import models.Guess;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import securesocial.core.Identity;
import securesocial.core.java.*;
import play.mvc.BodyParser;

public class GuessController extends Controller {
	@BodyParser.Of(BodyParser.Json.class)
	public static Result list() {
		List<Guess> guesses = Guess.find.findList();
		JsonNode result = Json.toJson(guesses);

		return ok(result);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result get(Long id) {
		Guess guess = Guess.find.byId(id);

		if (guess == null) {
			return notFound();
		}

		JsonNode result = Json.toJson(guess);

		return ok(result);
	}

	@SecureSocial.SecuredAction
	@BodyParser.Of(BodyParser.Json.class)
	public static Result post() {
		JsonNode json = request().body().asJson();
		Guess guess = Json.fromJson(json, Guess.class);

		// The owner should be logged in user
		User user = User.findByIdentity((Identity) ctx().args
				.get(SecureSocial.USER_KEY));
		guess.user = user;

		guess.save();

		return ok(guess.toJson());
	}

	@SecureSocial.SecuredAction
	@BodyParser.Of(BodyParser.Json.class)
	public static Result put(Long id) {
		JsonNode json = request().body().asJson();
		Guess guess = Json.fromJson(json, Guess.class);
		guess.id = id;

		// We should only update instance where the previous owner was this user
		Guess original = Guess.find.byId(id);

		if (original == null) {
			return notFound();
		}

		User user = User.findByIdentity((Identity) ctx().args
				.get(SecureSocial.USER_KEY));
		if (!original.user.equals(user)) {
			return forbidden();
		}

		guess.update();

		return ok(Json.toJson(guess));
	}

	@SecureSocial.SecuredAction
	@BodyParser.Of(BodyParser.Json.class)
	public static Result delete(Long id) {
		Guess guess = Guess.find.byId(id);

		if (guess == null) {
			return notFound();
		}

		// We should only update instance where the previous owner was this user
		User user = User.findByIdentity((Identity) ctx().args
				.get(SecureSocial.USER_KEY));
		if (!guess.user.equals(user)) {
			return forbidden();
		}

		guess.delete();

		return ok();
	}
}
