package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class SpeedCalcFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpeedCalcFrame frame = new SpeedCalcFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpeedCalcFrame() {
		initialize();
	}
	
	private void initialize() {
		this.setTitle("Speed Calculator");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSlider baseSpeedSlider = new JSlider();
		baseSpeedSlider.setForeground(Color.WHITE);
		baseSpeedSlider.setValue(20);
		baseSpeedSlider.setPaintLabels(true);
		baseSpeedSlider.setMinimum(20);
		baseSpeedSlider.setMaximum(142);
		baseSpeedSlider.setBounds(103, 87, 286, 50);
		contentPane.add(baseSpeedSlider);		
		
		JLabel titleLabel = new JLabel("MAX-MIN SPEED CALC");
		titleLabel.setForeground(Color.RED);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		titleLabel.setBounds(63, 27, 262, 50);
		contentPane.add(titleLabel);
		
		JRadioButton maxSpeedButton = new JRadioButton("Max-speed");
		maxSpeedButton.setSelected(true);
		maxSpeedButton.setBounds(280, 163, 109, 23);
		contentPane.add(maxSpeedButton);
		
		JRadioButton minSpeedButton = new JRadioButton("Min-speed");
		minSpeedButton.setBounds(280, 190, 109, 23);
		contentPane.add(minSpeedButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(minSpeedButton);
		group.add(maxSpeedButton);		
		
		JLabel speedLabel = new JLabel("");
		speedLabel.setBackground(Color.YELLOW);
		speedLabel.setFont(new Font("Times New Roman", Font.PLAIN, 96));
		speedLabel.setEnabled(false);
		speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		speedLabel.setBounds(82, 136, 150, 94);
		speedLabel.setVisible(false);
		contentPane.add(speedLabel);
		
		JLabel baseSpeedLabel = new JLabel("");
		baseSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		baseSpeedLabel.setEnabled(false);
		baseSpeedLabel.setBounds(63, 100, 28, 23);
		contentPane.add(baseSpeedLabel);
		
		maxSpeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						if(minSpeedButton.isSelected()) {
							minSpeedButton.setEnabled(false);
							speedLabel.setText(Integer.toString(maxSpeed(baseSpeedSlider.getValue())));
						}
					}
				});
		
		minSpeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						if(maxSpeedButton.isSelected()) {
							maxSpeedButton.setEnabled(false);
							speedLabel.setText(Integer.toString(minSpeed(baseSpeedSlider.getValue())));
						}
					}
				});
		
		baseSpeedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int baseSpeed = baseSpeedSlider.getValue();
				baseSpeedLabel.setText(Integer.toString(baseSpeed));
				baseSpeedLabel.setVisible(true);
				int speed = 0;
				if(minSpeedButton.isSelected()) {
					speed = minSpeed(baseSpeed);
				}
				else if(maxSpeedButton.isSelected()) {
					speed = maxSpeed(baseSpeed);
				}
				
				speedLabel.setText(Integer.toString(speed));
				speedLabel.setVisible(true);
			}
		});		
		
	}
	
	private int maxSpeed(int baseSpeed) { //94 = 63 + 31
		return (int)((double)(((( 2 * baseSpeed + 94)) * 50 / 100) + 5) * 1.1);
	}
	
	private int minSpeed(int baseSpeed) { //3181 = 31 + 252/4 * lvl
		return (int)((double)(((( 2 * baseSpeed ) * 50) / 100) + 5) * 0.9);
	}
}
