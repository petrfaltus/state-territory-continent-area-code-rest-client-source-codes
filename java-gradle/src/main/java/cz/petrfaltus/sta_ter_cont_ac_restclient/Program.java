package cz.petrfaltus.sta_ter_cont_ac_restclient;

import static java.lang.System.out;

public class Program {

	public static void main(String[] args) {
		String requestJson = "{ \"method_number\":3, \"owner\":\"francie\", \"continent\":\"tichý oceán\" }";

		String replyJson = Web.request(requestJson);
		out.println(replyJson);
	}

}
