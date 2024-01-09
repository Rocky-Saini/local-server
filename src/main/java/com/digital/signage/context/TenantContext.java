package com.digital.signage.context;

import com.digital.signage.exceptions.NotLoggedInException;
import com.digital.signage.models.LoggedInDevice;
import com.digital.signage.models.PdnUserAuth;
import com.digital.signage.models.TpaServerAuth;
import com.digital.signage.models.User;
import com.digital.signage.util.CustomerType;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author -Ravi Kumar created on 1/19/2023 10:45 AM
 * @project - Digital Sign-edge
 */
public class TenantContext {
    private TenantContext() {
        // prevent instantiation
    }

    private static final ThreadLocal<Long> CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> CONTEXTDEVICE = new ThreadLocal<>();



    public static void setUserId(Long userId) {
        CONTEXT.set(userId);
    }


    public static Long getUserId() {
        return CONTEXT.get();

    }

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    private static final ThreadLocal<LoggedInDevice> currentDevice = new ThreadLocal<>();

    private static ThreadLocal<CustomerType> currentCustomerType = new ThreadLocal<>();

    private static ThreadLocal<String> currentSlug = new ThreadLocal<>();

    private static ThreadLocal<String> currentToken = new ThreadLocal<>();

    private static ThreadLocal<Long> currentCustomerId = new ThreadLocal<>();

    private static ThreadLocal<Long> deviceId = new ThreadLocal<>();

    public static Long getDeviceId() {
        return deviceId.get();
    }

    public static void setDeviceId(Long deviceId) {
        TenantContext.deviceId.set(deviceId);
    }
    private static ThreadLocal<Boolean> isDevice = new ThreadLocal<>();

    public static Boolean getIsDevice() {
        return CONTEXTDEVICE.get();
    }

    public static void setIsDevice(Boolean isDevice) {
        CONTEXTDEVICE.set(isDevice);
    }

    public static CustomerType getCurrentCustomerType() {
        if (SecurityContextHolder.getContext() != null
                && SecurityContextHolder.getContext().getAuthentication() != null) {
            if ((SecurityContextHolder.getContext().getAuthentication().getPrincipal()) instanceof User) {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (!user.getIsPanasonicUser()) {
                    return CustomerType.ADVANCED;//user.getCustomers().iterator().next().getCustomerId(); --user-service get through webclient
                }
            }
        }
        if (currentCustomerType.get() == null) {
            throw new IllegalStateException(
                    "Current customer type should be set by now. Please contact server team.");
        }
        return currentCustomerType.get();
    }

    public static void setCurrentCustomerType(CustomerType customerType) {
        currentCustomerType.set(customerType);
    }

    public static void setCurrentCustomerId(Long customerId) {
        currentCustomerId.set(customerId);
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentSlug(String slug) {
        currentSlug.set(slug);
    }

    public static String getCurrentSlug() {
        return currentSlug.get();
    }

    public static void setCurrentToken(String token) {
        currentTenant.set(token);
    }

    public static String getCurrentToken() {
        return currentTenant.get();
    }

    public static void setCurrentDevice(LoggedInDevice device) {
        currentDevice.set(device);
    }

    public static LoggedInDevice getCurrentDevice() {
        return currentDevice.get();
    }

    public static void clear() {
        currentTenant.remove();
        currentDevice.remove();
        currentCustomerType.remove();
        CONTEXT.remove();
    }

    public static Long getCustomerId() throws NotLoggedInException {
        if (currentCustomerId.get() != null) {
            return currentCustomerId.get();
        } else if (getCurrentTenant() != null && !getCurrentTenant().trim().isEmpty()) {
            return Long.valueOf(getCurrentTenant());
        } else {
            throw new NotLoggedInException();
        }
    }

    /**
     * @return {@link User}
     * @throws NotLoggedInException - when user is not logged in
     * @deprecated - the entire user object won't be stored in memcahe when it is implemented
     */
    @SuppressWarnings("squid:S1133")
    @Deprecated
    public static User getLoggedInUser() throws NotLoggedInException {
        return null;
        /*User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            return user;
        } else {
            throw new NotLoggedInException();
        }*/
    }

    public static Long getCurrentUserId() throws NotLoggedInException {
        return CONTEXT.get();
        /*User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            return user.getUserId();
        } else {
            throw new NotLoggedInException();
        }*/
    }

    public static Long getCurrentTpaServerId() throws NotLoggedInException {
        TpaServerAuth tpaServerAuth =
                (TpaServerAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (tpaServerAuth != null) {
            return tpaServerAuth.getTpaServerId();
        } else {
            throw new NotLoggedInException();
        }
    }

    public static Long getTpaId() throws NotLoggedInException {
        TpaServerAuth tpaServerAuth =
                (TpaServerAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (tpaServerAuth != null) {
            return tpaServerAuth.getTpAppId();
        } else {
            throw new NotLoggedInException();
        }
    }

    public static PdnUserAuth getPdnUserAuth() throws NotLoggedInException {
        PdnUserAuth pdnUserAuth =
                (PdnUserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (pdnUserAuth != null) {
            return pdnUserAuth;
        } else {
            throw new NotLoggedInException();
        }
    }

    @Deprecated
    public static LoggedInDevice getLoggedInDevice() throws NotLoggedInException {
        if (SecurityContextHolder.getContext() != null
                && SecurityContextHolder.getContext().getAuthentication() != null) {
            if ((SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()) instanceof LoggedInDevice) {
                return (LoggedInDevice) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            } else {
                return getCurrentDevice();
            }
        } else if (getCurrentDevice() != null) {
            return getCurrentDevice();
        } else {
            throw new NotLoggedInException();
        }
    }

    public static Long getLoggedInDeviceId() throws NotLoggedInException {
        LoggedInDevice device = (LoggedInDevice) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (device != null) {
            return device.getDeviceId();
        } else {
            throw new NotLoggedInException();
        }
    }

    public static boolean isDevice() throws NotLoggedInException {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            throw new NotLoggedInException();
        }
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal() instanceof LoggedInDevice;
    }

    public static boolean isUser() throws NotLoggedInException {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            throw new NotLoggedInException();
        }
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User;
    }

    public static boolean isPdnUser() throws NotLoggedInException {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            throw new NotLoggedInException();
        }
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal() instanceof PdnUserAuth;
    }

    public static boolean isTpaServer() throws NotLoggedInException {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            throw new NotLoggedInException();
        }
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal() instanceof TpaServerAuth;
    }

    public static boolean isCurrentCustomerIsBasic() {
        return CustomerType.BASIC.equals(getCurrentCustomerType());
    }

    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }
    public static String getTenantId() {
        return TENANT_ID.get();
    }
}
