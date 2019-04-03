package ie.tcd.wayfind.lowlevel.request;

import ie.tcd.wayfind.lowlevel.type.TravelMode;

public class TravelModeBasedOnPreference {

	public UserPreferences UserPreferences;
	public int TotalDistance;
	public int Segment1Distance;
	public int Segment3Distance;
	public TravelMode Segment1Mode;
	public TravelMode Segment2Mode;
	public TravelMode Segment3Mode;

	public TravelModeBasedOnPreference(UserPreferences userPreferences, int totalDistance, int segment1Distance,
			int segment3Distance, TravelMode segment1Mode, TravelMode segment2Mode, TravelMode segment3Mode) {
		super();
		UserPreferences = userPreferences;
		TotalDistance = totalDistance;
		Segment1Distance = segment1Distance;
		Segment3Distance = segment3Distance;
		Segment1Mode = segment1Mode;
		Segment2Mode = segment2Mode;
		Segment3Mode = segment3Mode;
	}

	public TravelModeBasedOnPreference(UserPreferences userPreferences, int totalDistance, String badWeather) {

		TravelModeBasedOnPreference current = getTravelModeBasedOnPreference(userPreferences, totalDistance,
				badWeather.equals("Bad") ? true : false);

		UserPreferences = userPreferences;
		TotalDistance = totalDistance;
		Segment1Distance = current.Segment1Distance;
		Segment3Distance = current.Segment3Distance;
		Segment1Mode = current.Segment1Mode;
		Segment2Mode = current.Segment2Mode;
		Segment3Mode = current.Segment3Mode;
	}

	public TravelModeBasedOnPreference getTravelModeBasedOnPreference(UserPreferences userPreferences,
			int totalDistance, boolean badWeather) {

		if (totalDistance > 5000)
			return getTravelModeMoreThan5(userPreferences, totalDistance, badWeather);

		return getTravelModeLessThan5(userPreferences, totalDistance, badWeather);
	}

	private TravelModeBasedOnPreference getTravelModeMoreThan5(UserPreferences userPreferences, int totalDistance,
			boolean badWeather) {

		if (userPreferences.ConcernHealth == 1) {

			return new TravelModeBasedOnPreference(userPreferences, totalDistance, 2500, 2500,
					badWeather ? TravelMode.driving : TravelMode.walking, TravelMode.driving, TravelMode.bicycling);
		}

		else if (userPreferences.ConcernSpeed == 1) {
			return new TravelModeBasedOnPreference(userPreferences, totalDistance, 1000, 1000,
					badWeather ? TravelMode.driving : TravelMode.bicycling, TravelMode.driving, TravelMode.walking);
		}

		else if (userPreferences.ConcernCost == 1) {
			return new TravelModeBasedOnPreference(userPreferences, totalDistance, 1000, 1000,
					badWeather ? TravelMode.driving : TravelMode.bicycling, TravelMode.transit, TravelMode.walking);
		}

		else if (userPreferences.ConcernPollutionAvoidance == 1) {
			return new TravelModeBasedOnPreference(userPreferences, totalDistance, 1000, 1000, TravelMode.driving,
					TravelMode.driving, TravelMode.driving);
		}

		else
			return new TravelModeBasedOnPreference(userPreferences, totalDistance, 2000, 2000,
					badWeather ? TravelMode.driving : TravelMode.walking, TravelMode.transit, TravelMode.walking);

	}

	private TravelModeBasedOnPreference getTravelModeLessThan5(UserPreferences userPreferences, int totalDistance,
			boolean badWeather) {

		if (userPreferences.ConcernHealth == 1) {

			return new TravelModeBasedOnPreference(userPreferences, totalDistance, totalDistance, 0,
					TravelMode.bicycling, null, null);
		}

		else if (userPreferences.ConcernSpeed == 1) {
			return new TravelModeBasedOnPreference(userPreferences, totalDistance, totalDistance, 0, TravelMode.driving,
					null, null);
		}

		else if (userPreferences.ConcernCost == 1) {
			return new TravelModeBasedOnPreference(userPreferences, totalDistance, totalDistance, 0,
					badWeather ? TravelMode.driving : TravelMode.walking, null, null);
		}

		else if (userPreferences.ConcernPollutionAvoidance == 1) {
			return new TravelModeBasedOnPreference(userPreferences, totalDistance, totalDistance, 0, TravelMode.driving,
					null, null);
		}

		else
			return new TravelModeBasedOnPreference(userPreferences, totalDistance, totalDistance, 0, TravelMode.transit,
					null, null);

	}
}
