package models

import play.api.libs.json._
import play.api.libs.ws.WSResponse
import play.api.mvc.Headers

import scala.concurrent.Future

/**
 * Created by deep on 7/16/15.
 */
object Activity extends ParseObject {
  val className = "Activity"

  def createActivity(userID: JsValue, storeID: JsValue, amountDonated: JsValue): Future[WSResponse] = {
    return super.createObject(
      JsObject(Seq(
        "userID" -> JsObject(Seq(
          "__type" -> JsString("Pointer"),
          "className" -> JsString("_User"),
          "objectId" -> userID
        )),
        "storeID" -> JsObject(Seq(
          "__type" -> JsString("Pointer"),
          "className" -> JsString("Store"),
          "objectId" -> storeID
        )),
        "amountDonated" -> amountDonated
      )))
  }

  def getActivity(id: String, headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObject(id)(addHeaders(headers), addQueryStrings(query))
  }

  def getActivities(headers: Headers, query: Map[String, Seq[String]]): Future[WSResponse] = {
    return super.retrieveObjects(addHeaders(headers), addQueryStrings(query))
  }

  def updateActivity(id: String, amountDonated: JsValue): Future[WSResponse] = {
    return super.updateObject(id, JsObject(Seq(
      "amountDonated" -> amountDonated
    )))
  }
}