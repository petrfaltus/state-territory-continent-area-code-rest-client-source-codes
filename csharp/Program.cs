using System;
using System.Net;
using System.Text;

using Newtonsoft.Json;

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

            // one query
            Console.WriteLine("One query:"); 

            RestRequest3 restRequest3 = new RestRequest3();
            restRequest3.country = "";
            restRequest3.iso_code_2_char = "";
            restRequest3.iso_code_3_char = "";
            restRequest3.owner = "francie";
            restRequest3.continent = "tichý oceán";
            restRequest3.phone_prefix = "";

            string restRequestJson = JsonConvert.SerializeObject(restRequest3);

            string restReplyJson;
            try
            {
                restReplyJson = client.UploadString(URL_ADDRESS, restRequestJson);
            }
            catch (Exception)
            {
                Console.WriteLine(WEB_REQUEST_FAILED);
                return;
            }

            RestReply3 restReply3 = JsonConvert.DeserializeObject<RestReply3>(restReplyJson);

            if (restReply3.error_code != 0)
            {
                Console.WriteLine(restReply3.error_string);
                return;
            }

            foreach (OneItem replyItem in restReply3.data)
            {
                Console.WriteLine(" - country: " + replyItem.country);
                Console.WriteLine(" - ISO code 2 characters: " + replyItem.iso_code_2_char);
                Console.WriteLine(" - ISO code 3 characters: " + replyItem.iso_code_3_char);
                Console.WriteLine(" - owner: " + replyItem.owner);
                Console.WriteLine(" - continent: " + replyItem.continent);
                Console.WriteLine(" - phone prefix: " + replyItem.phone_prefix);

                Console.WriteLine();
            }
        }
    }
}
