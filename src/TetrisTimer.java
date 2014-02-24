import java.util.TimerTask;


public class TetrisTimer extends TimerTask {

	private Tetris game;
	public TetrisTimer(Tetris game) {
		this.game = game;
	}
	@Override
	public void run() {
		game.timer();
	}

}
