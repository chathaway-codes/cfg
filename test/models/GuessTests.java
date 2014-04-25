package models;

import org.junit.*;
import org.openqa.selenium.android.library.Logger;

import com.avaje.ebean.Ebean;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import play.test.*;

public class GuessTests extends WithApplication {
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
		InitialData.UserData();
	}

	@Test
	public void testCanGetGuessScore() {
		Guess guess = Guess.find.byId(1L);
		Review review = guess.reviews.get(0);
		
		Logger.getLogger().warning(review.toString());

		// assertThat(null, is(not(review.score)));
		assertNotNull(review.score);
	}
}
