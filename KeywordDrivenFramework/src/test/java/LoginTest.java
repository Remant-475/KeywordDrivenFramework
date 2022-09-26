import org.testng.annotations.Test;

import com.utility.KeywordDrivenEngine;

public class LoginTest {
private KeywordDrivenEngine keyword;
	
	/**
	 * Method: To Test Login Data
	 */
	@Test
	public void loginTest() {
		
		keyword = new KeywordDrivenEngine();
		keyword.startExecution("LoginTestData");
}
}
