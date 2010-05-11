package rohan.vishaan.jaanvi;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GridUtilTest extends TestCase {

	public void testGetBoxesPerRow() {
		Box expected = new Box(100, 100, 2);
		Box actual = GridUtil.getBoxesPerRow(4, 200, 200);
		Assert.assertEquals(expected, actual);

		expected = new Box(150, 150, 2);
		actual = GridUtil.getBoxesPerRow(4, 300, 300);
		Assert.assertEquals(expected, actual);

		expected = new Box(100, 150, 3);
		actual = GridUtil.getBoxesPerRow(5, 300, 300);
		Assert.assertEquals(expected, actual);

		expected = new Box(100, 150, 3);
		actual = GridUtil.getBoxesPerRow(6, 300, 300);
		Assert.assertEquals(expected, actual);
	}

}
