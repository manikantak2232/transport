package com.pixelbox.dto;

import java.io.Serializable;
import java.util.List;

public class TestDto implements Serializable {
	
	private List<Integer> fk_spare_part_id;
	
	private Integer fk_driver_id;
	
	private Integer fk_truck_id;
	
	private String date;
	
	private int count;

	public Integer getFk_driver_id() {
		return fk_driver_id;
	}

	public void setFk_driver_id(Integer fk_driver_id) {
		this.fk_driver_id = fk_driver_id;
	}

	public Integer getFk_truck_id() {
		return fk_truck_id;
	}

	public void setFk_truck_id(Integer fk_truck_id) {
		this.fk_truck_id = fk_truck_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Integer> getFk_spare_part_id() {
		return fk_spare_part_id;
	}

	public void setFk_spare_part_id(List<Integer> fk_spare_part_id) {
		this.fk_spare_part_id = fk_spare_part_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
