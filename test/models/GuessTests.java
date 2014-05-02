package models;

import org.junit.*;
import org.openqa.selenium.android.library.Logger;

import com.avaje.ebean.Ebean;
import static org.junit.Assert.*;
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

		assertNotNull(review);
		assertNotNull(review.score);
		assertNotNull(review.score.grammarScore);
		assertEquals(new Double(7.0), review.score.grammarScore);
	}

	@Test
	public void testCanGetUserGuesses() {
		UserZ user = UserZ.find.byId(1L);

		assertNotNull(user.guesses);
		assertNotNull(user.guesses.get(0));
	}

	@Test
	public void testCanMakeGuess() {
		int originalCount = Guess.find.findRowCount();
		UserZ user = UserZ.find.byId(1L);

		user.guesses.add(new Guess());
		user.save();

		assertEquals(Guess.find.findRowCount(), originalCount + 1);
	}

	@Test
	public void testCanMakeReview() {
		int originalCount = Review.find.findRowCount();
		UserZ user = UserZ.find.byId(2L);

		Guess guess = Guess.find.byId(1L);

		Review review = new Review(user, guess, new Score(user, null, guess,
				10.0f, 10.0f, 10.0f));
		review.save();

		assertEquals(Review.find.findRowCount(), originalCount + 1);
	}
}
