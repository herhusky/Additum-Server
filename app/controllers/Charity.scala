package controllers

import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by deep on 7/22/15.
 */
object Charity extends Controller {
  def createCharity = Action.async { implicit request =>
    models.Charity.createCharity(
      request.body.asJson.get.\("name"),
      request.body.asJson.get.\("cause"),
      request.body.asJson.get.\("totalDonations"))
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
      request.body.asJson.get.\("name"),
      request.body.asJson.get.\("cause"),
      request.body.asJson.get.\("totalDonations"))
      .map {
      response => Ok(response.json.toString)
    }
  }

  def addPaymentMethod(id: String) = Action.async { implicit request =>
    models.Charity.addPaymentMethod(id,
      request.body.asJson.get.\("abaRouting"),
      request.body.asJson.get.\("bankAccount"),
      request.body.asJson.get.\("accountType"),
      request.body.asJson.get.\("bankName"),
      request.body.asJson.get.\("accountName")
    ).map {
      response => Ok(response.json.toString)
    }
  }
}
