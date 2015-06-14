import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;


public class ControllPane extends JFrame {
	
	/**
	 * ControllPanel to simulate
	 * 
	 */
	
	public Simulator mysimulator;//simulator(empty)
	
	public static void main(String[] args){
		ControllPane mytestpane = new ControllPane();
	}
	
	public ControllPane() {
		
		String[] data = {"Random","Distance(My Addition)","Greedy"};
		String[] data2 = {"Static","Dynamic","Dynamic Task","Unreliable","Dificulty Change"};
		String[] data3 = {"Simple","Complex"};
		
		setPreferredSize(new Dimension(100, 800));
		setTitle("SimulationController");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 630));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("HAgent's Thinking:");
		panel_2.add(lblNewLabel);
		final JComboBox comboBox = new JComboBox(data);
		panel_2.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("HAgent's Eval: ");
		panel_3.add(lblNewLabel_2);
		
		JComboBox comboBox_2 = new JComboBox(data3);
		panel_3.add(comboBox_2);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Environment:");
		panel_1.add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox(data2);
		panel_1.add(comboBox_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_6.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("Try: ");
		panel_6.add(lblNewLabel_4);
		
		final JSpinner spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(50, 20));
		panel_6.add(spinner);
		
		/*Start Button */
		JButton btnNewButton = new JButton("Start");
		panel_6.add(btnNewButton);
		
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setMaximumSize(new Dimension(250, 13));
		
		JLabel lblNewLabel_3 = new JLabel("NoGUI");
		lblNewLabel_3.setMaximumSize(new Dimension(250, 13));
		lblNewLabel_3.setPreferredSize(new Dimension(50, 13));
		panel_5.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("");
		panel_5.add(chckbxNewCheckBox);
		
		btnNewButton.addActionListener(new ActionListener(){
			//On button clicked,generate simulator window.
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int checked = 0;
				if(chckbxNewCheckBox.isSelected()){
					checked = 1;
				}else{
					checked = 0;
				}
				System.out.println((String)comboBox.getSelectedItem());
				//create simulator
				mysimulator = new Simulator(checked,
						                    comboBox.getSelectedIndex(),
						                    0,
						                    0,
						                    (int)spinner.getValue());
				Thread simthre = new Thread(mysimulator);
				simthre.start();
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(300, 300));
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_5 = new JLabel("Agent1: ");
		panel_7.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("0");
		panel_7.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(" Agent2: ");
		panel_7.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("0 ");
		panel_7.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Agent3: ");
		panel_7.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("0");
		panel_7.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Agent4 ");
		panel_7.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("0");
		panel_7.add(lblNewLabel_12);
		
		setBounds(100,100,300,300);
		setVisible(true);
	}

}
