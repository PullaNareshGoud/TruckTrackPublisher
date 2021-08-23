package com.hlc.trucksignaling.domain;

import lombok.Data;

@Data
public class Message {
	public Object time_offset;
	public Object type;
	public int value;
	public String value_uom;
	public Gps gps;
	public String sitename;
}