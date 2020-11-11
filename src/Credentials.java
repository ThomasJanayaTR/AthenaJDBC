import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Credentials {
 private static String AWS_ACCESS_KEY = "";
 private static String AWS_SECRET_KEY = "";
 private static String AWS_SESSION_TOKEN = "";
 
 public static String getAWS_ACCESS_KEY() {
	return AWS_ACCESS_KEY;
}

public static void setAWS_ACCESS_KEY(String aWS_ACCESS_KEY) {
	AWS_ACCESS_KEY = aWS_ACCESS_KEY;
}

public static String getAWS_SECRET_KEY() {
	return AWS_SECRET_KEY;
}

public static void setAWS_SECRET_KEY(String aWS_SECRET_KEY) {
	AWS_SECRET_KEY = aWS_SECRET_KEY;
}

public static String getAWS_SESSION_TOKEN() {
	return AWS_SESSION_TOKEN;
}

public static void setAWS_SESSION_TOKEN(String aWS_SESSION_TOKEN) {
	AWS_SESSION_TOKEN = aWS_SESSION_TOKEN;
}

public static String getConnectionURL() {
	return connectionURL;
}

public static void setConnectionURL(String connectionURL) {
	Credentials.connectionURL = connectionURL;
}

public static String getS3OutputLocation() {
	return s3OutputLocation;
}

public static void setS3OutputLocation(String s3OutputLocation) {
	Credentials.s3OutputLocation = s3OutputLocation;
}

private static String connectionURL = "jdbc:aws:athena://AwsRegion=us-east-2";
 private static String s3OutputLocation = "s3://a202826-athena-test/Athena-output/";
// private String QUERY ="select * from athenacrawler.test_athena_final_test limit 10;";
// private String	DRIVER_CLASS = "com.simba.athena.jdbc.Driver";	 
 
 public static void read(String textFile) {
		//new file
		File file = new File(textFile);
		//access file
		try {
			//scanner to read through file
			Scanner scanner = new Scanner(new FileInputStream(file));
			
			//loop to scans file as long as there is another line
			while(scanner.hasNext()) {
				//string variable that hold the line to	check for what needs to be found
				String line = scanner.nextLine();
				if(line.contains("aws_access_key_id")) {
					String array[] = line.split("\\s+");
					AWS_ACCESS_KEY = array[2];
					System.out.println("Access Key "+ AWS_ACCESS_KEY);
				}
				if(line.contains("aws_secret_access_key")) {
					String array[] = line.split("\\s+");
					AWS_SECRET_KEY = array[2];
					System.out.println("Secret Key "+ AWS_SECRET_KEY);
				}
				if(line.contains("aws_session_token")) {
					String array[] = line.split("\\s+");
					AWS_SESSION_TOKEN = array[2];
					System.out.println("Session Token "+ AWS_SESSION_TOKEN);
				}
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found!");
		}	
	}
		
	
}

