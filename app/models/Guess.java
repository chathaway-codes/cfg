package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.libs.Json;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class Guess extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@OneToOne
	public Context context;
	@ManyToOne
	public UserZ user;

	@OneToMany(mappedBy = "guess", cascade = CascadeType.ALL)
	public List<Review> reviews;

	@javax.persistence.Column(length = 240)
	public String guess;

	public Guess() {
		reviews = new ArrayList<Review>();
	}

	private class JsonClass {
		private Guess _this;

		JsonClass(Guess _this) {
			this._this = _this;
		}

		public Long getId() {
			return _this.id;
		}

		public JsonNode getContext() {
			return _this.context.toJson();
		}

		public JsonNode getUser() {
			return _this.user.toJson();
		}

		public String getGuess() {
			return _this.guess;
		}
	}

	public JsonNode toJson() {
		return Json.toJson(new JsonClass(this));
	}

	private static Random random = new Random();

	public static Guess getRandomGuess() {
		int rowCount = Guess.find.findRowCount();
		if (rowCount == 0)
			return null;
		Long id = Math.abs(random.nextLong()) % rowCount + 1;
		return Guess.find.byId(id);
	}

	public static final Finder<Long, Guess> find = new Finder<Long, Guess>(
			Long.class, Guess.class);
}
