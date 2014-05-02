package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;
import play.libs.Json;
import scala.Some;
import securesocial.core.Identity;

@Entity
public class Context extends Model {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	@ManyToMany
	public List<ContextHasSentences> sentences;

	// This is mostly for caching purposes
	@ManyToOne
	public Book book;

	@OneToOne
	public Guess guess;

	private class JsonClass {
		private Context _this;

		JsonClass(Context context) {
			this._this = context;
		}

		public Long getId() {
			return _this.id;
		}

		public List<JsonNode> getSentences() {
			List<JsonNode> data = new ArrayList<JsonNode>();
			for (ContextHasSentences sentence : _this.sentences) {
				data.add(sentence.toJson());
			}
			return data;
		}

		public JsonNode getBook() {
			if (_this.sentences.size() > 0 && _this.book == null) {
				_this.book = sentences.get(0).sentence.paragraph.chapter.book;
			}
			if (_this.book != null) {
				return _this.book.toJson();
			}
			return null;
		}
	}

	public JsonNode toJson() {
		return Json.toJson(new JsonClass(this));
	}
}
