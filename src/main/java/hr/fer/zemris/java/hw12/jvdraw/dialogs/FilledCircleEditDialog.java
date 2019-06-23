package hr.fer.zemris.java.hw12.jvdraw.dialogs;

import hr.fer.zemris.java.hw12.jvdraw.colorarea.JColorArea;
import hr.fer.zemris.java.hw12.jvdraw.objects.FilledCircle;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Edit dialog for editing filled circle. It can edit filled circles 
 * center point, radius, outline color and inside color.
 * @author Vilim Staroveški
 *
 */
public class FilledCircleEditDialog extends JPanel{

	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**Filled circle to edit*/
	private FilledCircle fcircle;
	/**Area for definig center point's x coordinate*/
	private JTextField startX;
	/**Area for definig center point's y coordinate*/
	private JTextField startY;
	/**Area for definig circles radius*/
	private JTextField radius;
	/**Area for definig circles line color*/
	private JColorArea outlineColors;
	/**Area for definig circles inside color*/
	private JColorArea insideColors;
	
	/**
	 * Creates new {@link CircleEditDialog}
	 * @param fcircle circle that will be redefined
	 */
	public FilledCircleEditDialog(FilledCircle fcircle) {
		this.fcircle = fcircle;
		initDialog();
		
	}
	/**
	 * Initializes dialogs GUI.
	 */
	private void initDialog() {
		setLayout(new GridLayout(5, 2));
		//row 1
		add(new JLabel("Center point X:"));
		startX = new JTextField(Integer.toString(fcircle.getStartPointX()));
		add(startX);
		//row 2
		add(new JLabel("Center point Y:"));
		startY = new JTextField(Integer.toString(fcircle.getStartPointY()));
		add(startY);
		//row 3 
		add(new JLabel("Radius:"));
		radius = new JTextField(Integer.toString(fcircle.getRadius()));
		add(radius);
		//row 4
		add(new JLabel("Outline color"));
		outlineColors = new JColorArea(fcircle.getOutline());
		add(outlineColors);
		//row 5
		add(new JLabel("Inside color"));
		insideColors = new JColorArea(fcircle.getInside());
		add(insideColors);
		
	}
	/**
	 * Performes all defined changes on circle.
	 */
	public void updateObject() {
		fcircle.setStartPoint(Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()));
		fcircle.setEndPoint(Integer.parseInt(startX.getText())+Integer.parseInt(radius.getText()), Integer.parseInt(startY.getText()));
		fcircle.setOutline(outlineColors.getCurrentColor());
		fcircle.setInsideColor(insideColors.getCurrentColor());
	}
}
