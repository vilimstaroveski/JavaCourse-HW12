package hr.fer.zemris.java.hw12.jvdraw.dialogs;

import hr.fer.zemris.java.hw12.jvdraw.colorarea.JColorArea;
import hr.fer.zemris.java.hw12.jvdraw.objects.Line;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Edit dialog for editing line. It can edit lines 
 * start and end point and line color
 * @author Vilim Staroveški
 *
 */
public class LineEditDialog extends JPanel {
	
	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**Line that is going to be modified*/
	private Line line;
	/**Area for defining line's start point x coordinate*/
	private JTextField startX;
	/**Area for defining line's start point y coordinate*/
	private JTextField startY;
	/**Area for defining line's end point x coordinate*/
	private JTextField endX;
	/**Area for defining line's end point y coordinate*/
	private JTextField endY;
	/**Area for defining line's color*/
	private JColorArea colors;
	/**
	 * Creates new {@link LineEditDialog}
	 * @param line line to define.
	 */
	public LineEditDialog(Line line) {
		this.line = line;
		initDialog();
	}
	/**
	 * Initializes GUI of this dialog
	 */
	private void initDialog() {
		setLayout(new GridLayout(5, 2));

		//row 1
		add(new JLabel("Start point X:"));
		startX = new JTextField(Integer.toString(line.getStartPointX()));
		add(startX);
		//row 2
		add(new JLabel("Start point Y:"));
		startY = new JTextField(Integer.toString(line.getStartPointY()));
		add(startY);
		//row 3 
		add(new JLabel("End point X:"));
		endX = new JTextField(Integer.toString(line.getEndPointX()));
		add(endX);
		//row 4
		add(new JLabel("End point Y:"));
		endY = new JTextField(Integer.toString(line.getEndPointY()));
		add(endY);
		//row 5
		add(new JLabel("Line color"));
		colors = new JColorArea(line.getOutline());
		colors.setMaximumSize(new Dimension(15, 15));
		add(colors);
	}
	/**
	 * Performes given modifications on line
	 */
	public void updateObject() {
		line.setStartPoint(Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()));
		line.setEndPoint(Integer.parseInt(endX.getText()), Integer.parseInt(endY.getText()));
		line.setOutline(colors.getCurrentColor());
	}

}
