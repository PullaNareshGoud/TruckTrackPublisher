package com.hlc.TruckTrackPublisher.domain;

import lombok.Data;
@Data
public class Transaction {
	public String trans_String_time;
	public int String_time_offset;
	public int tkt_code;
	public String tkt_String_time;
	public int order_code;
	public int truck_code;
	public int employee_code;
	public Message message;
	public String trans_cat;
	public String trans_type;
	public String trans_sub_type;
	public String trans_origin;
	public String trans_origin_ipaddr;
	public String trans_origin_machname;
	public String trans_origin_username;
	public int time_to_live;
}