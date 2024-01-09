package com.digital.signage.configuration.multitenant;

import com.digital.signage.context.TenantContext;
import com.digital.signage.models.Customer;
import com.digital.signage.report.LicenseDto;
import com.digital.signage.report.Response;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.CustomerType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class MultiTenancyInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    EntityManager entityManager;

    @Value("${service-gateway.baseUrl}")
    private String baseUrl;

    @Autowired
    private WebClient webClient;

    @Autowired
    public MultiTenancyInterceptor() {
        this.webClient = WebClient.builder().baseUrl("http://k8s-neuro-ingressp-74011e45bf-569147679.ap-southeast-1.elb.amazonaws.com").build();


    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String slugIdentifier = "dse_dev_db"; //TODO slug identifier, hard coding the default database
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null && (request.getRequestURI().contains("/api/unregistereDdevice") || request.getRequestURI().contains("/onboarding"))) {
            try {
                String licenseCode = request.getParameter("licenseCode");
                com.digital.signage.report.Response<LicenseDto> license =
                        (com.digital.signage.report.Response<LicenseDto>) getLicenseByLicenseCode("/license-management/lms/v1/getByLicenseCode/{licenseCode}", licenseCode);
                String slugId = license.getData().getSlugId();
                TenantContext.setCurrentSlug(slugId);
                TenantContext.setCurrentCustomerId(license.getData().getCustomerId());
                slugIdentifier = TenantContext.getCurrentSlug();
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                setResponseMessage(response, "Invalid License Code");
                return false;
            }

        }else if (token == null && (request.getRequestURI().contains("/api/reports/panel-on-off-logs") || request.getRequestURI().contains("/api/reports/device-on-off-logs") || request.getRequestURI().contains("/api/notify/isScheduler"))) {
            try {
                String slugId = request.getHeader("X-Tenant-Id");
//                com.digital.signage.report.Response<LicenseDto> license =
//                        (com.digital.signage.report.Response<LicenseDto>) getLicenseByLicenseCode("/license-management/lms/v1/getByLicenseCode/{licenseCode}", licenseCode);
//                String slugId = license.getData().getSlugId();
                TenantContext.setCurrentSlug(slugId);
//                TenantContext.setCurrentCustomerId(license.getData().getCustomerId());
                slugIdentifier = TenantContext.getCurrentSlug();
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                setResponseMessage(response, "Invalid slug");
                return false;
            }

        } else if (token != null && (token.toLowerCase().startsWith(/*OAuth2AccessToken.BEARER_TYPE.toLowerCase()*/"bearer"))) {
            CustomerType customerType = CustomerType.NOT_APPLICABLE;
            Long customerId = 0l;
            try {
                //OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
                String[] split_string = token.split("\\.");
                //String base64EncodedHeader = split_string[0];
                String base64EncodedBody = split_string[1];
                //String base64EncodedSignature = split_string[2];

                Base64 base64Url = new Base64(true);
                String body = new String(base64Url.decode(base64EncodedBody));
                HashMap<String, Object> extraInfoMap = new ObjectMapper().readValue(body, HashMap.class);
                slugIdentifier = extraInfoMap.get("slugId").toString();
                if (extraInfoMap.get("customer_type").toString().equals("BASIC")) {
                    customerType = CustomerType.BASIC;
                } else if (extraInfoMap.get("customer_type").toString().equals("ADVANCED")) {
                    customerType = CustomerType.ADVANCED;
                }
                customerId = Long.valueOf(extraInfoMap.get("customer_id").toString());
                TenantContext.setCurrentCustomerId(customerId);
                TenantContext.setCurrentCustomerType(customerType);
                TenantContext.setCurrentSlug(slugIdentifier);
                TenantContext.setCurrentToken(token);//.substring(7));
                System.out.println("New Slug identified.." + slugIdentifier);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute(ApplicationConstants.CUSTOMER_TYPE, customerType);
        }
        request.setAttribute(ApplicationConstants.TENANT_IDENTIFIER, slugIdentifier);
        return true;
    }

    private CustomerType getCustomerType(String slugIdentifier) {
        Query query = entityManager.createNativeQuery("select * from customer where \"slugId\" = :slugId", Customer.class);
        query.setParameter("slugId", slugIdentifier);
        List<Customer> customerList = query.getResultList();

        if (!customerList.isEmpty()) {
            return customerList.get(0).getCustomerType();
        }
        return null;
    }

    public Object getLicenseByLicenseCode(String uri, String licenseCode) {
        ParameterizedTypeReference<Response<LicenseDto>> reference = new ParameterizedTypeReference<com.digital.signage.report.Response<LicenseDto>>() {
        };
        Mono<Response<LicenseDto>> data = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(uri).build(licenseCode))
                .retrieve()
                .bodyToMono(reference);
//                .onErrorMap(WebClientResponseException.class, this::handleWBException);
        if (data.block().getCode() == HttpStatus.OK.value()) {
            return data.block();
        } else if (data.block().getCode() == HttpStatus.NOT_FOUND.value()) {
            return data.block();
        }
        return null;
    }

    private void setResponseMessage(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"message\": \"" + message + "\"}");
            writer.flush();
        }

    }
}
