package module2_advanced_java;

import java.util.Random;

/**
 * RaceContender01 class used for illustration of object oriented concepts.
 * This class represents a simple race contender. 
 * @author Joanna Klukowska
 * @version Jan 10, 2014 
 *
 */
public class RaceContender01 {
	
	/** Number on the "shirt" of the race contender   */
	protected String number;
	/** Position of the race contender   */
	protected int position;
	
	//random number generator 
	private static Random rand = new Random();
	//size of the largest allowed move 
	public static final int LARGEST_MOVE = 5;
	
	/**
	 * @param number
	 *   number on the "shirt" of the race contender
	 */
	public RaceContender01(String number) {
		this.number = number;
		position = 0;
	}
	
	/**
	 * Update the current position.
	 */
	public void move( ) {
		 //move by a random positive number between 1 and LARGEST_MOVE
		position = position + rand.nextInt(LARGEST_MOVE)+1;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
    */ 
	@Override
	public String toString() {
		return number;
	}
	
}
