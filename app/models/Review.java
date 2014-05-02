package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.libs.Json;

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
	public UserZ reviewer;
	@OneToOne
	public Score score;

	public Review() {
		this(new UserZ(), new Guess(), null);
	}

	public Review(UserZ reviewer, Guess guess, Score score) {
		this.guess = guess;
		this.reviewer = reviewer;
		this.score = score;
	}

	private class JsonClass {
		private Review _this;

		JsonClass(Review _this) {
			this._this = _this;
		}

		public Long getId() {
			return _this.id;
		}

		public JsonNode getGuess() {
			if (_this.guess != null)
				return _this.guess.toJson();
			return null;
		}

		public JsonNode getScore() {
			return _this.score.toJson();
		}
	}

	public JsonNode toJson() {
		return Json.toJson(new JsonClass(this));
	}

	public String toString() {
		return "reviewer: " + reviewer.toString() + " guess: "
				+ guess.toString() + " score: " + score.toString();
	}

	public static final Finder<Long, Review> find = new Finder<Long, Review>(
			Long.class, Review.class);
}
