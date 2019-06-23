package hr.fer.zemris.java.hw12.jvdraw.actions;

import hr.fer.zemris.java.hw12.jvdraw.JVDraw;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JToggleButton;
/**
 * Action that selects mode for drawing circles in {@link JVDraw} program.
 * @author Vilim Staroveški
 *
 */
public class DrawCircleAction extends AbstractAction {
	
	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {

		JToggleButton button = (JToggleButton) e.getSource();
		button.setSelected(true);
	}

}
