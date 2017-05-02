package com.nasa.robot.mars;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nasa.robot.exception.RobotException;
import com.nasa.robot.model.RobotModel;
import com.nasa.robot.service.RobotService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RobotServiceTest {

	@Configuration
	static class RobotServiceTestConfiguration {
		@Bean
		public RobotService robotService() {
			return new RobotService();
		}
	}
	
	@Autowired
	private RobotService robotService;
	
	// Testar Movimentos invalidos
	@Test(expected = RobotException.class)
	public void testInvalidMovimentWithRightRotation() throws RobotException {
		RobotModel robot = robotService.moviment("MMRMMMMM");		
		Assert.assertNotEquals("Response failure - Http status 400", 400, robot.toString());
	}

	@Test(expected = RobotException.class)
	public void testInvalidMovimentWithLeftRotation() throws RobotException {
		RobotModel robot = robotService.moviment("MMMLMM");
		
		Assert.assertNotEquals("Response failure - Http status 400", 400, robot.toString());
	}
	
	// Testar Movimentos validos
	@Test
	public void testValidMovimentWithoutRotation() throws RobotException {
		RobotModel robot = robotService.moviment("MMM");
		Assert.assertEquals("Response content correct.", new RobotModel(0, 3, "N"), robot);
	}		
	
	@Test
	public void testValidMovimentWithRightRotation() throws RobotException {
		RobotModel robot = robotService.moviment("MMMRMM");
		Assert.assertEquals("Response content correct.", new RobotModel(2, 3, "E"), robot);
	}	
	
	@Test
	public void testValidMovimentWithLeftRotation() throws RobotException {
		RobotModel robot = robotService.moviment("MMRMMLM");
		Assert.assertEquals("Response content correct.", new RobotModel(2, 3, "N"), robot);
	}		
	
	
	
	
	
	
}
