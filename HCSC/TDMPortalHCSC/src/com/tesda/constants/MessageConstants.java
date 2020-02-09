/*---------------------------------------------------------------------------------------
 * Object Name: TDMConstants.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.constants;

/**
 * Constants class which will provide the string constants for class names and method names
 * which are used in logging(Log4j) system for debugging purpose.
 */

public class MessageConstants
{

	public static final String TDM_FORGOT_PASS = "forgotPass ~ ";
	public static final String TDM_LOGIN_CTLR = "LoginController ~ ";
	public static final String LOG_INFO_PARAMS_NO = " Method Begain ~ params : No ";
	public static final String EXCEPTION_ADMIN = "Exception Occurred Contact Admin!!!";
	public static final String LOG_ERROR_EXCEPTION = " Exception ";
	public static final String TDM_SEARCH_SERVICE = "searchManagementService";
	public static final String TDM_FTD_SERVICE = "TDMProviserSearchServiceImpl ~ ";
	public static final String TDM_FTD_SERVICE_DAO = "TDMProviserSearchDAOImpl ~ ";
	public static final String TDM_FTD_SER_FORGOT_PASS1 = "forgotPassword  ~  ";
	public static final String TDM_FTD_CHECK_AVIL_USER = "checkAvailabilityOfUserId ~ ";
	public static final String NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION = "NRE_0106";
	public static final String INVALID_QUERY_EXCEPTION = "11200";
	public static final String NULL_POINTER_EXCEPTION = "11201";
	public static final String DATABASE_EXCEPTION = "11202";
	public static final String SERVICE_EXCEPTION = "11203";
	public static final String IE_EXCEPTION = "11204";
	public static final String PARSE_EXCEPTION = "11205";

	public static final String LOG_INFO_RETURN = " next is return ";
	public static final String TWO_PARAMS = " TWO PARAMS";

	public static final String EMAIL_CNTRL = "EmailController ~";
	public static final String L1L2GET = " popupEmailL1L2Get ~";
	public static final String POPUPEMAIL = " popupEmailGet ~";

	public static final String LOGIN_CNTRL = "LoginController ~";
	public static final String LOGIN = "login ~ ";
	public static final String LOGOUT = "logout ~ ";
	public static final String USERMANUAL = "doDownloadTdm ~ download usermanual";
	public static final String FORGOTPASS = "forgotPass ~ ";

	public static final String ADMIN_CNTRL = "TDMAdminController ~ ";
	public static final String USRDTLS = "userDetails ~ ";
	public static final String DISPLYUSR = "displayUser ~";
	public static final String CRTNEWUSR = "createNewUser ~";
	public static final String DELUSR = "daleteUser ~";
	public static final String VALUSR = "validateUserId ~";
	public static final String EDTUSR = "editUser ~";

	public static final String CHNGREQ_CNTRL = "TDMChangeReqController ~";
	public static final String GETREQEXT = "tdmGetCngReqExt ~";
	public static final String POSTREQEXT = "tdmGetCngReqExtPost ~";
	public static final String GETREQEXTID = "tdmGetCngReqExtID ~";

	public static final String MTRICS_CNTRL = "TDMCmdCenterMetricsController ~";
	public static final String DOWNLD_MTRCS = "doDownloadMetrics ~";
	public static final String INDXMTRICS = "indexMatrics ~";

