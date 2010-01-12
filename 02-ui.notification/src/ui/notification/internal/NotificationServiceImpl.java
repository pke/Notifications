package ui.notification.internal;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Display;
import org.osgi.service.component.ComponentContext;

import ui.notification.Notification;
import ui.notification.NotificationEvent;
import ui.notification.NotificationEventType;
import ui.notification.NotificationListener;
import ui.notification.NotificationService;

/**
 * A simple implementation of the {@link NotificationService}.
 * 
 * <p>
 * It uses the NotifierDialog class from <a href="http://hexapixel.com/2009/06/30/creating-a-notification-popup-widget">Hexapixel blog</a>.
 * 
 * <p>
 * It also informs {@link NotificationListener} about the display of a notification using the {@link NotificationEventType#Show} type. 
 *  
 * @author <a href="mailto:phil.kursawe@gmail.com">Philipp Kursawe</a>
 * @author <a href="mailto:emil.crumhorn@gmail.com">Emil Crumhorn</a> (NotifierDialog)
 * @since 1.0.2
 */
public class NotificationServiceImpl implements NotificationService {

	private ComponentContext context;

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
	
	protected void activate(ComponentContext context) {
		this.context = context;
	}
	
	public void show(final Notification notification) {
		if (notification != null) {
			final NotificationType type;
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
			
			Object listeners[] = context.locateServices("NotificationListener"); //$NON-NLS-1$
			if (listeners != null) {
				NotificationEvent event = new NotificationEvent(this, notification, NotificationEventType.Show);
				for (Object listener : listeners) {
					try {
						((NotificationListener)listener).onEvent(event);
					} catch (Throwable e) {						
					}
				}
			}
			
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					NotifierDialog.notify(notification.getTitle(), notification.getMessage(), type);
				}
			});
		}
	}

}
