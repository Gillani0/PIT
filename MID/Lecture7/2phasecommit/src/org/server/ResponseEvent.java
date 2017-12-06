package org.server;

import org.shared.Message;

public interface ResponseEvent {
	public void notify(Message message);
}
