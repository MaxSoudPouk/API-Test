package com.etl.kyc.etlkycsimregisterapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

@RestController
@RequestMapping("/ETLKYC")
public class kycController<JwtUserDetailsService, JwtTokenUtil> {
//loginService empService;
//	
//	@RequestMapping(value="/Requestlogin", method=RequestMethod.POST)
//	public User createEmployee(@RequestBody User emp) {
//	    return empService.createEmployee(emp);
//	}
//	
	

	//==========Check SiM Status after sim register
	    @RequestMapping(value="/querysimstatus", method=RequestMethod.POST)
		public SIM_status Querysim(String msisdn, String datetime, String token) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {
		
		SIM_status sim =new SIM_status();
		Calendar currentDate111 = Calendar.getInstance();
		SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter111.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
		String dateNow111 = formatter111.format(currentDate111.getTime());
		String datepro111 = dateNow111.toString();
		//sim.setResultCode("405000000");
		//t.setResultMsg("Response msg on tiem"+ datepro111);
		SIM_status getstatus=sim.QuerySIM(msisdn, datepro111, token);
		
			return getstatus;
			
		}	
	
////	@RequestMapping(value="/employees/{empId}", method=RequestMethod.PUT)
////	public Employee readEmployees(@PathVariable(value = "empId") Long id, @RequestBody Employee empDetails) {
////	    return empService.updateEmployee(id, empDetails);
////	}
//
//	@RequestMapping(value="/Requestlogin/{Id}", method=RequestMethod.DELETE)
//	public void deleteEmployees(@PathVariable(value = "Id") Integer id) {
//	    empService.deleteEmployee(id);
//	}
	

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public User getUser(@PathVariable("username") String username) {
//		return null;
//	}
//	
	//============================ Agent Login================
	//@PostMapping("/login")
	 @RequestMapping(value="/login", method=RequestMethod.POST)
	  @ResponseBody
	public User login(String username, String password) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {
		User getuser =new User();
	
		User dbgetuser=  getuser.Queryuser(username, password);
		return dbgetuser;
		
		
	}	
}

