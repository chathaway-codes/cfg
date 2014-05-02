package models;

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
	public User user;

	@Required
	public Sentence sentence;

	@Required
	public Guess guess;

	@Required
	public Float amount;

	@Required
	public DateTime when;

	public Purchase(User user, Sentence sentence, Guess guess, Float amount) {
		this(user, sentence, guess, amount, DateTime.now());
	}

	public Purchase(User user, Sentence sentence, Guess guess, Float amount,
			DateTime when) {
		this.user = user;
		this.sentence = sentence;
		this.guess = guess;
		this.amount = amount;
		this.when = when;
	}

	public static final Finder<Long, Purchase> find = new Finder<Long, Purchase>(
			Long.class, Purchase.class);
}
