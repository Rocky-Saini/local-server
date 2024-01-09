package com.digital.signage.service.impl;

import com.digital.signage.dto.DeletedDataDTO;
import com.digital.signage.dto.DevicePanelDTO;
import com.digital.signage.dto.PanelsStatusRequestDTO;
import com.digital.signage.exceptions.InvalidRequestDataException;
import com.digital.signage.exceptions.NotRequiredForLocalServerException;

//import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.localserver.apiservice.AllExceptionService;
import com.digital.signage.localserver.apiservice.PanelStatusApiService;
import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.models.*;
import com.digital.signage.service.PanelService;
import com.digital.signage.service.impl.BaseServiceImpl;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.SortEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.digital.signage.util.ApplicationConstants.Headers.AUTHORIZATION;

@Service
public class PanelServiceImpl extends BaseServiceImpl implements PanelService {
    private static final Logger logger = LoggerFactory.getLogger(com.digital.signage.service.impl.PanelServiceImpl.class);
    @Autowired
    private RetrofitHelper retrofitHelper;
    @Autowired
    private AllExceptionService allExceptionService;
    @Override
    public Response<?> addPanel(Panel panel, HttpServletRequest request) {
        return null;
    }

    @Override
    public Response<?> updatePanel(Long panelId, Panel panel, HttpServletRequest request) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<List<PanelExt>> getPanelList(HttpServletRequest request, SortEnum sortEnum) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<PanelExt> getPanel(Long panelId, HttpServletRequest request) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Panel createNewDefaultPanel(Device device) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateDevicePanels(DevicePanelDTO panel, HttpServletRequest request) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> validateDevicePanels(DevicePanelDTO devicePanelDTO, Map<Long, ArrayList<String>> linkedPanel, HttpServletRequest request) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updatePanelStatuses(PanelsStatusRequestDTO panelStatusReqDTO, HttpServletRequest request) {
        return updatePanelStatusesVersion2(panelStatusReqDTO, request);
    }

    @Override
    public Response<?> updatePanelStatusesVersion2(PanelsStatusRequestDTO panelStatusReqDTO, HttpServletRequest request) {
//        final String uri = mainServerBaseUrl() + "/panel/status";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        request.getHeader("Authorization");
//        headers.set("Authorization", request.getHeader("Authorization"));
//        HttpEntity<?> entity = new HttpEntity<>(panelStatusReqDTO, headers);
//        log.debug("Going to send Panel status update to main server");
//        return new RestTemplate().exchange(uri, HttpMethod.POST, entity, Response.class).getBody();
        try {
            retrofit2.Response<Response<Object>> response =
                    retrofitHelper.newMainServerRetrofit()
                            .create(PanelStatusApiService.class)
                            .panelStatusV2(
                                    request.getHeader(AUTHORIZATION),
                                    panelStatusReqDTO
                            ).execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(response, objectMapper);
        } catch (Exception e) {
            allExceptionService.saveException(e, "/panel/v2/status");
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getPanelStatusByDeviceId(Long deviceId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response deletePanel(long deviceId, long panelId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getPanels(Long deviceId, String panelName, DataCollectionEnum.PanelStatus panelStatus, SortEnum sortEnum) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public List<Long> getDeviceListByLocation(User user, String mediaPlayerName) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateDeletedPanelStatus(Long panelId, List<DeletedDataDTO> panelDeletedStatusDTOS, HttpServletRequest httpServletRequest) throws InvalidRequestDataException {
        try {
            retrofit2.Response<Response<Object>> response =
                    retrofitHelper.newMainServerRetrofit()
                            .create(PanelStatusApiService.class)
                            .panelStatusDeletedDataAll(
                                    httpServletRequest.getHeader(AUTHORIZATION), panelId,
                                    panelDeletedStatusDTOS).execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(response, objectMapper);
        } catch (Exception e) {
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public PanelExt getPanelExtFromPanel(Panel panel) {
        throw new NotRequiredForLocalServerException();
    }
}
