/*
Hans Lee V00847557
Runming Gao V00872823
Assignment 1
*/
import java.util.HashSet;
import java. util.Set;
import java.io.*;
public class WaterJug extends Problem{
  static final int J12= 0;
  static final int J8 =1;
  static final int J3 = 2;
  static final int ground =3;
    boolean goal_test(Object state){
      WaterState curr = (WaterState) state;
      int []results = curr.WArray;
      for(int i:results)
        if(i == 1) return true;

      return false;

}
private boolean isValid(WaterState state){
  int[] jugs = state.WArray;
  if(jugs[0] > 12 || jugs[0]<0) return false;
  if(jugs[1] > 8 || jugs[1]<0) return false;
  if(jugs[2] > 3 || jugs[2]<0) return false;

  return true;

}
    Set<Object> getSuccessors(Object state){
      Set<Object> set = new HashSet<Object>();
      WaterState Wstate = (WaterState) state;
      WaterState successor_state;
      int amount = 0;
      int L12,L8,L3,left12,left8,left3 = 0;


      //+12
      successor_state = new WaterState(Wstate);
         if (successor_state.WArray[J12] < 12) {
             amount = 12 - successor_state.WArray[J12];
             successor_state.WArray[J12] += amount;
             if (isValid(successor_state)) set.add(successor_state);
         }

         //+8
         successor_state = new WaterState(Wstate);
         if (successor_state.WArray[J8] < 8) {
             amount = 8 - successor_state.WArray[J8];
             successor_state.WArray[J8] += amount;
             if (isValid(successor_state)) set.add(successor_state);
         }
         //8 to 3
         successor_state = new WaterState(Wstate);
         L8 = successor_state.WArray[J8];
         left3 = 3 - successor_state.WArray[J3];
         if (L8 > 0 && left3 > 0) {
             if (L8 <= left3) {
                 successor_state.WArray[J8] -= L8;
                 successor_state.WArray[J3] += L8;
             } else {
                 successor_state.WArray[J8] -= left3;
                 successor_state.WArray[J3] += left3;
             }
             if(isValid(successor_state)) set.add(successor_state);
         }


         //+3
         successor_state = new WaterState(Wstate);
         if (successor_state.WArray[J3] < 3) {
             amount = 3 - successor_state.WArray[J3];
             successor_state.WArray[J3] += amount;
             if (isValid(successor_state)) set.add(successor_state);
         }


         //12 to 8
         successor_state = new WaterState(Wstate);
         L12 = successor_state.WArray[J12];
         left8 = 8 - successor_state.WArray[J8];
         if(L12 > 0 && left8 >0) {
             if (L12 <= left8) {
                 successor_state.WArray[J12] -= L12;
                 successor_state.WArray[J8] += L12;
             } else {
                 successor_state.WArray[J12] -= left8;
                 successor_state.WArray[J8] += left8;
             }
             if(isValid(successor_state)) set.add(successor_state);
         }




         //8 to 12
         successor_state = new WaterState(Wstate);
         L8 = successor_state.WArray[J8];
         left12 = 12 - successor_state.WArray[J12];
         if (L8 > 0 && left12 > 0) {
             if (L8 <= left12) {
                 successor_state.WArray[J8] -= L8;
                 successor_state.WArray[J12] += L8;
             } else {
                 successor_state.WArray[J8] -= left12;
                 successor_state.WArray[J12] += left12;
             }
             if(isValid(successor_state)) set.add(successor_state);
         }

         //Pour 3 to 8
         successor_state = new WaterState(Wstate);
         L3 = successor_state.WArray[J3];
         left8 = 8 - successor_state.WArray[J8];
         if (L3 > 0 && left8 > 0) {
             if (L3 != 0 && L3 <= left8) {
                 successor_state.WArray[J3] -= L3;
                 successor_state.WArray[J8] += L3;
             } else {
                 successor_state.WArray[J3] -= left8;
                 successor_state.WArray[J8] += left8;
             }
             if(isValid(successor_state)) set.add(successor_state);
         }

         //Pour 3 to 12
         successor_state = new WaterState(Wstate);
         L3 = successor_state.WArray[J3];
         left12 = 12 - successor_state.WArray[J12];
         if (L3 > 0 && left12 > 0) {
             if (L3 != 0 && L3 <= left12) {
                 successor_state.WArray[J3] -= L3;
                 successor_state.WArray[J12] += L3;
             } else {
                 successor_state.WArray[J3] -= left12;
                 successor_state.WArray[J12] += left12;
             }
             if(isValid(successor_state)) set.add(successor_state);
         }
         //-3
         successor_state = new WaterState(Wstate);
         if (successor_state.WArray[J3]>0){
             successor_state.WArray[ground] += successor_state.WArray[J3];
             successor_state.WArray[J3] = 0;
             if(isValid(successor_state)) set.add(successor_state);
         }


         //-12
         successor_state = new WaterState(Wstate);
         if (successor_state.WArray[J12]>0){
             successor_state.WArray[ground] += successor_state.WArray[J12];
             successor_state.WArray[J12] = 0;
             if(isValid(successor_state)) set.add(successor_state);
         }
         //12 to 3
         successor_state = new WaterState(Wstate);
         L12 = successor_state.WArray[J12];
         left3 = 3 - successor_state.WArray[J3];
         if (L12 > 0 && left3 >0) {
             if (L12 <= left3) {
                 successor_state.WArray[J12] -= L12;
                 successor_state.WArray[J3] += L12;
             } else {
                 successor_state.WArray[J12] -= left3;
                 successor_state.WArray[J3] += left3;
             }
             if (isValid(successor_state)) set.add(successor_state);
         }

         //-8
         successor_state = new WaterState(Wstate);
         if (successor_state.WArray[J8]>0){
             successor_state.WArray[ground] += successor_state.WArray[J8];
             successor_state.WArray[J8] = 0;
             if(isValid(successor_state)) set.add(successor_state);
         }


         return set;

      }
      double step_cost(Object fromState, Object toState) {
      int costs=0;
      WaterState p = (WaterState) fromState;
      WaterState n = (WaterState) toState;

      for(int i=0; i< p.WArray.length; i++){
          if(n.WArray[i] - p.WArray[i] >0) costs+=(n.WArray[i] - p.WArray[i]);
      }

      return costs;
    }


    public double h(Object state){return 0;}

    public static void main(String[] args) throws Exception {
        WaterJug problem = new WaterJug();
        int[] newArray = {0,0,0,0};
		    problem.initialState = new WaterState(newArray);
        PrintStream p = new PrintStream(new File("WaterJug.txt"));
        Search search  = new Search(problem);
        System.setOut(p);
        System.out.println("Hans Lee V00847557, Runming Gao V00872823 :");
        System.setOut(p);
        System.out.println("\nQ4.\t : ==========\n");
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
