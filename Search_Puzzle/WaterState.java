/*
Hans Lee V00847557
Runming Gao V00872823
Assignment 1
*/
public class WaterState
{
	int N;
    int[] WArray;
    int i0; //position of zero

    public WaterState(int[] WArray) {
    	this.WArray = WArray;
    	N = WArray.length;

    	//Find the position of 0
    	mainLoop:
        for(int i=0; i<N; i++)
        		if(WArray[i]== 0) {
        			i0 = i;
        			break mainLoop;
        		}
    }

    //It has to be a copy of values not reference because we will
    //create many states and don't want to overwrite the same array.
    public WaterState(WaterState state) {
    	N = state.N;
    	WArray = new int[N];

        for(int i=0; i<N; i++)
        		WArray[i]= state.WArray[i];

        i0 = state.i0;
    }


    public int hashCode() {
        return java.util.Arrays.hashCode( WArray );
    }

    public String toString()
    {
    	return java.util.Arrays.toString( WArray );
    }
}
