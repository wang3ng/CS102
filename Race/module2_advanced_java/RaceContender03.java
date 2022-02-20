package module2_advanced_java;


/**
 * RaceContender03 class used for illustration of object oriented concepts.
 * This class represents a simple race contender.
 * This is an abstract class with abstract move() method.
 *
 * @author Joanna Klukowska
 * @version Jan 10, 2014
 *
 */
abstract public class RaceContender03 {

	/* Number on the "shirt" of the race contender   */
	protected String number;
	/* Position of the race contender   */
	protected int position;

	/**size of the largest allowed move */
	public static final int LARGEST_MOVE = 5;

	/**
	 * @param number
	 *   number on the "shirt" of the race contender
	 */
	public RaceContender03(String number) {
		this.number = number;
		position = 0;
	}

	/**
	 * Update the current position.
	 */
	abstract public void move( );

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
