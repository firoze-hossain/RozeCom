package com.roze.admin.report;

import com.roze.admin.setting.SettingService;
import com.roze.common.entity.setting.Setting;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/reports")
    public String viewSalesReportHome(HttpServletRequest request) {
        loadCurrencySetting(request);
        return "reports/reports";
    }

    private void loadCurrencySetting(HttpServletRequest request) {
        List<Setting> currencySettings = settingService.getCurrencySettings();

        for (Setting setting : currencySettings) {
            request.setAttribute(setting.getKey(), setting.getValue());
        }
    }
}
