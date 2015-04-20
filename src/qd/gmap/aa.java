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

public class aa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String s = "";
	public static String[] ss = null;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();

		// ///////////////////////////////////////////////////////////
		// s =
		// rfu("http://maps.google.com/maps/api/geocode/xml?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true");
		// ///////////////////////////////////////////////////////////
		if (ss != null)
			for (int i = 0; i < ss.length; i++)
				out.println(ss[i]);
		else
			out.println("\r\n----------- ss=null --------------");

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
		
		// s = req.getParameter("cons_addr") + "\r\n"
		// + req.getParameter("cons_lat") + " "
		// + req.getParameter("cons_lng") + "\r\n";
try{
		String str = req.getParameter("a");
		String[] words = str.split("\r\n");
		// ss=new String[words.length];
		if(words==null)
			ss = new String[]{"","","0.0","0.0",""};
		else
			ss = words;
		for (int i = 0; i < ss.length; i++)
			out.println(ss[i]);
	}
	catch(Exception eee){}

	}

}