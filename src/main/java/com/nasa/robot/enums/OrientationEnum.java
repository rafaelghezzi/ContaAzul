package com.nasa.robot.enums;

public enum OrientationEnum {

	NORTH(0) {
		@Override
		public String toString() {
			return "N";
		}},
	EAST(1) {
		@Override
		public String toString() {
			return "E";
		}},
	SOUTH(2) {
		@Override
		public String toString() {
			return "S";
		}},
	WEST(3) {
		@Override
		public String toString() {
			return "W";
		}};

	private int value;

	OrientationEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
