package models;

import java.util.ArrayList;
import java.util.List;

import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.JsonNode;

import scala.Some;
import securesocial.core.Identity;

@Entity
public class User extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public String email;

	public String username;

	public String first_name;

	public String last_name;

	public String full_name;

	public String provider;

	public String password_hash;

	public String password_salt;

	public Float monies;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Guess> guesses;

	@OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
	public List<Review> reviews;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Score> scores;

	@OneToMany
	public List<Purchase> purchases;

	public User() {
		this.email = "user@example.com";
		this.username = "user";
		this.provider = "userpass";
	}

	public User(String email, String username, String provider, String password) {
		this.email = email;
		this.username = username;
		this.provider = provider;
		this.password_hash = hashPassword(this, password);

		_User();
	}

	public User(Identity i) {
		this.username = i.identityId().userId();
		this.provider = i.identityId().providerId();
		this.first_name = i.firstName();
		this.last_name = i.lastName();
		this.full_name = i.fullName();
		this.monies = 0.0f;
		if (i.email() instanceof Some) {
			this.email = i.email().get();
		}
		if (i.passwordInfo() instanceof Some) {
			this.password_hash = i.passwordInfo().get().password();
			if (i.passwordInfo().get().salt() instanceof Some)
				this.password_salt = i.passwordInfo().get().salt().get();
		}

		_User();
	}

	private void _User() {
		this.guesses = new ArrayList<Guess>();
		this.scores = new ArrayList<Score>();
		this.purchases = new ArrayList<Purchase>();
	}

	public static User findByIdentity(Identity i) {
		System.out.println(i.identityId());
		User user = User.find.where()
				.eq("provider", i.identityId().providerId())
				.eq("username", i.identityId().userId()).findUnique();

		return user;
	}

	public static String hashPassword(User user, String password) {
		if (user.password_salt == null) {
			user.password_salt = BCrypt.gensalt();
		}
		return BCrypt.hashpw(password, user.password_salt);
	}

	private class JsonClass {
		private User _this;

		JsonClass(User _this) {
			this._this = _this;
		}

		public Long getId() {
			return _this.id;
		}

		public String getName() {
			if (_this.full_name != null) {
				return _this.full_name;
			}
			if (_this.email != null) {
				return _this.email;
			}
			return "Nameless";
		}

		public Float getScore() {
			Float total = 0.0f;
			for (Score score : _this.scores) {
				total += score.averageScore();
			}
			return total;
		}

		public Float getMonies() {
			if (_this.monies == null)
				return 0.0f;
			return _this.monies;
		}

		public int getGuessCount() {
			return _this.guesses.size();
		}

		public int getReviewCount() {
			return _this.reviews.size();
		}
	}

	public JsonNode toJson() {
		return Json.toJson(new JsonClass(this));
	}

	public static final Finder<Long, User> find = new Finder<Long, User>(
			Long.class, User.class);

}
