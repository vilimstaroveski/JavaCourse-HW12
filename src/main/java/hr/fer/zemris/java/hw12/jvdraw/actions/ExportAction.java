package hr.fer.zemris.java.hw12.jvdraw.actions;

import hr.fer.zemris.java.hw12.jvdraw.JVDraw;
import hr.fer.zemris.java.hw12.jvdraw.list.DrawingModel;
import hr.fer.zemris.java.hw12.jvdraw.objects.Circle;
import hr.fer.zemris.java.hw12.jvdraw.objects.FilledCircle;
import hr.fer.zemris.java.hw12.jvdraw.objects.GeometricalObject;
import hr.fer.zemris.java.hw12.jvdraw.objects.Line;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * Action that exports current picture in {@link JVDraw} program.
 * @author Vilim Staroveški
 *
 */
public class ExportAction extends AbstractAction {

	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**List model containing all objects*/
	private DrawingModel model;
	/**{@link JVDraw} in which this action is performed in*/
	private JVDraw parent;
	/**
	 * Creates new {@link ExportAction}
	 * @param jvDraw {@link JVDraw} in which this action is performed in
	 */
	public ExportAction(JVDraw jvDraw) {
		model = jvDraw.getDrawingModel();
		parent = jvDraw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object[] possibilities = {"jpg", "png", "gif"};
		String s = (String)JOptionPane.showInputDialog(
		                    parent,
		                    "In which file type you want to export image?",
		                    "Export type",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "jpg");

		if ((s == null) || (s.length() == 0)) {
			return;
		}
		String fileType = s;
		
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Export"); 
		if(fc.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(parent, 
					"Nothing has been exported!", 
					"System messege", 
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		File exportFile = fc.getSelectedFile();
		if(Files.exists(exportFile.toPath())) {
			int r = JOptionPane.showConfirmDialog(parent, 
					"File "+exportFile.toPath()+" already exist. Do you want to overwrite it?", 
					"Warning", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(r != JOptionPane.YES_OPTION) {
				return;
			}
		}
		if(!exportFile.toString().endsWith(fileType)) {
			exportFile = new File(exportFile.getPath()+"."+fileType);
		}

		int eastest = 0;
		int westest = 10000;
		int northest = 10000;
		int southest = 0;
		for(int i = 0; i < model.getSize(); i++) {
			
			GeometricalObject object = model.getObject(i);
			
			Point upLeft = object.getUpperLeftCorner();
			Point downRight = object.getDownRightCorner();
			
			northest = northest > upLeft.y ? upLeft.y : northest;
			southest = southest < downRight.y ? downRight.y : southest;
			
			westest = westest > upLeft.x ? upLeft.x : westest;
			eastest = eastest < downRight.x ? downRight.x : eastest;
		}
		
		BufferedImage image = new BufferedImage(eastest-westest, southest-northest, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, eastest-westest, southest-northest);
		for(int i = 0; i < model.getSize(); i++) {
			
			GeometricalObject object = model.getObject(i);
			
			if(object instanceof Line) {
				
				g.setColor(object.getOutline());
				g.drawLine(object.getStartPointX()-westest, 
						object.getStartPointY()-northest, 
						object.getEndPointX()-westest, 
						object.getEndPointY()-northest);
			}
			if(object instanceof Circle) {
				
				int diammeter = 2 * ( (Circle)object).getRadius();
				g.setColor(object.getOutline());
				g.drawOval(object.getStartPointX()-((Circle)object).getRadius()-westest, 
						object.getStartPointY()-((Circle)object).getRadius()-northest,
						diammeter, 
						diammeter);
				if(object instanceof FilledCircle) {
					g.setColor( ((FilledCircle)object).getInside());
					g.fillOval(object.getStartPointX()-((Circle)object).getRadius()-westest, 
						object.getStartPointY()-((Circle)object).getRadius()-northest,
						diammeter, 
						diammeter);
				}
			}
		}
		g.dispose();
		try {
			ImageIO.write(image, fileType, exportFile);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(parent, "An error occured while exporting file!", 
					"Export error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(parent, "Image has been exported!", 
				"Export information", JOptionPane.INFORMATION_MESSAGE);
	}

}
