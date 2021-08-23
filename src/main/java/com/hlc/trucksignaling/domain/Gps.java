package com.hlc.trucksignaling.domain;

import lombok.Data;
@Data
public class Gps {
	public String utc_String_time;
	public int utctime;
	public double latitude;
	public String latitude_hemi;
	public double longitude;
	public String longitude_hemi;
	public Object satellites;
	public double speed;
	public int heading;
	public Object antenna;
	public double version;
	public String text;
}





