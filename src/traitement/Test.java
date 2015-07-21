package traitement;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Test {
	public static void main(String [] args) throws IOException{
		Document doc = Jsoup.connect("http://fr.uefa.com/memberassociations/uefarankings/club/seasonclub/index.html").get();
		ArrayList<String> l = new ArrayList<String>();
		String real = "Juventus";
		//System.out.println(doc);
		//System.out.println(doc.getElementsByTag("td").text());
		Elements td = doc.getElementsByClass("td");
		for(Element e : td){
			//	System.out.println(e.text());
				l.add(e.text());
		}
		
			//System.out.println(l.get(8));
			
			for(int i = 2; i < 100; i+=3){
			
				if(l.get(i).equals(real)){
					System.out.println(l.get(i-2));
				}
			}
	}
}
		