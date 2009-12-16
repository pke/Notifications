package ui.notification.command.internal;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.service.component.ComponentContext;

import ui.notification.Notification;
import ui.notification.NotificationService;
import org.eclipse.jface.dialogs.IMessageProvider;

/**
 * An Equinox console command provider that provides the <code>notify</code> command.
 * 
 * <p>
 * It uses a static reference to the {@link NotificationService}.
 *  
 * @author <a href="mailto:phil.kursawe@gmail.com">Philipp Kursawe</a>
 */
public class NotifyCommand implements CommandProvider {

	NotificationService service;
	
	protected void activate(ComponentContext context) {
		service = (NotificationService) context.locateService("NotificationService"); //$NON-NLS-1$
	}
	
	public void _notify(CommandInterpreter ci) {
		final String message = ci.nextArgument();
		if (null == message) {
			ci.println("You need to specify a message"); //$NON-NLS-1$
			return;
		}
		String title = ci.nextArgument();
		if (title == null) {
			title = "Notification"; //$NON-NLS-1$
		}
		
		final String finalTitle = title;
		service.show(new Notification() {
			public String getTitle() {
				return finalTitle;
			}
			
			public String getMessage() {
				return message;
			}

			public int getMessageType() {
				return IMessageProvider.INFORMATION;
			}
		});
	}
	
	public String getHelp() {
		return "--- Notification ---\n\tnotify message [title]"; //$NON-NLS-1$
	}
}
