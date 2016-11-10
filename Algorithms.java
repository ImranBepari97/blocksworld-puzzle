import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Algorithms {
	
	private int size;
	
	public static void main(String[] args) {
		Algorithms a = new Algorithms(3);
		a.dfs(new Node(3));
	}
	
	
	public Algorithms(int size) {
		this.size = size;
	}
	
	public void bfs(Node root) {
		LinkedList<Node> queue = new LinkedList<Node>();
		
		int node = 0;
		
		if(root == null) {
			return;
		}
		
		queue.clear();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			
			temp.getState().printState();
			System.out.println("Node number: " + node + "	Node depth: " + temp.getDepth() );
			System.out.println("-------");
			
			if (temp.getState().checkSolved()) {
				System.out.println("solved");
				return;
			}
			
			if (temp.getState().checkMove("up")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
				newNode.getState().move("up");
				queue.add(newNode);
				node++;
			}
			
			if (temp.getState().checkMove("down")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
				newNode.getState().move("down");
				queue.add(newNode);
				node++;
			}
			
			if (temp.getState().checkMove("left")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
				newNode.getState().move("left");
				queue.add(newNode);
				node++;
			}
			
			if (temp.getState().checkMove("right")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
				newNode.getState().move("right");
				queue.add(newNode);
				node++;
			}
		}
	}
	
	public void dfs(Node root) {
		Stack<Node> stack = new Stack<Node>();
		
		int node = 0;
		
		if(root == null) {
			return;
		}
		
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node temp = stack.pop();
			
			temp.getState().printState();
			System.out.println("Node number: " + node + "	Node depth: " + temp.getDepth() );
			System.out.println("-------");
			
			if (temp.getState().checkSolved()) {
				System.out.println("solved");
				return;
			}
			
			if (temp.getState().checkMove("up")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
				newNode.getState().move("up");
				stack.push(newNode);
				node++;
			}
			
			if (temp.getState().checkMove("down")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
				newNode.getState().move("down");
				stack.push(newNode);
				node++;
			}
			
			if (temp.getState().checkMove("left")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
				newNode.getState().move("left");
				stack.push(newNode);
				node++;
			}
			
			if (temp.getState().checkMove("right")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
				newNode.getState().move("right");
				stack.push(newNode);
				node++;
			}
		}
	}
}
