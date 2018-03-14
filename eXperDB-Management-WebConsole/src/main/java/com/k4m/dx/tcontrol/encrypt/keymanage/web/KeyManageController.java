package com.k4m.dx.tcontrol.encrypt.keymanage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.k4m.dx.tcontrol.admin.accesshistory.service.AccessHistoryService;
import com.k4m.dx.tcontrol.cmmn.serviceproxy.vo.CryptoKeySymmetric;
import com.k4m.dx.tcontrol.common.service.HistoryVO;
import com.k4m.dx.tcontrol.encrypt.service.call.CommonServiceCall;
import com.k4m.dx.tcontrol.encrypt.service.call.KeyManageServiceCall;
/**
 * keyManageController 컨트롤러 클래스를 정의한다.
 *
 * @author 변승우 대리
 * @see
 * 
 *      <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2018.01.09   변승우대리  최초 생성
 *      </pre>
 */
@Controller
public class KeyManageController {
	
	@Autowired
	private AccessHistoryService accessHistoryService;

	/**
	 * 암호화 키 리스트 화면을 보여준다.
	 * 
	 * @param
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/keyManage.do")
	public ModelAndView keyManage(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		CommonServiceCall csc= new CommonServiceCall();
		JSONArray result = new JSONArray();
		try {
			// 화면접근이력 이력 남기기
//			CmmnUtils.saveHistory(request, historyVO);
//			historyVO.setExe_dtl_cd("DX-T0055");
//			accessHistoryService.insertHistory(historyVO);
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");
			
			result = csc.selectSysCodeListExper(restIp, restPort, strTocken,loginId,entityId);
			mv.setViewName("encrypt/keyManage/keyManage");
			mv.addObject("result",result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	
	/**
	 * 암호화 키 등록 팝업을 보여준다.
	 * 
	 * @param request
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/keyManageRegForm.do")
	public ModelAndView keyManageRegForm(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		CommonServiceCall csc= new CommonServiceCall();
		JSONArray result = new JSONArray();
		try {
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			result = csc.selectSysCodeListExper(restIp, restPort, strTocken,loginId,entityId);
//			// 화면접근이력 이력 남기기
//			historyVO.setExe_dtl_cd("DX-T0056");
//			historyVO.setMnu_id(12);
//			accessHistoryService.insertHistory(historyVO);

			mv.setViewName("encrypt/popup/keyManageRegForm");
			mv.addObject("result",result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;

	}
	
	/**
	 * 암호화 키 수정 팝업을 보여준다.
	 * 
	 * @param request
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/keyManageRegReForm.do")
	public ModelAndView keyManageRegReForm(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		CommonServiceCall csc= new CommonServiceCall();
		JSONArray result = new JSONArray();
		try {
			String resourceName = request.getParameter("resourceName");
			String resourceNote = request.getParameter("resourceNote");
			String keyUid = request.getParameter("keyUid");
			String keyStatusCode = request.getParameter("keyStatusCode");
			String keyStatusName = request.getParameter("keyStatusName");
			String cipherAlgorithmName = request.getParameter("cipherAlgorithmName");
			String cipherAlgorithmCode = request.getParameter("cipherAlgorithmCode");
			String updateDateTime = request.getParameter("updateDateTime");
			
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			result = csc.selectSysCodeListExper(restIp, restPort, strTocken,loginId,entityId);
//			// 화면접근이력 이력 남기기
//			historyVO.setExe_dtl_cd("DX-T0056");
//			historyVO.setMnu_id(12);
//			accessHistoryService.insertHistory(historyVO);

			mv.setViewName("encrypt/popup/keyManageRegReForm");
			mv.addObject("result",result);
			mv.addObject("resourceName",resourceName);
			mv.addObject("resourceNote",resourceNote);
			mv.addObject("keyUid",keyUid);
			mv.addObject("keyStatusCode",keyStatusCode);
			mv.addObject("keyStatusName",keyStatusName);
			mv.addObject("cipherAlgorithmName",cipherAlgorithmName);
			mv.addObject("cipherAlgorithmCode",cipherAlgorithmCode);
			mv.addObject("updateDateTime",updateDateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	

	/**
	 * 암호화 키를 조회한다.
	 * 
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectCryptoKeyList.do")
	public @ResponseBody JSONObject selectCryptoKeyList(HttpServletRequest request) {
			
		KeyManageServiceCall kmsc= new KeyManageServiceCall();
		JSONObject result = new JSONObject();
		try {
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			result = kmsc.selectCryptoKeyList(restIp, restPort, strTocken,loginId,entityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 암호화 키를 등록한다.
	 * 
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertCryptoKeySymmetric.do")
	public @ResponseBody JSONObject insertCryptoKeySymmetric(HttpServletRequest request) {
		
		String resourceName = request.getParameter("resourceName");
		String cipherAlgorithmCode = request.getParameter("cipherAlgorithmCode");
		String resourceNote = request.getParameter("resourceNote");
		String validEndDateTime = request.getParameter("validEndDateTime") + " 23:59:59.999999";
			
		CryptoKeySymmetric param = new CryptoKeySymmetric();
		param.setResourceName(resourceName);
		param.setCipherAlgorithmCode(cipherAlgorithmCode);
		param.setResourceNote(resourceNote);
		param.setValidEndDateTime(validEndDateTime);
		param.setKeyStatusCode("KS50");
		

		KeyManageServiceCall kmsc= new KeyManageServiceCall();
		JSONObject result = new JSONObject();
		try {
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			result = kmsc.insertCryptoKeySymmetric(restIp, restPort,loginId,entityId, strTocken, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 암호화 키를 업데이트한다.
	 * 
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCryptoKeySymmetric.do")
	public @ResponseBody JSONObject updateCryptoKeySymmetric(HttpServletRequest request) {
		
		String keyUid = request.getParameter("keyUid");
		String resourceUid = request.getParameter("resourceUid");
		String resourceName = request.getParameter("resourceName");
		String cipherAlgorithmCode = request.getParameter("cipherAlgorithmCode");
		String resourceNote = request.getParameter("resourceNote");
		String validEndDateTime = request.getParameter("validEndDateTime") + " 23:59:59.999999";
		String renew = request.getParameter("renew");
		String copyBin = request.getParameter("copyBin");
		
		
		CryptoKeySymmetric param = new CryptoKeySymmetric();
		param.setKeyUid(keyUid);
		param.setResourceUid(resourceUid);
		param.setResourceName(resourceName);
		param.setCipherAlgorithmCode(cipherAlgorithmCode);
		param.setResourceNote(resourceNote);
		param.setValidEndDateTime(validEndDateTime);
		param.setKeyStatusCode("KS50");
		param.setUpdateUid("00000000-0000-0000-0000-000000000001");
		if(renew.equals("true")){
			param.setRenew(true);
		}else{
			param.setRenew(false);
		}
		
		if(copyBin.equals("true")){
			param.setCopyBin(true);
		}else{
			param.setCopyBin(false);
		}

		KeyManageServiceCall kmsc= new KeyManageServiceCall();
		JSONObject result = new JSONObject();
		try {
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			result = kmsc.updateCryptoKeySymmetric(restIp, restPort, strTocken,loginId,entityId, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 암호화 키 갱신이력을 조회한다.
	 * 
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/historyCryptoKeySymmetric.do")
	public @ResponseBody JSONObject historyCryptoKeySymmetric(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			
			String keyUid = request.getParameter("keyUid");
			
			CryptoKeySymmetric param = new CryptoKeySymmetric();
			param.setKeyUid(keyUid);
			
			KeyManageServiceCall kmsc= new KeyManageServiceCall();

			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			result = kmsc.selectCryptoKeySymmetricList(restIp, restPort, strTocken, loginId, entityId, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**CryptoKeySymmetric
	 * 암호화 키를 삭제한다.
	 * 
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCryptoKeySymmetric.do")
	public @ResponseBody JSONObject deleteCryptoKeySymmetric(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try{
			String keyUid = request.getParameter("keyUid");
			String resourceName = request.getParameter("resourceName");
			String resourceTypeCode = request.getParameter("resourceTypeCode");
			
			CryptoKeySymmetric param = new CryptoKeySymmetric();
			param.setKeyUid(keyUid);
			param.setResourceName(resourceName);
			param.setResourceTypeCode(resourceTypeCode);			
			
			KeyManageServiceCall kmsc= new KeyManageServiceCall();
			
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");
			
			result= kmsc.deleteCryptoKeySymmetric(restIp, restPort, strTocken, loginId, entityId, param);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
