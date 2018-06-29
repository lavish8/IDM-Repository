package com.identity.platform.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.couchbase.client.deps.com.lmax.disruptor.EventHandler;
import com.google.common.base.Throwables;
import com.identity.platform.auth.constant.PlatformConstants;
import com.identity.platform.persistence.domain.ExceptionTrackingModel;
import com.identity.platform.persistence.util.CommonCouchbaseRepository;
import com.identity.platform.utils.error.exception.PlatformException;

public abstract class AbstractPlatformEventHandler<T extends AbstractPlatformEvent>
		implements EventHandler<AbstractPlatformEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractPlatformEventHandler.class);

	@Autowired
	private PlarformEventProducer eventProducer;

	/** The data access util. */
	@Autowired
	private CommonCouchbaseRepository<ExceptionTrackingModel, Long> commonCouchbaseRepository;

	@Override
	public void onEvent(AbstractPlatformEvent event, long sequence, boolean endOfBatch) throws PlatformException {
		 try {
	            this.handleEvent(event, sequence, endOfBatch);
	        } catch (final PlatformException e) {

	            // start : saving exception in DB
	            final ExceptionTrackingModel exceptionTracking = new ExceptionTrackingModel();
	            exceptionTracking.setId(Long.valueOf(System.currentTimeMillis()));
	            exceptionTracking.setDocType(PlatformConstants.EXCEPTION);
	            exceptionTracking.setStackTrace(Throwables.getStackTraceAsString(e));
	            exceptionTracking.setOrigin(this.getClass().getSimpleName());
	            //exceptionTracking.createdDate = new java.util.Date().getTime();
	            this.commonCouchbaseRepository.save(exceptionTracking);

	            // ends : saving exception in DB

	            LOG.error("Error occours", e);
	        }
	}
	
	   public abstract void handleEvent(AbstractPlatformEvent event, long sequence,
	            boolean endOfBatch) throws PlatformException;

}
