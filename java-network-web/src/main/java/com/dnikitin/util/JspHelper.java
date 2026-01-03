package com.dnikitin.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String PATH = "/WEB-INF/jsp/%s";

    public static String getPath(String jspName){
        return String.format(PATH, jspName);
    }
}
