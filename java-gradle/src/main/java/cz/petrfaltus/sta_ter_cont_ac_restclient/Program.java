package cz.petrfaltus.sta_ter_cont_ac_restclient;

import static java.lang.System.out;

import java.util.List;

public class Program {

    public static void main(String[] args) {
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
        String replyJsonQuery = Web.request(requestJsonQuery);
        List<OneItem> replyItems = Json.decodeResultOneQuery(replyJsonQuery);

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
