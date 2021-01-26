package cz.petrfaltus.sta_ter_cont_ac_restclient;

import static java.lang.System.out;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Program {

    public static void main(String[] args) {
        // all continents query
        out.println("All continents:");

        String requestJsonContinents = Json.codeQueryContinents();
        if (requestJsonContinents == null) {
            out.println(" - error coding of request JSON");
            return;
        }
        String replyJsonContinents = Web.request(requestJsonContinents);
        if (replyJsonContinents == null) {
            out.println(" - error sending POST web query");
            return;
        }
        Map<String, Integer> continents = Json.decodeResultOwnersOrContinents(replyJsonContinents);
        if (continents == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - replied error '" + errorString + "'");
            } else {
                out.println(" - error decoding of reply JSON");
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
            out.println(" - error coding of request JSON");
            return;
        }
        String replyJsonQuery = Web.request(requestJsonQuery);
        if (replyJsonQuery == null) {
            out.println(" - error sending POST web query");
            return;
        }
        List<OneItem> replyItems = Json.decodeResultOneQuery(replyJsonQuery);
        if (replyItems == null) {
            String errorString = Json.getLastErrorString();

            if (errorString != null) {
                out.println(" - replied error '" + errorString + "'");
            } else {
                out.println(" - error decoding of reply JSON");
            }

            return;
        }

        for (OneItem replyItem: replyItems) {
            out.println(" - country: " + replyItem.country);
            out.println(" - ISO code 2 characters: " + replyItem.iso_code_2_char);
            out.println(" - ISO code 3 characters: " + replyItem.iso_code_3_char);
            out.println(" - owner: " + replyItem.owner);
            out.println(" - continent: " + replyItem.continent);
            out.println(" - phone prefix: " + replyItem.phone_prefix);

            out.println();
        }
    }

}
