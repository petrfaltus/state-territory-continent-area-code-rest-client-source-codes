import http.client

class Web:
    HOST = "api.petrfaltus.net"
    PATH = "/country_code_prefix/json/1.0"
    ENCODING = "utf-8"
    USER_AGENT = "Petr Faltus Python State, territory, continent and area code REST client"

    @staticmethod
    def request(body):
        headers = {"user-agent": Web.USER_AGENT}

        connection = http.client.HTTPConnection(Web.HOST)
        connection.request("POST", Web.PATH, body, headers)

        response = connection.getresponse()
        responseData = response.read()
        responseData = responseData.decode(Web.ENCODING)

        connection.close()

        return responseData
