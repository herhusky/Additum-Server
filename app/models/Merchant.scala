package models

import play.api.libs.json.{JsNumber, JsObject, JsString}
import play.api.libs.ws.WSResponse
import play.api.mvc.Headers

import scala.concurrent.Future

/**
 * Created by deep on 7/16/15.
 */
object Merchant extends ParseObject {
  val className: String = "Merchant"

  def createMerchant(name: String,
                     business: String,
                     website: String,
                     charity: String,
                     abaRouting: String,
                     bankAccount: String,
                     accountType: String,
                     bankName: String,
                     accountName: String): Future[WSResponse] = {
    return super.createObject(JsObject(Seq(
      "name" -> JsString(name),
      "business" -> JsString(business),
      "website" -> JsString(website),
      "charity" -> JsObject(Seq(
        "__type" -> JsString("Relation"),
        "className" -> JsString("Charity"),
        "objectId" -> JsString(charity)
      )),
      "abaRouting" -> JsNumber(BigDecimal(abaRouting)),
      "bankAccount" -> JsString(bankAccount),
      "accountName" -> JsString(accountName),
      "accountType" -> JsString(accountName),
      "bankName" -> JsString(bankName),
      "accountName" -> JsString(accountName)
    )))
  }

  def getMerchant(id: String, headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObject(id)(addHeaders(headers), addQueryStrings(query))
  }

  def getMerchants(headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObjects(addHeaders(headers), addQueryStrings(query))
  }

  def updateMerchant(id: String,
                     name: String,
                     business: String,
                     website: String,
                     charity: String,
                     abaRouting: String,
                     bankAccount: String,
                     accountType: String,
                     bankName: String,
                     accountName: String): Future[WSResponse] = {
    return super.updateObject(id, JsObject(Seq(
      "name" -> JsString(name),
      "business" -> JsString(business),
      "website" -> JsString(website),
      "charity" -> JsObject(Seq(
        "__type" -> JsString("Relation"),
        "className" -> JsString("Charity"),
        "objectId" -> JsString(charity)
      )),
      "abaRouting" -> JsNumber(BigDecimal(abaRouting)),
      "bankAccount" -> JsString(bankAccount),
      "accountName" -> JsString(accountName),
      "accountType" -> JsString(accountName),
      "bankName" -> JsString(bankName),
      "accountName" -> JsString(accountName)
    )))
  }
}
