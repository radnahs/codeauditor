/**

 * <p>Project: rytry </p>

 * <p>Package Name: com.webtual.rytry.barclays.checkMarx.solutions </p>

 * <p>File Name: HeapInspection.java </p>

 * <p>Create Date: Jun 11, 2018 </p>

 * <p>Create Time: 5:33:13 PM </p>

 * <p>Description: </p>

 * <p>Copyright: Copyright (c) 2018</p>

 * <p>Company:  </p>

 * @author Shantanu Sikdar

 * @version 1.0

 */



package com.webtual.rytry.barclays.checkMarx.solutions;



import java.io.IOException;

import java.security.InvalidKeyException;

import java.security.Key;

import java.security.NoSuchAlgorithmException;



import javax.crypto.Cipher;

import javax.crypto.IllegalBlockSizeException;

import javax.crypto.KeyGenerator;

import javax.crypto.NoSuchPaddingException;

import javax.crypto.SealedObject;



/**

 * {@link http://www.java2s.com/Code/JavaAPI/javax.crypto/javaxcryptoSealedObject.htm}

 * 

 * <p><b>Heap Inspection </b></p>

 * <pre>

 * <b>Risk</b><br/>

 * <b>What might happen</b> <br/>

 * All variables stored by the application in unencrypted memory can potentially

 * be retrieved by an unauthorized user, with privlieged access to the machine.

 * For example, a privileged attacker could attach a debugger to the running

 * process, or retrieve the process's memory from the swapfile or crash dump

 * file. Once the attacker finds the user passwords in memory, these can be

 * reused to easily impersonate the user to the system.

 * <p>

 * <b>Cause</b><br>

 * <b>How does it happen</b><br>

 * String variables are immutable - in other words, once a string variable is

 * assigned, its value cannot be changed or removed. Thus, these strings may

 * remain around in memory, possibly in multiple locations, for an indefinite

 * period of time until the garbage collector happens to remove it. Sensitive

 * data, such as passwords, will remain exposed in memory as plaintext with no

 * control over their lifetime.

 * </p>

 * 

 * <p>

 * <b>General Recommendations</b> <b>How to avoid it</b> <b>Generic

 * Guidance:</b> o Do not store senstiive data, such as passwords or encryption

 * keys, in memory in plaintext, even for a short period of time. o Prefer to

 * use specialized classes that store encrypted memory. o Alternatively, store

 * secrets temporarily in mutable data types, such as byte arrays, and then

 * promptly zeroize the memory locations. Specific Recommendations - Java: o

 * Instead of storing passwords in immutable strings, prefer to use an encrypted

 * memory object, such as SealedObject.

 * </p>

 * 

 * @author Shantanu Sikdar

 *

 */

public class HeapInspection {



	public String cannotSkipHeap(String password) {

		String unencryptedPassword = null;

		unencryptedPassword = password;

		return unencryptedPassword;

	}



