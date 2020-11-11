import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Properties;

//import com.simba.athena.amazonaws.auth.profile.internal.BasicProfileConfigFileLoader;
import com.simba.athena.shaded.apache.log4j.BasicConfigurator;

public class App
{
	static String athenaURL = "jdbc:awsathena://AwsRegion=us-east-2;";
	static String s3OutputLocation = "s3://my-athena-result-bucket/test/";
	
	static Connection connection;
	
	public static void main(String[] args)
	{
		//OBTAIN CREDENTIALS FROM FILE
		BasicConfigurator.configure();
		//new CustomSessionCredentialsProvider(Credentials.getAWS_ACCESS_KEY(),Credentials.getAWS_SECRET_KEY(),Credentials.getAWS_SESSION_TOKEN());
		
		//INSERT PROPERTIES
		Properties info = new Properties();
		//info.put("AwsCredentialsProviderClass", com.simba.athena.amazonaws.auth.profile.ProfileCredentialsProvider);
		info.put("athenaURL", Credentials.getConnectionURL());
		info.put("s3OutputLocation", Credentials.getS3OutputLocation());
		//info.put("AwsCredentialsProviderClass","CustomSessionCredentialsProvider");
		info.put("driver", "com.simba.athena.jdbc.Driver"); //POSSIBLY optional to include
		
		//profile test
		info.put("AwsCredentialsProviderClass","com.simba.athena.amazonaws.auth.profile.ProfileCredentialsProvider");
		info.put("AwsCredentialsProviderArguments", "tr-central-sandbox");
		
		//ASSIGN/LINK DRIVER
		try 
		{
			Class.forName("com.simba.athena.jdbc.Driver");
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			//System.out.println(athenaURL);
			connection = DriverManager.getConnection(athenaURL, info);
		} catch(SQLTimeoutException e) 
		{
			 System.out.println("Connection Unsuccessful: Connection timed out");
		} catch (SQLException e)
		{
			 System.out.println("Connection Unsuccessful: Error accessing the database");
		}
		
		//EXECUTE SQL CODE USING CUSTOM FUNCTIONS
		executeSingleStatement("select * from athenacrawler.test_athena_final_test limit 10;");
	}
	
	/**
	 * Contains a collection of prewritten SQL Statements. Call this function with an integer argument to get a SQL Query string 
	 * that can be used with java.sql.Connection.createStatement.execute()
	 * @param i Integer corresponding to which switch case statement to execute
	 * @return SQL Query string
	 */
	public static String getQuery(int i)
	{
		switch(i)
		{
		case 1:
			return "SQL QUERY 1";
		case 2:
			return "SQL QUERY 2";
		case 3:
			return "SQL QUERY 3";
		default:
			return null;	
		}
	}

	public static boolean executeSingleStatement(String sqlQuery)
	{
		try
		{
			connection.createStatement().execute(sqlQuery);
		} catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * TODO
	 */
//	public static boolean executePreparedStatement(String sqlQuery)
//	{
//		//
//	}


}

