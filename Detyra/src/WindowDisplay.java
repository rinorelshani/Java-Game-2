import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowDisplay extends JFrame implements KeyListener {
	private BoardPanel pnlBoard;
	private Tabela board;
	private HumanPlayer player;
	private int boxSize = 50;
	private JLabel pointsLabel;

	/**
	 * @wbp.parser.constructor
	 */
	public WindowDisplay(Tabela board, HumanPlayer player) {
		this("My Board", board, player);
	}

	public WindowDisplay(String title, Tabela board, HumanPlayer player) {
		this.board = board;
		this.player = player;
		setTitle(title);
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		
		pointsLabel = new JLabel("Points:");
		getContentPane().add(pointsLabel, BorderLayout.NORTH);
		
		pnlBoard = new BoardPanel(board, boxSize,pointsLabel);
		content.add(pnlBoard, BorderLayout.CENTER);



		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 200);
		pack();
		setVisible(true);
	}

	public void update() {
		pnlBoard.updateDrawing();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			board.updatePlayerPosition(Direction.Right);
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			board.updatePlayerPosition(Direction.Left);
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			board.updatePlayerPosition(Direction.Down);
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			board.updatePlayerPosition(Direction.Up);
		update();
	}
}