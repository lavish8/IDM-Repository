SET SCHEMA ORGSEC_DB;
SET REFERENTIAL_INTEGRITY FALSE;
--
-- Dumping data for table `access_mode`
--

--INSERT INTO access_mode (ID, CODE, DESCRIPTION, LOCKED, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'A','Add','N',1,'2014-01-22 19:41:14',NULL,2,'2014-01-22 19:41:14',2,'2014-01-22 19:41:15'),(2,'B','Browse','N',1,'2014-01-22 19:41:15',NULL,2,'2014-01-22 19:41:15',2,'2014-01-22 19:41:15'),(3,'BU','Browse Update','N',1,'2014-01-22 19:41:15',NULL,2,'2014-01-22 19:41:15',2,'2014-01-22 19:41:15'),(4,'ABU','Add Browse Update','N',1,'2014-01-22 19:41:15',NULL,2,'2014-01-22 19:41:15',2,'2014-01-22 19:41:15'),(5,'ABDU','Add Browse Delete Update','N',1,'2014-01-22 19:41:15',NULL,2,'2014-01-22 19:41:15',2,'2014-01-22 19:41:15');

--
-- Dumping data for table `access_type`
--

--INSERT INTO access_type (ID, NAME, DESCRIPTION, LOCKED, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'ROLE','Role based access support','N',1,NULL,NULL,2,NULL,2,'2014-01-22 12:34:15');

--
-- Dumping data for table `application`
--

--INSERT INTO application (ID, NAME, DESCRIPTION, LOCKED, ACCESS_TYPE__ID, TYPE__ID, DEFAULT_ROLE__ID, AUTHENTICATION_TYPE, COMP_LEVEL_ACCESS_REQD, LOWER_LEVEL_DD_REQD, MENU_REQD, ACCESS_SELECTION_REQD, SUBSEQUENT_DATA_LOAD_REQD, SUBSEQUENT_CHILD_DATA, SUBSEQUENT_COMP_ACCESS, ENVIRONMENT, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'OrgSec III','OrgSec III Application','N',1,3,1,'CAS','N','Y','N','Y','Y','Y','Y','INTRANET/EXTRANET',1,NULL,NULL,1,NULL,1,'2014-01-20 16:15:21'),(2,'DRP','DRP application description','N',1,3,1,'SELF','N','N','Y','N','N','N','N','INTRANET',1,NULL,'2013-01-29 00:11:00',1,NULL,1,'2014-01-20 16:15:21'),(3,'PRISMA Web','PRISMA Web application description','N',1,3,1,'CAS','Y','N','Y','N','N','N','N','E2O',1,NULL,'2013-01-29 00:11:00',1,NULL,1,'2014-01-20 16:15:21'),(10,'PRIS','Pris test','',1,3,NULL,'CAS','Y','Y','Y','Y','Y','Y','Y','INTRANET/EXTRANET',1,'2016-02-22 20:05:55',NULL,2,'2016-02-22 20:05:55',2,'2016-02-22 20:06:07'),(11,'IP','Pris test','',1,3,NULL,'CAS','Y','Y','Y','Y','Y','Y','Y','INTRANET/EXTRANET',1,'2016-02-24 19:47:18',NULL,2,'2016-02-24 19:47:18',2,'2016-02-24 19:47:55'),(13,'IP 2','IP 2 test update','',1,3,NULL,'CAS','Y','Y','Y','Y','Y','Y','Y','INTRANET/EXTRANET',1,'2016-02-25 23:39:38',NULL,2,'2016-02-25 23:39:38',2,'2016-02-28 20:52:14'),(14,'PRISMA','Prisma track 2 test','',1,3,NULL,'CAS','Y','Y','Y','Y','Y','Y','Y','INTRANET',2,'2016-02-28 23:36:47',NULL,2,'2016-02-28 23:28:46',2,'2016-02-28 23:37:13');

--
-- Dumping data for table `application_configuration`
--


--
-- Dumping data for table `application_x_attribute`
--


--
-- Dumping data for table `attribute`
--


--
-- Dumping data for table `company`
--

