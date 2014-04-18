package models;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Book extends Model {
	@Id
	@Constraints.Min(10)
	public Long id;
	
	@Constraints.Required
	@NotNull
	public String title;
	
	public String author;
	
	@Formats.DateTime(pattern="yyyy-MM-dd")
	public Date publicationDate;
	
	public Book() {
		this(null, null, null, null);
	}
	
	public Book(Long id, String title, String author, Date publicationDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
	}
	
	public static Finder<Long,Book> find = new Finder<Long,Book>(
		Long.class, Book.class
	);
}
