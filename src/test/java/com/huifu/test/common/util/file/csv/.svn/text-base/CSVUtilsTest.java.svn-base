/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.test.common.util.file.csv;

import java.util.Iterator;
import java.util.List;

import com.huifu.common.utils.file.csv.CSVUtils;

import junit.framework.TestCase;

/**
 * 
 * @author zhanghaijie
 * @version $Id: CSVUtilsTest.java, v 0.1 2012-9-19 下午03:35:32 zhanghaijie Exp $
 */
public class CSVUtilsTest extends TestCase {

    public void testRead() throws Exception {
        List<String[]> list = CSVUtils.csvAnalyze("E:\\config\\test.csv");

        CSVUtils.csvWriter("E:\\config\\test2.csv", list);
    }
}
