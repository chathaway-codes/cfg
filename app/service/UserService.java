package service;

import play.Application;
import play.Logger;
import scala.Option;
import scala.Some;
import securesocial.core.AuthenticationMethod;
import securesocial.core.Identity;
import securesocial.core.IdentityId;
import securesocial.core.PasswordInfo;
import securesocial.core.SocialUser;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.Token;

import java.util.*;

import org.joda.time.DateTime;

import models.LocalToken;
import models.User;

public class UserService extends BaseUserService {

	public UserService(Application application) {
		super(application);
	}

	@Override
	public Identity doSave(Identity user) {
		User found = User.findByIdentity(user);
		if (found == null) {
			User localUser = new User(user);
			localUser.save();
		} else {
			// Already saved
		}
		return user;
	}

	@Override
	public void doSave(Token token) {
		LocalToken localToken = new LocalToken();
		localToken.uuid = token.uuid;
		localToken.email = token.email;
		localToken.createdAt = token.creationTime;
		localToken.expireAt = token.expirationTime;
		localToken.isSignUp = token.isSignUp;
		localToken.save();
	}

	@Override
	public Identity doFind(IdentityId identityId) {
		if (Logger.isDebugEnabled()) {
			Logger.debug("find...");
			Logger.debug(String.format("id = %s", identityId.userId()));
		}
		User localUser = User.find.where()
				.eq("username", (identityId.userId())).findUnique();
		if (localUser == null)
			return null;
		SocialUser socialUser = new SocialUser(new IdentityId(
				localUser.username, localUser.provider), localUser.first_name,
				localUser.last_name, localUser.full_name,
				Option.apply(localUser.email), null, new AuthenticationMethod(
						"userPassword"), null, null,
				Some.apply(new PasswordInfo("bcrypt", localUser.password_hash,
						Option.apply(localUser.password_salt))));
		if (Logger.isDebugEnabled()) {
			Logger.debug(String.format("socialUser = %s", socialUser));
		}
		return socialUser;
	}

	@Override
	public Token doFindToken(String tokenId) {
		if (Logger.isDebugEnabled()) {
			Logger.debug("findToken...");
			Logger.debug(String.format("token = %s", tokenId));
		}
		LocalToken localToken = LocalToken.find.byId(tokenId);
		if (localToken == null)
			return null;
		Token result = new Token();
		result.uuid = localToken.uuid;
		result.creationTime = new DateTime(localToken.createdAt);
		result.email = localToken.email;
		result.expirationTime = new DateTime(localToken.expireAt);
		result.isSignUp = localToken.isSignUp;
		if (Logger.isDebugEnabled()) {
			Logger.debug(String.format("foundToken = %s", result));
		}
		return result;
	}

	@Override
	public Identity doFindByEmailAndProvider(String email, String providerId) {
		if (Logger.isDebugEnabled()) {
			Logger.debug("findByEmailAndProvider...");
			Logger.debug(String.format("email = %s", email));
			Logger.debug(String.format("providerId = %s", providerId));
		}
		List<User> list = User.find.where().eq("email", email)
				.eq("provider", providerId).findList();
		if (list.size() != 1)
			return null;
		User localUser = list.get(0);
		SocialUser socialUser = new SocialUser(new IdentityId(
				localUser.username, localUser.provider), null, null, null,
				Option.apply(localUser.email), null, new AuthenticationMethod(
						"userPassword"), null, null,
				Some.apply(new PasswordInfo("bcrypt", localUser.password_hash,
						Option.apply(localUser.password_salt))));
		if (Logger.isDebugEnabled()) {
			Logger.debug(String.format("socialUser = %s", socialUser));
		}
		return socialUser;
	}

	@Override
	public void doDeleteToken(String uuid) {
		if (Logger.isDebugEnabled()) {
			Logger.debug("deleteToken...");
			Logger.debug(String.format("uuid = %s", uuid));
		}
		LocalToken localToken = LocalToken.find.byId(uuid);
		if (localToken != null) {
			localToken.delete();
		}
	}

	@Override
	public void doDeleteExpiredTokens() {
		if (Logger.isDebugEnabled()) {
			Logger.debug("deleteExpiredTokens...");
		}
		List<LocalToken> list = LocalToken.find.where()
				.lt("expireAt", new DateTime().toString()).findList();
		for (LocalToken localToken : list) {
			localToken.delete();
		}

	}

}
