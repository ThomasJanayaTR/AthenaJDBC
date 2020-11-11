import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.simba.athena.amazonaws.auth.BasicSessionCredentials;

public class CustomSessionCredentialsProvider implements AWSCredentialsProvider {

	private BasicSessionCredentials m_credentials;
	
	public CustomSessionCredentialsProvider(String awsAccessKey, String awsSecretKey, String sessionToken)
	{
		m_credentials = new BasicSessionCredentials(awsAccessKey, awsSecretKey, sessionToken);
	}
	
	@Override
	public AWSCredentials getCredentials() {
		return m_credentials;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
