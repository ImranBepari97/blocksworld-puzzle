import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Algorithms {
	/*
	 * This algorithms class is just a set of 4 search algorithms to solve the
	 * puzzle. It contains Breadth First Search, Depth First Search, Iterative
	 * Deepening and A Star Heuristic. To use Depth First Search, use dfs() with
	 * a negative iterations value, otherwise, using it with a +ve iterations
	 * value does Iterative Deepening.
	 */
	public static void main(String[] args) {
		Algorithms a = new Algorithms();
		a.bfs(new Node(2));

	}

	public void bfs(Node root) {
		/*
		 * Breadth first search will expand all possible moves, and see which
		 * path eventually hits the solved state. This goes level by level,
		 * slowly going deeper. Utilizes a queue for this, will search whatevers
		 * in front of the queue.
		 */
		LinkedList<Node> queue = new LinkedList<Node>();
		int node = 0;
		int nodeExp = 0;
		if (root == null) {
			return;
		}

		// start the queue with the root
		queue.add(root);
		// keep going
		while (!queue.isEmpty()) {
			// get a node and take it off the queue to analyze it
			Node temp = queue.poll();
			nodeExp++;

			// just print details
			temp.getState().printState();
			System.out.println("Total node number: " + node + "	Node depth: " + temp.getDepth() + "	Node direction: " + temp.directionFromParent);
			System.out.println("-------");

			// is it solved? if so then stop, print out the output
			if (temp.getState().checkSolved()) {
				System.out.println("Solved");
				System.out.println("Space complexity: " + Math.pow(4, temp.depth + 1));
				System.out.println("Nodes expanded: " + nodeExp);
				
				Node curNodePath = temp;
				String path = curNodePath.getDirectionFromParent();
				
				while((curNodePath = curNodePath.getParent()) != null) {
					path = curNodePath.getDirectionFromParent() + ", " + path;
				}
				
				System.out.println("Path to solution: " + path);
				
				return;
			}

			// add all the possible moves to the tree from where you are,
			// incrememnt node amount and depth too
			if (temp.getState().checkMove("up")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1, "UP", temp);
				newNode.getState().move("up");
				queue.add(newNode);
				node++;
			}

			if (temp.getState().checkMove("down")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1, "DOWN", temp);
				newNode.getState().move("down");
				queue.add(newNode);
				node++;
			}

			if (temp.getState().checkMove("left")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1, "LEFT", temp);
				newNode.getState().move("left");
				queue.add(newNode);
				node++;
			}

			if (temp.getState().checkMove("right")) {
				Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1, "RIGHT", temp);
				newNode.getState().move("right");
				queue.add(newNode);
				node++;
			}
		}
	}

	public void dfs(Node root, int iterations) {
		/*
		 * Iterative deepening search and normal depth first search in one. To
		 * use depth first as usual, make the iterations -1. Depth first goes
		 * down a whole branch till the bottom, comes back up and searches the
		 * next branch. A branch is stopped by an invalid move. Stack is used to
		 * pop.
		 */
		Stack<Node> stack = new Stack<Node>();
		int node = 0;
		if (root == null) {
			return;
		}

		stack.push(root);
		int nodeExp = 0;
		/*
		 * all of this is largely the same as BFS aside from the stack and using
		 * the iterations, however nodes are added to an arraylist first to be
		 * randomly added to the tree. This way you don't go down the up branch
		 * all the way first.
		 */
		while (true) {
			while (!stack.isEmpty()) {
				Node temp = stack.pop();
				nodeExp++;
				temp.getState().printState();
				System.out.println("Node number: " + node + "	Node depth: " + temp.getDepth() + "	Node direction: " + temp.directionFromParent);
				
				System.out.println("-------");

				if (temp.getState().checkSolved()) {
					System.out.println("Solved on depth try: " + iterations);
					System.out.println("Nodes expanded: " + nodeExp);
					
					Node curNodePath = temp;
					String path = curNodePath.getDirectionFromParent();
					
					while((curNodePath = curNodePath.getParent()) != null) {
						path = curNodePath.getDirectionFromParent() + ", " + path;
					}
					
					System.out.println("Path to solution: " + path);
					
					return;
				}

				ArrayList<Node> nextLevel = new ArrayList<Node>();

				// when the depth specified is hit, then stop generated nodes
				// for the tree to search
				if (temp.depth != iterations) {
					if (temp.getState().checkMove("up")) {
						Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1, "UP", temp);
						newNode.getState().move("up");
						nextLevel.add(newNode);
						node++;
					}

					if (temp.getState().checkMove("down")) {
						Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1, "DOWN", temp);
						newNode.getState().move("down");
						nextLevel.add(newNode);
						node++;
					}

					if (temp.getState().checkMove("left")) {
						Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1, "LEFT", temp);
						newNode.getState().move("left");
						nextLevel.add(newNode);
						node++;
					}

					if (temp.getState().checkMove("right")) {
						Node newNode = new Node(new State(temp.getState()), temp.getDepth() + 1, "RIGHT", temp);
						newNode.getState().move("right");
						nextLevel.add(newNode);
						node++;
					}

					// randomly add the new nodes to the stack
					while (!nextLevel.isEmpty()) {
						int r = new Random().nextInt(nextLevel.size());
						stack.push(nextLevel.remove(r));
					}
					// add one to the iteration because you've generated a new
					// level
				}
			}
			// if you get to the end of the tree with no solutions, stop
			System.out.println("Could not find solution up to depth of " + iterations + ", incrementing.");
			System.out.println();
			stack.clear();
			stack.push(root);
			iterations++;
		}
	}

	public void aStar(HeuristicNode root) {
		/*
		 * A Star Heuristic search uses guesses based on the Manhattan distance.
		 * It's basically the breadth first above copied and pasted, using a
		 * Priority Queue. The queue can sort the Nodes by which ones are closer
		 * to the solution. This formula can be found in the Node class at line
		 * 40 onward. The queue just searches these first constantly to get
		 * there quicker.
		 */
		PriorityQueue<HeuristicNode> queue = new PriorityQueue<HeuristicNode>();
		int node = 0;
		int nodeExp = 0;

		if (root == null) {
			return;
		}
		// start the queue with the root
		queue.add(root);

		// keep going
		while (!queue.isEmpty()) {
			// get a node and take it off the queue to analyze it
			HeuristicNode temp = queue.poll();
			nodeExp++;

			// just print details
			temp.getState().printState();
			System.out.println("Node number: " + node + "	Node depth: " + temp.getDepth() + "	Total Cost: "
					+ (temp.getManDist() + temp.getDepth()) + "	Node direction: " + temp.directionFromParent);
			
			System.out.println("-------");

			// is it solved? if so then stop
			if (temp.getState().checkSolved()) {
				System.out.println("Nodes expanded: " + nodeExp);
				System.out.println("solved");
				
				Node curNodePath = temp;
				String path = curNodePath.getDirectionFromParent();
				
				while((curNodePath = curNodePath.getParent()) != null) {
					path = curNodePath.getDirectionFromParent() + ", " + path;
				}
				
				System.out.println("Path to solution: " + path);
				
				return;
			}

			// add all the possible moves to the tree from where you are,
			// increment node amount and depth too

			if (temp.getState().checkMove("up")) {
				HeuristicNode newNode = new HeuristicNode(new State(temp.getState()), temp.getDepth() + 1, "UP", temp);
				newNode.getState().move("up");
				queue.add(newNode);
				node++;
			}

			if (temp.getState().checkMove("down")) {
				HeuristicNode newNode = new HeuristicNode(new State(temp.getState()), temp.getDepth() + 1, "DOWN", temp);
				newNode.getState().move("down");
				queue.add(newNode);
				node++;
			}

			if (temp.getState().checkMove("left")) {
				HeuristicNode newNode = new HeuristicNode(new State(temp.getState()), temp.getDepth() + 1, "LEFT", temp);
				newNode.getState().move("left");
				queue.add(newNode);
				node++;
			}

			if (temp.getState().checkMove("right")) {
				HeuristicNode newNode = new HeuristicNode(new State(temp.getState()), temp.getDepth() + 1, "RIGHT", temp);
				newNode.getState().move("right");
				queue.add(newNode);
				node++;
			}
		}
	}
}
