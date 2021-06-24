package com.hlc.TruckTrackPublisher.domain;

import lombok.Data;

@Data
public class TmsMsg {
	public String trans_id;
	public Object veh_fleet_code;
	public int veh_radio_code;
	public int protocol_version;
	public Object packet_code;
	public Transaction transaction;
	public String xsd;
	public String xsi;
	public String text;
}