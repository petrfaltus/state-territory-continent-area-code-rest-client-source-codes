
namespace StaTerContAcRestClient
{
    public class RestRequest3
    {
        public int method_number { get; set; }

        public string country { get; set; }
        public string iso_code_2_char { get; set; }
        public string iso_code_3_char { get; set; }
        public string owner { get; set; }
        public string continent { get; set; }
        public string phone_prefix { get; set; }

        public RestRequest3()
        {
            method_number = 3;
        }
    }
}
