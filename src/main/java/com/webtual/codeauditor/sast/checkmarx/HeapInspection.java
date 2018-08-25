/**
 * <p>Project: codeauditor </p>
 * <p>Package Name: com.webtual.codeauditor.sast.checkmarx </p>
 * <p>File Name: HeapInspection.java </p>
 * <p>Create Date: Jun 11, 2018 </p>
 * <p>Create Time: 5:33:13 PM </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company:  </p>
 * @author Shantanu Sikdar
 * @version 1.0
 */

package com.webtual.codeauditor.sast.checkmarx;

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
 * <p>
 * <b>Heap Inspection </b>
 * </p>
 * 
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
		// hic.setPassword(System.console().readLine("Enter your password: "));
		hic.setPassword();

		/*
		 * select {instance: password, content: password.toString()} from
		 * java.lang.String password where s.count>2 &&
		 * s.toString().substring(0,2)=="GH"
		 */
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
			char[] input = System.console().readPassword("Enter your password: "); 
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
