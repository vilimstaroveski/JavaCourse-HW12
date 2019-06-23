package hr.fer.zemris.java.hw12.jvdraw.objects;

import java.awt.Color;
import java.awt.Point;
/**
 * Object that represents a line.
 * @author Vilim Staroveški
 *
 */
public class Line extends GeometricalObject {
	/**
	 * Creates new {@link Line}
	 * @param color outline color
	 */
	public Line(Color color) {
		super();
		this.outline = color;
		name = "LINE";
	}

	@Override
	public Point getUpperLeftCorner() {
		int minX = startPointX <= endPointX ? startPointX : endPointX;
		int minY = startPointY <= endPointY ? startPointY : endPointY;
		return new Point(minX, minY);
	}

	@Override
	public Point getDownRightCorner() {
		int maxX = startPointX >= endPointX ? startPointX : endPointX;
		int maxY = startPointY >= endPointY ? startPointY : endPointY;
		return new Point(maxX, maxY);
	}
	
}
