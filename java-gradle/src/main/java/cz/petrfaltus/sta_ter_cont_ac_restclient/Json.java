package cz.petrfaltus.sta_ter_cont_ac_restclient;

import java.io.IOException;
import java.io.StringWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
    private static final String METHOD_NUMBER = "method_number";

    private static final String COUNTRY = "country";
    private static final String ISO_CODE_2_CHAR = "iso_code_2_char";
    private static final String ISO_CODE_3_CHAR = "iso_code_3_char";
    private static final String OWNER = "owner";
    private static final String CONTINENT = "continent";
    private static final String PHONE_PREFIX = "phone_prefix";

    private static final String ERROR_CODE = "error_code";
    private static final String ERROR_STRING = "error_string";
    private static final String DATA = "data";

    private static final long METHOD_ONE_QUERY_NUMBER = 3;

    private static String objToString(JSONObject obj) {
        String retString = Const.EMPTY_STRING;

        try {
            StringWriter out = new StringWriter();
            obj.writeJSONString(out);

            retString = out.toString();
        } catch (IOException ioex) {
            retString = null;
        }

        return retString;
    }

    public static String codeOneQuery(OneItem query) {
        JSONObject obj = new JSONObject();
        obj.put(METHOD_NUMBER, METHOD_ONE_QUERY_NUMBER);
        obj.put(COUNTRY, query.country);
        obj.put(ISO_CODE_2_CHAR, query.iso_code_2_char);
        obj.put(ISO_CODE_3_CHAR, query.iso_code_3_char);
        obj.put(OWNER, query.owner);
        obj.put(CONTINENT, query.continent);
        obj.put(PHONE_PREFIX, query.phone_prefix);

        String retString = objToString(obj);

        return retString;
    }

    public static List<OneItem> decodeResultOneQuery(String resultJson) {
        List<OneItem> retList = null;

        try {
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(resultJson);
            long errorCode = (long) jsonObject.get(ERROR_CODE);

            if (errorCode == 0) {
                JSONArray data = (JSONArray) jsonObject.get(DATA);

                retList = new ArrayList<OneItem>();

                Iterator<JSONObject> di = data.iterator();
                while (di.hasNext()) {
                    JSONObject jsonObjectNext = di.next();

                    OneItem oneItem = new OneItem();
                    oneItem.country = (String) jsonObjectNext.get(COUNTRY);
                    oneItem.iso_code_2_char = (String) jsonObjectNext.get(ISO_CODE_2_CHAR);
                    oneItem.iso_code_3_char = (String) jsonObjectNext.get(ISO_CODE_3_CHAR);
                    oneItem.owner = (String) jsonObjectNext.get(OWNER);
                    oneItem.continent = (String) jsonObjectNext.get(CONTINENT);
                    oneItem.phone_prefix = (String) jsonObjectNext.get(PHONE_PREFIX);

                    retList.add(oneItem);
                }
            }
        } catch (ParseException pe) {
            retList = null;
        } catch (NullPointerException npe) {
            retList = null;
        }

        return retList;
    }

}
