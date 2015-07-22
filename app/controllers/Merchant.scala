package controllers

import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by deep on 7/22/15.
 */
object Merchant extends Controller {
  def createMerchant = Action.async { implicit request =>
    models.Merchant.createMerchant(
      request.body.asJson.get.\("name").toString,
      request.body.asJson.get.\("business").toString,
      request.body.asJson.get.\("website").toString,
      request.body.asJson.get.\("charity").toString,
      request.body.asJson.get.\("abaRouting").toString,
      request.body.asJson.get.\("bankAccount").toString,
      request.body.asJson.get.\("accountType").toString,
      request.body.asJson.get.\("bankName").toString,
      request.body.asJson.get.\("accountName").toString
    ).map {
      response => Ok(response.json.toString)
    }
  }

  def getMerchant(id: String) = Action.async { implicit request =>
    models.Merchant.getMerchant(id, request.headers, request.queryString)
      .map {
      response => Ok(response.json.toString)
    }
  }

  def getMerchants = Action.async { implicit request =>
    models.Merchant.getMerchants(request.headers, request.queryString)
      .map {
      response => Ok(response.json.toString)
    }
  }

  def updateMerchant(id: String) = Action.async { implicit request =>
    models.Merchant.updateMerchant(id,
      request.body.asJson.get.\("name").toString,
      request.body.asJson.get.\("business").toString,
      request.body.asJson.get.\("website").toString,
      request.body.asJson.get.\("charity").toString,
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
