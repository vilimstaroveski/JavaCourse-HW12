package hr.fer.zemris.java.hw12.jvdraw.list;

import hr.fer.zemris.java.hw12.jvdraw.objects.GeometricalObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Model that contains all present objecst on picture.
 * @author Vilim Staroveški
 *
 */
public class ObjectsModel implements DrawingModel {
	/**List of geometrical objects*/
	private List<GeometricalObject> objectsPresent;
	/**List of listeners of this model.*/
	private List<DrawingModelListener> listeners;
	/**
	 * Creates new {@link ObjectsModel}
	 */
	public ObjectsModel() {

		objectsPresent = new ArrayList<GeometricalObject>();
		listeners = new ArrayList<DrawingModelListener>();
	}
	
	@Override
	public int getSize() {
		return objectsPresent.size();
	}

	@Override
	public GeometricalObject getObject(int index) {
		return objectsPresent.get(index);
	}

	@Override
	public void add(GeometricalObject object) {
		
		int indexOfAdded = objectsPresent.size();
		objectsPresent.add(object);
		for(DrawingModelListener listener : listeners) {
			listener.objectsAdded(ObjectsModel.this, indexOfAdded, indexOfAdded);
		}
	}

	@Override
	public void addDrawingModelListener(DrawingModelListener l) {
		listeners.add(l);
	}

	@Override
	public void removeDrawingModelListener(DrawingModelListener l) {
		listeners.remove(l);
	}
	
	public void clearModel() {
		objectsPresent.clear();
	}
	
}
