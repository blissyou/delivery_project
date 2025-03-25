package org.delivery.api.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Slf4j
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain Chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) Request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) Response);
        log.info("INIT URI: {}",req.getRequestURI());
        Chain.doFilter(req, res);

        // request 정보
        var headersNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headersNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);

            // authorization- token : ??? , user-agent: ???
            headerValues.append("[")
                    .append(headerKey)
                    .append(":")
                    .append(headerValue)
                    .append("] ");


        });

        var requestBody = new String(req.getContentAsByteArray());

        var uri = req.getRequestURI();
        var method = req.getMethod();
        log.info(">>>> uri: {}, method: {}, headers: {} , body: {}",uri,method ,headerValues, requestBody);

        //response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);

            responseHeaderValues.append("[")
                    .append(headerKey)
                    .append(":")
                    .append(headerValue)
                    .append("] ");
        });
        var responseBody = new String(res.getContentAsByteArray());
        log.info("<<<<uri{}, method: {}, header : {}, body : {}",uri,method, responseHeaderValues, responseBody);

        res.copyBodyToResponse();
    }
}
