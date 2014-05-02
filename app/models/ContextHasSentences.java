package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.libs.Json;

@Entity
public class ContextHasSentences extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	public Long id;
	@ManyToOne
	public Context context;
	@OneToOne
	public Sentence sentence;
	public Boolean visible = false;
	public Float cost;

	public ContextHasSentences(Context context, Sentence sentence,
			Boolean visible) {
		this.context = context;
		this.sentence = sentence;
		this.visible = visible;
		this.cost = getCost();
	}

	private class JsonObject {
		private ContextHasSentences _this;

		JsonObject(ContextHasSentences _this) {
			this._this = _this;
		}

		public Long getId() {
			return _this.id;
		}

		public JsonNode getSentence() {
			return _this.sentence.toJson();
		}

		public Boolean getVisible() {
			return _this.visible;
		}

		public Double getCost() {
			// Cost is equal to 100/monies per word
			return _this.getCost();
		}
	}

	public Double getCost() {
		// Cost is equal to 100/monies per word
		if (this.cost == null)
			this.cost = sentence.content.split(" ").length * 100.0;
		return this.cost;
	}

	public JsonNode toJson() {
		return Json.toJson(new JsonObject(this));
	}

	public static final Finder<Long, ContextHasSentences> find = new Finder<Long, ContextHasSentences>(
			Long.class, ContextHasSentences.class);
}
