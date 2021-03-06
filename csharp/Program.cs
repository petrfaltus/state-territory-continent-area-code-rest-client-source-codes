using System;
using System.Collections.Generic;

using Newtonsoft.Json;

namespace StaTerContAcRestClient
{
    public class Program
    {
        private static readonly string MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service";
        private static readonly string MESSAGE_RECEIVED_ERROR = "received error";

        public static void Main(string[] args)
        {
            // all continents query
            Console.WriteLine("All continents:");

            RestRequest2 restRequest2 = new RestRequest2();

            string restRequestJsonContinents = JsonConvert.SerializeObject(restRequest2);

            string restReplyJsonContinents;
            try
            {
                restReplyJsonContinents = Web.Request(restRequestJsonContinents);
            }
            catch (Exception)
            {
                Console.WriteLine(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
                return;
            }

            RestReply1or2 restReply2 = JsonConvert.DeserializeObject<RestReply1or2>(restReplyJsonContinents);

            if (restReply2.error_code != 0)
            {
                Console.WriteLine(" - " + MESSAGE_RECEIVED_ERROR + ": " + restReply2.error_string);
                return;
            }

            foreach(KeyValuePair<string, int> continent in restReply2.data)
            {
                Console.WriteLine(" - " + continent.Key + " (" + continent.Value + " countries}");
            }

            Console.WriteLine();

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
                restReplyJson = Web.Request(restRequestJson);
            }
            catch (Exception)
            {
                Console.WriteLine(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
                return;
            }

            RestReply3 restReply3 = JsonConvert.DeserializeObject<RestReply3>(restReplyJson);

            if (restReply3.error_code != 0)
            {
                Console.WriteLine(" - " + MESSAGE_RECEIVED_ERROR + ": " + restReply3.error_string);
                return;
            }

            int totalItems = 0;
            foreach (OneItem replyItem in restReply3.data)
            {
                if (totalItems > 0)
                {
                    Console.WriteLine();
                }

                Console.WriteLine(" - country: " + replyItem.country);
                Console.WriteLine(" - ISO code 2 characters: " + replyItem.iso_code_2_char);
                Console.WriteLine(" - ISO code 3 characters: " + replyItem.iso_code_3_char);
                Console.WriteLine(" - owner: " + replyItem.owner);
                Console.WriteLine(" - continent: " + replyItem.continent);
                Console.WriteLine(" - phone prefix: " + replyItem.phone_prefix);

                totalItems++;
            }
        }
    }
}
