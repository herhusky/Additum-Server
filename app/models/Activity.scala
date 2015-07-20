package models

import play.api.libs.json._
import play.api.libs.ws.WSResponse

import scala.collection.immutable.HashMap
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

  private def addHeaders(headers: HashMap[String, Any]): List[Option[String Tuple2 String]] = {
    return headers map {
      case (k, v) => Some(k.toString, v.toString)
    } toList
  }
}