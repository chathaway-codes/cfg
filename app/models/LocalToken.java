package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.DateTime;

import play.db.ebean.Model;

@Entity
public class LocalToken extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public String uuid;

	public String email;

	public DateTime createdAt;

	public DateTime expireAt;

	public boolean isSignUp;

	public static final Finder<String, LocalToken> find = new Finder<String, LocalToken>(
			String.class, LocalToken.class);
}