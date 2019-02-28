/**
 * 
 */
package ie.tcd.wayfinder.envmetrics.weather.GeoHash;

import ch.hsr.geohash.GeoHash;


public class GeoHashFunctions {

	final int precision = 5;
	
	public String getGeoHash(double latitude, double longitude)
	{
		return GeoHash.geoHashStringWithCharacterPrecision(latitude, longitude, precision);
	}
}
