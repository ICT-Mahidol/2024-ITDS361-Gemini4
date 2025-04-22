package com.gemini4.gemini4_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.gemini.app.ocs.model.VirtualTelescope;
import jakarta.persistence.*;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Entity
public class Telescope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private double lat;
    private double lon;
    private double step;
    private String status;
    private String version;
    private String state;
    private String location;
//    private Date installedDate;



    public Telescope(int id){
        this.id = id;
        this.version = "2021.04.28_v1.0";
        this.status = "DOWN";
        this.state = "NOT_READY";
        this.location = "Mauna Kea, Hawaii";
        this.lat = 100.0F;
        this.lon = 150.0F;
        this.step = 10.0F;
    }

    public Telescope(){
        super();
        this.version = "2021.04.28_v1.0";
        this.status = "DOWN";
        this.state = "NOT_READY";
        this.location = "Mauna Kea, Hawaii";
        this.lat = 100.0F;
        this.lon = 150.0F;
        this.step = 10.0F;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setState(String state) {
        this.state = state;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public void setVersion(String version) {
        this.version = version;
    }



    public String RunTest(){
        if (getState().equals("READY") && getStatus().equals("RUNNING")){
            return "OK";
        } else if (getState().equals("NOT_READY") && getStatus().equals("SHUTDOWN")) {
            return "BAD";
        } else {
            return "WARNING";
        }
    }

    public String executeCommand(String command){
        switch (command){
            case "GetVersion":
                return getVersion();

            case "GetStatus":
                return getStatus();

            case "GetState":
                return getState();

            case "RunTest":
                return RunTest();
        }
        return "ERROR: Wrong command. Please choose from the list of available commands (GetVersion, GetStatus, GetState, RunTest, START, UP, DOWN, LEFT, RIGHT, FOCUS, TAKE_PHOTO, STOP)";
    }

    public String executeCommand(VirtualTelescope.COMMAND command) {
        if (command != VirtualTelescope.COMMAND.START && (!"RUNNING".equalsIgnoreCase(this.status) || !"READY".equalsIgnoreCase(this.state))) {
            return "ERROR: Cannot execute command. Telescope is not in RUNNING and READY state.";
        }

        switch (command) {
            case START:
                this.status = "RUNNING";
                this.state = "READY";
                return "Starting the virtual telescope ...\nTelescope is pointing to: " + this.lat + ": " + this.lon;
            case STOP:
                this.status = "SHUTDOWN";
                this.state = "NOT_READY";
                return "Stopping the virtual telescope ...";
            case LEFT:
                if (this.lon + this.step <= (double)180.0F) {
                    this.lon += this.step;
                }

                return "Telescope is pointing to: " + this.lat + ": " + this.lon;
            case RIGHT:
                if (this.lon - this.step >= (double)-180.0F) {
                    this.lon -= this.step;
                }

                return "Telescope is pointing to: " + this.lat + ": " + this.lon;
            case UP:
                if (this.lat + this.step <= (double)90.0F) {
                    this.lat += this.step;
                }

                return "Telescope is pointing to: " + this.lat + ": " + this.lon;
            case DOWN:
                if (this.lat - this.step >= (double)-90.0F) {
                    this.lat -= this.step;
                }

                return "Telescope is pointing to: " + this.lat + ": " + this.lon;
            case FOCUS:
                return "Auto focusing at: " + this.lat + " , " + this.lon;
            case TAKE_PHOTO:
                this.state = "BUSY";
                System.out.println(this.state);
                this.state = "READY";
                return "Taking a photo at location: (" + this.location + ")";
        }
        return "ERROR: Wrong command. Please choose from the list of available commands (GetVersion, GetStatus, GetState, RunTest, START, UP, DOWN, LEFT, RIGHT, FOCUS, TAKE_PHOTO, STOP)";
    }

    @Override
    public String toString() {
        return "Telescope{" +
                "version='" + version + '\'' +
                "status='" + status + '\'' +
                "state='" + state + '\'' +
                "location='" + location + '\'' +
                "lat='" + lat + '\'' +
                "lon='" + lon + '\'' +
                "step='" + step + '\'' +
                '}';
    }

}


