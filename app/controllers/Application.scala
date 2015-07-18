package controllers

import play.api.mvc.{Action, Controller}

object Application extends Controller {

  def index = Action {
    Ok("Welcome to Additum. Check API for specific routes");
  }
}