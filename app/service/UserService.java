package service;

import play.Application;
import play.Logger;
import scala.Option;
import securesocial.core.Identity;
import securesocial.core.IdentityId;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.Token;

import java.util.*;

public class UserService extends BaseUserService {

	public UserService(Application application) {
		super(application);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Identity doSave(Identity user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(Token token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Identity doFind(IdentityId identityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Token doFindToken(String tokenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identity doFindByEmailAndProvider(String email, String providerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doDeleteToken(String uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDeleteExpiredTokens() {
		// TODO Auto-generated method stub
		
	}

}
