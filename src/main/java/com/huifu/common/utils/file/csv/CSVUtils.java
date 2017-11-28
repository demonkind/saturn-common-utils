/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.common.utils.file.csv;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * CVS工具类
 * 
 * @author zhanghaijie
 * @version $Id: CSVUtils.java, v 0.1 2012-9-19 下午01:54:37 zhanghaijie Exp $
 */
public class CSVUtils {

    private static final Logger  logger              = Logger.getLogger(CSVUtils.class);

    private static final Integer MAX_TOTAL_LINE      = 5000;

    private static final String  DEFAULT_ENCODING    = "GBK";

    private static final char    DEFAULT_SEPRATE_DOT = ',';

    public static final void csvWriter(String file, List<String[]> list) throws IOException {
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(file), DEFAULT_SEPRATE_DOT);
            Iterator<String[]> it = list.iterator();
            while (it.hasNext()) {
                writer.writeNext(it.next());
            }
        } catch (IOException e) {
            logger.error("【CSV异常】IO异常");
            throw e;
        } finally {
            writer.close();
        }
    }

    /**
     * CSV解析 传入File名称
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static final List<String[]> csvAnalyze(String file) throws Exception {
        SaCSVReader parser = null;
        try {
            parser = new SaCSVReader(file, DEFAULT_ENCODING, DEFAULT_SEPRATE_DOT);
            List<String[]> list = getLines(parser);
            return list;
        } catch (UnsupportedEncodingException e) {
            logger.error("【CSV文件解析失败】编码格式不支持", e);
            throw e;
        } catch (FileNotFoundException e) {
            logger.error("【CSV文件解析失败】文件找不到", e);
            throw e;
        } finally {
            if (null != parser) {
                parser.close();
            }
        }
    }

    /**
     * CVS解析，按行返回，不做分割
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static final List<String> csvAnalyzeReturnWithString(String file) throws Exception {
        SaCSVReader parser = null;
        try {
            parser = new SaCSVReader(file, DEFAULT_ENCODING, DEFAULT_SEPRATE_DOT);
            List<String> list = getStringLines(parser);
            return list;
        } catch (UnsupportedEncodingException e) {
            logger.error("【CSV文件解析失败】编码格式不支持", e);
            throw e;
        } catch (FileNotFoundException e) {
            logger.error("【CSV文件解析失败】文件找不到", e);
            throw e;
        } finally {
            if (null != parser) {
                parser.close();
            }
        }
    }

    /**
     * CSV解析，传入文件及编码格式
     * 
     * @param file
     * @param encoding
     * @return
     * @throws Exception
     */
    public static final List<String[]> csvAnalyze(String file, String encoding) throws Exception {
        SaCSVReader parser = null;
        try {
            parser = new SaCSVReader(file, encoding, DEFAULT_SEPRATE_DOT);
            List<String[]> list = getLines(parser);
            return list;
        } catch (UnsupportedEncodingException e) {
            logger.error("【CSV文件解析失败】编码格式不支持", e);
            throw e;
        } catch (FileNotFoundException e) {
            logger.error("【CSV文件解析失败】文件找不到", e);
            throw e;
        } finally {
            if (null != parser) {
                parser.close();
            }
        }
    }

    /**
     * 
     * @param parser
     * @return
     * @throws Exception
     */
    private static List<String> getStringLines(SaCSVReader parser) throws Exception {
        List<String> list = new LinkedList<String>();
        String nextLine = null;
        int total = 0;
        while ((nextLine = parser.readNextWithString()) != null) {
            if (StringUtils.isBlank(nextLine)) {
                continue;
            }
            total++;
            if (total > MAX_TOTAL_LINE) {
                throw new Exception("CVS长度超长");
            }
            list.add(nextLine);
        }
        return list;
    }

    /**
     * 
     * @param parser
     * @return
     * @throws Exception
     */
    private static List<String[]> getLines(SaCSVReader parser) throws Exception {
        List<String[]> list = new LinkedList<String[]>();
        String[] nextLine = null;
        int total = 0;
        while ((nextLine = parser.readNext()) != null) {
            if (nextLine.length == 1 && StringUtils.isBlank(nextLine[0])) {
                continue;
            }
            total++;
            if (total > MAX_TOTAL_LINE) {
                throw new Exception("CVS长度超长");
            }
            list.add(nextLine);
        }
        return list;
    }
}
