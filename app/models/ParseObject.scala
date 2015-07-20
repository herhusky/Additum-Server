package models

import play.api.Play.current
import play.api.libs.json.JsValue
import play.api.libs.ws.{WS, WSResponse}

import scala.collection.mutable.HashMap
import scala.concurrent.Future

/**
 * Created by deep on 7/16/15.
 */
trait ParseObject {
  val ParseAppID = "X-Parse-Application-Id" -> System.getenv("ADDITUM_PARSE_APP_ID")
  val ParseRESTKey = "X-Parse-REST-API-Key" -> System.getenv("ADDITUM_PARSE_REST_API_KEY")
  val ContentType = "Content-Type" -> "application/json"
  val baseURL = "https://api.parse.com/1/classes"
  val className: String
  implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext

  /**
   * Creates a new object with given data. If you just want to create an object,
   * headers can be specified as null. Special headers can be specified for special operations.
   * @param data
   * @param headers
   * @return
   */
  def createObject(data: JsValue, headers: List[Option[String Tuple2 String]]): Future[WSResponse] = {
    return WS.url(baseURL + "/" + className)
      .withHeaders(ParseAppID, ParseRESTKey, ContentType)
      .withHeaders(headers.flatten.toSeq: _*)
      .withFollowRedirects(true)
      .post(data)
  }

  /**
   * Retrieves a single object with the objectId and the given class. If you just want to
   * retrieve an object, headers can be specified as null. Special headers can be specified for special operations.
   * @param objectId
   * @param headers
   * @return
   */
  def retrieveObject(objectId: String, headers: HashMap[String, String]): Future[WSResponse] = ???

  /**
   * Updates the object with objectId and given class, with the given data. If you just want to
   * update and object, headers can be specified as null. Special headers can be specified for special operations.
   * @param objectId
   * @param data
   * @param headers
   * @return
   */
  def updateObject(objectId: String, data: JsValue, headers: HashMap[String, String]): Future[WSResponse] = ???
}
