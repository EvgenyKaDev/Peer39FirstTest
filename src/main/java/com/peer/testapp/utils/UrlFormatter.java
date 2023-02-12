package com.peer.testapp.utils;

import org.apache.commons.lang3.StringUtils;

/**
* As we are getting URLs from JSON it is impossible to get /n in url
 */
@Deprecated
public class UrlFormatter {
    public static String formatUrl(String url){
        if(StringUtils.isBlank(url))
            return url;

        return url.trim().replaceAll("\n", "");
    }

}
