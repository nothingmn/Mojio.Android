package Mojio;

import org.json.JSONObject;

/**
 * Created by robchartier on 15-01-17.
 */
public class Vehicle {
    public Vehicle() {
        LastLocation = new Location();
    }
    public Vehicle(JSONObject data)
    {
        this();
        OwnerId = getString(data, "OwnerId", "");
        MojioId = getString(data, "MojioId", "");
        Name = getString(data, "Name", "");
        VIN = getString(data, "VIN", "");
        LicensePlate = getString(data, "LicensePlate", "");
        IgnitionOn = getBool(data, "IgnitionOn", false);
        VehicleTime = getString(data, "VehicleTime", "");
        LastTripEvent = getString(data, "LastTripEvent", "");
        LastLocationTime = getString(data, "LastLocationTime", "");
        LastLocation = getLocation(data, "LastLocation", new Location());
        LastSpeed = getDouble(data, "LastSpeed", .0);
        FuelLevel = getDouble(data, "FuelLevel", .0);
        LastBatteryVoltage = getDouble(data, "LastBatteryVoltage", .0);
        LastDistance = getDouble(data, "LastDistance", .0);
        LastFuelEfficiency = getDouble(data, "LastFuelEfficiency", .0);
        id = MojioId;
    }

    public String id;
    public String OwnerId;
    public String MojioId;
    public String Name;
    public String VIN;
    public String LicensePlate;
    public Boolean IgnitionOn;
    public String VehicleTime;
    public String LastTripEvent;
    public String LastLocationTime;
    public Location LastLocation;
    public Double LastSpeed;
    public Double FuelLevel;
    public Double LastAcceleration;
    public Double LastBatteryVoltage;
    public Double LastDistance;
    public Double LastFuelEfficiency;

    private String getString(JSONObject data,String key, String defaultValue) {
        try {
            return data.getString(key);
        }catch(Exception e){

        }
        return defaultValue;
    }

    private Double getDouble(JSONObject data,String key, Double defaultValue) {
        try {
            return data.getDouble(key);
        }catch(Exception e){

        }
        return defaultValue;
    }


    private Boolean getBool(JSONObject data,String key, Boolean defaultValue) {
        try {
            return data.getBoolean(key);
        }catch(Exception e){

        }
        return defaultValue;
    }

    private Location getLocation(JSONObject data, String key, Location defaultValue){
        try {
            Location location = new Location();
            JSONObject root = data.getJSONObject(key);
            location.Lat = getDouble(root, "Lat", .0);
            location.Lng = getDouble(root, "Lng", .0);
            location.FromLockedGPS = getBool(root, "FromLockedGPS", false);
            location.Dilution = getDouble(root, "Dilution", .0);
            location.IsValid = getBool(root, "IsValid", false);
        }catch(Exception e){

        }
        return defaultValue;
    }

    @Override
    public String toString() {
        return Name + " (" + VIN + ")";
    }
}
