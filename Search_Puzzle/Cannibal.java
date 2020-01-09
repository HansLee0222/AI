/*
Hans Lee V00847557
Runming Gao V00872823
Assignment 1
*/
import java.util.HashSet;
import java. util.Set;
import java.io.*;
public class Cannibal extends Problem{
  static final int CR= 0;
  static final int MR =1;
  static final int BR = 2;
  static final int CL =3;
  static final int ML = 4;
  static final int BL = 5;
  static final int N = 6;
    boolean goal_test(Object state){
    StateCannibal CAN_state = (StateCannibal) state;
    if (CAN_state.canArray[CL] == 3&&CAN_state.canArray[ML] == 3&&CAN_state.canArray[BL] == 1)
        return true;
    else return false;
    }
    Set<Object> getSuccessors(Object state){
      Set<Object> set = new HashSet<Object>();
      StateCannibal CAN_state = (StateCannibal) state;
      StateCannibal successor_state;

      //Only Cannibal from right to left
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[CR]--;
      successor_state.canArray[CL]++;
      successor_state.canArray[BR]--;
      successor_state.canArray[BL]++;
      if(isValid(successor_state)) set.add(successor_state);
      //Two Cannibal from right to left
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[CR]-=2;
      successor_state.canArray[CL]+=2;
      successor_state.canArray[BR]--;
      successor_state.canArray[BL]++;
      if(isValid(successor_state)) set.add(successor_state);
      //One missionary from right to left
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[MR]--;
      successor_state.canArray[ML]++;
      successor_state.canArray[BR]--;
      successor_state.canArray[BL]++;
      if(isValid(successor_state)) set.add(successor_state);
      //two missionary from right to left
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[MR]-=2;
      successor_state.canArray[ML]+=2;
      successor_state.canArray[BR]--;
      successor_state.canArray[BL]++;
      if(isValid(successor_state)) set.add(successor_state);
      //One cannibal from left to right
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[CL]--;
      successor_state.canArray[CR]++;
      successor_state.canArray[BL]--;
      successor_state.canArray[BR]++;
      if(isValid(successor_state)) set.add(successor_state);
      //two cannibal from left to right
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[CL]-=2;
      successor_state.canArray[CR]+=2;
      successor_state.canArray[BL]--;
      successor_state.canArray[BR]++;
      if(isValid(successor_state)) set.add(successor_state);
      //two missionry left to right
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[ML]-=2;
      successor_state.canArray[MR]+=2;
      successor_state.canArray[BL]--;
      successor_state.canArray[BR]++;
      if(isValid(successor_state)) set.add(successor_state);
      //one missionary left to right
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[ML]--;
      successor_state.canArray[MR]++;
      successor_state.canArray[BL]--;
      successor_state.canArray[BR]++;
      if(isValid(successor_state)) set.add(successor_state);
      //one cannibal and one missionary from left to right
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[CL] --;
      successor_state.canArray[CR] ++;
      successor_state.canArray[ML] --;
      successor_state.canArray[MR] ++;
      successor_state.canArray[BL] --;
      successor_state.canArray[BR] ++;
      if (isValid(successor_state)) set.add(successor_state);

      //one cannibal and one missionary from right to left
      successor_state = new StateCannibal(CAN_state);
      successor_state.canArray[CL] ++;
      successor_state.canArray[CR] --;
      successor_state.canArray[ML] ++ ;
      successor_state.canArray[MR] --;
      successor_state.canArray[BL] ++;
      successor_state.canArray[BR] --;
      if (isValid(successor_state)) set.add(successor_state);


      return set;




    }
    private boolean isValid(StateCannibal state){
      for(int i = 0; i <N; i++){
        if(state.canArray[i] <0) return false;
      }
        if(state.canArray[CR] + state.canArray[CL] > 3) return false;
        if(state.canArray[MR] + state.canArray[ML] > 3) return false;
        if(state.canArray[BR] + state.canArray[BL] > 1) return false;
        if(state.canArray[BL] == 1 && state.canArray[CR] > state.canArray[MR]) return false;
        if(state.canArray[BR] == 1 && state.canArray[CL] > state.canArray[ML]) return false;

return true;

    }
    double step_cost(Object fromState, Object toState){return 1;}
    public double h(Object state){
      StateCannibal CAN_state = (StateCannibal) state;
      return (CAN_state.canArray[CR]+CAN_state.canArray[MR])/2;
    }

    public static void main(String[] args) throws Exception {
        Cannibal problem = new Cannibal();
        int[] newArray = {3,3,1,0,0,0};
		    problem.initialState = new StateCannibal(newArray);
        PrintStream p = new PrintStream(new File("Cannibal1.txt"));
       Search search  = new Search(problem);
       System.setOut(p);
       System.out.println("Hans Lee V00847557, Runming Gao V00872823 :");
        System.setOut(p);
        System.out.println("\nQ3.\t : ==========\n");
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
