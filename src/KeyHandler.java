import java.awt.event.KeyEvent;
import java.awt.event.*;
public class KeyHandler implements KeyListener{
    panel dp;
    public KeyHandler(panel dp){
        this.dp = dp;

    }
    public void keyTyped(KeyEvent e){
    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_M) {
            dp.search("DFS"); //search options: A*, BFS, DFS
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
