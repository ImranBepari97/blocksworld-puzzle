import java.awt.Point;

public class State {
	private char[][] board;
	private int size;
	private Point agentPosition;
	private int agentX;
	private int agentY;

//	public static void main(String[] args) {
//		State s = new State(4);
//		s.move("right");
//		s.move("up");
//		s.printState();
//		s.move("left");
//		s.printState();
//		s.move("down");
//		s.printState();
//		s.move("right");
//		s.printState();
//
//	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public int getSize() {
		return size;
	}

	public Point getAgentPosition() {
		return agentPosition;
	}

	public void setAgentPosition(Point agentPosition) {
		this.agentPosition = agentPosition;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void createSolved(int n) {
		char[][] solved = new char[n][n];

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				solved[y][x] = '0';
			}
		}

		for (int y = 0; y < solved.length; y++) {
			char currentLetter = 'a';
			for (int x = 1; x < solved.length; x++) {
				solved[x][y] = currentLetter;
				currentLetter++;
			}
		}
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print(solved[y][x]);
			}
			System.out.println();
		}
	}
	// y is first array, x is second array

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
		agentPosition = new Point(size - 1, size - 1);
		agentX = agentY = size - 1;

	}

	public void printState() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print(board[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public boolean checkAgentPosition() {
		if (board[agentPosition.y][agentPosition.x] == '!') {
			return true;
		} else {
			for (int y = 0; y < size - 1; y++) {
				for (int x = 0; x < size - 1; x++) {
					if (board[y][x] == '!') {
						agentPosition = new Point(x, y);
					}
				}
			}
		}
		return false;
	}

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
