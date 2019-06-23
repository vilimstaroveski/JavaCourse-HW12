package hr.fer.zemris.java.hw12.jvdraw;

import hr.fer.zemris.java.hw12.jvdraw.actions.DrawCircleAction;
import hr.fer.zemris.java.hw12.jvdraw.actions.DrawFCircleAction;
import hr.fer.zemris.java.hw12.jvdraw.actions.DrawLineAction;
import hr.fer.zemris.java.hw12.jvdraw.actions.ExitAction;
import hr.fer.zemris.java.hw12.jvdraw.actions.ExportAction;
import hr.fer.zemris.java.hw12.jvdraw.actions.OpenAction;
import hr.fer.zemris.java.hw12.jvdraw.actions.SaveAction;
import hr.fer.zemris.java.hw12.jvdraw.actions.SaveAsAction;
import hr.fer.zemris.java.hw12.jvdraw.canvas.JDrawingCanvas;
import hr.fer.zemris.java.hw12.jvdraw.colorarea.JColorArea;
import hr.fer.zemris.java.hw12.jvdraw.colorarea.StatusLabel;
import hr.fer.zemris.java.hw12.jvdraw.dialogs.CircleEditDialog;
import hr.fer.zemris.java.hw12.jvdraw.dialogs.FilledCircleEditDialog;
import hr.fer.zemris.java.hw12.jvdraw.dialogs.LineEditDialog;
import hr.fer.zemris.java.hw12.jvdraw.list.DrawingModel;
import hr.fer.zemris.java.hw12.jvdraw.list.DrawingObjectListModel;
import hr.fer.zemris.java.hw12.jvdraw.list.ObjectsModel;
import hr.fer.zemris.java.hw12.jvdraw.objects.Circle;
import hr.fer.zemris.java.hw12.jvdraw.objects.FilledCircle;
import hr.fer.zemris.java.hw12.jvdraw.objects.GeometricalObject;
import hr.fer.zemris.java.hw12.jvdraw.objects.Line;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
/**
 * Program that allows user to draw lines circles and filled circles 
 * on white surface. Program supports actions such as Open, Save, Save as and 
 * Export. When object is drawn, user can redefine its attributes.
 * @author Vilim Staroveški
 *
 */
