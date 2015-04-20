package qd.gmap.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL; //import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import qd.gmap.client.GreetingService;
import qd.gmap.PMF;
import qd.gmap.Mrkr4;
import qd.gmap.a2;
import qd.gmap.a4;
import qd.gmap.aa;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();

	public String[] get_r4(String s1, String s2) {
				return get_LL(s2, s1 + " | " + s2);
	}

	public String get(String s) {
		// s=rfu("http://map.quicklydone.com/geo?"+s);
		return s;
	}

	@SuppressWarnings("unchecked")
	public String[] de2_mrkr(String s1, String s2) {

		try {
			String[] ss2 = { "70", "-88", "init" };
			return ss2;
			
		} catch (Exception e) {
			return new String[] { "44", "-70", e.toString()};
		}

	}

	@SuppressWarnings("unchecked")
	public String[] det_mrkr() {
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "SELECT FROM " + Mrkr4.class.getName()
					+ " WHERE s5 == \""
					+ getThreadLocalRequest().getUserPrincipal().getName()
					+ "\"";
			List<Mrkr4> results = (List<Mrkr4>) pm.newQuery(query).execute();
			int i = 0, n = 0, k = results.size();
			String[] ss = new String[k * 4];
			Mrkr4 mk = null;
			while (i < k) {
				mk = results.get(i++);
				ss[n++] = mk.get_s1();
				ss[n++] = mk.get_s2();
				ss[n++] = mk.get_s3();
				ss[n++] = mk.get_s6();
			}
			
			ss=new String[]{ "r", "50.0", "-60.0", "address","2", "text" };
		
			return ss;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unused")
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
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

	public String[] get_user(String input) {

		HttpServletRequest req = getThreadLocalRequest();
		String s[] = { "init" };

		UserService userService = UserServiceFactory.getUserService();

		User user = userService.getCurrentUser();
		if (user != null) {
			s[0] = "<br>&nbsp;&nbsp;" + req.getUserPrincipal().getName()
					+ " | <b><a href=\"" + userService.createLogoutURL("/")
					+ "\">Logout</a></b><br><br>";
		} else {
			s[0] = "<center><br><br><br><br><br><b><a href=\""
					+ userService.createLoginURL("/")
					+ "\">Sign In</a></b></crnter>";
		}

		// if (input.equals("user"))
		// s=user.getNickname();
		return s;
	}

	public String[] greetServer(String input) {
		String s[] = { "", "", "", "" };

		if (input.equals("aa"))
			s = aa.ss;
		if (input.equals("a2"))
			s = a2.s2;

		if (input.equals("a4"))
		{
			String sxml=rfu("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.atom");
			s = new String[]{"55","-74",sxml};//det_mrkr();
			
		}
			

		if (input.equals("login"))
			s = new String[]{"Logout"};//get_user("");
		return s;
	}

	public String[] get_LL(String s, String s2) {
		String ss[] = { "70", "-88", s2 };
		s = s.replace(' ', '+');
		try {
			s = rfu("http://maps.google.com/maps/api/geocode/xml?address=" + s
					+ "&sensor=true");
			if (s.indexOf("</location>") > s.indexOf("<location>")) {
				s = s.substring(s.indexOf("<location>") + 10,
						s.indexOf("</location>"));
				if (s.indexOf("</lat>") > s.indexOf("<lat>")
						&& s.indexOf("</lng>") > s.indexOf("<lng>")) {
					ss[0] = s.substring(s.indexOf("<lat>") + 5,
							s.indexOf("</lat>"));
					ss[1] = s.substring(s.indexOf("<lng>") + 5,
							s.indexOf("</lng>"));
				}
			}

		} catch (Exception e) {
			ss = new String[] { "70", "-88", e.toString() };
		}

		return ss;
	}
}
