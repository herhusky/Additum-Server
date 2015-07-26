package models

import play.api.libs.json.{JsObject, JsValue}
import play.api.libs.ws.WSResponse

import scala.concurrent.Future

/**
 * Created by deep on 7/26/15.
 */
object Payment extends ParseObject {
  val className: String = "Payment"

  def createPaymentInfo(abaRouting: JsValue,
                        bankAccount: JsValue,
                        accountType: JsValue,
                        bankName: JsValue,
                        accountName: JsValue): Future[WSResponse] =
    return super.createObject(
      JsObject(Seq(
        "abaRouting" -> abaRouting,
        "bankAccount" -> bankAccount,
        "accountType" -> accountType,
        "bankName" -> bankName,
        "accountName" -> accountName
      )))

  def updatePaymentInfo(id: String,
                        abaRouting: JsValue,
                        bankAccount: JsValue,
                        accountType: JsValue,
                        bankName: JsValue,
                        accountName: JsValue): Future[WSResponse] =
    return super.updateObject(id,
      JsObject(Seq(
        "abaRouting" -> abaRouting,
        "bankAccount" -> bankAccount,
        "accountType" -> accountType,
        "bankName" -> bankName,
        "accountName" -> accountName
      )))
}
