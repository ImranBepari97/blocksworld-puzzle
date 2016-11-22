
public class HeuristicNode extends Node implements Comparable  {
	
	private int manDist;

	public HeuristicNode(int n) {
		super(n);
		manDist = calcManDistance();
	}
	
	public int getManDist() {
		return manDist;
	}
	
	public HeuristicNode(State s, int depth) {
		super(s, depth);
		manDist = calcManDistance();
	}
	
	/* Calculates the Manhattan Distance
	 * Goes through the whole board and finds the letter blocks. It then uses that letter block
	 * relative to the ALPHABET array to calculate where the letter block should be in the array.
	 * It uses the distance as a cost, and sums up for every letter. 
	 */
	public int calcManDistance() {
		int manXDist = 0;
		int manYDist = 0;
		for (int y = 0; y < state.getSize(); y++) {
			for(int x = 0; x < state.getSize(); x++) {
				for (int c = 0; c < state.getSize(); c++) {
					if(state.getBoard()[y][x] == ALPHABET[c]) {
						manYDist += Math.abs(y - (c + 1)); 			
						manXDist += x;
					}
				}
			}
		}
		
		return manXDist + manYDist;
	}
	
	/*Comparable uses the Manhattan distances plus how deep the current node is to judge
	 * how good a Node is for getting to the solved state.
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object node2) {
		return (this.manDist + depth) - (((HeuristicNode) node2).manDist + ((HeuristicNode) node2).depth);
	}


}
