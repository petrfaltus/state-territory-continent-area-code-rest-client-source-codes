using System;
using System.Net;
using System.Text;

namespace StaTerContAcRestClient
{
    public class Program
    {
        private static readonly string URL_ADDRESS = "http://api.petrfaltus.net/country_code_prefix/json/1.0";
        private static readonly string WEB_REQUEST_FAILED = "The web request to the REST service failed";
        private static readonly Encoding encoding = Encoding.UTF8;

        public static void Main(string[] args)
        {
            WebClient client = new WebClient();
            client.Encoding = encoding;

            string restRequestJson = "{ \"method_number\":3, \"owner\":\"francie\", \"continent\":\"tichý oceán\" }";

            try
            {
                string restReplyJson = client.UploadString(URL_ADDRESS, restRequestJson);
                Console.WriteLine(restReplyJson);
            }
            catch (Exception)
            {
                Console.WriteLine(WEB_REQUEST_FAILED);
                return;
            }
        }
    }
}
