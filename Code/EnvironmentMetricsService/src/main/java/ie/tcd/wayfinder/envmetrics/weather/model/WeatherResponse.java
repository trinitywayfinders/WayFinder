package ie.tcd.wayfinder.envmetrics.weather.model;

import java.util.Objects;
import java.util.regex.Matcher;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import ie.tcd.wayfinder.envmetrics.exceptions.ApiResponseMessage;
import ie.tcd.wayfinder.envmetrics.exceptions.ValueNotAcceptedException;
import io.swagger.annotations.ApiModelProperty;

/**
 * WeatherResponse
 */
@Validated
public class WeatherResponse {

	public static final String GOOD = "Good";
	public static final String BAD = "Bad";
	public static final String UNKNOWN = "Unknown";
	public static final String THUNDERSTORM = "Thunderstorm";
	public static final String DRIZZLE = "Drizzle";
	public static final String RAIN = "Rain";
	public static final String SNOW = "Snow";
	public static final String ATMOSPHERE = "Atmosphere";
	public static final String CLEAR = "Clear";
	public static final String CLOUDS = "Clouds";
	public static final String DEFAULT_ICON = "00d";

	private final String overallPattern = String.format("^(%s)|(%s)|(%s)$", WeatherResponse.GOOD, WeatherResponse.BAD,
			WeatherResponse.UNKNOWN);
	private final String conditionPattern = String.format(
			"^(%s)|(%s)|(%s)|(%s)|(%s)|(%s)|(%s)|(%s)$",
			WeatherResponse.THUNDERSTORM, WeatherResponse.DRIZZLE, WeatherResponse.RAIN, WeatherResponse.SNOW,
			WeatherResponse.ATMOSPHERE, WeatherResponse.CLEAR, WeatherResponse.CLOUDS, WeatherResponse.UNKNOWN);
	private final String iconPattern = "^(\\d{2}(d|n))$";

	@JsonProperty("latitude")
	private float latitude = 0L;

	@JsonProperty("longitude")
	private float longitude = 0L;

	@JsonProperty("overall")
	private String overall = null;

	@JsonProperty("condition")
	private String condition = null;

	@JsonProperty("icon")
	private String icon = null;

	@JsonProperty("timestamp")
	private long timestamp = -1L;

	public WeatherResponse latitude(float latitude) {
		this.latitude = latitude;
		return this;
	}

	/**
	 * Get latitude
	 * 
	 * @return latitude
	 **/
	@ApiModelProperty(example = "12.45", required = true, value = "")
	@NotNull

	@Valid
	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public WeatherResponse longitude(float longitude) {
		this.longitude = longitude;
		return this;
	}

	/**
	 * Get longitude
	 * 
	 * @return longitude
	 **/
	@ApiModelProperty(example = "87.92", required = true, value = "")
	@NotNull

	@Valid
	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public WeatherResponse overall(String overall) throws ValueNotAcceptedException {
		if (checkPattern(overallPattern, overall)) {
			this.overall = overall;
			return this;
		} else {
			throw new ValueNotAcceptedException(ApiResponseMessage.ERROR, "The accepted pattern is " + overallPattern);
		}
	}

	/**
	 * Get overall
	 * 
	 * @return overall
	 **/
	@ApiModelProperty(example = "Good", required = true, value = "")
	@NotNull

	@Pattern(regexp = "^(Good)|(Bad)$")
	public String getOverall() {
		return overall;
	}

	public void setOverall(String overall) throws ValueNotAcceptedException {
		if (checkPattern(overallPattern, overall)) {
			this.overall = overall;
		} else {
			throw new ValueNotAcceptedException(ApiResponseMessage.ERROR, "The accepted pattern is " + overallPattern);
		}

	}

	public WeatherResponse condition(String condition) throws ValueNotAcceptedException {
		if (checkPattern(conditionPattern, condition)) {
			this.condition = condition;
			return this;
		} else {
			throw new ValueNotAcceptedException(ApiResponseMessage.ERROR,
					"The accepted pattern is " + conditionPattern);
		}
	}

	/**
	 * Get condition
	 * 
	 * @return condition
	 **/
	@ApiModelProperty(example = "Thunderstorm", required = true, value = "")
	@NotNull

	@Pattern(regexp = "^(Thunderstorm)|(Drizzle)|(Rain)|(Snow)|(Atmosphere)|(Clear)|(Clouds)$")
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) throws ValueNotAcceptedException {
		if (checkPattern(conditionPattern, condition)) {
			this.condition = condition;
		} else {
			throw new ValueNotAcceptedException(ApiResponseMessage.ERROR,
					"The accepted pattern is " + conditionPattern);
		}
	}

	public WeatherResponse icon(String icon) throws ValueNotAcceptedException {
		if (checkPattern(iconPattern, icon)) {
			this.icon = icon;
			return this;
		} else {
			throw new ValueNotAcceptedException(ApiResponseMessage.ERROR, "The accepted pattern is " + iconPattern);
		}
	}

	/**
	 * Get icon
	 * 
	 * @return icon
	 **/
	@ApiModelProperty(example = "01d", required = true, value = "")
	@NotNull

	@Pattern(regexp = "^(\\d{2}d)$")
	public String getIcon() {

		return icon;
	}

	public void setIcon(String icon) throws ValueNotAcceptedException {
		if (checkPattern(iconPattern, icon)) {
			this.icon = icon;
		} else {
			throw new ValueNotAcceptedException(ApiResponseMessage.ERROR,
					"The accepted pattern is " + iconPattern);
		}
	}

	public WeatherResponse timestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	/**
	 * Get timestamp
	 * 
	 * @return timestamp
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WeatherResponse weatherResponse = (WeatherResponse) o;
		return Objects.equals(this.latitude, weatherResponse.latitude)
				&& Objects.equals(this.longitude, weatherResponse.longitude)
				&& Objects.equals(this.overall, weatherResponse.overall)
				&& Objects.equals(this.condition, weatherResponse.condition)
				&& Objects.equals(this.icon, weatherResponse.icon)
				&& Objects.equals(this.timestamp, weatherResponse.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude, overall, condition, icon, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class WeatherResponse {\n");

		sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
		sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
		sb.append("    overall: ").append(toIndentedString(overall)).append("\n");
		sb.append("    condition: ").append(toIndentedString(condition)).append("\n");
		sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
		sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	private boolean checkPattern(String pattern, String checkString) {
		java.util.regex.Pattern regexPattern = java.util.regex.Pattern.compile(pattern);
		Matcher matcher = regexPattern.matcher(checkString);
		return matcher.find();
	}
}
