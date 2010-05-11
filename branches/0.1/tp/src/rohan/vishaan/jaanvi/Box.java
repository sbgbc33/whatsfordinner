package rohan.vishaan.jaanvi;

public class Box {
	private int w;
	private int h;
	private int numberPerRow;

	public Box(int w, int h, int numberPerRow) {
		super();
		this.w = w;
		this.h = h;
		this.numberPerRow = numberPerRow;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + h;
		result = prime * result + numberPerRow;
		result = prime * result + w;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Box other = (Box) obj;
		if (h != other.h)
			return false;
		if (numberPerRow != other.numberPerRow)
			return false;
		if (w != other.w)
			return false;
		return true;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getNumberPerRow() {
		return numberPerRow;
	}

	public void setNumberPerRow(int numberPerRow) {
		this.numberPerRow = numberPerRow;
	}

	public double getRatio() {
		double ratio = (1.0 * this.w) / this.h;
		return ratio;
	}
}
