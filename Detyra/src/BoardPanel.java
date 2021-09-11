import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
	private Tabela board;
	private int width;
	private int height;
	private int boxWidth;
	private int boxHeight;
	private JLabel pointsLabel;

	public BoardPanel(Tabela board, int boxSize,JLabel pointsLabel) {
		this.board = board;
		width = boxSize * board.tableRow + 20;
		height = boxSize * board.tableColumn + 20;
		boxWidth = width / board.tableRow;
		boxHeight = height / board.tableColumn;
		this.pointsLabel =pointsLabel;
	}
	

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		this.pointsLabel.setText("Points:"+Game.player.getPoints());
		for (int i = 0; i < board.tableRow; i++) {
			for (int j = 0; j < board.tableColumn; j++) {
				int boxX = i * boxWidth;
				int boxY = j * boxHeight;
				g.drawRect(boxX, boxY, boxWidth, boxHeight);
				if (board != null) {
					String content = board.getBox(i, j).toString();
					g.drawString(content, boxY + boxHeight / 2, boxX + boxWidth / 2);
				}
			}
		}
	}

	public void updateDrawing() {
		repaint();
	}
}