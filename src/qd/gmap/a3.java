package qd.gmap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import javax.jdo.PersistenceManager;
import qd.gmap.Mrkr;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.List;

public class a3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String s = "";
	public static String[] s2 = null;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();

		// ///////////////////////////////////////////////////////////
		//s = rfu("http://maps.google.com/maps/api/geocode/xml?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true");
		// ///////////////////////////////////////////////////////////
		if(s2!=null)
		for (int i=0; i < s2.length; i++)
			out.println (s2[i]);
		else
			out.println("\r\n----------- ok --------------");

	}

	public static String rfu(String url) {
		StringBuffer s = new StringBuffer();
		try {
			URL u = new URL(url);
			InputStream in = u.openConnection().getInputStream();
			for (int ch = in.read(); ch > 0; ch = in.read()) {
				s.append((char) ch);
			}
			in.close();
		} catch (IOException e) {
			return e.toString();
		}
		return s.toString();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		
		String scn="init";
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
				String thisURL = req.getRequestURI();
				if ( user != null) {
					scn="scn = "+req.getUserPrincipal().getName();
				} else {
					user=new User("test@quicklydone.com",
						"quicklydone.com","test");
				}
		String str =req.getParameter("b");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Mrkr ns = new Mrkr(user, new Date(),
				str, null, 
				null, null, 
				null, null, 
				null, null, 
				null, null);
		
		pm.makePersistent(ns);

				out.println (scn);
	}

}