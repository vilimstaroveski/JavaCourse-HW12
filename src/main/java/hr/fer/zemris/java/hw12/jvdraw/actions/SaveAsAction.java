package hr.fer.zemris.java.hw12.jvdraw.actions;

import hr.fer.zemris.java.hw12.jvdraw.JVDraw;
import hr.fer.zemris.java.hw12.jvdraw.objects.GeometricalObject;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * Action that saves current picture as in .jvd format
 * @author Vilim Staroveški
 *
 */
public class SaveAsAction extends AbstractAction {

	/**Default serial version UID. */
	private static final long serialVersionUID = 1L;
	/**{@link JVDraw} in which this action is performed in*/
	private JVDraw parent;
	/**
	 * Creates new {@link SaveAsAction}
	 * @param jvDraw {@link JVDraw} in which this action is performed in
	 */
	public SaveAsAction(JVDraw jvDraw) {
		parent = jvDraw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save document"); 
		if(fc.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(parent, 
					"Nothing has been saved!", 
					"System messege", 
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Path file = fc.getSelectedFile().toPath();
		if(!file.toString().endsWith("jvd")) {
			file = Paths.get(file.toString()+".jvd");
		}
		if(Files.exists(file)) {
			int r = JOptionPane.showConfirmDialog(parent, 
					"File "+file+" already exist. Do you want to overwrite it?", 
					"Warning", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(r != JOptionPane.YES_OPTION) {
				return;
			}
		}
		parent.setPathToCurrentFile(file);
		
		String modelText = "";
		for(int i = 0; i < parent.getDrawingModel().getSize(); i++) {
			GeometricalObject object = parent.getDrawingModel().getObject(i);
			modelText += object.toString()+"\r\n";
		}
		
		try {
			Files.write(parent.getPathToCurrentFile(), modelText.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(parent, 
					e1.getMessage(),
					"Error", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		parent.setModified(false);
	}

}
