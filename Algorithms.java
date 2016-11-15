import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Algorithms {

	private int size;

	public static void main(String[] args) {
		Algorithms a = new Algorithms(3);
		a.dfs(new Node(3), 20);
	}

	public Algorithms(int size) {
		this.size = size;
	}

	public void bfs(Node root) {
		/* Breadth first search will expand all possible moves, and see which path eventually hits the solved state.
		 * This goes level by level, slowly going deeper. Utilizes a queue for this, will search whatevers in front of the queue. 
		 */
		
		LinkedList<Node> queue = new LinkedList<Node>();

		int node = 0;

		if (root == null) {
			return;
		}

		queue.clear();
		//start the queue with the root
		queue.add(root);

		//keep going
		while (!queue.isEmpty()) {
			//get a node and take it off the queue to analyze it
			Node temp = queue.poll();

			//just print details
			temp.getState().printState();
			System.out.println("Node number: " + node + "	Node depth: " + temp.getDepth());
			System.out.println("-------");

			//is it solved? if so then stop
			if (temp.getState().checkSolved()) {
				System.out.println("solved");
				return;
			}

			//add all the possible moves to the tree from where you are, incrememnt node amount and depth too
			
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

	public void dfs(Node root, int iterations) {
		/* Iterative deepening search and normal depth first search in one. To use depth first as usual, make the iterations -1.
		 *  Depth first goes down a whole branch till the bottom, comes back up and searches the next branch. A branch is stopped by an invalid move.
		 *  Stack is used to pop.
		 */
		Stack<Node> stack = new Stack<Node>();
		int curIter = 0;
		int node = 0;

		if (root == null) {
			return;
		}

		stack.push(root);

		/*all of this is largely the same as BFS aside from the stack and using the iterations, however nodes are added to an
		* arraylist first to be randomly added to the tree. This way you don't go down the up branch all the way first. 
		*/
		
		while (!stack.isEmpty()) {
			Node temp = stack.pop();

			temp.getState().printState();
			System.out.println("Node number: " + node + "	Node depth: " + temp.getDepth());
			System.out.println("-------");

			if (temp.getState().checkSolved()) {
				System.out.println("solved");
				return;
			}

			ArrayList<Node> nextLevel = new ArrayList<Node>();

			//when the depth specified is hit, then stop generated nodes for the tree to search
			if (curIter != iterations) {
				if (temp.getState().checkMove("up")) {
					Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
					newNode.getState().move("up");
					nextLevel.add(newNode);
					node++;
				}

				if (temp.getState().checkMove("down")) {
					Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
					newNode.getState().move("down");
					nextLevel.add(newNode);
					node++;
				}

				if (temp.getState().checkMove("left")) {
					Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
					newNode.getState().move("left");
					nextLevel.add(newNode);
					node++;
				}

				if (temp.getState().checkMove("right")) {
					Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1);
					newNode.getState().move("right");
					nextLevel.add(newNode);
					node++;
				}

				
				//randomly add the new nodes to the stack
				while (!nextLevel.isEmpty()) {
					int r = new Random().nextInt(nextLevel.size());
					stack.push(nextLevel.remove(r));
				}
				
				curIter++;
			}

			
		}
		//if you get to the end of the tree with no solutions, stop
		System.out.println("Could not find solution up to depth of " + iterations);
	}
}
