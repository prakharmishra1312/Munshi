package com.munshi.device;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.munshi.device.bean.Device;
import com.munshi.device.controller.DeviceController;
import com.munshi.device.controller.exception.DeviceNotFoundException;
import com.munshi.device.service.DeviceDAOService;


public class DeviceApplicationTests {

	@InjectMocks
	private DeviceController controller;
	
	@Mock
	private DeviceDAOService deviceDAOService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	private Device initDevice(String id){
		Device device = new Device();
		device.setId(id);
		device.setType("Default");
		device.setUserId("test.user@dummy.com");
		
		
		
		return device;
	}
	
	@Test
	public void getPositiveDeviceTest() {
		
		Device device=initDevice("Test1");
		when(deviceDAOService.getDevicebyID("Test1")).thenReturn(device);
		Device fromController = controller.getDevicebyId("Test1").getContent();
		
		verify(deviceDAOService).getDevicebyID("Test1");
		assertSame(device, fromController);
		
	}
	
	@Test 
	public void getNegativeTest(){
		Device device=initDevice("Test1");
		Device device2=initDevice("Test2");
		when(deviceDAOService.getDevicebyID("Test1")).thenReturn(device);
		when(deviceDAOService.getDevicebyID("Test2")).thenReturn(device2);
		
		Device fromController = controller.getDevicebyId("Test2").getContent();
		
		verify(deviceDAOService).getDevicebyID("Test2");
		assertNotSame(device, fromController);
	}
	@Test 
	public void getNullTest(){
	
		when(deviceDAOService.getDevicebyID("Test1")).thenReturn(null);
		try{
		
		Device fromController = controller.getDevicebyId("Test1").getContent();
		}catch(DeviceNotFoundException e){
			verify(deviceDAOService).getDevicebyID("Test1");
			assertNotNull(e);
		}
		finally{
			verify(deviceDAOService).getDevicebyID("Test1");
			
		}
		
	}
	

}
