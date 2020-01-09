import java.util.*;
import java.math.*;
public class CSPZebra extends CSP {

	public boolean isGood(Object X, Object Y, Object x, Object y) {
		//if X is not even mentioned in by the constraints, just return true
		//as nothing can be violated
		if(!C.containsKey(X))
			return true;

		//check to see if there is an arc between X and Y
		//if there isn't an arc, then no constraint, i.e. it is good
		if(!C.get(X).contains(Y))
			return true;


		//unique
		if(nationV.contains(X) && !X.equals(Y) && x.equals(y)&&nationV.contains(Y))return false;

		if(colorV.contains(X) &&  !X.equals(Y) && x.equals(y)&&colorV.contains(Y))return false;

		if(drinksV.contains(X) && drinksV.contains(Y) && !X.equals(Y) && x.equals(y))return false;

		if(cigarV.contains(Y)&&cigarV.contains(X)  && !X.equals(Y) && x.equals(y))return false;

		if(petV.contains(Y) && !X.equals(Y)&&petV.contains(X) && x.equals(y))return false;

		//equal
		//1
		if(X.equals("englishman") && Y.equals("red") && !x.equals(y)) return false;
	// 2
	if(X.equals("spaniard") && Y.equals("dog") && !x.equals(y)) return false;
	// 3
	if(X.equals("coffee") && Y.equals("green") && !x.equals(y)) return false;
	//	4.
	if(X.equals("ukrainian") && Y.equals("tea") && !x.equals(y)) return false;
	// 5
	if((X.equals("ivory") && Y.equals("green") && !((Integer)x - (Integer)y != 1)) ||
			(X.equals("green") && Y.equals("ivory") && !((Integer)x - (Integer)y == 1)) )return false;
	//	6
	if(X.equals("old-gold") && Y.equals("snails") && !x.equals(y)) return false;
	//7
	if(X.equals("kools") && Y.equals("yellow") && !x.equals(y)) return false;
// 8
if((X.equals("milk") && !x.equals(new Integer(3))) || (Y.equals("milk") && !y.equals(new Integer(3))))
		return false;
// 9
if (X.equals("norwegian") && (Integer) x != 1) return false;
// 10
	if(X.equals("chesterfield") && Y.equals("fox") && Math.abs((Integer)x-(Integer)y) !=1) return false;
	// 11
	if(X.equals("kools") && Y.equals("horse") && Math.abs((Integer)x-(Integer)y) !=1) return false;
	// 12
	if(X.equals("lucky-strike") && Y.equals("orange-juice") && !x.equals(y)) return false;
	//13
	if(X.equals("japanese") && Y.equals("parliament") && !x.equals(y)) return false;
	// 14
if(X.equals("norwegian") && Y.equals("blue") && Math.abs((Integer)x-(Integer)y) !=1) return false;



		return true;
}


private static void BI(CSPZebra csp, String x, String y){
	csp.addBidirectionalArc(x,y);
}

static Set<Object> colorV = new HashSet<Object> (Arrays.asList(new String[] {"red", "green", "ivory", "blue", "yellow"}));
static Set<Object> drinksV = new HashSet<Object> (Arrays.asList(new String[] {"water", "tea", "orange-juice", "milk", "coffee"}));
static Set<Object> nationV = new HashSet<Object> (Arrays.asList(new String[] {"japanese", "norwegian", "englishman", "spaniard", "ukrainian"}));
static Set<Object> petV = new HashSet<Object> (Arrays.asList(new String[] {"horse", "zebra", "dog", "snails", "fox"}));
static Set<Object> cigarV = new HashSet<Object> (Arrays.asList(new String[] {"chesterfield", "lucky-strike", "kools", "parliament", "old-gold"}));

	public static void main(String[] args) throws Exception {

		CSPZebra csp = new CSPZebra();
		Integer[] dom = {1, 2, 3, 4, 5};
		for(Object X: colorV)
			csp.addDomain(X, dom);
		for(Object X: drinksV)
			csp.addDomain(X, dom);
		for(Object X: nationV)
			csp.addDomain(X, dom);
		for(Object X: petV)
			csp.addDomain(X, dom);
		for(Object X: cigarV)
			csp.addDomain(X, dom);

		BI(csp,"englishman", "red");
		BI(csp,"spaniard", "dog");
		BI(csp,"ukrainian", "tea");
		BI(csp,"coffee", "green");
		BI(csp,"green", "ivory");
		BI(csp,"old-gold", "snails");
		BI(csp,"chesterfield", "fox");
		BI(csp,"kools", "yellow");
		BI(csp,"lucky-strike", "orange-juice");
		BI(csp,"japanese", "parliament");
		BI(csp,"norwegian", "blue");
		BI(csp,"kools", "horse");

		for(Object X: colorV)
			for(Object Y: colorV)
				csp.addBidirectionalArc(X,Y);

		for(Object X: drinksV)
			for(Object Y: drinksV)
				csp.addBidirectionalArc(X,Y);

		for(Object X: nationV)
			for(Object Y: nationV)
				csp.addBidirectionalArc(X,Y);

		for(Object X: petV)
			for(Object Y: petV)
				csp.addBidirectionalArc(X,Y);

		for(Object X: cigarV)
			for(Object Y: cigarV)
				csp.addBidirectionalArc(X,Y);


	for (int i=1; i<=5; i++)
			if (i !=3) csp.D.get("milk").remove(i);
	    // The Norwegian lives in the first house on the left.
		for (int i=2; i<=5; i++)
			csp.D.get("norwegian").remove(i);


		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}
