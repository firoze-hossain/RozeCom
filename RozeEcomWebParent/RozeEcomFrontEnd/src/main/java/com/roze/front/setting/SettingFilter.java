package com.roze.front.setting;

import com.roze.common.Constants;
import com.roze.common.entity.setting.Setting;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Order(-123)
public class SettingFilter implements Filter {

    @Autowired
    private SettingService service;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String url = servletRequest.getRequestURL().toString();

        if (url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".png") ||
                url.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        List<Setting> generalSettings = service.getGeneralSettings();

        generalSettings.forEach(setting -> {
            request.setAttribute(setting.getKey(), setting.getValue());
            System.out.println(setting.getKey() + " == > " + setting.getValue());
        });

        request.setAttribute("S3_BASE_URI", Constants.S3_BASE_URI);

        chain.doFilter(request, response);

    }

}
