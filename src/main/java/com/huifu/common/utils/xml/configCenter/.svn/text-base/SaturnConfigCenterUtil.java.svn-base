/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.common.utils.xml.configCenter;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.huifu.saturn.common.constants.code.respCode.SaturnBasicRespCode;
import com.huifu.saturn.common.constants.constants.SaturnCommonConstants;
import com.huifu.saturn.common.utils.SaturnLoggerUtils;

/**
 * 配置中心配置信息解析
 * 
 * @author zhanghaijie
 * @version $Id: SaturnConfigurationUtil.java, v 0.1 2012-9-14 下午04:57:20 zhanghaijie Exp $
 */
public class SaturnConfigCenterUtil {

    private static final Logger logger = Logger.getLogger(SaturnConfigCenterUtil.class);

    /**
     * 获取配置信息
     * 
     * @param xml
     * @return
     * @throws Exception 
     */
    public static Map<String, String> getConfiguration(String xml) throws Exception {
        Element rootElement = validationCheck(xml);
        Map<String, String> map = analyzeXML(rootElement);
        return map;

    }

    /**
     * 通过Key获取配置信息
     * 
     * @param xml
     * @param key
     * @return
     * @throws Exception
     */
    public static String getConfiguration(String xml, String key) throws Exception {
        Element rootElement = validationCheck(xml);
        Map<String, String> map = analyzeXML(rootElement);
        if (null != map) {
            return map.get(key);
        }
        return null;
    }

    /**
     * 获取系统名
     * 
     * @return
     * @throws Exception 
     */
    public static String getSystemName(String xml) throws Exception {
        Element rootElement = validationCheck(xml);
        return rootElement.getChildText(SaturnCommonConstants.CONFIG_CENTER_SYSTEM_NAME);
    }

    /**
     * 获取系统环境
     * 
     * @return
     * @throws Exception 
     */
    public static String getEnvironment(String xml) throws Exception {
        Element rootElement = validationCheck(xml);
        return rootElement.getChildText(SaturnCommonConstants.CONFIG_CENTER_ENVIRONMENT);
    }

    /**
     * 
     * @param xml
     * @return
     * @throws Exception
     */
    private static Element validationCheck(String xml) throws Exception {
        Reader reader = new StringReader(xml);
        SAXBuilder saxb = new SAXBuilder();
        Document doc;
        try {
            doc = saxb.build(reader);
        } catch (JDOMException e) {
            SaturnLoggerUtils.error(logger, e, "【配置信息解析失败】jdom解析失败", xml);
            throw new Exception("jdom解析失败");
        } catch (IOException e) {
            SaturnLoggerUtils.error(logger, e, "【配置信息解析失败】IO异常", xml);
            throw new Exception("配置中心加载IO异常", e);
        }
        Element rootElement = (Element) doc.getRootElement();
        String respCode = rootElement.getChildText(SaturnCommonConstants.CONFIG_CENTER_RESP_CODE);
        if (null == respCode || !SaturnBasicRespCode.SUCCESS.getReturnCode().equals(respCode)) {
            SaturnLoggerUtils.error(logger, null, "【配置信息解析失败】配置中心返回异常", xml);
            throw new Exception(respCode);
        }

        return rootElement;
    }

    /**
     * 
     * @param xml
     * @return
     */
    private static Map<String, String> analyzeXML(Element rootElement) {
        String xml = rootElement.getChildText(SaturnCommonConstants.CONFIG_CENTER_CONG);
        if (StringUtils.isBlank(xml)) {
            return null;
        }

        Map<String, String> map = new ConcurrentHashMap<String, String>();
        String[] properties = xml.split(",");
        for (String property : properties) {
            String[] pro = property.split("=");
            map.put(pro[0], pro[1]);
        }
        return map;
    }
}
