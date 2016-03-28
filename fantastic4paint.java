package fantastic4paint;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
public class fantastic4paint extends Applet implements ActionListener,MouseListener,MouseMotionListener{
	
	 final int MAX_X           = 800;
	 final int MAX_Y           = 600;
     final int  NO_OP          = 0;
     final int  PEN_OP         = 1;
     final int  CLEAR_OP       = 4;
//initial mouse position
     int mousex                = 0;
     int mousey                = 0;

     int prevx                 = 0;
     int prevy                 = 0;

    boolean initialPen            = true;

 int    opStatus           = PEN_OP;
 int    colorStatus        = 1;
Color  mainColor          = new Color(0,0,0);
Color  statusBarColor     = Color.lightGray;

Button penButton          = new Button("Pen");

 Button clearButton        = new Button("Clear");
 Button blackButton        = new Button("Black");
Button blueButton         = new Button("Blue");
 Button redButton          = new Button("Red");
 Button greenButton        = new Button("Green");
 Button purpleButton       = new Button("Purple");
 Button orangeButton       = new Button("Orange");
 Button pinkButton         = new Button("Pink");
Button grayButton         = new Button("Gray");
 Button yellowButton       = new Button("Yellow");


 TextField colorStatusBar  = new TextField(20);
 TextField opStatusBar     = new TextField(20);
 TextField mouseStatusBar  = new TextField(10);

 Label operationLabel      = new Label("   Tool mode:");
 Label colorLabel          = new Label("   Color mode:");
Label cursorLabel         = new Label("   Cursor:");


