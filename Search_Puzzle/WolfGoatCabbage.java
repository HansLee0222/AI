/*
Hans Lee V00847557
Runming Gao V00872823
Assignment 1
*/
import java.util.HashSet;
import java. util.Set;
import java.io.*;
public class WolfGoatCabbage extends Problem{
  static final int boat = 0;
  static final int cabbage =1;
  static final int goat = 2;
  static final int wolf =3;
    boolean goal_test(Object state){
    StateFWCG r_state = (StateFWCG) state;
    if (r_state.FWCGArray[wolf] == 0&&r_state.FWCGArray[boat] == 0&&r_state.FWCGArray[goat] == 0&&r_state.FWCGArray[cabbage] == 0)
        return true;
    else return false;
    }
    Set<Object> getSuccessors(Object state){
      Set<Object> set = new HashSet<Object>();
      StateFWCG r_state = (StateFWCG) state;
      StateFWCG successor_state;

      //cabbage from left to right
      successor_state = new StateFWCG(r_state);
      successor_state.FWCGArray[cabbage]--;
      successor_state.FWCGArray[boat]--;
      if(isValid(successor_state)) set.add(successor_state);
      //cabbage from right to left
      successor_state = new StateFWCG(r_state);
      successor_state.FWCGArray[cabbage]++;
      successor_state.FWCGArray[boat]++;
      if(isValid(successor_state)) set.add(successor_state);
      //wolf from left to right
      successor_state = new StateFWCG(r_state);
      successor_state.FWCGArray[wolf]--;
      successor_state.FWCGArray[boat]--;
      if(isValid(successor_state)) set.add(successor_state);
      //wolf from right to left
      successor_state = new StateFWCG(r_state);
      successor_state.FWCGArray[wolf]++;
      successor_state.FWCGArray[boat]++;
      if(isValid(successor_state)) set.add(successor_state);
      //goat from left to right
      successor_state = new StateFWCG(r_state);
      successor_state.FWCGArray[goat]--;
      successor_state.FWCGArray[boat]--;
      if(isValid(successor_state)) set.add(successor_state);
      //goat from right to left
      successor_state = new StateFWCG(r_state);
      successor_state.FWCGArray[goat]++;
      successor_state.FWCGArray[boat]++;
      if(isValid(successor_state)) set.add(successor_state);
      //only boat moves left to right
      successor_state = new StateFWCG(r_state);
      successor_state.FWCGArray[boat]++;
      if(isValid(successor_state)) set.add(successor_state);
      //only boat moves right to left
      successor_state = new StateFWCG(r_state);
      successor_state.FWCGArray[boat]--;
      if(isValid(successor_state)) set.add(successor_state);

      return set;




    }
    private boolean isValid(StateFWCG state){
      for(int i = 0; i <4; i++){
        if(state.FWCGArray[i] <0 || state.FWCGArray[i] >1) return false;
      }
      if(state.FWCGArray[boat] == 1 &&state.FWCGArray[cabbage] == 1 &&state.FWCGArray[goat] == 0&& state.FWCGArray[wolf]==0){
        return false;
      }
      if(state.FWCGArray[boat] == 0 &&state.FWCGArray[cabbage] == 1 &&state.FWCGArray[goat] == 1&& state.FWCGArray[wolf]==0){
        return false;
      }
      if(state.FWCGArray[boat] == 1 &&state.FWCGArray[cabbage] == 0 &&state.FWCGArray[goat] == 0&& state.FWCGArray[wolf]==1){
        return false;
      }
      if(state.FWCGArray[boat] == 0 &&state.FWCGArray[cabbage] == 1 &&state.FWCGArray[goat] == 1&& state.FWCGArray[wolf]==1){
        return false;
      }
      if(state.FWCGArray[boat] == 0 &&state.FWCGArray[cabbage] == 0 &&state.FWCGArray[goat] == 1&& state.FWCGArray[wolf]==1){
        return false;
      }
      return true;

    }
    public double h (Object state){
      StateFWCG r_state = (StateFWCG) state;
      return 2*(r_state.FWCGArray[cabbage] +r_state.FWCGArray[goat] + r_state.FWCGArray[wolf])-1;
    }
    double step_cost(Object fromState, Object toState){return 1;}


    public static void main(String[] args) throws Exception {
        WolfGoatCabbage problem = new WolfGoatCabbage();
        int[] newArray = {1,1,1,1};
		    problem.initialState = new StateFWCG(newArray);
        PrintStream p = new PrintStream(new File("FWCG.txt"));
		    Search search  = new Search(problem);
        System.setOut(p);
        System.out.println("Hans Lee V00847557, Runming Gao V00872823");

        System.setOut(p);
        System.out.println("\nQ2.\t : ==========\n");
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
