import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JOptionPane;

public class MyDrawTool extends JPanel{

	static JFrame frame;
	static final int frameWidth = 500;
	static final int frameHeight = 400;
	int height = 10;
	int width = 10;
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	String shape = "";
	String color = "";
	String style = "MS Gothic";
	Color c = Color.black;
	int size = 20;
	Boolean clean = false;
	Boolean pen = false; 
	Boolean text = false;
	int fontSize = 20;
	JLabel coordinate;
	
	public static void main(String[] args){
		frame = new JFrame("MyDrawTool");
		frame.getContentPane().add(new MyDrawTool());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);

	}

	public MyDrawTool(){
	    setLayout(new FlowLayout());
	      
	    JButton penButton = new JButton("ペン");
	    this.add(penButton);
	    penButton.addActionListener(new PenListener());
	    
	    JButton cleanButton = new JButton("消しゴム");
	    this.add(cleanButton);
	    cleanButton.addActionListener(new CleanListener());
	    
		JButton clearButton = new JButton("Clear");
		this.add(clearButton);
		clearButton.addActionListener(new ClearListener(this));
		
		JButton  textButton = new JButton("テキスト");
	    this.add(textButton);
	    textButton.addActionListener(new TextListener());
	
	    JPanel p = new JPanel();
		coordinate = new JLabel();
		p.add(coordinate);
		frame.add(p,BorderLayout.PAGE_END);
	    
		this.setBackground(Color.white);

		//メニューバーのインスタンス化
		JMenuBar menuBar = new JMenuBar();
		//メニューバーをフレームに追加
		frame.setJMenuBar(menuBar);

		//メニューのインスタンス化
		JMenu shapeMenu = new JMenu("図形");
		menuBar.add(shapeMenu);
		JMenu colorMenu = new JMenu("色");
		menuBar.add(colorMenu);
		JMenu sizeMenu = new JMenu("大きさ");
		menuBar.add(sizeMenu);
		JMenu fontMenu = new JMenu("文字サイズ");
		menuBar.add(fontMenu);
		JMenu styleMenu = new JMenu("書体");
		menuBar.add(styleMenu);

		//開くメニュー
		//メニューアイテムのインスタンス化
		JMenuItem squareItem = new JMenuItem("四角形");
		shapeMenu.add(squareItem);
		squareItem.addActionListener(new SquareListener());
		//ツールチップの付与
		squareItem.setToolTipText("四角形");

		JMenuItem circleItem = new JMenuItem("円");
		shapeMenu.add(circleItem);
		circleItem.addActionListener(new CircleListener());
		circleItem.setToolTipText("円");


		JMenuItem fillSquareItem = new JMenuItem("塗りつぶし四角形");
		shapeMenu.add(fillSquareItem);
		fillSquareItem.addActionListener(new FillSquareListener());
		fillSquareItem.setToolTipText("塗りつぶし四角形");

		JMenuItem fillCircleItem = new JMenuItem("塗りつぶし円");
		shapeMenu.add(fillCircleItem);
		fillCircleItem.addActionListener(new FillCircleListener());
		fillCircleItem.setToolTipText("塗りつぶし円");

		JMenuItem lineItem = new JMenuItem("直線");
		shapeMenu.add(lineItem);
		lineItem.addActionListener(new LineListener());
		lineItem.setToolTipText("直線");

		JMenuItem redItem = new JMenuItem("赤");
		colorMenu.add(redItem);
		redItem.addActionListener(new RedListener());
		redItem.setToolTipText("赤");

		JMenuItem blueItem = new JMenuItem("青");
		colorMenu.add(blueItem);
		blueItem.addActionListener(new BlueListener());
		blueItem.setToolTipText("青");

		JMenuItem greenItem = new JMenuItem("緑");
		colorMenu.add(greenItem);
		greenItem.addActionListener(new GreenListener());
		greenItem.setToolTipText("緑");

		JMenuItem blackItem = new JMenuItem("黒");
		colorMenu.add(blackItem);
		blackItem.addActionListener(new BlackListener());
		blackItem.setToolTipText("黒");
		
		JMenuItem yellowItem = new JMenuItem("黄");
		colorMenu.add(yellowItem);
		yellowItem.addActionListener(new YellowListener());
		yellowItem.setToolTipText("黄");

		JMenuItem largeItem = new JMenuItem("大");
		sizeMenu.add(largeItem);
		largeItem.addActionListener(new LargeListener());
		largeItem.setToolTipText("大");

		JMenuItem mediumItem = new JMenuItem("中");
		sizeMenu.add(mediumItem);
		mediumItem.addActionListener(new MediumListener());
		mediumItem.setToolTipText("中");

		JMenuItem smallItem = new JMenuItem("小");
		sizeMenu.add(smallItem);
		smallItem.addActionListener(new SmallListener());
		smallItem.setToolTipText("小");

		JMenuItem tinyItem = new JMenuItem("極小");
		sizeMenu.add(tinyItem);
		tinyItem.addActionListener(new TinyListener());
		tinyItem.setToolTipText("極小");

		JMenuItem fontLargeItem = new JMenuItem("80");
		fontMenu.add(fontLargeItem);
		fontLargeItem.addActionListener(new FontLargeListener());
		fontLargeItem.setToolTipText("80");

		JMenuItem fontMediumItem = new JMenuItem("50");
		fontMenu.add(fontMediumItem);
		fontMediumItem.addActionListener(new FontMediumListener());
		fontMediumItem.setToolTipText("50");

		JMenuItem fontSmallItem = new JMenuItem("30");
		fontMenu.add(fontSmallItem);
		fontSmallItem.addActionListener(new FontSmallListener());
		fontSmallItem.setToolTipText("30");

		JMenuItem fontTinyItem = new JMenuItem("20");
		fontMenu.add(fontTinyItem);
		fontTinyItem.addActionListener(new FontTinyListener());
		fontTinyItem.setToolTipText("20");
		
		JMenuItem minchoItem = new JMenuItem("HGB明朝B");
		styleMenu.add(minchoItem);
		minchoItem.addActionListener(new MinchoListener());
		minchoItem.setToolTipText("HGB明朝B");
		
		JMenuItem gothicItem = new JMenuItem("MSゴシック");
		styleMenu.add(gothicItem);
		gothicItem.addActionListener(new GothicListener());
		gothicItem.setToolTipText("MSゴシック");
		
		JMenuItem popItem = new JMenuItem("HG創英角ポップ体");
		styleMenu.add(popItem);
		popItem.addActionListener(new PopListener());
		popItem.setToolTipText("HG創英角ポップ体");
		
		JMenuItem dialogItem = new JMenuItem("HG教科書体");
		styleMenu.add(dialogItem);
		dialogItem.addActionListener(new DialogListener());
		dialogItem.setToolTipText("HG教科書体");
		

		//パネルにMouseListenerを付与
		addMouseListener(new MouseMotionCheck());
		//パネルにMouseMotionListenerを付与
		addMouseMotionListener(new MouseMotionCheck());
	}

	class ClearListener implements ActionListener{
		MyDrawTool panel;
	
		ClearListener(MyDrawTool mpanel){
			panel = mpanel;
		}
		public void actionPerformed(ActionEvent e){
			int warning = JOptionPane.showConfirmDialog(frame, "全て消えますがよろしいですか？");
			if(warning == JOptionPane.YES_OPTION){
			repaint();
			}
		}
	}

	class SquareListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			shape = "四角形";
			pen = clean = false;
		}
	}

	class CircleListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			shape = "円";
			pen = clean = false;
		}
	}

	class FillSquareListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			shape = "塗りつぶし四角形";
			pen = clean = false;
		}
	}

	class FillCircleListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			shape = "塗りつぶし円";
			pen = clean = false;
		}
	}

	class LineListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			shape = "直線";
			pen = clean = false;
		}
	}

	class RedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			c = Color.red;
		}
	}

	class BlueListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			c = Color.blue;
		}
	}

	class GreenListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			c = Color.green;
		}
	}

	class BlackListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			c = Color.black;
		}
	}
	
	class YellowListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			c = Color.yellow;
		}
	}

	class LargeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			size = 50;
		}
	}

	class MediumListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			size = 30;
		}
	}

	class SmallListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			size = 20;
		}
	}

	class TinyListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			size = 10;
		}
	}

	class FontLargeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			fontSize = 80;
		}
	}

	class FontMediumListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			fontSize = 50;
		}
	}

	class FontSmallListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			fontSize = 30;
		}
	}

	class FontTinyListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			fontSize = 20;
		}
	}
	
	class MinchoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			style = "HGSMinchoB";
		}
	}
	
	class GothicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			style = "MS Gothic";
		}
	}
	
	class PopListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			style = "HGSoeiKakupoptai";
		}
	}
	
	class DialogListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			style = "HGSKyokashotai";
		}
	}
	
	class TextListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			text = true;
			pen = clean = false;
		}
	}
	
	class PenListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pen = true;
			clean = false;
		}
	}
	
	class CleanListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			clean = true;
			pen = false;
		}
	}

	class MouseMotionCheck extends MouseInputAdapter{
		//マウスが押されたとき
		public void mousePressed(MouseEvent e){
			x1 = e.getX();
			y1 = e.getY();
			
			if(text){
			    String value = JOptionPane.showInputDialog(this, "テキストを入力してください");
			    Graphics g = getGraphics();

			    if (value == null){
			
			    }else{
			    	Font font = new Font(style, Font.BOLD, fontSize);
			    	g.setFont(font);
			    	g.setColor(c);
			    	g.drawString(value, x1, y1);
			    }
			    text = false;
			}
		}

		//マウスが動いたとき
		public void mouseDragged(MouseEvent e){			
			if(pen){
				Graphics g = getGraphics();
				x1 = e.getX();
				y1 = e.getY();
				g.setColor(c);
				g.fillOval(x1, y1, size, size);
			}
			else if(clean){
				Graphics g = getGraphics();
				x1 = e.getX();
				y1 = e.getY();
				g.setColor(Color.white);
				g.fillOval(x1, y1, size, size);
			}
		}

		public void mouseClicked(MouseEvent e){

		}

		//マウスが離されたとき
		public void mouseReleased(MouseEvent e){
			x2 = e.getX();
			y2 = e.getY();
			width = Math.abs(x2 - x1);
			height = Math.abs(y2 - y1);

			if(x2 < x1){
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			if(y2 < y1){
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}

			if(!shape.equals("")){
				Graphics g = getGraphics();
				g.setColor(c);
				
				if(shape.equals("四角形")){
					g.drawRect(x1, y1, width, height);
				}
				else if(shape.equals("円")){
					g.drawOval(x1, y1, width, height);
				}
				else if(shape.equals("塗りつぶし四角形")){
					g.fillRect(x1, y1, width, height);
				}
				else if(shape.equals("塗りつぶし円")){
					g.fillOval(x1, y1, width, height);
				}
				else if(shape.equals("直線")){
					g.drawLine(x1, y1, x2, y2);
				}
			}
		}
		
		public void mouseMoved(MouseEvent e){
			String tmp = "(" + e.getX() + "," + e.getY() + ")";
			coordinate.setText(tmp);
		}
	}
}
