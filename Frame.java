import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Frame extends JFrame implements MouseListener {
    private boolean playFlg = false;
    private final int GRID_SIZE = 64;
    private final int GRID_WIDTH = 64;
    private final int GRID_HEIGHT = 64;
    private final int FRAME_WIDTH = 64 * 4;
    private final int FRAME_HEIGT = 64 * 4;
    public ImageIcon[] iconArray = new ImageIcon[4 * 4];
    public JLabel[] jlabelArray = new JLabel[4 * 4];
    public Algorithm algo = new Algorithm();
    
    public Frame() {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        for (int i = 1; i < iconArray.length; i++) {
            iconArray[i] = new ImageIcon("./block/" + decimalFormat.format(i) + ".gif");
        }
        for (int i = 1; i < jlabelArray.length; i++) {
            jlabelArray[i] = new JLabel(iconArray[i]);
            this.getContentPane().add(jlabelArray[i]);
        }
        this.setLayout(null);
        this.setSize(FRAME_WIDTH + 14, FRAME_HEIGT + 37);
        this.setIcon(algo);
        this.getContentPane().addMouseListener(this);
    }
    
    public void setIcon(Algorithm algo) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                int v = algo.getValue(x, y);
                if (v == 0) { continue; }
                jlabelArray[v].setBounds(x * GRID_SIZE, y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {
        if(this.algo.getClearFlg() == true) {
            System.out.println("ゲームクリア");
            this.getContentPane().add(new JLabel(new ImageIcon("./sample.gif")));

        }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        if (playFlg == false) {
            System.out.println("ゲームスタート");
            this.algo.shuffle();
            playFlg = true;
        }
        int indexX = e.getX() / GRID_SIZE;
        int indexY = e.getY() / GRID_SIZE;
        if (this.algo.Move(indexX, indexY) == false) {
            this.algo.jumpMove(indexX, indexY);
        }
        this.setIcon(algo);

        if (algo.getClearFlg()) {
            showDialog("ゲームクリア");
            showDialog("クリックすると始まります");
            algo.shuffle();
        }
    }

    public void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}