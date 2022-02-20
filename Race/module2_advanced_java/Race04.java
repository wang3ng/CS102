package module2_advanced_java;

import java.util.Arrays;
import java.util.Random;

/**
 * Race04 class used for demonstration of object oriented concepts.
 * This class simulates running race between an array of race contenders. 
 * This class provides main() method. 
 * @author Joanna Klukowska
 * @version Jan 10, 2014 
 *
 */
public class Race04 {
	
	/** Position of the finish line   */
	public static final int FINISH_LINE = 70;
	/** Number of lines to be cleared in order to clear the screen in the terminal window. */
	public static final int SCREEN_HEIGHT = 60;
	
	public static void main ( String [] args ) {
		
		Random rand = new Random(); 
		
		//create an array of race contenders
		int numOfRacers = 12; //ensure that numOfRacers is a multiple of 3
		RaceContender04 [] racers  =  new RaceContender04 [numOfRacers];
		for (int i = 0; i < racers.length; i=i+3 )
		{
			racers[i] = new Rabbit04("R" + (rand.nextInt(89)+10) );
			racers[i+1] = new Duck04("D" + (rand.nextInt(89)+10) );
			racers[i+2] = new Snail04("S" + (rand.nextInt(89)+10) );
		}
		RaceContender04 winner = null;
		
		//indicates if the race is over or not
		boolean isOver = false;
		
		//print the initial positions
		clearScreen();
		for (int i = 0; i < racers.length; i++ )
		{
			printPosition( racers[i].getPosition(), racers[i].getNumber() );
		}
		
		while ( !isOver )
		{	//after every step wait 500 milliseconds 
			try{Thread.sleep(1000);}
			catch(InterruptedException e){}
			
			//move the contenders and print their updated positions
			clearScreen();
			for (int i = 0; i < racers.length; i++ )
			{
				racers[i].move();
				printPosition( racers[i].getPosition(), racers[i].getNumber() );
			}
			//display information about who is leading the race 
			System.out.printf("\nThe leader is %3s.\n", findLeader(racers) );
			
			//test if there is a winner
			for (int i = 0; i < racers.length; i++ )
			{
				if (racers[i].getPosition() >= FINISH_LINE ){
					winner = racers[i];
					isOver = true;
					break;
				}
			}
		}
		//after race is over wait a moment before announcing the winner 
		try{Thread.sleep(1000);}
		catch(InterruptedException e){}
		
		//sort the array of racers by their final positions:
		Arrays.sort(racers);
		
		//announce the winner and the end of the race
		clearScreen();
		System.out.printf(
				"            %s is the winner of today's race! \n\n\n" +
		        "           The order of all the contenders is: \n" + 
				"%s \n\n" +
		        "Thank you for joining us for another exciting competition.\n" +
				"            Goodbye and see you again soon. \n\n\n", 
				winner, Arrays.toString(racers) );
	
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
	
	/**
	 * Determines which of the race contenders is in the lead. 	 
	 * @param r
	 *    an array of race contenders
	 * @return
	 *    returns reference to the race contender currently in the lead
	 */
	public static RaceContender04 findLeader ( RaceContender04 [] r ) {
		RaceContender04 leader = r[0];
		for (int i = 1; i < r.length; i++ ) {
			if ( leader.compareTo(r[i]) > 0 )
				leader = r[i];
		}
		return leader;
	}

}
