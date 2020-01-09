/*
Hans Lee V00847557
Runming Gao V00872823
Assignment 1
*/
public class StateFWCG
{
	int N;
    int[] FWCGArray;
    int i0; //position of zero

    public StateFWCG(int[] FWCGArray) {
    	this.FWCGArray = FWCGArray;
    	N = FWCGArray.length;

    	//Find the position of 0
    	mainLoop:
        for(int i=0; i<N; i++)
        		if(FWCGArray[i]== 0) {
        			i0 = i;
        			break mainLoop;
        		}
    }

    //It has to be a copy of values not reference because we will
    //create many states and don't want to overwrite the same array.
    public StateFWCG(StateFWCG state) {
    	N = state.N;
    	FWCGArray = new int[N];

        for(int i=0; i<N; i++)
        		FWCGArray[i]= state.FWCGArray[i];

        i0 = state.i0;
    }


    public int hashCode() {
        return java.util.Arrays.hashCode( FWCGArray );
    }

    public String toString()
    {
    	return java.util.Arrays.toString( FWCGArray );
    }
}
