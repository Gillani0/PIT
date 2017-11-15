package org.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.test.model.Currency;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("currencyConverter")
public class CurrencyConverter {

	private String version = "1.0";

	private static ArrayList<Currency> currencyList = new ArrayList<Currency>();
	private static HashMap<Integer, Currency> currencyMap = new HashMap<Integer, Currency>();
	private static HashMap<String, Currency> currencyNameMap = new HashMap<String, Currency>();

	@GET
	@Path("version")
	public String version() {
		return "The current version is " + version;
	}

	@GET
	@Path("currencies")
	@Produces(MediaType.TEXT_XML)
	public ArrayList<Currency> getCurrenciesXML(@QueryParam("sortedYN") String sortedYN) {
		initializeCurrencies();
		if (sortedYN.equals("y")) {
			ArrayList<String> currencyNames = new ArrayList<String>();
			for (int i = 0; i < currencyList.size(); i++) {
				currencyNameMap.put(currencyList.get(i).getName(), currencyList.get(i));
				currencyNames.add(currencyList.get(i).getName());
			}

			Collections.sort(currencyNames);
			currencyList.clear();
			for (int i = 0; i < currencyNames.size(); i++) {
				currencyList.add(currencyNameMap.get(currencyNames.get(i)));
			}
		}
		return currencyList;

	}

	@GET
	@Path("currencies")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCurrenciesJSON(@QueryParam("sortedYN") String sortedYN)
			throws JsonGenerationException, JsonMappingException, IOException {
		initializeCurrencies();
		if (sortedYN.equals("y")) {
			ArrayList<String> currencyNames = new ArrayList<String>();
			for (int i = 0; i < currencyList.size(); i++) {
				currencyNameMap.put(currencyList.get(i).getName(), currencyList.get(i));
				currencyNames.add(currencyList.get(i).getName());
			}

			Collections.sort(currencyNames);
			currencyList.clear();
			for (int i = 0; i < currencyNames.size(); i++) {
				currencyList.add(currencyNameMap.get(currencyNames.get(i)));
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String currencyJson = mapper.writeValueAsString(currencyList);
		return currencyJson;
	}

	@GET
	@Path("currency/{id}")
	public String currency(@PathParam("id") int id) {
		initializeCurrencies();
		return "The currency is " + currencyMap.get(id).getName();
	}

	@GET
	@Path("conversion/{source}/{destination}/{amount}")
	public String convert(@PathParam("source") String source, @PathParam("destination") String destination,
			@PathParam("amount") double amount) {
		initializeCurrencies();
		double amount2 = 0;
		switch (source) {
		case "Euro":
			if (destination.equals("Dollar")) {
				amount2 = amount * 1.09952;
			} else if (destination.equals("Yen")) {
				amount2 = amount * 114.458;
			} else
				amount2 = amount;
			break;

		case "Dollar":
			if (destination.equals("Euro")) {
				amount2 = amount * 0.909487;
			} else if (destination.equals("Yen")) {
				amount2 = amount * 104.108;
			} else
				amount2 = amount;
			break;

		case "Yen":
			if (destination.equals("Euro")) {
				amount2 = amount * 0.00873683;
			} else if (destination.equals("Dollar")) {
				amount2 = amount * 0.00960537;
			} else
				amount2 = amount;
			break;
		}

		return amount + " " + source + " = " + amount2 + " " + destination;

	}

	public static void main(String[] arg) {
		initializeCurrencies();
	}

	private static void initializeCurrencies() {
		currencyList.add(new Currency("EU", "Euro", 2000, 2));
		currencyList.add(new Currency("USA", "Dollar", 1800, 1));
		currencyList.add(new Currency("Japan", "Yen", 1945, 3));

		for (int i = 0; i < currencyList.size(); i++) {
			currencyMap.put(currencyList.get(i).getID(), currencyList.get(i));
		}

	}

}
