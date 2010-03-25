import java.io.IOException;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;

import com.thoughtworks.xstream.XStream;

public class OAuthTokenTwitter {
	public static void main(String args[]) throws TwitterException, IOException {
		// The factory instance is re-useable and thread safe.
		Twitter twitter = new TwitterFactory().getInstance();
		// twitter.setOAuthConsumer("[consumer key]", "[consumer secret]");
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);

		XStream xstream = new XStream();

		Status status = twitter.showStatus(10766116499l);
		String stxml = xstream.toXML(status);
		System.out.println(stxml);
		//	
		// ResponseList<Status> sList = twitter.getUserTimeline("iamsrk");
		//
		// for (Status st : sList) {
		// }
		//
		// String x = xstream.toXML(twitter);
		// System.out.println(x);
		// if (true)
		// System.exit(0);
		//
		// for (User u : r) {
		//
		// String xml = xstream.toXML(u);
		//
		// System.out.println(xml);
		// }
		System.exit(0);
	}

	private static AccessToken loadAccessToken() {
		String token = System.getProperty("token");// load from a persistent
		// store
		String tokenSecret = System.getProperty("tokenSecret");// load from a
		// persistent
		// store
		return new AccessToken(token, tokenSecret);
	}

	private static void storeAccessToken(int useId, AccessToken at) {
		// store at.getToken()
		System.out.println("token = " + at.getToken());
		System.out.println("tokenSecret = " + at.getTokenSecret());
		// store at.getTokenSecret()
	}
}
