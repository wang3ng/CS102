package module2_advanced_java;

/**
 * Race01 class used for demonstration of object oriented concepts.
 * This class simulates running race between four race contenders. 
 * This class provides main() method. 
 * @author Joanna Klukowska
 * @version Jan 10, 2014 
 *
 */
public class Race02 {
	
	/** Position of the finish line   */
	public static final int FINISH_LINE = 70;
	/** Number of lines to be cleared in order to clear the screen in the terminal window. */
	public static final int SCREEN_HEIGHT = 60;
	
	public static void main ( String [] args ) {
		
		//create four race contenders 
		Rabbit02 r1 = new Rabbit02("R34");
		Duck02 r2 = new Duck02("D51");
		Snail02 r3 = new Snail02("S73");
		RaceContender02 r4 = new RaceContender02("125");
		RaceContender02 winner = null;
		
		//indicates if the race is over or not
		boolean isOver = false;
		
		//print the initial positions
		clearScreen();
		printPosition( r1.getPosition(), r1.getNumber() );
		printPosition( r2.getPosition(), r2.getNumber() );
		printPosition( r3.getPosition(), r3.getNumber() );
		printPosition( r4.getPosition(), r4.getNumber() );
		
		while ( !isOver )
		{	//after every step wait 500 milliseconds 
			try{Thread.sleep(500);}
			catch(InterruptedException e){}
			
			//move the contenders and print their updated positions
			r1.move();
			r2.move();
			r3.move();
			r4.move();
			clearScreen();
			printPosition( r1.getPosition(), r1.getNumber() );
			printPosition( r2.getPosition(), r2.getNumber() );
			printPosition( r3.getPosition(), r3.getNumber() );
			printPosition( r4.getPosition(), r4.getNumber() );
			
			//test if there is a winner
			if (r1.getPosition() >= FINISH_LINE ) {
				winner = r1;
				isOver = true;
			}
			else if (r2.getPosition() >= FINISH_LINE ) {
				winner = r2;
				isOver = true ;
			}
			else if (r3.getPosition() >= FINISH_LINE ) {
				winner = r3;
				isOver = true ;
			}
			else if (r4.getPosition() >= FINISH_LINE ) {
				winner = r4;
				isOver = true ;
			}
		}
		//after race is over wait a moment before announcing the winner 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){}
		
		//announce the winner and the end of the race
		clearScreen();
		System.out.printf(
				"            %s is the winner of today's race! \n\n\n" +
		        "Thank you for joining us for another exciting competition.\n" +
				"            Goodbye and see you again soon. \n\n\n", 
				winner );
	
	}
	
	/**
	 * Clears the "screen" of the terminal by printing SCREEN_HEIGHT number 
	 * of blank lines.
	 */
	public static void clearScreen( ) {
		for (int i = 0; i < SCREEN_HEIGHT; i++ )		{
			System.out.println(" ");
		}
	}
	
	/**
	 * Prints the position of a race contender.
	 * @param position 
	 *    Current position of the contender.
	 * @param number
	 *    Number of the current contender. 
	 */
	public static void printPosition( int position, String number ) {
		int i;
		for (i = 0; i < position; i++ ) {
			System.out.print(" ");
		}
		System.out.print(number);
		
		if (i < FINISH_LINE) {
			for ( ; i < FINISH_LINE; i++ )
				System.out.print(" ");
			System.out.print("|\n");
		}
		else
			System.out.print("\n");
	}

}
