import java.awt.Point;

public class Game {
	private State current;

	public Game(int size) {
		current = new State(size);
	}
	
	

	public State getCurrent() {
		return current;
	}



	// checks if the puzzle is solved
	public boolean checkSolved() {
		 for (int y = 0; y < current.getBoard().length; y++) {
			 char currentLetter = 'a';
			 int correctLetters = 0;
			 for(int x = 1; x < current.getBoard().length; x++) {
				 if(current.getBoard()[x][y] == currentLetter) {
					 currentLetter++;
					 correctLetters++;
				 } else {
					 break;
				 }
			 }
			 
			 if (correctLetters == current.getSize() - 1) {
				 return true;
			 }
			 
		 }
		 return false;
	}
	
	// checks if the puzzle is solved
		public boolean checkSolved(State puzzle) {
			 for (int y = 0; y < puzzle.getBoard().length; y++) {
				 char currentLetter = 'a';
				 int correctLetters = 0;
				 for(int x = 1; x < puzzle.getBoard().length; x++) {
					 if(puzzle.getBoard()[x][y] == currentLetter) {
						 currentLetter++;
						 correctLetters++;
					 } else {
						 break;
					 }
				 }
				 
				 if (correctLetters == puzzle.getSize() - 1) {
					 return true;
				 }
				 
			 }
			 return false;
		}

	// moves the agent
	public State move(String direction) {
		if (!current.checkAgentPosition()) {
			System.out.println("Agent desynced at some point, resyncing?");
		}
		
		char curAgent = current.getBoard()[current.getAgentPosition().y][current.getAgentPosition().x];
		char newAgentSpot;

		switch (direction) {
		
		case "up":
			try {
				newAgentSpot = current.getBoard()[current.getAgentPosition().y - 1][current.getAgentPosition().x];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid move");
				return current;
			}

			current.getBoard()[current.getAgentPosition().y - 1][current.getAgentPosition().x] = curAgent;
			current.getBoard()[current.getAgentPosition().y][current.getAgentPosition().x] = newAgentSpot;

			current.setAgentPosition(new Point(current.getAgentPosition().x, current.getAgentPosition().y - 1));
			break;
		case "down":
			try {
				newAgentSpot = current.getBoard()[current.getAgentPosition().y + 1][current.getAgentPosition().x];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid move");
				return current;
			}

			current.getBoard()[current.getAgentPosition().y + 1][current.getAgentPosition().x] = curAgent;
			current.getBoard()[current.getAgentPosition().y][current.getAgentPosition().x] = newAgentSpot;

			current.setAgentPosition(new Point(current.getAgentPosition().x, current.getAgentPosition().y + 1));
			
			break;
		case "left":
			try {
				newAgentSpot = current.getBoard()[current.getAgentPosition().y ][current.getAgentPosition().x - 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid move");
				return current;
			}

			current.getBoard()[current.getAgentPosition().y ][current.getAgentPosition().x -1] = curAgent;
			current.getBoard()[current.getAgentPosition().y][current.getAgentPosition().x] = newAgentSpot;

			current.setAgentPosition(new Point(current.getAgentPosition().x - 1, current.getAgentPosition().y));
			break;
		case "right":
			try {
				newAgentSpot = current.getBoard()[current.getAgentPosition().y ][current.getAgentPosition().x + 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid move");
				return current;
			}

			current.getBoard()[current.getAgentPosition().y ][current.getAgentPosition().x + 1] = curAgent;
			current.getBoard()[current.getAgentPosition().y][current.getAgentPosition().x] = newAgentSpot;

			current.setAgentPosition(new Point(current.getAgentPosition().x + 1, current.getAgentPosition().y));
			
			break;
			default:
				System.out.println("Invalid move");
				
		}
		System.out.println(current.getAgentPosition());
		return current;

	}
	
	public State move(State state, String direction) {
		int size = state.getSize();
		State newState = new State(size);
		newState.setAgentPosition(new Point(state.getAgentPosition().x, state.getAgentPosition().y));
		
		for (int x = 0; x < newState.getSize(); x++) {
			for (int y = 0; y < size; y++) {
				newState.getBoard()[y][x] = state.getBoard()[y][x];
			}
		}		
		
		newState.checkAgentPosition();
		
		char newAgentSpot;

		switch (direction) {
		
		case "up":
			try {
				newAgentSpot = newState.getBoard()[newState.getAgentPosition().y - 1][newState.getAgentPosition().x];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid move");
				return state;
			}

			newState.getBoard()[newState.getAgentPosition().y - 1][newState.getAgentPosition().x] = '@';
			newState.getBoard()[newState.getAgentPosition().y][newState.getAgentPosition().x] = newAgentSpot;

			newState.setAgentPosition(new Point(newState.getAgentPosition().x, newState.getAgentPosition().y - 1));
			break;
		case "down":
			try {
				newAgentSpot = newState.getBoard()[newState.getAgentPosition().y + 1][newState.getAgentPosition().x];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid move");
				return state;
			}

			newState.getBoard()[state.getAgentPosition().y + 1][state.getAgentPosition().x] = '@';
			newState.getBoard()[state.getAgentPosition().y][state.getAgentPosition().x] = newAgentSpot;

			newState.setAgentPosition(new Point(state.getAgentPosition().x, state.getAgentPosition().y + 1));
			
			break;
		case "left":
			try {
				newAgentSpot = state.getBoard()[state.getAgentPosition().y ][state.getAgentPosition().x - 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid move");
				return state;
			}

			newState.getBoard()[state.getAgentPosition().y ][state.getAgentPosition().x -1] = '@';
			newState.getBoard()[state.getAgentPosition().y][state.getAgentPosition().x] = newAgentSpot;

			newState.setAgentPosition(new Point(state.getAgentPosition().x - 1, state.getAgentPosition().y));
			break;
		case "right":
			try {
				newAgentSpot = state.getBoard()[state.getAgentPosition().y ][state.getAgentPosition().x + 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid move");
				return state;
			}

			newState.getBoard()[state.getAgentPosition().y ][state.getAgentPosition().x + 1] = '@';
			newState.getBoard()[state.getAgentPosition().y][state.getAgentPosition().x] = newAgentSpot;

			newState.setAgentPosition(new Point(state.getAgentPosition().x + 1, state.getAgentPosition().y));
			
			break;
			default:
				System.out.println("Invalid move");
				
		}
		return newState;

	}
	
	public boolean checkValidMove(State state, String direction) {
		Point agent = state.getAgentPosition();
		
		switch(direction) {
		case "up":
			try {
				char tryIt = state.getBoard()[state.getAgentPosition().y -1][state.getAgentPosition().x];
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}

			break;
		case "down":
			try {
				char tryIt = state.getBoard()[state.getAgentPosition().y +1][state.getAgentPosition().x ];
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
			break;
		case "right":
			try {
				char tryIt = state.getBoard()[state.getAgentPosition().y ][state.getAgentPosition().x + 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
			break;
		case "left":
			try {
				char tryIt = state.getBoard()[state.getAgentPosition().y ][state.getAgentPosition().x - 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
			break;
		}
		
		return true;
	}

}