INSERT INTO company (ID, NAME, CODE, DESCRIPTION, COMPANY_TYPE, LOCKED, TYPE__ID, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'ALL COMPANIES','ALL_COMPANIES','All companies','PARENT COMPANY','N',2,1,'2013-07-09 10:11:45',NULL,2,'2013-07-09 10:11:45',2,'2013-07-09 12:18:03'),(2,'STMicroelectronics','STM','STMicroelectronics Pvt. Ltd','PARENT COMPANY','N',2,1,'2013-07-09 10:11:45',NULL,2,'2013-07-09 10:11:45',2,'2013-07-09 12:18:03'),(3,'Numonyx','NMX','Numonyx','JV COMPANY','N',2,1,'2013-07-09 10:11:45',NULL,2,'2013-07-09 10:11:45',2,'2013-07-09 12:18:03'),(4,'STEricsson','SNW','STEricsson','JV COMPANY','N',2,1,'2013-07-09 10:11:45',NULL,2,'2013-07-09 10:11:45',2,'2013-07-09 12:18:03');

--
-- Dumping data for table `company_x_entity`
--


--
-- Dumping data for table `component`
--


--
-- Dumping data for table `composite_role_def`
--


--
-- Dumping data for table `data`
--


--
-- Dumping data for table `data_repository`
--


--
-- Dumping data for table `delegation`
--


--
-- Dumping data for table `entity_type`
--

INSERT INTO entity_type (ID, NAME, DESCRIPTION, TABLE_NAME, LOCKED, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'USER','USER','USER_MAPPING','N',1,'2013-07-09 10:00:37',NULL,2,'2013-07-09 10:00:37',2,'2013-07-09 12:19:33'),(2,'COMPANY','COMPANY','COMPANY','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-07-09 12:19:33'),(3,'APPLICATION','APPLICATION','APPLICATION','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-07-09 12:19:33'),(4,'ATTRIBUTE','ATTRIBUTE','ATTRIBUTE','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-07-09 12:19:33'),(5,'COMPONENT','COMPONENT','COMPONENT','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-07-09 12:19:33'),(6,'DATA','DATA','DATA','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-07-09 12:19:33'),(7,'FUNCTION','FUNCTION','FUNCTION','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-07-09 12:19:33'),(8,'GROUP','GROUP','GROUP','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-07-09 12:19:33'),(9,'MENU','MENU','MENU','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-12-23 21:35:54'),(10,'ROLE','ROLE','ROLE','N',1,'2013-07-09 10:07:33',NULL,2,'2013-07-09 10:07:33',2,'2013-07-09 12:19:33');

--
-- Dumping data for table `function`
--


--
-- Dumping data for table `function_x_component`
--


--
-- Dumping data for table `function_x_menu`
--


--
-- Dumping data for table `group`
--


--
-- Dumping data for table `group_x_roles`
--


--
-- Dumping data for table `group_x_users`
--


--
-- Dumping data for table `help`
--

