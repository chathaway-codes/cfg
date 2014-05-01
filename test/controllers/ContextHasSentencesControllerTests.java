package controllers;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import models.ContextHasSentences;
import models.InitialData;
import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.FakeApplication;
import play.test.WithApplication;
import securesocial.core.java.SecureSocial;
import securesocial.core.Identity;
import service.UserService;
import static play.test.Helpers.*;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class ContextHasSentencesControllerTests extends WithApplication {
	play.Application app;
	@Before
	public void setUp() {
		FakeApplication fakeApp = fakeApplication(inMemoryDatabase());
		start(fakeApp);
		this.app = new play.Application((play.api.Application)fakeApp.getWrappedApplication());

		InitialData.UserData();
	}
	
	@Test
	public void testCanPurchaseSentence() {
		/*User user = User.find.byId(1L);
		
		ContextHasSentences chs = ContextHasSentences.find.byId(1L);
		
		UserService us = new UserService(this.app);
		Identity identity = us.findByEmailAndProvider("user@example.com", "userpass").get();
		
		FakeRequest request = new FakeRequest(PUT, routes.ContextHasSentencesController.put(1L));
		request = request.withHeader("Content-Type", "application/json").withJsonBody(chs.toJson());
		
		play.test.Helpers.callAction(controllers.routes.ref.ContextHasSentencesController.put(), request);*/
	}
}
