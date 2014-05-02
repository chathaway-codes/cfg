package models;

import javax.persistence.Column;
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
	public UserZ user;
	@OneToOne
	public Review review;
	@ManyToOne
	public Guess guess;

	@Required
	@Column(columnDefinition = "NUMERIC")
	public Float grammarScore;
	@Required
	@Column(columnDefinition = "NUMERIC")
	public Float funScore;
	@Required
	@Column(columnDefinition = "NUMERIC")
	public Float accuracyScore;

	public Score(UserZ user, Review review, Guess guess) {
		this(user, review, guess, 0.0f, 0.0f, 0.0f);
	}

	public Score(UserZ user, Review review, Guess guess, Float grammarScore,
			Float funScore, Float accuracyScore) {
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

		public Float getGrammarScore() {
			return _this.grammarScore;
		}

		public Float getFunScore() {
			return _this.funScore;
		}

		public Float getAccuracyScore() {
			return _this.accuracyScore;
		}
	}

	public JsonNode toJson() {
		return Json.toJson(new JsonClass(this));
	}

	public Float averageScore() {
		return (this.grammarScore + this.funScore + this.accuracyScore) / 3;
	}

	public static final Finder<Long, Score> find = new Finder<Long, Score>(
			Long.class, Score.class);
}
