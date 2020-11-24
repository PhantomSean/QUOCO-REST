import java.text.NumberFormat;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import service.core.ClientApplication;
import service.core.ClientInfo;
import service.core.Quotation;

public class Client {
    /**
	 * This is the starting point for the application. Here, we must
	 * get a reference to the Broker Service and then invoke the
	 * getQuotations() method on that service.
	 * 
	 * Finally, you should print out all quotations returned
	 * by the service.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        setClientApplications();
        for(ClientApplication application : clientApplications) {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<ClientApplication> request = new HttpEntity<>(application);
            ClientApplication result = 
                restTemplate.postForObject("http://localhost:8079/applications", request, ClientApplication.class);
            for(Quotation quote : result.getQuotations())
                application.addQuotation(quote);
        }

        for(ClientApplication application : clientApplications) {
            displayProfile(application.getClient());
            for(Quotation quote : application.getQuotations())
                displayQuotation(quote);
        }
   } 
       

    /**
	 * Display the client info nicely.
	 * 
	 * @param info
	 */
    public static void displayProfile(ClientInfo info) {
        System.out.println("|=================================================================================================================|");
		System.out.println("|                                     |                                     |                                     |");
		System.out.println(
				"| Name: " + String.format("%1$-29s", info.getName()) + 
				" | Gender: " + String.format("%1$-27s", (info.getGender()==ClientInfo.MALE?"Male":"Female")) +
				" | Age: " + String.format("%1$-30s", info.getAge())+" |");
		System.out.println(
				"| License Number: " + String.format("%1$-19s", info.getLicenseNumber()) + 
				" | No Claims: " + String.format("%1$-24s", info.getNoClaims()+" years") +
				" | Penalty Points: " + String.format("%1$-19s", info.getPoints())+" |");
		System.out.println("|                                     |                                     |                                     |");
		System.out.println("|=================================================================================================================|");
    }
    
    /**
	 * Display a quotation nicely - note that the assumption is that the quotation will follow
	 * immediately after the profile (so the top of the quotation box is missing).
	 * 
	 * @param quotation
	 */
    public static void displayQuotation(Quotation quotation) {
		System.out.println(
				"| Company: " + String.format("%1$-26s", quotation.getCompany()) + 
				" | Reference: " + String.format("%1$-24s", quotation.getReference()) +
				" | Price: " + String.format("%1$-28s", NumberFormat.getCurrencyInstance().format(quotation.getPrice()))+" |");
		System.out.println("|=================================================================================================================|");
    }
    
    /**
	 * Test Data
	 */
    public static final ClientInfo[] clients = {
		new ClientInfo("Niki Collier", ClientInfo.FEMALE, 43, 0, 5, "PQR254/1"),
		new ClientInfo("Old Geeza", ClientInfo.MALE, 65, 0, 2, "ABC123/4"),
		new ClientInfo("Hannah Montana", ClientInfo.FEMALE, 16, 10, 0, "HMA304/9"),
		new ClientInfo("Rem Collier", ClientInfo.MALE, 44, 5, 3, "COL123/3"),
		new ClientInfo("Jim Quinn", ClientInfo.MALE, 55, 4, 7, "QUN987/4"),
		new ClientInfo("Donald Duck", ClientInfo.MALE, 35, 5, 2, "XYZ567/9")	
    };

    public static ClientApplication[] clientApplications = new ClientApplication[clients.length];

    public static ClientApplication setClientApplication(ClientInfo info, int id) {
        ClientApplication application = new ClientApplication();
        application.setClient(info);
        application.setId(id);
        return application;
    }

    public static void setClientApplications() {
        int i = 0;
        for (ClientInfo client : clients) {
            clientApplications[i] = setClientApplication(client, i+1);
            i++;
        }
    }
}
