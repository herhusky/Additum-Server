package models

import play.api.libs.json.{JsObject, JsString, JsValue}
import play.api.libs.ws.WSResponse
import play.api.mvc.Headers

import scala.concurrent.Future

/**
 * Created by deep on 7/16/15.
 */
object Merchant extends ParseObject {
  val className: String = "Merchant"

  def createMerchant(name: JsValue,
                     business: JsValue,
                     website: JsValue,
                     charity: JsValue,
                     imageURL: JsValue): Future[WSResponse] = {
    return super.createObject(JsObject(Seq(
      "name" -> name,
      "business" -> business,
      "website" -> website,
      "charity" -> JsObject(Seq(
        "__type" -> JsString("Relation"),
        "className" -> JsString("Charity"),
        "objectId" -> charity
      )),
      "imageURL" -> imageURL
    )))
  }

  def getMerchant(id: String, headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObject(id)(addHeaders(headers), addQueryStrings(query))
  }

  def getMerchants(headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObjects(addHeaders(headers), addQueryStrings(query))
  }

  def updateMerchant(id: String,
                     name: JsValue,
                     business: JsValue,
                     website: JsValue,
                     charity: JsValue): Future[WSResponse] = {
    return super.updateObject(id, JsObject(Seq(
      "name" -> name,
      "business" -> business,
      "website" -> website,
      "charity" -> JsObject(Seq(
        "__type" -> JsString("Relation"),
        "className" -> JsString("Charity"),
        "objectId" -> charity
      ))
    )))
  }

  def addPaymentMethod(id: String,
                       abaRouting: JsValue,
                       bankAccount: JsValue,
                       accountType: JsValue,
                       bankName: JsValue,
                       accountName: JsValue): Future[WSResponse] = {
    val futureResponse: Future[WSResponse] = for {
      createdNewPaymentMethodResponse <- models.Payment.createPaymentInfo(abaRouting, bankAccount, accountType, bankName, accountName);
      relatedPaymentMethodToObject <- super.addRelation(id, "paymentInfo", "Payment", createdNewPaymentMethodResponse.json.\("objectId"))
    } yield relatedPaymentMethodToObject
    return futureResponse
  }
}
