/**
 * <p>Project: rytry </p>
 * <p>Package Name: com.webtual.rytry.barclays.checkMarx.solutions </p>
 * <p>File Name: PrivacyViolation.java </p>
 * <p>Create Date: Jul 21, 2018 </p>
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
  
 * Privacy Violation 
 * Risk 
 * What might happen 
 * A users personal information could be stolen by a malicious programmer, 
 * or an attacker that intercepts the data.
 * 
 * Cause 
 * How does it happen 
 * The application sends user information, such as passwords, account 
 * information, or credit card numbers, outside the application, such 
 * as writing it to a local text or log file or sending it to an external 
 * web service. 
 * 
 * General Recommendations 
 * How to avoid it 
 * 1. Personal data should be removed before writing to logs or other files.
 * 2. Review the need and justification of sending personal data to remote 
 * web services.
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
  	MD5 md5Hash = System.Security.Cryptography.MD5.Create();
		System.getSecurityManager().
		byte[] data = md5Hash.ComputeHash(Encoding.UTF8.GetBytes(password));
		StringBuilder md5Password = new StringBuilder();
  	for (int i = 0; i < data.length; i++) {
			md5Password.Append(data[i].toString("x2"));
		}
		insert_sql = insert_sql.Replace("$password", md5Password.toString());
		System.out.println(insert_sql);

	}

}
