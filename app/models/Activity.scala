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

  def createActivity(userID: String, storeID: String, amountDonated: String): Future[WSResponse] = {
    return super.createObject(JsObject(Seq(
      "userID" -> JsObject(Seq(
        "__type" -> JsString("Pointer"),
        "className" -> JsString("_User"),
        "objectId" -> JsString(userID)
      )),
      "storeID" -> JsObject(Seq(
        "__type" -> JsString("Pointer"),
        "className" -> JsString("Store"),
        "objectId" -> JsString(storeID)
      )),
      "amountDonated" -> JsNumber(BigDecimal(amountDonated))
    )))
  }

  def getActivity(id: String, headers: Headers): Future[WSResponse] = {
    return super.retrieveObject(id)(addHeaders(headers))
  }

  def getActivities(headers: Headers): Future[WSResponse] = {
    return super.retrieveObjects(addHeaders(headers))
  }

  def updateActivity(id: String, userID: String, storeID: String, amountDonated: String, headers: Headers): Future[WSResponse] = {
    return super.updateObject(id, JsObject(Seq(
      "userID" -> JsObject(Seq(
        "__type" -> JsString("Pointer"),
        "className" -> JsString("_User"),
        "objectId" -> JsString(userID)
      )),
      "storeID" -> JsObject(Seq(
        "__type" -> JsString("Pointer"),
        "className" -> JsString("Store"),
        "objectId" -> JsString(storeID)
      )),
      "amountDonated" -> JsNumber(BigDecimal(amountDonated))
    )))
  }
}