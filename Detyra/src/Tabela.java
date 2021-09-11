import java.util.Iterator;
import java.util.Random;

public class Tabela {
	private Kutia[][] field;

	public static int tableRow = 5;
	public static int tableColumn = 5;
	public static Position currentPlayerPosition;
	public static Position currentMonsterPosition;

	static Random random = new Random();

	public Tabela(final int row, final int column) {
		if (row < 1 || column < 1) {
			// ketu duhet te hudhet ndoje exception por per demonstrim vetem nje mesazh
			// eshte shfaqur
			System.out.println("Vlerat e reshtave dhe kolonave duhet te jene pozitive");
		} else {
			this.tableRow = row;
			this.tableColumn = column;

			populate(row, column);
			System.out.println("rows:" + tableRow + " columns: " + tableColumn);
		}
	}

	private void setPlayerRandomLocation() {
		// Set Player to a random position
		currentPlayerPosition = new Position();
		int randomNum = Tabela.getRandomInteger(tableRow * tableColumn);
		currentPlayerPosition.setCoordinateFromPosition(randomNum);

		field[currentPlayerPosition.getX()][currentPlayerPosition.getY()] = new EmptySpace();
		field[currentPlayerPosition.getX()][currentPlayerPosition.getY()].setPlayer(new HumanPlayer());
	}

	private void setMonsterRandomLocation() {
		// Set Monster to a random position
		currentMonsterPosition = new Position();

		do {
			int randomNum = Tabela.getRandomInteger(tableRow * tableColumn);
			currentMonsterPosition.setCoordinateFromPosition(randomNum);

		} while (field[currentMonsterPosition.getX()][currentMonsterPosition.getY()] != null);

		field[currentMonsterPosition.getX()][currentMonsterPosition.getY()] = new EmptySpace();
		field[currentMonsterPosition.getX()][currentMonsterPosition.getY()].setMonster(new Monster());
	}

	private void setThesaraRandomLocation(int nr) {
		for (int i = 0; i < nr; i++) {
			Position p;
			do {
				p = new Position();
				int randomNum = Tabela.getRandomInteger(tableRow * tableColumn);
				p.setCoordinateFromPosition(randomNum);

			} while (field[p.getX()][p.getY()] != null);

			field[p.getX()][p.getY()] = new Thesari();
		}
	}

	private void setPengesaRandomLocation(int nr) {
		for (int i = 0; i < nr; i++) {
			Position p;
			do {
				p = new Position();
				int randomNum = Tabela.getRandomInteger(tableRow * tableColumn);
				p.setCoordinateFromPosition(randomNum);

			} while (field[p.getX()][p.getY()] != null);

			field[p.getX()][p.getY()] = new Pengesa();
		}
	}

	private void setBlackholesRandomLocation(int nr) {
		for (int i = 0; i < nr; i++) {
			Position p;
			do {
				p = new Position();
				int randomNum = Tabela.getRandomInteger(tableRow * tableColumn);
				p.setCoordinateFromPosition(randomNum);

			} while (field[p.getX()][p.getY()] != null);

			field[p.getX()][p.getY()] = new BlackHole();
		}
	}

	private void populate(int row, int column) {
		this.field = new Kutia[row][column];

		// Set player
		setPlayerRandomLocation();

		// Set Monster
		setMonsterRandomLocation();
		// Set Thesaret
		setThesaraRandomLocation(4);

		// Set Pengesa
		setPengesaRandomLocation(2);

		// Set Blackhole
		setBlackholesRandomLocation(1);

		for (int i = 0; i < tableRow; i++) {
			for (int j = 0; j < tableColumn; j++) {
				if (field[i][j] == null)
					field[i][j] = new EmptySpace();
			}
		}

		System.out.println("Fusha e lojes u krijua");
		System.out.println("Lojtari u vendos ne poziten [" + currentPlayerPosition.getX() + "] ["
				+ currentPlayerPosition.getY() + "]");
		System.out.println("Monsteri u vendos ne poziten [" + currentMonsterPosition.getX() + "] ["
				+ currentMonsterPosition.getY() + "]");
	}

