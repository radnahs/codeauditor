/**
* <p>Project: codeauditor </p>
* <p>Package Name: com.webtual.codeauditor.sast.checkmarx </p>
* <p>File Name: CleartextSubmissionOfSensitiveInformation.java </p>
* <p>Create Date: Jul 27, 2018 </p>
* <p>Create Time: 2:28:14 PM </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2018</p>
* <p>Company:  </p>
* @author Shantanu Sikdar
* @version 1.0
*/

package com.webtual.codeauditor.sast.checkmarx;

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
 * ensure the data is encrypted before sending it over the unprotected channel.
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
				output = new PrintWriter(server.getOutputStream(), true);
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
