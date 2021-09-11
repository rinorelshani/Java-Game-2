public class Worker implements Runnable {
	Tabela board;
	WindowDisplay display;
	
	public Worker(Tabela board,WindowDisplay display) {
		this.board = board;
		this.display = display;
	}
	
	public void run() {
		while(true)
		{
			//Choose a random Direction to move to
			Direction randomDirection = Direction.values()[Tabela.getRandomInteger(4)];
			board.updateMonsterPosition(randomDirection);
			display.update();
			try {
				Thread.sleep(1700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}