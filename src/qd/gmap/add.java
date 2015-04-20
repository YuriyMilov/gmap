package qd.gmap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;


public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		String s = "<html><a href=\"http://map.quicklydone.com\" >Get map</a></html>";
		try {
			String usr = req.getParameter("usr");
			String clr = req.getParameter("clr");
			String lat = req.getParameter("lat");
			String lng = req.getParameter("lng");
			String id = req.getParameter("id");
			String msg = req.getParameter("msg");
			User user = null;
			if (id.indexOf("@") > -1)
				user = new User(id, id.substring(id.indexOf("@") + 1));
			else
				user = new User("test@quicklydone.com", "quicklydone.com");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Mrkr4 ns = new Mrkr4(new Date(), clr, lat, lng, id, usr, msg);
			pm.makePersistent(ns);
		} catch (Exception e) {
			s = e.toString();
		}
		out.println(s);
	}

}