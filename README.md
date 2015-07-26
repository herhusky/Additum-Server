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
* Activity
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
* Charity
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
* Merchant
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

## Contributors
* [Deep Randhawa](https://www.github.com/hermes95)

## License
 *Yet To Come*
