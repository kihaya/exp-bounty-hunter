import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;


/**
 * @date 2015/6/4
 * @author Kihaya
 * test implementation of "Bounty Hunters and Multiagent Task Allocation"
 */
public class MainUI extends JFrame implements KeyListener {

	//for windows 620,for mac 600
	private static final int FRAME_WIDTH_WINDOWS = 620;
	private static final int FRAME_WIDTH_OSX = 600;
	private static final int FRAME_HEIGHT = 450;
	
	JPanel panel; //
	LineBorder border;
	EtchedBorder border2;
	ArrayList<JLabel> cells;
	
	public int keypress;//keypress flag to watch.
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MainUI mui = new MainUI("ExperimentBoutyHunter");
        mui.setVisible(true);
	}
    
	MainUI(String title){
		setUp(title);
		addKeyListener(this);
		setVisible(true);
	}
	
	/*
	 * initialize all.
	 */
	void setUp(String title){
		
		//setResizable(false);
		setTitle(title);
		
		if(System.getProperty("os.name").indexOf("Windows") >= 0){
		setBounds(200,200,FRAME_WIDTH_WINDOWS,FRAME_HEIGHT);
		}else{
			setBounds(200,200,FRAME_WIDTH_OSX,FRAME_HEIGHT);
		}
		
		//setLayout(new BoxLayout(rootPane, BoxLayout.Y_AXIS));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    border = new LineBorder(Color.GREEN, 1, false);
	    
	    panel = new JPanel();
	    //panel.setSize(600,400);
	    panel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
	    
	    initializeCells();
	    
	    JPanel pane2 = new JPanel();
	    pane2.setBorder(border);
	    
	    Container conpane = getContentPane();
	    
	    conpane.add(panel);
	    //conpane.add(pane2);
	    
	    
	    //pack();
	}
	
	public void setCellColor(HunterAgent agent){
		int arrayindex = zahyoToArrayIndex(agent.getX(),agent.getY());
		cells.get(arrayindex).setBackground(Color.BLACK);
	}
	
	public void setCellColor(Ball ball){
		int arrayindex = zahyoToArrayIndex(ball.getX(),ball.getY());
		
		cells.get(arrayindex).setBackground(Color.BLUE);
	}
	
	public void setCellColor(int x,int y,int type){
		int arrayindex = zahyoToArrayIndex(x,y);
		if(type == Consts.EMPTY){
		   cells.get(arrayindex).setBackground(Color.WHITE);
		}
		if(type == Consts.BALL){
			   cells.get(arrayindex).setBackground(Color.BLUE);
		}
		if(type == Consts.AGENT_1 ||type == Consts.AGENT_2 || type == Consts.AGENT_3
				||type == Consts.AGENT_4){
			   cells.get(arrayindex).setBackground(Color.BLACK);
		}
	}
	
	/*
	 * set cells.
	 */
	private void initializeCells(){
		cells = new ArrayList<>();
	    for(int i=0;i<2400;++i){
	    	cells.add(new JLabel());
	    }
	    for(JLabel jl:cells){
	    		jl.setPreferredSize(new Dimension(10,10));
	    		jl.setBorder(border);
	    	    jl.setOpaque(true);
	    	    jl.setBackground(Color.WHITE);
	    	    panel.add(jl);
	    }
	}
	
	/*
	 * translate zahyo to arraylist index
	 * exp = y * 60 + x
	 */
	private int zahyoToArrayIndex(int x,int y){
		return (y*60)+x;
	}
	private int[] arrayindexToZahyo(int arrayindex){	
		int[] ret = {0,0};
		//calc both position
		int xpos = (int)(arrayindex/40);
		int ypos = (int)(arrayindex/60);
		ret[0] = xpos;
		ret[1] = ypos;	
		return ret;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		    keypress = 1;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
