SET SCHEMA ORGSEC_DB;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: dlhcwl0518.dlh.st.com    Database: orgsec_db
-- ------------------------------------------------------
-- Server version	5.1.73-community

--
-- Table structure for table `access_mode`
--

DROP TABLE IF EXISTS `access_mode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_mode` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `CODE` varchar(6) NOT NULL COMMENT 'Access codes supported by the system i.e. ABDU, ABU, BU, B etc',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the access mode',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`),
  KEY `fk__access_mode__status_idx` (`STATUS__ID`),
  KEY `fk__access_mode__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__access_mode__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__access_mode__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__access_mode__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__access_mode__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Access Modes supported by the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `access_type`
--

DROP TABLE IF EXISTS `access_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_type` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(45) NOT NULL COMMENT 'Name of access type supported by the system',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the access type',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `fk__status_id__access_type__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__access_type__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__access_type__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__access_type__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__access_type__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__access_type__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for the type of accesses supported by the product';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the application',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the application',
  `LOCKED` varchar(1) DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `ACCESS_TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to Access_type table.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of Application type i.e. Web App, VB, Mobile App etc',
  `DEFAULT_ROLE__ID` bigint(20) unsigned DEFAULT NULL COMMENT 'ID of the default role. NULL means default role is not specified',
  `AUTHENTICATION_TYPE` varchar(45) DEFAULT 'SELF' COMMENT 'Specifies authentication mechanism used by Application i.e. CAS, NTLM, self implemented etc.\n',
  `COMP_LEVEL_ACCESS_REQD` varchar(1) DEFAULT 'Y' COMMENT 'Specifies if the component level access is required for the application\n',
  `LOWER_LEVEL_DD_REQD` varchar(1) DEFAULT 'N' COMMENT 'Specifies if the lower level datadimensions will be loaded or not\n',
  `MENU_REQD` varchar(1) DEFAULT 'Y' COMMENT 'Specifies if menu will be managed in OS3\n',
  `ACCESS_SELECTION_REQD` varchar(1) DEFAULT 'N' COMMENT 'Represents if Role or Function selection is required or not. Valid values are Y and N.\n',
  `SUBSEQUENT_DATA_LOAD_REQD` varchar(1) DEFAULT 'N' COMMENT 'Specifies if data will be loaded subsequently\n',
  `SUBSEQUENT_CHILD_DATA` varchar(1) DEFAULT 'N' COMMENT 'Specifies if lower orgs will be loaded subsequently',
  `SUBSEQUENT_COMP_ACCESS` varchar(1) DEFAULT 'N' COMMENT 'Specifies if components will be loaded subsequently\n',
  `ENVIRONMENT` varchar(45) NOT NULL COMMENT 'Specifies the environment in which application will be deployed i.e. INTRANET, EXTRANET, E2O etc.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `fk__type_id__application__entity_type_idx` (`TYPE__ID`),
  KEY `fk__default_role_id__application__role_idx` (`DEFAULT_ROLE__ID`),
  KEY `fk__status_id__application__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__application__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__application__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk_access_type_id__application__access_type_idx` (`ACCESS_TYPE__ID`),
  CONSTRAINT `fk_access_type_id__application__access_type` FOREIGN KEY (`ACCESS_TYPE__ID`) REFERENCES `access_type` (`ID`),
  CONSTRAINT `fk__created_by__application__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__default_role_id__application__role` FOREIGN KEY (`DEFAULT_ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__modified_by__application__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__application__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__application__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Applications managed by the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application_configuration`
--

DROP TABLE IF EXISTS `application_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_configuration` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'primary key of this table',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to application table\n',
  `USER_REPOSITORY__ID` bigint(20) unsigned NOT NULL COMMENT 'Description of user repository',
  `DATA_REPOSITORY__ID` bigint(20) unsigned NOT NULL COMMENT 'Description of data repository\n',
  `PROFILING_SYSTEM__ID` bigint(20) unsigned NOT NULL COMMENT 'Description of profiling system',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  KEY `fk__status_id__application__status_idx` (`USER_REPOSITORY__ID`),
  KEY `fk__data_repo_id__app_config__data_repo_idx` (`DATA_REPOSITORY__ID`),
  KEY `fk__prof_sys_id__app_config__prof_system_idx` (`PROFILING_SYSTEM__ID`),
  KEY `fk__status_id__app_config__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__app_config__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__app_config__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__application_id__app_config__application_idx` (`APPLICATION__ID`),
  CONSTRAINT `fk__application_id__app_config__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__app_config__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__data_repo_id__app_config__data_repo` FOREIGN KEY (`DATA_REPOSITORY__ID`) REFERENCES `data_repository` (`ID`),
  CONSTRAINT `fk__modified_by__app_config__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__prof_sys_id__app_config__prof_system` FOREIGN KEY (`PROFILING_SYSTEM__ID`) REFERENCES `profiling_system` (`ID`),
  CONSTRAINT `fk__status_id__app_config__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__user_repo_id__app_config__user_repo` FOREIGN KEY (`USER_REPOSITORY__ID`) REFERENCES `user_repository` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Metadata for App like profile repo, user repo & data repo';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application_x_attribute`
