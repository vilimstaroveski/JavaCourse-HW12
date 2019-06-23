package hr.fer.zemris.java.hw12.jvdraw.list;
/**
 * Interface for objects that listens to {@link DrawingModel}
 * @author Vilim Staroveški
 *
 */
public interface DrawingModelListener {
	/**
	 * Notification fired when objects are added
	 * @param source component on which objects are added
	 * @param index0 index of first added object
	 * @param index1 index of last added object
	 */
	public void objectsAdded(DrawingModel source, int index0, int index1);
	/**
	 * Notification fired when objects are removed
	 * @param source component on which objects are removed
	 * @param index0 index of first removed object
	 * @param index1 index of last removed object
	 */
	public void objectsRemoved(DrawingModel source, int index0, int index1);
	/**
	 * Notification fired when something is changed
	 * @param source component on which objects are changed
	 * @param index0 index of first changed object
	 * @param index1 index of last changed object
	 */
	public void objectsChanged(DrawingModel source, int index0, int index1);
}
