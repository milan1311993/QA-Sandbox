import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderForLogIn {

	@DataProvider(name = "LoginData")
	public Object[][] loginData(Method m) {
		Object[][] obj = { new Object[1], new Object[2] };

		if (m.getName().contains("valid")) {
			obj = new Object[1][2];

			obj[0][0] = "milanmihajlovic93@gmail.com";
			obj[0][1] = "milan1311";
		}

		if (m.getName().contains("wrongEmail")) {
			obj = new Object[1][2];

			obj[0][0] = "milanmihajlvi@gmail.com";
			obj[0][1] = "milan1311";
		}

		if (m.getName().contains("invalidEmail")) {
			obj = new Object[1][2];

			obj[0][0] = "milanmihajlovic93@gmailcom";
			obj[0][1] = "milan1311";
		}

		if (m.getName().contains("wrongPassword")) {
			obj = new Object[1][2];

			obj[0][0] = "milanmihajlovic93@gmail.com";
			obj[0][1] = "milan1";
		}

		if (m.getName().contains("invalidPassword")) {
			obj = new Object[1][2];

			obj[0][0] = "milanmihajlovic93@gmail.com";
			obj[0][1] = "milan";
		}

		if (m.getName().contains("emptyEmail")) {
			obj = new Object[1][2];

			obj[0][0] = "";
			obj[0][1] = "milan1311";
		}

		if (m.getName().contains("emptyPassword")) {
			obj = new Object[1][2];

			obj[0][0] = "milanmihajlovic93@gmail.com";
			obj[0][1] = "";
		}
		
		if (m.getName().contains("emptyEmailAndPassword")) {
			obj = new Object[1][2];

			obj[0][0] = "";
			obj[0][1] = "";
		}
		
		

		return obj;
	}
}
