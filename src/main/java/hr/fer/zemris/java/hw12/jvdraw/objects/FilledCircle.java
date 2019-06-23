package hr.fer.zemris.java.hw12.jvdraw.objects;

import java.awt.Color;
/**
 * Object that represents filled circle
 * @author Vilim Staroveški
 *
 */
public class FilledCircle extends Circle {
	/**Inside color of this filled circle*/
	protected Color inside;
	/**
	 * Creates new {@link FilledCircle}
	 * @param outline outline color
	 * @param inside inside color
	 */
	public FilledCircle(Color outline, Color inside) {
		super(outline);
		this.inside = inside;
		name = "FCIRCLE";
	}
	/**
	 * Returns inside color of this filled circle
	 * @return inside color of this filled circle
	 */
	public Color getInside() {
		return inside;
	}
	/**
	 * Sets inside color of this Filled circle
	 * @param color new inside color of this filled circle
	 */
	public void setInsideColor(Color color) {
		inside = color;
	}
	
	@Override
	public String toString() {
		return super.toString()+
				" "+inside.getRed()+
				" "+inside.getGreen()+
				" "+inside.getBlue();
	}
}
