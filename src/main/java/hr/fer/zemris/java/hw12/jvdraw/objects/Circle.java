package hr.fer.zemris.java.hw12.jvdraw.objects;

import java.awt.Color;
import java.awt.Point;
/**
 * Object represents drawn circle.
 * @author Vilim Staroveški
 *
 */
public class Circle extends GeometricalObject {
	/**
	 * Creates new {@link Circle}
	 * @param outline outline color
	 */
	public Circle(Color outline) {
		super();
		this.outline = outline;
		name = "CIRCLE";
	}
	/**
	 * Returns circle radius.
	 * @return circle radius.
	 */
	public int getRadius() {
		return (int) Math.round( 
				Math.sqrt( 
						Math.pow(startPointX - endPointX, 2) + 
						Math.pow(startPointY - endPointY, 2)
						));
	}
	
	@Override
	public Point getUpperLeftCorner() {
		int radius = getRadius();
		return new Point(startPointX-radius, startPointY-radius);
	}

	@Override
	public Point getDownRightCorner() {
		int radius = getRadius();
		return new Point(startPointX+radius, startPointY+radius);
	}
	
	@Override
	public String toString() {
		return name+" "+startPointX+
				" "+startPointY+
				" "+getRadius()+
				" "+outline.getRed()+
				" "+outline.getGreen()+
				" "+outline.getBlue();
	}
}
