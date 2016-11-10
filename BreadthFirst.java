import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BreadthFirst {
	Game g;
	LinkedList<Node> queue;
	
	public static void main(String[] args) {
		new BreadthFirst(4);
	}
	
	public BreadthFirst(int size) {
		queue = new LinkedList<Node>();
		g = new Game(size);
		search(new Node(size));
		
	}
	
	public void search(Node root) {
		int node = 0;
		
		if(root == null) {
			return;
		}
		
		queue.clear();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			
			temp.getState().printState();
			System.out.println(node);
			System.out.println();
			
			if (g.checkSolved(temp.getState())) {
				System.out.println("solved");
				return;
			}
			
			if (g.checkValidMove(temp.getState(), "up")) queue.add(new Node(g.move(temp.getState(), "up")));
			if (g.checkValidMove(temp.getState(), "down")) queue.add(new Node(g.move(temp.getState(), "down")));
			if (g.checkValidMove(temp.getState(), "left")) queue.add(new Node(g.move(temp.getState(), "left")));
			if (g.checkValidMove(temp.getState(), "right")) queue.add(new Node(g.move(temp.getState(), "right")));
			
			node++;
			
			
		}
		
	}

}
