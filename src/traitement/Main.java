package traitement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import IHM.Window;


public class Main {
/**
 * Se connecter avec l'utitisateur postgres
 * executer le fichier data.sql
 * lancer le programmme
 * executer le fichier missing_data.sql
 */
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String [] args) throws IOException{
		
		//fillInTable();
		
		try {
			new Window();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void fillInTable(){
		int start = 0; int stop = 0;
		int count = 0;
		ArrayList<String> teams = new ArrayList<String>();
		teams.add("Paris");teams.add("Chelsea");teams.add("Shakhtar Donetsk");teams.add("Bayern");
		teams.add("Schalke");teams.add("Real Madrid");teams.add("Basel");teams.add("Porto");
		teams.add("Man. City");teams.add("Barcelona");teams.add("Juventus");teams.add("Dortmund");
		teams.add("Arsenal");teams.add("Monaco");teams.add("Leverkusen");teams.add("Atlético");

		ArrayList<String> urls = new ArrayList<String>();
		String firstPage = "http://fr.uefa.com/uefachampionsleague/season=2015"
				+ "/statistics/round=2000548/clubs/index.html";
		String secondPage = "http://fr.uefa.com/uefachampionsleague/season=2015"
				+ "/statistics/round=2000548/clubs/kind=goals/index.html";
		String thirdPage = "http://www.uefa.com/uefachampionsleague/season=2015"
				+ "/statistics/round=2000548/clubs/kind=attempts/index.html";
		String fourthPage = "http://www.uefa.com/uefachampionsleague/season=2015"
				+ "/statistics/round=2000548/clubs/kind=distribution/index.html";
		String fifthPage = "http://www.uefa.com/uefachampionsleague/season=2015"
				+ "/statistics/round=2000548/clubs/kind=setpieces/index.html";
		String sixthPage = "http://www.uefa.com/uefachampionsleague/season=2015"
				+ "/statistics/round=2000548/clubs/kind=disciplinary/index.html";

		urls.add(firstPage);urls.add(secondPage);urls.add(thirdPage);urls.add(fourthPage);
		urls.add(fifthPage);urls.add(sixthPage);


		for(String url : urls){
			for(String team : teams){
				if(url.equals(firstPage)){
					start = 7;
					stop = 10;
				}else if(url.equals(secondPage)){
					start = 11;
						stop = 15;
				}else if(url.equals(thirdPage)){
					start = 16;
					stop = 21;
				}else if(url.equals(fourthPage)){
					start = 22;
					stop = 26 ;
				}else if(url.equals(fifthPage)){
					start = 27;
					stop = 32;
				}else if(url.equals(sixthPage)){
					start = 33;
					stop = 37;
				}else{
					System.out.println("ERROR");
				}

				retrieveData(url, team, start, stop,count);
				
			}
			count=-1;
		}
	}
	public static void retrieveData(String urlOfPage, String nameOfTeam, int startColumns, int stopColumns,int count){
		try{

			
			Connection conn = ConnectionBase.getConnection();
			Document doc = Jsoup.connect(urlOfPage).get();

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from team");
			ResultSetMetaData rsm = rs.getMetaData();
			String insertRequest = null;
			//for(int i=1; i <= rsm.getColumnCount(); i++)
				//System.out.println(i+" - "+rsm.getColumnName(i));

			Elements elementClassL = doc.getElementsByClass("l");
			for (Element l : elementClassL) {
				if (l.text().equals(nameOfTeam)) {
					if(count == 0){
						insertRequest = "insert into  team (name_team) values ('"+l.text()+"');";
						//System.out.println(insertRequest);
						st.execute(insertRequest);
					}
					Element nxt = l.nextElementSibling();
					for(int i = startColumns ; nxt != null && i <= stopColumns; i++) {
						String updateRequest = "update team set "+rsm.getColumnName(i)
								+" = '"+nxt.text()+"' where name_team = '"+l.text()+"';";
					//	System.out.println(updateRequest);
						st.execute(updateRequest);
					//	st.execute("\\i ../missing_data.sql");
						nxt = nxt.nextElementSibling();
					}
				}
			}

			//System.out.println("Connexion effective");


		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	/*public static void retrieveRankingUefa() throws Exception{
		ArrayList<String> teams = new ArrayList<String>();
		teams.add("Paris Saint-Germain");teams.add("Chelsea FC");teams.add("FC Shakhtar Donetsk");
		teams.add("FC Bayern München");teams.add("FC Schalke 04");teams.add("Real Madrid CF");
		teams.add("FC Basel 1893");teams.add("FC Porto");teams.add("Manchester City FC");
		teams.add("FC Barcelona");teams.add("Juventus");teams.add("Borussia Dortmund");
		teams.add("Arsenal FC");teams.add("AS Monaco FC");teams.add("Bayer 04 Leverkusen");
		teams.add("Club Atlético de Madrid");
		
		Connection conn = getConnection();
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from team");
		ResultSetMetaData rsm = rs.getMetaData();
		
		
		
	}*/	
	
	
	

}
