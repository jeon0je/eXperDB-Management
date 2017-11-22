package com.k4m.dx.tcontrol.cmmn.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * agent 기능
 * 
 * 1. Database List 조회(dxT001)
 * 2. table List 조회(dxT002)
 * 3. 서버연결 Test(dxT003)
 * 4. Connector 연결 Test(dxT004)
 * 5. 백업실행(dxT005)
 * 6. DB접근제어 CRUD(dxT006)
 * 7. 감사로그설정(dxT007)
 * 8. 감사로그조회(dxT008)
 * 9. Agent Monitoring(dxT009)
 * 10. Extention 설치 유무 조회(dxT010)
 * 11. role name 조회(dxT011)
 * 12. All 스키마 태이블 조회
 * 13. bottledWater 실행/종료
 * 14. Kafka-connector connect 등록/수정/삭제/조회
 * 15. 감사로그 파일 리스트 조회 / 내용보기
 * 16. 디렉터리 존재유무 체크
 * 17. tbl_mapps 등록/삭제/조회
 * 18. kafka_con_config 등록/삭제
 * 19. Hostname 조회
 * 
 * @author thpark
 *
 */
public class ClientTester {
	
	public static void main(String[] args) {
		
		ClientTester clientTester = new ClientTester();
		
		String Ip = "222.110.153.162";
		//Ip = "222.110.153.251";
		 //	Ip = "127.0.0.1";
		 Ip = "222.110.153.231";
		int port = 9001;
		try {
			
			//clientTester.dxT001(Ip, port);
			//clientTester.dxT002(Ip, port);
			//clientTester.dxT003(Ip, port);
			//clientTester.dxT004(Ip, port);
			clientTester.dxT005(Ip, port);
			//clientTester.dxT006_C(Ip, port);
//			clientTester.dxT006_R(Ip, port);
			//clientTester.dxT006_U(Ip, port);
			//clientTester.dxT006_D(Ip, port);
			//clientTester.dxT007_C(Ip, port);
			//clientTester.dxT007_R(Ip, port);
			
			
			//clientTester.dxT010(Ip, port);
			//clientTester.dxT011(Ip, port);
			//clientTester.dxT012(Ip, port);
			
			//clientTester.dxT013(Ip, port);

			//clientTester.dxT014_R(Ip, port);
			//clientTester.dxT014_C(Ip, port);
			//clientTester.dxT014_U(Ip, port);
			//clientTester.dxT014_D(Ip, port);
			
			//clientTester.dxT015_R(Ip, port);
			//clientTester.dxT015_V(Ip, port);
			//clientTester.dxT015_DL(Ip, port);
			//clientTester.dxT016(Ip, port);
			//clientTester.dxT017_insert(Ip, port);
			//clientTester.dxT017_delete(Ip, port);
			//clientTester.dxT017_select(Ip, port);
			//clientTester.dxT018_insert(Ip, port);
			//clientTester.dxT018_delete(Ip, port);
			//clientTester.dxT019(Ip, port);
			//clientTester.dxT020(Ip, port);
			//clientTester.dxT021(Ip, port);
			//clientTester.dxT022(Ip, port);
			
			//clientTester.test();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT001(String Ip, int port) {
		try {


			JSONObject serverObj = new JSONObject();
			
			/**
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
		**/
			
		
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162_6432");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
		
			
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT001(ClientTranCodeType.DxT001, serverObj);
			
			String _tran_err_msg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			
			List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
			
			System.out.println("strDxExCode : " + " " + strDxExCode);
			
			for(int i=0; i<selectDBList.size(); i++) {
				
				Object obj = selectDBList.get(i);
				
				HashMap hp = (HashMap) obj;
				String datname = (String) hp.get("datname");

				System.out.println(i + " " + datname);

			}
				
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT002(String Ip, int port) {
		try {


			JSONObject serverObj = new JSONObject();
			
/*			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "5432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "ibizspt");
			serverObj.put(ClientProtocolID.USER_ID, "pgmon");
			serverObj.put(ClientProtocolID.USER_PWD, "pgmon");*/
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
			
			String strSchema = "tcontrol";
				
			objList = CA.dxT002(ClientTranCodeType.DxT002, serverObj, strSchema);
			
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			
			List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
			
			System.out.println("strDxExCode : " + " " + strDxExCode);
			System.out.println("strResultCode : " + " " + strResultCode);
			System.out.println("strErrCode : " + " " + strErrCode);
			System.out.println("strErrMsg : " + " " + strErrMsg);
			
			for(int i=0; i<selectDBList.size(); i++) {
				
				Object obj = selectDBList.get(i);

				HashMap hp = (HashMap) obj;
				String table_schema = (String) hp.get("table_schema");
				String table_name = (String) hp.get("table_name");

				System.out.println(i + " " + table_schema + " " + table_name);

			}
				
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT003(String Ip, int port) {
		try {
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
			
			objList = CA.dxT003(ClientTranCodeType.DxT003, serverObj);
			
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			
			System.out.println("strDxExCode : " + " " + strDxExCode);
			System.out.println("strResultCode : " + " " + strResultCode);
			System.out.println("strErrCode : " + " " + strErrCode);
			System.out.println("strErrMsg : " + " " + strErrMsg);
			
			System.out.println("RESULT_DATA : " + " " + objList.get(ClientProtocolID.RESULT_DATA));
			
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT004(String Ip, int port) {
		try {
			JSONArray arrDBInfo = new JSONArray();
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				//CA.dxT001("dxT001", arrDBInfo);
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT005(String Ip, int port) {
		try {
			
/*			String[] CMD =
				{
					"pg_dump --file=cats_tmp_acquire.sql --table=ibizspt.cats_tmp_acquire ibizspt",
					"pg_dump --file=cats_tmp_acquire_kb_owner.sql --table=ibizspt.cats_tmp_acquire_kb_owner ibizspt",
					"pg_dump --file=cats_tmp_cardinfo.sql --table=ibizspt.cats_tmp_cardinfo ibizspt",
					"pg_dump --file=cats_tmp_cardinfo.sql --table=ibizspt.cats_tmp_cardinfo ibizsptss",
					"pg_dump --file=pdt_corppdt.sql --table=ibizspt.pdt_corppdt ibizspt"
				};*/
			
			String[] CMD = {
					"java -version",
					"java -version"
				};
			
			JSONObject reqJObj = new JSONObject();
			
			JSONArray arrCmd = new JSONArray();
			
			JSONObject objJob_01 = new JSONObject();
			objJob_01.put(ClientProtocolID.SCD_ID, "1"); //스캐쥴ID
			objJob_01.put(ClientProtocolID.WORK_ID, "1"); //작업ID
			objJob_01.put(ClientProtocolID.EXD_ORD, "1"); //실행순서
			objJob_01.put(ClientProtocolID.NXT_EXD_YN, "N"); //다음실행여부
			objJob_01.put(ClientProtocolID.REQ_CMD, CMD[0]);
			objJob_01.put(ClientProtocolID.BCK_OPT_CD, "213");
			objJob_01.put(ClientProtocolID.DB_ID, 1);
			objJob_01.put(ClientProtocolID.BCK_FILE_PTH, "SASD");
			objJob_01.put(ClientProtocolID.BCK_FILENM, "ASD");
			objJob_01.put(ClientProtocolID.LOG_YN, "Y");
			
			JSONObject objJob_02 = new JSONObject();
			objJob_02.put(ClientProtocolID.SCD_ID, ""); //스캐쥴ID
			objJob_02.put(ClientProtocolID.WORK_ID, ""); //작업ID
			objJob_02.put(ClientProtocolID.EXD_ORD, ""); //실행순서
			objJob_02.put(ClientProtocolID.NXT_EXD_YN, ""); //다음실행여부
			objJob_02.put(ClientProtocolID.REQ_CMD, CMD[0]);
			objJob_02.put(ClientProtocolID.BCK_OPT_CD, "213");
			objJob_02.put(ClientProtocolID.DB_ID, "");
			objJob_02.put(ClientProtocolID.BCK_FILE_PTH, "");
			objJob_02.put(ClientProtocolID.LOG_YN, "Y");
			objJob_02.put(ClientProtocolID.BCK_FILENM, "");
			
			JSONObject objJob_03 = new JSONObject();
			objJob_03.put(ClientProtocolID.SCD_ID, ""); //스캐쥴ID
			objJob_03.put(ClientProtocolID.WORK_ID, ""); //작업ID
			objJob_03.put(ClientProtocolID.EXD_ORD, ""); //실행순서
			objJob_03.put(ClientProtocolID.NXT_EXD_YN, ""); //다음실행여부
			objJob_03.put(ClientProtocolID.REQ_CMD, CMD[0]); //명령어
			objJob_03.put(ClientProtocolID.BCK_OPT_CD, "");
			objJob_03.put(ClientProtocolID.DB_ID, "");
			objJob_03.put(ClientProtocolID.BCK_FILE_PTH, "");
			objJob_03.put(ClientProtocolID.LOG_YN, "Y");
			objJob_03.put(ClientProtocolID.BCK_FILENM, "");
			
			arrCmd.add(0, objJob_01);
			//arrCmd.add(1, objJob_02);
			//arrCmd.add(2, objJob_03);

			JSONObject serverObj = new JSONObject();
			
			//serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			//serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			//serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			
			reqJObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT005);
			reqJObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			reqJObj.put(ClientProtocolID.ARR_CMD, arrCmd);
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				CA.dxT005(reqJObj);
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT006_C(String Ip, int port) {
		try {
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject objList;

			
			JSONObject acObj = new JSONObject();
			acObj.put(ClientProtocolID.AC_SET, "1");
			acObj.put(ClientProtocolID.AC_TYPE, "host");
			acObj.put(ClientProtocolID.AC_DATABASE, "experdba");
			acObj.put(ClientProtocolID.AC_USER, "experdba");
			acObj.put(ClientProtocolID.AC_IP, "222.110.153.254");
			acObj.put(ClientProtocolID.AC_IPMASK, "222.110.153.254");
			acObj.put(ClientProtocolID.AC_METHOD, "trust");
			acObj.put(ClientProtocolID.AC_OPTION, "");

			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT006);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_C);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.ACCESS_CONTROL_INFO, acObj);
			
			objList = CA.dxT006(ClientTranCodeType.DxT006, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			CA.close();
			
			ClientTester clientTester = new ClientTester();
			clientTester.dxT006_R(Ip, port);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	private void dxT006_R(String Ip, int port) {
		try {
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject objList;

			
			JSONObject acObj = new JSONObject();
			acObj.put(ClientProtocolID.AC_SET, "1");
			acObj.put(ClientProtocolID.AC_TYPE, "host");
			acObj.put(ClientProtocolID.AC_DATABASE, "experdba");
			acObj.put(ClientProtocolID.AC_USER, "experdba");
			acObj.put(ClientProtocolID.AC_IP, "222.110.153.254");
			acObj.put(ClientProtocolID.AC_IPMASK, "222.110.153.254");
			acObj.put(ClientProtocolID.AC_METHOD, "trust");
			acObj.put(ClientProtocolID.AC_OPTION, "");

			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT006);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_R);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.ACCESS_CONTROL_INFO, acObj);
			
			objList = CA.dxT006(ClientTranCodeType.DxT006, jObj);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
			
			for(int i=0; i<selectDBList.size()-1; i++) {
				JSONObject mp = (JSONObject) selectDBList.get(i);
				
				System.out.println("seq : " +  mp.get(ClientProtocolID.AC_SEQ)
				                + " Set : " +  mp.get(ClientProtocolID.AC_SET)
				                + " Type : " +  mp.get(ClientProtocolID.AC_TYPE)
								+ " Database : " +  mp.get(ClientProtocolID.AC_DATABASE)
								+ " User : " +  mp.get(ClientProtocolID.AC_USER)
								+ " Ip : " +  mp.get(ClientProtocolID.AC_IP)
								+ " Ipmask : " +  mp.get(ClientProtocolID.AC_IPMASK)
								+ " Method : " +  mp.get(ClientProtocolID.AC_METHOD)
								+ " Option : " +  mp.get(ClientProtocolID.AC_OPTION));

			}
			
			CA.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT006_U(String Ip, int port) {
		try {
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject objList;

			
			JSONObject acObj = new JSONObject();
			acObj.put(ClientProtocolID.AC_SEQ, "5");
			acObj.put(ClientProtocolID.AC_SET, "1");
			acObj.put(ClientProtocolID.AC_TYPE, "host");
			acObj.put(ClientProtocolID.AC_DATABASE, "experdba");
			acObj.put(ClientProtocolID.AC_USER, "experdba");
			acObj.put(ClientProtocolID.AC_IP, "222.110.153.254");
			acObj.put(ClientProtocolID.AC_IPMASK, "222.110.153.253");
			acObj.put(ClientProtocolID.AC_METHOD, "trust");
			acObj.put(ClientProtocolID.AC_OPTION, "");

			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT006);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_U);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.ACCESS_CONTROL_INFO, acObj);
			
			objList = CA.dxT006(ClientTranCodeType.DxT006, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			CA.close();
			
			ClientTester clientTester = new ClientTester();
			clientTester.dxT006_R(Ip, port);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT006_D(String Ip, int port) {
		try {
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject objList;

			HashMap<String, String> hpSeq = new HashMap<String, String>();
			hpSeq.put(ClientProtocolID.AC_SEQ, "5");
			
			HashMap<String, String> hpSeq2 = new HashMap<String, String>();
			hpSeq2.put(ClientProtocolID.AC_SEQ, "6");
			
			ArrayList arrSeq = new ArrayList();
			arrSeq.add(hpSeq);
			arrSeq.add(hpSeq2);
			
	
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT006);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_D);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.ARR_AC_SEQ, arrSeq);
			
			objList = CA.dxT006(ClientTranCodeType.DxT006, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			CA.close();
			
			ClientTester clientTester = new ClientTester();
			clientTester.dxT006_R(Ip, port);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 		감사로그활성화
	 * 
			로그종류 
				pgaudit.log - read, write, function, role, ddl, misc
			로그 수준 
				pgaudit.log level
				Debug,info,notice,warning,log

			로그 카탈로그 
				pgaudit.log catalog
				- on / off
			로그 Parameter
				pgaudit.log parameter
				- on / off
			로그 Relation
				pgaudit.log relation
				- on / off
			로그 statement
				pgaudit.log_statement_once
				- on / off
			Role
				pgaudit.role
	 * @param Ip
	 * @param port
	 */
	private void dxT007_C(String Ip, int port) {
		try {
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject objSettingInfo = new JSONObject();
			
			//로그종류 
			objSettingInfo.put(ClientProtocolID.AUDIT_USE_YN, "Y");
			objSettingInfo.put(ClientProtocolID.AUDIT_LOG, "ddl,write");
			objSettingInfo.put(ClientProtocolID.AUDIT_LEVEL, "LOG");
			objSettingInfo.put(ClientProtocolID.AUDIT_CATALOG, "off");
			objSettingInfo.put(ClientProtocolID.AUDIT_PARAMETER, "off");
			objSettingInfo.put(ClientProtocolID.AUDIT_RELATION, "off");
			objSettingInfo.put(ClientProtocolID.AUDIT_STATEMENT_ONCE, "off");
			objSettingInfo.put(ClientProtocolID.AUDIT_ROLE, "experdba,tcontrol");

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
			
			objList = CA.dxT007(ClientTranCodeType.DxT007, ClientProtocolID.COMMAND_CODE_C, serverObj, objSettingInfo);

			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void dxT007_R(String Ip, int port) {
		try {
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject objSettingInfo = new JSONObject();
			
			//로그종류 
			objSettingInfo.put(ClientProtocolID.AUDIT_USE_YN, "Y");
			objSettingInfo.put(ClientProtocolID.AUDIT_LOG, "ddl, write");
			objSettingInfo.put(ClientProtocolID.AUDIT_LEVEL, "info");
			objSettingInfo.put(ClientProtocolID.AUDIT_CATALOG, "off");
			objSettingInfo.put(ClientProtocolID.AUDIT_PARAMETER, "off");
			objSettingInfo.put(ClientProtocolID.AUDIT_RELATION, "off");
			objSettingInfo.put(ClientProtocolID.AUDIT_STATEMENT_ONCE, "off");
			objSettingInfo.put(ClientProtocolID.AUDIT_ROLE, "postgres");

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
			
			objList = CA.dxT007(ClientTranCodeType.DxT007, ClientProtocolID.COMMAND_CODE_R, serverObj, objSettingInfo);

			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			HashMap selectData =(HashMap) objList.get(ClientProtocolID.RESULT_DATA);
			
				
				System.out.println("log : " +  selectData.get("log")
				                + " log_level : " +  selectData.get("log_level")
				                + " log_relation : " +  selectData.get("log_relation")
								+ " role : " +  selectData.get("role")
								+ " log_catalog : " +  selectData.get("log_catalog")
								+ " log_parameter : " +  selectData.get("log_parameter")
								+ " log_statement_once : " +  selectData.get("log_statement_once")
								);

			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT008(String Ip, int port) {
		try {
			JSONArray arrDBInfo = new JSONArray();
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				//CA.dxT001("dxT001", arrDBInfo);
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT009(String Ip, int port) {
		try {
			JSONArray arrDBInfo = new JSONArray();
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				//CA.dxT001("dxT001", arrDBInfo);
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 확장 설치 리스트 조회
	 * @param Ip
	 * @param port
	 */
	private void dxT010(String Ip, int port) {
		try {
			JSONObject serverObj = new JSONObject();
			
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			String strExtname = "pgaudit";
			
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
			
			JSONObject objList;
			
			//strExtname = "pgaudit";
			objList = CA.dxT010(ClientTranCodeType.DxT010, serverObj, strExtname);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			
			List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
			
			if(selectDBList.size() > 0) {
				for(int i=0; i<selectDBList.size(); i++) {
					Object obj = selectDBList.get(i);
					
					HashMap hp = (HashMap) obj;
					String extname = (String) hp.get("extname");
	
					System.out.println(i + " " + extname);
				}
				
			}
			
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT011(String Ip, int port) {
		try {


			JSONObject serverObj = new JSONObject();
			
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			
			/**
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "5432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "pgmon");
			serverObj.put(ClientProtocolID.USER_ID, "pgmon");
			serverObj.put(ClientProtocolID.USER_PWD, "pgmon");
			**/
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT011(ClientTranCodeType.DxT011, serverObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
			
			System.out.println("strDxExCode : " + " " + strDxExCode);
			if(selectDBList.size() > 0) {
				for(int i=0; i<selectDBList.size(); i++) {
					
					Object obj = selectDBList.get(i);
					
					HashMap hp = (HashMap) obj;
					String rolname = (String) hp.get("rolname");
	
					System.out.println(i + " " + rolname);
	
				}
			}
				
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void dxT012(String Ip, int port) {
		try {


			JSONObject serverObj = new JSONObject();
			
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "postgres");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			
			/**
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "5432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "pgmon");
			serverObj.put(ClientProtocolID.USER_ID, "pgmon");
			serverObj.put(ClientProtocolID.USER_PWD, "pgmon");
			**/
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT011(ClientTranCodeType.DxT012, serverObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
			
			System.out.println("strDxExCode : " + " " + strDxExCode);
			if(selectDBList.size() > 0) {
				for(int i=0; i<selectDBList.size(); i++) {
					
					Object obj = selectDBList.get(i);
					
					HashMap hp = (HashMap) obj;
					String table_schema = (String) hp.get("table_schema");
					String table_name = (String) hp.get("table_name");
					System.out.println(i + " " + table_schema + " " + table_name);
	
				}
			}
				
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT013(String Ip, int port) {
		try {

			String strExecTxt = "java -version";

			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT013);
			jObj.put(ClientProtocolID.TRF_TRG_ID, "12");
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.RUN);
			jObj.put(ClientProtocolID.EXEC_TXT, strExecTxt);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT013(ClientTranCodeType.DxT013, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
		
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT013_delete_slot(String Ip, int port) {
		try {

			String strExecTxt = "slotid";

			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT013);
			jObj.put(ClientProtocolID.TRF_TRG_ID, "12");
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.SLOT);
			jObj.put(ClientProtocolID.EXEC_TXT, strExecTxt);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT013(ClientTranCodeType.DxT013, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
		
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * kafka connect 조회
	 * @param Ip
	 * @param port
	 */
	private void dxT014_R(String Ip, int port) {
		try {

			
			JSONObject serverObj = new JSONObject();
			
			String strServerIp = "222.110.153.201";
			String strServerPort = "8083";
			
			serverObj.put(ClientProtocolID.SERVER_IP, strServerIp);
			serverObj.put(ClientProtocolID.SERVER_PORT, strServerPort);
			
			
			//strName : 공백이면 전체 검색
			String strName = "";
			
			/**
			String strConnector_class = "io.confluent.connect.hdfs.HdfsSinkConnector";
			String strTasks_max = "3";
			String strTopics = "connect_test06.postgres.table1, connect_test06.postgres.table2";
			String strHdfs_url = "hdfs://KAFKA0:8020/dxm/warehouse/";
			String strHadoop_conf_dir = "/etc/kafka-connect-hdfs";
			String strHadoop_home = "/home/";
			String strFlush_size = "100";
			String strRotate_interval_ms = "1000";
			**/
			
			JSONObject connectorInfoObj = new JSONObject();
			
			connectorInfoObj.put(ClientProtocolID.CONNECTOR_NAME, strName);
			
			
			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT014);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_R);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.CONNECTOR_INFO, connectorInfoObj);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT014(ClientTranCodeType.DxT014, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
			
			System.out.println("strDxExCode : " + " " + strDxExCode);
			if(selectDBList.size() > 0) {
				for(int i=0; i<selectDBList.size(); i++) {
					
					Object obj = selectDBList.get(i);
					
					HashMap hp = (HashMap) obj;
					String name = (String) hp.get("name");
					String hdfs_url = (String) hp.get("hdfs.url");
					
					System.out.println(i + " " + hp );
					System.out.println(i + " name : " + name );
					System.out.println(i + " hdfs_url : " + hdfs_url );
	
				}
			}
				
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT014_C(String Ip, int port) {
		try {

			
			JSONObject serverObj = new JSONObject();
			
			String strServerIp = "222.110.153.201";
			String strServerPort = "8083";
			
			serverObj.put(ClientProtocolID.SERVER_IP, strServerIp);
			serverObj.put(ClientProtocolID.SERVER_PORT, strServerPort);
			

			String strName = "connect01";

			String strConnector_class = "io.confluent.connect.hdfs.HdfsSinkConnector";
			String strTasks_max = "3";
			String strTopics = "";
			String strHdfs_url = "hdfs://KAFKA0:8020/dxm/warehouse/";
			String strHadoop_conf_dir = "/etc/kafka-connect-hdfs";
			String strHadoop_home = "/home/";
			String strFlush_size = "100";
			String strRotate_interval_ms = "1000";

			
			JSONObject connectorInfoObj = new JSONObject();
			
			connectorInfoObj.put(ClientProtocolID.CONNECTOR_NAME, strName);
			connectorInfoObj.put(ClientProtocolID.CONNECTOR_CLASS, strConnector_class);
			connectorInfoObj.put(ClientProtocolID.TASK_MAX, strTasks_max);
			connectorInfoObj.put(ClientProtocolID.TOPIC, strTopics);
			connectorInfoObj.put(ClientProtocolID.HDFS_URL, strHdfs_url);
			connectorInfoObj.put(ClientProtocolID.HADOOP_CONF_DIR, strHadoop_conf_dir);
			connectorInfoObj.put(ClientProtocolID.HADOOP_HOOM, strHadoop_home);
			connectorInfoObj.put(ClientProtocolID.FLUSH_SIZE, strFlush_size);
			connectorInfoObj.put(ClientProtocolID.ROTATE_INTERVAL_MS, strRotate_interval_ms);
			
			
			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT014);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_C);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.CONNECTOR_INFO, connectorInfoObj);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT014(ClientTranCodeType.DxT014, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
		
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT014_U(String Ip, int port) {
		try {

			
			JSONObject serverObj = new JSONObject();
			
			String strServerIp = "222.110.153.201";
			String strServerPort = "8083";
			
			serverObj.put(ClientProtocolID.SERVER_IP, strServerIp);
			serverObj.put(ClientProtocolID.SERVER_PORT, strServerPort);
			

			String strName = "connect01";

			String strConnector_class = "io.confluent.connect.hdfs.HdfsSinkConnector";
			String strTasks_max = "3";
			String strTopics = "connect_test06.postgres.table1, connect_test06.postgres.table2";
			String strHdfs_url = "hdfs://KAFKA0:8020/dxm/warehouse/";
			String strHadoop_conf_dir = "/etc/kafka-connect-hdfs";
			String strHadoop_home = "/home/";
			String strFlush_size = "200";
			String strRotate_interval_ms = "1000";

			
			JSONObject connectorInfoObj = new JSONObject();
			
			connectorInfoObj.put(ClientProtocolID.CONNECTOR_NAME, strName);
			connectorInfoObj.put(ClientProtocolID.CONNECTOR_CLASS, strConnector_class);
			connectorInfoObj.put(ClientProtocolID.TASK_MAX, strTasks_max);
			connectorInfoObj.put(ClientProtocolID.TOPIC, strTopics);
			connectorInfoObj.put(ClientProtocolID.HDFS_URL, strHdfs_url);
			connectorInfoObj.put(ClientProtocolID.HADOOP_CONF_DIR, strHadoop_conf_dir);
			connectorInfoObj.put(ClientProtocolID.HADOOP_HOOM, strHadoop_home);
			connectorInfoObj.put(ClientProtocolID.FLUSH_SIZE, strFlush_size);
			connectorInfoObj.put(ClientProtocolID.ROTATE_INTERVAL_MS, strRotate_interval_ms);
			
			
			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT014);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_U);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.CONNECTOR_INFO, connectorInfoObj);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT014(ClientTranCodeType.DxT014, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
		
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT014_D(String Ip, int port) {
		try {

			
			JSONObject serverObj = new JSONObject();
			
			String strServerIp = "222.110.153.201";
			String strServerPort = "8083";
			
			serverObj.put(ClientProtocolID.SERVER_IP, strServerIp);
			serverObj.put(ClientProtocolID.SERVER_PORT, strServerPort);
			

			String strName = "connect01";

			
			JSONObject connectorInfoObj = new JSONObject();
			
			connectorInfoObj.put(ClientProtocolID.CONNECTOR_NAME, strName);
			
			
			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT014);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_D);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.CONNECTOR_INFO, connectorInfoObj);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT014(ClientTranCodeType.DxT014, jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
		
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT015_R(String Ip, int port) {
		try {

			String strDirectory = "/home/devel/experdb/data/pg_log/";
			
			strDirectory = "C:\\k4m\\01-1. DX 제폼개발\\04. 시험\\pg_log\\";
			
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, Ip);
			serverObj.put(ClientProtocolID.SERVER_IP, Integer.toString(port));
			
			String strStartDate = "2017-07-25";
			String strEndDate = "2017-07-26";
				
			JSONObject searchInfoObj = new JSONObject();
			searchInfoObj.put(ClientProtocolID.START_DATE, strStartDate);
			searchInfoObj.put(ClientProtocolID.END_DATE, strEndDate);
			
			JSONObject jObj = new JSONObject();
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT015);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_R);
			jObj.put(ClientProtocolID.FILE_DIRECTORY, strDirectory);
			jObj.put(ClientProtocolID.SEARCH_INFO, searchInfoObj);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT015(jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			List<HashMap<String, String>> fileList = (List<HashMap<String, String>>) objList.get(ClientProtocolID.RESULT_DATA);
			
			int i = 0;
			for(HashMap<String, String> hp:fileList) {
				i++;
				String strFileName = (String) hp.get(ClientProtocolID.FILE_NAME);
				String strFileSize = (String) hp.get(ClientProtocolID.FILE_SIZE);
				String strFileLastModified = (String) hp.get(ClientProtocolID.FILE_LASTMODIFIED);
				
				System.out.println(i + "|" + strFileName + "|" + strFileSize + "|" + strFileLastModified);
			}
		
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT015_V(String Ip, int port) {
		try {

			String strDirectory = "/home/devel/experdb/data/pg_log/";
			//strDirectory = "C:\\k4m\\01-1. DX 제폼개발\\04. 시험\\pg_log\\";
			
			String strFileName = "postgresql-2017-07-28_101101.log";
			//strFileName = "postgresql-2017-07-31_000000.log";
			
			//strDirectory = "C:\\k4m\\01-1. DX 제폼개발\\04. 시험\\pg_log\\";
			
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, Ip);
			serverObj.put(ClientProtocolID.SERVER_IP, Ip);
			serverObj.put(ClientProtocolID.SERVER_PORT, Integer.toString(port));
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT015);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_V);
			jObj.put(ClientProtocolID.FILE_DIRECTORY, strDirectory);
			jObj.put(ClientProtocolID.FILE_NAME, strFileName);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
				
			objList = CA.dxT015_V(jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			String strLogView = (String)objList.get(ClientProtocolID.RESULT_DATA);
			
			System.out.println("###############");
			
			//System.out.println(strLogView);
			
			System.out.println("###############");
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT015_DL(String Ip, int port) {
		try {
			
			Ip = "222.110.153.162";

			String strDirectory = "/home/devel/experdb/data/pg_log/";
			//strDirectory = "C:\\k4m\\01-1. DX 제폼개발\\04. 시험\\pg_log\\";
			
			String strFileName = "postgresql-2017-08-01_000000.log";
			//strFileName = "postgresql-2017-07-31_000000.log";
			
			//strDirectory = "C:\\k4m\\01-1. DX 제폼개발\\04. 시험\\pg_log\\";
			
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, Ip);
			serverObj.put(ClientProtocolID.SERVER_IP, Ip);
			serverObj.put(ClientProtocolID.SERVER_PORT, Integer.toString(port));
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT015_DL);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_DL);
			jObj.put(ClientProtocolID.FILE_DIRECTORY, strDirectory);
			jObj.put(ClientProtocolID.FILE_NAME, strFileName);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 
			
			HttpServletRequest request = null;
			HttpServletResponse response = null;
			CA.dxT015_DL(jObj, request, response);
			
				
			//CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT016(String Ip, int port) {
		try {
			

			String strDirectory = "/home/devel/experdb/data/pg_log/";
			strDirectory = "C:\\k4m\\01-1. DX 제폼개발\\04. 시험\\pg_log\\";
			
			JSONObject serverObj = new JSONObject();
			
			serverObj.put(ClientProtocolID.SERVER_NAME, Ip);
			serverObj.put(ClientProtocolID.SERVER_IP, Ip);
			serverObj.put(ClientProtocolID.SERVER_PORT, Integer.toString(port));
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT016);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			jObj.put(ClientProtocolID.FILE_DIRECTORY, strDirectory);

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT016(jObj);
			
			HashMap obj = (HashMap)objList.get(ClientProtocolID.RESULT_DATA);
			
			String checkDir = (String)obj.get(ClientProtocolID.IS_DIRECTORY);
			String capacity = (String)obj.get(ClientProtocolID.CAPACITY);
			
			System.out.println("디렉터리 존재 유무(0:1) : " + checkDir + " 용량 : " + capacity);
			
			CA.close();
			
				
			//CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT017_insert(String Ip, int port) {
		try {
			

			JSONObject serverObj = new JSONObject();
			
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT017);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_C);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			
			ArrayList<HashMap<String, String>> arrTableInfo = new ArrayList<HashMap<String, String>>();
			HashMap hp = new HashMap();
			hp.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			hp.put(ClientProtocolID.TABLE_NAME, "tttt");
			hp.put(ClientProtocolID.TABLE_SCHEMA, "public");
			hp.put(ClientProtocolID.TOPIC_NAME, "test2.tttt");
			hp.put(ClientProtocolID.REMARK, "1");
			
			arrTableInfo.add(hp);
			
			jObj.put(ClientProtocolID.TABLE_INFO, arrTableInfo);

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT017(jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			CA.close();
			
				
			//CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT017_select(String Ip, int port) {
		try {
			

			JSONObject serverObj = new JSONObject();
			
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT017);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_R);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			
			ArrayList<HashMap<String, String>> arrTableInfo = new ArrayList<HashMap<String, String>>();
			HashMap hp = new HashMap();
			hp.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			hp.put(ClientProtocolID.TABLE_NAME, "test_a");
			hp.put(ClientProtocolID.TABLE_SCHEMA, "public");

			
			arrTableInfo.add(hp);
			
			jObj.put(ClientProtocolID.TABLE_INFO, arrTableInfo);

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT017(jObj);
			
			CA.close();
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);

			List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
			
			for(int i=0; i<selectDBList.size(); i++) {
				
				Object obj = selectDBList.get(i);
				
				HashMap hpResult = (HashMap) obj;
				String database_name = (String) hpResult.get("database_name");
				String table_schema = (String) hpResult.get("table_schema");
				String table_name = (String) hpResult.get("table_name");
				long relnamespace = (long) hpResult.get("relnamespace");
				long reloid = (long) hpResult.get("reloid");

				System.out.println(i + " " + database_name);
				System.out.println(i + " " + table_schema);
				System.out.println(i + " " + table_name);
				System.out.println(i + " " + relnamespace);
				System.out.println(i + " " + reloid);

			}
			//CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void test ()  throws Exception{
		JSONParser parser=new JSONParser();
		
		String test = "{\"ERR_CODE\":\"\",\"DX_EX_CODE\":\"DxT017\",\"RESULT_DATA\":[{\"database_name\":\"test_db1\",\"reloid\":19461,\"remark\":\"\",\"table_name\":\"test_a\",\"topic_name\":\"test.test_a\",\"create_user\":\"experdba\",\"relnamespace\":2200,\"table_schema\":\"public\"}],\"ERR_MSG\":\"\",\"RESULT_CODE\":\"0\"}";
		
		JSONObject objList=(JSONObject)parser.parse(new String(test));
		
		List<Object> selectDBList =(ArrayList<Object>) objList.get(ClientProtocolID.RESULT_DATA);
		
		for(int i=0; i<selectDBList.size(); i++) {
			
			Object obj = selectDBList.get(i);
			
			HashMap hpResult = (HashMap) obj;
			String database_name = (String) hpResult.get("database_name");
			String table_schema = (String) hpResult.get("table_schema");
			String table_name = (String) hpResult.get("table_name");
			long relnamespace = (long) hpResult.get("relnamespace");
			long reloid = (long) hpResult.get("reloid");

			System.out.println(i + " " + database_name);
			System.out.println(i + " " + table_schema);
			System.out.println(i + " " + table_name);
			System.out.println(i + " " + relnamespace);
			System.out.println(i + " " + reloid);

		}
		
	}
	
	private void dxT017_delete(String Ip, int port) {
		try {
			

			JSONObject serverObj = new JSONObject();
			
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT017);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_D);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			
			ArrayList<HashMap<String, String>> arrTableInfo = new ArrayList<HashMap<String, String>>();
			HashMap hp = new HashMap();
			hp.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			hp.put(ClientProtocolID.TABLE_NAME, "tttt");
			hp.put(ClientProtocolID.TABLE_SCHEMA, "public");

			
			arrTableInfo.add(hp);
			
			jObj.put(ClientProtocolID.TABLE_INFO, arrTableInfo);

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT017(jObj);
			
			CA.close();
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);

				
			//CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT018_insert(String Ip, int port) {
		try {
			

			JSONObject serverObj = new JSONObject();
			
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT018);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_C);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			
			JSONObject tableInfoObj = new JSONObject();
			
			tableInfoObj.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			tableInfoObj.put(ClientProtocolID.CONNECT_NAME, "test2");
			tableInfoObj.put(ClientProtocolID.CONTENTS, "{\"status\":\"RUNNING\",\"name\":\"test\",\"connector.class\":\"io.confluent.connect.hdfs.HdfsSinkConnector\",\"hadoop.home\":\"hdfs://222.110.153.216:8020/dxm/warehouse/test\",\"topics\":\"test.public.test_a\",\"tasks.max\":\"3\",\"rotate.interval.ms\":\"3000\",\"hdfs.url\":\"hdfs://222.110.153.216:8020/dxm/warehouse/test\",\"hadoop.conf.dir\":\"/etc/kafka-connect-hdfs\",\"flush.size\":\"100\"}");
			tableInfoObj.put(ClientProtocolID.REMARK, "1");
			
			jObj.put(ClientProtocolID.TABLE_INFO, tableInfoObj);

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT018(jObj);
			CA.close();
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			

			
				
			//CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dxT018_delete(String Ip, int port) {
		try {
			

			JSONObject serverObj = new JSONObject();
			
			
			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.162");
			serverObj.put(ClientProtocolID.SERVER_PORT, "6432");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			serverObj.put(ClientProtocolID.USER_ID, "experdba");
			serverObj.put(ClientProtocolID.USER_PWD, "experdba");
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT018);
			jObj.put(ClientProtocolID.COMMAND_CODE, ClientProtocolID.COMMAND_CODE_D);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			
			JSONObject tableInfoObj = new JSONObject();
			
			tableInfoObj.put(ClientProtocolID.DATABASE_NAME, "test_db1");
			tableInfoObj.put(ClientProtocolID.CONNECT_NAME, "test2");

			
			jObj.put(ClientProtocolID.TABLE_INFO, tableInfoObj);

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT018(jObj);
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			CA.close();
			
				
			//CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT019(String Ip, int port) {
		try {
			
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT019);
			

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT019(jObj);
			
			CA.close();
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);

			String host = (String) objList.get(ClientProtocolID.RESULT_DATA);
			
			System.out.println("host : " + host);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT020(String Ip, int port) {
		try {
			
			JSONArray arrServerInfo = new JSONArray();

			JSONObject serverObj01 = new JSONObject();
			
			
			serverObj01.put(ClientProtocolID.SERVER_NAME, "222.110.153.251");
			serverObj01.put(ClientProtocolID.SERVER_IP, "222.110.153.251");
			serverObj01.put(ClientProtocolID.SERVER_PORT, "5433");
			serverObj01.put(ClientProtocolID.DATABASE_NAME, "experdb");
			serverObj01.put(ClientProtocolID.USER_ID, "experdb");
			serverObj01.put(ClientProtocolID.USER_PWD, "experdb");
			
			arrServerInfo.add(serverObj01);
			
			
			JSONObject serverObj02 = new JSONObject();
			
			
			serverObj02.put(ClientProtocolID.SERVER_NAME, "222.110.153.231");
			serverObj02.put(ClientProtocolID.SERVER_IP, "222.110.153.231");
			serverObj02.put(ClientProtocolID.SERVER_PORT, "1234");
			serverObj02.put(ClientProtocolID.DATABASE_NAME, "experdb");
			serverObj02.put(ClientProtocolID.USER_ID, "experdb");
			serverObj02.put(ClientProtocolID.USER_PWD, "experdb");
			
			arrServerInfo.add(serverObj02);
			
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT020);
			jObj.put(ClientProtocolID.ARR_SERVER_INFO, arrServerInfo);

			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT020(jObj);
			
			CA.close();
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			
			JSONArray arrResult = (JSONArray) objList.get(ClientProtocolID.RESULT_DATA);
			
			System.out.println("RESULT_CODE : " +  strResultCode);
			System.out.println("ERR_CODE : " +  strErrCode);
			System.out.println("ERR_MSG : " +  strErrMsg);
			
			if(strResultCode.equals("0")) {
				for(int i=0; i<arrResult.size(); i++) {
					
					JSONObject outObj = new JSONObject();
					outObj = (JSONObject) arrResult.get(i);
					
					String strServerIP = (String) outObj.get(ClientProtocolID.SERVER_IP);
					String strServerPort = (String) outObj.get(ClientProtocolID.SERVER_PORT);
					String strDatabaseName = (String) outObj.get(ClientProtocolID.DATABASE_NAME);
					String strMasterGbn = (String) outObj.get(ClientProtocolID.MASTER_GBN); 
					String strConnectYn = (String) outObj.get(ClientProtocolID.CONNECT_YN); 
					String strDBHostName = (String) outObj.get(ClientProtocolID.CMD_HOSTNAME); 
					
					System.out.println(strServerIP + " " + strServerPort + " " + strDatabaseName + " " 
					+ strMasterGbn + " " + strConnectYn + " " + strDBHostName);
					
				}
			}
			
			
			
				
			//CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT021(String Ip, int port) {
		try {


			JSONObject serverObj = new JSONObject();

			serverObj.put(ClientProtocolID.SERVER_NAME, "222.110.153.251");
			serverObj.put(ClientProtocolID.SERVER_IP, "222.110.153.251");
			serverObj.put(ClientProtocolID.SERVER_PORT, "5433");
			serverObj.put(ClientProtocolID.DATABASE_NAME, "experdb");
			serverObj.put(ClientProtocolID.USER_ID, "experdb");
			serverObj.put(ClientProtocolID.USER_PWD, "experdb");
		
			JSONObject jObj = new JSONObject();
			
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT021);
			jObj.put(ClientProtocolID.SERVER_INFO, serverObj);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT021(jObj);
			
			CA.close();
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);
			
			HashMap resultHp = (HashMap) objList.get(ClientProtocolID.RESULT_DATA);
			
			
			Iterator<String> keys = resultHp.keySet().iterator();

	        while( keys.hasNext() ){
	            String key = keys.next();
	            System.out.println( String.format("키 : %s, 값 : %s", key, resultHp.get(key)) );
	        }
	
				
			CA.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void dxT022(String Ip, int port) {
		try {


			JSONObject serverObj = new JSONObject();

	
		
			JSONObject jObj = new JSONObject();
			
			String strPath = "/home/experdb/pgdata/bckDir/rman";
			
			jObj.put(ClientProtocolID.DX_EX_CODE, ClientTranCodeType.DxT022);

			jObj.put(ClientProtocolID.CMD_BACKUP_PATH, strPath);
			
			
			JSONObject objList;
			
			ClientAdapter CA = new ClientAdapter(Ip, port);
			CA.open(); 

			objList = CA.dxT022(jObj);
			
			CA.close();
			
			String strErrMsg = (String)objList.get(ClientProtocolID.ERR_MSG);
			String strErrCode = (String)objList.get(ClientProtocolID.ERR_CODE);
			String strDxExCode = (String)objList.get(ClientProtocolID.DX_EX_CODE);
			String strResultCode = (String)objList.get(ClientProtocolID.RESULT_CODE);

			System.out.println("Result : " + strResultCode);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
