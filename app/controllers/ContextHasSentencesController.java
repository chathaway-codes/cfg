package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;

import models.ContextHasSentences;
import models.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import securesocial.core.Identity;
import securesocial.core.java.SecureSocial;

public class ContextHasSentencesController extends Controller {
	@SecureSocial.SecuredAction
	@BodyParser.Of(BodyParser.Json.class)
	public static Result put(Long id) {

		User user = User.findByIdentity((Identity) ctx().args
				.get(SecureSocial.USER_KEY));
		
		ContextHasSentences chs = ContextHasSentences.find.byId(id);
		
		// Buy the sentence
		if(chs.visible) {
			return ok(chs.toJson());
		} else if(user.monies > chs.getCost()) {
			// Lock the database to prevent cheating
			Transaction transaction = Ebean.beginTransaction();
			
			user.monies -= chs.getCost();
			chs.visible = true;
			
			user.save();
			chs.save();
			transaction.commit();
		} else {
			return forbidden();
		}
		
		return play.mvc.Results.TODO;
	}
}
