package ie.tcd.wayfinder.simulation.request;

public class ResponseClass {
        
      public double lat;
      public double lng;
      public String reason;

      public void SetVal(double latitude, double longitude, String reason) {
            lat = latitude;
            lng = longitude;
            this.reason = reason;
       }
        
       public double getLat() {
            return lat;
       }
       public double getLng() {
            return lng;
       }
       public String getReason() {
           return reason;
       }
       
       public String toString() {
           return this.lat+","+this.lng+","+this.reason;
       }
        
}

