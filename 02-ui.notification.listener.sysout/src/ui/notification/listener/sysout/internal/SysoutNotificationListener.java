package ui.notification.listener.sysout.internal;

import ui.notification.NotificationEvent;
import ui.notification.NotificationEventType;
import ui.notification.NotificationListener;

public class SysoutNotificationListener implements NotificationListener {

	public void onEvent(NotificationEvent event) {
		if (event.getType() == NotificationEventType.Show) {
			System.out.println(event.getNotification().getTitle() + ": " //$NON-NLS-1$
					+ event.getNotification().getMessage());
		}
	}
}
