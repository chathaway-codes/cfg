package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.JsonNode;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.libs.Json;

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

	public Score(User user, Review review, Guess guess) {
		this(user, review, guess, 0.0, 0.0, 0.0);
	}

	public Score(User user, Review review, Guess guess, Double grammarScore,
			Double funScore, Double accuracyScore) {
		this.user = user;
		this.review = review;
		this.guess = guess;
		this.grammarScore = grammarScore;
		this.funScore = funScore;
		this.accuracyScore = accuracyScore;
	}

	private class JsonClass {
		private Score _this;

		JsonClass(Score _this) {
			this._this = _this;
		}

		public Long getId() {
			return _this.id;
		}

		public Double getGrammarScore() {
			return _this.grammarScore;
		}

		public Double getFunScore() {
			return _this.funScore;
		}

		public Double getAccuracyScore() {
			return _this.accuracyScore;
		}
	}

	public JsonNode toJson() {
		return Json.toJson(new JsonClass(this));
	}

	public Double averageScore() {
		return (this.grammarScore + this.funScore + this.accuracyScore) / 3;
	}

	public static final Finder<Long, Score> find = new Finder<Long, Score>(
			Long.class, Score.class);
}
