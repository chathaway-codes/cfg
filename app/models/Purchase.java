package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Purchase extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	@ManyToOne
	public UserZ user;

	@Required
	public Sentence sentence;

	@Required
	public Guess guess;

	@Required
	@Column(columnDefinition = "NUMERIC")
	public Float amount;

	@Required
	public DateTime when_i_hate_play;

	public Purchase(UserZ user, Sentence sentence, Guess guess, Float amount) {
		this(user, sentence, guess, amount, DateTime.now());
	}

	public Purchase(UserZ user, Sentence sentence, Guess guess, Float amount,
			DateTime when) {
		this.user = user;
		this.sentence = sentence;
		this.guess = guess;
		this.amount = amount;
		this.when_i_hate_play = when;
	}

	public static final Finder<Long, Purchase> find = new Finder<Long, Purchase>(
			Long.class, Purchase.class);
}
