package traitement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Team {

	private String name;
	private String win;
	private String loose;
	private String nbr_match;
	private String goals; 
	private String attempts_on_targets;
	private String attempts_on_block;
	private String attempts_woodwork;
	private String crosses_attempted;
	private String crosses_completed;
	private String goals_againts;
	private String ball_possesion;
	private String time_possession;
	private String yellow_card;
	private String red_card;
	private String ranking_champ;
	private float score ;

	public Team(String name,String win, String loose, String nbr_match, String goals,
			String attempts_on_targets, String attempts_on_block,
			String attempts_woodwork, String crosses_attempted,
			String crosses_completed, String goals_againts,
			String ball_possesion, String time_possession, String yellow_card,
			String red_card, String ranking_champ) {
		super();
		this.name = name;
		this.win = win;
		this.loose = loose;
		this.nbr_match = nbr_match;
		this.goals = goals;
		this.attempts_on_targets = attempts_on_targets;
		this.attempts_on_block = attempts_on_block;
		this.attempts_woodwork = attempts_woodwork;
		this.crosses_attempted = crosses_attempted;
		this.crosses_completed = crosses_completed;
		this.goals_againts = goals_againts;
		this.ball_possesion = ball_possesion;
		this.time_possession = time_possession;
		this.yellow_card = yellow_card;
		this.red_card = red_card;
		this.ranking_champ = ranking_champ;
		this.score = 0;
	}

	public Team() {
		
	}

	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getNbr_match() {
		return nbr_match;
	}
	public void setNbr_match(String nbr_match) {
		this.nbr_match = nbr_match;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getAttempts_on_targets() {
		return attempts_on_targets;
	}
	public void setAttempts_on_targets(String attempts_on_targets) {
		this.attempts_on_targets = attempts_on_targets;
	}
	public String getAttempts_on_block() {
		return attempts_on_block;
	}
	public void setAttempts_on_block(String attempts_on_block) {
		this.attempts_on_block = attempts_on_block;
	}
	public String getAttempts_woodwork() {
		return attempts_woodwork;
	}
	public void setAttempts_woodwork(String attempts_woodwork) {
		this.attempts_woodwork = attempts_woodwork;
	}
	public String getCrosses_attempted() {
		return crosses_attempted;
	}
	public void setCrosses_attempted(String crosses_attempted) {
		this.crosses_attempted = crosses_attempted;
	}
	public String getCrosses_completed() {
		return crosses_completed;
	}
	public void setCrosses_completed(String crosses_completed) {
		this.crosses_completed = crosses_completed;
	}
	public String getGoals_againts() {
		return goals_againts;
	}
	public void setGoals_againts(String goals_againts) {
		this.goals_againts = goals_againts;
	}
	public String getBall_possesion() {
		return ball_possesion;
	}
	public void setBall_possesion(String ball_possesion) {
		this.ball_possesion = ball_possesion;
	}
	public String getTime_possession() {
		return time_possession;
	}
	public void setTime_possession(String time_possession) {
		this.time_possession = time_possession;
	}
	public String getYellow_card() {
		return yellow_card;
	}
	public void setYellow_card(String yellow_card) {
		this.yellow_card = yellow_card;
	}
	public String getRed_card() {
		return red_card;
	}
	public void setRed_card(String red_card) {
		this.red_card = red_card;
	}
	public String getRanking_champ() {
		return ranking_champ;
	}
	public void setRanking_champ(String ranking_champ) {
		this.ranking_champ = ranking_champ;
	}

	public float getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getLoose() {
		return loose;
	}

	public void setLoose(String loose) {
		this.loose = loose;
	}

	public void treatmentScore(){
		
		int rank = Integer.valueOf(this.ranking_champ);
		this.score += ((Double.valueOf(this.win)) / (Double.valueOf(this.nbr_match) * 6)) ;
		
		this.score += (Double.valueOf(this.loose) / Double.valueOf(this.nbr_match)) * (-6) ;
		
		this.score += (Double.valueOf(this.goals) / (Double.valueOf(this.attempts_on_targets) +
				Double.valueOf(this.attempts_woodwork) + Double.valueOf(this.attempts_on_block)) * 3) ;
		
		this.score += (Double.valueOf(this.crosses_completed) / Double.valueOf(this.crosses_attempted)) * 3;
		
		this.score += (Double.valueOf(this.goals_againts) * (-1));
		
		this.score += ((Double.valueOf(this.ball_possesion.substring(0,this.ball_possesion.length()-1))) +
				(Double.valueOf(this.time_possession.substring(0,this.time_possession.length()-1)))) * 3;
		
		if(rank == 1)
			this.score += rank * 6;
		if(rank == 2)
			this.score += rank * 2;
		if(rank == 3)
			this.score += rank;

		this.score += ((Double.valueOf(this.yellow_card) + (Double.valueOf(this.red_card) * 2)) / 
				Double.valueOf(this.nbr_match) * (-4));
	}

	public void trert(String teamB) throws Exception{
		Connection conn = ConnectionBase.getConnection();
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String query = "select (showdown_win_home + showdown_win_visitor + showdown_draws) as nbr_match from matchs where name_team_home ='"+teamB+"';";
		ResultSet resul = st.executeQuery(query);
		int nbr_match = 0 ;
		int victory = 0;
		while(resul.next())
			nbr_match = resul.getInt(1);



		query = "select showdown_win_visitor from matchs where name_team_home='"+teamB+"';";
		resul = st.executeQuery(query);
		while(resul.next())
			victory = resul.getInt(1);
		if(!(nbr_match <= 0))
			this.score += (victory / nbr_match ) * 4;

	}
	
	
	public void getLastMatch(int win, int draw , int lose ){
	
		score+= (win*3);
		score+= (draw*1);
		score+= (lose* (-3)  );
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
