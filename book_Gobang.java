


import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Container;  
import java.awt.Font;  
import java.awt.GridLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;

import javax.swing.JApplet;  
import javax.swing.JButton;  
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;  
import javax.swing.JTextField;
public class book_Gobang extends JApplet implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static int BOARD_SIZE = 25;//定义棋盘大小。
    private boolean who = true;
    private boolean result=false;
    private boolean continuer=true;
    private int count=0;;
    public JTextField textfield =new JTextField("黑方先手");
    public  JButton board[][]=new JButton[BOARD_SIZE][BOARD_SIZE];

    public void init() {//创建棋盘。
        Container c= getContentPane();
        JPanel panel = new JPanel();
        c.add(textfield,BorderLayout.NORTH);
        c.add(panel,BorderLayout.CENTER);
        panel.setLayout(new GridLayout(BOARD_SIZE,BOARD_SIZE,0,0));//布局
        for(int i=0;i<BOARD_SIZE;i++) {//添加按钮
            for(int j=0;j<BOARD_SIZE;j++) {
                board[i][j]=new JButton("");
                board[i][j].setBackground(Color.white);
                board[i][j].setFont(new Font("宋体",Font.PLAIN,2));
                panel.add(board[i][j]);
                board[i][j].addActionListener(this);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("+")||e.getActionCommand().equals("-")) {
            textfield.setText("您不能下在已经有子的地方");
        }else if(who) {
                for(int i=0;i<BOARD_SIZE;i++) {
                    for(int j=0;j<BOARD_SIZE;j++) {
                        if(e.getSource()==board[i][j]) {
                            board[i][j].setBackground(Color.BLACK);
                            board[i][j].setText("+");
                        }
                    }
                }
                if(win("+")) {//判断是否胜利。
                    JOptionPane.showMessageDialog(null, "黑方胜利！");
                    textfield.setText("黑方胜利！");
                }else {//否则对方落子。
                    textfield.setText("请红方落子");
                    who = false;
                }
        }else {
            for(int i=0;i<BOARD_SIZE;i++) {
                for(int j=0;j<BOARD_SIZE;j++) {
                    if(e.getSource()==board[i][j]) {
                        board[i][j].setBackground(Color.red);
                        board[i][j].setText("-");
                    }
                }
            }
            if(win("-")) {//判断是否胜利
                JOptionPane.showMessageDialog(null, "红方胜利！");
                textfield.setText("红方胜利！");
            }else {
                textfield.setText("请黑方落子");
                who = true;
            }
        }
    }
    public boolean win(String player) {//疯狂遍历二阶数组。
        count=0;
        outer://外循环
        for(int i=0;i<BOARD_SIZE;i++) {//横向遍历
            for(int j=0;j<(BOARD_SIZE-4);j++) {//中间循环
                if(board[i][j].getText().equals(player)) {
                    iner://内循环
                    for(int k=j;k<(j+5);k++) {
                        if(board[i][k].getText().equals(player)) {
                            count++;
                            if(count==5) {//五子连珠
                                count=0;
                                result = true;//win=true;
                                continuer = false;//第一种判断win=true不再继续判断。
                                break outer;//跳出外循环，返回result
                            }
                        }else {
                            count=0;
                            result = false;
                            break iner;//跳出内循环，继续遍历。
                        }
                    }
                } 
            }
        }
        if(continuer) {
        count=0;
        outer:
        for(int j=0;j<BOARD_SIZE;j++) {//纵向遍历
            for(int i=0;i<(BOARD_SIZE-4);i++) {
                if(board[i][j].getText().equals(player)) {
                    iner:
                    for(int k=i;k<(i+5);k++) {
                        if(board[k][j].getText().equals(player)) {
                            count++;
                            if(count==5) {
                                count=0;
                                result = true;
                                continuer = false;
                                break outer;
                            }
                        }else {
                            count=0;
                            result = false;
                            break iner;
                        }
                    }
                } 
            }
        }
        }
        if(continuer) {
        count=0;
        outer:
        for(int i=0;i<(BOARD_SIZE-4);i++) {//左下遍历
            for(int j=0;j<(BOARD_SIZE-4);j++) {
                if(board[i][j].getText().equals(player)) {
                    int x = i;
                    iner:
                    for(int k=j;k<(j+5);k++,x++) {
                        if(board[x][k].getText().equals(player)) {
                            count++;
                            if(count==5) {
                                count=0;
                                result = true;
                                continuer = false;
                                break outer;
                            }
                        }else {
                            count=0;
                            result = false;
                            break iner;
                        }
                    }
                } 
            }
        }
        }
        if(continuer) {//右下遍历
        count=0;
        outer:
        for(int i=0;i<(BOARD_SIZE-4);i++) {
            for(int j=4;j<BOARD_SIZE;j++) {
                if(board[i][j].getText().equals(player)) {
                    int x = i;
                    iner:
                    for(int k=j;k<(j+5);k--,x++) {
                        if(board[x][k].getText().equals(player)) {
                            count++;
                            if(count==5) {
                                count=0;
                                result = true;
                                continuer = false;
                                break outer;
                            }
                        }else {
                            count=0;
                            result = false;
                            break iner;
                        }
                    }
                } 
            }
        }
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame("五子棋");
        book_Gobang gb= new book_Gobang();
        frame.getContentPane().add(gb, BorderLayout.CENTER);    
        gb.init();
        gb.start();
        frame.setSize(550,574);
        frame.setVisible(true);
        }
    }

