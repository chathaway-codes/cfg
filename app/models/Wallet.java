package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class Wallet extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public Double score;
	public Double monies;
	@OneToOne
	public User user;

	public Wallet(Double score, Double monies, User user) {
		this.score = score;
		this.monies = monies;
		this.user = user;
	}

	public Wallet() {
		this.score = 0.0;
		this.monies = 0.0;
	}

	public static final Finder<Long, Wallet> find = new Finder<Long, Wallet>(
			Long.class, Wallet.class);
}
