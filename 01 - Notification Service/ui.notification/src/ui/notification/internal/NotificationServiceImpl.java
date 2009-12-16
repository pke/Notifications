package ui.notification.internal;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Display;

import ui.notification.Notification;
import ui.notification.NotificationService;

/**
 * A simple implementation of the {@link NotificationService}.
 * 
 * <p>
 * It uses the NotifierDialog class from <a href="http://hexapixel.com/2009/06/30/creating-a-notification-popup-widget">Hexapixel blog</a>.
 * 
 * @author <a href="mailto:phil.kursawe@gmail.com">Philipp Kursawe</a>
 * @author <a href="mailto:emil.crumhorn@gmail.com">Emil Crumhorn</a> (NotifierDialog)
 */
public class NotificationServiceImpl implements NotificationService {

	public NotificationServiceImpl() {
		Runnable runnable = new Runnable() {
			public void run() {
				while (!Display.getDefault().isDisposed()) {
		            if (!Display.getDefault().readAndDispatch()) {
		            	Display.getDefault().sleep();
		            }
		        }
			}
		};
		new Thread(runnable, "SWT").start(); //$NON-NLS-1$
	}
	
	public void show(final Notification notification) {
		if (notification != null) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					NotificationType type;
					switch (notification.getMessageType()) {
					case IMessageProvider.WARNING:
						type = NotificationType.WARN;
						break;
					case IMessageProvider.ERROR:
						type = NotificationType.ERROR;
						break;
					default:
						type = NotificationType.INFO;
						break;
					}
					NotifierDialog.notify(notification.getTitle(), notification.getMessage(), type);
				}
			});
		}
	}

}
