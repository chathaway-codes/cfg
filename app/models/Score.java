package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Score extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@ManyToOne
	public User user;
	@OneToOne
	public Review review;
	@ManyToOne
	public Guess guess;

	@Required
	public Double grammarScore;
	@Required
	public Double funScore;
	@Required
	public Double accuracyScore;

	public Double averageScore() {
		return (this.grammarScore + this.funScore + this.accuracyScore) / 3;
	}

	public static final Finder<Long, Score> find = new Finder<Long, Score>(
			Long.class, Score.class);
}