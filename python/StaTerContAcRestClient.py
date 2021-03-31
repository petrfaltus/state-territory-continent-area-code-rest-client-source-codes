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
