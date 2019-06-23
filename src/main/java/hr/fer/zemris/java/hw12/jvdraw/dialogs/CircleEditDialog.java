package hr.fer.zemris.java.hw12.jvdraw.dialogs;

import hr.fer.zemris.java.hw12.jvdraw.colorarea.JColorArea;
import hr.fer.zemris.java.hw12.jvdraw.objects.Circle;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Edit dialog for editing circle. It can edit circles 
 * center point, radius and line color.
 * @author Vilim Staroveški
 *
 */
public class CircleEditDialog extends JPanel {
	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**Circle to edit*/
	private Circle circle;
	/**Area for definig center point's x coordinate*/
	private JTextField startX;
	/**Area for definig center point's y coordinate*/
	private JTextField startY;
	/**Area for definig circles radius*/
	private JTextField radius;
	/**Area for definig circles line color*/
	private JColorArea colors;
	/**
	 * Creates new {@link CircleEditDialog}
	 * @param circle circle that will be redefined
	 */
	public CircleEditDialog(Circle circle) {
		this.circle = circle;
		initDialog();
		
	}
	/**
	 * Initializes dialogs GUI.
	 */
	private void initDialog() {
		setLayout(new GridLayout(4, 2));
		//row 1
		add(new JLabel("Center point X:"));
		startX = new JTextField(Integer.toString(circle.getStartPointX()));
		add(startX);
		//row 2
		add(new JLabel("Center point Y:"));
		startY = new JTextField(Integer.toString(circle.getStartPointY()));
		add(startY);
		//row 3 
		add(new JLabel("Radius:"));
		radius = new JTextField(Integer.toString(circle.getRadius()));
		add(radius);
		//row 4
		add(new JLabel("Outline color"));
		colors = new JColorArea(circle.getOutline());
		add(colors);
		
		
	}
	/**
	 * Performes all defined changes on circle.
	 */
	public void updateObject() {
		circle.setStartPoint(Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()));
		circle.setEndPoint(Integer.parseInt(startX.getText())+Integer.parseInt(radius.getText()), Integer.parseInt(startY.getText()));
		circle.setOutline(colors.getCurrentColor());
	}
}
