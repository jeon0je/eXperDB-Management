package com.k4m.dx.tcontrol.backup.service;

import java.util.List;

public interface BackupService {

	/**
	 * 백업 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<WorkVO> selectWorkList(WorkVO workVO) throws Exception;
	
	/**
	 * 옵션별 상세내역 조회
	 * @return
	 * @throws Exception
	 */
	public List<WorkOptDetailVO> selectOptDetailList(WorkOptDetailVO workOptDetailVO) throws Exception;
	
	/**
	 * Rman백업내역 insert
	 * @param WorkVO
	 * @return String
	 * @throws Exception
	 */
	public void insertRmanWork(WorkVO workVO) throws Exception;
	
	/**
	 * Dump백업내역 insert
	 * @param WorkVO
	 * @return String
	 * @throws Exception
	 */
	public void insertDumpWork(WorkVO workVO) throws Exception;
	
	/**
	 * Rman백업수정 내역 update
	 * @param WorkVO
	 * @return String
	 * @throws Exception
	 */
	public void updateRmanWork(WorkVO workVO) throws Exception;
	
	/**
	 * get wrk_id MAX value
	 * @return WorkVO
	 * @throws Exception
	 */
	public WorkVO lastWorkId() throws Exception;

	/**
	 * Work Option Insert
	 * @param WorkOptVO
	 * @return 
	 * @throws Exception
	 */
	public void insertWorkOpt(WorkOptVO workOptVO) throws Exception;
	
	/**
	 * Work별 등록 옵션 내역 조회
	 * @return
	 * @throws Exception
	 */
	public List<WorkOptVO> selectWorkOptList(WorkVO workVO) throws Exception;
	
	/**
	 * Work별 등록 옵션 delete
	 * @param WorkOptVO
	 * @return
	 * @throws Exception
	 */
	public void deleteWorkOpt(WorkOptVO workOptVO) throws Exception;
	
	/**
	 * Work delete
	 * @param WorkVO
	 * @return
	 * @throws Exception
	 */
	public void deleteWork(WorkVO workVO) throws Exception;
	
	/**
	 * DB 목록 조회
	 * @param WorkVO
	 * @return List<DbVO>
	 * @throws Exception
	 */
	public List<DbVO> selectDbList(WorkVO workVO) throws Exception;
	
	/**
	 * Work Log List
	 * @param WorkLogVO
	 * @return List<WorkLogVO>
	 * @throws Exception
	 */
	public List<WorkLogVO> selectWorkLogList(WorkLogVO workLogVO) throws Exception;
}