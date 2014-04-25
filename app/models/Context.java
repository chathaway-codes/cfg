package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.mindrot.jbcrypt.BCrypt;

import play.db.ebean.Model;
import scala.Some;
import securesocial.core.Identity;

@Entity
public class Context extends Model {
	@Id
	public Long id;

	@ManyToMany
	public List<Sentence> sentences;

	@OneToOne
	public Guess guess;
}
