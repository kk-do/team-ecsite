package jp.co.internous.mushrooms.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.mushrooms.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.mushrooms.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.mushrooms.model.session.LoginSession;

@Controller
@RequestMapping("/mushrooms/history")
public class PurchaseHistoryController {
	@Autowired//LoginSessionインスタンスの作成
	private LoginSession loginSession;
	
	@Autowired  // TblPurchaseHistoryMapperインスタンスの作成
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
	
	@RequestMapping("/")
	public String index(Model m) {
		long userId = loginSession.getUserId();
		List<PurchaseHistoryDto> historyList = purchaseHistoryMapper.findByUserId(userId);
		
		m.addAttribute("historyList", historyList);
		m.addAttribute("loginSession",loginSession);
				
		return "purchase_history"; 
		
	}
		

	//購入履歴を表示するメソッド 
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		long userId = loginSession.getUserId();
		long result = purchaseHistoryMapper.logicalDeleteByUserId(userId);
			
		return result > 0;
	}
		
}
