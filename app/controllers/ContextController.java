package controllers;

import java.util.List;

import models.Guess;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class ContextController extends Controller {
	@BodyParser.Of(BodyParser.Json.class)
	public static Result list() {
		return play.mvc.Results.TODO;
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result post() {
		return play.mvc.Results.TODO;
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result put(Long id) {
		return play.mvc.Results.TODO;
	}
}
