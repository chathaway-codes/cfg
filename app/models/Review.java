package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Review extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	public Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	public Guess guess;
	@ManyToOne(cascade = CascadeType.ALL)
	public User reviewer;
	@OneToOne
	public Score score;
	
	public Review() {
		this(new User(), new Guess(), new Score());
	}
	
	public Review(User reviewer, Guess guess, Score score) {
		this.guess = guess;
		this.reviewer = reviewer;
		this.score = score;
	}

	public String toString() {
		return "reviewer: " + reviewer.toString() + " guess: "
				+ guess.toString() + " score: " + score.toString();
	}

	public static final Finder<Long, Review> find = new Finder<Long, Review>(
			Long.class, Review.class);
}
