package models

import play.api.libs.json._
import play.api.libs.ws.WSResponse

import scala.concurrent.Future

/**
 * Created by deep on 7/16/15.
 */
case class Activity(userID: String, storeID: String, amountDonated: String) extends ParseObject {
  val className = "Activity"

  def createActivity: Future[WSResponse] = {
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
    )), List(Some("limit" -> "100")))
  }
}