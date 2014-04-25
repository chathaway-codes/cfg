package models;

import java.util.List;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	public User user;

	@OneToMany(mappedBy = "guess", cascade = CascadeType.ALL)
	public List<Review> reviews;

	@javax.persistence.Column(length = 240)
	public String guess;

	public static final Finder<Long, Guess> find = new Finder<Long, Guess>(
			Long.class, Guess.class);
}
