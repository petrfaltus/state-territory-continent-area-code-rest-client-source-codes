from classes.Web import Web

MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service"

requestJsonContinents = "{ \"method_number\" : 1 }"

try:
    replyJsonContinents = Web.request(requestJsonContinents)
except:
    print(" - " + MESSAGE_ERROR_CONTACTING_SERVICE)
    exit()

print(replyJsonContinents)
