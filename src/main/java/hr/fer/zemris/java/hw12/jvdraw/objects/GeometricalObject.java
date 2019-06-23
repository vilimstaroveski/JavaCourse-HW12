package hr.fer.zemris.java.hw12.jvdraw.objects;

import java.awt.Color;
import java.awt.Point;
/**
 * Class that defines geometrical objects such as line, circle, filled circle...
 * @author Vilim Staroveški
 *
 */
public abstract class GeometricalObject {
	/**Name of this geometrical object*/
	protected String name;
	/**Outline color of this object*/
	protected Color outline;
	/**Start point x coordinate*/
	protected int startPointX;
	/**Start point y coordinate*/
	protected int startPointY;
	/**End point x coordinate*/
	protected int endPointX;
	/**End point y coordinate*/
	protected int endPointY;
	/**
	 * Method that returns upper left corner of this object
	 * @return upper left corner of this object
	 */
	public abstract Point getUpperLeftCorner();
	/**
	 * Returns down right corner of this object
	 * @return down right corner of this object
	 */
	public abstract Point getDownRightCorner();
	/**
	 * Sets start point of this object
	 * @param x start point x coordinate
	 * @param y start point y coordinate
	 */
	public void setStartPoint(int x, int y) {
		this.startPointX = x;
		this.startPointY = y;
	}
	/**
	 * Sets end point of this object
	 * @param x end point x coordinate
	 * @param y end point y coordinate
	 */
	public void setEndPoint(int x, int y) {
		this.endPointX = x;
		this.endPointY = y;
	}
	/**
	 * Returns name of this object
	 * @return name of this object
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns outline color of this object
	 * @return outline color of this object
	 */
	public Color getOutline() {
		return outline;
	}
	/**
	 * Returns start point x coordinate
	 * @return start point x coordinate
	 */
	public int getStartPointX() {
		return startPointX;
	}
	/**
	 * Returns start point y coordinate
	 * @return start point y coordinate
	 */
	public int getStartPointY() {
		return startPointY;
	}
	/**
	 * Returns end point x coordinate
	 * @return end point x coordinate
	 */
	public int getEndPointX() {
		return endPointX;
	}
	/**
	 * Returns end point y coordinate
	 * @return end point y coordinate
	 */
	public int getEndPointY() {
		return endPointY;
	}
	/**
	 * Sets outline color of this object
	 * @param color new outline color of this object
	 */
	public void setOutline(Color color) {
		outline = color;
	}
	
	@Override
	public String toString() {
		return name+" "+startPointX+
				" "+startPointY+
				" "+endPointX+
				" "+endPointY+
				" "+outline.getRed()+
				" "+outline.getGreen()+
				" "+outline.getBlue();
	}
}