public class JVDraw extends JFrame {
	
	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**Action for opening a .jvd picture in window*/
	private OpenAction openDocument;
	/**Action for saving current picture in .jvd format.*/
	private SaveAction saveDocument;
	/**Action for saving picture as*/
	private SaveAsAction saveAsDocument;
	/**Action for exiting the program.*/
	private ExitAction exitProgram;
	/**Action for choosing mode for drawing lines.*/
	private DrawLineAction drawLine;
	/**Action for choosing mode for drawing circles.*/
	private DrawCircleAction drawCircle;
	/**Action for choosing mode for drawing filled circles.*/
	private DrawFCircleAction drawFCircle;
	/**Action for exporting current picture in format .jpg, .pgn or .gif*/
	private ExportAction export;
	/**Button group for buttons defining program draw modes.*/
	private ButtonGroup drawButtons;
	/**Component on which picture is rendered.*/
	private JDrawingCanvas canvas;
	/**List of objects that are on the picture*/
	private JList<GeometricalObject> listOfObjects;
	/**Model for list containing all objects.*/
	private ObjectsModel drawingModel;
	/**Foreground color of objects*/
	private JColorArea foreground;
	/**Background color of objects.*/
	private JColorArea background;
	/**Flag that tells wheater picture has unsaved modifications.*/
	private boolean modified;
	/**Path where current picture is located.*/
	private Path pathToCurrentFile;
	/**
	 * Creates new {@link JVDraw} frame.
	 */
	public JVDraw() {
		
		setTitle("JVDraw");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocation(200, 200);
		setSize(800, 400);
		modified = false;
		pathToCurrentFile = null;
		
		initGUI();
		initListeners();
	}
	/**
	 * Initializes listeners on program window.
	 */
	private void initListeners() {

		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
            	
            	exitProgram.actionPerformed(null);
            }
        });
	}
	/**
	 * Initializes GUI of this program.
	 */
	private void initGUI() {
		
		getContentPane().setLayout(new BorderLayout());
		
		drawButtons = new ButtonGroup();
		
		drawingModel = new ObjectsModel();
		DrawingObjectListModel objectList = new DrawingObjectListModel(drawingModel);
		listOfObjects = new JList<GeometricalObject>(objectList);
		listOfObjects.setBorder(BorderFactory.createRaisedBevelBorder());
		listOfObjects.setBackground(Color.LIGHT_GRAY);
		drawingModel.addDrawingModelListener(objectList);
		listOfObjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(e.getClickCount() != 2) {
					return;
				}
				int selectedIndex = listOfObjects.getSelectedIndex();
				
				GeometricalObject object = drawingModel.getObject(selectedIndex);
				if(object instanceof Line) {
					LineEditDialog dialog = new LineEditDialog((Line)object);
					int result = JOptionPane.showConfirmDialog(JVDraw.this, 
							dialog, 
							"Edit line", 
							JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.PLAIN_MESSAGE
							);
					if(result == JOptionPane.OK_OPTION) {
						dialog.updateObject();
						canvas.repaint();
						modified = true;
					}
				}
				else if(object instanceof FilledCircle) {
					FilledCircleEditDialog dialog = new FilledCircleEditDialog((FilledCircle)object);
					int result = JOptionPane.showConfirmDialog(JVDraw.this, 
							dialog, 
							"Edit filled circle", 
							JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.PLAIN_MESSAGE
							);
					if(result == JOptionPane.OK_OPTION) {
						dialog.updateObject();
						canvas.repaint();
						modified = true;
					}
				}
				else if(object instanceof Circle) {
					CircleEditDialog dialog = new CircleEditDialog((Circle)object);
					int result = JOptionPane.showConfirmDialog(JVDraw.this, 
							dialog, 
							"Edit circle", 
							JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.PLAIN_MESSAGE
							);
					if(result == JOptionPane.OK_OPTION) {
						dialog.updateObject();
						canvas.repaint();
						modified = true;
					}
				}
				
			}
		});
		createActions();
        createMenus();
        createToolBar();
        
        canvas = new JDrawingCanvas(JVDraw.this);
		canvas.setBorder(BorderFactory.createLoweredBevelBorder());
		drawingModel.addDrawingModelListener(canvas);
		
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(listOfObjects, BorderLayout.EAST);
		
	}
	/**
	 * Creates actions supported by this program
	 */
	private void createActions() {

		openDocument = new OpenAction(this);
		modifyAction(openDocument, "Open", "control O", 
				KeyEvent.VK_O, "Used to open existing picture from disk.", 
				new ImageIcon("icons/open.png"), new ImageIcon());
		
		saveDocument = new SaveAction(this);
		modifyAction(saveDocument, "Save", "control S", 
				KeyEvent.VK_S, "Saves this picture.",
				new ImageIcon("icons/save.png"), new ImageIcon());

		saveAsDocument = new SaveAsAction(this);
		modifyAction(saveAsDocument, "Save As", "", 
				-1, "Saves this picture as...", 
				new ImageIcon("icons/saveAs.png"), new ImageIcon());
		
		exitProgram = new ExitAction(this);
		modifyAction(exitProgram, "Exit", "control W", 
				KeyEvent.VK_E, "Exit program", 
				new ImageIcon("icons/exit.png"), new ImageIcon());
		
		drawLine = new DrawLineAction();
		modifyAction(drawLine, "", "", 
				KeyEvent.VK_L, "Draws a line", 
				new ImageIcon("icons/line.png"), new ImageIcon("icons/line.png"));
	
		drawCircle = new DrawCircleAction();
		modifyAction(drawCircle, "", "", 
				KeyEvent.VK_C, "Draws a unfilled circle.", 
				new ImageIcon("icons/circle.png"), new ImageIcon("icons/circle.png"));
		
		drawFCircle = new DrawFCircleAction();
		modifyAction(drawFCircle, "", "", 
				KeyEvent.VK_F, "Draws a filled circle.", 
				new ImageIcon("icons/fcircle.png"), new ImageIcon("icons/fcircle.png"));
		
		export = new ExportAction(this);
		modifyAction(export, "Export", "", 
				-1, "Exports image", 
				new ImageIcon("icons/export.png"), new ImageIcon());
	}
	/**
	 * Modifies actions so they are user friendly.
	 * @param action what action does
	 * @param name name of action
	 * @param accelerator accelerator key
	 * @param mnemonic mnemonic key
	 * @param description short description 
	 * @param smallIcon small icon for action in menu
	 * @param largeIcon large icon for action in toolbar
	 */
	private void modifyAction(AbstractAction action, String name,
			String accelerator, int mnemonic, String description, ImageIcon smallIcon, 
			ImageIcon largeIcon) {
		
		if(name.isEmpty()) {
			action.putValue(Action.LARGE_ICON_KEY, largeIcon);
		}
		else {
			action.putValue(Action.NAME, name);
		}
		
		if(!accelerator.isEmpty()) {
			action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
		}
		if(mnemonic != -1) {
			action.putValue(Action.MNEMONIC_KEY, mnemonic);
		}
		action.putValue(Action.SHORT_DESCRIPTION, description);
		action.putValue(Action.SMALL_ICON, smallIcon);
		
	}
	/**
	 * Creates and initializes toolbar of this program.
	 */
	private void createToolBar() {
		
		JToolBar toolBar = new JToolBar("Tools");
		toolBar.setFloatable(true);
		
		//add 2 color area
		foreground = new JColorArea(Color.RED); 
		background = new JColorArea(Color.BLUE);
		foreground.setToolTipText("Select foreground color.");
		background.setToolTipText("Select background color.");
		toolBar.add(foreground);
		toolBar.add(background);
		
		toolBar.addSeparator();
		
		addToolBarButtons(toolBar);
		
		StatusLabel stats = new StatusLabel(background, foreground);
		foreground.addColorChangeListener(stats);
		background.addColorChangeListener(stats);
		getContentPane().add(stats, BorderLayout.SOUTH);
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
	}
	/**
	 * Adds toolbar buttons with actions.
	 * @param toolBar toolbar of this program.
	 */
	private void addToolBarButtons(JToolBar toolBar) {

		JToggleButton lineButton = new JToggleButton(drawLine);
		JToggleButton circleButton = new JToggleButton(drawCircle);
		JToggleButton fcircleButton = new JToggleButton(drawFCircle);

		lineButton.setActionCommand("LINE");
		circleButton.setActionCommand("CIRCLE");
		fcircleButton.setActionCommand("FCIRCLE");
		
		drawButtons.add(lineButton);
		drawButtons.add(circleButton);
		drawButtons.add(fcircleButton);
		
		drawButtons.setSelected(lineButton.getModel(), true);
		
		toolBar.add(lineButton);
		toolBar.add(circleButton);
		toolBar.add(fcircleButton);
	}
	/**
	 * Creates menus for this program.
	 */
	private void createMenus() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		fileMenu.add(openDocument);
		fileMenu.add(saveDocument);
		fileMenu.add(saveAsDocument);
		fileMenu.add(export);
		fileMenu.add(exitProgram);

		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}
	/**
	 * Returns this programs draw button group
	 * @return this programs draw button group
	 */
	public ButtonGroup getDrawButtons() {
		return drawButtons;
	}
	/**
	 * Returns this programs model for list with objects
	 * @return this programs model for list with objects
	 */
	public DrawingModel getDrawingModel() {
		return drawingModel;
	}
	/**
	 * Returns this programs object foreground color
	 * @return this programs object foreground color
	 */
	public JColorArea getForeGround() {
		return foreground;
	}
	/**
	 * Returns this programs object background color
	 * @return this programs object background color
	 */
	public JColorArea getBackGround() {
		return background;
	}
	/**
	 * Returns this programs modification flag
	 * @return this programs modification flag
	 */
	public boolean getModified() {
		return modified;
	}
	/**
	 * Sets this program modification flag.
	 * @param modified new state of flag
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	/**
	 * Saves current picture on disk
	 */
	public void save() {
		saveDocument.actionPerformed(null);
	}
	/**
	 * Sets path of current picture
	 * @param path path of current picture
	 */
	public void setPathToCurrentFile(Path path) {
		pathToCurrentFile = path;
	}
	/**
	 * Returns current picture path on disk
	 * @return current picture path on disk
	 */
	public Path getPathToCurrentFile() {
		return pathToCurrentFile;
	}
	/**
	 * Sets up the program
	 */
	private static void appSetUp() {
		
		try {
			
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
        } catch (Exception ignorable) {
        }
		JFrame jvdraw = new JVDraw();
		jvdraw.setVisible(true);
	}
	/**
	 * Method called on program start.
	 * @param args command line arguments, not used in this program
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater( () -> {
			appSetUp();
		});
	}

}
