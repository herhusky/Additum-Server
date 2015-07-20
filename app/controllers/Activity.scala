package controllers

import play.api.mvc.{Action, Controller}

/**
 * Created by deep on 7/18/15.
 */
object Activity extends Controller {
  def createActivity = Action.async { implicit request =>
    import scala.concurrent.ExecutionContext.Implicits.global

    models.Activity.createActivity(request.body.asJson.get.\("userID").toString,
      request.body.asJson.get.\("storeID").toString,
      request.body.asJson.get.\("amountDonated").toString)
      .map {
      response => Ok(response.json.toString)
    }
  }
}
