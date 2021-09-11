public abstract class Kutia
{
	private Player player;
	private Player monster;

	public boolean hasPlayer() {
		return player != null;
	}

	public Player getPlayer() {
		if (hasPlayer()) {
			return player;
		}
		return null;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean hasMonster() {
		return monster != null;
	}

	public Player getMonster() {
		if (hasMonster()) {
			return monster;
		}
		return null;
	}

	public void setMonster(Player player) {
		this.monster = player;
	}

	public String toString() {
		if(monster != null)
		{
			return monster.toString();
		}
		else if(player != null)
		{
			return player.toString();
		}
		return  "";
	}
	
    abstract  boolean passable();
}