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

public class del_marker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter(); 
		String s="";
		try{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Mrkr4> rd = (List<Mrkr4>) pm.newQuery("SELECT FROM " + Mrkr4.class.getName()+ " WHERE s4==\""+req.getParameter("id")  + "\" && s5==\""+req.getParameter("usr") +"\"").execute();
		s=String.valueOf(rd.size());
		for (int i = 0; i < rd.size(); i++)
		{
			Mrkr4 mr = pm.getObjectById(Mrkr4.class, rd.get(i).getId());
			pm.deletePersistent(mr);
		}
		}catch(Exception e){s=e.toString();}
		out.println(s);
	}
 
}