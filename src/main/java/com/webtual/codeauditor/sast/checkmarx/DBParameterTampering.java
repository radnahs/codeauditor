  /**
 * <p>Project: rytry </p>
 * <p>Package Name: com.webtual.codeauditor.sast.checkmarx </p>
 * <p>File Name: DBParameterTampering.java </p>
 * <p>Create Date: Aug 28, 2018 </p>
 * <p>Create Time: 2:12:26 PM </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company:  </p>
 * @author Shantanu Sikdar
 * @version 1.0
 */

package com.webtual.codeauditor.sast.checkmarx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpRequest;

/**
 * 
 * DB Parameter Tampering
 *  
 * Risk 
 * What might happen 
 * A malicious user could access other users personal information, by simply 
 * altering the reference parameter sent to the server. Thus, the malicious 
 * user could bypass access controls and access unauthorized records, such as 
 * other users accounts, stealing confidential or restricted information.
 * 
 * Cause 
 * How does it happen 
 * The application accesses user information without
 * filtering by user ID. For example, it may provide information solely by a
 * submitted account ID. The application uses the user input to filter specific
 * records from database tables, which contain sensitive personal information
 * (e.g. user accounts or payment details). Since the application does not

 * filter the records according to any user identifier, nor constrain it to a

 * pre-computed list of acceptable values, a malicious user can easily modify

 * the submitted reference identifier, and thus access unauthorized records.

 * 

 * General Recommendations 

 * How to avoid it 

 * Generic Guidance: 

 * o Enforce authorization checks before providing any access to sensitive data, 

 * including the specific object reference. 

 * o Explicitly block access to any unauthorized data, especially to other users 

 * data. 

 * o If possible, avoid allowing the user to request arbitrary data by simply 

 * sending a record ID. For example, instead of having the user send an account 

 * ID, the application should look up the account ID for the current authenticated 

 * user session.

 * 

 * Specific Mitigation: 

 * o Filter the database query according to a user-specific identifier, such as 
 * the customer number. 
 * o Map the user input to an indirect reference, e.g. via a prepared list of 
 * allowable values.
 * 
 * @author Shantanu Sikdar
 *
 *
 */

public class DBParameterTampering {

	public static void main(String[] args) {

	}

}

class NonCompliantDBParameterTampering {

	/**
	 * Unfiltered Direct Object Reference
	 * @param req
	 * @return
	 */
	public ResultSet getAccountInfo(HttpServletRequest req) {
		Connection connection = DriverManager.getConnection("", user, password);
		int accountId = Integer.parseInt(req.getParameter("accountId"));
		PreparedStatement stmt = connection.prepareStatement("SELECT * from Accounts where AccountId = ?");
		stmt.setInt(1, accountId);
		ResultSet accountRS = stmt.executeQuery();
		return accountRS;
	}

}



class CompliantDBParameterTampering {


}
