package controllers;

import java.util.List;

import models.Context;
import models.ContextHasSentences;
import models.Sentence;
import models.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import securesocial.core.Identity;
import securesocial.core.java.SecureSocial;

public class UserController extends Controller {
	@SecureSocial.SecuredAction
	@BodyParser.Of(BodyParser.Json.class)
	public static Result get() {
		User user = User.findByIdentity((Identity) ctx().args
				.get(SecureSocial.USER_KEY));

		return ok(user.toJson());

	}
}
