package qd.gmap.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Gmap implements EntryPoint {
	int i = 0;
	double d1 = 0, d2 = 0;
	public static String ch = "";
	boolean red = true;
	private final GreetingServiceAsync srv = GWT.create(GreetingService.class);
	MapWidget map;
	LatLng place = LatLng.newInstance(30, 10);
	HTML info;

	final VerticalPanel vp = new VerticalPanel();
	final VerticalPanel vpm = new VerticalPanel();
	// final ListBox km = new ListBox(false);
	final ArrayList<Marker> ar = new ArrayList<Marker>();;
	final FlexTable layout = new FlexTable();
	final TextBox tbox1 = new TextBox();
	final Geocoder geo = new Geocoder();
	final Button but1 = new Button("Кляк");

	// final Button sendButton = new Button("Send");

	public void onModuleLoad() {
		// Add a handler
		MyHandler handler = new MyHandler();
		but1.addClickHandler(handler);
		tbox1.addKeyUpHandler(handler);

		map = new MapWidget(place, 2);
		map.setSize("900px", "600px");
		// map.setSize("1200px", "800px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl());
		// km.addItem("10 km");
		// km.addItem("20 km");
		// km.addItem("50 km");
		// km.addItem("100 km");
		// km.addItem("200 km");
		// km.addItem("all");

		login_plus();
	}

	private void login_plus() {

		srv.greetServer("login", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new HTML("где-где???"));
			}

			public void onSuccess(final String r[]) {
				// if (r[0].indexOf("Logout") > -1) {

				load_markers();

				tbox1.setText("Лондон");
				layout.setHTML(0, 5, "");
				layout.setCellSpacing(3);
				layout.setWidget(0, 0, new Label(
						"Угадайте, где через час будет землятресение? -  "));
				layout.setWidget(0, 1, tbox1);
				layout.setWidget(0, 2, new HTML(""));
				// layout.setWidget(0, 3, km);
				layout.setWidget(0, 4, but1);
				RootPanel.get().add(layout);
				RootPanel.get().add(map);
				// qq();

				// RootPanel.get().add(new HTML(r[0]));
				// } else
				// RootPanel.get().add(new HTML(r[0]));
			}
		});
	}



	private void load_markers() {

		srv.greetServer("a4", new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {

				String s8 = caught.toString();

			}

			public void onSuccess(final String r[]) {

				Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
				icon.setImageURL("markerGreen.png");
				icon.setIconSize(Size.newInstance(12, 20));
				icon.setIconAnchor(Point.newInstance(6, 20));
				
				LatLng place = LatLng.newInstance(Double.parseDouble("46"),
						Double.parseDouble("-77"));
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				final Marker md = new Marker(place, ops);
				md.addMarkerClickHandler(new MarkerClickHandler() {
					public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(md,
								new InfoWindowContent("22222222222"));
					}
				});
				map.addOverlay(md);

				
				
				Icon icon2 = Icon.newInstance(Icon.DEFAULT_ICON);
				icon2.setImageURL("marker.png");
				icon2.setIconSize(Size.newInstance(12, 20));
				icon2.setIconAnchor(Point.newInstance(6, 20));
				place = LatLng.newInstance(Double.parseDouble(r[0]), Double.parseDouble(r[1]));
				ops = MarkerOptions.newInstance(icon2);
				ops.setIcon(icon2);
				final Marker md2 = new Marker(place, ops);
				md2.addMarkerClickHandler(new MarkerClickHandler() {
					public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(md2,
								new InfoWindowContent(r[2]));
					}
				});
				map.addOverlay(md2);
			}
		});
	}

	final LatLngCallback geoint = new LatLngCallback() {
		@Override
		public void onFailure() {
			RootPanel.get().add(new HTML("???"));
		}

		@Override
		public void onSuccess(LatLng point) {
			try {
				place = point;
				Icon icon = Icon.newInstance(Icon.DEFAULT_ICON);
				icon.setImageURL("blue.png");
				icon.setIconSize(Size.newInstance(12, 20));
				icon.setIconAnchor(Point.newInstance(6, 20));
				MarkerOptions ops = MarkerOptions.newInstance(icon);
				ops.setIcon(icon);
				final Marker md = new Marker(place, ops);
				md.addMarkerClickHandler(new MarkerClickHandler() {
					public void onClick(MarkerClickEvent event) {
						map.getInfoWindow().open(
								md,
								new InfoWindowContent(tbox1.getText()));
					}
				});
				map.addOverlay(md);
				layout.setHTML(0, 5, "");
			} catch (Exception eee) {
				layout.setHTML(0, 5, eee.toString());
			}
		}
	};

	class MyHandler implements ClickHandler, KeyUpHandler {
		public void onClick(ClickEvent event) {
			layout.setHTML(0, 5, "Подождите...");
			geo.getLatLng(tbox1.getText(), geoint);
		}

		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				layout.setHTML(0, 5, "Подождите...");
				geo.getLatLng(tbox1.getText(), geoint);
			}
		}
	}

}
