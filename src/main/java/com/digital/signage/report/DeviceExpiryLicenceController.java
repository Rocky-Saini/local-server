package com.digital.signage.report;
import com.digital.signage.controllers.BaseController;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/device")//UserConstants.USER_CONTEXT)
public class DeviceExpiryLicenceController extends BaseController {
    @Autowired(required = true)
    @Lazy
    private DeviceExpiryService deviceExpiryService;


    @GetMapping("/device-license-expiry")
    public Response<?> getDeviceLicenseExpiryReports(
            @RequestParam(value = "fromDate", required = false) Long fromDate,
            @RequestParam(value = "toDate", required = false) Long toDate,
            @RequestParam(value = "mediaPlayerName", required = false) String deviceName,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "currentPage", required = false) Integer currentPage,
            @RequestParam(value = "noPerPage", required = false) Integer noPerPage

    ) {
        return deviceExpiryService.getDeviceLicenseExpiryReports(fromDate,toDate,deviceName,location,status,currentPage,noPerPage);
    }

    @Override
    protected BaseService getBaseService() {
        return (BaseService) deviceExpiryService;
    }
}
