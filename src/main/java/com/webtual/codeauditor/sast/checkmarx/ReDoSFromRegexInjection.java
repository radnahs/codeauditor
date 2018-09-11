 /**
 * <p>Project: rytry </p>
 * <p>Package Name: com.webtual.codeauditor.sast.checkmarx; </p>
 * <p>File Name: ReDoSFromRegexInjection.java </p>
 * <p>Create Date: Jul 30, 2018 </p>
 * <p>Create Time: 8:24:02 PM </p>
 * <p>Description: ReDoS From Regex Injection</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company:  </p>
 * @author Shantanu Sikdar
 * @version 1.0
 */

package com.webtual.codeauditor.sast.checkmarx;;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * <p>
 * <b>ReDoS From Regex Injection</b>
 * </p>
 * 
 * <pre>
 * <p>
 * <b>Risk</b><br>
 * <b>What might happen</b><br>
 * ReDoS (regular expression denial of service) can use complex patterns to
 * cause a denial of service (DoS). With certain patterns, processing time can
 * grow exponentially in relation to input size. An attacker can use these
 * regular expressions to cause the application to spend an effectively infinite
 * amount of time processing, preventing legitmate users from accessing the
 * application.<br>
 * 
 * <b>Cause</b><br>
 * <b>How does it happen</b><br>
 * ReDoS (regular expression denial of service) is an algorithmic complexity
 * attack, that exploits exponential time worst case complexity. In particular,
 * certain regex patterns - either explicitly coded in the application, or
 * accepted from user input and used for searching text - can cause extreme
 * levels of processing for some input texts. For example, `(a+)+` would hang on
 * an input of a long string of "aaaaaaaaaaaaaaaaaaaaaaaa!"
 * 
 * <b>General Recommendations</b><br>
 * <b>How to avoid it</b> <br>
 * o Do not use input for constructing a regular expression. o Ensure all
 * regexes are not vulnerable to ReDoS, specifically worst case complexity does
 * not cause the application to hang. o Strive to avoid unnecessarily complex
 * expressions, making them as simple as possible.
 * </p>
 * 
 * @author Shantanu Sikdar
 * 
 */

public class ReDoSFromRegexInjection {

	private void regexEscape(String text) {
		System.out.println(text);

		String ss = text.replace("/([^a-zA-Z0-9])/g", "\\$1");

		System.out.println(ss);

	}



	public static void main(String[] args) {

		ReDoSFromRegexInjection redosFromRegexInjection = new ReDoSFromRegexInjection();

		redosFromRegexInjection.regexEscape("shanta_$$5sdf@$%^//");

		

		

		CompliantReDoSFromRegexInjection compliantReDosCodeExample = new CompliantReDoSFromRegexInjection();

		String ster = CompliantReDoSFromRegexInjectionDotNetWay.escapeRegEx("shanta_$$5sdf@$%^//");

		System.out.println(ster);

		/*

		 * DummyRequest dummyRequest = new DummyRequest();

		 * dummyRequest.setAttribute("regex", "\\d+");

		 * dummyRequest.setAttribute("input", "234132341322");

		 * 

		 * NonCompliantReDoSFromRegexInjection typeNonCompliant = new

		 * NonCompliantReDoSFromRegexInjection();

		 * System.out.println(typeNonCompliant.nonCompliantReDosCodeExample(

		 * dummyRequest));

		 * 

		 * CompliantReDoSFromRegexInjection typeCompliant = new

		 * CompliantReDoSFromRegexInjection();

		 * System.out.println(typeCompliant.compliantCodeEg1(dummyRequest));

		 */

	}



}



class DummyRequest {



	private Map<String, String> mapRequest = new HashMap<>();



	public void setAttribute(String key, String value) {

		mapRequest.put(key, value);

	}



	public String getParameter(String key) {

		return mapRequest.get(key);

	}



}



// https://rules.sonarsource.com/java/tag/owasp/RSPEC-2631

class NonCompliantReDoSFromRegexInjection {



	// BAD: Unsanitized user input is used to construct a regular expression

	// Enables attackers to force the web server to evaluate

	// regex such as "(a+)+" on inputs such as "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa!"

	public boolean nonCompliantReDosCodeExample(DummyRequest request) {

		String regex = request.getParameter("regex");

		String input = request.getParameter("input");



		return input.matches(regex); // Noncompliant

	}



}



// https://help.semmle.com/wiki/display/CSHARP/Regular+expression+injection

class CompliantReDoSFromRegexInjection {



	// GOOD: User input is sanitized before constructing the regex

	public boolean compliantCodeEg1(DummyRequest request) {

		String input = request.getParameter("input");



		return input.matches("\\d+"); // Compliant - use a safe hardcoded regex

	}



	public boolean compliantCodeEg2(DummyRequest request) {

		String input = request.getParameter("input");

		String regex = request.getParameter("regex");



		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(input);

		while (m.find()) {

			System.out.println(input.substring(m.start(), m.end()));

		}

		return true;

	}

	

}



//https://referencesource.microsoft.com/#System/regex/system/text/regularexpressions/RegexParser.cs,bf0364a806ccb693

class CompliantReDoSFromRegexInjectionDotNetWay{

	private static byte Q = 5;    // quantifier

	private static byte S = 4;    // ordinary stoppper

	private static byte Z = 3;    // ScanBlank stopper

	private static byte X = 2;    // whitespace

	private static byte E = 1;    // should be escaped



	private static byte[] _category = new byte[] {

               0,0,0,0,0,0,0,0,0,X,X,0,X,X,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,

               X,0,0,Z,S,0,0,0,S,S,Q,Q,0,0,S,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,Q,

               0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,S,S,0,S,0,

               0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,Q,S,0,0,0};

	

	public static String escapeRegEx(String inputStr) {

		char[] input = inputStr.toCharArray();

		for (int i = 0; i < input.length; i++) {

			if (isMetachar(input[i])) {

				StringBuilder sb = new StringBuilder();

				char ch = input[i];

				int lastpos;

				sb.append(input, 0, i);

				do {

					sb.append('\\');

					switch (ch) {

					case '\n':

						ch = 'n';

						break;

					case '\r':

						ch = 'r';

						break;

					case '\t':

						ch = 't';

						break;

					case '\f':

						ch = 'f';

						break;

					}

					sb.append(ch);

					i++;

					lastpos = i;

					while (i < input.length) {

						ch = input[i];

						if (isMetachar(ch)) {

							break;

						}

						i++;

					}

					sb.append(input, lastpos, i - lastpos);

				} while (i < input.length);



				return sb.toString();

			}

		}

		return new String(input);

	}



	private static boolean isMetachar(char ch) {

		return (ch <= '|' && _category[ch] >= E);

	}



}

