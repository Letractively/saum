package br.com.meganet.util;

import javax.servlet.http.HttpServletRequest;

public class UtilMobile {
	private static UtilMobile singleton = null;
	public static String status = null;

	private UtilMobile() {

	}

	public static UtilMobile getInstancia() {
		if (singleton == null) {
			singleton = new UtilMobile();
		}
		return singleton;
	}

	public boolean mobileDeviceDetect(HttpServletRequest request) {
		status = null;
		String user_agent = request.getHeader("user-agent");
		String accept = request.getHeader("accept");
		boolean mobile_browser = false;
		if (matches("ipod", user_agent) || matches("iphone", user_agent)) {
			mobile_browser = true;
			status = "Apple";
		} else if (matches("android", user_agent)) {
			mobile_browser = true;
			status = "Android";
		} else if (matches("opera mini", user_agent)) {
			mobile_browser = true;
			status = "Opera";
		} else if (matches("blackberry", user_agent)) {
			mobile_browser = true;
			status = "Blackberry";
		} else if (pregMactches(
				"palm os|palm|hiptop|avantgo|fennec|plucker|xiino|blazer|elaine",
				user_agent)) {
			mobile_browser = true;
			status = "Palm";
		} else if (pregMactches(
				"iris|3g_t|windows ce|opera mobi|windows ce; smartphone;|windows ce; iemobile",
				user_agent)) {
			mobile_browser = true;
			status = "Windows Smartphone";
		} else if (pregMactches(
				"mini 9.5|vx1000|lge |m800|e860|u940|ux840|compal|wireless| mobi|ahong|lg380|lgku|lgu900|lg210|lg47|lg920|lg840|lg370|sam-r|mg50|s55|g83|t66|vx400|mk99|d615|d763|el370|sl900|mp500|samu3|samu4|vx10|xda_|samu5|samu6|samu7|samu9|a615|b832|m881|s920|n210|s700|c-810|_h797|mob-x|sk16d|848b|mowser|s580|r800|471x|v120|rim8|c500foma:|160x|x160|480x|x640|t503|w839|i250|sprint|w398samr810|m5252|c7100|mt126|x225|s5330|s820|htil-g1|fly v71|s302|-x113|novarra|k610i|-three|8325rc|8352rc|sanyo|vx54|c888|nx250|n120|mtk |c5588|s710|t880|c5005|i;458x|p404i|s210|c5100|teleca|s940|c500|s590|foma|samsu|vx8|vx9|a1000|_mms|myx|a700|gu1100|bc831|e300|ems100|me701|me702m-three|sd588|s800|8325rc|ac831|mw200|brew |d88|htc\\/|htc_touch|355x|m50|km100|d736|p-9521|telco|sl74|ktouch|m4u\\/|me702|8325rc|kddi|phone|lg |sonyericsson|samsung|240x|x320vx10|nokia|sony cmd|motorola|up.browser|up.link|mmp|symbian|smartphone|midp|wap|vodafone|o2|pocket|kindle|mobile|psp|treo",
				user_agent)) {
			mobile_browser = true;
			status = "Mobile genérico - pregMatches";
		} else if ((indexOf(accept, "text/vnd.wap.wml") > 0)
				|| (indexOf(accept, "application/vnd.wap.xhtml+xml") > 0)) {
			mobile_browser = true;
			status = "Mobile genérico - accept header";
		}
		return mobile_browser;
	}

	private static int indexOf(String accept, String string) {
		int indexOf = -1;
		if (accept != null) {
			indexOf = accept.indexOf(string);
		}
		return indexOf;
	}

	private boolean pregMactches(String string, String user_agent) {
		String regexs[] = string.split("\\|");
		boolean retorno = false;
		for (int i = 0; i < regexs.length && !retorno; i++) {
			retorno = matches(regexs[i], user_agent);
		}
		return retorno;
	}

	private boolean matches(String regex, String source) {
		if (source != null) {
			source = source.toLowerCase();
		}
		return source.matches(".*" + regex + ".*");
	}

}
	