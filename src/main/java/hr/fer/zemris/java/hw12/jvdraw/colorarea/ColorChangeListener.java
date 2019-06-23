package hr.fer.zemris.java.hw12.jvdraw.colorarea;

import java.awt.Color;
/**
 * Interface for object that listens for changes in components
 * that store colors.
 * @author Vilim Staroveški
 *
 */
public interface ColorChangeListener {
	/**
	 * Method called when new color is selected.
	 * @param source component that contains this color
	 * @param oldColor old color of component
	 * @param newColor new color of component.
	 */
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor);
}
