package com.nasa.robot.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RobotModel implements Serializable{

	public int X, Y;
	public String direction;
	
	public RobotModel(int x, int y, String direction ) {
		this.X = x;
		this.Y = y;
		this.direction = direction;		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RobotModel))
			return false;
		
		RobotModel objRobot = (RobotModel) obj;
		
		return this.X == objRobot.X && 
			   this.Y == objRobot.Y && 
			   this.direction.equals(objRobot.direction);
	}
}
