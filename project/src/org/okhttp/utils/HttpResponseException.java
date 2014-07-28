package org.okhttp.utils;

import java.io.IOException;

/**
 * Signals a non 2xx HTTP response.
 */
public class HttpResponseException extends IOException {

	private static final long serialVersionUID = 5913405710588083393L;

    private final int statusCode;
	    
    public HttpResponseException(int statusCode, final String s) {
        super(s);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
	

}
