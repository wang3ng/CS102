package project4;

/**
 *  The program is not interactive.
 *  It will solve the puzzle array given in command line argument.
 *  As described on https://cs.nyu.edu/~joannakl/cs102_f21/projects/project4.html:
 *  “The puzzle uses an array of positive integers.
 *  The objective is to find a path from index zero to the last index in the array.
 *  At each step we need to know the distance to be traveled and the direction.
 *  Each entry in the array is a number indicating the distance to be traveled on this particular leg of the path.
 *  The player (your program) needs to decide the direction (if the move should be made to the right or to the left).”
 *
 * @author William Wang
 */
public class WayFinder {
    // Counting the total available ways
    private static int AvailableWays = 0;
    private static Stack stack = new Stack();

    // Variable to keep in track if a value has been used in the list, avoiding loop.
    private static boolean[] access;

    /**
     * A simple stack created for storing solutions
     */
    private static class Stack{
        private static String[] Solutions = new String[10]; // The variable storing data
        private static int SolutionsIndex = 0;

        /**
         * Add element to the stack.
         * @param solution elements added
         */
        public static void add(String solution){
            if(SolutionsIndex==Solutions.length){
                String[] New  = new String[SolutionsIndex*2];
                for (int i = 0;i<SolutionsIndex;i++){
                    New[i] = Solutions[i];
                }
                Solutions = New;
            }
            Solutions[SolutionsIndex] = solution;
            SolutionsIndex += 1;
        }

        /**
         * remove the last element
         */
        public static void remove(){
            SolutionsIndex -= 1;
        }

        /**
         * Print the stack
         */
        public static void printSolutions(){
            for (int i=0; i<SolutionsIndex; i++){
                System.out.println(Solutions[i]);
            }
            System.out.println();
        }
    }

    /**
     * The program is not interactive.
     * It will solve the puzzle array given in command line argument.
     * @param arg The command line arguments represent the values for the array. Restrictions:
     *            all values have to be non-negative integers in the range of 0 to 99 inclusive
     *            the last value has to be zero
     *            Example of arg would be:
     *            java WayFinder 3 6 4 1 3 4 2 5 3 0
     */
    public static void main(String[] arg){
        // Check if the argument is valid
        if (arg.length <= 2) {
            System.err.println("Error：The program expect a valid argument\n");
            System.exit(1);
        }
        if (!arg[0].equals("java") || !arg[1].equals("WayFinder")){
            System.err.println("Error：The program expect a valid argument\n");
            System.exit(1);
        }

        // Convert the argument to integer list
        int [] numList = new int[arg.length-2];
        for (int i = 2; i<arg.length;i++){
            try{
                int j = Integer.parseInt(arg[i]);
                if(j<0 || j>99){
                    System.err.println("Error：The program expect a valid argument\n");
                    System.exit(1);
                }
                numList[i-2] = j;
            }
            catch (Exception e){
                System.err.println("Error：The program expect a valid argument\n");
                System.exit(1);
            }
        }

        // Create the access array
        access = new boolean[numList.length];

        //Solving the puzzle
        try{
            findWay(numList);
        }
        catch (Exception e){
            System.err.println("Error：The program expect a valid argument\n");
            System.exit(1);
        }

        if(AvailableWays == 0) System.out.println("No way through this puzzle.");
        else System.out.println(String.format("There are %o ways through the puzzle.", AvailableWays));

    }

    /**
     * A wrapper function for the recursion function. Checking if its a single element array,
     * and if the array contains non zero at the end
     * @param numList the puzzle input
     */
    public static void findWay(int[] numList){
        if(numList[numList.length-1] != 0) return;
        if(numList.length==1){
            AvailableWays = 1;
            System.out.println("[ 0 ]");
        }
        stepForward(0,true,numList);
    }

    /**
     * This is the recursion function that would go through every possible way to solve the puzzle.
     * It will add a step to the solution and removing that step from the stack at the end.
     * This function would call itself to progress through the puzzle.
     * @param index the current index
     * @param direction the direction which the index is pointing to
     * @param numList
     * @return true only when reaching to zero, avoiding wasted recursion.
     */
    public static Boolean stepForward(int index, boolean direction, int[] numList){
        //Check if reaching to a zero
        if(numList[index]==0) {
            if(index == numList.length-1){
                //Print the Solutions stack if the solution is correct
                AvailableWays += 1;
                stack.printSolutions();
            }
            return true;
        }

        //Write a step and add that to the stack
        String Solution = stepString(index,direction,numList);
        Stack.add(Solution);
        access[index] = true;

        // Verify and call the next recursion function.
        int NewIndex = index + numList[index]*(direction?1:-1);
        if(NewIndex < 0 || NewIndex >=numList.length) {
            access[index] = false;
            Stack.remove();
            return false;
        }
        if(access[NewIndex]) {
            access[index] = false;
            Stack.remove();
            return false;
        }
        if(!stepForward(NewIndex,true,numList))stepForward(NewIndex,false,numList);
        access[index] = false;
        Stack.remove();
        return false;
    }

    /**
     * This will generate a string as one step for the solution.
     * @param index
     * @param direction
     * @param numList
     * @return a string as one step for the solution
     */
    public static String stepString(int index, boolean direction, int[] numList){
        String rec = "[";
        for(int i = 0; i<numList.length-1; i++){
            if(index != i) rec = String.format("%s%2d , ",rec,numList[i]);
            else {
                String d = direction ? "R":"L";
                rec = String.format("%s%2d%s, ",rec,numList[i],d);
            }
        }
        rec = rec +  " 0 ]";
        return rec;
    }


}