--INSERT INTO help (ID, FIELD_ID, MESSAGE, LOCKED, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'mngDataCmpnyId','Company help is returned.','N',1,'2014-03-27 09:56:54',NULL,1,'2014-03-27 09:56:54',1,'2014-03-27 10:11:54'),(2,'mngDataNameId','Name Help is returned.','N',1,'2014-03-27 10:01:48',NULL,1,'2014-03-27 10:01:48',1,'2014-03-27 10:11:54'),(3,'mngDataCodeId','Code help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(4,'mngDataTypeId','Type help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(5,'mngDataParentNameId','Parent Name help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(6,'mngDataDescId','Description help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(7,'mngDataIssueOrgLvlId','Issuing Org Level help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(8,'mngDataStatusId','Status help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(9,'mngDataObsoleteDateId','Date help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(10,'appManageID','appManageID help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(11,'appDescriptionid','appDescriptionid Help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(12,'appCreateTypeID','appCreateTypeID help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(13,'appProID','appProID help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(14,'appProfilingId','appProfilingId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-04-15 11:03:58'),(15,'ownerID','ownerID help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(16,'appOwnerId','appOwnerId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(17,'appSearchAccessTypeId','appSearchAccessTypeId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(18,'appDefaultacces','appDefaultacces help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(19,'appEnviron','appEnviron help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(20,'appObsoletedate','appObsoletedate help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(21,'appManageNameid','appManageNameid help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(22,'appStatusId','appStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(23,'applicationSearchId','applicationSearchId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-04-15 11:16:13'),(24,'appSrchFunId','appSrchFunId Help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(25,'appSrcMenuId','appSrcMenuId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-04-15 11:16:13'),(26,'appSrchStatusId','appSrchStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-04-15 11:16:13'),(27,'srcAppcomponentid','srcAppcomponentid help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-04-15 11:16:13'),(28,'appSrchOwnId','appSrchOwnId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(29,'srcAppAuthenticationId','srcAppAuthenticationId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-04-15 11:20:20'),(30,'appSrchEvnId','appSrchEvnId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(31,'srcAppTypeId','srcAppTypeId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(32,'appCreateNameid','appCreateNameid help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(33,'appCreateDescriptionid','appCreateDescriptionid Help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(34,'appCreateProfilingId','appCreateProfilingId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(35,'appCreateOwnerId','appCreateOwnerId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(36,'appCreateAccesstypeId','appCreateAccesstypeId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(37,'crtAppTypeId','crtAppTypeId is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(38,'appCreateEnvironmentId','appCreateEnvironmentId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(39,'CreateDefAccessID','CreateDefAccessID help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(40,'appCreateStatusId','appCreateStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(41,'appCreateObsoletedateId','appCreateObsoletedateId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(42,'reportTypesearchId','reportTypesearchId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(43,'createUserLastNameId','createUserLastNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(44,'createUserObsoleteDateId','createUserObsoleteDateId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(45,'createUserEmailId','createUserEmailId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(46,'createUserUsrRepsId','createUserUsrRepsId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(47,'createUserStatusId','createUserStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(48,'createUserCntryId','createUserCntryId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(49,'createUserCompanyId','createUserCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(50,'createUserOrgId','createUserOrgId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(51,'createUserLocationId','createUserLocationId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(52,'createUserNameId','createUserNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(53,'createUserLoginId','createUserLoginId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(54,'createUserPageid','createUserPageid help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-27 10:11:54'),(55,'userSearchLoginId','userSearchLoginId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(56,'userSearchFirstNameId','userSearchFirstNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(57,'userSearchLocationId','userSearchLocationId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(58,'userSearchOrganizationId','userSearchOrganizationId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(59,'userSearchCompanyId','userSearchCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(60,'userSearchUserRepositoryId','userSearchUserRepositoryId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(61,'userSearchStatusId','userSearchStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(62,'userSearchCountryId','userSearchCountryId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(63,'userSearchLastNameId','userSearchLastNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(64,'userSearchEmailId','userSearchEmailId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(65,'userSearchObsoleteDateId','userSearchObsoleteDateId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(66,'mngUserLastNameId','mngUserLastNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:27:44'),(67,'mngUserObsoleteDateId','mngUserObsoleteDateId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:27:45'),(68,'mngUserEmailId','mngUserEmailId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:27:45'),(69,'mngUserUsrRepsId','mngUserUsrRepsId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:27:45'),(70,'mngUserStatusId','mngUserStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:27:45'),(71,'mngUserCntryId','mngUserCntryId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:27:45'),(72,'mngUserCompanyId','mngUserCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:27:45'),(73,'mngUserOrgId','mngUserOrgId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:27:45'),(74,'mngUserLocationId','mngUserLocationId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(75,'mngUserNameId','mngUserNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(76,'mngUserLoginId','mngUserLoginId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(77,'mngUserPageid','mngUserPageid help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(78,'mngUserRoleAppId','mngUserRoleAppId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(79,'mngUserRoleTypeId','mngUserRoleTypeId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(80,'mngUserRoleCompanyId','mngUserRoleCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(81,'mngUserRoleNameId','mngUserRoleNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(82,'mngUserFunApplicationId','mngUserFunApplicationId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(83,'mngUserFunCompanyId','mngUserFunCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(84,'mngUserFunNameId','mngUserFunNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(85,'mngUserAttrStatusId','mngUserAttrStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(86,'mngUserAttrAppId','mngUserAttrAppId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(87,'mngUserAttrCompanyId','mngUserAttrCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(88,'mngUserAttrNameId','mngUserAttrNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(89,'mngUserGrpDescriptionId','mngUserGrpDescriptionId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(90,'mngUserGrpCompanyId','mngUserGrpCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(91,'mngUserGrpNameId','mngUserGrpNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(92,'mngAttrNameId','mngAttrNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:39:36'),(93,'mngAttrOlDateId','mngAttrOlDateId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(94,'mngAttrDescId','mngAttrDescId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(95,'mngAttrCompanyId','mngAttrCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(96,'mngAttrStatusId','mngAttrStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(97,'mngAttrAppId','mngAttrAppId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(98,'LinkAttrAppId','LinkAttrAppId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(99,'LinkAttrRoleNameId','LinkAttrRoleNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(100,'linkAttrRoleLoginId','linkAttrRoleLoginId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(101,'createGrpId','createGrpId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 14:16:05'),(102,'createGrpNameId','createGrpNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 14:16:05'),(103,'createGrpOlDateId','createGrpOlDateId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 14:16:05'),(104,'createGrpDescId','createGrpDescId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 14:16:05'),(105,'createGrpCompanyId','createGrpCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(106,'createGrpStatusId','createGrpStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(107,'srchGrpNameId','srchGrpNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(108,'srchGrpOlDateId','srchGrpOlDateId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(109,'srchGrpDescId','srchGrpDescId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(110,'srchGrpCompanyId','srchGrpCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(111,'srchGrpStatusId','srchGrpStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(112,'mngGrpId','mngGrpId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(113,'mngGrpNameId','mngGrpNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(114,'mngGrpOlDateId','mngGrpOlDateId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(115,'mngGrpDescId','mngGrpDescId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(116,'mngGrpCompanyId','mngGrpCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(117,'mngGrpStatusId','mngGrpStatusId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(118,'mngGrpUsersLoginId','mngGrpUsersLoginId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(119,'mngGrpUsersLocationId','mngGrpUsersLocationId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(120,'mngGrpUsersCompanyId','mngGrpUsersCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(121,'mngGrpUsersEmailId','mngGrpUsersEmailId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(122,'mngGrpRoleId','mngGrpRoleId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(123,'mngGrpRolesNameId','mngGrpRolesNameId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(124,'mngGrpRoleCompanyId','mngGrpRoleCompanyId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(125,'mngGrpRoleTypeId','mngGrpRoleTypeId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(126,'mngGrpRoleApplicationId','mngGrpRoleApplicationId help is returned.','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(127,'createRlNameId','createRlNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 15:16:32'),(128,'createRlAppId','createRlAppId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 15:16:32'),(129,'createRlCmpnyId','createRlCmpnyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 15:16:32'),(130,'createRlObsoleteDateId','createRlObsoleteDateId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 15:16:32'),(131,'createRlDescId','createRlDescId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 15:16:32'),(132,'createRlStatusId','createRlStatusId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 15:16:32'),(133,'createRlTypeId','createRlTypeId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(134,'createCmpRlNameId','createCmpRlNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(135,'createCmpRlAppId','createCmpRlAppId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(136,'createCmpRlCmpnyId','createCmpRlCmpnyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(137,'createCmpRlObsoleteDateId','createCmpRlObsoleteDateId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(138,'createCmpRlDescId','createCmpRlDescId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(139,'createCmpRlStatusId','createCmpRlStatusId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(140,'createCmpRlTypeId','createCmpRlTypeId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(141,'roleSrchNameId','roleSrchNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(142,'roleSrchDescId','roleSrchDescId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(143,'roleSrchFunNameId','roleSrchFunNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(144,'roleSrchCompNameId','roleSrchCompNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(145,'roleSrchMenuTextId','roleSrchMenuTextId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(146,'roleSrchCmpnyId','roleSrchCmpnyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(147,'roleSrchAppId','roleSrchAppId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(148,'roleSrchStatusId','roleSrchStatusId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(149,'roleSrchTypeId','roleSrchTypeId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(150,'roleSrchObsoleteDateId','roleSrchObsoleteDateId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(151,'roleSrchDataAxisNameId','roleSrchDataAxisNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(152,'mngRlNameId','mngRlNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(153,'mngRlAppId','mngRlAppId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(154,'mngRlCmpnyId','mngRlCmpnyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(155,'mngRlObsoleteDateId','mngRlObsoleteDateId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(156,'mngRlDescId','mngRlDescId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(157,'mngRlStatusId','mngRlStatusId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(158,'mngRlTypeId','mngRlTypeId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(159,'mngRlDataNameId','mngRlDataNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(160,'mngRlDataTypeId','mngRlDataTypeId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(161,'mngRlDataCodeId','mngRlDataCodeId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(162,'mngRlDataCompanyId','mngRlDataCompanyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(163,'mngRlDataStatusId','mngRlDataStatusId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(164,'mngRlAttrNameId','mngRlAttrNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(165,'mngRlAttrCompanyId','mngRlAttrCompanyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(166,'mngRlAttrAppId','mngRlAttrAppId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(167,'mngRlAttrStatusId','mngRlAttrStatusId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(168,'mngRlSecurNameId','mngRlSecurNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(169,'mngRlSecurCompanyId','mngRlSecurCompanyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(170,'mngRlSecurEmailId','mngRlSecurEmailId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(171,'mngRlGrpNameId','mngRlGrpNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(172,'mngRlHrchyCompanyId','mngRlHrchyCompanyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(173,'mngRlUsersLoginId','mngRlUsersLoginId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(174,'mngRlUsersFirstNameId','mngRlUsersFirstNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(175,'mngRlUsersCompanyId','mngRlUsersCompanyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(176,'mngRlUserslastNameId','mngRlUserslastNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(177,'mngRlUsersEmailId','mngRlUsersEmailId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(178,'mngRlGrpNameId','mngRlGrpNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(179,'mngRlGrpCompanyId','mngRlGrpCompanyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(180,'mngRlGrpDescriptionId','mngRlGrpDescriptionId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(181,'cmpRlNameId','cmpRlNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(182,'cmpRlAppId','cmpRlAppId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(183,'cmpRlCmpnyId','cmpRlCmpnyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(184,'cmpRlObsoleteDateId','cmpRlObsoleteDateId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(185,'cmpRlDescId','cmpRlDescId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(186,'cmpRlStatusId','cmpRlStatusId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(187,'cmpRlTypeId','cmpRlTypeId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(188,'cmpRlUsersLoginId','cmpRlUsersLoginId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(189,'cmpRlUsersFirstNameId','cmpRlUsersFirstNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(190,'cmpRlUsersCompanyId','cmpRlUsersCompanyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(191,'cmpRlUserslastNameId','cmpRlUserslastNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(192,'cmpRlUsersEmailId','cmpRlUsersEmailId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(193,'cmpRlGrpNameId','cmpRlGrpNameId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(194,'cmpRlGrpCompanyId','cmpRlGrpCompanyId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52'),(195,'cmpRlGrpDescriptionId','cmpRlGrpDescriptionId','N',1,'2014-03-27 10:06:44',NULL,1,'2014-03-27 10:06:44',1,'2014-03-28 13:13:52');

--
-- Dumping data for table `issuing_org_level`
--


--
-- Dumping data for table `menu`
--


--
-- Dumping data for table `message`
--


--
-- Dumping data for table `owners`
--

--INSERT INTO owners (ID, ENTITY_TYPE__ID, ENTITY__ID, USER__ID, DESCRIPTION, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (7,3,10,2,'Owner for PRIS',1,'2016-02-22 20:05:55',NULL,2,'2016-02-22 20:05:55',2,'2016-02-22 20:06:08'),(8,3,11,2,'Owner for IP',1,'2016-02-24 19:47:18',NULL,2,'2016-02-24 19:47:18',2,'2016-02-24 19:47:55'),(9,3,13,1,'Owner for IP 2',1,'2016-02-25 23:39:38',NULL,2,'2016-02-25 23:39:38',2,'2016-02-28 23:23:31'),(10,3,14,1,'Owner for PRISMA',1,'2016-02-28 23:28:46',NULL,2,'2016-02-28 23:28:46',2,'2016-02-28 23:29:24');

--
-- Dumping data for table `profiling_system`
--

--INSERT INTO profiling_system (ID, NAME, DESCRIPTION, CONNECTION_DETAILS, FORMAT, LOCKED, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'OrgSec III','OrgSec III','<configuration><protocol>jdbc</protocol><host>lxora.dlh.st.com</host><port>3316</port><datarepository>orgsec_db</datarepository><username>orgsec_user</username><password>12:75:25:55:110:46:109:81:125:54:15:70:14:14:11:63:40</password></configuration>','','N',1,'2013-07-09 11:30:18',NULL,2,'2013-07-09 11:30:18',2,'2013-07-09 12:20:15'),(2,'OrgSec1','OrgSec1','<configuration><protocol>jdbc</protocol><host>lxora.dlh.st.com</host><port>1561</port><datarepository>sicom10g</datarepository><username>dlh_synch</username><password>30:93:75:79:74:44:62:118:59:57:53:56:29:12:111:92:6:12:70</password></configuration','','N',1,'2013-07-09 11:30:18',NULL,2,'2013-07-09 11:30:18',2,'2013-07-09 12:20:15');

--
-- Dumping data for table `role`
--


--
-- Dumping data for table `role_x_attribute`
--


--
-- Dumping data for table `role_x_data`
--


--
-- Dumping data for table `role_x_function`
--


--
-- Dumping data for table `role_x_hierarchy`
--


--
-- Dumping data for table `status`
--

INSERT INTO status (ID, NAME, CODE, DESCRIPTION, LOCKED, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'ACTIVE',30,'ACTIVE','N',NULL,2,NULL,2,'2013-07-09 12:21:02'),(2,'INACTIVE',60,'INACTIVE','N',NULL,2,NULL,2,'2013-07-09 12:21:02'),(3,'OBSOLETE',90,'OBSOLETE','N',NULL,2,NULL,2,'2013-07-09 12:21:02'),(4,'LOCKED',100,'LOCKED','N',NULL,2,NULL,2,'2013-07-09 12:21:02');

--
-- Dumping data for table `subscribers`
--


--
-- Dumping data for table `system_sequences`
--

--INSERT INTO system_sequences (TABLE_NAME, DESCRIPTION, SEQUENCE, MODIFICATION_DATE) VALUES ('access_mode','',6,'2014-01-22 19:41:15'),('access_type','',2,'2014-01-22 12:48:45'),('application','',15,'2016-02-28 23:29:15'),('application_configuration','',1,'2014-01-22 12:48:45'),('application_x_attribute','',1,'2014-01-22 12:48:45'),('attribute','',1,'2014-03-11 17:55:30'),('company','',5,'2014-01-22 12:48:45'),('company_x_entity','',1,'2014-01-22 12:48:45'),('component','',1,'2014-01-22 12:48:45'),('composite_role_def','',1,'2014-01-22 12:48:45'),('data','',1,'2014-01-22 12:48:45'),('data_repository','',1,'2014-01-22 12:48:45'),('delegation','',1,'2014-01-22 12:48:45'),('entity_type','',11,'2014-01-22 12:48:45'),('function','',1,'2014-01-22 12:48:45'),('function_x_component','',1,'2014-01-22 12:48:45'),('function_x_menu','',1,'2014-01-22 12:48:45'),('group','',1,'2014-01-22 12:48:45'),('group_x_roles','',1,'2014-01-22 12:48:45'),('group_x_users','',1,'2014-01-22 12:48:45'),('help','',196,'2014-04-22 13:33:44'),('issuing_org_level','',1,'2014-01-22 12:48:45'),('menu','',1,'2014-01-22 12:48:45'),('message','',1,'2014-04-22 13:54:04'),('owners','',11,'2016-02-28 23:29:19'),('profiling_system','',3,'2014-01-22 12:48:45'),('role','',1,'2014-01-22 12:48:45'),('role_x_attribute','',1,'2014-01-22 12:48:45'),('role_x_data','',1,'2014-01-22 12:48:45'),('role_x_function','',1,'2014-01-22 12:48:45'),('role_x_hierarchy','',1,'2014-01-22 12:48:45'),('status','',5,'2014-01-22 12:48:45'),('subscribers','',1,'2014-01-22 12:48:45'),('user_mapping','',3,'2014-01-22 12:48:45'),('user_master','',2,'2014-01-22 12:48:45'),('user_repository','',4,'2014-01-22 12:48:45'),('user_x_application_x_company','',1,'2014-01-22 12:48:45'),('user_x_attribute','',1,'2014-01-22 12:48:45'),('user_x_func_x_org','',1,'2014-01-22 12:48:45'),('user_x_role','',1,'2014-01-22 12:48:45');

--
-- Dumping data for table `user_mapping`
--

INSERT INTO user_mapping (ID, LOGIN, EMAIL, UNIQUE_IDENTIFIER_VALUE, UNIQUE_IDENTIFIER_KEY, LOCKED, USER_REPOSITORY__ID, COMPANY__ID, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE, FIRST_NAME, LAST_NAME, DEPARTMENT, LDAP_STED_UID, COUNTRY, PASSWORD, PHONE_NUMBER) VALUES (1,'Administrator','sicom.orgsec@st.com','1','ID','N',1,1,1,'2013-07-09 08:30:59',NULL,1,'2013-07-09 08:30:59',1,'2017-11-25 01:30:52',NULL,NULL,NULL,NULL,NULL,'fe5c3358bc09de0f6717b92dc82e5d98',8888888888),(2,'VIVEKC','vivek.chauhan@st.com','VIVEKC','UID','N',2,2,1,'2013-07-09 11:51:12',NULL,1,'2013-07-09 11:51:12',1,'2017-11-24 09:53:21',NULL,NULL,NULL,'',NULL,'',8888888888);

--
-- Dumping data for table `user_master`
--

--INSERT INTO user_master (ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL, LOGIN, PASSWORD, COST_CENTER, ADDRESS, POSTAL_ZIP_CODE, CITY, STATE, COUNTRY, TITLE, JOB_FUNCTION, DEPARTMENT, MANAGER_EMAIL, LOCKED, TYPE__ID, COMPANY__ID, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'OrgSec III','Admin','User','sicom.orgsec@st.com','Administrator',NULL,'GN7441','STMicroelectronics Pvt. Ltd.','201308','Greater Noida','Uttar Pradesh','India','App','Application User','ICT','vivek.chauhan@st.com','N',1,1,1,'2013-07-09 08:26:33',NULL,1,'2013-07-09 08:26:33',1,'2013-07-09 08:27:19');

--
-- Dumping data for table `user_repository`
--

INSERT INTO user_repository (ID, NAME, DESCRIPTION, CONNECTION_DETAILS, FORMAT, LOCKED, STATUS__ID, STATUS_DATE, OBSOLETE_DATE, CREATED_BY, CREATION_DATE, MODIFIED_BY, MODIFICATION_DATE) VALUES (1,'LOCAL','Local database','<configuration><protocol>jdbc</protocol><host>lxora.dlh.st.com</host><port>3316</port><datarepository>orgsec_db</datarepository><username>orgsec_user</username><password>12:75:25:55:110:46:109:81:125:54:15:70:14:14:11:63:40</password></configuration>','','N',1,NULL,NULL,2,NULL,2,'2013-07-17 08:37:20'),(2,'E-DIRECTORY','ST standard user base','<configuration><protocol>ldap</protocol><host>ldap-read.sgp.st.com</host><port>389</port><basedn>ou=people, dc=st, dc=com</basedn><username>uid=prs user,cn=orgsec2,ou=Applications,dc=st,dc=com</username><password>62:33:86:71:65:27:83:112:114:60:51:97:33:65:64:15:98:58:20</password></configuration>','','N',1,NULL,NULL,2,NULL,2,'2013-07-09 12:15:11'),(3,'ACTIVE DIRECTORY (EXTRANET)','ST standard user base for extranet','<configuration><protocol>ldap</protocol><host>eun2190.sgp.st.com</host><port>389</port><basedn>OU=People,DC=stp-dev,DC=st,DC=com</basedn><username>CN=PRS User,OU=AdminAccounts,DC=stp-dev,DC=st,DC=com</username><password>62:33:86:71:65:27:83:112:114:60:51:97:33:65:64:15:98:58:20</password></configuration>','','N',1,NULL,NULL,2,NULL,2,'2013-07-09 12:15:11');

--
-- Dumping data for table `user_x_application_x_company`
--


--
-- Dumping data for table `user_x_attribute`
--


--
-- Dumping data for table `user_x_func_x_org`
--


--
-- Dumping data for table `user_x_role`
--


--
-- Dumping events for database 'orgsec_db'
--

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
SET REFERENTIAL_INTEGRITY TRUE;