package hr.fer.zemris.java.hw12.jvdraw.actions;

import hr.fer.zemris.java.hw12.jvdraw.JVDraw;
import hr.fer.zemris.java.hw12.jvdraw.list.ObjectsModel;
import hr.fer.zemris.java.hw12.jvdraw.objects.Circle;
import hr.fer.zemris.java.hw12.jvdraw.objects.FilledCircle;
import hr.fer.zemris.java.hw12.jvdraw.objects.Line;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * Action that opens a .jvd file and presents it in 
 * as picture.
 * @author Vilim Staroveški
 *
 */
public class OpenAction extends AbstractAction {
	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**{@link JVDraw} on which this action is performed on.*/
	private JVDraw parent;
	/**
	 * Creates new {@link OpenAction}
	 * @param jvdraw {@link JVDraw} on which this action is performed on.
	 */
	public OpenAction(JVDraw jvdraw) {
		this.parent = jvdraw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(parent.getModified()) {
			
			int r = JOptionPane
					.showConfirmDialog(
							parent,
							"There are unsaved changes in this file, do you want to save changes?",
							"Warning", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
			if (r == JOptionPane.YES_OPTION) {
				parent.save();
			}
			else if (r == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
		
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open file");
		if (fc.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		Path filePath = fc.getSelectedFile().toPath();
		if (!Files.isReadable(filePath)) {
			JOptionPane.showMessageDialog(parent, "File " + filePath
					+ " does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		List<String> list = null;
		try {
			list = Files.readAllLines(filePath, StandardCharsets.UTF_8);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(parent, e1.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		ObjectsModel model = (ObjectsModel) parent.getDrawingModel();
		model.clearModel();
		for(String objectInString : list) {
			String[] parts = objectInString.split(" ");
			if(parts[0].equals("LINE")) {
				Line line = new Line(new Color(Integer.parseInt(parts[5]), 
						Integer.parseInt(parts[6]), 
						Integer.parseInt(parts[7])));
				line.setStartPoint(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
				line.setEndPoint(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
				model.add(line);
			}
			else if(parts[0].equals("CIRCLE")) {
				Circle circle = new Circle(new Color(Integer.parseInt(parts[4]), 
						Integer.parseInt(parts[5]), 
						Integer.parseInt(parts[6])));
				circle.setStartPoint(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
				circle.setEndPoint(circle.getStartPointX() + Integer.parseInt(parts[3]), circle.getStartPointY());
				model.add(circle);
			}
			else if(parts[0].equals("FCIRCLE")) {
				FilledCircle fcircle = new FilledCircle(
						new Color(Integer.parseInt(parts[4]),
								Integer.parseInt(parts[5]),
								Integer.parseInt(parts[6])), 
						new Color(Integer.parseInt(parts[7]),
								Integer.parseInt(parts[8]),
								Integer.parseInt(parts[9])));
				fcircle.setStartPoint(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
				fcircle.setEndPoint(fcircle.getStartPointX() + Integer.parseInt(parts[3]), fcircle.getStartPointY());
				model.add(fcircle);
			}
		}
	}
}
