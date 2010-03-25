import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SampleTwitter {

	private static String twitterID = System.getProperty("username");
	private static String twitterPassword = System.getProperty("password");

	public static void main(String args[]) throws TwitterException {
		// The factory instance is re-useable and thread safe.
		Twitter twitter = new TwitterFactory().getInstance(twitterID,
				twitterPassword);
		List<Status> statuses = twitter.getFriendsTimeline();
		System.out.println("Showing friends timeline.");
		for (Status status : statuses) {
			System.out.println(status.getUser().getName() + ":"
					+ status.getText());
		}

	}
}
