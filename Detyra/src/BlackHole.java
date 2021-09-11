
public class BlackHole extends Kutia {

	@Override
	public boolean passable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return super.hasPlayer() ? super.toString() : "B";
	}

}
