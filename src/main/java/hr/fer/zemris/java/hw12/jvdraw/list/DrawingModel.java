package hr.fer.zemris.java.hw12.jvdraw.list;

import hr.fer.zemris.java.hw12.jvdraw.objects.GeometricalObject;
/**
 * Interface for list model that shows all present objects in picture
 * @author Vilim Staroveški
 *
 */
public interface DrawingModel {
	/**
	 * Returns number of contained objects
	 * @return number of contained objects
	 */
	public int getSize();
	/**
	 * Returns object at index index
	 * @param index index on which object is located in list
	 * @return  object at index index
	 */
	public GeometricalObject getObject(int index);
	/**
	 * Adds {@link GeometricalObject} to this model
	 * @param object {@link GeometricalObject} that is going to be added
	 */
	public void add(GeometricalObject object);
	/**
	 * Adds {@link DrawingModelListener} on this model
	 * @param l {@link DrawingModelListener} that is going to listen this object
	 */
	public void addDrawingModelListener(DrawingModelListener l);
	/**
	 * Removes {@link DrawingModelListener} on this model
	 * @param l {@link DrawingModelListener} that is listening this object
	 */
	public void removeDrawingModelListener(DrawingModelListener l);
}
