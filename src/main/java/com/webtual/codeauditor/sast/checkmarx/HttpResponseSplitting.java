/**
 * <p>Project: codeauditor </p>
 * <p>Package Name: com.webtual.codeauditor.sast.checkmarx </p>
 * <p>File Name: HttpResponseSplitting.java </p>
 * <p>Create Date: Aug 27, 2018 </p>
 * <p>Create Time: 12:49:02 PM </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company:  </p>
 * @author Shantanu Sikdar
 * @version 1.0		
 */

package com.webtual.codeauditor.sast.checkmarx;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * Risk 
 * What might happen                                                             
 * If the header setting code is of a vulnerable version, an attacker could:     
 * o Arbitrarily change the application servers response header to a victims   
 * HTTP request by manipulating headers                                          
 * o Arbitrarily change the application servers response body by injecting two  
 * consecutive line breaks, which may result in Cross-Site Scripting (XSS) attacks           
 * o Cause cache poisoning, potentially controlling any sites HTTP responses going      

 * through the same proxy as this application. 
 * 
 * Cause 
 * How does it happen 
 * Since user input is being used in an HTTP response header, an attacker could        
 * include NewLine characters to make the header look like multiple headers with 
 * engineered content, potentially making the response look like multiple        
 * responses (for example, by engineering duplicate content-length headers).     
 * This can cause an organizational proxy server to provide the second,          
 * engineered response to a victims subsequent request; or, if the proxy server 
 * also performs response caching, the attacker can send an immediate subsequent 
 * request to another site, causing the proxy server to cache the engineered     
 * response as a response from this second site and to later serve the response  
 * to other users. 
 * Many modern web frameworks mitigate this issue, by offering sanitization for 
 * new line characters in strings inserted into headers by default. However, since 
 * many older versions of web frameworks fail to automatically mitigate this issue, 
 * manual sanitization of input may be required. 
 * 
 * General Recommendations 
 * How to avoid it 
 * 1. Validate all input, regardless of source (including cookies). Validation 
 * should be based on a whitelist: accept only data fitting a specified structure, 
 * rather than reject bad patterns. Check for: 
 * o Data type 
 * o Size 
 * o Range 
 * o Format 
 * o Expected values 
 * 2. Additionally, remove or URL-encode all special (non-alphanumeric) user input 
 * before including it in the response header.                        
 * 
 * 
 * 
 * @author Shantanu Sikdar
 *
 */
	
public class HttpResponseSplitting {

	public static void main(String[] args) {
		CompliantHTTPResponseSplitting compliantHTTPResponseSplitting = new CompliantHTTPResponseSplitting();
		String employee = "Anthony\nGonzalves<script></script>";
		String encode = compliantHTTPResponseSplitting.removeNewLineAndEncodeResponse(employee);
		System.out.println(employee+" after encode == "+encode);
		String decode = compliantHTTPResponseSplitting.decodeResponse(employee);
		System.out.println(encode +" after decode == "+decode);
	}
}

class NonCompliantHTTPResponseSplitting {

	public void vulnerableCodeInMethod(HttpServletRequest req, HttpServletResponse res){
		String employee = req.getParameter("employee");
		res.addHeader("employee", employee);
	}

}

class CompliantHTTPResponseSplitting {
	
	public void resilientCodeInMethod(HttpServletRequest request, HttpServletResponse response) {
		String employee = request.getParameter("employee");
		String sanitizeEmployee = removeNewLineAndEncodeResponse(employee);
		response.addHeader("employee", sanitizeEmployee);
	}


	/**
	 * Removing the newline and URL-encode all special (non-alphanumeric) user input 
	 * before including it in the response header.
	 * @param employee
	 * @return
	 */
	public String removeNewLineAndEncodeResponse(String employee){
		String sanitizeVal=null;
		if (!employee.contains("\n")) {
			try{
				sanitizeVal = URLEncoder.encode(employee,"UTF-8");
				System.out.println("sanitizeVal ===  "+sanitizeVal);
			}catch(UnsupportedEncodingException uee){
				uee.printStackTrace();
			}
		}
		return sanitizeVal;
	}
	

	public String decodeResponse(String employee){
		String sanitizeVal=null;
		try {
			sanitizeVal = URLDecoder.decode(employee,"UTF-8");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		return sanitizeVal;
	}	

}

