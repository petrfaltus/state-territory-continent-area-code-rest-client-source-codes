import json

class Json:
    METHOD_NUMBER = "method_number"
    COUNTRY = "country"
    ISO_CODE_2_CHAR = "iso_code_2_char"
    ISO_CODE_3_CHAR = "iso_code_3_char"
    OWNER = "owner"
    CONTINENT = "continent"
    PHONE_PREFIX = "phone_prefix"

    ERROR_CODE = "error_code"
    ERROR_STRING = "error_string"
    DATA = "data"

    METHOD_OWNERS_NUMBER = 1
    METHOD_CONTINENTS_NUMBER = 2
    METHOD_ONE_QUERY_NUMBER = 3

    lastErrorString = None

    @staticmethod
    def codeQueryOwners():
        output = {Json.METHOD_NUMBER: Json.METHOD_OWNERS_NUMBER}
        outputJson = json.dumps(output)

        return outputJson

    @staticmethod
    def codeQueryContinents():
        output = {Json.METHOD_NUMBER: Json.METHOD_CONTINENTS_NUMBER}
        outputJson = json.dumps(output)

        return outputJson

    @staticmethod
    def codeQueryOneQuery(country, iso_code_2_char, iso_code_3_char, owner, continent, phone_prefix):
        output = {Json.METHOD_NUMBER: Json.METHOD_ONE_QUERY_NUMBER,
                  Json.COUNTRY: country,
                  Json.ISO_CODE_2_CHAR: iso_code_2_char,
                  Json.ISO_CODE_3_CHAR: iso_code_3_char,
                  Json.OWNER: owner,
                  Json.CONTINENT: continent,
                  Json.PHONE_PREFIX: phone_prefix}
        outputJson = json.dumps(output)

        return outputJson

    @staticmethod
    def decodeResult(inputJson):
        retData = None
        Json.lastErrorString = None

        try:
            input = json.loads(inputJson)

            if input[Json.ERROR_CODE] == 0:
                retData = input[Json.DATA]
            else:
                # error reported by the service
                Json.lastErrorString = input[Json.ERROR_STRING]
        except:
            # invalid or corrupted JSON
            retData = None

        return retData

    @staticmethod
    def getLastErrorString():
        return Json.lastErrorString