	public String skipHeap(String password) {

		KeyGenerator keyGenerator = null;

		Cipher cipher = null;

		SealedObject so = null;

		String unencryptedPassword = null;

		try {

			keyGenerator = KeyGenerator.getInstance("DESede");

			Key key = keyGenerator.generateKey();

			cipher = Cipher.getInstance("DESede");

			cipher.init(Cipher.ENCRYPT_MODE, key);

			so = new SealedObject(password, cipher);

			unencryptedPassword = (String) so.getObject(key);

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (InvalidKeyException e) {

			e.printStackTrace();

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

		return unencryptedPassword;

	}



	public static void main(String[] args) throws Exception {

		HeapInspection hi = new HeapInspection();



		System.out.println(hi.cannotSkipHeap("shan_tanu@123"));

		System.out.println(hi.skipHeap("shan_tanu@124"));



		NonCompliantHeapInspection hic = new NonCompliantHeapInspection();

		//hic.setPassword(System.console().readLine("Enter your password: "));

		hic.setPassword();

		

		

		/*select {instance: password, content: password.toString()} from

		java.lang.String password where s.count>2 &&

		s.toString().substring(0,2)=="GH"*/

		

		CompliantHeapInspection hisf = new CompliantHeapInspection();

		hisf.setPassword();

		

	}



}



class NonCompliantHeapInspection {

	private String password;



	public void setPassword() {

		password = System.console().readLine("Enter your password: ");

	}

}



class CompliantHeapInspection {



	private SealedObject password;



	Key getKeyFromConfig() {

		KeyGenerator keyGenerator = null;

		Key key = null;

		try {

			keyGenerator = KeyGenerator.getInstance("AES");

			key = keyGenerator.generateKey();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		}

		return key;

	}



	public void setPassword() {

		try {

			Cipher cphr = Cipher.getInstance("AES");

			cphr.init(Cipher.ENCRYPT_MODE, getKeyFromConfig());

			char[] input = System.console().readPassword("Enter your password: "); // readPassword method read the

			password = new SealedObject(input, cphr);

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (InvalidKeyException e) {

			e.printStackTrace();

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}



}






.............................................................................................................................
  
  
  /**

 * <p>Project: rytry </p>

 * <p>Package Name: com.webtual.rytry.barclays.checkMarx.solutions </p>

 * <p>File Name: CleartextSubmissionOfSensitiveInformation.java </p>

 * <p>Create Date: Jul 27, 2018 </p>

 * <p>Create Time: 2:28:14 PM </p>

 * <p>Description: </p>

 * <p>Copyright: Copyright (c) 2018</p>

 * <p>Company:  </p>

 * @author Shantanu Sikdar

 * @version 1.0

 */



package com.webtual.rytry.barclays.checkMarx.solutions;



import java.io.IOException;

import java.io.PrintWriter;

import java.net.ServerSocket;

import java.net.Socket;



import javax.net.ssl.SSLServerSocketFactory;



/**

 * <p>

 * <b>Cleartext Submission of Sensitive Information </b>

 * </p>

 * 

 * <pre>

 * <p>

 * <b>Risk</b><br>

 * <b> What might happen</b><br>

 * Sensitive and personal details, such as passwords, social security numbers,

 * credit card data, and other forms of PII (Personally Identifiable

 * Information), must always be protected when being transmitted over the

 * network. Sending this private data over the unencrypted network, without

 * using SSL/TLS or other form of encryption, can reveal the users' secret

 * information, and expose them to risks of impersonation, identity theft, and

 * financial fraud. If the SSL/TLS channel is terminated on a front-end

 * webserver, reverse proxy, or the like, this issue can be ignored.

 * 

 * <b>Cause </b><br>

 * <b>How does it happen</b><br>

 * The application handles sensitive and private information in several ways. At

 * a certain point, this secret data is sent over the network, however the

 * application does not use SSL/TLS, or any other secure protocol, and does not

 * ensure the data is encrypted before sending it over the unprotected

 * channel.

 * 

 * <b>General Recommendations</b><br>

 * <b>How to avoid it </b><br>

 * <b>Generic Guidance: <br>

 * o Always protect all PII and other sensitive data, especially when it is

 * being sent over the network. <br>

 * o Use SSL/TLS whenever transmitting sensitive data. Alternatively, other

 * encrypted protocols, such as IPsec or SSH, can also be used.<br>

 * o Reconsider if the application absolutely requires these personal details.

 * Data that is not received cannot be exposed. <br>

 * Specific Recommendations: <br>

 * o In web applications, do not output personal info directly to the response

 * without verifying the channel is secured with SSL. <br>

 * o Do not write personal info directly to a standard socket either. Instead,

 * always use SSLSocket to ensure your channel makes use of SSL/TLS.

 * </p>

 * 

 * @author Shantanu Sikdar

 *

 */

public class CleartextSubmissionOfSensitiveInformation {



	public static void main(String[] args) {



	}



}



class MyUser {

	private String accountId;



	public String getAccountId() {

		return accountId;

	}



	public void setAccountId(String accountId) {

		this.accountId = accountId;

	}



}



class NonCompliantCleartextSubmissionofSensitiveInformation {



	public void runServer() {

		int PORT = 8888;

		ServerSocket server = new ServerSocket(PORT);

		Socket client;

		while (true) {

			client = server.accept();

			MyUser user = handleRequest(client);

			PrintWriter output = new PrintWriter(server.getOutputStream());

			// output.println(user.AccountId);

			System.out.println(user.getAccountId());

			output.flush();

		}

	}



	private MyUser handleRequest(Socket client) {

		MyUser user = new MyUser();

		try {

			client.getInputStream();

			// user.setAccountId(client.getInputStream().toString());

		} catch (IOException e) {

			e.printStackTrace();

		}

		return user;

	}



}



class CompliantCleartextSubmissionofSensitiveInformation {

	public void runServer() {

		int PORT = 8888;

		SSLServerSocketFactory factory;

		ServerSocket server;

		Socket client;

		MyUser user;

		PrintWriter output;

		try {

			factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

			server = factory.createServerSocket(PORT);



			while (true) {

				client = server.accept();

				user = handleRequest(client);

				output = new PrintWriter(server.getOutputStream(),true);

				// output.println(user.AccountId);

				System.out.println(user.getAccountId());

				output.flush();

			}

		} catch (IOException ex) {

			// handleException(ex);

			ex.printStackTrace();

		} finally {

			if (output != null)

				output.close();

			if (client != null)

				if (!client.isClosed())

					client.close();

			if (server != null)

				if (!server.isClosed())

					server.close();

		}

	}



	private MyUser handleRequest(Socket client) {

		MyUser user = new MyUser();

		try {

			client.getInputStream();

			// user.setAccountId(client.getInputStream().toString());

		} catch (IOException e) {

			e.printStackTrace();

		}

		return user;

	}

}



.....................................................................................................
  
  /**

 * <p>Project: rytry </p>

 * <p>Package Name: com.webtual.rytry.barclays.checkMarx.solutions </p>

 * <p>File Name: PrivacyViolation.java </p>

 * <p>Create Date: Aug 22, 2018 </p>

 * <p>Create Time: 2:08:41 PM </p>

 * <p>Description: </p>

 * <p>Copyright: Copyright (c) 2018</p>

 * <p>Company:  </p>

 * @author Shantanu Sikdar

 * @version 1.0

 */



package com.webtual.rytry.barclays.checkMarx.solutions;



/**

 * 

 * Privacy Violation Risk What might happen A users personal information could

 * be stolen by a malicious programmer, or an attacker that intercepts the data.

 * Cause How does it happen The application sends user information, such as

 * passwords, account information, or credit card numbers, outside the

 * application, such as writing it to a local text or log file or sending it to

 * an external web service. General Recommendations How to avoid it 1. Personal

 * data should be removed before writing to logs or other files. 2. Review the

 * need and justification of sending personal data to remote web services.

 * 

 * @author Shantanu Sikdar

 *

 */

public class PrivacyViolation {



	public static void main(String[] args) {



	}



}



class NonCompliantPrivacyViolation {



	static void foo(String insert_sql) {

		String password = "unsafe_password";

		insert_sql = insert_sql.replace("$password", password);

		System.out.print(insert_sql);

	}



}



class CompliantPrivacyViolation {



	static void foo(String insert_sql) {

		String password = "unsafe_password";

		

		//MD5 md5Hash = System.Security.Cryptography.MD5.Create();

		

		System.getSecurityManager().

		

		byte[] data = md5Hash.ComputeHash(Encoding.UTF8.GetBytes(password));

		StringBuilder md5Password = new StringBuilder();

		

		for (int i = 0; i < data.length; i++) {

			md5Password.Append(data[i].toString("x2"));

		}

		insert_sql = insert_sql.Replace("$password", md5Password.toString());



		//System.Console.WriteLine(insert_sql);

		System.out.println(insert_sql);

	}



}

................................................................................................................................
  
  
  
  /**

 * <p>Project: rytry </p>

 * <p>Package Name: com.webtual.rytry.barclays.checkMarx.solutions </p>

 * <p>File Name: ReDoSFromRegexInjection.java </p>

 * <p>Create Date: Jul 30, 2018 </p>

 * <p>Create Time: 8:24:02 PM </p>

 * <p>Description: ReDoS From Regex Injection</p>

 * <p>Copyright: Copyright (c) 2018</p>

 * <p>Company:  </p>

 * @author Shantanu Sikdar

 * @version 1.0

 */



package com.webtual.rytry.barclays.checkMarx.solutions;



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

.......................................................................................................................
  
  /**

 * <p>Project: rytry </p>

 * <p>Package Name: com.webtual.rytry.barclays.checkMarx.solutions </p>

 * <p>File Name: SessionFixation.java </p>

 * <p>Create Date: Aug 16, 2018 </p>

 * <p>Create Time: 3:11:23 PM </p>

 * <p>Description: </p>

 * <p>Copyright: Copyright (c) 2018</p>

 * <p>Company:  </p>

 * @author Shantanu Sikdar

 * @version 1.0

 */



package com.webtual.rytry.barclays.checkMarx.solutions;



import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



/**

 * @author Shantanu Sikdar

 *

 */

public class SessionFixation {



	/**

	 * @param args

	 */

	public static void main(String[] args) {

	}



}



class NonCompliantSessionFixation {



	/*	static void foo(String firstName) {

		HttpContext context = HttpContext.Current;

		context.Session["FirstName"] = firstName;

	}*/

}



class CompliantSessionFixation extends WebSecurityConfigurerAdapter {

	

	

	/*static void foo(String firstName, HttpContext old_Context) {

		old_Context.Session.Abandon();

		HttpContext context = HttpContext.Current;

		context.Session["FirstName"] = firstName;

	}*/

	

	//@Configuration

	@Override

	protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement().sessionFixation().newSession();

    }

}