	public void reset() {
		for (int rreshti = 0; rreshti < tableRow; rreshti++) {
			for (int shtylla = 0; shtylla < tableColumn; shtylla++) {
				field[rreshti][shtylla] = null;
			}
		}
		populate(tableRow, tableColumn);
	}

	public Kutia getBox(Position pozita) {
		if (areInsideBounds(pozita.getX(), pozita.getY())) {
			return field[pozita.getX()][pozita.getY()];
		}
		return null;
	}

	public Kutia getBox(int row, int col) {
		Position temp = new Position(row, col);
		return getBox(temp);
	}

	public static int getRandomInteger(int maximum) {
		return random.nextInt(maximum);
	}

	public Position getNewPlayerPostion(Direction dir) {
		Position newPosition = new Position();
		switch (dir) {
		case Left:
			newPosition.setX(this.currentPlayerPosition.getX());
			newPosition.setY(this.currentPlayerPosition.getY() - 1);
			break;
		case Right:
			newPosition.setX(this.currentPlayerPosition.getX());
			newPosition.setY(this.currentPlayerPosition.getY() + 1);
			break;
		case Up:
			newPosition.setX(this.currentPlayerPosition.getX() - 1);
			newPosition.setY(this.currentPlayerPosition.getY());
			break;
		case Down:
			newPosition.setX(this.currentPlayerPosition.getX() + 1);
			newPosition.setY(this.currentPlayerPosition.getY());
			break;
		default:
			break;
		}
		return newPosition;
	}

	public Position getNewMonsterPostion(Direction dir) {
		Position newPosition = new Position();
		switch (dir) {
		case Left:
			newPosition.setX(this.currentMonsterPosition.getX());
			newPosition.setY(this.currentMonsterPosition.getY() - 1);
			break;
		case Right:
			newPosition.setX(this.currentMonsterPosition.getX());
			newPosition.setY(this.currentMonsterPosition.getY() + 1);
			break;
		case Up:
			newPosition.setX(this.currentMonsterPosition.getX() - 1);
			newPosition.setY(this.currentMonsterPosition.getY());
			break;
		case Down:
			newPosition.setX(this.currentMonsterPosition.getX() + 1);
			newPosition.setY(this.currentMonsterPosition.getY());
			break;
		default:
			break;
		}
		return newPosition;
	}

	public void updatePlayerPosition(Direction dir) {

		Position newPosition = getNewPlayerPostion(dir);

		boolean isInsideBounds = areInsideBounds(newPosition.getX(), newPosition.getY());

		Kutia kutia = getBox(newPosition);

		if (kutia != null && kutia.passable() && kutia.getMonster() == null) {
			if (kutia instanceof BlackHole) {
				Game.endGame();
			} else if (kutia instanceof Thesari) {
				int points = ((Thesari) kutia).getPoints();
				Game.player.raisePoints(points);
				field[newPosition.getX()][newPosition.getY()] = new EmptySpace();
			}

			// largoje nga pozita e kaluar
			field[currentPlayerPosition.getX()][currentPlayerPosition.getY()].setPlayer(null);

			// vendose ne poziten e re
			field[newPosition.getX()][newPosition.getY()].setPlayer(new HumanPlayer());
			this.currentPlayerPosition = newPosition;
		}

	}

	public void updateMonsterPosition(Direction dir) {

		Position newPosition = getNewMonsterPostion(dir);

		boolean isInsideBounds = areInsideBounds(newPosition.getX(), newPosition.getY());

		Kutia kutia = getBox(newPosition);

		if (kutia != null && kutia.passable()) {

			// largoje nga pozita e kaluar
			field[currentMonsterPosition.getX()][currentMonsterPosition.getY()].setMonster(null);

			// vendose ne poziten e re
			field[newPosition.getX()][newPosition.getY()].setMonster(new Monster());
			this.currentMonsterPosition = newPosition;

			if (kutia.getPlayer() != null) {
				Game.endGame();
			}

		}

	}

	public static boolean areInsideBounds(int xPosition, int yPosition) {
		return xPosition >= 0 && xPosition < tableRow && yPosition >= 0 && yPosition < tableColumn;
	}
}
