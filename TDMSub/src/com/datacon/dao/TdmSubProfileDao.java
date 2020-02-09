package com.datacon.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.datacon.exception.DAOException;
import com.datacon.model.DO.ProjectDO;

public interface TdmSubProfileDao{

	String saveProfile(ProjectDO convertTdmProfileDTOTOProjectDO,EntityManager entityManager) throws DAOException;

	String checkProfileName(String profileName, EntityManager entityManager) throws DAOException;

	List<ProjectDO> fetchAllProfiles(long requestid, int offSet, int recordsperpage, boolean b,
			String userid, EntityManager entityManager) throws DAOException;

	long getExistingProfilersCount(String username, EntityManager managerUser) throws DAOException;

	void deleteProfiler(String reqVals, EntityManager managerUser) throws DAOException;

	ProjectDO fetchProfile(String reqVals, EntityManager managerUser) throws DAOException;

	String editProfile(ProjectDO convertTdmProfileDTOTOProjectDO, EntityManager managerUser) throws DAOException;
}
