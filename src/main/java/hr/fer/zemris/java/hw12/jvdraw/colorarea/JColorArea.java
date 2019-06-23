package hr.fer.zemris.java.hw12.jvdraw.colorarea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
/**
 * Component that stores a selected color. Implementation
 * of {@link IColorProvider}
 * @author Vilim Staroveški
 *
 */
public class JColorArea extends JComponent implements IColorProvider {

	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**Stored color*/
	private Color selectedColor;
	/**List of listeners of this object*/
	private List<ColorChangeListener> listeners;
	/**
	 * Creates new {@link JColorArea} with initial color selected
	 * @param initialColor initial color selected
	 */
	public JColorArea(Color initialColor) {

		selectedColor = initialColor;
		listeners = new ArrayList<ColorChangeListener>();
		
		initGUI();
	}
	/**
	 * Initializes GUI of this component.
	 */
	private void initGUI() {

		this.setOpaque(true);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Color choosed = JColorChooser.showDialog(JColorArea.this, "Select color", selectedColor);
				if(choosed == null) {
					return;
				}
				for(ColorChangeListener listener : listeners) {
					listener.newColorSelected(JColorArea.this, selectedColor, choosed);
				}
				selectedColor = choosed;
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(selectedColor);
		g2d.fillRect(0, 0, JColorArea.this.getWidth(), JColorArea.this.getHeight());
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(15, 15);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
	
	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Color getCurrentColor() {
		return selectedColor;
	}
	/**
	 * Adds listener of this component. 
	 * @param l listener of this component.
	 */
	public void addColorChangeListener(ColorChangeListener l) {
		listeners.add(l);
	}
	/**
	 * Removes listener of this component. 
	 * @param l listener of this component. 
	 */
	public void removeColorChangeListener(ColorChangeListener l) {
		listeners.add(l);
	}
}
