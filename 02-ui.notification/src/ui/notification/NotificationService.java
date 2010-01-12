package ui.notification;

/**
 * Service for displaying notifications.
 * 
 * @author <a href="mailto:phil.kursawe@gmail.com">Philipp Kursawe</a>
 *
 */
public interface NotificationService {
	/**
	 * Shows a notification. 
	 * 
	 * <p>
	 * Re-arranges already visible notifications.
	 * 
	 * @param notification to show. Must not be <code>null</code>.
	 */
	void show(Notification notification);
}
