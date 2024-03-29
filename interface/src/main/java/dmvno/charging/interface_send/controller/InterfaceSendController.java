package dmvno.charging.interface_send.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dmvno.charging.interface_send.client.dto.HistoryAttempt;
import dmvno.charging.interface_send.domain.AuthBeans;
import dmvno.charging.interface_send.domain.HistoryBeans;
import dmvno.charging.interface_send.service.InterfaceSendService;

/**
 * 사용자가 POST 로 답안을 전송하도록 REST API 를 제공하는 클래스
 */
@RestController
@RequestMapping("/Interface")
@CrossOrigin
final class InterfaceSendController {

	private final InterfaceSendService interfaceSendService;

	@Autowired
	public InterfaceSendController(final InterfaceSendService interfaceSendService) {
		this.interfaceSendService = interfaceSendService;
	}

	// 외부 인증
	@RequestMapping("/Auth")
	ResponseEntity<String> sendAuthMessage(@RequestBody AuthBeans authBeans) {
		System.out.println("Auth ----" + authBeans);

		String linkYn = interfaceSendService.checkAuth(authBeans);

		return ResponseEntity.ok(linkYn);
	}

	// 이력 외부로부터 가져오기 & 내부 Insert
	@RequestMapping("/GetDonation")
	ResponseEntity<String> sendHistoryMessage(@RequestBody AuthBeans authBeans) {
		System.out.println("GetDonation ----" + authBeans);

		String getYn = interfaceSendService.getHistory(authBeans);

		return ResponseEntity.ok(getYn);
	}
	
	// 개인별 연동이력 조회
	@RequestMapping("/GetLnkg")
	ResponseEntity<List<HistoryBeans>> sendLnkgMessage(@RequestBody AuthBeans authBeans) {
		System.out.println("GetLnkg ----" + authBeans);

		List<HistoryBeans> lnkg = interfaceSendService.getLnkg(authBeans);

		return ResponseEntity.ok(lnkg);
	}
}
