import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanInput {
	Game g;
	
	public static void main(String[] args) {
		new HumanInput(4).play();
		
	}
	
	public HumanInput(int size) {
		g = new Game(size);
	}
	
	public void play() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(!g.checkSolved()) {
			try {
				g.getCurrent().printState();
				g.move(br.readLine());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("solved");
		
		
	}
	
}
