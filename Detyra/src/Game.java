import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Game {

	private Tabela board;
	private static WindowDisplay display;
	public static HumanPlayer player = new HumanPlayer();

	public Game(Tabela board, WindowDisplay ui) {
		this.board = board;
		this.display = ui;
		ui.update();
	}

	public static void endGame() {
			display.dispose();
			System.exit(0);
	}

	public void reset() {
		board.reset();
		display.update();
	}
	
	public void activateMonster(WindowDisplay display)
	{
		Worker worker = new Worker(board,display);
		Thread th = new Thread(worker);
		th.start();
	}

	public static void main(String[] args) {
		int boardSize = 8;

		Tabela board = new Tabela(boardSize, boardSize);
		WindowDisplay display = new WindowDisplay(board, new HumanPlayer());
		Game mygame = new Game(board, display);
		mygame.activateMonster(display);
	}

}