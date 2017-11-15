package org.twitter.rest;

import java.net.URLEncoder;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import twitter4j.TwitterException;

public class TwitterRESTClient {
	final static String KEY = "YOUR KEY";
	final static String SECRET = "YOUR SECRET";
	final static String TOKEN = "YOU TOKEN";
	final static String TOKENPASS = " YOUR TOKEN PASS";

	public static void main(String[] args) throws ParseException, TwitterException {

		getAccountInfo();
	}

	private static void getAccountInfo() throws ParseException {

		// instantiation of the service and authentication
		OAuthService service = new ServiceBuilder().provider(TwitterApi.SSL.class).apiKey(KEY).apiSecret(SECRET)
				.build();
		// Token requestToken = service.getRequestToken();
		Token accessToken = new Token(TOKEN, TOKENPASS);
		OAuthRequest request = new OAuthRequest(Verb.GET,
				"https://api.twitter.com/1.1/account/verify_credentials.json");
		service.signRequest(accessToken, request);
		// getting a response
		Response response = request.send();
		// Printing the response
		System.out.println(response.getBody());

		// JSON Parser
		// Create parser
		JSONParser parser = new JSONParser();

		// Pass parameter to the parser

		Object objetNormal;

		// verifying if it's an object or array
		// in our case it's just an object
		objetNormal = parser.parse(response.getBody()); // parsing the response
														// and saving it in a
														// object
		JSONObject jsonObject = (JSONObject) objetNormal; // saving the
															// objetNormal data
															// into a JSONObject
															// type

		// Question 1
		// Parsing the response
		// since the parser is already created we just get and print the results
		System.out.println("the id_str is " + jsonObject.get("id_str"));
		System.out.println("the name is " + jsonObject.get("name"));
		System.out.println("the screen_name is " + jsonObject.get("screen_name"));
		System.out.println("the followers_count " + jsonObject.get("followers_count"));
		System.out.println("the friends_count is " + jsonObject.get("friends_count"));
		System.out.println("the created_at is " + jsonObject.get("created_at"));
		System.out.println("the time_ zone is " + jsonObject.get("time_ zone"));

		/**
		 * Post a new Tweet using REST Web Service
		 */
		// postTweet(service, accessToken);
	}

	private static void postTweet(OAuthService service, Token accessToken) {
		String tweet = "This is my RESTFUL status Generated at " + (new Date().toString());

		/// We create a new request changing the resource
		// // This time our request is a POST with our tweet encoded in the URL
		// to
		// // be sent

		OAuthRequest requestUpdateStatus = new OAuthRequest(Verb.POST,
				"https://api.twitter.com/1.1/statuses/update.json?status=" + URLEncoder.encode(tweet));
		//
		// // this resource needs an authentication son we get it
		service.signRequest(accessToken, requestUpdateStatus);
		// // getting a response
		Response responseUpdateStatus = requestUpdateStatus.send();
		// // Printing the response
		System.out.println(responseUpdateStatus.getBody());
	}
}
