
public class Node {
	private State state;
	private int depth;

	
	public Node(int n, int depth) {
		state = new State(n);
		this.depth = depth;
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
	
}
