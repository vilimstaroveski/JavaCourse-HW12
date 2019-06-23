package hr.fer.zemris.java.hw12.jvdraw.colorarea;

import java.awt.Color;
/**
 * Interface for objects that store a {@link Color} item.
 * @author Vilim Staroveški
 *
 */
public interface IColorProvider {
	/**
	 * Returns current color stored in this object.
	 * @return current color stored in this object.
	 */
	public Color getCurrentColor();
}
