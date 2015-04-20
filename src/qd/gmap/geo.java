package qd.gmap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;

public class geo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		String s = "";
		try {
			s = req.getQueryString();
			 s = rfu("http://maps.google.com/maps/api/geocode/xml?address=" + s	+ "&sensor=true");

			 
			if (s.indexOf("</location>") > s.indexOf("<location>")) {
				s = s.substring(s.indexOf("<location>") + 10, s
						.indexOf("</location>"));
				if (s.indexOf("</lat>") > s.indexOf("<lat>")
						&& s.indexOf("</lng>") > s.indexOf("<lng>"))
					s = s.substring(s.indexOf("<lat>") + 5, s
							.indexOf("</lat>"))
							+ ";"
							+ s.substring(s.indexOf("<lng>") + 5, s
									.indexOf("</lng>"));
			} else
				s = "no LOCATION tag";
				

		} catch (Exception e) {
			s = e.toString();
		}
		
		// Gala privet 4
		
		out.print(s);
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