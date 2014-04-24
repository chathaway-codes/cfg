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
	Guess guess;
	@ManyToOne(cascade = CascadeType.ALL)
	User reviewer;
	@OneToOne
	Score score;
	
	public static final Finder<Long, Review> find 
		= new Finder<Long, Review>(Long.class, Review.class);
}
