package models;

import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import play.test.*;
import static play.test.Helpers.*;

public class SentenceTests extends WithApplication {
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
		InitialData.BasicData1();
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
}
