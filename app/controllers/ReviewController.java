package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Guess;
import models.Review;
import models.Score;
import models.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import securesocial.core.Identity;
import securesocial.core.java.SecureSocial;

public class ReviewController extends Controller {
	@BodyParser.Of(BodyParser.Json.class)
	public static Result list() {
		return play.mvc.Results.TODO;
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result get(Long id) {
		return play.mvc.Results.TODO;
	}

	@SecureSocial.SecuredAction
	@BodyParser.Of(BodyParser.Json.class)
	public static Result post() {
		User user = User.findByIdentity((Identity) ctx().args
				.get(SecureSocial.USER_KEY));
		JsonNode json = request().body().asJson();
		Score score = Json.fromJson(json.get("score"), Score.class);
		Long guess_id = Json.fromJson(json.get("guess").get("id"), Long.class);
		Guess guess = Guess.find.byId(guess_id);

		Review review = new Review(user, guess, score);

		review.save();

		// Reward the user monies for playing
		// Don't need to lock the DB
		// 20 monies per character?
		user.monies += review.guess.guess.length() * 20;
		user.save();

		return ok(review.toJson());
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result put(Long id) {
		return play.mvc.Results.TODO;
	}

	@SecureSocial.SecuredAction
	@BodyParser.Of(BodyParser.Json.class)
	public static Result New() {
		User user = User.findByIdentity((Identity) ctx().args
				.get(SecureSocial.USER_KEY));
		// Pick a random guess
		Guess guess = Guess.getRandomGuess();

		Review review = new Review(user, guess, null);
		review.score = new Score(user, review, guess);

		return ok(review.toJson());
	}
}
