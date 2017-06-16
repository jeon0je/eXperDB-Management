package com.k4m.dx.tcontrol.backup.web;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.k4m.dx.tcontrol.backup.service.BackupService;
import com.k4m.dx.tcontrol.backup.service.DbVO;
import com.k4m.dx.tcontrol.backup.service.WorkLogVO;
import com.k4m.dx.tcontrol.backup.service.WorkOptDetailVO;
import com.k4m.dx.tcontrol.backup.service.WorkOptVO;
import com.k4m.dx.tcontrol.backup.service.WorkVO;
import com.k4m.dx.tcontrol.common.service.CmmnCodeDtlService;
import com.k4m.dx.tcontrol.common.service.CmmnCodeVO;
import com.k4m.dx.tcontrol.common.service.PageVO;

@Controller
public class BackupController {
	@Autowired
	private BackupService backupService;
	
	@Autowired
	private CmmnCodeDtlService cmmnCodeDtlService;
	
	/**
	 * Rman백업 페이지를 반환한다.
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/backup/rmanList.do")
	public ModelAndView rmanList(@ModelAttribute("workVo") WorkVO workVO, ModelMap model) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("db_svr_id",workVO.getDb_svr_id());
		mv.setViewName("backup/rmanList");
		return mv;	
	}
	
	/**
	 * Dump백업 페이지를 반환한다.
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/backup/dumpList.do")
	public ModelAndView dumpList(@ModelAttribute("workVo") WorkVO workVO, ModelMap model) throws Exception {
		ModelAndView mv = new ModelAndView();

		List<DbVO> dbList = backupService.selectDbList(workVO);
		
		mv.addObject("dbList",dbList);
		mv.addObject("db_svr_id",workVO.getDb_svr_id());
		mv.setViewName("backup/dumpList");
		return mv;
	}
	
	/**
	 * Rman백업목록을 조회한다.
	 * @param WorkVO
	 * @return List<WorkVO>
	 * @throws Exception
	 */
	@RequestMapping(value="/backup/workList.do")
	@ResponseBody
	public List<WorkVO> rmanDataList(@ModelAttribute("workVo") WorkVO workVO) throws Exception{
		List<WorkVO> resultSet = null;
		resultSet = backupService.selectWorkList(workVO);
		
		return resultSet;
	}
	
	/**
	 * 백업 Log목록을 조회한다.
	 * @param WorkVO
	 * @return List<WorkVO>
	 * @throws Exception
	 */
	@RequestMapping(value="/backup/selectWorkLogList.do")
	@ResponseBody
	public List<WorkLogVO> selectWorkLogList(@ModelAttribute("workLogVo") WorkLogVO workLogVO) throws Exception{
		List<WorkLogVO> resultSet = null;
		resultSet = backupService.selectWorkLogList(workLogVO);

		return resultSet;
	}
	
