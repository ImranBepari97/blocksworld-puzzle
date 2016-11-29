
public class Node {
	/* A node class is used in the trees generated by the Algorithms class. It contains
	 * a board state, a value for the depth, and a Manhattan Distance used for the A* Heuristic.
	 */
	
	protected State state;
	protected int depth;
	protected static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	protected String directionFromParent;
	protected Node parent;
	
	//Intial constructor, just makes a new fresh puzzle
	public Node(int n) {
		state = new State(n);
		this.depth = 1;
		directionFromParent = "INITIAL";
		this.parent = null;
		
	}

	/* New constructor for going down the tree. It takes the old State from before. It duplicates 
	* so that nothing breaks referencing up the tree. The depth can just increment.
	*/
	public Node(State s, int depth, String directionFromParent, Node parent) {
		state = s;
		this.depth = depth;
		this.directionFromParent = directionFromParent;
		this.parent = parent;
	}

	public String getDirectionFromParent() {
		return directionFromParent;
	}

	public void setDirectionFromParent(String directionFromParent) {
		this.directionFromParent = directionFromParent;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	//getters setters
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
