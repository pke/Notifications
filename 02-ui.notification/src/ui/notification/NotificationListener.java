package ui.notification;

import java.util.EventListener;

public interface NotificationListener extends EventListener {
	void onEvent(NotificationEvent event);
}
