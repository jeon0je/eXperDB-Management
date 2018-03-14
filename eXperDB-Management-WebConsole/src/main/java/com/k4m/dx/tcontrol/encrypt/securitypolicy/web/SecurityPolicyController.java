package com.k4m.dx.tcontrol.encrypt.securitypolicy.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.k4m.dx.tcontrol.admin.accesshistory.service.AccessHistoryService;
import com.k4m.dx.tcontrol.cmmn.CmmnUtils;
import com.k4m.dx.tcontrol.cmmn.serviceproxy.SystemCode;
import com.k4m.dx.tcontrol.cmmn.serviceproxy.vo.ProfileAclSpec;
import com.k4m.dx.tcontrol.cmmn.serviceproxy.vo.ProfileCipherSpec;
import com.k4m.dx.tcontrol.cmmn.serviceproxy.vo.ProfileProtection;
import com.k4m.dx.tcontrol.common.service.HistoryVO;
import com.k4m.dx.tcontrol.encrypt.service.call.CommonServiceCall;
import com.k4m.dx.tcontrol.encrypt.service.call.KeyManageServiceCall;
import com.k4m.dx.tcontrol.encrypt.service.call.SecurityPolicyServiceCall;

/**
 * PolicyController 컨트롤러 클래스를 정의한다.
 *
 * @author 김주영
 * @see
 * 
 *      <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2017.11.20   김주영 최초 생성
 *      </pre>
 */
@Controller
public class SecurityPolicyController {

	@Autowired
	private AccessHistoryService accessHistoryService;
	
	/**
	 * 보안정책관리 화면을 보여준다.
	 * 
	 * @param
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/securityPolicy.do")
	public ModelAndView securityPolicy(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			// 화면접근이력 이력 남기기
			CmmnUtils.saveHistory(request, historyVO);
			historyVO.setExe_dtl_cd("DX-T0055");
			accessHistoryService.insertHistory(historyVO);

			mv.setViewName("encrypt/securityPolicy/securityPolicy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	
	/**
	 * 보안정책관리 리스트를 조회한다.
	 * 
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectSecurityPolicy.do")
	public @ResponseBody JSONObject selectSecurityPolicy(HttpServletRequest request) {
		SecurityPolicyServiceCall sic = new SecurityPolicyServiceCall();
		JSONObject result = new JSONObject();
		try {
			
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			result = sic.selectProfileList(restIp,restPort,strTocken,loginId,entityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 보안정책등록 화면을 보여준다.
	 * 
	 * @param request
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/securityPolicyInsert.do")
	public ModelAndView securityPolicyInsert(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		SecurityPolicyServiceCall sic = new SecurityPolicyServiceCall();
		JSONArray dataTypeCode = new JSONArray();
		JSONArray denyResultTypeCode = new JSONArray();
		
		try {

//			// 화면접근이력 이력 남기기
//			historyVO.setExe_dtl_cd("DX-T0056");
//			historyVO.setMnu_id(12);
//			accessHistoryService.insertHistory(historyVO);
			
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			/*데이터타입*/
			dataTypeCode = sic.selectParamSysCodeListDatatype(restIp, restPort, strTocken,loginId,entityId);
			mv.addObject("dataTypeCode",dataTypeCode);
			/*접근거부시처리*/
			denyResultTypeCode = sic.selectParamSysCodeListDenyresult(restIp, restPort, strTocken,loginId,entityId);
			mv.addObject("denyResultTypeCode",denyResultTypeCode);
			
