package com.nasa.robot.mars;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.nasa.robot.controller.RobotController;
import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RobotControllerTest {

	 @Autowired
     private RobotController robotControllerTest;
	 
	 private MockMvc mvc;
	 
     private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
             MediaType.APPLICATION_JSON.getSubtype(),
             Charset.forName("utf8"));
     
     @Before
     public void setup(){
    	 this.mvc = MockMvcBuilders.standaloneSetup(robotControllerTest).build();
     }
     
 	@Test
 	public void testCommandInvalid() throws Exception{
 		this.mvc.perform(post("/rest/mars/NMM").contentType(contentType))
 											   .andExpect(status().isBadRequest());
 	}
 	          
 	@Test
 	public void testCommandReceivedEmpty() throws Exception{
 		this.mvc.perform(post("/rest/mars/").contentType(contentType))
 											.andExpect(status().isNotFound());
 	}
 	 	 	
	@Test
	public void testCommandValid() throws Exception{
		this.mvc.perform(post("/rest/mars/MMRMLL").contentType(contentType))
												  .andExpect(status().isOk())
												  .andExpect(jsonPath("$.X", is(1)))
												  .andExpect(jsonPath("$.Y", is(2)))
												  .andExpect(jsonPath("$.direction", is("W")));
		
		this.mvc.perform(post("/rest/mars/MMRMMMM").contentType(contentType))
												   .andExpect(status().isOk())
												   .andExpect(jsonPath("$.X", is(4)))
												   .andExpect(jsonPath("$.Y", is(2)))
												   .andExpect(jsonPath("$.direction", is("E")));
	}
	
	@Test
	public void testCommandInvalideTerrain() throws Exception{
		this.mvc.perform(post("/rest/mars/MMMMLMM").contentType(contentType))
												   .andExpect(status().isBadRequest());
		
		this.mvc.perform(post("/rest/mars/MMRMMMMM").contentType(contentType))
													.andExpect(status().isBadRequest());				
	} 	
	
	
	
}
