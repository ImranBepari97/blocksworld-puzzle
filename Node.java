
public class Node {
	private State state;
	private int depth;

	
	public Node(int n) {
		state = new State(n);
		this.depth = 1;
	}
	
	public Node(State s, int depth) {
		state = s;
		this.depth = depth;
	}

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