	/**
	 * Rman Log목록을 조회페이지를 반환한다.
	 * @param String db_svr_id
	 * @return mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/backup/rmanLogList.do")
	public ModelAndView rmanLogList(@RequestParam("db_svr_id") String db_svr_id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("db_svr_id",db_svr_id);
		mv.setViewName("backup/rmanLogList");
		return mv;	
	}
	
	/**
	 * Dump Log목록을 조회페이지를 반환한다.
	 * @param String db_svr_id
	 * @return mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/backup/dumpLogList.do")
	public ModelAndView dumpLogList(@ModelAttribute("workVo") WorkVO workVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		List<DbVO> dbList = backupService.selectDbList(workVO);
		mv.addObject("dbList",dbList);
		mv.addObject("db_svr_id",workVO.getDb_svr_id());
		mv.setViewName("backup/dumpLogList");
		return mv;	
	}

	
	/**
	 * Rman백업 등록팝업 페이지를 반환한다.
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/popup/rmanRegForm.do")
	public ModelAndView rmanRegForm(@ModelAttribute("workVo") WorkVO workVO, ModelMap model) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("db_svr_id",workVO.getDb_svr_id());
		mv.setViewName("popup/rmanRegForm");
		return mv;	
	}
	
	/**
	 * Dump백업 등록팝업 페이지를 반환한다.
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/dumpRegForm.do")
	public ModelAndView dumpRegForm(@ModelAttribute("workVo") WorkVO workVO, ModelMap model) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dbList", backupService.selectDbList(workVO));
		
		// incoding code List
		try {
			PageVO pageVO = new PageVO();
			pageVO.setGrp_cd("TC0005");
			pageVO.setSearchCondition("0");
			List<CmmnCodeVO> cmmnCodeVO = cmmnCodeDtlService.cmmnDtlCodeSearch(pageVO);
			mv.addObject("incodeList",cmmnCodeVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("db_svr_id",workVO.getDb_svr_id());
		mv.setViewName("popup/dumpRegForm");
		return mv;	
	}
	
	/**
	 * Rman Work을 등록한다.
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/workRmanWrite.do")
	public void workRmanWrite(@ModelAttribute("workVO") WorkVO workVO, HttpServletResponse response) throws Exception{
		WorkVO resultSet = null;
		
		backupService.insertRmanWork(workVO);
		resultSet = backupService.lastWorkId();
		
		System.out.println("result:"+resultSet.getWrk_id());

		response.getWriter().println(resultSet.getWrk_id());
	}

	/**
	 * Dump Work을 등록한다.
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/workDumpWrite.do")
	public void workDumpWrite(@ModelAttribute("workVO") WorkVO workVO, HttpServletResponse response) throws Exception{
		WorkVO resultSet = null;
		
		backupService.insertDumpWork(workVO);
		resultSet = backupService.lastWorkId();
		
		System.out.println("result:"+resultSet.getWrk_id());

		response.getWriter().println(resultSet.getWrk_id());
	}
	
	@RequestMapping(value = "/popup/workOptWrite.do")
	public void workOptWrite(@ModelAttribute("WorkOptVO") WorkOptVO workOptVO, HttpServletResponse response){
		try{
			backupService.insertWorkOpt(workOptVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rman백업 수정팝업 페이지를 반환한다.
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/popup/rmanRegReForm.do")
	public ModelAndView rmanRegReForm(@ModelAttribute("workVo") WorkVO workVO, ModelMap model) throws Exception  {
		ModelAndView mv = new ModelAndView();

		workVO.setBck_bsn_dscd("rman");
		model.addAttribute("workInfo", backupService.selectWorkList(workVO));

		// incoding code List
		try {
			PageVO pageVO = new PageVO();
			pageVO.setGrp_cd("TC0005");
			pageVO.setSearchCondition("0");
			List<CmmnCodeVO> cmmnCodeVO = cmmnCodeDtlService.cmmnDtlCodeSearch(pageVO);
			mv.addObject("incodeList",cmmnCodeVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("db_svr_id",workVO.getDb_svr_id());
		mv.addObject("wrk_id",workVO.getWrk_id());
		
		mv.setViewName("popup/rmanRegReForm");
		return mv;	
	}
	
	/**
	 * Dump백업 수정팝업 페이지를 반환한다.
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/popup/dumpRegReForm.do")
	public ModelAndView dumpRegReForm(@ModelAttribute("workVo") WorkVO workVO, ModelMap model) throws Exception  {
		ModelAndView mv = new ModelAndView();

		workVO.setBck_bsn_dscd("dump");
		model.addAttribute("workInfo", backupService.selectWorkList(workVO));
		model.addAttribute("workOptInfo", backupService.selectWorkOptList(workVO));

		mv.addObject("db_svr_id",workVO.getDb_svr_id());
		mv.addObject("wrk_id",workVO.getWrk_id());
		
		mv.setViewName("popup/dumpRegReForm");
		return mv;	
	}
	
	/**
	 * Rman Work을 수정등록한다.
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/workRmanReWrite.do")
	public void workRmanReWrite(@ModelAttribute("workVO") WorkVO workVO, HttpServletResponse response) throws Exception{
		backupService.updateRmanWork(workVO);
		
		// 옵션내역 삭제
		WorkOptVO workOptVO = new WorkOptVO();
		workOptVO.setWrk_id(workVO.getWrk_id());
		backupService.deleteWorkOpt(workOptVO);
		
		response.getWriter().println(workVO.getWrk_id());
	}
	
	/**
	 * Work을 삭제한다.
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/workDelete.do")
	public void workDelete(@ModelAttribute("workVO") WorkVO workVO, HttpServletResponse response) throws Exception{
		
		// 옵션내역 삭제
		WorkOptVO workOptVO = new WorkOptVO();
		workOptVO.setWrk_id(workVO.getWrk_id());
		backupService.deleteWorkOpt(workOptVO);
		
		// work삭제
		backupService.deleteWork(workVO);
		
		response.getWriter().println(workVO.getWrk_id());
	}
}