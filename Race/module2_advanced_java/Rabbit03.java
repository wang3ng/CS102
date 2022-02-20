package module2_advanced_java;

import java.util.Random;

/**
 * Rabbit03 class used for illustration of object oriented concepts
 * (inheritance, method overloading, dynamic binding).
 * This class represents a rabbit race contender.
 * This class extends the abstract class RaceContender03.
 *
 * @author Joanna Klukowska
 * @version Jan 10, 2014
 *
 */
public  class Rabbit03 extends RaceContender03 {

	//random number generator
	private static Random rand = new Random();

	/**
	 * @param number
	 */
	public Rabbit03(String number) {
		super(number);
	}



	/* (non-Javadoc)
	 * @see lecture01.RaceContender02#move()
	 */
	@Override
	public void move (  ) {
		int whichMove = rand.nextInt(10);
		switch (whichMove) {
		case 0:
		case 1: position = position + 10;   //20% large hop: +10
                break;
		case 2:
		case 3:
		case 4:
		case 5: position = position + 3;    //40% small hop:  +3
                break;
		case 6:
		case 7: position = position - 8;    //20% slips back: -8
		        break;
		case 8:
		case 9: position = position + 0;		//20% no change

		}
	}

}
