/**
 * <p>Project: rytry </p>
 * <p>Package Name: com.webtual.codeauditor.sast.checkmarx </p>
 * <p>File Name: DeserializationOfUntrustedData.java </p>
 * <p>Create Date: Aug 30, 2018 </p>
 * <p>Create Time: 2:25:27 PM </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company:  </p>
 * @author Shantanu Sikdar
 * @version 1.0
 */

package com.webtual.codeauditor.sast.checkmarx;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

/**
 * Deserialization of Untrusted Data
 *
 * Risk What might happen Deserialization of untrusted data may allow attackers
 * to craft and provide a malicious object to the deserializing code. If a
 * dangerous object is unsecurely deserialized, it may result in execution of
 * code or operating system commands, by invoking classes or methods potentially
 * available to the object during the deserialization process. In Java,
 * deserialized classes may often borrow classes from the general namespace,
 * allowing compound deserialized objects ("Gadgets") to deserialize and invoke
 * dangerous built in types, such as java.lang.Runtime, which is capable of
 * executing operating system commands.
 * 
 * Cause How does it happen Object serialization and deserialization is integral
 * to the process of remoting, wherein objects are passed between code instances
 * over an intermediary medium, such as over a network. During deserialization,
 * a new object is constructed from a serialized object provided over the
 * medium; however, if the object being deserialized is untrusted, an unexpected
 * and potentially dangerous object can be provided. If a dangerous object is
 * unsecurely deserialized, it may contain unexpected objects that, when
 * deserialized, may result in execution of code or operating system commands.
 * 
 * General Recommendations How to avoid it · Where possible, do not pass
 * serialized object between remote instances. Instead, consider passing value
 * primitives between instances and use these values to populate a newly
 * constructed object. · If required, use a whitelist approach to passed
 * objects. Always ensure the passed object is known, trusted and expected. Do
 * not dynamically construct object from any source unless the object has been
 * verified and is of a trusted, known type, and may not contain untrusted
 * objects within it.
 * 
 * 
 * @author Shantanu Sikdar
 *
 */

public class DeserializationOfUntrustedData {

	public static void main(String[] args) {

  }
  
}

class NonCompliantDeserializationOfUntrustedData {

	// Reading an Object from ServletRequest Input Stream

	public void readingObject(ServletRequest request) {
		try {
  		ServletInputStream sis = request.getInputStream();
			ObjectInputStream oins = new ObjectInputStream(sis);
			Object obj = oins.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



class CompliantDeserializationOfUntrustedData {

  class SafeObjectInputStream extends ObjectInputStream {

		protected SafeObjectInputStream(InputStream in) throws IOException, SecurityException {
			super();
		}

		@Override
		protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
			if (!desc.getName().equals(TrustedObject.class.getName())) {
				throw new ClassNotFoundException("Unsupported Class: "+ desc.getName());
			}
			return super.resolveClass(desc);
		}
	}

public void readingObject(ServletRequest request) {
		try {
			ServletInputStream sis = request.getInputStream();
  		ObjectInputStream oins = new SafeObjectInputStream(sis);
			Object obj = oins.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
  		e.printStackTrace();
		}
	}

}

