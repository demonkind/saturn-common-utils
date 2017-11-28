/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2012 ChinaPnR,Inc.All Rights Reserved.
 */
package com.huifu.common.utils.file.csv;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVParser;

/**
 * 
 * @author zhanghaijie
 * @version $Id: SaCSVReader.java, v 0.1 2012-9-19 下午02:24:21 zhanghaijie Exp $
 */
public class SaCSVReader implements Closeable {

    private BufferedReader      br;

    private boolean             hasNext            = true;

    private CSVParser           parser;

    private int                 skipLines;

    private boolean             linesSkiped;

    /**
     * The default line to start reading.
     */
    public static final int     DEFAULT_SKIP_LINES = 0;

    private static final String DEFAULT_ENCODING   = "GBK";

    /**
     * Constructs CSVReader using a comma for the separator.
     * 
     * @param reader
     *            the reader to an underlying CSV source.
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file) throws UnsupportedEncodingException, FileNotFoundException {
        this(file, DEFAULT_ENCODING, CSVParser.DEFAULT_SEPARATOR,
            CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * Constructs CSVReader using a comma for the separator.
     * 
     * @param reader
     *            the reader to an underlying CSV source.
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file, String charEncoding) throws UnsupportedEncodingException,
                                                        FileNotFoundException {
        this(file, charEncoding, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER,
            CSVParser.DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * Constructs CSVReader with supplied separator.
     * 
     * @param reader
     *            the reader to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries.
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file, String charEncoding, char separator)
                                                                        throws UnsupportedEncodingException,
                                                                        FileNotFoundException {
        this(file, charEncoding, separator, CSVParser.DEFAULT_QUOTE_CHARACTER,
            CSVParser.DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * Constructs CSVReader with supplied separator and quote char.
     * 
     * @param reader
     *            the reader to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries
     * @param quotechar
     *            the character to use for quoted elements
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file, String charEncoding, char separator, char quotechar)
                                                                                        throws UnsupportedEncodingException,
                                                                                        FileNotFoundException {
        this(file, charEncoding, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER,
            DEFAULT_SKIP_LINES, CSVParser.DEFAULT_STRICT_QUOTES);
    }

    /**
     * Constructs CSVReader with supplied separator, quote char and quote handling
     * behavior.
     *
     * @param reader
     *            the reader to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries
     * @param quotechar
     *            the character to use for quoted elements
     * @param strictQuotes
     *            sets if characters outside the quotes are ignored
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file, String charEncoding, char separator, char quotechar,
                       boolean strictQuotes) throws UnsupportedEncodingException,
                                            FileNotFoundException {
        this(file, charEncoding, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER,
            DEFAULT_SKIP_LINES, strictQuotes);
    }

    /**
      * Constructs CSVReader with supplied separator and quote char.
      *
      * @param reader
      *            the reader to an underlying CSV source.
      * @param separator
      *            the delimiter to use for separating entries
      * @param quotechar
      *            the character to use for quoted elements
      * @param escape
      *            the character to use for escaping a separator or quote
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
      */

    public SaCSVReader(String file, String charEncoding, char separator, char quotechar, char escape)
                                                                                                     throws UnsupportedEncodingException,
                                                                                                     FileNotFoundException {
        this(file, charEncoding, separator, quotechar, escape, DEFAULT_SKIP_LINES,
            CSVParser.DEFAULT_STRICT_QUOTES);
    }

