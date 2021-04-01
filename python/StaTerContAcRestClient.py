from classes.Web import Web
from classes.Json import Json

MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service"
MESSAGE_ERROR_DECODING_JSON = "error decoding the reply JSON"
MESSAGE_RECEIVED_ERROR = "received error"

# all continents query
print("All continents:")

requestJsonContinents = Json.codeQueryContinents()

try:
    replyJsonContinents = Web.request(requestJsonContinents)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

continents = Json.decodeResult(replyJsonContinents)
if continents == None:
    errorString = Json.getLastErrorString()

    if errorString != None:
        print(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString)
    else:
        print(" - " + MESSAGE_ERROR_DECODING_JSON)

    exit()

for continent in continents:
    countries = str(continents[continent])
    print(" - " + continent + " (" + countries + " countries}")

print()

# one query
print("One query:")

country = ""
iso_code_2_char = ""
iso_code_3_char = ""
owner = "francie"
continent = "tichý oceán"
phone_prefix = ""

requestJson = Json.codeQueryOneQuery(country, iso_code_2_char, iso_code_3_char, owner, continent, phone_prefix)

try:
    replyJson = Web.request(requestJson)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

replyItems = Json.decodeResult(replyJson)
if replyItems == None:
    errorString = Json.getLastErrorString()

    if errorString != None:
        print(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString)
    else:
        print(" - " + MESSAGE_ERROR_DECODING_JSON)

    exit()

totalItems = 0
for replyItem in replyItems:
    if totalItems > 0:
        print()

    print(" - country: " + replyItem[Json.COUNTRY])
    print(" - ISO code 2 characters: " + replyItem[Json.ISO_CODE_2_CHAR])
    print(" - ISO code 3 characters: " + replyItem[Json.ISO_CODE_3_CHAR])
    print(" - owner: " + replyItem[Json.OWNER])
    print(" - continent: " + replyItem[Json.CONTINENT])
    print(" - phone prefix: " + replyItem[Json.PHONE_PREFIX])

    totalItems = totalItems + 1
