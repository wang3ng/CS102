package module2_advanced_java;


/**
 * RaceContender04 class used for illustration of object oriented concepts.
 * This class represents a simple race contender.
 * This is an abstract class with abstract move() method.
 * This class implements Comparable interface.
 * @author Joanna Klukowska
 * @version Jan 10, 2014
 *
 */
abstract public class RaceContender04 implements Comparable<RaceContender04>{

	/** Number on the "shirt" of the race contender   */
	protected String number;
	/** Position of the race contender   */
	protected int position;

	//size of the largest allowed move
	public static final int LARGEST_MOVE = 5;

	/**
	 * @param number
	 *   number on the "shirt" of the race contender
	 */
	public RaceContender04(String number) {
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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(RaceContender04 r) {
		if (this.position < r.position )
			return 1;
		else if (this.position > r.position )
			return -1;
		else
			return 0;
	}



}
