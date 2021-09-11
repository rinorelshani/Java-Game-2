import java.util.Random;

public class Thesari extends Kutia {
	private int points;

	public Thesari() {
		setRandomPoints();
	}

	public int getPoints() {
		return points;
	}

	public void setRandomPoints() {
		this.points = Tabela.getRandomInteger(5)+1;
		// testing
		System.out.println(this.points);
	}

	@Override
	public boolean passable() {
		return true;
	}
	
	@Override
	public String toString() {
		return super.hasPlayer() ? super.toString(): "Th";
	}
}
