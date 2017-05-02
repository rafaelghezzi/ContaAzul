package com.nasa.robot.service;

import java.awt.Point;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.nasa.robot.enums.OrientationEnum;
import com.nasa.robot.exception.RobotException;
import com.nasa.robot.model.RobotModel;

@Service
public class RobotService {
	private final String M = "m", L = "l", R = "r";
	private Point[] TERRAIN = new Point[4];
	private int idx;
	Point pos = new Point(0, 0);

	@PostConstruct
	public void init() {
		TERRAIN[OrientationEnum.NORTH.getValue()] = new Point(0, 1);
		TERRAIN[OrientationEnum.EAST.getValue()] = new Point(1, 0);
		TERRAIN[OrientationEnum.SOUTH.getValue()] = new Point(0, -1);
		TERRAIN[OrientationEnum.WEST.getValue()] = new Point(-1, 0);
	}

	/**
	 * Recebe o comando enviado e realiza os movimentos.  
	 **/
	public RobotModel moviment(String input) throws RobotException {
		if (input.trim().isEmpty())
			throw new RobotException("Command Received Empty");

		idx = OrientationEnum.NORTH.getValue();
		pos = new Point(0, 0);
		
		for (char mov : input.toCharArray()) {
			movimentRobot(mov);
		}
		
		RobotModel moveResult = new RobotModel((int) pos.getX(), (int) pos.getY(), OrientationEnum.values()[idx].toString());
		
		return moveResult;
	}
	
	/**
	 * Valida movimento 
	 **/
	private Boolean validMove(int X, int Y) {
		if ( 0 <= X && X <= 4	&& 0 <= Y && Y <= 4)
			return true;
		else
			return false;		
	}
	
	/**
	 * Move para Esquerda 
	 **/
	private void moveLeft() {
		if (idx == OrientationEnum.NORTH.getValue()) {					
			idx = OrientationEnum.WEST.getValue();
		} else {
			idx--;
		}
	}
	
	/**
	 * Move para direita. 
	 **/
	private void moveRight() {		
		if (idx == OrientationEnum.WEST.getValue()) {
			idx = OrientationEnum.NORTH.getValue();
		} else {
			idx++;
		}
	}
	
	/**
	 * Realiza cada movimento recebido. 
	 **/
	private void movimentRobot(char m) throws RobotException {
		String mov = Character.toString(m).toLowerCase();
		if (M.equals(mov) || L.equals(mov) || R.equals(mov)) {
			if (M.equals(mov)) {
				int posX = (int) (pos.getX() + TERRAIN[idx].getX());
				int posY = (int) (pos.getY() + TERRAIN[idx].getY());
				
				//Valida e executa o movimento solicitado
				if(validMove(posX, posY)){
					pos.x = (int) (pos.getX() + TERRAIN[idx].getX());
					pos.y = (int) (pos.getY() + TERRAIN[idx].getY());					
				} else {					
					throw new RobotException("Invalid Position");
				}			
			} else if (L.equals(mov)) {				
				moveLeft();
			} else {
				moveRight();
			}

		} else {
			throw new RobotException("Invalid command");
		}
	}
	

	
	
}
