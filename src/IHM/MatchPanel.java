package IHM;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MatchPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4490265918822470667L;

	private String teamHome; 
	private String teamVisitor;
	private String percentHome;
	private String percentDraws;
	private String percentVisitor;

	private JLabel labelTeamHome;
	private JLabel labelTeamVisitor;
	private JLabel labelPercentHome;
	private JLabel labelPercentDraws;
	private JLabel labelPercentVisitor;

	private String imgHome;
	private String imgVisitor;
	
	public MatchPanel(String teamHome, String imgHome, String teamVisitor,String imgVisitor,String percentHome,
			String percentDraws, String percentVisitor){
		this.teamHome = teamHome;
		this.teamVisitor = teamVisitor;
		this.percentHome = percentHome;
		this.percentDraws = percentDraws;
		this.percentVisitor = percentVisitor;
		
		this.imgHome = imgHome;
		this.imgVisitor = imgVisitor;
		
		this.setLayout(new GridLayout(0, 4));

		
		
		this.labelTeamHome = new JLabel(this.teamHome+"    ", new ImageIcon(this.imgHome), SwingConstants.CENTER);
		this.labelTeamHome.setFont(new FootballFont());
		this.labelTeamHome.setForeground(Color.WHITE);
		
		this.add(labelTeamHome);

		this.labelTeamVisitor = new JLabel(this.teamVisitor+"    ", new ImageIcon(this.imgVisitor), SwingConstants.CENTER);
		this.labelTeamVisitor.setFont(new FootballFont());
		this.labelTeamVisitor.setForeground(Color.WHITE);
		
		this.add(labelTeamVisitor);


		this.labelPercentHome = new JLabel(this.percentHome+"    ");
		this.labelPercentHome.setFont(new FootballFont());
		this.labelPercentHome.setForeground(Color.WHITE);
		this.add(labelPercentHome);

		this.labelPercentDraws = new JLabel(this.percentDraws+"    ");
		this.labelPercentDraws.setFont(new FootballFont());
		this.labelPercentDraws.setForeground(Color.WHITE);
		//this.add(labelPercentDraws);

		this.labelPercentVisitor = new JLabel(this.percentVisitor+"    ");
		this.labelPercentVisitor.setFont(new FootballFont());
		this.labelPercentVisitor.setForeground(Color.WHITE);
		this.add(labelPercentVisitor);
		this.setOpaque(false);
	}

	
	
	
	public JLabel getLabelTeamHome() {
		return labelTeamHome;
	}


	public void setLabelTeamHome(JLabel labelTeamHome) {
		this.labelTeamHome = labelTeamHome;
	}


	public JLabel getLabelTeamVisitor() {
		return labelTeamVisitor;
	}


	public void setLabelTeamVisitor(JLabel labelTeamVisitor) {
		this.labelTeamVisitor = labelTeamVisitor;
	}


	public JLabel getLabelPercentHome() {
		return labelPercentHome;
	}


	public void setLabelPercentHome(JLabel labelPercentHome) {
		this.labelPercentHome = labelPercentHome;
	}


	public JLabel getLabelPercentDraws() {
		return labelPercentDraws;
	}


	public void setLabelPercentDraws(JLabel labelPercentDraws) {
		this.labelPercentDraws = labelPercentDraws;
	}


	public JLabel getLabelPercentVisitor() {
		return labelPercentVisitor;
	}


	public void setLabelPercentVisitor(JLabel labelPercentVisitor) {
		this.labelPercentVisitor = labelPercentVisitor;
	}



}
