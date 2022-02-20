package module2_advanced_java;

import java.util.Random;

/**
 * Duck02 class used for illustration of object oriented concepts
 * (inheritance, method overloading, dynamic binding).
 * This class represents a duck race contender. 
 * This class extends the abstract class RaceContender04.
 * 
 * @author Joanna Klukowska
 * @version Jan 10, 2014 
 *
 */
public class Duck04 extends RaceContender04 {
	
	//random number generator 
	private static Random rand = new Random();
		
	/**
	 * @param number
	 */
	public Duck04(String number) {
		super(number);
	}
	
	/* (non-Javadoc)
	 * @see lecture01.RaceContender02#move()
	 */
	//@Override
	public void move () {
		int whichMove = rand.nextInt(10);
		switch (whichMove) {
		case 0: 
		case 1: 
		case 2: position = position + 7;    //30% short flight: +7
            break;
		case 3: 
		case 4:
		case 5: 
		case 6: position = position + 2;    //40% duck walk:  +2
                break;
		case 7: position = position - 2;    //20% slips back: -2
		        break;
		case 8:
		case 9: position = position + 0;		//20% no change
		
		}
	}

}
