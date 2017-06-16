package com.k4m.dx.tcontrol.backup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.k4m.dx.tcontrol.backup.service.BackupService;
import com.k4m.dx.tcontrol.backup.service.DbVO;
import com.k4m.dx.tcontrol.backup.service.WorkLogVO;
import com.k4m.dx.tcontrol.backup.service.WorkOptDetailVO;
import com.k4m.dx.tcontrol.backup.service.WorkOptVO;
import com.k4m.dx.tcontrol.backup.service.WorkVO;


@Service("backupServiceImpl")
public class BackupServiceImpl implements BackupService{
	
	@Resource(name = "BackupDAO")
	private BackupDAO backupDAO;

	public List<WorkVO> selectWorkList(WorkVO workVO) throws Exception {
		return backupDAO.selectWorkList(workVO);	
	}

	public List<WorkOptDetailVO> selectOptDetailList(WorkOptDetailVO WorkOptDetailVO) throws Exception {
		return backupDAO.selectOptDetailList(WorkOptDetailVO);	
	}
	
	public void insertRmanWork(WorkVO workVO) throws Exception {
		 backupDAO.insertRmanWork(workVO);
	}
	
	public void insertDumpWork(WorkVO workVO) throws Exception {
		 backupDAO.insertDumpWork(workVO);
	}
	
	public void updateRmanWork(WorkVO workVO) throws Exception{
		backupDAO.updateRmanWork(workVO);
	}
	public WorkVO lastWorkId() throws Exception{
		return backupDAO.lastWorkId();
	}
	
	public void insertWorkOpt(WorkOptVO workOptVO) throws Exception{
		backupDAO.insertWorkOpt(workOptVO);
	}
	
	public List<WorkOptVO> selectWorkOptList(WorkVO workVO) throws Exception{
		return backupDAO.selectWorkOptList(workVO);
	}
	
	public void deleteWorkOpt(WorkOptVO workOptVO) throws Exception{
		backupDAO.deleteWorkOpt(workOptVO);
	}
	
	public void deleteWork(WorkVO workVO) throws Exception{
		backupDAO.deleteWork(workVO);
	}
	
	public List<DbVO> selectDbList(WorkVO workVO) throws Exception{
		return backupDAO.selectDbList(workVO);
	}
	
	public List<WorkLogVO> selectWorkLogList(WorkLogVO workLogVO) throws Exception{
		return backupDAO.selectWorkLogList(workLogVO);
	}
	
}