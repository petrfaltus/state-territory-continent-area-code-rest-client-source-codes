using System.Collections.Generic;

namespace StaTerContAcRestClient
{
    public class RestReply1or2
    {
        public int error_code { get; set; }
        public string error_string { get; set; }

        public Dictionary<string, int> data { get; set; }
    }
}
