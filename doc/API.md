# State, territory, continent and area code REST API
Application interface description
(c) Petr Faltus 2021

**Get all owners**
----
Returns json data about all owners with counts in the API.

* **URL**
  http://api.petrfaltus.net/country_code_prefix/json/1.0

* **Method:**
  `POST`

* **URL Params**
  None

* **Data Params**
  * **Required:**
    `method_number : 1`

  * **Optional:**
    None

  * **Example JSON Request Data:**
    ```javascript
    {
      "method_number" : 1
    }
    ```

* **Success Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : 0,
      "error_string" : "ok",
      "data" : {
                 "Spojené království Velké Británie a Severního Irska (zámořské území)" : 10,
                 "Spojené království Velké Británie a Severního Irska (korunní závislost s rozsáhlou autonomií)" : 5
               }
    }
    ```

* **Error Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : integer,
      "error_string" : "message"
    }
    ```
    *(`error_code` != 0)*

**Get all continents**
----
Returns json data about all continents with counts in the API.

* **URL**
  http://api.petrfaltus.net/country_code_prefix/json/1.0

* **Method:**
  `POST`

* **URL Params**
  None

* **Data Params**
  * **Required:**
    `method_number : 2`

  * **Optional:**
    None

  * **Example JSON Request Data:**
    ```javascript
    {
      "method_number" : 2
    }
    ```

* **Success Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : 0,
      "error_string" : "ok",
      "data" : {
                 "Afrika" : 48,
                 "Evropa" : 44
               }
    }
    ```

* **Error Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : integer,
      "error_string" : "message"
    }
    ```
    *(`error_code` != 0)*

**One query request**
----
Returns json data for one request to the API.

* **URL**
  http://api.petrfaltus.net/country_code_prefix/json/1.0

* **Method:**
  `POST`

* **URL Params**
  None

* **Data Params**
  * **Required:**
    `method_number : 3`

  * **Required at least one of:**
    `country : [country string]`
    `iso_code_2_char : [ISO 3166-1 alpha-2 code string]`
    `iso_code_3_char : [ISO 3166-1 alpha-3 code string]`
    `owner : [owner string]`
    `continent : [continent string]`
    `phone_prefix : [phone prefix or phone number string]`

  * **Optional:**
    `czech_sensitive : [0|1]`
    `case_sensitive : [0|1]`

* **Example JSON Request Data:**
  ```javascript
  {
    "method_number" : 3,
    "owner" : "francie",
    "continent" : "tichý oceán"
  }
  ```
  or
  ```javascript
  {
    "method_number" : 3,
    "country" : "rakousko"
  }
  ```
  or
  ```javascript
  {
    "method_number" : 3,
    "country" : "Panenské ostrovy",
    "czech_sensitive : 1,
    "case_sensitive : 1
  }
  ```

* **Success Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : 0,
      "error_string" : "ok",
      "czech_sensitive" : 0,
      "case_sensitive" : 0,
      "data" : [
                 {
                   "country" : "Francouzská Polynésie",
                   "iso_code_2_char" : "PF",
                   "iso_code_3_char" : "PYF",
                   "owner" : "Francie (zámořské společenství)",
                   "continent" : "Tichý oceán",
                   "phone_prefix" : "+689"
                 },
                 {
                   "country" : "Nová Kaledonie",
                   "iso_code_2_char" : "NC",
                   "iso_code_3_char" : "NCL",
                   "owner" : "Francie (zámořské území)",
                   "continent" : "Tichý oceán",
                   "phone_prefix" : "+687"
                 }
               ]
    }
    ```
    *(with `czech_sensitive` and `case_sensitive` the values used for this request)*

* **Error Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : integer,
      "error_string" : "message"
    }
    ```
    *(`error_code` != 0)*
