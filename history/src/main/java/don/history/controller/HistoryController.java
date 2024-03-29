package don.history.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import don.history.domain.DonUserDonHst;
import don.history.domain.DonUserInfoMain;
import don.history.service.HistoryService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
//@Controller //json형식으로 데이터 송수신
@RestController //json형식으로 데이터 송수신
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	private HistoryService histService;	

	@Autowired
	  public HistoryController(final HistoryService histService) {
	    this.histService = histService;
	}
	
	// MY기부내역 조회
	@RequestMapping("/selectMain")
	public @ResponseBody DonUserInfoMain selectMyDonation(@RequestBody String userId) {

		System.out.println("userId : "+userId);
		DonUserInfoMain result = histService.selectHistory(userId);
		System.out.println("result : " + result);
		
		return result;
		
	}
	
	// MY기부내역 자동추가
	@RequestMapping("/insertHistory")
	public @ResponseBody String insertMyDonation(@RequestBody List<HashMap<String, Object>> donationHistory) {

		System.out.println("donUserDonHst : "+donationHistory);
		String result = histService.insertHistory(donationHistory, "REL02");
		System.out.println("result : " + result);
		
		return result;
		
	}

	// MY기부내역 수동추가
	@RequestMapping("/insertHistoryText")
	public @ResponseBody String insertMyDonationText(@RequestBody HashMap<String, Object> donationText) {
		
		List<HashMap<String, Object>> donationHistory = new ArrayList<HashMap<String, Object>>();
		donationHistory.add(donationText);
		System.out.println("donationText : "+donationText);
		String result = histService.insertHistory(donationHistory, "REL01");
		System.out.println("result : " + result);
		
		return result;
		
	}
	
}



