package models;

import java.util.Random;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Sentence extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Constraints.Min(10)
	public Long id;
	
	@NotNull
	public String content;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Paragraph paragraph;
	
	public static Finder<Long,Sentence> find = new Finder<Long,Sentence>(
		Long.class, Sentence.class
	);
	
	public static Sentence getRandomSentence() {
		Random random = new Random();
		return Sentence.find.byId(random.nextLong()%(Sentence.find.findRowCount()));
	}
}
