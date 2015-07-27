# Additum-Server

## Description
This project implments the server for Additum app. It is hosted on [Heroku](https://www.heroku.com) 
with the database hosted on [Parse](https://www.parse.com)
* Languages: Scala, HTML, CSS
* REST: Play Framework
* Security: SecureSocial

## Motivation
 *Yet To Come*

## Db Model
* User => Stores all user information
* Activity => Stores all information about activity for when a user walks into a store. Includes user, store, timestamp, amountDonated
* Charity => Includes name, cause, totalDonations, bankInformation. Detailed information about donations collected by charity should be collected from Activity with backend computation
* Merchant => Includes name, website, charities, bankInformation. Detailed information about donations made by merchant should be collected from Activity with backend computation
* Store => Includes location, merchant

## REST API Reference
* **Activity**
  * `GET /activity/:id` => 
    * @param: None
    * @return: activity with `id` as json string. empty, if no such activity is found
  * `GET /activity` =>
    * @param: None 
    * @return: all activities in the database, by default it will have `limit=100` activities. Send request with custom values for respective change
  * `POST /activity` => 
    * @info: creates a new object in the database, fields that are not specified will remain `null` in the database
    * @param: 
      * `userID`: user that entered the store
      * `storeID`: unique store id
      * `amountDonated`: amount that got donated because of this user. This amount will be then divided to charities on the server as per merchant-charity agreements
    * @return: 
      * `objectId`: ~
      * `createdAt`: ~
  * `PUT /activity/:id` => 
    * @info: updates an activity in the db with `id`. only send fields that you want to update in the database, rest will remain unchanged
    * @param: 
      * `user_id`: do not update once created
      * `store_id`: do not update once created
      * `amountDonated`: ~
    * @return: 
      * `updatedAt`: ~
* **Charity**
  * `GET /charity/:id` => 
    * @info: ~
    * @params: None
    * @return: charity with `id` as json string. empty, if no such charity is found
  * `GET /charity` => 
    * @info: ~
    * @params: None
    * @return: all charities in the database, by default it will have `limit=100` activities. Send request with custom values for respective change
  * `POST /charity` => 
    * @info: creates a new object in the database, fields that are not specified will remain `null` in the database
    * @param:
      * `name`: ~
      * `cause`: ~
      * `totalDonations`: ~
    * @return: 
      * `objectId`: ~
      * `createdAt`: ~
  * `POST /charity/payment/:id` =>
    * @info: adds a payment method to the charity. this method will be used by server in merchant-charity transactions
    * @param:
      * `abaRouting`: ~
      * `bankAccount`: ~
      * `accountType`: ~
      * `bankName`: ~
      * `accountName`: ~
    * @return:
      * `updatedAt`: ~ 
  * `PUT /charity/:id` => 
    * @info: updates a charity in the db with `id`. only send fields that you want to update in the database, rest will remain unchanged 
    * @param:
      * `name`: ~
      * `cause`: ~
      * `totalDonations`: do not updated. this will be updated by the server as per the activities
    * @return:
      * `updatedAt`: ~
* **Merchant**
  * `GET /merchant/:id` => 
    * @info: ~
    * @params: None
    * @return: merchant with `id` as json string. empty, if no such charity is found
  * `GET /merchant` => 
    * @info: ~
    * @params: None
    * @return: all merchants in the database, by default it will have `limit=100` activities. Send request with custom values for respective change
  * `POST /merchant` => 
    * @info: creates a new object in the database, fields that are not specified will remain `null` in the database
    * @param:
      * `name`: ~
      * `business`: ~
      * `website`: ~
      * `charity`: list of charities in agreement with this merchant
    * @return: 
      * `objectId`: ~
      * `createdAt`: ~ 
  * `POST /merchant/payment/:id` =>
    * @info: adds a payment method to the merchant. this method will be used by server in merchant-charity transactions
    * @param:
      * `abaRouting`: ~
      * `bankAccount`: ~
      * `accountType`: ~
      * `bankName`: ~
      * `accountName`: ~
    * @return:
      * `updatedAt`: ~ 
  * `PUT /merchant/:id` => 
    * @info: updates a merchant in the db with `id`. only send fields that you want to update in the database, rest will remain unchanged 
    * @param:
      * `name`: ~
      * `business`: ~
      * `website`: ~
      * `charity`: ~
    * @return:
      * `updatedAt`: ~

      
## Example Usage
### Charity
###### Request #1
```
curl -X POST \
  --header "Content-type: application/json" \
  --data '{"name":"RedRover", "cause":"American Kidney Fund", "totalDonations": 50000}' \
  https://additum-server.herokuapp.com/charity
```
###### Response #1
```
{
    "createdAt": "2015-07-27T08:40:47.501Z",
    "objectId": "5hIppVGHet"
}
```
###### Request #2
```
curl -X GET \
  --header "Content-type: application/json" \
  https://additum-server.herokuapp.com/charity/5hIppVGHet
```
###### Response #2
```
{
    "cause": "American Kidney Fund",
    "createdAt": "2015-07-27T08:40:47.501Z",
    "name": "RedRover",
    "objectId": "5hIppVGHet",
    "paymentInfo": {
        "__type": "Relation",
        "className": "Payment"
    },
    "totalDonations": 50000,
    "updatedAt": "2015-07-27T08:40:47.501Z"
}
```

### Merchant
###### Request #1
```
curl -X POST \
  --header "Content-type: application/json" \
  --data '{"name":"Dicks Sporting Goods", "business":"Sporting Goods", "website": "dickssportinggoods.com/"}' \
  https://additum-server.herokuapp.com/merchant
```
###### Response #1
```
{
    "createdAt": "2015-07-27T08:45:11.481Z",
    "objectId": "WBlifuGXXE"
}
```

###### Request #2
```
{
    "business": "Sporting Goods",
    "charity": {
        "__type": "Relation",
        "className": "Charity"
    },
    "createdAt": "2015-07-27T08:45:11.481Z",
    "name": "Dicks Sporting Goods",
    "objectId": "WBlifuGXXE",
    "paymentInfo": {
        "__type": "Relation",
        "className": "Payment"
    },
    "updatedAt": "2015-07-27T08:45:11.481Z",
    "website": "dickssportinggoods.com/"
}
```

## Contributors
* [Deep Randhawa](https://www.github.com/hermes95)

## License
 *Yet To Come*
