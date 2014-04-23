package models;

import java.util.ArrayList;
import java.util.List;
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
	
	public List<Sentence> getContext() {
		return new ArrayList<Sentence>(this.paragraph.sentences);
	}
	
	public String toString() {
		return this.content;
	}
	
	public static Finder<Long,Sentence> find = new Finder<Long,Sentence>(
		Long.class, Sentence.class
	);
	
	private static Random random = new Random();
	
	public static Sentence getRandomSentence() {
		Long id = Math.abs(random.nextLong())%(Sentence.find.findRowCount())+1;
		System.out.println(id);
		return Sentence.find.byId(id);
	}
	
	public static List<Sentence> getContext(Sentence s) {
		return s.getContext();
	}
}
