package com.javaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Integer floorArea;
	private Integer districtId;
	private String ward;
	private String street;
	private Integer numberOfBasement;
	private String direction;
	private String level;
	private Integer rentAreaFrom;
	private Integer rentAreaTo;
	private Integer rentPriceFrom;
	private Integer rentPriceTo;
	private String managerName;
	private String managerPhoneNumber;
	private Integer staffId;
	private List<String> rentType;
	
	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.districtId = builder.districtId;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentAreaTo = builder.rentAreaTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.staffId = builder.staffId;
		this.rentType = builder.rentType;
	}
	
	public String getName() {
		return name;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public String getDirection() {
		return direction;
	}
	public String getLevel() {
		return level;
	}
	public Integer getRentAreaFrom() {
		return rentAreaFrom;
	}
	public Integer getRentAreaTo() {
		return rentAreaTo;
	}
	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}
	public Integer getRentPriceTo() {
		return rentPriceTo;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public List<String> getRentType() {
		return rentType;
	}
	
	public static class Builder {
		private String name;
		private Integer floorArea;
		private Integer districtId;
		private String ward;
		private String street;
		private Integer numberOfBasement;
		private String direction;
		private String level;
		private Integer rentAreaFrom;
		private Integer rentAreaTo;
		private Integer rentPriceFrom;
		private Integer rentPriceTo;
		private String managerName;
		private String managerPhoneNumber;
		private Integer staffId;
		private List<String> rentType;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setDistrictId(Integer districtId) {
			this.districtId = districtId;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}
		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}
		public Builder setRentAreaFrom(Integer rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}
		public Builder setRentAreaTo(Integer rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}
		public Builder setRentPriceFrom(Integer rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}
		public Builder setRentPriceTo(Integer rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder setManagerPhoneNumber(String managerPhoneNumber) {
			this.managerPhoneNumber = managerPhoneNumber;
			return this;
		}
		public Builder setStaffId(Integer staffId) {
			this.staffId = staffId;
			return this;
		}
		public Builder setRentType(List<String> rentType) {
			this.rentType = rentType;
			return this;
		}
		
		public BuildingSearchBuilder build() {
			BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder(this);
			return buildingSearchBuilder;
		}
	}
}
