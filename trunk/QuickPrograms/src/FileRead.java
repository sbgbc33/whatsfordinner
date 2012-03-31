import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class FileRead {

	private static Map<String, Integer> m = new HashMap<String, Integer>();
	private static Map<Integer, List<String>> m2 = new HashMap<Integer, List<String>>();

	private static List<String> wordsToIgnore = new ArrayList<String>();
	static {
		wordsToIgnore.add("and");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		wordsToIgnore.add("of");
		wordsToIgnore.add("with");
		wordsToIgnore.add("for");
		wordsToIgnore.add("a");
		wordsToIgnore.add("in");
		wordsToIgnore.add("is");
		wordsToIgnore.add("or");
		wordsToIgnore.add("Job");
		wordsToIgnore.add("as");
		wordsToIgnore.add("an");
		wordsToIgnore.add("are");
		wordsToIgnore.add("we");
	}

	private static boolean shouldIgnoreWord(String word) {
		if (word == null || word.trim().isEmpty()) {
			return true;
		}

		String treim = word.trim();
		for (String w : wordsToIgnore) {
			if (w.equalsIgnoreCase(treim)) {
				return true;
			}
		}

		return false;
	}

	private static void process(String line) {
		if (line == null || line.trim().isEmpty()) {
			return;
		}
		String[] s = line.split(" ");
		for (String j : s) {
			String lowercase = j.toLowerCase();
			if (shouldIgnoreWord(lowercase)) {
				continue;
			}
			Integer count = m.get(lowercase);
			if (count == null) {
				count = 0;
			}
			count++;
			m.put(lowercase, count);
		}
	}

	private static void readURL() {
		try {
			// Create a URL for the desired page
			URL url = new URL(URL);

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(url
					.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				process(str);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void file(String filename) {
		try {
			// Open the file that is the first
			// command line parameter
			InputStream fstream = FileRead.class.getClassLoader()
					.getResourceAsStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				// System.out.println(strLine);
				process(strLine);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	private static void print() {
		for (String s : m.keySet()) {
			Integer count = m.get(s);
			List<String> l = m2.get(count);
			if (l == null) {
				l = new ArrayList<String>();
				m2.put(count, l);
			}

			l.add(s);
		}

		Set<Integer> si = m2.keySet();

		List<Integer> l3 = new ArrayList<Integer>(si);
		Collections.sort(l3);

		for (Integer c : l3) {
			List<String> l = m2.get(c);
			Collections.sort(l);
			System.out.println(String.format("%5d = %s", c, l));

		}
	}

	private static final String URL = "http://www.careerbuilder.com/JobSeeker/Jobs/JobDetails.aspx?ipath=HPRJ&&Job_DID=JHL8996R6CVHSCVYPGN";

	public static void main(String args[]) {
		file("mgr1.txt");
		// readURL();

		print();

	}
}
