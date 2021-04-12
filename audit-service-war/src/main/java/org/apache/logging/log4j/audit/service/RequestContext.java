/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.log4j.audit.service;

import java.util.UUID;
import java.util.function.Supplier;

import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.audit.annotation.Chained;
import org.apache.logging.log4j.audit.annotation.ClientServer;
import org.apache.logging.log4j.audit.annotation.HeaderPrefix;
import org.apache.logging.log4j.audit.annotation.Local;
import org.apache.logging.log4j.core.util.NetUtils;
import org.apache.logging.log4j.core.util.UuidUtil;

/**
 * Defines all the variables that an application needs to be available in the ThreadContext for audit logging and
 * general application usage.
 */
@HeaderPrefix("mycorp-context-")
public final class RequestContext {
    @ClientServer
    public static final String REQUEST_ID = "requestId";
    private String requestId;
    @ClientServer
    public static final String SESSION_ID = "sessionId";
    private String sessionId;
    
    @ClientServer
    public static final String IP_ADDRESS = "ipAddress";
    private String ipAddress;
    @ClientServer
    public static final String USER_ID = "userId";
    private String userId;
    
	@ClientServer
	public static final String PROGRAM_NAME= "programName";
	private String programName;
	@ClientServer
	public static final String PROGRAM_ID= "programId";
	private Integer programId;
	@ClientServer
	public static final String ACTION= "action";
	private String action;
	
	@ClientServer
	public static final String MU_ID= "muId";
	private Integer muId;
	
	@ClientServer
	public static final String CT_ID= "ctId";
	private Integer ctId;
	@ClientServer
	public static final String ASSOCIATED_SKILL= "associatedSkill";
	private String associatedSkill;
	@ClientServer
    public static final String SKILL_LEVEL = "skillLevel";
    private Integer skillLevel;
    @Local
    public static final String CALLING_HOST = "callingHost";
    private String callingHost;
	
	
	
	

    public static final String HOST_NAME = "hostName";
    private String hostName;

    private static final String LOCAL_HOST_NAME = NetUtils.getLocalHostname();
    /**
     * The Supplier is used to populate the hostName key after the hostName value from the caller has been
     * placed into the callingHost map entry.
     */
    @Chained(fieldName = HOST_NAME, chainedFieldName = CALLING_HOST)
    public static final Supplier<String> LOCAL_HOST_SUPPLIER = () -> LOCAL_HOST_NAME;
    /**
     * The methods in this class are not required by framework components that use the RequestContext properties.
     * They are provided as a convenience for applications. If they are not provided the properties can be accessed
     * directly through the Log4j ThreadContext Map using the keys above.
     */
    public static void clear() {
        ThreadContext.clearMap();
    }

    public static String getRequestId() {
        String uuidStr = ThreadContext.get(REQUEST_ID);
        UUID uuid;
        if (uuidStr == null) {
            uuid = UuidUtil.getTimeBasedUuid();
            ThreadContext.put(REQUEST_ID, uuid.toString());
        }
        return uuidStr;
    }

    public static String getSessionId() {
        return ThreadContext.get(SESSION_ID);
    }

    public static void setSessionId(UUID sessionId) {
        if (sessionId != null) {
            ThreadContext.put(SESSION_ID, sessionId.toString());
        }
    }

    public static void setSessionId(String sessionId) {
        if (sessionId != null) {
            ThreadContext.put(SESSION_ID, sessionId);
        }
    }

    

    public static void setIpAddress(String address) {
        ThreadContext.put(IP_ADDRESS, address);
    }

    public static String getIpAddress() {
        return ThreadContext.get(IP_ADDRESS);
    }

    public static void setUserId(String userId) {
        ThreadContext.put(USER_ID, userId);
    }

    public static String getUserId() {
        return ThreadContext.get(USER_ID);
    }
	public static void setProgramName(String programName) {
        ThreadContext.put(PROGRAM_NAME, programName);
    }
	public static String getProgramName() {
        return ThreadContext.get(PROGRAM_NAME);
    }
	
	public static void setAction(String action) {
        ThreadContext.put(ACTION, action);
    }
	public static String getAction() {
        return ThreadContext.get(ACTION);
    }
	
	
	public static void setMuId(Integer muId) {
        ThreadContext.put(MU_ID, muId.toString());
    }
	public static Integer getMuId() {
       String value = ThreadContext.get(MU_ID);
        if (value == null || value.length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
	
	public static void setProgramId(Integer programId) {
        ThreadContext.put(PROGRAM_ID, programId.toString());
    }
	public static Integer getProgramId() {
       String value = ThreadContext.get(PROGRAM_ID);
        if (value == null || value.length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
	
	
	public static void setCtId(Integer ctId) {
        ThreadContext.put(CT_ID, ctId.toString());
    }
	public static Integer getCtId() {
       String value = ThreadContext.get(CT_ID);
        if (value == null || value.length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
	public static void setAssociatedSkill(String associatedSkill) {
        ThreadContext.put(ASSOCIATED_SKILL, associatedSkill);
    }
	public static String getAssociatedSkill() {
        return ThreadContext.get(ASSOCIATED_SKILL);
    }
	public static void setSkillLevel(Integer skillLevel) {
        ThreadContext.put(SKILL_LEVEL, skillLevel.toString());
    }
	public static Integer getSkillLevel() {
       String value = ThreadContext.get(SKILL_LEVEL);
        if (value == null || value.length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
    

    public static String getHostName() {
        return ThreadContext.get(HOST_NAME);
    }

    public static void setHostName(String hostName) {
        ThreadContext.put(HOST_NAME, hostName);
    }

    public static String getCallingHost() {
        return ThreadContext.get(CALLING_HOST);
    }

    public static void setCallingHost(String hostName) {
        ThreadContext.put(CALLING_HOST, hostName);
    }

   

    /**
     * Save the RequestContext data.
     * @return A copy of the RequestContext data.
     */
    public static RequestContext save() {
        RequestContext context = new RequestContext();
        context.callingHost = getCallingHost();
        context.hostName = getHostName();
        context.ipAddress = getIpAddress();
        context.requestId = getRequestId();
        context.sessionId = getSessionId();
        context.userId = getUserId();
		context.programName= getProgramName();
		context.action= getAction();
		context.muId=getMuId();
		context.ctId=getCtId();
		context.getAssociatedSkill();
		context.getSkillLevel();
		context.getProgramId();
        return context;
    }

    /**
     * Populate the ThreadContext from a RequestContext object.
     */
    public void restore() {
       
        setCallingHost(this.callingHost);
        setHostName(this.hostName);
        setIpAddress(this.ipAddress);
        ThreadContext.put(REQUEST_ID, this.requestId);
        setSessionId(this.sessionId);
        setUserId(this.userId);
		setProgramName(this.programName);
		setAction(this.action);
		setMuId(this.muId);
		setCtId(this.ctId);
		setAssociatedSkill(this.associatedSkill);
		setSkillLevel(this.skillLevel);
		setProgramId(this.programId);
    }
}