	public static final String MSKING_CNTRL = "TDMDataMaskingController ~";
	public static final String GETREQDTLS = "tdmDataMaskingReqDtlGet ~";
	public static final String POSTREQDTLS = "tdmDataMaskingReqDtlPost ~";
	public static final String GETPREREQ = "tdmDataMaskingPreReqGet ~";
	public static final String POSTPREREQ = "tdmDataMaskingPreReqPost ~";
	public static final String GETMSKDTLS = "tdmDataMaskingMskDtlGet ~";
	public static final String POSTMSKDTLS = "tdmDataMaskingMskDtlPost ~";
	public static final String MSKNGEXPRT = "tdmMaskingExportFRGet ~";
	public static final String MSKNGDASHBRD = "tdmDtMaskDashboard ~";
	public static final String MSKNG_DASHBRDCR = "tdmDtMaskDashboardCR ~";
	public static final String MSKNGEXPRT_FR = "tdmMaskingExportFR ~";
	public static final String MSKNGEXPRT_CR = "tdmMaskingExportCR ~";
	public static final String CANCL_ONBRDREQ = "tdmCancelOnboardReq ~";
	public static final String TDM_CANCEL_REQ = "tdmDtMaskCancelReq ~";
	public static final String TDM_DEL_MSKING_REQROW = "tdmDeleteMaskingReqRow ~";

	public static final String DMND_CNTRL = "TDMDemandController ~";
	public static final String GETDTSUBSET = "tdmDataSubsettingGet ~";
	public static final String CHNGMGMTGET = "tdmDataChangeMgmtGet ~";
	public static final String DTREFRESHGET = "tdmDataRefreshGet ~";
	public static final String DTREQGET = "tdmTestDataRequestGet ~";

	public static final String ESTMCNTRL = "TDMEstiToolController ~";
	public static final String INDEXGOV = "indexGovnce ~";
	public static final String DNLDTDP = "doDownloadTdp ~";
	public static final String DNLDTDM = "doDownloadTdm ~";

	public static final String TDMGOVCNTRL = "TDMGovernenceController ~";
	public static final String INDXGVNCE = "indexGovnce ~";
	public static final String DWNLDGOV = "doDownload ~";

	public static final String RESRVTION_CNTRL = "TDMMyReservationController ~";
	public static final String RESVTION_RCRDS = "myReservationRecords ~";
	public static final String UNRESRVE_RCRDS = "unResrveReservedRecords ~";
	public static final String EXPRT_RESRVE_RCRDS = "exportReservedRecords ~";

	public static final String NONSTAND_CNTRL = "TDMNonStandardSearchController ~";
	public static final String NONSATND_RESRVE = "tdmGetNonStandReserve ~";
	public static final String NONSTAND_FIELDS = "tdmGetNonStandFields ~";
	public static final String NONSTAND_SRCHRECORDS = "tdmGetNonStandSearchRecords ~";
	public static final String RESET_NONSTAND = "tdmResetNonStand ~";
	public static final String EXPORT_NONSTAND = "tdmNonStandResultExport ~";
	public static final String TDM_NS_PAGINATE = "tdmGetNonStandSearchPagination ~";
	public static final String SEARCH_CRITERIA = "getSearchCriteria ~";

	public static final String ONBRDREQ_CNTRL = "TDMOnBoardReqController ~";
	public static final String GET_ONBRDREQ = "tdmGetOnboardReq ~";
	public static final String POST_ONBRDREQ = "tdmPostOnboardReq ~";
	public static final String DTMSK_DASHBRD = "tdmDtMaskDashboard ~";
	public static final String DTMSK_DASHBRDCR = "tdmDtMaskDashboardCR ~";
	public static final String ONBRD_EXPORTFR = "tdmOnBoardingExportFR ~";
	public static final String CANCEL_ONBRDREQ = "tdmCancelOnboardReq ~";
	public static final String DELETE_ONBOARD_REQROW = "tdmDeleteOnboardReqRow ~";

