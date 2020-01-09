import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Nim extends Game {

    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;

    public Nim() {
    	currentState = new StateNim();
    }

    public boolean isWinState(State state)
    {
      StateNim st = (StateNim) state;
      if(st.coin == 1)return true;
      return false;
    }

    public boolean isStuckState(State state) {
    	return false;
    }


	public Set<State> getSuccessors(State state)
    {
		if(isWinState(state) || isStuckState(state))
			return null;

		Set<State> successors = new HashSet<State>();
        StateNim tstate = (StateNim) state;

        StateNim successor_state;



        for(int i=1; i<=3; i++){
            successor_state = new StateNim(tstate);
            successor_state.coin -= i;
            successor_state.player = (state.player==0 ? 1 : 0);

            successors.add(successor_state);
        }


        return successors;
    }

    public double eval(State state)
    {
    	if(isWinState(state)) {
    		//player who made last move
    		int previous_player = (state.player==0 ? 1 : 0);

	    	if (previous_player==0) //computer wins
	            return WinningScore;
	    	else //human wins
	            return LosingScore;
    	}

        return NeutralScore;
    }


    public static void main(String[] args) throws Exception {

        Game game = new Nim();
        Search search = new Search(game);
        int depth = 13;

        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

        	StateNim 	nextState = null;

            switch ( game.currentState.player ) {
              case 1: //Human

            	  //get human's move
                    System.out.print("Enter your *valid* move> ");
                  int pos = Integer.parseInt( in.readLine() );
                  while(pos<1||pos>3){
                  System.out.println("Please enter a number between 1-3");
                  pos = Integer.parseInt( in.readLine() );
                  }
                  nextState = new StateNim((StateNim)game.currentState);
                  nextState.player = 1;
                  nextState.coin -=pos;
                  System.out.println("Human's move --> "+nextState);
                  break;

              case 0: //Computer

            	  nextState = (StateNim)search.bestSuccessorState(depth);
            	  nextState.player = 0;
            	  System.out.println("Computer's move --> " + nextState);
                  break;
            }

            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);

            //Who wins?
            if ( game.isWinState(game.currentState) ) {

            	if (game.currentState.player == 1) //i.e. last move was by the computer
            		System.out.println("Computer wins!");
            	else
            		System.out.println("You win!");

            	break;
            }

            if ( game.isStuckState(game.currentState) ) {
            	System.out.println("Cat's game!");
            	break;
            }
        }
    }
}