--

DROP TABLE IF EXISTS `application_x_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_x_attribute` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to application table\n',
  `ATTRIBUTE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to attribute table\n',
  `VALUE` varchar(45) NOT NULL COMMENT 'Value of the attribute linked',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `APP_ATTR_VAL_UNIQUE` (`APPLICATION__ID`,`ATTRIBUTE__ID`,`VALUE`),
  KEY `fk__status_id__app_x_attr__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__app_x_attr__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__app_x_attr__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__attr_id__app_x_attr__attribute` (`ATTRIBUTE__ID`),
  CONSTRAINT `fk__app_id__app_x_attr__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__attr_id__app_x_attr__attribute` FOREIGN KEY (`ATTRIBUTE__ID`) REFERENCES `attribute` (`ID`),
  CONSTRAINT `fk__created_by__app_x_attr__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__app_x_attr__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__app_x_attr__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for attribute linkage at application level';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the attribute',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the attribute',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of the application. ID=1 means ALL Applications',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of attribute type',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_APP_UNIQUE` (`NAME`,`APPLICATION__ID`),
  KEY `fk__type_id__attribute__entity_type_idx` (`TYPE__ID`),
  KEY `fk__status_id__attribute__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__attribute__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__attribute__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__application_id__attribute__application_idx` (`APPLICATION__ID`),
  CONSTRAINT `fk__application_id__attribute__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__attribute__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__attribute__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__attribute__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__attribute__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for attribute definition';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of company',
  `CODE` varchar(45) NOT NULL COMMENT 'Company codes supported by the system',
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT 'Description of company',
  `COMPANY_TYPE` varchar(45) NOT NULL COMMENT 'Specifies the type of company i.e. Parent Company, JV Company, Partner, Subcontractor company etc.\n',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of USER type',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_NAME_UNIQUE` (`CODE`,`NAME`,`COMPANY_TYPE`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`),
  KEY `STATUS__FK_idx` (`STATUS__ID`),
  KEY `fk__type_id__company__entity_type_idx` (`TYPE__ID`),
  KEY `fk__created_by__company__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__company__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__company__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__company__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__company__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__company__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='List of companies supported by the product';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_x_entity`
--

DROP TABLE IF EXISTS `company_x_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_x_entity` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `COMPANY__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to company table\n',
  `ENTITY_TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to application table',
  `ENTITY__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of the ENTITY to which company is linked i.e. ROLE, USER, GROUP, ATTRIBUTE, FUNCTION, DATA etc.',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of company and entity relation',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `COMP_ENTITY_ENTYTYTYPE_UNIQUE` (`COMPANY__ID`,`ENTITY__ID`,`ENTITY_TYPE__ID`),
  KEY `fk__status_id__company_x_entity__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__company_x_entity__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__company_x_entity__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__entity_type_id__company_x_entity__entity_type` (`ENTITY_TYPE__ID`),
  CONSTRAINT `fk__company_id__company_x_entity__company` FOREIGN KEY (`COMPANY__ID`) REFERENCES `company` (`ID`),
  CONSTRAINT `fk__created_by__company_x_entity__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__entity_type_id__company_x_entity__entity_type` FOREIGN KEY (`ENTITY_TYPE__ID`) REFERENCES `entity_type` (`ID`),
  CONSTRAINT `fk__modified_by__company_x_entity__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__company_x_entity__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to define company entities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `component` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the components',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the components',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of the application',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of component type',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_APP_UNIQUE` (`NAME`,`APPLICATION__ID`),
  KEY `fk__app_id__component__application_idx` (`APPLICATION__ID`),
  KEY `fk__type_id__component__entity_type_idx` (`TYPE__ID`),
  KEY `fk__status_id__component__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__component__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__component__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__app_id__component__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__component__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__component__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__component__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__component__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to maintain components';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `composite_role_def`
--

DROP TABLE IF EXISTS `composite_role_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `composite_role_def` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `COMPOSITE_ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of composite role',
  `SIMPLE_ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of simple role',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CROLE_SROLE_UNIQUE` (`COMPOSITE_ROLE__ID`,`SIMPLE_ROLE__ID`),
  KEY `fk__status_id__composite_role_def__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__composite_role_def__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__composite_role_def__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__simple_role_id__composite_role_def__role` (`SIMPLE_ROLE__ID`),
  CONSTRAINT `fk__composite_role_id__composite_role_def__role` FOREIGN KEY (`COMPOSITE_ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__created_by__composite_role_def__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__composite_role_def__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__simple_role_id__composite_role_def__role` FOREIGN KEY (`SIMPLE_ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__status_id__composite_role_def__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Composite role is defined here';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `data`
--

DROP TABLE IF EXISTS `data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the data',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the data',
  `CODE` varchar(45) NOT NULL COMMENT 'Contain the code of data\n',
  `ISSUING_ORG_LEVEL__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of data i.e. 01=Group, 02=Division etc.',
  `UPPER_ORG__ID` bigint(20) unsigned DEFAULT NULL COMMENT 'Represents parent node code of the current entry. If the value is NULL, then the current entry is the root node',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of data type',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_IOL_UNIQUE` (`CODE`,`ISSUING_ORG_LEVEL__ID`),
  KEY `fk__type_id__data__entity_type_idx` (`TYPE__ID`),
  KEY `fk__status_id__data__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__data__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__data__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__issuing_org_level_id__data__issuing_org_level` (`ISSUING_ORG_LEVEL__ID`),
  KEY `fk__upper_org_id__data__data_idx_idx` (`UPPER_ORG__ID`),
  CONSTRAINT `fk__created_by__data__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__issuing_org_level_id__data__issuing_org_level` FOREIGN KEY (`ISSUING_ORG_LEVEL__ID`) REFERENCES `issuing_org_level` (`ID`),
  CONSTRAINT `fk__modified_by__data__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__data__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__data__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`),
  CONSTRAINT `fk__upper_org_id__data__data_idx` FOREIGN KEY (`UPPER_ORG__ID`) REFERENCES `data` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to manage data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `data_repository`
--

DROP TABLE IF EXISTS `data_repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_repository` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of data repository',
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT 'Description of data repository',
  `CONNECTION_DETAILS` varchar(10000) NOT NULL COMMENT 'Specifies the connection details. It can be XML or JSON or another format. \n',
  `FORMAT` varchar(45) NOT NULL COMMENT 'Specifies the format in which connection details are stored i.e. XML, JSON etc.',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `fk__status_id__data_repository__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__data_repository__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__data_repository__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__data_repository__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__data_repository__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__data_repository__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Defination of supported data repositories by the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delegation`
--

DROP TABLE IF EXISTS `delegation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delegation` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `FROM_DATE` datetime NOT NULL COMMENT 'Delegation start date\n',
  `TO_DATE` datetime NOT NULL COMMENT 'Delegation end date\n',
  `REMARKS` varchar(250) NOT NULL COMMENT 'Remarks on delegation\n',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `DELEGATOR__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of user who has delegated his access to some other user\n',
  `DELEGATE__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of user to which access has been delegated\n',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of the application',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  KEY `fk__status_id__delegation__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__data_repository__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__data_repository__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__application_id__delegation__application_idx` (`APPLICATION__ID`),
  KEY `fk__delegator_id__delegation__user_mapping_idx` (`DELEGATOR__ID`),
  KEY `fk__delegate_id__delegation__user_mapping_idx` (`DELEGATE__ID`),
  CONSTRAINT `fk__application_id__delegation__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__delegation__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__delegate_id__delegation__user_mapping` FOREIGN KEY (`DELEGATE__ID`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__delegator_id__delegation__user_mapping` FOREIGN KEY (`DELEGATOR__ID`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__delegation__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__delegation__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to manage delegation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entity_type`
--

DROP TABLE IF EXISTS `entity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity_type` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of entity',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of entity',
  `TABLE_NAME` varchar(45) NOT NULL COMMENT 'Will contain the table name of the entity\n\n',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_TBLNAME_UNIQUE` (`NAME`,`TABLE_NAME`),
  KEY `fk__status_id__entity_type__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__entity_type__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__entity_type__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__entity_type__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__entity_type__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__entity_type__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='All the entities spported by the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `function`
--

DROP TABLE IF EXISTS `function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `function` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the function',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the function',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of the application',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of function type',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_APP_ID_UNIQUE` (`NAME`,`APPLICATION__ID`),
  KEY `fk__status_id__function__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__function__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__function__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__type_id__function__entity_type_idx` (`TYPE__ID`),
  KEY `fk__app_id__function__application_idx` (`APPLICATION__ID`),
  CONSTRAINT `fk__app_id__function__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__function__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__function__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__function__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__function__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Application functions are defined in this table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `function_x_component`
--

DROP TABLE IF EXISTS `function_x_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `function_x_component` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `FUNCTION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of function',
  `COMPONENT__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of component',
  `ACCESS_MODE__ID` bigint(20) unsigned NOT NULL COMMENT 'Access mode id',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FUNC_COMP_ACCESS_UNIQUE` (`FUNCTION__ID`,`COMPONENT__ID`,`ACCESS_MODE__ID`),
  KEY `fk__created_by__function_x_component__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__function_x_component__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__status_id__function_x_component__status_idx` (`STATUS__ID`),
  KEY `fk__component_id__function_x_component__component_idx` (`COMPONENT__ID`),
  KEY `fk__access_id__function_x_component__access_mode_idx` (`ACCESS_MODE__ID`),
  CONSTRAINT `fk__access_id__function_x_component__access_mode` FOREIGN KEY (`ACCESS_MODE__ID`) REFERENCES `access_mode` (`ID`),
  CONSTRAINT `fk__component_id__function_x_component__component` FOREIGN KEY (`COMPONENT__ID`) REFERENCES `component` (`ID`),
  CONSTRAINT `fk__created_by__function_x_component__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__function_id__function_x_component__function` FOREIGN KEY (`FUNCTION__ID`) REFERENCES `function` (`ID`),
  CONSTRAINT `fk__modified_by__function_x_component__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__function_x_component__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to define component and function mapping';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `function_x_menu`
--

DROP TABLE IF EXISTS `function_x_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `function_x_menu` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `FUNCTION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of function',
  `MENU__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of menu',
  `PARENT_MENU__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of parent menu',
  `ORDER` bigint(20) NOT NULL DEFAULT '0',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk__created_by__function_x_menu__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__function_x_menu__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__status_id__function_x_menu__status_idx` (`STATUS__ID`),
  KEY `fk__function_id__function_x_menu__function_idx` (`FUNCTION__ID`),
  KEY `fk__menu_id__function_x_menu__menu_idx` (`MENU__ID`),
  KEY `fk__par_menu_id__function_x_menu__menu_idx` (`PARENT_MENU__ID`),
  CONSTRAINT `fk__created_by__function_x_menu__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__function_id__function_x_menu__function` FOREIGN KEY (`FUNCTION__ID`) REFERENCES `function` (`ID`),
  CONSTRAINT `fk__menu_id__function_x_menu__menu` FOREIGN KEY (`MENU__ID`) REFERENCES `menu` (`ID`),
  CONSTRAINT `fk__modified_by__function_x_menu__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__par_menu_id__function_x_menu__menu` FOREIGN KEY (`PARENT_MENU__ID`) REFERENCES `menu` (`ID`),
  CONSTRAINT `fk__status_id__function_x_menu__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to define which menus are linked to which functions';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the Group',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the Group',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of the application',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of Group type',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_APPID_UNIQUE` (`NAME`,`APPLICATION__ID`),
  KEY `fk__status_id__group__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__group__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__group__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__type_id__group__entity_type_idx` (`TYPE__ID`),
  KEY `fk__app_id__group__application_idx` (`APPLICATION__ID`),
  CONSTRAINT `fk__app_id__group__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__group__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__group__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__group__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__group__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to manage Groups';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_x_roles`
--

DROP TABLE IF EXISTS `group_x_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_x_roles` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `GROUP__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of group',
  `ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of role linked to the group',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `GRP_ROLE_UNIQUE` (`GROUP__ID`,`ROLE__ID`),
  KEY `fk__status_id__group_x_roles__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__group_x_roles__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__group_x_roles__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__role_id__group_x_roles__role` (`ROLE__ID`),
  CONSTRAINT `fk__created_by__group_x_roles__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__group_id__group_x_roles__group` FOREIGN KEY (`GROUP__ID`) REFERENCES `group` (`ID`),
  CONSTRAINT `fk__modified_by__group_x_roles__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__role_id__group_x_roles__role` FOREIGN KEY (`ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__status_id__group_x_roles__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to define which roles are linked to which group';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_x_users`
--

DROP TABLE IF EXISTS `group_x_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_x_users` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `GROUP__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of group',
  `USER__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of user linked to the group',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `GRP_USR_UNIQUE` (`GROUP__ID`,`USER__ID`),
  KEY `fk__status_id__group_x_users__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__group_x_users__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__group_x_users__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__users_id__group_x_users__users_mapping` (`USER__ID`),
  CONSTRAINT `fk__created_by__group_x_users__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__group_id__group_x_users__group` FOREIGN KEY (`GROUP__ID`) REFERENCES `group` (`ID`),
  CONSTRAINT `fk__modified_by__group_x_users__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__group_x_users__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__users_id__group_x_users__users_mapping` FOREIGN KEY (`USER__ID`) REFERENCES `user_mapping` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to define which users are linked to which group';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `help`
--

DROP TABLE IF EXISTS `help`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `help` (
  `ID` bigint(20) unsigned NOT NULL,
  `FIELD_ID` varchar(50) NOT NULL COMMENT 'ID of component for specific form as used on the from end',
  `MESSAGE` varchar(255) DEFAULT NULL COMMENT 'Message to be displayed in help panel for this component',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  KEY `fk__created_by__help__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__help__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__status_id__help__status_idx` (`STATUS__ID`),
  CONSTRAINT `fk__created_by__help__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__help__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__help__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for managing help messages';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `issuing_org_level`
--

DROP TABLE IF EXISTS `issuing_org_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issuing_org_level` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of entity',
  `CODE` varchar(45) NOT NULL COMMENT 'Issusing org level code',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of entity',
  `SHORT_DESCRIPTION` varchar(45) DEFAULT NULL COMMENT 'Short description of issuing org level\n',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`),
  KEY `fk__status_id__issuing_org_level__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__issuing_org_level__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__issuing_org_level__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__issuing_org_level__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__issuing_org_level__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__issuing_org_level__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Defines type of data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the menu',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the menu',
  `DISPLAY_TEXT` varchar(45) NOT NULL COMMENT 'Menu Display text\n',
  `ITEM_IMAGE` varchar(45) NOT NULL COMMENT 'URL to the image to be displayed in menu item\n',
  `TARGET` varchar(45) DEFAULT NULL COMMENT 'Valid values can be _blank, _top, target frame name etc. This will specify how the page will open on click\n',
  `URL` varchar(250) NOT NULL COMMENT 'URL to be opened on click of a menu item.\n',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of menu type',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of the application',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  KEY `fk__status_id__menu__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__menu__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__function__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__type_id__menu__entity_type_idx` (`TYPE__ID`),
  KEY `fk__app_id__menu__application_idx` (`APPLICATION__ID`),
  CONSTRAINT `fk__app_id__menu__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__menu__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__menu__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__menu__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__menu__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for menu definition';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary Key of this table',
  `CODE` varchar(50) NOT NULL COMMENT 'Message code',
  `DESCRIPTION` varchar(255) NOT NULL COMMENT 'Message details',
  `MESSAGE_TYPE` varchar(45) NOT NULL COMMENT 'Message type, Info, Warning, Error, ValidationFailure etc.',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  `MESSAGE` varchar(255) NOT NULL COMMENT 'Message details',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`),
  KEY `fk__created_by__messages__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__messages__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__status_id__messages__status_idx` (`STATUS__ID`),
  CONSTRAINT `fk__created_by__messages__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__messages__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__messages__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Messages used by the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `owners`
--

DROP TABLE IF EXISTS `owners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owners` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `ENTITY_TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to Entity Type table. Specifies the type of entity i.e. User, roles etc.',
  `ENTITY__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to the entity table for which user is defined as owner. Foreign key contraint will not be applied at DB level as it refers to multiple tables. We can derive the entity details from entity type id and entity id\n',
  `USER__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of user who is defined as owner',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USR_ENTITY_ENTITYTYPE_UNIQUE` (`USER__ID`,`ENTITY__ID`,`ENTITY_TYPE__ID`),
  KEY `fk__status_id__owners__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__owners__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__owners__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__entity_type_id__owners__entity_type` (`ENTITY_TYPE__ID`),
  KEY `FK_jc1avj8o5cqxnl48wt9scu66e` (`ENTITY__ID`),
  CONSTRAINT `FK_jc1avj8o5cqxnl48wt9scu66e` FOREIGN KEY (`ENTITY__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__owners__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__entity_type_id__owners__entity_type` FOREIGN KEY (`ENTITY_TYPE__ID`) REFERENCES `entity_type` (`ID`),
  CONSTRAINT `fk__modified_by__owners__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__owners__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__user_id__owners__user_mapping` FOREIGN KEY (`USER__ID`) REFERENCES `user_mapping` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Will contain owners of various entities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `profiling_system`
--

DROP TABLE IF EXISTS `profiling_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profiling_system` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of profiling system',
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT 'Description of profiling system',
  `CONNECTION_DETAILS` varchar(10000) NOT NULL COMMENT 'Specifies the connection details. It can be XML or JSON or another format. \n',
  `FORMAT` varchar(45) NOT NULL COMMENT 'Specifies the format in which connection details are stored i.e. XML, JSON etc.',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `fk__status_id__profiling_system__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__profiling_system__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__profiling_system__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__profiling_system__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__profiling_system__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__profiling_system__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Supported profiling systems i.e. OrgSec1 , OrgSec II etc.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the role',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of the role',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of Application to which role belongs',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of ROLE type',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  KEY `fk__type_id__role__entity_type_idx` (`TYPE__ID`),
  KEY `fk__status_id__role__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__role__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__role__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__application_id__role__applicaiton_idx` (`APPLICATION__ID`),
  CONSTRAINT `fk__application_id__role__applicaiton` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__created_by__role__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__role__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__role__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__role__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Role types supported by the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_x_attribute`
--

DROP TABLE IF EXISTS `role_x_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_x_attribute` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to role table\n',
  `ATTRIBUTE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to attribute table\n',
  `VALUE` varchar(45) NOT NULL COMMENT 'Value of the attribute linked',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLE_ATTR_VAL_UNIQUE` (`ROLE__ID`,`ATTRIBUTE__ID`,`VALUE`),
  KEY `fk__status_id__role_x_attribute__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__role_x_attribute__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__role_x_attribute__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__attribute_id__role_x_attribute__attribute` (`ATTRIBUTE__ID`),
  CONSTRAINT `fk__attribute_id__role_x_attribute__attribute` FOREIGN KEY (`ATTRIBUTE__ID`) REFERENCES `attribute` (`ID`),
  CONSTRAINT `fk__created_by__role_x_attribute__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__role_x_attribute__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__role_id__role_x_attribute__role` FOREIGN KEY (`ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__status_id__role_x_attribute__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for attribute linkage at role level';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_x_data`
--

DROP TABLE IF EXISTS `role_x_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_x_data` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'Id of role. Foreignkey to Role table',
  `DATA__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of data linked to role',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLE_DATA_UNIQUE` (`ROLE__ID`,`DATA__ID`),
  KEY `fk__status_id__role_x_data__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__role_x_data__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__role_x_data__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__data_id__role_x_data__data` (`DATA__ID`),
  CONSTRAINT `fk__created_by__role_x_data__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__data_id__role_x_data__data` FOREIGN KEY (`DATA__ID`) REFERENCES `data` (`ID`),
  CONSTRAINT `fk__modified_by__role_x_data__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__role_id__role_x_data__role` FOREIGN KEY (`ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__status_id__role_x_data__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Linkage of data with Roles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_x_function`
--

DROP TABLE IF EXISTS `role_x_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_x_function` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'Id of role. Foreignkey to Role table',
  `FUNCTION__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of function which is linked to role',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLE_FUNC_UNIQUE` (`ROLE__ID`,`FUNCTION__ID`),
  KEY `fk__status_id__role_x_function__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__role_x_function__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__role_x_function__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__function_id__role_x_function__function` (`FUNCTION__ID`),
  CONSTRAINT `fk__created_by__role_x_function__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__function_id__role_x_function__function` FOREIGN KEY (`FUNCTION__ID`) REFERENCES `function` (`ID`),
  CONSTRAINT `fk__modified_by__role_x_function__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__role_id__role_x_function__role` FOREIGN KEY (`ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__status_id__role_x_function__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Linkage of functions with Roles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_x_hierarchy`
--

DROP TABLE IF EXISTS `role_x_hierarchy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_x_hierarchy` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of role',
  `PARENT_ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of role defined as parent role',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLE_PARENT_UNIQUE` (`ROLE__ID`,`PARENT_ROLE__ID`),
  KEY `fk__status_id__role_x_hierarchy__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__role_x_hierarchy__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__role_x_hierarchy__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__parent_role_id__role_x_hierarchy__role` (`PARENT_ROLE__ID`),
  CONSTRAINT `fk__created_by__role_x_hierarchy__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__role_x_hierarchy__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__parent_role_id__role_x_hierarchy__role` FOREIGN KEY (`PARENT_ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__role_id__role_x_hierarchy__role` FOREIGN KEY (`ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__status_id__role_x_hierarchy__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to define parent and child relation between roles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Unique identifier for the table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the status i.e. Active, Inactive, Disabled etc.',
  `CODE` bigint(2) NOT NULL COMMENT 'Status codes i.e. 30,60, 90 etc.',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description of status code',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_CODE_UNIQUE` (`NAME`,`CODE`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `fk__created_by__status__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__status__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__status__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__status__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Supprorted status codes in the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subscribers`
--

DROP TABLE IF EXISTS `subscribers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscribers` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `ENTITY_TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to Entity Type table. Specifies the type of entity i.e. User, roles etc.',
  `ENTITY__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to the entity table for which user is defined as subscriber. Foreign key contraint will not be applied at DB level as it refers to multiple tables. We can derive the entity details from entity type id and entity id\n',
  `USER__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of user who is defined as subscriber',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT 'Description',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USR_ENTITY_ENTITYTYPE_UNIQUE` (`USER__ID`,`ENTITY__ID`,`ENTITY_TYPE__ID`),
  KEY `fk__status_id__subscribers__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__subscribers__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__subscribers__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__entity_type_id__subscribers__entity_type` (`ENTITY_TYPE__ID`),
  CONSTRAINT `fk__created_by__subscribers__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__entity_type_id__subscribers__entity_type` FOREIGN KEY (`ENTITY_TYPE__ID`) REFERENCES `entity_type` (`ID`),
  CONSTRAINT `fk__modified_by__subscribers__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__subscribers__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__user_id__subscribers__user_mapping` FOREIGN KEY (`USER__ID`) REFERENCES `user_mapping` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Will contain subscribers of various entities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_sequences`
--

DROP TABLE IF EXISTS `system_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_sequences` (
  `TABLE_NAME` varchar(45) NOT NULL COMMENT 'Name of table',
  `DESCRIPTION` varchar(255) NOT NULL COMMENT 'Specifies the purpose of table and type of use in the system',
  `SEQUENCE` bigint(20) unsigned NOT NULL COMMENT 'index count will be managed in this table',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  UNIQUE KEY `TABLE_UNIQUE` (`TABLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to manage indexes for all tables';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_mapping`
--

DROP TABLE IF EXISTS `user_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_mapping` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key of this table',
  `LOGIN` varchar(50) NOT NULL COMMENT 'User''s login. This is mandatory',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT 'User''s email address',
  `UNIQUE_IDENTIFIER_VALUE` varchar(50) DEFAULT NULL COMMENT 'This is the value of unique identifier for an entry in different User repositories\n',
  `UNIQUE_IDENTIFIER_KEY` varchar(50) NOT NULL DEFAULT 'UID' COMMENT 'This is the key of unique identifier in different User repositories. Adaptor''s will use this key and corresponding value to search the entry in respective repository',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `USER_REPOSITORY__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of user repository',
  `COMPANY__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the company of entity\n',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  `FIRST_NAME` varchar(50) DEFAULT NULL COMMENT 'First name of the user',
  `LAST_NAME` varchar(50) DEFAULT NULL COMMENT 'Last name of the user',
  `DEPARTMENT` varchar(50) DEFAULT NULL COMMENT 'Department of user. Master is ldap repository',
  `LDAP_STED_UID` varchar(50) DEFAULT NULL COMMENT 'LDAP_STED_UID of user. Master is ldap repository',
  `COUNTRY` varchar(50) DEFAULT NULL COMMENT 'COUNTRY of user. Master is ldap repository',
  `PASSWORD` varchar(50) NOT NULL COMMENT 'storing password of user',
  `PHONE_NUMBER` bigint(10) unsigned NOT NULL DEFAULT '8888888888' COMMENT 'contact no of user',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN_UNIQUE` (`LOGIN`),
  UNIQUE KEY `LOGIN_UNIQUE_ID__UNIQUE` (`LOGIN`,`UNIQUE_IDENTIFIER_VALUE`),
  KEY `fk__status_id__user_mapping__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__user_mapping__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__user_mapping__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__user_repository_id__user_mapping__user_repository_idx` (`USER_REPOSITORY__ID`),
  KEY `fk__company_id__user_mapping__company_idx` (`COMPANY__ID`),
  CONSTRAINT `fk__company_id__user_mapping__company` FOREIGN KEY (`COMPANY__ID`) REFERENCES `company` (`ID`),
  CONSTRAINT `fk__created_by__user_mapping__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__user_mapping__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__user_mapping__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__user_repository_id__user_mapping__user_repository` FOREIGN KEY (`USER_REPOSITORY__ID`) REFERENCES `user_repository` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='User mapping with different user repositories';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `FIRST_NAME` varchar(45) NOT NULL COMMENT 'User''s first name. This is mandatory.',
  `MIDDLE_NAME` varchar(45) DEFAULT NULL COMMENT 'User''s middle name. This is optional',
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL COMMENT 'User''s email address',
  `LOGIN` varchar(50) NOT NULL COMMENT 'User''s login. This is mandatory',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT 'User''s password. This is optional',
  `COST_CENTER` varchar(45) NOT NULL COMMENT 'User''s Cost center. This is mandatory',
  `ADDRESS` varchar(250) DEFAULT NULL COMMENT 'User''s address',
  `POSTAL_ZIP_CODE` varchar(45) DEFAULT NULL COMMENT 'User''s postal code',
  `CITY` varchar(45) DEFAULT NULL COMMENT 'User''s City',
  `STATE` varchar(45) DEFAULT NULL COMMENT 'User''s State',
  `COUNTRY` varchar(45) DEFAULT NULL COMMENT 'User''s country',
  `TITLE` varchar(5) DEFAULT NULL COMMENT 'User''s title i.e. Mr. Mrs. Ms. etc.\n',
  `JOB_FUNCTION` varchar(50) DEFAULT NULL COMMENT 'User''s Job function',
  `DEPARTMENT` varchar(45) NOT NULL COMMENT 'Specifies the department to which user belongs',
  `MANAGER_EMAIL` varchar(45) NOT NULL COMMENT 'User''s manager email address',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `TYPE__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the type of each record. For instance, this entry is of USER type',
  `COMPANY__ID` bigint(20) unsigned NOT NULL COMMENT 'Specifies the parent company of user',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN_UNIQUE` (`LOGIN`),
  KEY `STATUS_FK_idx` (`STATUS__ID`),
  KEY `CREATED_BY_FK_idx` (`CREATED_BY`),
  KEY `ENTITY_TYPE_FK_idx` (`TYPE__ID`),
  KEY `fk__modified_by__user_master__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__company_id__user_master__company_idx` (`COMPANY__ID`),
  CONSTRAINT `fk__company_id__user_master__company` FOREIGN KEY (`COMPANY__ID`) REFERENCES `company` (`ID`),
  CONSTRAINT `fk__created_by__user_master__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__user_master__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__user_master__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__type_id__user_master__entity_type` FOREIGN KEY (`TYPE__ID`) REFERENCES `entity_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='User repository for local users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_repository`
--

DROP TABLE IF EXISTS `user_repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_repository` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of user repository',
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT 'Description of user repository',
  `CONNECTION_DETAILS` varchar(10000) NOT NULL COMMENT 'Specifies the connection details. It can be XML or JSON or another format. \n',
  `FORMAT` varchar(45) NOT NULL COMMENT 'Specifies the format in which connection details are stored i.e. XML, JSON etc.',
  `LOCKED` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'Specifies if the entry has been locked by the Admin or not. Default Value ''N''. Locked Entry can''t be modified by anyone except for Admin.',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `fk__status_id__user_repository__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__user_repository__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__user_repository__user_mapping_idx` (`MODIFIED_BY`),
  CONSTRAINT `fk__created_by__user_repository__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__user_repository__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__user_repository__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Details of user repsitories supported by the system';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_x_application_x_company`
--

DROP TABLE IF EXISTS `user_x_application_x_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_x_application_x_company` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `USER__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to the User mapping table\n',
  `COMPANY__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to company table\n',
  `APPLICATION__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to application table',
  `REMARKS` varchar(250) NOT NULL COMMENT 'Remarks for cross profiling\n',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USR_APP_COMP_UNIQUE` (`USER__ID`,`APPLICATION__ID`,`COMPANY__ID`),
  KEY `fk__status_id__user_x_app_x_comp__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__user_x_app_x_comp__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__user_x_app_x_comp__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__app_id__user_x_app_x_comp__application` (`APPLICATION__ID`),
  KEY `fk__company_id__user_x_app_x_comp__company` (`COMPANY__ID`),
  CONSTRAINT `fk__app_id__user_x_app_x_comp__application` FOREIGN KEY (`APPLICATION__ID`) REFERENCES `application` (`ID`),
  CONSTRAINT `fk__company_id__user_x_app_x_comp__company` FOREIGN KEY (`COMPANY__ID`) REFERENCES `company` (`ID`),
  CONSTRAINT `fk__created_by__user_x_app_x_comp__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__user_x_app_x_comp__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__user_x_app_x_comp__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__user_id__user_x_app_x_comp__user_mapping` FOREIGN KEY (`USER__ID`) REFERENCES `user_mapping` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for cross company profiling';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_x_attribute`
--

DROP TABLE IF EXISTS `user_x_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_x_attribute` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `USER__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to the User mapping table\n',
  `ATTRIBUTE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to attribute table\n',
  `VALUE` varchar(45) NOT NULL COMMENT 'Value of the attribute linked',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USR_ATTR_VAL_UNIQUE` (`USER__ID`,`ATTRIBUTE__ID`,`VALUE`),
  KEY `fk__status_id__user_x_attr__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__user_x_attr__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__user_x_attr__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__attr_id__user_x_attr__attribute` (`ATTRIBUTE__ID`),
  CONSTRAINT `fk__attr_id__user_x_attr__attribute` FOREIGN KEY (`ATTRIBUTE__ID`) REFERENCES `attribute` (`ID`),
  CONSTRAINT `fk__created_by__user_x_attr__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__user_x_attr__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__user_x_attr__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__user_id__user_x_attr__user_mapping` FOREIGN KEY (`USER__ID`) REFERENCES `user_mapping` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for attribute linkage with users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_x_func_x_org`
--

DROP TABLE IF EXISTS `user_x_func_x_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_x_func_x_org` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `USER__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to the User mapping table\n',
  `FUNCTION__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to function table\n',
  `DATA__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to DATA table\n',
  `ACCESS_MODE__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign Key of ACCESS_MODE table\n',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USR_ATTR_VAL_UNIQUE` (`USER__ID`,`FUNCTION__ID`,`DATA__ID`,`ACCESS_MODE__ID`),
  KEY `fk__status_id__user_x_func_x_org__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__user_x_func_x_org__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__user_x_func_x_org__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__data_id__user_x_func_x_org__data` (`DATA__ID`),
  KEY `fk__function_id__user_x_func_x_org__function` (`FUNCTION__ID`),
  CONSTRAINT `fk__created_by__user_x_func_x_org__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__data_id__user_x_func_x_org__data` FOREIGN KEY (`DATA__ID`) REFERENCES `data` (`ID`),
  CONSTRAINT `fk__function_id__user_x_func_x_org__function` FOREIGN KEY (`FUNCTION__ID`) REFERENCES `function` (`ID`),
  CONSTRAINT `fk__modified_by__user_x_func_x_org__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__status_id__user_x_func_x_org__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__user_id__user_x_func_x_org__user_mapping` FOREIGN KEY (`USER__ID`) REFERENCES `user_mapping` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to link user with function and data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_x_role`
--

DROP TABLE IF EXISTS `user_x_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_x_role` (
  `ID` bigint(20) unsigned NOT NULL COMMENT 'Primary key of this table',
  `USER__ID` bigint(20) unsigned NOT NULL COMMENT 'ID of user to which role will be linked',
  `ROLE__ID` bigint(20) unsigned NOT NULL COMMENT 'Id of role. Foreignkey to Role table',
  `PRIORITY` bigint(5) DEFAULT '10' COMMENT 'Specify the priority of attribute to be loaded',
  `STATUS__ID` bigint(20) unsigned NOT NULL COMMENT 'Foreign key to status table',
  `STATUS_DATE` timestamp NULL DEFAULT NULL COMMENT 'Specifies the last date when status was changed',
  `OBSOLETE_DATE` datetime DEFAULT NULL COMMENT 'Specify the date after which the entry will become obsolete in the system and system will ignore the obsolete record',
  `CREATED_BY` bigint(20) unsigned NOT NULL COMMENT 'Contain the id of user who created the entry',
  `CREATION_DATE` timestamp NULL DEFAULT NULL COMMENT 'Creation date of the record',
  `MODIFIED_BY` bigint(20) unsigned NOT NULL COMMENT 'ID of user who modified the entry',
  `MODIFICATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'last modification date of the entry',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USR_ROLE_UNIQUE` (`USER__ID`,`ROLE__ID`),
  KEY `fk__status_id__user_x_role__status_idx` (`STATUS__ID`),
  KEY `fk__created_by__user_x_role__user_mapping_idx` (`CREATED_BY`),
  KEY `fk__modified_by__user_x_role__user_mapping_idx` (`MODIFIED_BY`),
  KEY `fk__role_id__user_x_role__role` (`ROLE__ID`),
  CONSTRAINT `fk__created_by__user_x_role__user_mapping` FOREIGN KEY (`CREATED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__modified_by__user_x_role__user_mapping` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `user_mapping` (`ID`),
  CONSTRAINT `fk__role_id__user_x_role__role` FOREIGN KEY (`ROLE__ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `fk__status_id__user_x_role__status` FOREIGN KEY (`STATUS__ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `fk__user_id__user_x_role__user_mapping` FOREIGN KEY (`USER__ID`) REFERENCES `user_mapping` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table for user role linkage';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'orgsec_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-20  1:31:33
