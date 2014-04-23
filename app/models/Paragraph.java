package models;

import java.util.Set;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Paragraph extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Constraints.Min(10)
	public Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	public Chapter chapter;
	@OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL)
	public Set<Sentence> sentences;

	public static Finder<Long, Paragraph> find = new Finder<Long, Paragraph>(
			Long.class, Paragraph.class);
}
