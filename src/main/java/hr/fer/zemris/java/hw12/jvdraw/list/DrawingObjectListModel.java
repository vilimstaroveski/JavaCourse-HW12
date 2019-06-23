package hr.fer.zemris.java.hw12.jvdraw.list;

import hr.fer.zemris.java.hw12.jvdraw.objects.GeometricalObject;

import javax.swing.AbstractListModel;
/**
 * Adapter for model for list containing all objects present on picture.
 * @author Vilim Staroveški
 *
 */
public class DrawingObjectListModel extends AbstractListModel<GeometricalObject>  implements DrawingModelListener {

	
	/**Default serial version UID*/
	private static final long serialVersionUID = 1L;
	/**
	 * Model for list containing all objects present on picture.
	 */
	private DrawingModel dModel;
	/**
	 * Creates new {@link DrawingObjectListModel} 
	 * @param dModel model for which this {@link DrawingObjectListModel} will be adapter to
	 */
	public DrawingObjectListModel(DrawingModel dModel) {
		this.dModel = dModel;
	}
	
	@Override
	public int getSize() {
		return dModel.getSize();
	}

	@Override
	public GeometricalObject getElementAt(int index) {
		return dModel.getObject(index);
	}

	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		fireIntervalAdded(this, index0, index1);
	}

	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		fireIntervalRemoved(this, index0, index1);
	}

	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		fireContentsChanged(this, index0, index1);
	}

}
