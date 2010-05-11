package rohan.vishaan.jaanvi;

/**
 * Determine optimum length of boxes based on width / height.
 * 
 * 
 */
public class GridUtil {

	public static Box getBoxesPerRow(final int total, final int w, final int h) {
		int perimeterOfOne = (w + h) / total;
		int width = perimeterOfOne / 2;

		Box bestFit = null;
		for (int i = 1; i < total; i++) {
			bestFit = isBestFit(i, total, w, h, bestFit);
		}

		return bestFit;
	}

	private static Box isBestFit(final int numberOfCols, final int total,
			final int w, final int h, final Box currentBestFit) {
		double widthOfSingleBox = w / numberOfCols;

		double numberOfRowsRequired = (1.0 * total) / numberOfCols;

		long numberOfRowsRequiredAslong = Math.round(numberOfRowsRequired);

		long heightOfSingleBox = h / numberOfRowsRequiredAslong;

		double ratio = (1.0 * widthOfSingleBox) / heightOfSingleBox;

		Box result = new Box(Double.valueOf(widthOfSingleBox).intValue(),
				Double.valueOf(heightOfSingleBox).intValue(), numberOfCols);

		System.out.println("ratio = " + ratio + " for numberOfCols = "
				+ numberOfCols);

		if (currentBestFit == null || ratio == 1) {
			return result;
		} else {
			double distanceFromOneForBestMatchSoFar = Math
					.abs(1.0 - currentBestFit.getRatio());
			double distanceFromOneForThisOne = Math.abs(1.0 - ratio);

			if (distanceFromOneForThisOne < distanceFromOneForBestMatchSoFar) {
				return result;
			} else {
				return currentBestFit;
			}
		}
	}
}
