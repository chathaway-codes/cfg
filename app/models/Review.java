package models;

import play.db.ebean.Model;

public class Review extends Model {
	Guess guess;
	User reviewer;
	Double score;
}
