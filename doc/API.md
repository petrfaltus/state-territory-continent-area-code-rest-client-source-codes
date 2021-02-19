# State, territory, continent and area code REST API
Application interface description
(c) Petr Faltus 2021

---
## All owners request
Returns all owners with counts present in the API.

-   **URL**
    <http://api.petrfaltus.net/country_code_prefix/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value | Type    |
        | --------        | ----- | ----    |
        | `method_number` | 1     | integer |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 1
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value          | Type                |
        | --------       | -----          | ----                |
        | `error_code`   | 0              | integer             |
        | `error_string` | "ok"           | string              |
        | `data`         | *substructure* | list of *Data Item* |

    -   **Data Item Content**
        | Variable   | Value | Type    |
        | --------   | ----- | ----    |
        | owner name | count | integer |

    -   **Example JSON Reply**
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

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## All continents request
Returns all continents with counts present in the API.

-   **URL**
    <http://api.petrfaltus.net/country_code_prefix/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value | Type    |
        | --------        | ----- | ----    |
        | `method_number` | 2     | integer |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 2
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value          | Type                |
        | --------       | -----          | ----                |
        | `error_code`   | 0              | integer             |
        | `error_string` | "ok"           | string              |
        | `data`         | *substructure* | list of *Data Item* |

    -   **Data Item Content**
        | Variable       | Value | Type    |
        | --------       | ----- | ----    |
        | continent name | count | integer |

    -   **Example JSON Reply**
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

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## One query request
Returns one country/ISO code/owner/continent/phone prefix request from the API.

-   **URL**
    <http://api.petrfaltus.net/country_code_prefix/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value | Type    |
        | --------        | ----- | ----    |
        | `method_number` | 3     | integer |

    -   **Required at least one of**
        | Variable          | Value                        | Type   |
        | --------          | -----                        | ----   |
        | `country`         | country                      | string |
        | `iso_code_2_char` | ISO 3166-1 alpha-2 code      | string |
        | `iso_code_3_char` | ISO 3166-1 alpha-3 code      | string |
        | `owner`           | owner                        | string |
        | `continent`       | continent                    | string |
        | `phone_prefix`    | phone prefix or phone number | string |

    -   **Optional**
        | Variable          | Value  | Type    |
        | --------          | -----  | ----    |
        | `czech_sensitive` | 0 or 1 | integer |
        | `case_sensitive`  | 0 or 1 | integer |

    -   **Example JSON Request**
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

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable          | Value                          | Type                 |
        | --------          | -----                          | ----                 |
        | `error_code`      | 0                              | integer              |
        | `error_string`    | "ok"                           | string               |
        | `czech_sensitive` | 0 or 1 (used for this request) | integer              |
        | `case_sensitive`  | 0 or 1 (used for this request) | integer              |
        | `data`            | *substructure*                 | array of *Data Item* |

    -   **Data Item Content**
        | Variable          | Value                        | Type   |
        | --------          | -----                        | ----   |
        | `country`         | country                      | string |
        | `iso_code_2_char` | ISO 3166-1 alpha-2 code      | string |
        | `iso_code_3_char` | ISO 3166-1 alpha-3 code      | string |
        | `owner`           | owner                        | string |
        | `continent`       | continent                    | string |
        | `phone_prefix`    | phone prefix or phone number | string |

    -   **Example JSON Reply**
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

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |
