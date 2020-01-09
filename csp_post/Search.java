import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Search {

	CSP CSP;

	public Search(CSP CSP) { this.CSP = CSP; }

	//returns an assignment
	public Map<Object, Object> BacktrackingSearch() {
		//create an empty assignment
		Map<Object, Object> assignment = new TreeMap<Object, Object>();
		return Backtrack(assignment);
	}

	Map<Object, Object> Backtrack(Map<Object, Object> assignment) {
		if( isComplete(assignment) )
			return assignment;
		Object X = SelectUnassignedVariable(assignment);
		for(Object x : CSP.D.get(X))
			if(isConsistent(X,x,assignment)) {
				assign(X,x,assignment);

				Map<Object, Object> result = Backtrack(assignment);
				if(result!=null)
					return result;

				assignUndo(X,assignment);
			}
		return null;
	}

	Deque<Map<Object, Set<Object>>> stack = new ArrayDeque<Map<Object, Set<Object>>>();

	//assigns x to X and does inferencing
	void assign(Object X, Object x, Map<Object, Object> assignment) {
		assignment.put(X, x);

		//now do forward checking and record deleted values
		//var-set of deleted values
		if(!CSP.C.containsKey(X))
			return;
		Map<Object, Set<Object>> deletedValues = new TreeMap<Object, Set<Object>>();
		for(Object Y : CSP.C.get(X)) {
			if(assignment.containsKey(Y))
				continue;

			for(Object y : CSP.D.get(Y))
				if(!CSP.isGood(X, Y, x, y)) {

					if(!deletedValues.containsKey(Y))
						deletedValues.put(Y, new TreeSet<Object>());

					deletedValues.get(Y).add(y);
				}
		}

		stack.push(deletedValues);
		//do the real value deletions from variable domains in CSP
		for(Object Y : deletedValues.keySet())
			CSP.D.get(Y).removeAll(deletedValues.get(Y));
	}

	void assignUndo(Object X, Map<Object, Object> assignment) {
		assignment.remove(X);

		//Now undo value deletions by forward checking
		if (stack.isEmpty())
			return;

		Map<Object, Set<Object>> deletedValues = stack.pop();
		for(Object Y : deletedValues.keySet())
			CSP.D.get(Y).addAll(deletedValues.get(Y));
	}

	boolean isConsistent(Object X, Object x, Map<Object, Object> assignment) {
		for(Object Y : assignment.keySet()) {
			Object y = assignment.get(Y);

			if(!CSP.isGood(X,Y,x,y))
				return false;

			if(!CSP.isGood(Y,X,y,x))
				return false;
		}
		return true;
	}

	Object SelectUnassignedVariable(Map<Object, Object> assignment) {
		//Implements minimum remaining values (MRV)
		int min = Integer.MAX_VALUE;
		Object Xmin = null;

		for(Object X : CSP.D.keySet()) {
			if (assignment.containsKey(X))
				continue;

			if (CSP.D.get(X).size() < min) {
				min = CSP.D.get(X).size();
				Xmin = X;
			}
		}

		return Xmin;
	}

	boolean isComplete(Map<Object, Object> assignment) {
		if(assignment.size() == CSP.D.size())
			return true;

		return false;
	}
}
