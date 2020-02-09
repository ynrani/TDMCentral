package com.datacon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.datacon.constant.AppConstant;
import com.datacon.constant.MessageConstant;
import com.datacon.dao.TdmSubProfileDao;
import com.datacon.exception.DAOException;
import com.datacon.model.DO.DataConConnectionsDO;
import com.datacon.model.DO.ProjectDO;
import com.datacon.model.DO.SubsetCriteriaDO;
import com.datacon.model.DO.TdmUserDO;

@Component("tdmSubProfileDao")
public class TdmSubProfileDaoImpl implements TdmSubProfileDao{

	private static Logger logger = Logger.getLogger(TdmSubProfileDaoImpl.class);
	@Override
	public String saveProfile(ProjectDO projectDO, EntityManager entityManager)
			throws DAOException{

		logger.info("inside save profile of TdmSubProfileDaoImpl");
		String strMessage = AppConstant.FAILED;
		try {
			TdmUserDO userDO = entityManager.createNamedQuery("TdmUserDO.findByUserId", TdmUserDO.class)
					.setParameter("userId", projectDO.getUser().getUserId()).getSingleResult();
			DataConConnectionsDO connectionDO = entityManager.createNamedQuery("DataConConnectionDO.findByconnectionName", DataConConnectionsDO.class)
					.setParameter("connectionName", projectDO.getSubsetCriterias().get(0).getDataConConnection().getConnectionName()).getSingleResult();
			/*List<ProjectDO> projectDos = entityManager.createNamedQuery("ProjectDO.findByProjectName",ProjectDO.class).setParameter("projectName", projectDO.getProjectName()).getResultList();
			if(projectDos != null && !projectDos.isEmpty()){
				return "FAILED:Profile name is already exist";
			}*/
			String idrole="";
				String no = (String) entityManager.createQuery(
						"SELECT NVL(MAX(p.projectId),0)  from ProjectDO p").getSingleResult();
				int noo = Integer.parseInt(no) + 1;
				idrole = Integer.toString(noo);
				projectDO.setProjectId(idrole);
				projectDO.setUser(userDO);
				
				List<SubsetCriteriaDO> listSubsetValues = new ArrayList<SubsetCriteriaDO>();
				SubsetCriteriaDO subsetDo = projectDO.getSubsetCriterias().get(0);
				String childno = (String) entityManager.createQuery(
						"SELECT NVL(MAX(p.subsetId),0)  from SubsetCriteriaDO p").getSingleResult();
				int childnoo = Integer.parseInt(childno) + 1;
				//idrole = Integer.toString(noo);
				subsetDo.setDataConConnection(connectionDO);
				subsetDo.setSubsetId(Integer.toString(childnoo));
				
				//subsetDo.setProject(projectDO);
				//listSubsetValues.add(subsetDo);
				projectDO.setSubsetCriterias(listSubsetValues);
				
				entityManager.getTransaction().begin();
				entityManager.merge(projectDO);
				subsetDo.setProject(projectDO);
				entityManager.merge(subsetDo);
				//listSubsetValues.add(subsetDo);
				//projectDO.setSubsetCriterias(listSubsetValues);
				entityManager.getTransaction().commit();
				strMessage = AppConstant.SUCCESS;
				
			
			logger.info("return from saved profiler details");
			return strMessage;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	
		//return strMessage;
	}
	
	
	@Override
	public String checkProfileName(String profileName, EntityManager entityManager)
			throws DAOException{

		logger.info("inside save profile of checkProfileName");
		try {
			List<ProjectDO> projectDos = entityManager.createNamedQuery("ProjectDO.findByProjectName",ProjectDO.class).setParameter("projectName", profileName).getResultList();
			if(projectDos != null && !projectDos.isEmpty()){
				return AppConstant.FAILED;
			}else
				return AppConstant.SUCCESS;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	
		//return strMessage;
	}
	
	
	@Override
	public List<ProjectDO> fetchAllProfiles(long requestid, int offSet,
			int recordsperpage, boolean b, String userid, EntityManager entityManager)
			throws DAOException{

		logger.info("inside save profile of fetchAllProfiles");
		try {
			List<ProjectDO> listProjectDo=  entityManager.createNamedQuery("ProjectDO.findAll", ProjectDO.class).setFirstResult(offSet)
					.setMaxResults(recordsperpage).getResultList();
			logger.info("return from fetchAllProfiles details");
			return listProjectDo;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	
		//return strMessage;
	}


	@Override
	public long getExistingProfilersCount(String username, EntityManager managerUser)
			throws DAOException{
		Long lResult = 0L;
		try {
		if (managerUser != null) {
			lResult = (Long) managerUser.createQuery("SELECT COUNT(*) FROM ProjectDO p WHERE p.user.userId='"+username+"'")
					.getSingleResult();
		}
		return lResult;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}


	@Override
	public void deleteProfiler(String reqVals, EntityManager managerUser) throws DAOException{
		try {
		if (managerUser != null) {
			//for(ProjectDO projectdo : projectDos){
			managerUser.getTransaction().begin();
				managerUser.createQuery("DELETE FROM SubsetCriteriaDO p WHERE p.project.projectId='"+reqVals+"'").executeUpdate();
			//}
			managerUser.createQuery("DELETE FROM ProjectDO d WHERE d.projectId='"+reqVals+"'").executeUpdate();
			managerUser.getTransaction().commit();
		}
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}


	@Override
	public ProjectDO fetchProfile(String reqVals, EntityManager managerUser) throws DAOException{

		logger.info("inside fetchProfile");
		try {
			ProjectDO projectDos = managerUser.createNamedQuery("ProjectDO.findByProjectId",ProjectDO.class).setParameter("projectId", reqVals).getSingleResult();
			return projectDos;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	
		//return strMessage;
	}


	@Override
	public String editProfile(ProjectDO projectDO, EntityManager entityManager)
			throws DAOException{


		logger.info("inside edit profile of TdmSubProfileDaoImpl");
		String strMessage = AppConstant.FAILED;
		try {
			/*TdmUserDO userDO = entityManager.createNamedQuery("TdmUserDO.findByUserId", TdmUserDO.class)
					.setParameter("userId", projectDO.getUser().getUserId()).getSingleResult();*/
			DataConConnectionsDO connectionDO = entityManager.createNamedQuery("DataConConnectionDO.findByconnectionName", DataConConnectionsDO.class)
					.setParameter("connectionName", projectDO.getSubsetCriterias().get(0).getDataConConnection().getConnectionName()).getSingleResult();
			/*List<ProjectDO> projectDos = entityManager.createNamedQuery("ProjectDO.findByProjectName",ProjectDO.class).setParameter("projectName", projectDO.getProjectName()).getResultList();
			if(projectDos != null && !projectDos.isEmpty()){
				return "FAILED:Profile name is already exist";
			}*/
			ProjectDO projectDos = entityManager.createNamedQuery("ProjectDO.findByProjectName",ProjectDO.class).setParameter("projectName", projectDO.getProjectName()).getSingleResult();
		/*	String idrole="";
				String no = (String) entityManager.createQuery(
						"SELECT NVL(MAX(p.projectId),0)  from ProjectDO p").getSingleResult();
				int noo = Integer.parseInt(no) + 1;
				idrole = Integer.toString(noo);
				projectDO.setProjectId(idrole);
				projectDO.setUser(userDO);*/
			entityManager.getTransaction().begin();
			entityManager.createQuery("DELETE FROM SubsetCriteriaDO p WHERE p.project.projectId='"+projectDos.getProjectId()+"'").executeUpdate();
				List<SubsetCriteriaDO> listSubsetValues = new ArrayList<SubsetCriteriaDO>();
				SubsetCriteriaDO subsetDo = projectDO.getSubsetCriterias().get(0);
				String childno = (String) entityManager.createQuery(
						"SELECT NVL(MAX(p.subsetId),0)  from SubsetCriteriaDO p").getSingleResult();
				int childnoo = Integer.parseInt(childno) + 1;
				//idrole = Integer.toString(noo);
				subsetDo.setDataConConnection(connectionDO);
				subsetDo.setSubsetId(Integer.toString(childnoo));
				
				//subsetDo.setProject(projectDO);
				//listSubsetValues.add(subsetDo);
				projectDos.setSubsetCriterias(listSubsetValues);
				
//				entityManager.getTransaction().begin();
				//entityManager.merge(projectDO);
				subsetDo.setProject(projectDos);
				entityManager.merge(subsetDo);
				//listSubsetValues.add(subsetDo);
				//projectDO.setSubsetCriterias(listSubsetValues);
				entityManager.getTransaction().commit();
				strMessage = AppConstant.SUCCESS;
				
			
			logger.info("return from edit profiler details");
			return strMessage;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL + MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	
		//return strMessage;
	
	}
}
