package models;

import java.util.List;

import org.junit.*;

import com.avaje.ebean.Ebean;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import play.test.*;
import static play.test.Helpers.*;

public class SentenceTests extends WithApplication {

	FakeApplication app;

	@Before
	public void setUp() {
		app = fakeApplication(inMemoryDatabase());
		start(app);
		InitialData.BasicData1();
	}

	@After
	public void tearDown() {
		stop(app);
	}

	@Test
	public void testCanGetRandomSentence() {
		Sentence s = Sentence.getRandomSentence();

		assertNotEquals(s, null);
	}

	@Test
	public void testCanGetOtherNeabySentences() {
		Sentence sent = Sentence.find.byId(1L);
		List<Sentence> sentences = sent.getContext();
		assertTrue(1 <= sentences.size());
		boolean found_me = false;
		for (Sentence s : sentences) {
			assertEquals(s.paragraph.id, sent.paragraph.id);
			if (sent.id == s.id) {
				found_me = true;
			}
		}
		assertTrue(found_me);

		// And we shouldn't have ALL the sentences
		assertThat(sentences.size(), is(not(Sentence.find.findRowCount())));
	}

	@Test
	public void testCanDeleteSentence() {
		int initialCount = Sentence.find.findRowCount();
		Sentence.find.byId(1L).delete();

		assertEquals(initialCount - 1, Sentence.find.findRowCount());
	}

	@Test
	public void testDoesNotThrowDivisionByZero() {
		Ebean.delete(Sentence.find.findList());
		int a = Sentence.find.findRowCount();
		assertEquals(a, 0);
		Sentence.getRandomSentence();
	}

	@Test
	public void testToJsonDoesNotKillEverything() {
		Sentence sentence = Sentence.find.byId(1L);
		// If this works, test passes
		sentence.toJson();
	}
}
