import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanInput {
	State s;

	public static void main(String[] args) {
		new HumanInput(3).play();

	}

	public HumanInput(int size) {
		s = new State(size);
	}

	public void play() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (!s.checkSolved()) {
			try {
				s.printState();

				String move = br.readLine();

				if (s.checkMove(move)) {
					s.move(move);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("solved");

	}

}
