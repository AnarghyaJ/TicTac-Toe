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
    static int globalDepth=3;
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
     frame=new JFrame("Play with Me!");
   // frame.setLayout(null);
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
         bestMove=decisionTree(globalDepth," ",-100000,100000,1,1);
        System.err.println(bestMove);
         board[(bestMove.charAt(0)-48)/3][(bestMove.charAt(0)-48)%3]='O';frame.repaint();
         enableClick=true;
    
    }}
    public static String decisionTree(int depth,String move,int alpha,int beta,int node,int player ){
    
     int value;
     int possibilities=0;
     String moves="";
     if(depth==0){return move+evaluateBoard();}
     char c=player==0?'O':'X';
     for(int i=0;i<9;i++){
        if(board[i/3][i%3]==' '){
            possibilities++;
            moves+=i;
            }
 }
     
        
     node=1-node;
     player=1-player;
     for(int i=0;i<moves.length();i++)
     {
     board[(moves.charAt(i)-48)/3][(moves.charAt(i)-48)%3]=c;
       
     String returnString=decisionTree(depth-1, moves.substring(i,i+1), alpha, beta, node, player);
     
    value=Integer.valueOf(returnString.substring(1));
    board[(moves.charAt(i)-48)/3][(moves.charAt(i)-48)%3]=' ';
    if (node==0) {
                
                if (value<beta) {beta=value; if (depth==globalDepth) {move=returnString.substring(0,1);}}
            } 
            else if(node==1){
                if (value>alpha) {alpha=value; if (depth==globalDepth) {move=returnString.substring(0,1);}}
            }
            if (alpha>=beta) {
                
                if (node==0) {return move+beta;}  return move+alpha;
            }
     }
    if (node==0) {return move+beta;}return move+alpha;
    }
   
   
  public static int evaluateBoard(){
    int computer=1-player;
   
    char c=computer==0?'O':'X';
    char d=player==0?'O':'X';
    int Ovalue=0,Xvalue=0;
    for(int i=0;i<9;i++){
    switch(board[i/3][i%3]){
    
        case 'O':Ovalue+=vicinityEval('O',i);break;
        case 'X':Xvalue+=vicinityEval('X',i);break;
    }
    if(computer==0)return Ovalue-Xvalue;
    return Xvalue-Ovalue;
    }

    return 1;}

public static int vicinityEval(char c,int i){
    int hcount=0,vcount=0,dcount=0;
    
try{
    for(int j=0;j<3;j++){
        if(board[j][i%3]==c)vcount+=3;
        if(board[j][i%3]==' ')vcount+=1;
        if(board[j][i%3]!=c&&board[j][i%3]!=' ')vcount=0;}
    
    
}catch(Exception t){}
if(vcount==9)return 1000000;
try{
for(int j=0;j<3;j++){
        if(board[i/3][j]==c)hcount+=3;
        if(board[i/3][j]==' ')hcount+=1;
        if(board[i/3][j]!=' '&&board[i/3][j]!=c)hcount=0;
}
}catch(Exception t){}

if(hcount==9)return 1000000;

try{
if(board[0][0]==c)dcount+=3;
if(board[1][1]==c)dcount+=3;
if(board[2][2]==c)dcount+=3;
if(board[0][0]==' ')dcount+=1;
if(board[1][1]==' ')dcount+=1;
if(board[2][2]==' ')dcount+=1;
if((board[0][0]!=' '&&board[0][0]!=c)||(board[1][1]!=' '&&board[1][1]!=c)||(board[2][2]!=' '&&board[2][2]!=c))dcount=0;

}
catch(Exception t){}
if(dcount==9)return 100000;
try{
if(board[0][2]==c)dcount+=3;
if(board[1][1]==c)dcount+=3;
if(board[2][0]==c)dcount+=3;
if(board[0][2]==' ')dcount+=1;
if(board[1][1]==' ')dcount+=1;
if(board[2][0]==' ')dcount+=1;
if((board[0][2]!=' '&&board[0][2]!=c)||(board[1][1]!=' '&&board[1][1]!=c)||(board[2][0]!=' '&&board[2][0]!=c))dcount=0;
}
catch(Exception t){}
if(dcount==9)return 100000;
return vcount+hcount+dcount;}
}
    
  
    
    
    
    

