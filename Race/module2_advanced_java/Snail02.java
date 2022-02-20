package module2_advanced_java;

import java.util.Random;

/**
 * Snail02 class used for illustration of object oriented concepts
 * (inheritance, method overloading, dynamic binding).
 * This class represents a snail race contender whose moves are different than
 * the simple race contender's moves.  
 * @author Joanna Klukowska
 * @version Jan 10, 2014 
 *
 */
public class Snail02 extends RaceContender02 {
	
	//random number generator 
	private static Random rand = new Random();
		
	/**
	 * @param number
	 */
	public Snail02(String number) {
		super(number);
	}
	
	/* (non-Javadoc)
	 * @see lecture01.RaceContender02#move()
	 */
	@Override
	public void move ( ) {
		int whichMove = rand.nextInt(10);
		switch (whichMove) {
		case 0: 
		case 1: 
		case 2: position = position + 5;   //30% long slide: +5
                break;
		case 3: 
		case 4:
		case 5: 
		case 6: 
		case 7: position = position + 2;    //50% short slide:  +2
                break;
		case 8: position = position - 4;    //10% slips back: -4
		        break;
		case 9: position = position + 0;	//10% no change
		
		}
	}

}
