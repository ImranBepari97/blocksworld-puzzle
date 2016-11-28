public class State {
	/* State class represents the board and everything on it at the moment. It
	 * stores the board in the form of a char[][], the agents position and the size. 
	 */
	private char[][] board;
	private int size;
	private int agentX;
	private int agentY;

	//test code to see if creating a solved board would work
	public static void main(String[] args) {
		State s = new State(4);
		s.createSolved();
		s.printState();
	}

	// getters and setters
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/*method used to create a sample solved board. makes it in all columns
	*it was mainly used so I could code the method for checking whether the board 
	*was solved
	*/
	public void createSolved() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				board[y][x] = '0';
			}
		}

		for (int y = 0; y < size; y++) {
			char currentLetter = 'a';
			for (int x = 1; x < size; x++) {
				board[x][y] = currentLetter;
				currentLetter++;
			}
		}
		
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print(board[y][x]);
			}
			System.out.println();
		}
	}
	// y is first array, x is second array

	/*this constructor duplicates another State, this would be so that 
	 * the tree nodes would have separate States and it all wouldn't reference the same
	 * one. It just copies everything
	 */
	public State(State state) {
		board = new char[state.getSize()][state.getSize()];
		
		for (int x = 0; x < state.getSize(); x++) {
			for (int y = 0; y < state.getSize(); y++) {
				board[y][x] = state.board[y][x];
			}
		}

		this.size = state.size;
		this.agentX = state.agentX;
		this.agentY = state.agentY;
	}

	//constructor for the first state
	public State(int size) {
		this.size = size;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		board = new char[size][size];

		// fill board with blank tiles
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				board[y][x] = '0';
			}
		}

		// set the bottom row to be the blocks
		for (int x = 0; x < size - 1; x++) {
			board[size - 1][x] = alphabet[x];
		}

		// sets agent tile
		board[size - 1][size - 1] = '!';
		agentX = agentY = size - 1;
	}

	//just prints out the board state in correct form
	public void printState() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print(board[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/*kind of useless, decided to trust in my code keeping track of where
	* the agent is
	*/
	public boolean checkAgentPosition() {
		if (board[agentY][agentX] == '!') {
			return true;
		} else {
			for (int y = 0; y < size - 1; y++) {
				for (int x = 0; x < size - 1; x++) {
					if (board[y][x] == '!') {
						agentY = y;
						agentX = x;
					}
				}
			}
		}
		return false;
	}

	/*important method that checks whether the board is solved or not. 
	 * Counts the amount of letters in the right spot, for each column. 
	 * There's n-1 letters to the size.
	*/
	public boolean checkSolved() {
		for (int y = 0; y < board.length; y++) {
			char currentLetter = 'a';
			int correctLetters = 0;
			for (int x = 1; x < board.length; x++) {
				if (board[x][y] == currentLetter) {
					currentLetter++;
					correctLetters++;
				} else {
					break;
				}
			}

			if (correctLetters == size - 1) {
				return true;
			}
		}
		return false;
	}

	//verifies if a move is valid, or if you're going into a wall
	public boolean checkMove(String direction) {
		switch (direction) {
		case "up":
			if (agentY == 0) {
				return false;
			} else {
				return true;
			}
		case "down":
			if (agentY == size - 1) {
				return false;
			} else {
				return true;
			}
		case "left":
			if (agentX == 0) {
				return false;
			} else {
				return true;
			}
		case "right":
			if (agentX == size - 1) {
				return false;
			} else {
				return true;
			}
		default:
			System.out.println("invalid move check");
		}
		return false;
	}

	//Actually moves the agent
	public void move(String direction) {
		char newSpot;
		switch (direction) {
		case "up":
			newSpot = board[agentY - 1][agentX];
			board[agentY - 1][agentX] = '@';
			board[agentY][agentX] = newSpot;
			agentY -= 1;
			break;
		case "down":
			newSpot = board[agentY + 1][agentX];
			board[agentY + 1][agentX] = '@';
			board[agentY][agentX] = newSpot;
			agentY += 1;
			break;
		case "left":
			newSpot = board[agentY][agentX - 1];
			board[agentY][agentX - 1] = '@';
			board[agentY][agentX] = newSpot;
			agentX -= 1;
			break;
		case "right":
			newSpot = board[agentY][agentX + 1];
			board[agentY][agentX + 1] = '@';
			board[agentY][agentX] = newSpot;
			agentX += 1;
			break;
		}
	}
}