    /**
     * Constructs CSVReader with supplied separator and quote char.
     * 
     * @param reader
     *            the reader to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries
     * @param quotechar
     *            the character to use for quoted elements
     * @param line
     *            the line number to skip for start reading 
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file, String charEncoding, char separator, char quotechar, int line)
                                                                                                  throws UnsupportedEncodingException,
                                                                                                  FileNotFoundException {
        this(file, charEncoding, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER, line,
            CSVParser.DEFAULT_STRICT_QUOTES);
    }

    /**
     * Constructs CSVReader with supplied separator and quote char.
     *
     * @param reader
     *            the reader to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries
     * @param quotechar
     *            the character to use for quoted elements
     * @param escape
     *            the character to use for escaping a separator or quote
     * @param line
     *            the line number to skip for start reading
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file, String charEncoding, char separator, char quotechar,
                       char escape, int line) throws UnsupportedEncodingException,
                                             FileNotFoundException {
        this(file, charEncoding, separator, quotechar, escape, line,
            CSVParser.DEFAULT_STRICT_QUOTES);
    }

    /**
     * Constructs CSVReader with supplied separator and quote char.
     * 
     * @param reader
     *            the reader to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries
     * @param quotechar
     *            the character to use for quoted elements
     * @param escape
     *            the character to use for escaping a separator or quote
     * @param line
     *            the line number to skip for start reading
     * @param strictQuotes
     *            sets if characters outside the quotes are ignored
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file, String charEncoding, char separator, char quotechar,
                       char escape, int line, boolean strictQuotes)
                                                                   throws UnsupportedEncodingException,
                                                                   FileNotFoundException {
        this(file, charEncoding, separator, quotechar, escape, line, strictQuotes,
            CSVParser.DEFAULT_IGNORE_LEADING_WHITESPACE);
    }

    /**
     * Constructs CSVReader with supplied separator and quote char.
     * 
     * @param reader
     *            the reader to an underlying CSV source.
     * @param separator
     *            the delimiter to use for separating entries
     * @param quotechar
     *            the character to use for quoted elements
     * @param escape
     *            the character to use for escaping a separator or quote
     * @param line
     *            the line number to skip for start reading
     * @param strictQuotes
     *            sets if characters outside the quotes are ignored
     * @param ignoreLeadingWhiteSpace
     *            it true, parser should ignore white space before a quote in a field
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public SaCSVReader(String file, String charEncoding, char separator, char quotechar,
                       char escape, int line, boolean strictQuotes, boolean ignoreLeadingWhiteSpace)
                                                                                                    throws UnsupportedEncodingException,
                                                                                                    FileNotFoundException {
        this.br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charEncoding));
        this.parser = new CSVParser(separator, quotechar, escape, strictQuotes,
            ignoreLeadingWhiteSpace);
        this.skipLines = line;
    }

    /**
      * Reads the entire file into a List with each element being a String[] of
      * tokens.
      * 
      * @return a List of String[], with each String[] representing a line of the
      *         file.
      * 
      * @throws IOException
      *             if bad things happen during the read
      */
    public List<String[]> readAll() throws IOException {

        List<String[]> allElements = new ArrayList<String[]>();
        while (hasNext) {
            String[] nextLineAsTokens = readNext();
            if (nextLineAsTokens != null)
                allElements.add(nextLineAsTokens);
        }
        return allElements;

    }

    /**
     * 
     * @return
     * @throws IOException
     */
    public Integer getTotalLine() throws IOException {
        int i = 0;
        while (hasNext) {
            String[] nextLineAsTokens = readNext();
            if (nextLineAsTokens != null)
                i++;
        }
        return i;
    }

    /**
     * Reads the next line from the buffer and converts to a string array.
     * 
     * @return a string array with each comma-separated element as a separate
     *         entry.
     * 
     * @throws IOException
     *             if bad things happen during the read
     */
    public String readNextWithString() throws IOException {
        String result = null;
        do {
            String nextLine = getNextLine();
            if (!hasNext) {
                return result; // should throw if still pending?
            }
            result = nextLine;
        } while (parser.isPending());

        return result;
    }

    /**
     * Reads the next line from the buffer and converts to a string array.
     * 
     * @return a string array with each comma-separated element as a separate
     *         entry.
     * 
     * @throws IOException
     *             if bad things happen during the read
     */
    public String[] readNext() throws IOException {

        String[] result = null;
        do {
            String nextLine = getNextLine();
            if (!hasNext) {
                return result; // should throw if still pending?
            }
            String[] r = parser.parseLineMulti(nextLine);
            if (r.length > 0) {
                if (result == null) {
                    result = r;
                } else {
                    String[] t = new String[result.length + r.length];
                    System.arraycopy(result, 0, t, 0, result.length);
                    System.arraycopy(r, 0, t, result.length, r.length);
                    result = t;
                }
            }
        } while (parser.isPending());
        return result;
    }

    /**
     * Reads the next line from the file.
     * 
     * @return the next line from the file without trailing newline
     * @throws IOException
     *             if bad things happen during the read
     */
    private String getNextLine() throws IOException {
        if (!this.linesSkiped) {
            for (int i = 0; i < skipLines; i++) {
                br.readLine();
            }
            this.linesSkiped = true;
        }
        String nextLine = br.readLine();
        if (nextLine == null) {
            hasNext = false;
        }
        return hasNext ? nextLine : null;
    }

    /**
     * Closes the underlying reader.
     * 
     * @throws IOException if the close fails
     */
    public void close() throws IOException {
        br.close();
    }

}
