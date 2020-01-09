/*
Hans Lee V00847557
Runming Gao V00872823
Assignment 1
*/
public class StateCannibal
{
	int N;
    int[] canArray;
    int i0; //position of zero

    public StateCannibal(int[] canArray) {
    	this.canArray = canArray;
    	N = canArray.length;

    	//Find the position of 0
    	mainLoop:
        for(int i=0; i<N; i++)
        		if(canArray[i]== 0) {
        			i0 = i;
        			break mainLoop;
        		}
    }

    //It has to be a copy of values not reference because we will
    //create many states and don't want to overwrite the same array.
    public StateCannibal(StateCannibal state) {
    	N = state.N;
    	canArray = new int[N];

        for(int i=0; i<N; i++)
        		canArray[i]= state.canArray[i];

        i0 = state.i0;
    }


    public int hashCode() {
        return java.util.Arrays.hashCode( canArray );
    }

    public String toString()
    {
    	return java.util.Arrays.toString( canArray );
    }
}
