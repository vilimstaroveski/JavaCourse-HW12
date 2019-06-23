package hr.fer.zemris.java.hw12.jvdraw.canvas;

import hr.fer.zemris.java.hw12.jvdraw.JVDraw;
import hr.fer.zemris.java.hw12.jvdraw.colorarea.JColorArea;
import hr.fer.zemris.java.hw12.jvdraw.list.DrawingModel;
import hr.fer.zemris.java.hw12.jvdraw.list.DrawingModelListener;
import hr.fer.zemris.java.hw12.jvdraw.objects.Circle;
import hr.fer.zemris.java.hw12.jvdraw.objects.FilledCircle;
import hr.fer.zemris.java.hw12.jvdraw.objects.GeometricalObject;
import hr.fer.zemris.java.hw12.jvdraw.objects.Line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
/**
 * Component on which objects will be renderd on.
 * @author Vilim Staroveški
 *
 */
public class JDrawingCanvas extends JComponent implements DrawingModelListener {
	
	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**Model containing all objects present on picture*/
	private DrawingModel model;
	/**Currently drawn object.*/
	private GeometricalObject currentlyDrawing;
	/**Group of draw buttons*/
	private ButtonGroup group;
	/**Foreground color of objects*/
	private JColorArea foreground;
	/**Background color of objects*/
	private JColorArea background;
	/**{@link JVDraw} in which this canvas is located*/
	private JVDraw parent;
	/**
	 * Creates new {@link JDrawingCanvas}.
	 * @param parent {@link JVDraw} in which this canvas is located
	 */
	public JDrawingCanvas(JVDraw parent) {
		this.model = parent.getDrawingModel();
		this.group = parent.getDrawButtons();
		this.foreground = parent.getForeGround();
		this.background = parent.getBackGround();
		this.parent = parent;

		currentlyDrawing = null;
		MyMouseAdapter mouseListener = new MyMouseAdapter();
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
	}

	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {

		parent.setModified(true);
		repaint();
	}

	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		
		parent.setModified(true);
		repaint();
	}

	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		
		parent.setModified(true);
		repaint();
	}

	/**
	 * Private class which defines mouse actions on this canvas.
	 * @author Vilim Staroveški
	 *
	 */
	private class MyMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);
			if(currentlyDrawing != null) {
				currentlyDrawing.setEndPoint(e.getPoint().x, e.getPoint().y);
				repaint();
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if(currentlyDrawing == null) {
				if(group.getSelection().getActionCommand().equals("LINE")) {
					currentlyDrawing = new Line(foreground.getCurrentColor());
				}
				else if(group.getSelection().getActionCommand().equals("CIRCLE")) {
					currentlyDrawing = new Circle(foreground.getCurrentColor());
				}
				else if(group.getSelection().getActionCommand().equals("FCIRCLE")) {
					currentlyDrawing = new FilledCircle(foreground.getCurrentColor(), background.getCurrentColor());
				}
				currentlyDrawing.setStartPoint(e.getPoint().x, e.getPoint().y);
			}
			else {
				currentlyDrawing.setEndPoint(e.getPoint().x, e.getPoint().y);
				model.add(currentlyDrawing);
				currentlyDrawing = null;
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, JDrawingCanvas.this.getWidth(), JDrawingCanvas.this.getHeight());
		
		if(currentlyDrawing != null) {
			if(currentlyDrawing instanceof Line) {
				
				g2d.setColor(currentlyDrawing.getOutline());
				g2d.drawLine(currentlyDrawing.getStartPointX(), 
						currentlyDrawing.getStartPointY(), 
						currentlyDrawing.getEndPointX(), 
						currentlyDrawing.getEndPointY());
			}
			if(currentlyDrawing instanceof Circle) {
				
				int diammeter = 2 * ( (Circle)currentlyDrawing).getRadius();
				g2d.setColor(currentlyDrawing.getOutline());
				g2d.drawOval(currentlyDrawing.getStartPointX()-((Circle)currentlyDrawing).getRadius(), 
						currentlyDrawing.getStartPointY()-((Circle)currentlyDrawing).getRadius(), 
						diammeter, 
						diammeter);
				if(currentlyDrawing instanceof FilledCircle) {
					g2d.setColor( ((FilledCircle)currentlyDrawing).getInside());
					g2d.fillOval(currentlyDrawing.getStartPointX()-((Circle)currentlyDrawing).getRadius(), 
						currentlyDrawing.getStartPointY()-((Circle)currentlyDrawing).getRadius(),
						diammeter, 
						diammeter);
				}
			}
		}
		
		for(int i = 0; i < model.getSize(); i++) {
			GeometricalObject object = model.getObject(i);
			if(object instanceof Line) {
				
				g2d.setColor(object.getOutline());
				g2d.drawLine(object.getStartPointX(), 
						object.getStartPointY(), 
						object.getEndPointX(), 
						object.getEndPointY());
			}
			if(object instanceof Circle) {
				
				int diammeter = 2 * ( (Circle)object).getRadius();
				g2d.setColor(object.getOutline());
				g2d.drawOval(object.getStartPointX()-((Circle)object).getRadius(), 
						object.getStartPointY()-((Circle)object).getRadius(),
						diammeter, 
						diammeter);
				if(object instanceof FilledCircle) {
					g2d.setColor( ((FilledCircle)object).getInside());
					g2d.fillOval(object.getStartPointX()-((Circle)object).getRadius(), 
						object.getStartPointY()-((Circle)object).getRadius(),
						diammeter, 
						diammeter);
				}
			}
		}
	}
}
