/**
 * 
 */
package com.green.action.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.response.RestObject;

/**
 * @author yuanhualiang
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@ResponseBody
	@RequestMapping(value="/a", method = RequestMethod.GET)
	public RestObject add() {
		return RestObject.newOk("OK");
	}
}
