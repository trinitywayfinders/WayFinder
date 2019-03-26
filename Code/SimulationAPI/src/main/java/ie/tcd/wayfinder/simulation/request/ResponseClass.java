package ie.tcd.wayfinder.simulation.request;

public class ResponseClass {
        
      public double Lat;
      public double Lng;
      public String reason;

      public void SetVal(double lat, double lng, String reason) {
            Lat = lat;
            Lng = lng;
            reason = reason;
       }
        
       public double getLat() {
            return Lat;
       }
       public double getLng() {
            return Lng;
       }
       public String getReason() {
           return reason;
       }
       
       public String toString() {
           return this.Lat+","+this.Lng+","+this.reason;
       }
        
}

