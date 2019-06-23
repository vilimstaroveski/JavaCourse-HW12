package hr.fer.zemris.java.hw12.jvdraw.colorarea;

import java.awt.Color;

import javax.swing.JLabel;
/**
 * Label that shows foreground and background color components.
 * @author Vilim Staroveški
 *
 */
public class StatusLabel extends JLabel implements ColorChangeListener {
	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**Color for background*/
	private Color backgroundColor;
	/**Color for foreground*/
	private Color foregroundColor;
	/**{@link JColorArea} for background color*/
	private JColorArea background;
	/**{@link JColorArea} for foreground color*/
	private JColorArea foreground;
	/**
	 * Creates new {@link StatusLabel}
	 * @param background {@link JColorArea} for background color
	 * @param foreground {@link JColorArea} for foreground color
	 */
	public StatusLabel(JColorArea background, JColorArea foreground) {
		super();
		this.background = background;
		this.foreground = foreground;

		this.backgroundColor = this.background.getCurrentColor();
		this.foregroundColor = this.foreground.getCurrentColor();
		updateText();
	}

	@Override
	public void newColorSelected(IColorProvider source, Color oldColor,
			Color newColor) {
		if(source == background) {
			backgroundColor = newColor;
		}
		else if(source == foreground) {
			foregroundColor = newColor;
		}
		updateText();
	}
	/**
	 * Updates text that this label showing
	 */
	private void updateText() {
		
		this.setText("Foreground color: ("+foregroundColor.getRed()+", "+foregroundColor.getGreen()+", "+foregroundColor.getBlue()+"), "
				+ "background color: ("+backgroundColor.getRed()+", "+backgroundColor.getGreen()+", "+backgroundColor.getBlue()+").");

	}
}
