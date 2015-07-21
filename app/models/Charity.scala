package models

import play.api.libs.json.{JsNumber, JsObject, JsString}
import play.api.libs.ws.WSResponse
import play.api.mvc.Headers

import scala.concurrent.Future

/**
 * Created by deep on 7/16/15.
 */
object Charity extends ParseObject {
  val className = "Charity"

  def createCharity(name: String,
                    cause: String,
                    totalDonations: String,
                    abaRouting: String,
                    bankAccount: String,
                    accountType: String,
                    bankName: String,
                    accountName: String): Future[WSResponse] = {
    return super.createObject(
      JsObject(Seq(
        "name" -> JsString(name),
        "cause" -> JsString(cause),
        "totalDonations" -> JsNumber(BigDecimal(totalDonations)),
        "abaRouting" -> JsNumber(BigDecimal(abaRouting)),
        "bankAccount" -> JsString(bankAccount),
        "accountType" -> JsString(accountType),
        "bankName" -> JsString(bankName),
        "accountName" -> JsString(accountName)
      )))
  }

  def getCharity(id: String, headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObject(id)(addHeaders(headers), addQueryStrings(query))
  }

  def getCharities(headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObjects(addHeaders(headers), addQueryStrings(query))
  }

  def updateCharity(id: String,
                    name: String,
                    cause: String,
                    totalDonations: String,
                    abaRouting: String,
                    bankAccount: String,
                    accountType: String,
                    bankName: String,
                    accountName: String): Future[WSResponse] = {
    return super.updateObject(id,
      JsObject(Seq(
        "name" -> JsString(name),
        "cause" -> JsString(cause),
        "totalDonations" -> JsNumber(BigDecimal(totalDonations)),
        "abaRouting" -> JsNumber(BigDecimal(abaRouting)),
        "bankAccount" -> JsString(bankAccount),
        "accountType" -> JsString(accountType),
        "bankName" -> JsString(bankName),
        "accountName" -> JsString(accountName)
      )))
  }
}