	public static final String TDM_NS_DAO_IMPL = "TDMNonStandSearchDAOImpl ~";
	public static final String GET_TDMSRCHFLDS = "getTDMSearchFields ~";
	public static final String SAVE_RSERV_DATA = "saveReserveData ~";
	public static final String GET_NS_RECORDS = "getTDMNonStandSearchRecords ~";
	public static final String GET_RSERVE_RECORDS = "getReservedRecords ~";
	public static final String GET_RSERVE_RECORDSCOUNT = "getReservedRecordsCount ~";
	public static final String UN_RESRVE_RECORDS = "unReserveResrvedRecords ~";
	public static final String GET_NS_REC_COUNT = "getNonStandardSearchRecordCount ~";
	public static final String GET_RESRVED_RECORDS_ONEARG = "getReservedRecords one argument ~";
	public static final String GET_BLENDED_RECORDS = "getBlendedRecords ~";
	public static final String GET_DEPNDENT_DETAILS = "getDependentDetails ~";

	public static final String TDM_ADMIN_DAOIMPL = "TDMAdminDAOImpl ~";
	public static final String SAVE_USER_DETAILS = "saveUserDetails ~";
	public static final String GET_ALL_USERS = "getAllUser ~";
	public static final String GET_EDIT_USER = "getEditUser ~";
	public static final String DELETE_USER_BYID = "deleteUserByUserId ~";
	public static final String SEARCH_RECORDS_COUNT = "searchUserRecordsCount ~";
	public static final String VALIDATE_USERID = "validateUserId ~";
	public static final String SAVE_LOGINUSER_DETAILS = "saveLoginUserDetails ~";
	public static final String GET_ALLLOGIN_USERS = "getAllUsers ~";

	public static final String DATA_MASKING_DAOIMPL = "TDMDataMaskingDAOImpl ~";
	public static final String SAVE_MASKING_DATA = "saveMaskingData ~";
	public static final String GET_SAVE_REQDTLS = "getSaveReqDtls ~";
	public static final String GET_SAVED_DTLS = "getSavedDtls ~";
	public static final String GETALL_DTMASK_REQREC = "getAllDtMaskRequestedRecord ~";
	public static final String GET_DTMASK_REQREC = "getDtMaskRequestedRecord ~";
	public static final String SEARCH_MASKING_RECORDS = "searchDataMaskingRecords ~";
	public static final String GET_MSKING_REC_COUNT = "getDataMaskingRecordsCount ~";
	public static final String GET_USER_DETAILS = "getUserDetails ~";
	public static final String GET_USERDTLS_ONBOARD = "getUserDetailsOnboard ~";
	public static final String GETSAVE_ONBRD_REQ = "getSaveOnboardingReq ~";
	public static final String GET_EDITABLE_DTLS = "getEditableDetails ~";
	public static final String GET_REQID_LIST = "getReqIdList ~";
	public static final String GET_ONBRD_RECCOUNT = "getOnBoardingRecordsCount ~";
	public static final String SEARCH_ONBRD_REC = "searchOnBoardingRecords ~";
	public static final String CANCEL_ONBRD_REQ = "cancelOnBoardingReq ~";
	public static final String GET_DTLS_TOEXPORT = "getSavedDtlsforExport ~";
	public static final String CANCEL_MSKING_REQ = "cancelMaskingReq ~";
	public static final String DTMASK_CANCEL_REQ = "dtMaskCancelReq ~";
	public static final String TDM_DT_MSK_DAO_CHECK_EXISTING = "getCheckExistingReqYesNo ~";
	public static final String DELETE_ROW = "deleteRow ~";
	public static final String REMOV_DUPLICATES = "removeDuplicateRecordsFromRequestorCHDO ~";

	public static final String DATA_MASKING_SERVICEIMPL = "TDGDataMaskingServiceImpl ~";
	public static final String GET_RESV_REC_CNT = "getReservedRecordsCount ~";
	public static final String SAVE_REQDTLS = "saveReqDtls ~";
	public static final String GET_ALLDTMSK_REQREC_PGN = "getAllDtMaskRequestedRecordForPagination ~";
	public static final String GET_RESV_REC_CNT_ONBRD = "getReservedRecordsCountOnBoard ~";
	public static final String GET_ALLONBRD_REQREC_PGN = "getAllOnBoardRequestedRecordForPagination ~";
}
