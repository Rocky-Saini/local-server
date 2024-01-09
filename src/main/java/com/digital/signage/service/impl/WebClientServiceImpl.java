package com.digital.signage.service.impl;

import com.digital.signage.context.TenantContext;
import com.digital.signage.dto.ContentIdNameContentTypeAndMediaSizeDTO;
import com.digital.signage.dto.ContentIdsDTO;
import com.digital.signage.dto.DeviceDTO;
import com.digital.signage.exceptions.WebClientServiceException;
import com.digital.signage.report.LicenseDto;
import com.digital.signage.configuration.CustomWebClient;
import com.digital.signage.configuration.TokenHelper;
import com.digital.signage.exceptions.InvalidInputException;
import com.digital.signage.models.Device;
import com.digital.signage.models.Location;
import com.digital.signage.models.Response;
import com.digital.signage.service.WebClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class WebClientServiceImpl implements WebClientService {

    private static final Logger logger = LoggerFactory.getLogger(WebClientServiceImpl.class);
    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private WebClient webClient;
    private String GET_LOCATION_BY_ID = "/location-management/lcms/"+ TenantContext.getCurrentSlug()+"/v1/location/{locationId}";
    private String GET_LICENSE_BY_CODE = "/license-management/lms/v1/license/code/{licenseCode}";
    private String GET_SCHEDULER_STATUS = "/tenant-management/tms/v1/isScheduler/{customer_id}";
    private String UPDATE_LICENSE_BY_CODE = "/license-management/lms/v1/license/code/{licenseCode}";
    private String SAVE_QMS_DATA = "/api/qms/saveQMSDeviceCounterData";
    private String GET_LICENSE_BY_LICENSE_CODE = "/license-management/lms/v1/getByLicenseCode/{licenseCode}";
    private String GET_CAMPAIGN_BY_CAMPAIGN_ID = "/content-management/cms/api/v1/campaign/{campaignId}";
    private String GET_BY_CONTENT_IDS = "content-management/cms/api/getContentIdNameContentTypeAndMediaSize";

    private String GET_CHILD_LOCATION = "/location-management/lcms/"+ TenantContext.getCurrentSlug()+"/v1/getChildLocation";

    private String UPDATE_LICENSE_BY_CODE2 = "/license-management/lms/v1/license/code/{licenseCode}";

    public Response<?> getLocationByLocationId(Long LocationId) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> mono = webClient.get()
                .uri(GET_LOCATION_BY_ID, LocationId)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            return mono.block();
        } catch (WebClientServiceException ex) {
            return handleErrorResponse(ex);
        }
    }

    public Response<?> getLicenceByLicenseCode(String licenseCode) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> mono = webClient.get()
                .uri(GET_LICENSE_BY_CODE, licenseCode)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            return mono.block();
        } catch (WebClientServiceException ex) {
            return handleErrorResponse(ex);
        }
    }

    public Response<?> getCustomerSchedulerSatus(Long customerId) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> mono = webClient.get()
                .uri(GET_SCHEDULER_STATUS, customerId)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            return mono.block();
        } catch (WebClientServiceException ex) {
            return handleErrorResponse(ex);
        }
    }

    public Response<?> getByLicenseCode(String licenseCode) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> mono = webClient.get()
                .uri(GET_LICENSE_BY_LICENSE_CODE, licenseCode)
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            return mono.block();
        } catch (WebClientServiceException ex) {
            return handleErrorResponse(ex);
        }
    }

    public Response<?> getCampaignByCampaignId(Long campaignId) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> mono = webClient.get()
                .uri(GET_CAMPAIGN_BY_CAMPAIGN_ID, campaignId)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            return mono.block();
        } catch (WebClientServiceException ex) {
            return handleErrorResponse(ex);
        }
    }
    public Response<?> getChildLocation(Long locationIds) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> mono = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(GET_CHILD_LOCATION).queryParam("locationIds", locationIds).build())
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            return mono.block();
        } catch (WebClientServiceException ex) {
            return handleErrorResponse(ex);
        }
    }

    public Response<?> updateLicenseDetails(String licenseCode, Object requestBody) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        String requestBodyStr = "{}";
        try {
            requestBodyStr = new ObjectMapper().writeValueAsString(requestBody);
            logger.info("Request Body: {}", requestBodyStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mono<Response> mono = webClient.put()
                .uri(UPDATE_LICENSE_BY_CODE, licenseCode)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBodyStr))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            return mono.block();
        } catch (WebClientServiceException ex) {
            return handleErrorResponse(ex);
        }
    }

    @Override
    public Object getAllLicenseList(String uri, String slugId) {
        ParameterizedTypeReference<com.digital.signage.report.Response<List<LicenseDto>>> reference = new ParameterizedTypeReference<com.digital.signage.report.Response<List<LicenseDto>>>() {
        };
        Mono<com.digital.signage.report.Response<List<LicenseDto>>> data = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(uri).build(slugId))
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        if(data.block().getCode()==HttpStatus.OK.value()) {
            return data.block();
        } else if(data.block().getCode()==HttpStatus.NOT_FOUND.value()) {
            return data.block();
        }
        return null;
    }

    private Throwable handleWBException(Throwable ex) {
        if (!(ex instanceof WebClientResponseException)) {
            logger.warn("Got an unexpected error: {}, will rethrow it ", ex.getMessage());
            return new WebClientServiceException(ex.getMessage(), ex, HttpStatus.BAD_REQUEST);
        }
        WebClientResponseException wcre = (WebClientResponseException) ex;
        WebClientServiceException exception = new WebClientServiceException(wcre.getMessage(), wcre, wcre.getStatusCode());
        try {
            Response response = new ObjectMapper().readValue(wcre.getResponseBodyAsString(), Response.class);
            exception = new WebClientServiceException(response.getMessage(), wcre, wcre.getStatusCode());
        } catch (JsonProcessingException e) {
            exception = new WebClientServiceException("WEBCLIENT JSON PARSE ERROR: "+wcre.getMessage(), wcre, wcre.getStatusCode());
            if(wcre.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                exception = new WebClientServiceException("Unauthorized! Check token authorities: "+wcre.getMessage(), wcre, wcre.getStatusCode());
            }
        }
        switch (wcre.getStatusCode()) {
            case NOT_FOUND:
                return exception;
            case UNPROCESSABLE_ENTITY:
                return exception;
            default:
                logger.warn("Got an unexpected error: {}, will rethrow it Error: {}", wcre.getStatusCode(), wcre.getResponseBodyAsString());
                return exception;
        }
    }

    private Response handleErrorResponse(WebClientServiceException ex) {
        return new Response(null, null, "Error", ex.httpStatus.value(),
                ex.getMessage(), ex.httpStatus.value());
    }

    private Response handleMonoResponse(Mono<Response> mono) {
        return mono.onErrorResume(WebClientResponseException.NotFound.class, ex -> {
            try {
                WebClientResponseException wcre = (WebClientResponseException) ex;
                Response response = new ObjectMapper().readValue(wcre.getResponseBodyAsString(), Response.class);
                return Mono.just(response);
            } catch (JsonProcessingException e) {
                return Mono.just(new Response(null, null, "Error", ex.getStatusCode().value(), "WEBCLIENT JSON PARSE ERROR: "+ex.getMessage(), ex.getStatusCode().value()));
            }
        }).block();
    }

    protected Object get() {
        return webClient.get()
                .uri("/...")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(RuntimeException::new))//TODO
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(RuntimeException::new))//TODO
                .bodyToMono(Object.class)
                /*.onErrorResume()*///TODO
                .block();
    }

    protected Object post(Class clazz, String requestBody) {
        return webClient.post()
                .uri("/....")
                .body(requestBody, clazz)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    protected void postEntity(Class clazz, String requestBody) {
        webClient.post()
                .uri("/....")
                .body(requestBody, clazz)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public Object getData(String uri) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> data = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(uri).build())
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference);
        //.onErrorMap(WebClientResponseException.class, this::handleWBException);
        data.map(originalVariable -> {
            if(originalVariable.getData()!=null) {
                originalVariable.setResult(originalVariable.getData());
            }
            return null;
        });
        if(data.block().getCode()==HttpStatus.OK.value()) {
            return data.block();
        } else if(data.block().getCode()==HttpStatus.NOT_FOUND.value()) {
            return data.block();
        }
        return null;
    }

    public Object getDataWithQueryParam(String uri, HashMap<String, String> queryParams) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(uri);
        queryParams.forEach((paramName, paramValue) -> uriBuilder.queryParam(paramName, paramValue));
        Mono<Response> data = webClient.get()
                .uri(uriBuilder.build().toUri())
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        if(data.block().getCode()==HttpStatus.OK.value()) {
            return data.block().getResult();
        } else if(data.block().getCode()==HttpStatus.NOT_FOUND.value()) {
            return data.block();
        }
        return null;
    }

    public Object getDataWithPathParam(String uri, Long id) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> data = webClient.get()
                .uri(uri, id)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        if(data.block().getCode()==HttpStatus.OK.value()) {
            return data.block();
        } else if(data.block().getCode()==HttpStatus.NOT_FOUND.value()) {
            return data.block();
        }
        return null;
    }

    public Object putDataWithPathParam(String uri, Class clazz, Object requestBody) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        String requestBodyStr = "{}";
        try {
            requestBodyStr = new ObjectMapper().writeValueAsString(requestBody);
            logger.info("Request Body: {}", requestBodyStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mono<Response> data = webClient.put()
                .uri(uri)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBodyStr))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        if(data.block().getCode()==HttpStatus.OK.value()) {
            return data.block();
        } else if(data.block().getCode()==HttpStatus.NOT_FOUND.value()) {
            return data.block();
        }
        return null;
    }

    public Object getContentIdNameContentTypeAndMediaSize(ContentIdsDTO contentIdsDTO) {
        ParameterizedTypeReference<com.digital.signage.report.Response<List<ContentIdNameContentTypeAndMediaSizeDTO>>> reference = new ParameterizedTypeReference<com.digital.signage.report.Response<List<ContentIdNameContentTypeAndMediaSizeDTO>>>() {
        };
        String requestBodyStr = "{}";
        try {
            requestBodyStr = new ObjectMapper().writeValueAsString(contentIdsDTO);
            logger.info("Request Body: {}", requestBodyStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mono<com.digital.signage.report.Response<List<ContentIdNameContentTypeAndMediaSizeDTO>>> mono =webClient.method(HttpMethod.GET)
                .uri(GET_BY_CONTENT_IDS)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBodyStr))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            return mono.block();
        } catch (WebClientServiceException ex) {
            return handleErrorResponse(ex);
        }
    }

    @Override
    public void saveQMSDeviceCounterData(DeviceDTO deviceFromDb, String qmsAdminUrl, boolean isUpdated) {
        ParameterizedTypeReference<Response> reference = new ParameterizedTypeReference<Response>() {
        };
        Mono<Response> mono = webClient.post()
                .uri(uriBuilder -> uriBuilder.path(SAVE_QMS_DATA).queryParam("qmsAdminUrl", qmsAdminUrl).queryParam("isUpdated", isUpdated).build())
                .bodyValue(deviceFromDb)
                .headers(h -> h.setBearerAuth(tokenHelper.getToken()))
                .retrieve()
                .bodyToMono(reference)
                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        try {
            mono.block();
        } catch (WebClientServiceException ex) {
            handleErrorResponse(ex);
        }
    }
}
