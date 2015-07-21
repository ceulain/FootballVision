package traitement;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionBase {

	public static Connection getConnection() throws Exception{
		Class.forName("org.postgresql.Driver");
		//System.out.println("Driver O.K");

		String url = "jdbc:postgresql://localhost/master";
		String user ="postgres";
		String password = "az1987";
		Connection conn = DriverManager.getConnection(url, user, password);

		return conn;
	}
}
