package hr.fer.zemris.java.hw12.jvdraw.actions;

import hr.fer.zemris.java.hw12.jvdraw.JVDraw;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
/**
 * Action that exites the {@link JVDraw} program.
 * @author Vilim Staroveški
 *
 */
public class ExitAction extends AbstractAction {

	/** Default serial version UID. */
	private static final long serialVersionUID = 1L;
	/**{@link JVDraw} in which this action is performed*/
	private JVDraw parent;
	/**
	 * Creates new {@link ExitAction}
	 * @param jvDraw {@link JVDraw} in which this action is performed
	 */
	public ExitAction(JVDraw jvDraw) {
		parent = jvDraw;
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
			parent.dispose();
		}
		else {

			parent.dispose();
		}
	}

}
