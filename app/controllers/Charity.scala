package controllers

import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by deep on 7/22/15.
 */
object Charity extends Controller {
  def createCharity = Action.async { implicit request =>
    models.Charity.createCharity(
      request.body.asJson.get.\("name").toString,
      request.body.asJson.get.\("cause").toString,
      request.body.asJson.get.\("totalDonations").toString,
      request.body.asJson.get.\("abaRouting").toString,
      request.body.asJson.get.\("bankAccount").toString,
      request.body.asJson.get.\("accountType").toString,
      request.body.asJson.get.\("bankName").toString,
      request.body.asJson.get.\("accountName").toString)
      .map {
      response => Ok(response.json.toString)
    }
  }

  def getCharity(id: String) = Action.async { implicit request =>
    models.Charity.getCharity(id, request.headers, request.queryString)
      .map {
      response => Ok(response.json.toString)
    }
  }

  def getCharities = Action.async { implicit request =>
    models.Charity.getCharities(request.headers, request.queryString)
      .map {
      response => Ok(response.json.toString)
    }
  }

  def updateCharity(id: String) = Action.async { implicit request =>
    models.Charity.updateCharity(id,
      request.body.asJson.get.\("name").toString,
      request.body.asJson.get.\("cause").toString,
      request.body.asJson.get.\("totalDonations").toString,
      request.body.asJson.get.\("abaRouting").toString,
      request.body.asJson.get.\("bankAccount").toString,
      request.body.asJson.get.\("accountType").toString,
      request.body.asJson.get.\("bankName").toString,
      request.body.asJson.get.\("accountName").toString)
      .map {
      response => Ok(response.json.toString)
    }
  }
}
