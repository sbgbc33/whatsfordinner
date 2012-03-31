import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class DidIWin {

	private static void process(String line) {
		if (line == null || line.trim().isEmpty()) {
			return;
		}
		String[] s = line.split(",");
		isWinner(s);
	}

	private static boolean isWinningNumber(String s) {
		for (int i = 0; i < 5; i++) {
			if (winning[i].equalsIgnoreCase(s)) {
				return true;
			}

		}
		return false;
	}

	private static boolean isGoldWinner(String s) {
		return winning[5].equalsIgnoreCase(s);
	}

	private static void processChoice(List<String> w, String choice) {
		if (isWinningNumber(choice)) {
			w.add(choice);
		}

	}

	private static boolean debug = false;

	// winning # are 17-39-42-47-56---12
	private static void isWinner(String[] s) {

		String forA = s[0];
		String line = s[1];
		String gold = s[8];

		List<String> winningNumbersFound = new ArrayList<String>();

		processChoice(winningNumbersFound, s[2]);
		processChoice(winningNumbersFound, s[3]);
		processChoice(winningNumbersFound, s[4]);
		processChoice(winningNumbersFound, s[5]);
		processChoice(winningNumbersFound, s[6]);

		if (winningNumbersFound.isEmpty()) {
			if (debug) {
				System.out.println("No winning numbers in ticket for " + forA
						+ " ticket " + line);
			}
		} else {
			System.out.println("Found " + winningNumbersFound.size()
					+ " winning numbers for " + forA + " ticket " + line
					+ " winningNumbersFound = " + winningNumbersFound);
		}

		boolean goldWinner = isGoldWinner(gold);

		if (goldWinner) {
			System.out.println("Found GOLD CHOICE gold = " + gold + " for "
					+ forA + " ticket " + line);
		} else {
			if (debug) {
				System.out
						.println("No GOLD CHOICE winning numbers in ticket for "
								+ forA + " ticket " + line);
			}
		}

		if (goldWinner && winningNumbersFound.size() == 5) {
			for (int i = 0; i < 100000; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("JACKPOT WINNER");
			}
		}

		if (goldWinner) {
			switch (winningNumbersFound.size()) {
			case 4:
				System.out
						.println("You won $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000 $10,000");
				break;
			case 3:
				System.out
						.println("You won $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150 $150");
				break;
			case 2:
				System.out.println("You won $10");
				break;
			case 1:
				System.out.println("You won $3");
				break;
			case 0:
				System.out.println("You won $2");
				break;
			}
		} else {
			switch (winningNumbersFound.size()) {
			case 4:
				System.out.println("You won $150");
				break;
			case 3:
				System.out.println("You won $7");
				break;
			}
		}

	}

	private static void file(String filename) {
		try {
			// Open the file that is the first
			// command line parameter
			InputStream fstream = DidIWin.class.getClassLoader()
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

	private static void readWinningNumbers(String filename) {
		try {
			// Open the file that is the first
			// command line parameter
			// String.class.getClassLoader().getResourceAsStream(filename);
			// FileInputStream fstream = new FileInputStream(filename);
			InputStream fstream = DidIWin.class.getClassLoader()
					.getResourceAsStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				winning = strLine.split(",");
				System.out.println("winning # are " + winning[0] + "-"
						+ winning[1] + "-" + winning[2] + "-" + winning[3]
						+ "-" + winning[4] + "---" + winning[5]);
				break;
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static String[] winning = null;

	public static void main(String args[]) {

		readWinningNumbers("winningNumbers.csv");
		file("mega2012.csv");
	}
}
