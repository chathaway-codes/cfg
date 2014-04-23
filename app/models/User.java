package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
public class User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Constraints.Min(10)
	public Long id;
	
	@NotNull
	public String username;
	
	@NotNull
	public String email;
	
	@NotNull
	public String password_hash;
	
	@NotNull
	public String password_salt;
	
	public static final Finder<Long, User> find 
		= new Finder<Long, User>(Long.class, User.class);

}