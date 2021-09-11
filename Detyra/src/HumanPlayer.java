
public class HumanPlayer extends Player{

    private int points = 0;

    public int getPoints() {
        return points;
    }

    public void raisePoints(int points) {
        this.points += points;
    }

	@Override
	public void move(Tabela board,Direction dir) {
//		board.updatePlayerPosition(dir);
	}
	
	@Override
	public String toString() {
		return "Play";
	}
}
