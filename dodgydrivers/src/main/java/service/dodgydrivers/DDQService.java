package service.dodgydrivers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import service.core.AbstractQuotationService;
import service.core.ClientInfo;
import service.core.Quotation;

/**
 * Implementation of Quotation Service for Dodgy Drivers Insurance Company
 *  
 * @author Rem
 *
 */
@RestController
public class DDQService extends AbstractQuotationService {
	// All references are to be prefixed with an DD (e.g. DD001000)
	public static final String PREFIX = "DD";
	public static final String COMPANY = "Dodgy Drivers Corp.";
	
	/**
	 * Quote generation:
	 * 5% discount per penalty point (3 points required for qualification)
	 * 50% penalty for <= 3 penalty points
	 * 10% discount per year no claims
	 */
	public Quotation generateQuotation(ClientInfo info) {
		// Create an initial quotation between 800 and 1000
		double price = generatePrice(800, 200);
		
		// 5% discount per penalty point (3 points required for qualification)
		int discount = (info.getPoints() > 3) ? 5*info.getPoints():-50;
		
		// Add a no claims discount
		discount += getNoClaimsDiscount(info);
		
        // Generate the quotation and send it back
        Quotation quotation = new Quotation();
        quotation.setCompany(COMPANY);
        quotation.setReference(generateReference(PREFIX));
        quotation.setPrice((price * (100 - discount)) / 100);
        return quotation;
	}

	private int getNoClaimsDiscount(ClientInfo info) {
		return 10*info.getNoClaims();
	}

    private Map<String, Quotation> quotations = new HashMap<>();

    @RequestMapping(value="/quotations", method=RequestMethod.POST)
    public ResponseEntity<Quotation> createQuotation(@RequestBody ClientInfo info) throws java.net.URISyntaxException {
        Quotation quotation = generateQuotation(info);
        quotations.put(quotation.getReference(), quotation);
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()+"/quotations"+quotation.getReference();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));
        return new ResponseEntity<>(quotation, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/quotations/{reference}", method = RequestMethod.GET)
    public Quotation getResource(@PathVariable("reference") String reference) {
        Quotation quotation = quotations.get(reference);
        if (quotation == null) throw new NoSuchQuotationException();
        return quotation;
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public class NoSuchQuotationException extends RuntimeException {
        static final long serialVersionUID = -6516152229878843037L;
    }
}
