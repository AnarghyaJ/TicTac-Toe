/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic.tac.to;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.OptionPaneUI;
import static tic.tac.to.TicTacTo.globalDepth;

/**
 *
 * @author Anarghya
 */
public class PaintPane extends JPanel implements MouseListener{

    static int mutex=0;
    public void paintComponent(Graphics g1){
        
    this.addMouseListener(this);
        Graphics2D g=(Graphics2D)g1;
        super.paintComponent(g);
    Color front,back;
    front=new Color(252, 182, 90);
    back=new Color(153,28,3,250);
    g.setColor(back);
    int fheight=TicTacTo.frame.getHeight();
    int fwidth=TicTacTo.frame.getWidth();
    g.fillRect(0,0,TicTacTo.frame.getWidth(),TicTacTo.frame.getHeight());
    
    g.setColor(front);
    g.setStroke(new BasicStroke(5));
    g.drawLine(TicTacTo.frame.getWidth()/3, 0, TicTacTo.frame.getWidth()/3, TicTacTo.frame.getHeight());
    g.drawLine(2*TicTacTo.frame.getWidth()/3, 0, 2*TicTacTo.frame.getWidth()/3, TicTacTo.frame.getHeight());
     g.drawLine( 0, TicTacTo.frame.getHeight()/3, TicTacTo.frame.getWidth(),TicTacTo.frame.getHeight()/3);
      g.drawLine( 0, 2*TicTacTo.frame.getHeight()/3, TicTacTo.frame.getWidth(),2*TicTacTo.frame.getHeight()/3);
     
      for(int i=0;i<9;i++){
          
          switch(TicTacTo.board[i/3][i%3]){
          
              case ' ':break;
              case 'O':
                  g.setFont(new Font("Serif", Font.BOLD, 120));
                  g.drawString("O", 70+(i%3)*(fwidth/3), 140+(i/3)*(fheight/3));
                  break;
              case 'X':g.setFont(new Font("Serif", Font.BOLD, 120));
                  g.drawString("X", 70+(i%3)*(fwidth/3), 140+(i/3)*(fheight/3));
                  break;
          }
      
      }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    
        
   
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
            
        
     if(TicTacTo.enableClick)
     {
        
    if(e.getButton()==MouseEvent.BUTTON1&&mutex==0){
         mutex++;
        TicTacTo.enableClick=false;
        int x=e.getX();
        int y=e.getY();
        int fx=TicTacTo.frame.getBounds().x;
        int fy=TicTacTo.frame.getBounds().y;
        int fw=TicTacTo.frame.getBounds().width;
        int fh=TicTacTo.frame.getBounds().height;
        char c=TicTacTo.player==0?'O':'X';
        char d;int dialogBox;
          Object option[]={"Play","Close"};
        if(c=='O')d='X';else d='O';
        
      
      
        try{
           
            if( TicTacTo.board[(y)/(fh/3)][(x)/(fw/3)]==' '){
            System.err.println("My Move:"+(y)/(fh/3)+" "+(x)/(fw/3)); 
            if(TicTacTo.checkStatus()==0){
       TicTacTo.board[(y)/(fh/3)][(x)/(fw/3)]=c;
       display();repaint();
            
             if(TicTacTo.checkStatus()<0){dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "You Won!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();}
             if(TicTacTo.checkStatus()>0){dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "You Lost",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();}
              if(TicTacTo.checkStatus()==1000){ dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "Draw!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();}}
            
            else{
            if(TicTacTo.checkStatus()>0){
                      
                     dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "You Lost",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();
                    }
            if(TicTacTo.checkStatus()==1000){
                      
                     dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "Draw!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();
                    }
                    else
                    {
                     dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "You Won!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();
                    
                    }
            
            }
            }
            else
            {TicTacTo.enableClick=true; return;}}catch(Exception t){}
        
        try{
      String returnMove= TicTacTo.decisionTree(TicTacTo.globalDepth,"",0,0,1,1-TicTacTo.player);
      
            System.err.println("Comp move:"+Integer.parseInt(returnMove.substring(0,1))/3+" "+Integer.parseInt(returnMove.substring(0,1))%3);
           
            
            int value=Integer.valueOf(returnMove.substring(1));
            try{
             if(TicTacTo.checkStatus()==0){
                 if(TicTacTo.board[Integer.valueOf(returnMove.substring(0, 1))/3][Integer.valueOf(returnMove.substring(0, 1))%3]==' ')
           TicTacTo.board[Integer.valueOf(returnMove.substring(0, 1))/3][Integer.valueOf(returnMove.substring(0, 1))%3]=d;
                 else{
                 returnMove= TicTacTo.decisionTree(TicTacTo.globalDepth,"",-100000,100000,1,1-TicTacTo.player);
                  TicTacTo.board[Integer.valueOf(returnMove.substring(0, 1))/3][Integer.valueOf(returnMove.substring(0, 1))%3]=d;
                 }
                 
           
           display();
           repaint();
             if(TicTacTo.checkStatus()<0){dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "You Won!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();}
             if(TicTacTo.checkStatus()>0){dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "You Lost",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();}
              if(TicTacTo.checkStatus()==1000){ dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "Draw!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();}
            TicTacTo.enableClick=true;
                }
                
                else{
                    if(TicTacTo.checkStatus()>0){
                      
                     dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "You Lost",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();
                    }
                    
                    if(TicTacTo.checkStatus()==1000){
                      
                     dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "Draw!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();
                    }
                    else
                    {
                     dialogBox=JOptionPane.showOptionDialog(TicTacTo.frame, "Match Ended!", "You Won!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
                    if(dialogBox==1)TicTacTo.frame.dispose();
                    
                    }
                    
                }
            }catch(Exception r){}
        
            
        }
        catch(ArrayIndexOutOfBoundsException t){
            if(y/(fh/3)==3) 
                if(x/(fw/3)==3)
                TicTacTo.board[2][2]=c;
                else
                    TicTacTo.board[2][x/(fw/3)]=c;

            if(y/(fh/3)<0) 
                if(x/(fw/3)<0)
                TicTacTo.board[0][0]=c;
                else
                    TicTacTo.board[0][x/(fw/3)]=c;


        }
     
        
    } mutex=0;
    
        
     }  
         
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
 
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
        
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        
    }
    public static void display(){
        System.err.println("================");
    for(int i=0;i<3;i++){
    for(int j=0;j<3;j++){
        
        if(TicTacTo.board[i][j]==' ')System.out.print("_|");
        else System.out.print(TicTacTo.board[i][j]+"|");}
    
        System.out.print("\n");}
    }
    
}
