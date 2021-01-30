
namespace StaTerContAcRestClient
{
    public class RestReply3
    {
        public int error_code { get; set; }
        public string error_string { get; set; }

        public int czech_sensitive { get; set; }
        public int case_sensitive { get; set; }

        public OneItem[] data { get; set; }

        public RestReply3()
        {
            error_code = -999;
        }
    }
}
