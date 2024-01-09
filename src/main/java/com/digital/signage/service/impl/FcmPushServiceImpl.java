package com.digital.signage.service.impl;

import com.digital.signage.models.*;
import com.digital.signage.service.FcmPushService;
import com.digital.signage.util.ApplicationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.digital.signage.repository.PremisesIdRepository;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class FcmPushServiceImpl {
    private static final boolean TEMP = true;
    private static final Logger logger = LoggerFactory.getLogger(FcmPushServiceImpl.class);
    @Autowired
    private ObjectMapper mapper;
    //@Autowired
    //private FcmPushService fcmPushService;
    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Autowired
    private PremisesIdRepository premisesIdRepository;
    private String fcmServerKey;

    public FcmPushServiceImpl() {
    }

    // for tests only
    public FcmPushServiceImpl(String activeProfile) {
        this.activeProfile = activeProfile;
        logger.debug("FcmPushServiceImpl constructor 1");
    }

    public void checkIfKeyIsPresent() throws IllegalStateException {
        if (pickKeyBasedOnEnvironment() == null) {
            throw new IllegalStateException("Switch case should have Auth key for firebase");
        }
    }

    private String pickKeyBasedOnEnvironment() {
        if (fcmServerKey != null) {
            return fcmServerKey;
        }
        if (ApplicationConstants.SET_ALL_ON_PREMISES_PROFILES.contains(activeProfile)) {
            // on prem env
            synchronized (FcmPushServiceImpl.class) {
                if (fcmServerKey != null) {
                    return fcmServerKey;
                }
                fcmServerKey = premisesIdRepository.findAllByPkID().getServerFcmKey();
            }
            if (fcmServerKey != null) {
                return fcmServerKey;
            }
        }
        switch (activeProfile + ".json") {
            case "angular-dev.json":
                return "AAAAzfDJBgE:APA91bGtGQd21tp6AqijNHbAF3QIYzZc33ECEs7ATqEc9tsfYgYV_OfRHXiq8DOeJ8MTh5rT7MVR_qyC-U0sz5EoBAEPRT33hPpN_HeDWrRVGn-MHRJ2d4_doC2F_EciowjOyVABffgG";
            case "dev.json":
                return "AAAASEDoC-w:APA91bGsk5_P-BB5jBZ6PHTtdf5Ht6U6140B6QfgM9wKDaZjfqyY_n-X7TLOi2zOT8zfQgursMRdvjpMLAnnuwx2wyYGuw9ag2a9aVW39n9d_Ao8ejsgOm_yrZ5aSHXy8p_stx_vYQO0";
            case "pan-dev.json":
                return "AAAAj5QF-Z4:APA91bHzOvVYPLPdXe6dL3_J2GwxlIyOUjPi9huNMxx4YN8m5tSG6H5CPG7X4FfzvMLBhKlkcaLCf3D-sPQggTVNMthT3W0D3cjfIY1LeeU0K4fVOIys8h_0VV89EjsAyYwLRWqfwHrEgZXIQLodGptgUEuHSj1Ydw";
            case "pan-qa.json":
                return "AAAA_787cVc:APA91bEl2KBw8LpMIgCUO4bpv8rjgMxLkgSmY9HHNerfTGfq5NmL6lRo0p18EUFCcgISGJ_TREroQE8XTCLzATQAV9bxn9XWB2DVePrqTpP9HdZJbUjEGCrRe06ZWy05n_5rbCgVatHD7W8oaTxNLxkpj9tvBbcrcg";
            case "pan-stage.json":
                return "AAAAKUeQCXw:APA91bEnHQile5MT9XgiE-ba_XuE2Wrg8w4hLGj679TfuJzVMeZsWBu4dd3DBn0rIWoIewjAr4uMTxwVoDSvNp__YHqc-NYlFSYTHeQloitNO5V-d-9fMwcjj4MsxEbhyRMueg-P0uYT6NCiDKvACboGw0nXYeck-A";
            case "pi-nuc.json":
                return "AAAAk9If3GM:APA91bFbTUZDsk4XEotKcIM9jJXGUEvjuuNcR02sKecYkxWJCSYmECFgDL02hZoJ9giEuolWOObZlGes3eqNJg0ntSYZMHtUGx7GlwWVPaLqYNAIz0me6n68LNtTjVskVOEgJkU6K-Z9dv_ejWdBflOrGxCtKDM1Ag";
            case "pidev.json":
                return "AAAAg1EDkyk:APA91bEP5zWHmO6VQbrxbRMfpBlx3wbHCeXX_eukXPWPTEH-hfjWjbb8ExwNVA5xnSx0VhQ-wJWrRkAnhf3nvYpuZGQUcsxDz4PjzNSrvX8JEG9wU2stzQPaG8yNXXrrw_jJRvjTXSDw";
            case "pionpremises.json":
                return "AAAAtkrV4Qk:APA91bE4BMN2S9LKBmpunIT93WEsOXyETGqoFOsf_A6zyP-8IF-M4LGIPZmzONpxXXSiaks4EMC1u_LSHn9tW7HgdDsig4dn6MOB5w3tTbc0sY5AaKtxu84flBifJFw6UaGnPCBD-2oC7HENR7UP2BhCu3gA-e_Qjw";
            case "pionpremises2.json":
                return "AAAA2Ft6twY:APA91bGq-rbLJaZxKeIyrD6oIL023r-VulbMOX2jFnmKFVdmgQ-22_5GLVPpbtpygYNvoW6SqRKq_jUNTZG2fEYbky1gj_MA7_ntYDSnGLq3mJZYx0HS29H-aNP-X0pCRid3PGpkCuJsz8Gy3FDplWnCT8KvU22A2A";
            case "piqa1.json":
                return "AAAArN7iPz4:APA91bGf0bRJhkx-bLi_c12aLdCmK9zlg_kgqaV-VAy11r4pErXROHU6UlOyTA3AZami8dlTc6u8Ou6FYFrwlyyCnsO3f6Ch1ec48ZBgQ6VCRiMeMhVSotLV1DJ5WEW4jiCqKrxX6kJK";
            case "piqa2.json":
                return "AAAAL3G4WR8:APA91bHp0sGbxJHBj3_669CRaXWnwK9WDig_RhMKbxum81gy4wxSiaawrVXGG1N_ajkQ8B0Wha8dok8UckYtdAzF4X7ujJ9-s-fCnXQmeC4Vfec9gfKQVs1nJ3I4ME4Z7TsO9GZqoT2b";
            case "qa.json":
                return "AAAAkycenKY:APA91bFTklfSXFzwzW7F8dTWeQsMVsc46OMMAcY0eop3rGXq3m18lW30cBgJ9ikYMncNgCqKMHEQnqYmf1TBfcyJmXZiwHkwAVhEvE5NxAtN-xYhzKn2QZfD0gcdMu_oEWK41hOkPt5r";
            case "prod.json":
                return "AAAACghk5-g:APA91bFhEMA_k4whMPIvhLpWWIqHFkzcfU5Ivk8rMhwCBp1UlkRrlSOzCSTDI90-wjIV1oefdx49Fd93PG52u5jecMvrfyf4He2Ae4ADY3krLpakN0NS3y5J2FNloi5IpXtLizvlG5uR";
            default:
                return null;
        }
    }

    public FcmPushResponseData sendPush(String registrationId, UserMessage userMessage,
                                        boolean isSilentPush) throws JsonProcessingException {
        FcmPushRequestModel fcmPushRequestModel = null;
        if (!isSilentPush) {
            Notification notification = Notification.Builder.aNotification()
                    .withMessage(userMessage.getMessageString())
                    .withTitle(userMessage.getMessageTitle())
                    .build();
            fcmPushRequestModel =
                    FcmPushRequestModel.Builder.aFcmPushRequestModel()
                            .withToRegistrationId(registrationId)
                            .withUserMessage(userMessage)
                            .withNotification(notification)
                            .build(mapper);
        } else {
            fcmPushRequestModel =
                    FcmPushRequestModel.Builder.aFcmPushRequestModel()
                            .withToRegistrationId(registrationId)
                            .withUserMessage(userMessage)
                            .withPriority(FcmPushRequestModel.Priority.high)
                            .build(mapper);
        }
        SilentPushEntity silentPushEntity = null;
        if (TEMP) {
            String fcmPushRequestModelJson = mapper.writeValueAsString(fcmPushRequestModel);
            silentPushEntity = SilentPushEntity.Builder.aSilentPushEntity()
                    .withFcmRequest(fcmPushRequestModelJson)
                    .withFcmRegistrationId(registrationId)
                    .withUserId(fcmPushRequestModel.getUserMessage().getUserId())
                    .withCustomerId(fcmPushRequestModel.getUserMessage().getCustomerId())
                    .withEntityId(fcmPushRequestModel.getUserMessage().getEntityId())
                    .withEntityType(fcmPushRequestModel.getUserMessage().getEntityTypeString())
                    .withMessageType(fcmPushRequestModel.getUserMessage().getMessageType())
                    .withEntityPage(fcmPushRequestModel.getUserMessage().getEntityPage())
                    .build();
        }
        try {
            Response<FcmPushResponseModel> response = null;//new okhttp3.Response();//fcmPushService.sendPush("key=" + pickKeyBasedOnEnvironment(), fcmPushRequestModel).execute();
            if (TEMP) {
                silentPushEntity.setHttpStatusCode(response.code());
                silentPushEntity.setTimeOfSendingPush(new java.util.Date());
            }
            if (response.isSuccessful()) {
                FcmPushResponseModel fcmPushResponseModel = response.body();
                if (TEMP) {
                    String fcmPushResponseModelJson = mapper.writeValueAsString(fcmPushResponseModel);
                    silentPushEntity.setFcmResponse(fcmPushResponseModelJson);
                }
                return new FcmPushResponseData(fcmPushResponseModel, silentPushEntity);
            } else {
                ResponseBody errorBody = response.errorBody();
                if (TEMP) {
                    silentPushEntity.setFcmResponse(errorBody == null ? "null" : errorBody.toString());
                }
                logger.error("errorBody = {}", errorBody);
            }
        } catch (IOException e) {
            logger.error("FcmPushServiceImpl::sendPush ", e);
        }
        return new FcmPushResponseData(null, silentPushEntity);
    }

    static class FcmPushResponseData {
        FcmPushResponseModel fcmPushResponseModel;
        SilentPushEntity silentPushEntity;

        FcmPushResponseData(FcmPushResponseModel fcmPushResponseModel,
                            SilentPushEntity silentPushEntity) {
            this.fcmPushResponseModel = fcmPushResponseModel;
            this.silentPushEntity = silentPushEntity;
        }
    }
}
