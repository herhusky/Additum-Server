package models

import play.api.libs.json.{JsObject, JsValue}
import play.api.libs.ws.WSResponse
import play.api.mvc.Headers

import scala.concurrent.Future

/**
 * Created by deep on 7/16/15.
 */
object Charity extends ParseObject {
  val className = "Charity"

  def createCharity(name: JsValue,
                    cause: JsValue,
                    totalDonations: JsValue,
                    abaRouting: JsValue,
                    bankAccount: JsValue,
                    accountType: JsValue,
                    bankName: JsValue,
                    accountName: JsValue): Future[WSResponse] = {
    return super.createObject(
      JsObject(Seq(
        "name" -> name,
        "cause" -> cause,
        "totalDonations" -> totalDonations,
        "abaRouting" -> abaRouting,
        "bankAccount" -> bankAccount,
        "accountType" -> accountType,
        "bankName" -> bankName,
        "accountName" -> accountName
      )))
  }

  def getCharity(id: String, headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObject(id)(addHeaders(headers), addQueryStrings(query))
  }

  def getCharities(headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObjects(addHeaders(headers), addQueryStrings(query))
  }

  def updateCharity(id: String,
                    name: JsValue,
                    cause: JsValue,
                    totalDonations: JsValue,
                    abaRouting: JsValue,
                    bankAccount: JsValue,
                    accountType: JsValue,
                    bankName: JsValue,
                    accountName: JsValue): Future[WSResponse] = {
    return super.updateObject(id,
      JsObject(Seq(
        "name" -> name,
        "cause" -> cause,
        "totalDonations" -> totalDonations,
        "abaRouting" -> abaRouting,
        "bankAccount" -> bankAccount,
        "accountType" -> accountType,
        "bankName" -> bankName,
        "accountName" -> accountName
      )))
  }
}
