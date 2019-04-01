package ie.tcd.wayfind.lowlevel.request;

public class LatLng {
	
	public double Lat;
	public double Lng;

	public LatLng(double lat, double lng) {
		Lat = lat;
		Lng = lng;
	}
	
	public String toString() {
		return this.Lat+","+this.Lng;
	}
}
