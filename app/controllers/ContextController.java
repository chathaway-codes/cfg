package controllers;

import java.util.List;

import models.Context;
import models.Guess;
import models.Sentence;
import models.User;
import models.ContextHasSentences;
import actions.CorsComposition.Cors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import securesocial.core.Identity;
import securesocial.core.java.SecureSocial;

@Cors
public class ContextController extends Controller {
	@BodyParser.Of(BodyParser.Json.class)
	public static Result list() {
		return play.mvc.Results.TODO;
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result post() {
		return play.mvc.Results.TODO;
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
		Context context = new Context();

		context.guess = new Guess();
		context.guess.user = user;

		Sentence sentence = Sentence.getRandomSentence();

		List<Sentence> sentences = sentence.getContext();

		for (Sentence s : sentences) {
			ContextHasSentences thing;
			if (s.equals(sentence)) {
				thing = new ContextHasSentences(context, s, true);
			} else {
				thing = new ContextHasSentences(context, s, false);
			}
			context.sentences.add(thing);
			thing.save();
		}

		context.save();

		return ok(context.toJson());

	}
}
