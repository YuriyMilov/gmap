package qd.gmap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import javax.jdo.PersistenceManager;

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

public class a4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		String s = "<html><FORM ACTION=http://map.quicklydone.com/a4 METHOD=POST><TEXTAREA NAME=a COLS=40 ROWS=6>r\r\n45\r\n-77\r\nqq\r\n1\r\nqqqqqq\r\ng\r\n40\r\n-80\r\nzz\r\n1\r\nzzzzzzz</TEXTAREA><P><INPUT TYPE=SUBMIT VALUE=submit></FORM></html>";
		out.println(s);
	}
 
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//UserService userService = UserServiceFactory.getUserService();
		//
		//User user = userService.getCurrentUser();
				//if ( user == null) {
				//	user=new User("test@quicklydone.com",
				//		"quicklydone.com","test");
				//}
			//
				
				
		PrintWriter out = resp.getWriter();
		int t = 9999;
		String s="ok";
		try {
			String str = req.getParameter("a");
			String id = req.getParameter("id");
			
			//user=new User(id, id.substring(id.indexOf("@")+1));
			
			
			String[] words = str.split("\r\n");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			
			
			if (words == null)
				s4 = new String[] { "r", "50.0", "-60.0", "address","2", "text" };
			else
				s4 = words;
			
				List<Mrkr4> rd = (List<Mrkr4>) pm.newQuery("SELECT FROM " + Mrkr4.class.getName()+ " WHERE s5==\""+id+"\"").execute();
				for (int i = 0; i < rd.size(); i++)
				{
					Mrkr4 mr = pm.getObjectById(Mrkr4.class, rd.get(i).getId());
					pm.deletePersistent(mr);
				}
				for (int i = 0; i < s4.length; i = i + 6) {
				Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i + 1],
						s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5]);
				pm.makePersistent(ns);
				}
				
				/*
			
			for (int i = 0; i < s4.length; i = i + 6) {
				String query = "SELECT FROM " + Mrkr4.class.getName()
						+ " WHERE s2 == \"" + s4[i + 1] + "\" && s3 == \""
						+ s4[i + 2] + "\"";
				List<Mrkr4> rr = (List<Mrkr4>) pm.newQuery(query).execute();
				t = rr.size();
				if (rr.size() == 0) {
					Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i + 1],
							s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5], user);
					pm.makePersistent(ns);
				} else {

					Mrkr4 ns = pm.getObjectById(Mrkr4.class, rr.get(0).getId());
					pm.deletePersistent(ns);

					ns = new Mrkr4(new Date(), s4[i], s4[i + 1], s4[i + 2],
							s4[i + 3], s4[i + 4], s4[i + 5], user);
					pm.makePersistent(ns);
				}
				
				
			}
				*/
			
			
			
		} catch (Exception eee) {
			s=eee.toString();
		}
		//resp.sendRedirect("http://map.quicklydone.com");
		 out.println(s);
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
}