/*
Hans Lee V00847557
Runming Gao V00872823
Assignment 1
*/
import java.util.HashSet;
import java.util.Set;
import java.io.*;
public class ProblemPancake extends Problem{

    //static
    //initial state??
    static final int first = 0;
    static final int second = 1;
    static final int third = 2;
    static final int fourth = 3;
    static final int fifth = 4;
    static final int sixth = 5;

    boolean goal_test(Object state){

        StatePancake pan_state = (StatePancake) state;

        if(pan_state.pancakeArray[first] < pan_state.pancakeArray[second] &&
        pan_state.pancakeArray[second] < pan_state.pancakeArray[third] &&
        pan_state.pancakeArray[third] < pan_state.pancakeArray[fourth] &&
        pan_state.pancakeArray[fourth] < pan_state.pancakeArray[fifth] &&
        pan_state.pancakeArray[fifth] < pan_state.pancakeArray[sixth] )
            return true;

        else
            return false;

    }

    Set<Object> getSuccessors(Object state){


	    Set<Object> set = new HashSet<Object>();

        StatePancake pan_state = (StatePancake) state;
        StatePancake successor_state;

        //flip between the top and the second to the top
        successor_state = new StatePancake(pan_state);

        successor_state.pancakeArray[first] = successor_state.pancakeArray[first];
        if(check(successor_state))
            set.add(successor_state);

        //flip between the second to the top and the third to the top
        successor_state = new StatePancake(pan_state);
        int temp1 = successor_state.pancakeArray[first];
        successor_state.pancakeArray[first] = successor_state.pancakeArray[second];
        successor_state.pancakeArray[second] = temp1;
        if(check(successor_state))
            set.add(successor_state);

        //flip between the third to the top and the fourth to the top
        successor_state = new StatePancake(pan_state);
        int temp11 = successor_state.pancakeArray[first];

        successor_state.pancakeArray[first] = successor_state.pancakeArray[third];
        successor_state.pancakeArray[third] = temp11;
        if(check(successor_state))
            set.add(successor_state);

         //flip between the fourth to the top and the fifth to the top
        successor_state = new StatePancake(pan_state);
        int temp21 = successor_state.pancakeArray[first];
        int temp22 = successor_state.pancakeArray[second];
        successor_state.pancakeArray[first] = successor_state.pancakeArray[fourth];
        successor_state.pancakeArray[fourth] = temp21;
        successor_state.pancakeArray[second] = successor_state.pancakeArray[third];
        successor_state.pancakeArray[third] = temp22;
        if(check(successor_state))
            set.add(successor_state);

         //flip between the fifth to the top and the sixth to the top
        successor_state = new StatePancake(pan_state);
        int temp31 = successor_state.pancakeArray[first];
        int temp32 = successor_state.pancakeArray[second];
        successor_state.pancakeArray[first] = successor_state.pancakeArray[fifth];
        successor_state.pancakeArray[fifth] = temp21;
        successor_state.pancakeArray[second] = successor_state.pancakeArray[fourth];
        successor_state.pancakeArray[fourth] = temp22;
        if(check(successor_state))
            set.add(successor_state);

         //flip under all pancakes
        successor_state = new StatePancake(pan_state);
        int temp41 = successor_state.pancakeArray[first];
        int temp42 = successor_state.pancakeArray[second];
        int temp43 = successor_state.pancakeArray[third];
        successor_state.pancakeArray[first] = successor_state.pancakeArray[sixth];
        successor_state.pancakeArray[second] = successor_state.pancakeArray[fifth];
        successor_state.pancakeArray[third] = successor_state.pancakeArray[fourth];
        successor_state.pancakeArray[fourth] = temp43;
        successor_state.pancakeArray[fifth] = temp42;
        successor_state.pancakeArray[sixth] = temp41;
        if(check(successor_state))
            set.add(successor_state);

        return set;


    }


    private boolean check(StatePancake state){



        return true;

    }



	double step_cost(Object fromState, Object toState) {
		return 1;
	}

	public double h(Object state) {
    StatePancake pan_state = (StatePancake) state;
    int count = 0;
    for (int i = 0; i<pan_state.pancakeArray.length-1;i++){
        if(pan_state.pancakeArray[i]!=pan_state.pancakeArray[i+1] +1){
          count++;
      }
        else if (pan_state.pancakeArray[i]!=pan_state.pancakeArray[i+1] -1){
          count++;
        }
        else{
          count = count;
        }


    }
  //  System.out.print(count);
    return count;

    }


    public static void main(String[] args) throws Exception {


        ProblemPancake problem = new ProblemPancake();
        int[] pancakeArray = {1,0,3,5,2,4};
        problem.initialState = new StatePancake(pancakeArray);
        PrintStream p = new PrintStream(new File("Pancake.txt"));
        Search search  = new Search(problem);
        System.setOut(p);
        System.out.println("Hans Lee V00847557, Runming Gao V00872823 : ");
        System.setOut(p);
        System.out.println("\nQ5.\t : ==========\n");
        System.setOut(p);
        System.out.println("\nGreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
        System.setOut(p);
        System.out.println("\nGreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());
System.setOut(p);
        System.out.println("\nAstarTreeSearch:\t" + search.AstarTreeSearch());
        System.setOut(p);
        System.out.println("\nAstarGraphSearch:\t" + search.AstarGraphSearch());
System.setOut(p);
        System.out.println("\nBreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
        System.setOut(p);
        System.out.println("\nBreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
System.setOut(p);
        System.out.println("\nDepthFirstTreeSearch:\t" + search.DepthFirstTreeSearch());
        System.setOut(p);
        System.out.println("\nDepthFirstGraphSearch:\t" + search.DepthFirstGraphSearch());
        System.setOut(p);

        System.out.println("\nUniformCostTreeSearch:\t" + search.UniformCostTreeSearch());
        System.setOut(p);
        System.out.println("\nUniformCostGraphSearch:\t" + search.UniformCostGraphSearch());
System.setOut(p);
        System.out.println("\nIterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        System.setOut(p);
        System.out.println("\nIterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());





    }


}
