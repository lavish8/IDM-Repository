package com.identity.manager.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import com.identity.manager.exceptions.Message;
import com.identity.manager.exceptions.Message.MessageType;

public class GeneralUtility {
	private static Random random = new Random();
	
	private GeneralUtility(){};
	
	public static String getUniqueId() {
		DateFormat dateFormat= new SimpleDateFormat(GeneralBaseConstants.DATE_FORMAT);//GeneralUtilityConstants.dateFormat
		String formattedDate = dateFormat.format(Calendar.getInstance().getTime());
		return  formattedDate+ "-" + random.nextInt(GeneralBaseConstants.RANDOM_INCREMENTOR);
	}
	public static Message prepareMessage(String i18nCode, String... arguments){
		Message message = new Message();
		message.setI18nCode(i18nCode);
		message.setMessageArguments(arguments);
		return message;
	}
	
	public static Message prepareMessage(String i18nCode,MessageType messageType, String... arguments){
		return new Message(i18nCode,messageType,arguments);
	}
	
	public static String getStackTrace(Throwable e) {
		StringWriter sWriter = new StringWriter();
		PrintWriter pWriter = new PrintWriter(sWriter);
		e.printStackTrace(pWriter);
		return sWriter.toString();
	}
}
