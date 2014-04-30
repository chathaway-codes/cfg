package controllers;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import models.InitialData;
import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.WithApplication;

public class ContextHasSentencesControllerTests extends WithApplication {
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));

		InitialData.UserData();
	}
	
	@Test
	public void testCanPurchaseSentence() {
		User user = User.find.byId(1L);
	}
}
