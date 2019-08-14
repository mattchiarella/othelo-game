/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othelo;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author stMa429Ch7951
 */
    public class Othelo extends JFrame{
    public boolean Turn = true;
    public static JLabel[][] lbl = new JLabel[8][8];
    public static int[][] temp = new int[7][7]; 
    public Othelo() {
        int x;
        int y = 0 ;
        
        for (int i = 0; i < 8; i++)
        {
            y = y + 80;
            x = 0;
            for (int j = 0; j < 8; j++)
            {
                x = x + 80;
                lbl[i][j] = new JLabel();
                lbl[i][j].setVisible(true);
                this.add(lbl[i][j]);
                lbl[i][j].setBounds(x,y,80,80);
                lbl[i][j].setBorder(BorderFactory.createLineBorder(Color.red));
                lbl[i][j].setBackground(Color.blue);
                lbl[i][j].setOpaque(true);
                
            }
        }

        drawPiece("White.jpg",3,3);
        drawPiece("White.jpg",4,4);
        drawPiece("Black.jpg",4,3);
        drawPiece("Black.jpg",3,4);
        MouseListener m = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                JComponent jc = (JComponent)e.getSource();
                int a = jc.getX()/85;
                int b = jc.getY()/85;
                if (temp[b][a] == 0)
                {
                    SouthEast(a,b,Turn);
                    SouthWest(a,b,Turn);
                    NorthEast(a,b,Turn);
                    NorthWest(a,b,Turn);
                    North(a, b, Turn);
                    South(a, b, Turn);
                    East(a,b,Turn);
                    West(a,b,Turn);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
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
        };
        try
        {
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        for (int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                lbl[i][j].addMouseListener(m);
        setLayout(null);
        setSize(830,850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void drawPiece (String str, int a, int b)
    {
        File f = new File(str);
        BufferedImage img;
        try{
            img = ImageIO.read(f);
            Image img2 = img.getScaledInstance(lbl[a][b].getWidth(), lbl[a][b].getHeight(), Image.SCALE_SMOOTH);
            ImageIcon ic = new ImageIcon(img2);
            lbl[b][a].setIcon(ic);
        }catch (IOException e){
            System.out.println("Incorrect file name");
        }
        if (str == "White.jpg")
        {
            temp[b][a] = 1;
        }
            
        else
        {
            temp[b][a] = 2;
        }
            
    }
    
    public static int[][] create2dArray()
    {
        for (int[] temp1 : temp) {
            for (int j = 0; j < temp1.length; j++) {
                temp1[j] = 0;
            }
        }
        return temp;
    }
 
    public void North(int a, int b, boolean d)
    {
        int save = 0;
        if (d == true)
        {
            
            for (int i = b - 1; i > 0; i--)
            {
                if (temp[i][a] == 0)
                {
                    return;
                }
                if (temp[i][a] == 1)
                {
                    save = i;
                    break;
                }
            }
            if (save < b - 1)
            {
               for (int i = b; i >= save; i--)
                {
                    drawPiece ("White.jpg", a, i);
                    System.out.println("North");
                } 
               Turn = false;
            }
        }
        else if (d == false)
        {
            
            for (int i = b - 1; i > 0; i--)
            {
                if (temp[i][a] == 0)
                {
                    return;
                }
                if (temp[i][a] == 2)
                {
                    save = i;
                    break;
                }
            }
            if (save < b - 1)
            {
               for (int i = b; i >= save; i--)
                {
                    drawPiece ("Black.jpg", a, i);
                    System.out.println("North");
                } 
               Turn = true;
            }
        }
    }
    
    public void South(int a, int b, boolean d)
    {
        int save = 0;
        if (d == true)
        {
            
            for (int i = b + 1; i < 8; i++)
            {
                if (temp[i][a] == 0)
                {
                    return;
                }
                if (temp[i][a] == 1)
                {
                    save = i;
                    break;
                }
            }
            if (save > b + 1)
            {
               for (int i = b; i <= save; i++)
                {
                    drawPiece ("White.jpg", a, i);
                    System.out.println("South");
                } 
               Turn = false;
            }
        }
        else if (d == false)
        {
            
            for (int i = b + 1; i < 8; i++)
            {
                if (temp[i][a] == 0)
                {
                    return;
                }
                if (temp[i][a] == 2)
                {
                    save = i;
                    break;
                }
            }
            if (save > b + 1)
            {
               for (int i = b; i <= save; i++)
                {
                    drawPiece ("Black.jpg", a, i);
                    System.out.println("South");
                } 
               Turn = true;
            }
        }
    }
    
    public void East(int a, int b, boolean d)
    {
        int save = 0;
        if (d == true)
        {
            
            for (int i = a + 1; i < 8; i++)
            {
                if (temp[b][i] == 0)
                {
                    return;
                }
                if (temp[b][i] == 1)
                {
                    save = i;
                    break;
                }
            }
            if (save > a + 1)
            {
               for (int i = a; i <= save; i++)
                {
                    drawPiece ("White.jpg", i, b);
                    System.out.println("East");
                } 
               Turn = false;
            }
        }
        else if (d == false)
        {
            
            for (int i = a + 1; i < 8; i++)
            {
                if (temp[b][i] == 0)
                {
                    return;
                }
                if (temp[b][i] == 2)
                {
                    save = i;
                    break;
                }
            }
            if (save > a + 1)
            {
               for (int i = a; i <= save; i++)
                {
                    drawPiece ("Black.jpg", i, b);
                    System.out.println("East");
                } 
               Turn = true;
            }
        }
    }
    public void West(int a, int b, boolean d)
{
        int save = 0;
        if (d == true)
        {
            
            for (int i = a - 1; i > 0; i--)
            {
                if (temp[b][i] == 0)
                {
                    return;
                }
                if (temp[b][i] == 1)
                {
                    save = i;
                    break;
                }
            }
            if (save < a - 1)
            {
               for (int i = a; i >= save; i--)
                {
                    drawPiece ("White.jpg", i, b);
                    System.out.println("West");
                } 
               Turn = false;
            }
        }
        else if (d == false)
        {
            
            for (int i = a - 1; i > 0; i--)
            {
                if (temp[b][i] == 0)
                {
                    return;
                }
                if (temp[b][i] == 2)
                {
                    save = i;
                    break;
                }
            }
            if (save < a - 1)
            {
               for (int i = a; i >= save; i--)
                {
                    drawPiece ("Black.jpg", i, b);
                    System.out.println("West");
                } 
               Turn = true;
            }
        }
    }
    public void NorthWest(int a, int b, boolean d)
    {
        int Min;
        Min = Math.min(a,b);
        int save = 0;
        if (d == true)
        {
            for (int i = 1; i <= Min; i++)
            {
                if (temp[b-i][a-i] == 0)
                {
                    return;
                }
                if (temp[b-i][a-i] == 2)
                {
                    if (temp[(b-i)-1][(a-i)-1] == 1)
                    {
                        save = i;
                        break;
                    }
                }
            }
            if (save < Min)
            {
               for (int i = 0; i <= save; i++)
                {
                    drawPiece ("White.jpg", a-i, b-i);
                    System.out.println("North West");
                } 
               Turn = false;
            }
        }
        else if (d == false)
        {
            for (int i = 1; i <= Min; i++)
            {
                if (temp[b-i][a-i] == 0)
                {
                    return;
                }
                if (temp[b-i][a-i] == 1)
                {
                    if (temp[(b-i)-1][(a-i)-1] == 2)
                    {
                        save = i;
                        break;
                    }
                }
            }
            if (save < Min)
            {  
               for (int i = 0; i <= save; i++)
                {
                    drawPiece ("Black.jpg", a-i, b-i);
                    System.out.println("North West");
                } 
               Turn = true;
            }
        }
    }
    public void NorthEast(int a, int b, boolean d)
    {
        int Min;
        Min = Math.min(a,b);
        int save = 0;
        if (d == true)
        {
            for (int i = 1; i <= Min; i++)
            {
                if (temp[a-i][b+i] == 0)
                {
                    return;
                }
                if (temp[a-i][b+i] == 2)
                {
                    if (temp[(a-i)-1][(b+i)+1] == 1)
                    {
                        save = i;
                        break;
                    }
                }
            }
            if (save < Min)
            {
               for (int i = 0; i <= save; i++)
                {
                    drawPiece ("White.jpg", a-i, b+i);
                    System.out.println("North East");
                } 
               Turn = false;
            }
        }
        else if (d == false)
        {
            for (int i = 1; i <= Min; i++)
            {
                if (temp[a-i][b+i] == 0)
                {
                    return;
                }
                if (temp[a-i][b+i] == 1)
                {
                    if (temp[(a-i)-1][(b+i)+1] == 2)
                    {
                        if (temp[(a+i)+1][(b+i)+1] == 1)
                        {
                            save = i;
                            break;
                        }
                    }
                }
            }
            if (save < Min)
            {  
               for (int i = 0; i <= save; i++)
                {
                    drawPiece ("Black.jpg", a-i, b+i);
                    System.out.println("North East");
                } 
               Turn = true;
            }
        }
    }
    public void SouthEast(int a, int b, boolean d)
    {
        int Max;
        Max = Math.max(a,b);
        int save = 0;
        if (d == true)
        {
            for (int i = 1; i <= Max; i++)
            {
                if (temp[a+i][b+i] == 0)
                {
                    return;
                }
                if (temp[a+i][b+i] == 2)
                {
                    if (temp[(a+i)+1][(b+i)+1] == 1)
                    {
                        save = i;
                        break;
                    }
                }
            }
            if (save < Max)
            {
               for (int i = 0; i <= save; i++)
                {
                    drawPiece ("White.jpg", a+i, b+i);
                    System.out.println("South East");
                    
                } 
               Turn = false;
            }
        }
        else if (d == false)
        {
            for (int i = 1; i <= Max; i++)
            {
                if (temp[a+i][b+i] == 0)
                {
                    return;
                }
                if (temp[a+i][b+i] == 1)
                {
                    if (temp[(a+i)+1][(b+i)+1] == 2)
                        {
                            save = i;
                            System.out.println(save+" "+Max+" "+Turn);
                            break;
                        }
                }
            }
            if (save < Max)
            {  
               for (int i = 0; i <= save; i++)
                {
                    drawPiece ("Black.jpg", a+i, b+i);
                    System.out.println("South East");
                } 
               Turn = true;
            }
        }
    }
    public void SouthWest(int a, int b, boolean d)
    {
        {
        int Max;
        Max = Math.max(a,b);
        int save = 0;
        if (d == true)
        {
            for (int i = 1; i <= Max; i++)
            {
                if (temp[a+i][b-i] == 0)
                {
                    return;
                }
                if (temp[a+i][b-i] == 2)
                {
                    if (temp[(a+i)+1][(b-i)-1] == 1)
                    {
                        save = i;
                        break;
                    }
                }
                
            }
            if (save < Max)
            {
               for (int i = 0; i <= save; i++)
                {
                    drawPiece ("White.jpg", a+i, b-i);
                    System.out.println("South West");
                } 
               Turn = false;
            }
        }
        else if (d == false)
        {
            for (int i = 1; i <= Max; i++)
            {
                if (temp[a+i][b-i] == 0)
                {
                    return;
                }
                if (temp[a+i][b-i] == 1)
                {
                    if (temp[(a+i)+1][(b-i)-1] == 2)
                    {
                        save = i;
                        break;
                    }
                }
            }
            if (save < Max)
            {  
               for (int i = 0; i <= save; i++)
                {
                    drawPiece ("Black.jpg", a+i, b-i);
                    System.out.println("South West");
                } 
               Turn = true;
            }
        }
    }
    }
    public static void main(String[] args) 
    {
        new Othelo();
    }
    }