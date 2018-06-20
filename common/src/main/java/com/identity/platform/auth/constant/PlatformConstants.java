package com.identity.platform.auth.constant;

public class PlatformConstants {

	public static final String INVALID_REQUEST = "Invalid Request";

	/** String when message not found for given key/code. */
	public static final String MSG_NOT_FOUND = "error msg not found";

	/** Substring for key of developer message */
	public static final String DEVELOPER_MSG_SUBCODE = "developer.message";
	
	 /** The Constant FIRST_PAGE. */
    public static final int FIRST_PAGE = 1;

    /** The Constant PAGE_SIZE. */
    public static final int PAGE_SIZE = 10;

	public static final String EXCEPTION = "Exception";
	
	
	/**
     * The Enum Health.
     */
    public enum Health {

        /** The db healthy. */
        DB_HEALTHY("db_healthy"),
        /** The api healthy. */
        API_HEALTHY("api_healthy");

        /** The value. */
        private final String value;

        /**
         * Instantiates a new health.
         *
         * @param value
         *            the value
         */
        Health(String value) {
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public String getValue() {
            return this.value;
        }
    }
    
    /**
     * The Enum Info.
     */
    public enum Info {

        /** The service name. */
        SERVICE_NAME("info.service-name"),

        /** The service version. */
        SERVICE_VERSION("info.service-version");

        /** The value. */
        private final String value;

        /**
         * Instantiates a new info.
         *
         * @param value
         *            the value
         */
        Info(String value) {
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public String getValue() {
            return this.value;
        }
    }
    
    public enum PaginationParameters {

        /** The cur page. */
        curPage,
        /** The page size. */
        pageSize,
        /** The total pages. */
        totalPages,
        /** The total count. */
        totalCount,
        /** The next. */
        next,
        /** The previous. */
        previous,
        /** The first. */
        first,
        /** The last. */
        last,

        /** The self. */
        self
    }
    
    /**
     * The Enum ErrorLabelKey.
     */
    public enum ErrorLabelKey {

        /** The schema validation failed. */
        SCHEMA_VALIDATION_FAILED("schema.validation.failed"),

        /** The object already exist. */
        OBJECT_ALREADY_EXIST("object.already.exist"),

        /** The resource ref exist in resources. */
        RESOURCE_REF_EXIST_IN_RESOURCES("resource.ref.exist.in.resources"),

        /** The invalid date format. */
        INVALID_DATE_FORMAT("invalid.date.format"),
        
        /** The learning asset not found. */
        LEARNING_ASSET_NOT_FOUND("learning.asset.not.found"),

        /** The invalid request. */
        INVALID_REQUEST("Invalid Request"),
        
        /** The study plan not enabled. */
        STUDY_PLAN_NOT_ENABLED("study.plan.not.enabled"),

        /** The get rest client data failed. */
        GET_REST_CLIENT_DATA_FAILED("get.rest.client.data.failed"),

        /** The not blank. */
        NOT_BLANK("not.blank"),
        /** The object not found. */
        OBJECT_NOT_FOUND("object.not.found"),

        /** The criteria failed. */
        CRITERIA_FAILED("criteria.failed"),

        /** The json to object mapping failed. */
        JSON_TO_OBJECT_MAPPING_FAILED("json.to.object.mapping.failed"),

        /** The allowed status value failed. */
        ALLOWED_STATUS_VALUE_FAILED("allowed.status.value.failed"),

        /** The service not ready. */
        SERVICE_NOT_READY("service.not.ready"),

        /** The db not healthy. */
        DB_NOT_HEALTHY("db.not.healthy");

        /** The value. */
        private final String value;

        /**
         * Instantiates a new error label key.
         *
         * @param value
         *            the value
         */
        ErrorLabelKey(String value) {
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public String getValue() {
            return this.value;
        }

    }
    
    public enum SupporedDatabase {
        COUCHBASESERVER,
        MYSQL;
    }
    
    /**
     * enum define for the database configuration.
     */
    public enum CouchBaseDatabaseConfig {

        /** The couchbaseserver bucketname. */
    	SUPPORTED_COUCHBASE_DATEBASE("supported.couchbase.database"),
    	COUCHBASESERVER_COUCHBASE_USERNAME("couchbaseserver.couchbase.username"),
    	COUCHBASESERVER_COUCHBASE_PASSWORD("couchbaseserver.couchbase.password"),
        COUCHBASESERVER_BUCKETNAME("couchbaseserver.bucket.name"),
        COUCHBASESERVER_BUCKET_PASSWORD("couchbaseserver.bucket.password"),
    	COUCHBASESERVER_HOSTNAME("couchbaseserver.hostName"),
    	COUCHBASESERVER_DEFAULT_ASYNC_MODE("couchbase-env-config.async-mode"), //false
    	COUCHBASESERVER_DB_TRACE_ENABLED("couchbase.dbTraces.enabled"); 	//false
    	
        /** The val. */
        private final String val;

        CouchBaseDatabaseConfig(final String valueProp) {
            this.val = valueProp;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public String getValue() {
            return this.val;
        }
    }

}
