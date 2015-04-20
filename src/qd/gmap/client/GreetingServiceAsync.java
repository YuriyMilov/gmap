package qd.gmap.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String s, AsyncCallback<String[]> callback);
	void get(String s, AsyncCallback<String> callback);
	void get_r4(String s, String s2, AsyncCallback<String[]> callback);
}
