package ui.notification;

import java.util.EventObject;

public class NotificationEvent extends EventObject {
	private static final long serialVersionUID = -8633958140848611658L;

	private Notification notification;

	private NotificationEventType type;

	public NotificationEvent(NotificationService source, Notification notification, NotificationEventType type) {
		super(source);
		this.notification = notification;
		this.type = type;
	}

	public Notification getNotification() {
		return notification;
	}
	
	public NotificationEventType getType() {
		return type;
	}
}
