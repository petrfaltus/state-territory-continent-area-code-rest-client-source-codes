package cz.petrfaltus.sta_ter_cont_ac_restclient;

import static java.lang.System.out;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Program {
    public static final String MESSAGE_ERROR_CODING_JSON = "error coding the request JSON";
    public static final String MESSAGE_ERROR_CONTACTING_SERVICE = "error contacting the REST service";
    public static final String MESSAGE_ERROR_DECODING_JSON = "error decoding the reply JSON";
    public static final String MESSAGE_RECEIVED_ERROR = "received error";

    public static void main(String[] args) {
        // all continents query
        out.println("All continents:");

        String requestJsonContinents = Json.codeQueryContinents();
        if (requestJsonContinents == null) {
            out.println(" - " + MESSAGE_ERROR_CODING_JSON);
            return;
        }
        String replyJsonContinents = Web.request(requestJsonContinents);
        if (replyJsonContinents == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        Map<String, Integer> continents = Json.decodeResultOwnersOrContinents(replyJsonContinents);
        if (continents == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString);
            } else {
                out.println(" - " + MESSAGE_ERROR_DECODING_JSON);
            }

            return;
        }

        Set<Map.Entry<String, Integer>> continentsEntrySet = continents.entrySet();
        for (Map.Entry<String, Integer> continent : continentsEntrySet) {
            out.println(" - " + continent.getKey() + " (" + continent.getValue() + " countries}");
        }

        out.println();

        // one query
        out.println("One query:");

        OneItem requestItem = new OneItem();
        requestItem.country = "";
        requestItem.iso_code_2_char = "";
        requestItem.iso_code_3_char = "";
        requestItem.owner = "francie";
        requestItem.continent = "tichý oceán";
        requestItem.phone_prefix = "";

        String requestJsonQuery = Json.codeOneQuery(requestItem);
        if (requestJsonQuery == null) {
            out.println(" - " + MESSAGE_ERROR_CODING_JSON);
            return;
        }
        String replyJsonQuery = Web.request(requestJsonQuery);
        if (replyJsonQuery == null) {
            out.println(" - " + MESSAGE_ERROR_CONTACTING_SERVICE);
            return;
        }
        List<OneItem> replyItems = Json.decodeResultOneQuery(replyJsonQuery);
        if (replyItems == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - " + MESSAGE_RECEIVED_ERROR + ": " + errorString);
            } else {
                out.println(" - " + MESSAGE_ERROR_DECODING_JSON);
            }

            return;
        }

        int totalItems = 0;
        for (OneItem replyItem: replyItems) {
            if (totalItems > 0)
            {
                out.println();
            }

            out.println(" - country: " + replyItem.country);
            out.println(" - ISO code 2 characters: " + replyItem.iso_code_2_char);
            out.println(" - ISO code 3 characters: " + replyItem.iso_code_3_char);
            out.println(" - owner: " + replyItem.owner);
            out.println(" - continent: " + replyItem.continent);
            out.println(" - phone prefix: " + replyItem.phone_prefix);

            totalItems++;
        }
    }

}
