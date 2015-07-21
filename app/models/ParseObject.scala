package models

import play.api.Play.current
import play.api.libs.json.JsValue
import play.api.libs.ws.{WS, WSRequestHolder, WSResponse}
import play.api.mvc.Headers

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
  implicit val basicHeaders = HashMap("limit" -> 100) map {
    case (k, v) => Some(k.toString, v.toString)
  } toList
  implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext

  /**
   * Creates a new object with given data. If you just want to create an object,
   * headers can be specified as null. Special headers can be specified for special operations.
   * @param data
   * @param headers
   * @return
   */
  def createObject(data: JsValue)(implicit headers: List[Option[String Tuple2 String]],
                                  query: List[Option[String Tuple2 String]]): Future[WSResponse] = {
    return generateHolder(baseURL + "/" + className)(headers, query)
      .post(data)
  }

  /**
   * Generates generic WSRequestHolder for functions in ParseObject
   * @param url
   * @param headers
   * @param query
   * @return
   */
  def generateHolder(url: String)(implicit headers: List[Option[String Tuple2 String]],
                                  query: List[Option[String Tuple2 String]]): WSRequestHolder = {
    return WS.url(url)
      .withHeaders(ParseAppID, ParseRESTKey, ContentType)
      .withHeaders(headers.flatten.toSeq: _*)
      .withQueryString(query.flatten.toSeq: _*)
      .withFollowRedirects(true)
  }

  /**
   * Retrieves a single object with the objectId and the given class. If you just want to
   * retrieve an object, headers can be specified as null. Special headers can be specified for special operations.
   * @param objectId
   * @param headers
   * @return
   */
  def retrieveObject(objectId: String)(implicit headers: List[Option[String Tuple2 String]],
                                       query: List[Option[String Tuple2 String]]): Future[WSResponse] = {
    return generateHolder(baseURL + "/" + className + "/" + objectId)(headers, query)
      .get()
  }

  /**
   * Retrieves a number of objects from the given class.
   * Add headers for more control over which objects to return
   * @param headers
   * @return
   */
  def retrieveObjects(implicit headers: List[Option[String Tuple2 String]],
                      query: List[Option[String Tuple2 String]]): Future[WSResponse] = {
    return generateHolder(baseURL + "/" + className)(headers, query)
      .get()
  }

  /**
   * Updates the object with objectId and given class, with the given data. If you just want to
   * update and object, headers can be specified as null. Special headers can be specified for special operations.
   * @param objectId
   * @param data
   * @param headers
   * @return
   */
  def updateObject(objectId: String, data: JsValue)(implicit headers: List[Option[String Tuple2 String]],
                                                    query: List[Option[String Tuple2 String]]): Future[WSResponse] = {
    return generateHolder(baseURL + "/" + className + "/" + objectId)(headers, query)
      .put(data)
  }

  /**
   * Converts Headers object to required param type for future function calls,
   * also removes redundant headers from object
   * @param headers
   * @return
   */
  def addHeaders(headers: Headers): List[Option[String Tuple2 String]] = {
    return headers.toSimpleMap.-("Accept").-("User-Agent").-("Content-type").-("Content-Length").-("Host").map {
      case (k, v) => Some(k.toString, v.toString)
    }.toList
  }

  /**
   * Converts Query objects from request to required param type for future function calls
   * @todo at this point the Tuple2 created will only have the head of Seq from Map values. Fix it for
   *       all values in the Seq
   * @param query
   * @return
   */
  def addQueryStrings(query: Map[String, Seq[String]]): List[Option[String Tuple2 String]] = {
    return query.map {
      case (k, v) => Some(k.toString, v.head.toString)
    }.toList
  }
}