			mv.setViewName("encrypt/securityPolicy/securityPolicyInsert");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 보안정책수정 화면을 보여준다.
	 * 
	 * @param request
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/securityPolicyModify.do")
	public ModelAndView securityPolicyModify(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		SecurityPolicyServiceCall sic = new SecurityPolicyServiceCall();
		JSONArray dataTypeCode = new JSONArray();
		JSONArray denyResultTypeCode = new JSONArray();
		JSONObject result = new JSONObject();
		JSONArray cryptoKey = new JSONArray();
		try {

//			// 화면접근이력 이력 남기기
//			historyVO.setExe_dtl_cd("DX-T0056");
//			historyVO.setMnu_id(12);
//			accessHistoryService.insertHistory(historyVO);

			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			
			/*데이터타입*/
			dataTypeCode = sic.selectParamSysCodeListDatatype(restIp, restPort, strTocken,loginId,entityId);
			mv.addObject("dataTypeCode",dataTypeCode);
			/*접근거부시처리*/
			denyResultTypeCode = sic.selectParamSysCodeListDenyresult(restIp, restPort, strTocken,loginId,entityId);
			mv.addObject("denyResultTypeCode",denyResultTypeCode);
	
			/*키이름*/
			cryptoKey = sic.selectCryptoKeySymmetricList(restIp, restPort, strTocken, loginId, entityId);
			mv.addObject("cryptoKey",cryptoKey);
			
			String profileUid = request.getParameter("profileUid");
			result = sic.selectProfileProtectionContents(restIp, restPort, strTocken, loginId, entityId, profileUid);
			
			System.out.println(result);
			
			mv.addObject("profileUid",request.getParameter("profileUid"));
			mv.addObject("result",result);
			mv.setViewName("encrypt/securityPolicy/securityPolicyModify");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 암복호화 정책 등록/수정 팝업을 보여준다.
	 * 
	 * @param request
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/securityPolicyRegForm.do")
	public ModelAndView securityPolicyRegForm(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		CommonServiceCall csc= new CommonServiceCall();
		SecurityPolicyServiceCall sic = new SecurityPolicyServiceCall();
		KeyManageServiceCall kmsc= new KeyManageServiceCall();
		JSONArray cipherAlgorithmCode = new JSONArray();
		JSONArray initialVectorTypeCode = new JSONArray();
		JSONArray operationModeCode = new JSONArray();
		JSONObject binUid = new JSONObject();
		try {

//			// 화면접근이력 이력 남기기
//			historyVO.setExe_dtl_cd("DX-T0056");
//			historyVO.setMnu_id(12);
//			accessHistoryService.insertHistory(historyVO);
			String act = request.getParameter("act");
			
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			/*암호화 키*/
			binUid = kmsc.selectCryptoKeyList(restIp, restPort, strTocken,loginId,entityId);
			mv.addObject("binUid",binUid);
			/*암호화알고리즘*/
			cipherAlgorithmCode = csc.selectSysCodeListExper(restIp, restPort, strTocken,loginId,entityId);
			mv.addObject("cipherAlgorithmCode",cipherAlgorithmCode);
			/*초기벡터*/
			initialVectorTypeCode = sic.selectParamSysCodeListVector(restIp, restPort, strTocken,loginId,entityId);
			mv.addObject("initialVectorTypeCode",initialVectorTypeCode);
			/*운영모드*/
			operationModeCode = sic.selectParamSysCodeListOperation(restIp, restPort, strTocken,loginId,entityId);
			mv.addObject("operationModeCode",operationModeCode);
			
			if (act.equals("u")) {
				mv.addObject("rnum",request.getParameter("rnum").equals("undefined") ? "" : request.getParameter("rnum"));
				mv.addObject("offset",request.getParameter("offset").equals("undefined") ? "" : request.getParameter("offset"));
				mv.addObject("length",request.getParameter("length").equals("undefined") ? "" : request.getParameter("length"));
				mv.addObject("cipherAlgorithmCodeValue",request.getParameter("cipherAlgorithmCode").equals("undefined") ? "" : request.getParameter("cipherAlgorithmCode"));
				mv.addObject("binUidValue",request.getParameter("binUid").equals("undefined") ? "" : request.getParameter("binUid"));
				mv.addObject("initialVectorTypeCodeValue",request.getParameter("initialVectorTypeCode").equals("undefined") ? "" : request.getParameter("initialVectorTypeCode"));
				mv.addObject("operationModeCodeValue",request.getParameter("operationModeCode").equals("undefined") ? "" : request.getParameter("operationModeCode"));
			}
			mv.addObject("act", act);
			mv.setViewName("encrypt/popup/securityPolicyRegForm");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 접근제어 정책 등록/수정 팝업을 보여준다.
	 * 
	 * @param request
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/accessPolicyRegForm.do")
	public ModelAndView accessPolicyRegForm(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {

//			// 화면접근이력 이력 남기기
//			historyVO.setExe_dtl_cd("DX-T0056");
//			historyVO.setMnu_id(12);
//			accessHistoryService.insertHistory(historyVO);
			String act = request.getParameter("act");
			
			if (act.equals("u")) {
				mv.addObject("rnum",request.getParameter("rnum").equals("undefined") ? "" : request.getParameter("rnum"));
				mv.addObject("specName",request.getParameter("specName").equals("undefined") ? "" : request.getParameter("specName"));
				mv.addObject("serverInstanceId",request.getParameter("serverInstanceId").equals("undefined") ? "" : request.getParameter("serverInstanceId"));
				mv.addObject("serverLoginId",request.getParameter("serverLoginId").equals("undefined") ? "" : request.getParameter("serverLoginId"));
				mv.addObject("adminLoginId",request.getParameter("adminLoginId").equals("undefined") ? "" : request.getParameter("adminLoginId"));
				mv.addObject("osLoginId",request.getParameter("osLoginId").equals("undefined") ? "" : request.getParameter("osLoginId"));
				mv.addObject("applicationName",request.getParameter("applicationName").equals("undefined") ? "" : request.getParameter("applicationName"));
				mv.addObject("accessAddress",request.getParameter("accessAddress").equals("undefined") ? "" : request.getParameter("accessAddress"));
				mv.addObject("accessAddressMask",request.getParameter("accessAddressMask").equals("undefined") ? "" : request.getParameter("accessAddressMask"));
				mv.addObject("accessMacAddress",request.getParameter("accessMacAddress").equals("undefined") ? "" : request.getParameter("accessMacAddress"));
				mv.addObject("startDateTime",request.getParameter("startDateTime").equals("undefined") ? "" : request.getParameter("startDateTime"));
				mv.addObject("endDateTime",request.getParameter("endDateTime").equals("undefined") ? "" : request.getParameter("endDateTime"));
				mv.addObject("startTime",request.getParameter("startTime").equals("undefined") ? "" : request.getParameter("startTime"));
				mv.addObject("endTime",request.getParameter("endTime").equals("undefined") ? "" : request.getParameter("endTime"));
				mv.addObject("workDay",request.getParameter("workDay").equals("undefined") ? "" : request.getParameter("workDay"));
				mv.addObject("massiveThreshold",request.getParameter("massiveThreshold").equals("undefined") ? "" : request.getParameter("massiveThreshold"));
				mv.addObject("massiveTimeInterval",request.getParameter("massiveTimeInterval").equals("undefined") ? "" : request.getParameter("massiveTimeInterval"));
				mv.addObject("extraName",request.getParameter("extraName").equals("undefined") ? "" : request.getParameter("extraName"));
				mv.addObject("hostName",request.getParameter("hostName").equals("undefined") ? "" : request.getParameter("hostName"));
				mv.addObject("whitelistYesNo",request.getParameter("whitelistYesNo").equals("undefined") ? "" : request.getParameter("whitelistYesNo"));
			}
			
			mv.addObject("act", act);
			mv.setViewName("encrypt/popup/accessPolicyRegForm");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 보호 정책을 등록한다.
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertSecurityPolicy.do")
	public @ResponseBody Map<String, Object> insertSecurityPolicy(HttpServletRequest request) {
		SecurityPolicyServiceCall sic = new SecurityPolicyServiceCall();
		JSONArray cryptoKey = new JSONArray();
		Map<String, Object> result = null;
		try {		
			ProfileProtection param1 = new ProfileProtection();
			//보호정책 이름
			param1.setProfileName(request.getParameter("profileName")); 
			//보호 정책 설명
			param1.setProfileNote(request.getParameter("profilenote"));
			//데이터타입
			param1.setDataTypeCode(request.getParameter("dataTypeCode"));
			param1.setBase64YesNo("Y");
			//이중 암호화 방지
			param1.setPreventDoubleYesNo(request.getParameter("preventDoubleYesNo")==null ? "N" : request.getParameter("preventDoubleYesNo"));
			//Null 암호화
			param1.setNullEncryptYesNo(request.getParameter("nullEncryptYesNo")==null ? "N" : request.getParameter("nullEncryptYesNo"));
			//접근거부시처리
			param1.setDenyResultTypeCode(request.getParameter("denyResultTypeCode"));
			//대체문자열
			param1.setMaskingValue(request.getParameter("maskingValue"));
			
			long optionBits = 0;
			
			if(request.getParameter("log_on_fail")==null){
				//실패 로그기록 체크가 아니면
				optionBits |= SystemCode.BitMask.POLICY_OPT_NO_AUDIT_LOG_ON_FAIL;
			}
			if(request.getParameter("log_on_success")==null){
				//성공로그기록 체크가 아니면 
				optionBits |= SystemCode.BitMask.POLICY_OPT_NO_AUDIT_LOG_ON_SUCCESS;
			}
			if(request.getParameter("compress_audit_log")!=null){
				//로그압축 체크이면
				optionBits |= SystemCode.BitMask.POLICY_OPT_COMPRESS_AUDIT_LOG;
			}
			param1.setOptionBits(optionBits);
			
			boolean defaultAccessAllowTrueFalse = true;
			if(request.getParameter("defaultAccessAllowTrueFalse").equals("N")){
				defaultAccessAllowTrueFalse = false;
			}
			param1.setDefaultAccessAllowTrueFalse(defaultAccessAllowTrueFalse);
			
			/*암호화정책*/
			List param2 = new ArrayList();
			ProfileCipherSpec p = new ProfileCipherSpec();
			String strRows = request.getParameter("securityPolicy").toString().replaceAll("&quot;", "\"");
			JSONArray rows = (JSONArray) new JSONParser().parse(strRows);
					
			for (int i = 0; i < rows.size(); i++) {
				JSONObject jsrow = (JSONObject) rows.get(i);		
				p.setSpecIndex(i+1);
				//암호화 알고리즘 코드
				String getCipherAlgorithmCode = jsrow.get("cipherAlgorithmCode").toString();
				String cipherAlgorithmCode="";
				if(getCipherAlgorithmCode.equals("SEED-128")){
					cipherAlgorithmCode = "CAD1";
				}
				if(getCipherAlgorithmCode.equals("ARIA-128")){
					cipherAlgorithmCode = "CAR1";
				}
				if(getCipherAlgorithmCode.equals("ARIA-192")){
					cipherAlgorithmCode = "CAR2";
				}
				if(getCipherAlgorithmCode.equals("ARIA-256")){
					cipherAlgorithmCode = "CAR3";
				}
				if(getCipherAlgorithmCode.equals("AES-128")){
					cipherAlgorithmCode = "CAA1";
				}
				if(getCipherAlgorithmCode.equals("AES-192")){
					cipherAlgorithmCode = "CAA2";
				}
				if(getCipherAlgorithmCode.equals("AES-256")){
					cipherAlgorithmCode = "CAA3";
				}
				if(getCipherAlgorithmCode.equals("LPE-NUM")){
					cipherAlgorithmCode = "CALN";
				}
				
				p.setCipherAlgorithmCode(cipherAlgorithmCode);
				
				//운영모드코드
				String getOperationModeCode = jsrow.get("operationModeCode").toString();
				String operationModeCode="";
				if(getOperationModeCode.equals("CBC")){
					operationModeCode = "OMCB";
				}
				if(getOperationModeCode.equals("CTR")){
					operationModeCode = "OMCT";
				}
				p.setOperationModeCode(operationModeCode);
				
				//초기벡터코드
				String getInitialVectorTypeCode = jsrow.get("initialVectorTypeCode").toString();
				String initialVectorTypeCode = "";
				if(getInitialVectorTypeCode.equals("FIXED")){
					initialVectorTypeCode = "IVFX";
				}
				if(getInitialVectorTypeCode.equals("RANDOM")){
					initialVectorTypeCode = "IVRD";
				}
				p.setInitialVectorTypeCode(initialVectorTypeCode);
				
				//offset
				p.setOffset(Integer.parseInt(jsrow.get("offset").toString()));
				if(jsrow.get("length").toString().equals("끝까지")){
					p.setLength(null); //길이
				}else{
					p.setLength(Integer.parseInt(jsrow.get("length").toString())); //길이
				}
				
				HttpSession session = request.getSession();
				String restIp = (String)session.getAttribute("restIp");
				int restPort = (int)session.getAttribute("restPort");
				String strTocken = (String)session.getAttribute("tockenValue");
				String loginId = (String)session.getAttribute("usr_id");
				String entityId = (String)session.getAttribute("ectityUid");
				
				cryptoKey = sic.selectCryptoKeySymmetricList(restIp, restPort, strTocken, loginId, entityId);
				String getbinuid = jsrow.get("binUid").toString(); //키이름
				String binuid = "";
				for(int j=0; j<cryptoKey.size(); j++){
					JSONObject data = (JSONObject) cryptoKey.get(j);
					if(getbinuid.equals(data.get("resourceName"))){
						binuid=(String) data.get("getBinUid");
					}
				}
				p.setBinUid(binuid);
				param2.add(p.toJSONString());
			}

			/*접근제어 정책*/
			List param3 = new ArrayList();
			ProfileAclSpec r = new ProfileAclSpec();
			
			strRows = request.getParameter("accessPolicy").toString().replaceAll("&quot;", "\"");
			rows = (JSONArray) new JSONParser().parse(strRows);
			for (int i = 0; i < rows.size(); i++) {
				JSONObject jsrow = (JSONObject) rows.get(i);

				r.setSpecIndex(i+1); //row
				r.setSpecOrder(i+1); //row
				r.setSpecName(jsrow.get("specName").toString());
				//서버 인스턴스
				r.setServerInstanceId(jsrow.get("serverInstanceId").toString());
				//DB 사용자
				r.setServerLoginId(jsrow.get("serverLoginId").toString());
				//experdb 사용자
				r.setAdminLoginId(jsrow.get("adminLoginId").toString());
				//프로그램 이름
				r.setApplicationName(jsrow.get("applicationName").toString());
				//추가필드
				r.setExtraName(jsrow.get("extraName").toString());
				//호스트 이름
				r.setHostName(jsrow.get("hostName").toString());
				//접근 IP 주소
				r.setAccessAddress(jsrow.get("accessAddress").toString());
				//IP주소 마스크
				r.setAccessAddressMask(jsrow.get("accessAddressMask").toString());
				//접근 MAC 주소
				r.setAccessMacAddress(jsrow.get("accessMacAddress").toString());
				
				String startDateTime = jsrow.get("startDateTime").toString()+"000000";
				startDateTime =  startDateTime.replace("-","");
				String endDateTime = jsrow.get("endDateTime").toString()+"000000";
				endDateTime =  endDateTime.replace("-","");
				// 기간
				r.setStartDateTime(startDateTime);
				r.setEndDateTime(endDateTime);
				
				String startTime = jsrow.get("startTime").toString()+"00";
				startTime =  startTime.replace(":","");
				String endTime = jsrow.get("endTime").toString()+"00";
				endTime =  endTime.replace(":","");
				//시간
				r.setStartTime(startTime);
				r.setEndTime(endTime);
				
				//OS 사용자
				r.setOsLoginId(jsrow.get("osLoginId").toString());
				//초
				r.setMassiveTimeInterval(Integer.parseInt(jsrow.get("massiveTimeInterval").toString()));
				//대량작업임계건수
				r.setMassiveThreshold(Integer.parseInt(jsrow.get("massiveThreshold").toString()));
				//규칙만족할때 접근허용-Y 접근거부-N
				r.setWhitelistYesNo(jsrow.get("whitelistYesNo").toString());
				
				int workDay = 0;
				String workday = jsrow.get("workDay").toString();
				String data[] = workday.split(",");
		        for(int j=0 ; j<data.length ; j++)
		        {
		            workDay += data[j].equals("월")?SystemCode.Weekday.MONDAY : 0;
		            workDay += data[j].equals("화")?SystemCode.Weekday.TUESDAY : 0;
		            workDay += data[j].equals("수")?SystemCode.Weekday.WEDNESDAY : 0;
		            workDay += data[j].equals("목")?SystemCode.Weekday.THURSDAY : 0;
		            workDay += data[j].equals("금")?SystemCode.Weekday.FRIDAY : 0;
		            workDay += data[j].equals("토")?SystemCode.Weekday.SATURDAY : 0;
		            workDay += data[j].equals("일")?SystemCode.Weekday.SUNDAY : 0;
		        }
				r.setWorkDay(workDay);
				param3.add(r.toJSONString());
			}
			
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			result = sic.insertProfileProtection(restIp, restPort, strTocken, loginId, entityId, param1, param2, param3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	
	
	/**
	 * 보호 정책을 수정한다.
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSecurityPolicy.do")
	public @ResponseBody Map<String, Object> updateSecurityPolicy(HttpServletRequest request) {
		SecurityPolicyServiceCall sic = new SecurityPolicyServiceCall();
		JSONArray cryptoKey = new JSONArray();
		Map<String, Object> result = null;
		try {		
			ProfileProtection param1 = new ProfileProtection();
			param1.setProfileUid(request.getParameter("profileUid"));
			//보호정책 이름
			param1.setProfileName(request.getParameter("profileName")); 
			//보호 정책 설명
			param1.setProfileNote(request.getParameter("profilenote"));
			//데이터타입
			param1.setDataTypeCode(request.getParameter("dataTypeCode"));
			param1.setBase64YesNo("Y");	
			//이중 암호화 방지
			param1.setPreventDoubleYesNo(request.getParameter("preventDoubleYesNo")==null ? "N" : request.getParameter("preventDoubleYesNo"));
			//Null 암호화
			param1.setNullEncryptYesNo(request.getParameter("nullEncryptYesNo")==null ? "N" : request.getParameter("nullEncryptYesNo"));
			//접근거부시처리
			param1.setDenyResultTypeCode(request.getParameter("denyResultTypeCode"));
			//대체문자열
			param1.setMaskingValue(request.getParameter("maskingValue"));
			
			long optionBits = 0;
			
			if(request.getParameter("log_on_fail")==null){
				//실패 로그기록 체크가 아니면
				optionBits |= SystemCode.BitMask.POLICY_OPT_NO_AUDIT_LOG_ON_FAIL;
			}
			if(request.getParameter("log_on_success")==null){
				//성공로그기록 체크가 아니면 
				optionBits |= SystemCode.BitMask.POLICY_OPT_NO_AUDIT_LOG_ON_SUCCESS;
			}
			if(request.getParameter("compress_audit_log")!=null){
				//로그압축 체크이면
				optionBits |= SystemCode.BitMask.POLICY_OPT_COMPRESS_AUDIT_LOG;
			}
			param1.setOptionBits(optionBits);
			
			boolean defaultAccessAllowTrueFalse = true;
			if(request.getParameter("defaultAccessAllowTrueFalse").equals("N")){
				defaultAccessAllowTrueFalse = false;
			}
			param1.setDefaultAccessAllowTrueFalse(defaultAccessAllowTrueFalse);
			
			/*암호화정책*/
			List param2 = new ArrayList();
			ProfileCipherSpec p = new ProfileCipherSpec();
			String strRows = request.getParameter("securityPolicy").toString().replaceAll("&quot;", "\"");
			JSONArray rows = (JSONArray) new JSONParser().parse(strRows);
			for (int i = 0; i < rows.size(); i++) {
				JSONObject jsrow = (JSONObject) rows.get(i);
				p.setSpecIndex(i+1);
				//암호화 알고리즘 코드
				String getCipherAlgorithmCode = jsrow.get("cipherAlgorithmCode").toString();
				String cipherAlgorithmCode="";
				if(getCipherAlgorithmCode.equals("SEED-128")){
					cipherAlgorithmCode = "CAD1";
				}
				if(getCipherAlgorithmCode.equals("ARIA-128")){
					cipherAlgorithmCode = "CAR1";
				}
				if(getCipherAlgorithmCode.equals("ARIA-192")){
					cipherAlgorithmCode = "CAR2";
				}
				if(getCipherAlgorithmCode.equals("ARIA-256")){
					cipherAlgorithmCode = "CAR3";
				}
				if(getCipherAlgorithmCode.equals("AES-128")){
					cipherAlgorithmCode = "CAA1";
				}
				if(getCipherAlgorithmCode.equals("AES-192")){
					cipherAlgorithmCode = "CAA2";
				}
				if(getCipherAlgorithmCode.equals("AES-256")){
					cipherAlgorithmCode = "CAA3";
				}
				if(getCipherAlgorithmCode.equals("LPE-NUM")){
					cipherAlgorithmCode = "CALN";
				}
				
				p.setCipherAlgorithmCode(cipherAlgorithmCode);
				
				//운영모드코드
				String getOperationModeCode = jsrow.get("operationModeCode").toString();
				String operationModeCode="";
				if(getOperationModeCode.equals("CBC")){
					operationModeCode = "OMCB";
				}
				if(getOperationModeCode.equals("CTR")){
					operationModeCode = "OMCT";
				}
				p.setOperationModeCode(operationModeCode);
				
				//초기벡터코드
				String getInitialVectorTypeCode = jsrow.get("initialVectorTypeCode").toString();
				String initialVectorTypeCode = "";
				if(getInitialVectorTypeCode.equals("FIXED")){
					initialVectorTypeCode = "IVFX";
				}
				if(getInitialVectorTypeCode.equals("RANDOM")){
					initialVectorTypeCode = "IVRD";
				}
				p.setInitialVectorTypeCode(initialVectorTypeCode);
				
				//offset
				p.setOffset(Integer.parseInt(jsrow.get("offset").toString()));
				
				if(jsrow.get("length").toString().equals("끝까지")){
					p.setLength(null); //길이
				}else{
					p.setLength(Integer.parseInt(jsrow.get("length").toString())); //길이
				}
				
				HttpSession session = request.getSession();
				String restIp = (String)session.getAttribute("restIp");
				int restPort = (int)session.getAttribute("restPort");
				String strTocken = (String)session.getAttribute("tockenValue");
				String loginId = (String)session.getAttribute("usr_id");
				String entityId = (String)session.getAttribute("ectityUid");
				
				cryptoKey = sic.selectCryptoKeySymmetricList(restIp, restPort, strTocken, loginId, entityId);
				String getbinuid = jsrow.get("binUid").toString(); //키이름
				String binuid = "";
				for(int j=0; j<cryptoKey.size(); j++){
					JSONObject data = (JSONObject) cryptoKey.get(j);
					if(getbinuid.equals(data.get("resourceName"))){
						binuid=(String) data.get("getBinUid");
					}
				}
				p.setBinUid(binuid);
				param2.add(p.toJSONString());
			}

			/*접근제어 정책*/
			List param3 = new ArrayList();
			ProfileAclSpec r = new ProfileAclSpec();
			
			strRows = request.getParameter("accessPolicy").toString().replaceAll("&quot;", "\"");
			rows = (JSONArray) new JSONParser().parse(strRows);
			for (int i = 0; i < rows.size(); i++) {
				JSONObject jsrow = (JSONObject) rows.get(i);

				r.setSpecIndex(i+1); //row
				r.setSpecOrder(i+1); //row
				r.setSpecName(jsrow.get("specName").toString());
				//서버 인스턴스
				r.setServerInstanceId(jsrow.get("serverInstanceId").toString());
				//DB 사용자
				r.setServerLoginId(jsrow.get("serverLoginId").toString());
				//experdb 사용자
				r.setAdminLoginId(jsrow.get("adminLoginId").toString());
				//프로그램 이름
				r.setApplicationName(jsrow.get("applicationName").toString());
				//추가필드
				r.setExtraName(jsrow.get("extraName").toString());
				//호스트 이름
				r.setHostName(jsrow.get("hostName").toString());
				//접근 IP 주소
				r.setAccessAddress(jsrow.get("accessAddress").toString());
				//IP주소 마스크
				r.setAccessAddressMask(jsrow.get("accessAddressMask").toString());
				//접근 MAC 주소
				r.setAccessMacAddress(jsrow.get("accessMacAddress").toString());
				
				String startDateTime = jsrow.get("startDateTime").toString()+"000000";
				startDateTime =  startDateTime.replace("-","");
				String endDateTime = jsrow.get("endDateTime").toString()+"000000";
				endDateTime =  endDateTime.replace("-","");
				// 기간
				r.setStartDateTime(startDateTime);
				r.setEndDateTime(endDateTime);
				
				String startTime = jsrow.get("startTime").toString()+"00";
				startTime =  startTime.replace(":","");
				String endTime = jsrow.get("endTime").toString()+"00";
				endTime =  endTime.replace(":","");
				//시간
				r.setStartTime(startTime);
				r.setEndTime(endTime);
				//OS 사용자
				r.setOsLoginId(jsrow.get("osLoginId").toString());
				//초
				r.setMassiveTimeInterval(Integer.parseInt(jsrow.get("massiveTimeInterval").toString()));
				//대량작업임계건수
				r.setMassiveThreshold(Integer.parseInt(jsrow.get("massiveThreshold").toString()));
				//규칙만족할때 접근허용-Y 접근거부-N
				r.setWhitelistYesNo(jsrow.get("whitelistYesNo").toString());
				
				int workDay = 0;
				String workday = jsrow.get("workDay").toString();
				String data[] = workday.split(",");
		        for(int j=0 ; j<data.length ; j++)
		        {
		            workDay += data[j].equals("월")?SystemCode.Weekday.MONDAY : 0;
		            workDay += data[j].equals("화")?SystemCode.Weekday.TUESDAY : 0;
		            workDay += data[j].equals("수")?SystemCode.Weekday.WEDNESDAY : 0;
		            workDay += data[j].equals("목")?SystemCode.Weekday.THURSDAY : 0;
		            workDay += data[j].equals("금")?SystemCode.Weekday.FRIDAY : 0;
		            workDay += data[j].equals("토")?SystemCode.Weekday.SATURDAY : 0;
		            workDay += data[j].equals("일")?SystemCode.Weekday.SUNDAY : 0;
		        }
				r.setWorkDay(workDay);
				param3.add(r.toJSONString());
			}
			
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");
			
			result= sic.updateProfileProtection(restIp, restPort, strTocken,loginId, entityId, param1, param2, param3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 보호 정책을 삭제한다.
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value = "/deleteSecurityPolicy.do")
	public @ResponseBody JSONObject deleteSecurityPolicy(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		SecurityPolicyServiceCall sic = new SecurityPolicyServiceCall();
		try{
			HttpSession session = request.getSession();
			String restIp = (String)session.getAttribute("restIp");
			int restPort = (int)session.getAttribute("restPort");
			String strTocken = (String)session.getAttribute("tockenValue");
			String loginId = (String)session.getAttribute("usr_id");
			String entityId = (String)session.getAttribute("ectityUid");	
			
			String strProfileUid = request.getParameter("profileUid");
			ProfileProtection param = new ProfileProtection();
			param.setProfileUid(strProfileUid);		
			result= sic.deleteProfileProtection(restIp, restPort, strTocken,loginId,entityId, param);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
