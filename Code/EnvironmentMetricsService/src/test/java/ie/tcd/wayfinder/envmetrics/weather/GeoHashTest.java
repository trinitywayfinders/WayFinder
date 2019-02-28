package ie.tcd.wayfinder.envmetrics.weather;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ie.tcd.wayfinder.envmetrics.weather.GeoHash.GeoHashFunctions;

public class GeoHashTest {

	private static GeoHashFunctions geohash;

	@BeforeClass
	public static void createGeoHashObject() {
		GeoHashTest.geohash = new GeoHashFunctions();
	}

	@Test
	public void geohashTwoPositive() {
		double lat = 2.79;
		double lng = 1.34;
		assertEquals(GeoHashTest.geohash.getGeoHash(lat, lng), "s02zy");
	}

	@Test
	public void geohashTwoNegative() {
		double lat = -80.94;
		double lng = -41.34;
		assertEquals(GeoHashTest.geohash.getGeoHash(lat, lng), "51dee");
	}

	@Test
	public void geohashNegativePositive() {
		double lat = -81.34;
		double lng = 49.03;
		assertEquals(GeoHashTest.geohash.getGeoHash(lat, lng), "j1dc7");
	}

	@Test
	public void geohashPositiveNegative() {
		double lat = 1.21;
		double lng = -3.84;
		assertEquals(GeoHashTest.geohash.getGeoHash(lat, lng), "ebjqb");
	}

	@Test
	public void geohashZeroPositive() {
		double lat = 0;
		double lng = 42.1;
		assertEquals(GeoHashTest.geohash.getGeoHash(lat, lng), "sbjbn");
	}

	@Test
	public void geohashZeroZero() {
		double lat = 0;
		double lng = 0;
		assertEquals(GeoHashTest.geohash.getGeoHash(lat, lng), "s0000");
	}

	@Test(expected = IllegalArgumentException.class)
	public void geohashOutOfBoundsLatitude() {
		double lat = 91.29;
		double lng = -149.73;
		GeoHashTest.geohash.getGeoHash(lat, lng);
	}

	@Test(expected = IllegalArgumentException.class)
	public void geohashOutOfBoundsLongitude() {
		double lat = 41.3;
		double lng = 191.31;
		GeoHashTest.geohash.getGeoHash(lat, lng);
	}

//	    @Test
//	    public void geohashAdjacentNeighboursCheckLength() {
//	   	 String geohash = "gckbv";
//	   	 assertThat(getAdjacent(geohash), hasSize(8));
//	}
//	    
//	    @Test
//	    public void geohashAdjacentNeighboursCheckEntries() {
//	   	 String geohash = "gckbv";
//	   	 assertThat(getAdjacent(geohash), containsInAnyOrder("gckch", "gckcj", "gckcn", "gckbu", "gckbs", "gckbt", "gckbw", "gckby"));
//	}

}