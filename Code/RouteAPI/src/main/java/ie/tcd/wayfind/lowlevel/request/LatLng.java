package ie.tcd.wayfind.lowlevel.request;

public class LatLng {
	
	public double Lat;
	public double Lng;
	
	public LatLng(double lat, double lng) {
		Lat = lat;
		Lng = lng;
	}
	
	public double getLat() {
		return Lat;
	}
	
	public void setLat(double lat) {
		Lat = lat;
	}
	public double getLng() {
		return Lng;
	}
	public void setLng(double lng) {
		Lng = lng;
	}
	
	
}
