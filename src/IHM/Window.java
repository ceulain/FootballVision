package IHM;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.sound.sampled.* ;

import traitement.AlgoVision;
import traitement.Match;
import traitement.Team;






public class Window  extends JFrame{

	

	Match match1 = new Match("Chelsea","Paris");
	Match match2 = new Match("Bayern","Shakhtar Donetsk");
	Match match3 = new Match("Real Madrid","Schalke");
	Match match4 = new Match("Porto","Basel");

	Match match5 = new Match("Barcelona","Man. City");
	Match match6 = new Match("Monaco","Arsenal");
	Match match7 = new Match("Atlético","Leverkusen");
	Match match8 = new Match("Dortmund","Juventus");

	ArrayList<Match> matchs = new ArrayList<>();




	public Window() throws Exception{
		this.setTitle("Football Vision");
		this.setSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new BackgroundImage());
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));



		initialize();
		fill();
		/*
		this.add(new MatchPanel("Home", null,"Visitor",null, "Percent Home", "Percent Draws", 
				"Percent Visitor"));

		this.add(new MatchPanel("","chelsea.png","","paris.png",null,null,null));
		this.add(new MatchPanel("","bayern.png","","shakthar.png",null,null,null));
		this.add(new MatchPanel("","real.png","","schalke.png",null,null,null));
		this.add(new MatchPanel("","porto.png","","basel.png",null,null,null));
		this.add(new MatchPanel("","barca.png"," ","manCity.png",null,null,null));
		this.add(new MatchPanel("","monaco.png","","arsenal.png",null,null,null));
		this.add(new MatchPanel("","atleti.png","","leverkusen.png",null,null,null));
		this.add(new MatchPanel("","juventus.png","","dortmund.png",null,null,null));

		 */

		this.setVisible(true);
	}


	public JLabel footLabel(String text){
		JLabel label = new JLabel(text);
		label.setFont(new FootballFont());
		label.setForeground(Color.white);

		return label;
	}

	public void initialize(){

		matchs.add(match1);
		matchs.add(match2);
		matchs.add(match3);
		matchs.add(match4);
		matchs.add(match5);
		matchs.add(match6);
		matchs.add(match7);
		matchs.add(match8);

	}

	public void fill() throws Exception{
		Team a,b;
		AlgoVision algo = new AlgoVision();

		for(int i=0; i<matchs.size(); i++){
			a = algo.retrieveField(matchs.get(i).teamA);
			b = algo.retrieveField(matchs.get(i).teamB);

			a.treatmentScore();
			a.trert(b.getName());
			algo.retriveLastMacth(a);


			b.treatmentScore();
			b.trert(a.getName());
			algo.retriveLastMacth(b);
			matchs.get(i).win = algo.calculatePercentMatch(a.getScore(),b.getScore()).win;
			matchs.get(i).lose = algo.calculatePercentMatch(a.getScore(),b.getScore()).lose;
		}

		this.add(new MatchPanel("Home", null,"Visitor",null, "Percent Home", "Percent Draws", 
				"Percent Visitor"));

		this.add(new MatchPanel("","chelsea.png","","paris.png",""+matchs.get(0).win + " %" ,null,""+matchs.get(0).lose+ " %" ));
		this.add(new MatchPanel("","bayern.png","","shakthar.png",""+matchs.get(1).win + " %" ,null,""+matchs.get(1).lose+ " %" ));
		this.add(new MatchPanel("","real.png","","schalke.png",""+matchs.get(2).win+ " %" ,null,""+matchs.get(2).lose+ " %" ));
		this.add(new MatchPanel("","porto.png","","basel.png",""+matchs.get(3).win+ " %" ,null,""+matchs.get(3).lose+ " %" ));
		this.add(new MatchPanel("","barca.png"," ","manCity.png",""+matchs.get(4).win+ " %" ,null,""+matchs.get(4).lose+ " %" ));
		this.add(new MatchPanel("","monaco.png","","arsenal.png",""+matchs.get(5).win+ " %" ,null,""+matchs.get(5).lose+ " %" ));
		this.add(new MatchPanel("","atleti.png","","leverkusen.png",""+matchs.get(6).win+ " %" ,null,""+matchs.get(6).lose+ " %" ));
		this.add(new MatchPanel("","juventus.png","","dortmund.png",""+matchs.get(7).win+ " %" ,null,""+matchs.get(7).lose+ " %" ));

		

	}

}
