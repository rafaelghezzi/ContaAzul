package com.nasa.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nasa.robot.exception.RobotException;
import com.nasa.robot.model.RobotModel;
import com.nasa.robot.service.RobotService;

@RestController
public class RobotController {

	@Autowired
	private RobotService robotService;
	
	/**
	 * Metodo para checar o Robo.	 
	 **/
	@RequestMapping(value="/", 
					method = RequestMethod.GET)
	public String CheckRobot(){
		return "Robot - Spring Boot REST [OK]";
	}
	
	/**
	 * Recebe os comando da requisição POST.
	 * Retorna com a movimentação do robo baseada no comando recebido
	 **/
	@RequestMapping(method = RequestMethod.POST, 
				    value = "/rest/mars/{commandreceived}")	
	public ResponseEntity<RobotModel> move(@PathVariable("commandreceived") String commandreceived){
		try {
			RobotModel returnPosition = robotService.moviment(commandreceived);
			return new ResponseEntity<RobotModel>(returnPosition, HttpStatus.OK);			
		} catch (RobotException e) {
			return new ResponseEntity<RobotModel>(HttpStatus.BAD_REQUEST);
		}
		
	}
	


	
}
