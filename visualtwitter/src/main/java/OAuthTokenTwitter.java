import java.io.IOException;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;

import com.thoughtworks.xstream.XStream;

public class OAuthTokenTwitter {
	
	public static int getParentMessage(Twitter twitter, long id, int i) throws TwitterException {
		System.out.println("getting id = " +id);
		Status status = twitter.showStatus(id);
		if ( status.getInReplyToStatusId() != -1 ) {
			i++;
			getParentMessage(twitter, status.getInReplyToStatusId(), i);
		} 
		
		for(int j=0; j < i ; j++) {
			System.out.print("	");
		}
		
		System.out.println("from " + status.getUser().getName() + " text = " + status.getText());
		
		return i;
	}
	public static LinkedStatus getParentMessage(Twitter twitter, Status id) throws TwitterException {
		System.out.println("getting id = " +id);
		LinkedStatus parentStatus = null;
		if ( id.getInReplyToStatusId() != -1 ) {
			Status status = twitter.showStatus(id.getInReplyToStatusId());
			parentStatus = getParentMessage(twitter, status);
		} 

		LinkedStatus s = new LinkedStatus();
		s.setStatus(id);
		s.setParentStatus(parentStatus);
		
		return s;
	}
	
	public static String printParent(LinkedStatus tree, String tab) {
		if ( tree == null ) return tab;
		
		
		if ( tree.getParentStatus() != null ) {
			tab = printParent(tree.getParentStatus(), tab );
		}
		
		if ( tree.getStatus() != null ) {
			Status s = tree.getStatus();
			System.out.println(tab + s.getUser().getName() + ":" + s.getCreatedAt() + ":" + s.getText());
		}
		
		return tab;// + "	";
	}
	public static void main(String args[]) throws TwitterException, IOException {
		// The factory instance is re-useable and thread safe.
		Twitter twitter = new TwitterFactory().getInstance();
		// twitter.setOAuthConsumer("[consumer key]", "[consumer secret]");
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);

		XStream xstream = new XStream();

//		getParentMessage(twitter, 10955158827l, 1);
		Status status = twitter.showStatus(10791160833l);
		Status retweetstatus = status.getRetweetedStatus();
//		LinkedStatus parent = getParentMessage(twitter, status);
		
//		printParent(parent, "");
		String stxml = xstream.toXML(status);
		 System.out.println(stxml);
		 stxml = xstream.toXML(retweetstatus);
		 System.out.println(stxml);
//		
//		int i = 0;
//		if ( status.getInReplyToStatusId() != 0 ) {
//			twitter.showStatus(status.getInReplyToStatusId());
//		} else {
//			System.out.println("from " + status.getUser().getName() + " text = " + status.getText());
//		}
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
		 System.out.println(stxml);
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
