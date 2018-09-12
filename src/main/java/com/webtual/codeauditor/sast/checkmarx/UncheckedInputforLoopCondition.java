/**
 * <p>Project: rytry </p>
 * <p>Package Name: com.webtual.rytry.barclays.checkMarx.solutions </p>
 * <p>File Name: UncheckedInputforLoopCondition.java </p>
 * <p>Create Date: Jun 22, 2018 </p>
 * <p>Create Time: 2:54:33 PM </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company:  </p>
 * @author Shantanu Sikdar
 * @version 1.0
 */

package com.webtual.rytry.barclays.checkMarx.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 
 * <p>
 * <b>Unchecked Input for Loop Condition</b>
 * </p>
 * 
 * <pre>
 * <p>
 * <b>Risk</b><br>
 * <b>What might happen</b><br>
 * An attacker could input a very high value, potentially causing a denial of
 * service (DoS).
 * 
 * <b>Cause</b><br>
 * <b>How does it happen</b><br>
 * The application performs some repetitive task in a loop, and defines the
 * number of times to perform the loop according to user input. A very high
 * value could cause the application to get stuck in the loop and to be unable
 * to continue to other operations.
 * 
 * <b>General Recommendations</b><br>
 * <b>How to avoid it </b><br>
 * Ideally, dont base a loop on user-provided data. If it is necessary to do
 * so, the user input must be first validated and its range should be limited.
 * 
 * <b>Java</b><br> 
 * If absolutely necessary to allow the user to define the
 * parameters of a loop, ensure the input is validated and constrained.
 * 
 * 
 * </p>
 * 
 * @author Shantanu Sikdar  
 *
 */

public class UncheckedInputforLoopCondition {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		UncheckedInputforLoopCondition uiflc = new UncheckedInputforLoopCondition();
		List<Integer> lstIntr = new ArrayList<Integer>();
		List<Integer> lstIntr1 = new CopyOnWriteArrayList<>();
		lstIntr.add(1);
		lstIntr.add(2);
		lstIntr.add(3);
		lstIntr.add(4);
		lstIntr.add(5);

		// lstIntr1=new CopyOnWriteArrayList(lstIntr);

		uiflc.existingCode(lstIntr);
		for (Integer intgr : lstIntr) {

			System.out.println(intgr);

			if (intgr == 2)

				lstIntr.add(3);

			System.out.println(intgr);

		}

		/*

		 * Iterator<Integer> itr = lstIntr.listIterator(); while(itr.hasNext()){

		 * System.out.println(itr.next()); if(itr.next()==2) itr.add(3);

		 * System.out.println(itr.next()); }

		 */



	}

	void fixedCode() {

	}
 void existingCode(List<Integer> lstIntr) {
		for (Integer intgr : lstIntr) {
			System.out.println(intgr);
			if (intgr == 2)
				lstIntr.add(3);
			System.out.println(intgr);
		}
	}
}



class NonCompliantUncheckedInputforLoopCondition {

	private void whileLoopCondition(){
		int count = 0;
        while ((count = input.read(buf)) >= 0) {
            output.write(buf, 0, count);
        }
	}
}

class CompliantUncheckedInputforLoopCondition {

}

