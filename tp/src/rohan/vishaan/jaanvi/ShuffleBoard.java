package rohan.vishaan.jaanvi;

import java.util.Random;

public class ShuffleBoard {
	private int total;

	private Integer[] pics;

	public ShuffleBoard(Integer[] pics, int howMany) {
		total = howMany;
		this.pics = new Integer[howMany];

		for (int i = 0; i < howMany; i++) {
			this.pics[i] = pics[i];
		}
	}

	public void reset(Integer[] pics) {
		total = pics.length;
		this.pics = pics;
	}

	private void printBoard() {
		for (int i = 0; i < this.board.length; i++) {
			System.out.println(" i = " + i + " card = " + this.board[i]);
		}
	}

	public static void main(String args[]) {
		int count = 2;
		Integer[] p = new Integer[count];
		for (int i = 0; i < count; i++) {
			p[i] = i;
		}
		ShuffleBoard s = new ShuffleBoard(p, count);
		s.shuffle();
		s.printBoard();
	}

	public Integer[] shuffle() {
		board = new Integer[total * 2];
		for (int i = 0; i < total; i++) {
			hideCard(pics[i]);
			hideCard(pics[i]);
		}

		return board;
	}

	private Integer[] board;

	private int getNextRandomLocation(int upperLimit) {
		Random r = new Random();
		return r.nextInt(upperLimit);
	}

	private void addToBoard(int loc, int id) {
		board[loc] = id;
	}

	private void hideCard(int id) {
		int loc = getNextRandomLocation(board.length);

		boolean added = false;
		for (int i = loc; i < board.length; i++) {
			if (board[i] == null) {
				addToBoard(i, id);
				added = true;
				break;
			}
		}

		if (!added) {
			for (int i = 0; i < loc; i++) {
				if (board[i] == null) {
					addToBoard(i, id);
					added = true;
					break;
				}
			}
		}

		if (!added) {
			throw new RuntimeException(
					"Can't find a place to put item on board. loc=" + loc
							+ " board.size() = " + board.length);
		}
	}
}
