package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.mindrot.jbcrypt.BCrypt;

import scala.Some;
import securesocial.core.Identity;

@Entity
public class Guess extends Model {
	@Id
	public Long id;
	
	Context context;
	User owner;
	
	@javax.persistence.Column(length=240)
	String guess;
}
