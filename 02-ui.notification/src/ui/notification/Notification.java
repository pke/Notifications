package ui.notification;

import org.eclipse.jface.dialogs.IMessageProvider;

/**
 * Simple notification.
 * 
 * @author <a href="mailto:phil.kursawe@gmail.com">Philipp Kursawe</a>
 */
public interface Notification extends IMessageProvider {
	/**
	 * @return the title of this notification or <code>null</code> if none.
	 */
	String getTitle();
}
