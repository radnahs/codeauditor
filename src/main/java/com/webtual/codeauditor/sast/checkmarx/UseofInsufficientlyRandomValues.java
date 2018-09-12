  /**
 * <p>Project: rytry </p>
 * <p>Package Name: com.webtual.codeauditor.sast.checkmarx </p>
 * <p>File Name: UseofInsufficientlyRandomValues.java </p>
 * <p>Create Date: Jul 9, 2018 </p>
 * <p>Create Time: 3:11:44 PM </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company:  </p>
 * @author Shantanu Sikdar
 * @version 1.0
 */


package com.webtual.codeauditor.sast.checkmarx;

import java.security.SecureRandom;

/**
 * 
 * Use of Insufficiently Random Values 
 * 
 * Risk 
 * What might happen 
 * Random values are often used as a mechanism to prevent malicious users from
 * guessing a value, such as a password, encryption key, or session identifier. 
 * Depending on what this random value is used for, an attacker would be able to
 * predict the next numbers generated, or previously generated values. This could 
 * enable the attacker to hijack another user's session, impersonate another user, or crack
 * an encryption key (depending on what the pseudo-random value was used for).
 * 
 * Cause 
 * How does it happen 
 * The application uses a weak method of generating
 * pseudo-random values, such that other numbers could be determined from a
 * relatively small sample size. Since the pseudo-random number generator used
 * is designed for statistically uniform distribution of values, it is
 * approximately deterministic. Thus, after collecting a few generated values
 * (e.g. by creating a few individual sessions, and collecting the sessionids),
 * it would be possible for an attacker to calculate another sessionid.
 * Specifically, if this pseudo-random value is used in any security context,
 * such as passwords, keys, or secret identifiers, an attacker would be able to
 * predict the next numbers generated, or previously generated values.
 *  
 * General Recommendations 
 * How to avoid it 
 * Generic Guidance: 
 * o Whenever unpredicatable numbers are required in a security context, use 
 * a cryptographically strong random number generator, instead of a statistical 
 * pseudo-random generator. 
 * o Use the cryptorandom generator that is built-in to your language or platform,
 * and ensure it is securely seeded. Do not seed the generator with a weak,
 * non-random seed. (In most cases, the default is securely random). 
 * o Ensure you use a long enough random value, to make brute-force attacks unfeasible.
 * 
 * Specific Recommendations: 
 * o Do not use the statistical pseudo-random number generator, use the cryptorandom 
 * generator instead. In Java, this is the SecureRandom class. 
 * 
 * @author Shantanu Sikdar
 *
 */

public class UseofInsufficientlyRandomValues {

	public static void main(String[] args) {

  	CompliantUseofInsufficientlyRandomValues correctedCodeSynopsis = new CompliantUseofInsufficientlyRandomValues();
		String key = String.valueOf(correctedCodeSynopsis.generateRandom1(Integer.MAX_VALUE));

	}
}



class NonCompliantUseofInsufficientlyRandomValues {
	private void existingCodeType1() {
		// String key = String.valueOf((Math.random() * count));
		// String callingRef = ((Double)Math.ceil((Math.random() *
		// FundsTransferConstants.RANDOM_NUMBER_MULTIPLIER))).toString();

	}

}



class CompliantUseofInsufficientlyRandomValues {
	public static int generateRandom1(int maximumValue) {
		SecureRandom ranGen = new SecureRandom();
		return ranGen.nextInt(maximumValue);
	}

	public static int generateRandom2(int maximumValue) {
		byte[] randomBytes = new byte[128];
		SecureRandom random = new SecureRandom();
		random.nextBytes(randomBytes);
		return 0;
	}

}

