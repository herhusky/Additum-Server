package controllers

import models.Activity
import play.api.mvc.{Action, Controller}

/**
 * Created by deep on 7/18/15.
 */
object Activity extends Controller {
  def createActivity = Action.async { implicit request =>
    import scala.concurrent.ExecutionContext.Implicits.global

    val tmpActivity = new Activity(request.body.asJson.get.\("userID").toString,
      request.body.asJson.get.\("storeID").toString,
      request.body.asJson.get.\("amountDonated").toString)

    tmpActivity.createActivity map {
      response => Ok(response.json.toString)
    }
  }
}
