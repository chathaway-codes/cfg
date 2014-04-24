package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.mindrot.jbcrypt.BCrypt;

import play.db.ebean.Model;
import scala.Some;
import securesocial.core.Identity;

@Entity
public class Context extends Model {
	@ManyToMany
	List<Sentence> sentences;
	
	Guess guess;
}
