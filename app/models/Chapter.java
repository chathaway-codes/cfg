package models;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Chapter extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Constraints.Min(10)
	public Long id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Book book;
	
	public static Finder<Long,Chapter> find = new Finder<Long,Chapter>(
		Long.class, Chapter.class
	);
}