  Panel controlPanel        = new Panel(new GridLayout(11,1,6,6));
  Panel drawPanel           = new Panel();
  Panel statusPanel         = new Panel();


public void init()
{
   setLayout(new BorderLayout());

 
   controlPanel.add(blackButton);
   controlPanel.add(blueButton);
   controlPanel.add(redButton);
   controlPanel.add(greenButton);
   controlPanel.add(purpleButton);
   controlPanel.add(orangeButton);
   controlPanel.add(pinkButton);
   controlPanel.add(grayButton);
   controlPanel.add(yellowButton);


   blueButton.setBackground(Color.blue);
   redButton.setBackground(Color.red);
   greenButton.setBackground(Color.green);
   purpleButton.setBackground(Color.magenta);
   orangeButton.setBackground(Color.orange);
   pinkButton.setBackground(Color.pink);
   grayButton.setBackground(Color.gray);
   yellowButton.setBackground(Color.yellow);

   controlPanel.add(penButton);
  
   controlPanel.add(clearButton);

 
   controlPanel.setBounds(0,0,100,300);
 
   statusPanel.add(colorLabel);
   statusPanel.add(colorStatusBar);


   statusPanel.add(operationLabel);
   statusPanel.add(opStatusBar);


   statusPanel.add(cursorLabel);
   statusPanel.add(mouseStatusBar);

   colorStatusBar.setEditable(false);
   opStatusBar.setEditable(false);
   mouseStatusBar.setEditable(false);

   statusPanel.setBackground(statusBarColor);
   controlPanel.setBackground(Color.black);
   drawPanel.setBackground(Color.white);
   add(statusPanel, "South");
   add(controlPanel, "East");
   add(drawPanel, "Center");

  
   penButton.addActionListener(this);
 
   clearButton.addActionListener(this);
      
   blackButton.addActionListener(this);
   blueButton.addActionListener(this);
   redButton.addActionListener(this);
   greenButton.addActionListener(this);
   purpleButton.addActionListener(this);
   orangeButton.addActionListener(this);
   pinkButton.addActionListener(this);
   grayButton.addActionListener(this);
   yellowButton.addActionListener(this);
   drawPanel.addMouseMotionListener(this);
   drawPanel.addMouseListener(this);
   this.addMouseListener(this);
   this.addMouseMotionListener(this);

 

   opStatusBar.setText("Pen");
   colorStatusBar.setText("Black");
}



public void actionPerformed(ActionEvent e)
{
 
   if (e.getActionCommand() == "Pen")
      opStatus = PEN_OP;

   if (e.getActionCommand() == "Clear")
      opStatus = CLEAR_OP;

   if (e.getActionCommand() == "Black")
      colorStatus = 1;

   if (e.getActionCommand() == "Blue")
      colorStatus = 2;

   if (e.getActionCommand() == "Green")
      colorStatus = 3;

   if (e.getActionCommand() == "Red")
      colorStatus = 4;

   if (e.getActionCommand() == "Purple")
      colorStatus = 5;

   if (e.getActionCommand() == "Orange")
      colorStatus = 6;

   if (e.getActionCommand() == "Pink")
      colorStatus = 7;

   if (e.getActionCommand() == "Gray")
      colorStatus = 8;

   if (e.getActionCommand() == "Yellow")
      colorStatus = 9;

  switch (opStatus)
   {
      case PEN_OP   : opStatusBar.setText("Pen");
                      break;
      case CLEAR_OP : 
   	   				opStatusBar.setText("Clear");
   	   				Graphics g = drawPanel.getGraphics();
   	   				g.setColor(drawPanel.getBackground());
   	   				g.fillRect(0,0,drawPanel.getBounds().width,drawPanel.getBounds().height);
   	   				break;

   }

 
   switch (colorStatus)
   {
      case  1: colorStatusBar.setText("Black");
               break;

      case  2:  colorStatusBar.setText("Blue");
                break;

      case  3:  colorStatusBar.setText("Green");
                break;

      case  4:  colorStatusBar.setText("Red");
                break;

      case  5:  colorStatusBar.setText("Purple");
                break;

      case  6:  colorStatusBar.setText("Orange");
                break;

      case  7:  colorStatusBar.setText("Pink");
                break;

      case  8:  colorStatusBar.setText("Gray");
                break;

      case  9: colorStatusBar.setText("Yellow");
               break;

 
   }

}



public void penOperation(MouseEvent e)
{	
	setMainColor();
   Graphics g  = drawPanel.getGraphics();
   g.setColor(mainColor);

   
   if (initialPen)
   {
      setGraphicalDefaults(e);
      initialPen = false;
      g.drawLine(prevx,prevy,mousex,mousey);
   }

  
   if (mouseHasMoved(e))
   {
      mousex = e.getX();
      mousey = e.getY();

    
      g.drawLine(prevx,prevy,mousex,mousey);

     
      prevx = mousex;
      prevy = mousey;
   }
}

public boolean mouseHasMoved(MouseEvent e)
{
   	 return (mousex != e.getX() || mousey != e.getY());
}


public void setGraphicalDefaults(MouseEvent e)
{
   mousex   = e.getX();
   mousey   = e.getY();
   prevx    = e.getX();
   prevy    = e.getY();

}



public void mouseDragged(MouseEvent e)
{
   updateMouseCoordinates(e);

   switch (opStatus)
   {
    
      case PEN_OP   : penOperation(e);
                      break;

     
   }
}


public void mouseReleased(MouseEvent e)
{
   
   updateMouseCoordinates(e);

   switch (opStatus)
   {
     
      case PEN_OP    : releasedPen();
                       break;

     
   }
}



public void mouseEntered(MouseEvent e)
{
   updateMouseCoordinates(e);
}



public void setMainColor()
{
   switch (colorStatus)
   {
      case 1 : mainColor = Color.black;
               break;

      case 2:  mainColor = Color.blue;
               break;

      case 3:  mainColor = Color.green;
               break;

      case 4:  mainColor = Color.red;
               break;

      case 5:  mainColor = Color.magenta;
               break;

      case 6:  mainColor = Color.orange;
               break;

      case 7:  mainColor = Color.pink;
               break;

      case 8:  mainColor = Color.gray;
               break;

      case 9:  mainColor = Color.yellow;
               break;

   }
}



public void releasedPen()
{
   initialPen = true;
}
public void updateMouseCoordinates(MouseEvent e)
{
   String xCoor ="";
   String yCoor ="";

   if (e.getX() < 0) xCoor = "0";
   else
   {
      xCoor = String.valueOf(e.getX());
   }

   if (e.getY() < 0) xCoor = "0";
   else
   {
      yCoor = String.valueOf(e.getY());
   }
   mouseStatusBar.setText("x:" + xCoor + "   y:" + yCoor);
}


public void mouseClicked(MouseEvent e)
{
   updateMouseCoordinates(e);
 
}


public void mouseExited(MouseEvent e)
{
   updateMouseCoordinates(e);
}


public void mouseMoved(MouseEvent e)
{
   updateMouseCoordinates(e);
}


public void mousePressed(MouseEvent e)
{
   updateMouseCoordinates(e);
}

}
