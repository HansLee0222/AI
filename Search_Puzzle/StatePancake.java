/*
Hans Lee V00847557
Runming Gao V00872823
Assignment 1
*/
public class StatePancake
{
	int N;
    int[] pancakeArray;
    int i0; //position of zero

    public StatePancake(int[] pancakeArray) {
    	this.pancakeArray = pancakeArray;
    	N = pancakeArray.length;

    	//Find the position of 0
    	mainLoop:
        for(int i=0; i<N; i++)
        		if(pancakeArray[i]== 0) {
        			i0 = i;
        			break mainLoop;
        		}
    }

    //It has to be a copy of values not reference because we will
    //create many states and don't want to overwrite the same array.
    public StatePancake(StatePancake state) {
    	N = state.N;
    	pancakeArray = new int[N];

        for(int i=0; i<N; i++)
        		pancakeArray[i]= state.pancakeArray[i];

        i0 = state.i0;
    }


    public int hashCode() {
        return java.util.Arrays.hashCode( pancakeArray );
    }

    public String toString()
    {
    	return java.util.Arrays.toString( pancakeArray );
    }
}
