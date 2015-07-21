package traitement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class AlgoVision {

	String win;
	String nbr_match;
	String goals; 
	String attempts_on_targets;
	String attempts_off_targets;
	String attempts_woodwork;
	String crosses_attempted;
	String crosses_completed;
	String goals_againts;
	String ball_possesion;
	String time_possession;
	String yellow_card;
	String red_card;
	String ranking_champ;

	public Team retrieveField(String nameTeam) throws Exception{
		Team a= null;
		Connection conn = ConnectionBase.getConnection();
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String query = "SELECT win,loose, nbr_match, goals, attempts_on_targets, "
				+ 	"attempts_on_block , attempts_woodwork, crosses_attempted, crosses_completed,"
				+ " goals_againts, ball_possesion, time_possession,	yellow_card, red_card ,ranking_champ FROM team WHERE name_team =   '"+nameTeam+"';";


		//PreparedStatement prepare = conn.prepareStatement(result.getString(i++));

		//prepare.setString(1, field);
		//.setString(1, "Chelsea");
		//System.out.println(prepare.toString());

		ResultSet result = st.executeQuery(query);
		//ResultSetMetaData resultMeta = result.getMetaData();
		int i=1;
		while(result.next()){

			 a = new Team(nameTeam,result.getString(i++),result.getString(i++), result.getString(i++), 
					result.getString(i++), result.getString(i++), result.getString(i++),
					result.getString(i++), result.getString(i++), result.getString(i++), 
					result.getString(i++), result.getString(i++), result.getString(i++), 
					result.getString(i++), result.getString(i++),result.getString(i++));
		}

		//retriveLast();
		return a;
	}
		
		
		
		
	




	public float retriveLastMacth(Team a) throws Exception  {

		int win,lose,draw;
		

		Connection conn = ConnectionBase.getConnection();
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);


		String SqlMatchWin = "SELECT count(TableDixMatch.winner) as Win  "
				+ "FROM  "
				+ "( SELECT  winner "
				+ "FROM HistoryMatch "
				+ "WHERE ( TEAM_A =  '"+a.getName()+"'  or  TEAM_B = '"+a.getName()+"' ) and  winner != 'En cours' "
				+ "ORDER by DATE_MATCH desc "
				+ "LIMIT 10 ) "
				+ "AS TableDixMatch "
				+ "Where winner = '"+a.getName()+"' ; " ;

		ResultSet result = st.executeQuery(SqlMatchWin);
		result.next();
		
		win =  result.getInt("Win");
		
		String SqlMatchLose = "SELECT count(TableDixMatch.winner) as Lose  "
				+ "FROM  "
				+ "( SELECT  winner "
				+ "FROM HistoryMatch "
				+ "WHERE ( TEAM_A =  '"+a.getName()+"'  or  TEAM_B = '"+a.getName()+"' ) and  winner != 'En cours' "
				+ "ORDER by DATE_MATCH desc "
				+ "LIMIT 10 ) "
				+ "AS TableDixMatch "
				+ "Where winner != '"+a.getName()+"' and winner != 'MATCH NULL'; " ;


		result = st.executeQuery(SqlMatchLose);
		result.next();
		
		
		lose =  result.getInt("Lose");
		
		String SqlMatchDraw = "SELECT count(TableDixMatch.winner) as Draw  "
				+ "FROM  "
				+ "( SELECT  winner "
				+ "FROM HistoryMatch "
				+ "WHERE ( TEAM_A =  '"+a.getName()+"'  or  TEAM_B = '"+a.getName()+"' ) and  winner != 'En cours' "
				+ "ORDER by DATE_MATCH desc "
				+ "LIMIT 10 ) "
				+ "AS TableDixMatch "
				+ "Where winner = 'MATCH NULL'; " ;


		

		result = st.executeQuery(SqlMatchDraw);
		result.next();
		
		draw =  result.getInt("Draw");
		
		
		a.getLastMatch(win , draw, lose);
		
		
		return a.getScore();
		
	}


	

	public static void main(String [] args) throws Exception{

		AlgoVision al = new AlgoVision();
		float precentTheamA, precentTheamB ;
		Team a ,b;
		// score de paris en se basant sur le matchs contre chelsea
		//al.calculatePercentMatch("Paris","Chelsea");
		
		
		
		
		
		
		a = al.retrieveField("Paris");
		b = al.retrieveField("Chelsea");
		
		a.treatmentScore();
		a.trert(b.getName());
		al.retriveLastMacth(a);
		
		
		b.treatmentScore();
		b.trert(a.getName());
		al.retriveLastMacth(b);
		
		
		al.calculatePercentMatch(a.getScore(),b.getScore());
		
		
		
		
		//calcule le score contre equipe adverse 
		
		//precentTheamA = al.retriveLastMacth("Paris");
		
				
		
		//precentTheamA = al.retriveLastMacth("Chelsea");

	}




	public Match calculatePercentMatch(float precentTheamA, float precentTheamB) throws Exception {
		
		
		float diffTeam ;
		Match match = new Match();
		
		// score de paris en se basant sur le matchs contre chelsea
		//this.calculatePercentMatch(teamA,teamB);
		
		
		
		if(precentTheamA > precentTheamB) {
			diffTeam = (precentTheamA - precentTheamB)/ precentTheamA * 100;
			match.win = 50 + (diffTeam / 2); 
			match.lose = 50 - (diffTeam / 2);
		}else{
			diffTeam = (precentTheamB - precentTheamA)/ precentTheamB * 100;
			match.win = 50 + (diffTeam / 2); 
			match.lose = 50 - (diffTeam / 2);
		}
		match.win = Math.round(match.win);
		match.lose = Math.round(match.lose);
		
		return match; 
		
	}

}
