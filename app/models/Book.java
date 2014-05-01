package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.libs.Json;

@Entity
public class Book extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Constraints.Min(10)
	public Long id;

	@Constraints.Required
	@NotNull
	public String title;

	public String author;

	@Formats.DateTime(pattern = "yyyy-MM-dd")
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

	private class JsonClass {
		private Book _this;

		JsonClass(Book _this) {
			this._this = _this;
		}

		public Long getId() {
			return _this.id;
		}

		public String getTitle() {
			return _this.title;
		}

		public String getAuthor() {
			return _this.author;
		}
	}

	public JsonNode toJson() {
		return Json.toJson(new JsonClass(this));
	}

	public static Finder<Long, Book> find = new Finder<Long, Book>(Long.class,
			Book.class);
}
