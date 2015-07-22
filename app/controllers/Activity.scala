package controllers

import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by deep on 7/18/15.
 */
object Activity extends Controller {
  def createActivity = Action.async { implicit request =>
    models.Activity.createActivity(request.body.asJson.get.\("userID"),
      request.body.asJson.get.\("storeID"),
      request.body.asJson.get.\("amountDonated"))
      .map {
      response => Ok(response.json.toString)
    }
  }

  def getActivity(id: String) = Action.async { implicit request =>
    models.Activity.getActivity(id, request.headers, request.queryString).map {
      response => Ok(response.json.toString)
    }
  }

  def getActivities = Action.async { implicit request =>
    models.Activity.getActivities(request.headers, request.queryString).map {
      response => Ok(response.json.toString)
    }
  }

  def updateActivity(id: String) = Action.async { implicit request =>
    models.Activity.updateActivity(id, request.body.asJson.get.\("amountDonated"))
      .map {
      response => Ok(response.json.toString)
    }
  }
}
