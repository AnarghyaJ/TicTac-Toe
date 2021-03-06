/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic.tac.to;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Anarghya
 */
public class TicTacTo extends JFrame{
    static int globalDepth=6;//look ahead moves
 
    static JFrame frame;
    static int player=-1;
    private Graphics g;
    static char board[][]={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    
    public static boolean enableClick=false;//more on this later!
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new TicTacTo();
        
        // TODO code application logic here
    }
    
    public  TicTacTo(){
       
     frame=new JFrame("Play Tic Tac Toe!");
frame.setAlwaysOnTop(true);
  String bestMove = "";
   frame.setBounds(200, 100, 700, 600);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setMaximizedBounds(getBounds());
   frame.setMinimumSize(frame.getSize());
   PaintPane p=new PaintPane();
   frame.add(p);
   Object option[]={"O","X"};
    int player=JOptionPane.showOptionDialog(frame, "What is your Flavour of the day!", "Player Plays...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
    if(player==-1)frame.dispose();
    
    // 0 indicates O 1 indicates X
    this.player=player;
    
    if(player==0){
    enableClick=true;
 
  
    }
    else {
         bestMove=decisionTree(globalDepth,"",Integer.MIN_VALUE,Integer.MAX_VALUE,1,1-player);
       
        board[(bestMove.charAt(0)-48)/3][(bestMove.charAt(0)-48)%3]='O';PaintPane.display();frame.repaint();
         enableClick=true;
    
    }}
    
 
    public static String decisionTree(int depth,String move,int alpha,int beta,int node,int player){
    
     int value;
    if(node==1)value=alpha;else value=beta;
    int returnValue;
     int possibilities=0;
     String moves="";
     if(depth==0){
         
         if(checkStatus(depth)==1000||checkStatus(depth)==0){
            
       // PaintPane.display();
        return move+Integer.toString(checkStatus(depth));
         }
         return move+Integer.toString(checkStatus(depth));}
     char c=player==0?'O':'X';
     for(int i=0;i<9;i++){
        if(board[i/3][i%3]==' '){
            possibilities++;
            moves+=Integer.toString(i);
            }
 }
       
        if(possibilities==0&&player==1){return move+checkStatus(depth);}
       // if(possibilities==0&&player==0){return move+checkStatus(depth);}
       
     for(int i=0;i<moves.length();i++)
     {
     board[(moves.charAt(i)-48)/3][(moves.charAt(i)-48)%3]=c;
       
     String returnString=decisionTree(depth-1, moves.substring(i,i+1), alpha, beta, 1-node, 1-player);
        // System.err.println("returned:"+returnString);  
    returnValue=Integer.valueOf(returnString.substring(1));
    
    board[(moves.charAt(i)-48)/3][(moves.charAt(i)-48)%3]=' ';
    if (node==0) {
                
                if (returnValue<=beta) {beta=returnValue; if (depth==globalDepth) {move=returnString.substring(0,1);}}
            } 
            else if(node==1){
                if (returnValue>alpha) {alpha=returnValue; if (depth==globalDepth) {move=returnString.substring(0,1);}}
            }
            if (alpha>beta) {
                
                if (node==0) {return move+beta;}  return move+alpha;
            }
     }
     
     if(node==1)
     return move+alpha;return move+beta;
     
   
    }
   
   public static int checkStatus(int depth){
   char c=player==0?'O':'X';
   if(depth==0)depth=1;
   char d=c=='O'?'X':'O';
   if(board[0][0]==c&&board[0][1]==c&&board[0][2]==c)return -10000-depth;
    if(board[1][0]==c&&board[1][1]==c&&board[1][2]==c)return -10000-depth;
     if(board[2][0]==c&&board[2][1]==c&&board[2][2]==c)return -10000-depth;
      if(board[0][0]==c&&board[1][0]==c&&board[2][0]==c)return -10000-depth;
      if(board[0][1]==c&&board[1][1]==c&&board[2][1]==c)return -10000-depth;
      if(board[0][2]==c&&board[1][2]==c&&board[2][2]==c)return -10000-depth;
      if(board[0][0]==c&&board[1][1]==c&&board[2][2]==c)return -10000-depth;
      if(board[0][2]==c&&board[1][1]==c&&board[2][0]==c)return -10000-depth;
      
      
        if(board[0][0]==d&&board[0][1]==d&&board[0][2]==d)return 10000+depth;
    if(board[1][0]==d&&board[1][1]==d&&board[1][2]==d)return 10000+depth;
     if(board[2][0]==d&&board[2][1]==d&&board[2][2]==d)return 10000+depth;
      if(board[0][0]==d&&board[1][0]==d&&board[2][0]==d)return 10000+depth;
      if(board[0][1]==d&&board[1][1]==d&&board[2][1]==d)return 10000+depth;
      if(board[0][2]==d&&board[1][2]==d&&board[2][2]==d)return 10000+depth;
      if(board[0][0]==d&&board[1][1]==d&&board[2][2]==d)return 10000+depth;
      
      
      for(int i=0;i<9;i++){
      if(board[i/3][i%3]==' ')return 0;}
      return 1000;
    
    
   }}
  


    
  
    
    
    
    

