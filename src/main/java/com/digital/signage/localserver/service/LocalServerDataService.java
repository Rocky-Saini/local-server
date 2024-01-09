package com.digital.signage.localserver.service;



import com.digital.signage.localserver.apiservice.DeviceMeService;
import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.localserver.config.LocalServerData;
//import com.digital.signage.localserver.config.LocalServerSercurityConfigKt;
import com.digital.signage.localserver.config.LocalServerSercurityConfig;
import com.digital.signage.localserver.dto.RelevantDeviceForLocalServer;
import com.digital.signage.models.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LocalServerDataService {
    private static final Logger logger = LoggerFactory.getLogger(LocalServerDataService.class);

    @Autowired
    private LocalServerSercurityConfig localServerSercurityConfig;
    @Value("${root.storage.dir}")
    private String localServerDataDir;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LocalServerData localServerData;
    @Autowired
    private RetrofitHelper retrofitHelper;

    @Async
    public void downloadConfigForLocalServer(String bearerToken) {
        if (this.localServerData.isDataComplete()) {
            // don't have to write if data is already complete
            return;
        }
        try {
            retrofit2.Response<Response<RelevantDeviceForLocalServer>> resp =
                    retrofitHelper.newMainServerRetrofit()
                            .create(DeviceMeService.class)
                            .meForLocalServer("Bearer " + bearerToken)
                            .execute();
            Response<?> data = RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
            if (data.getResult() instanceof RelevantDeviceForLocalServer) {
                RelevantDeviceForLocalServer device = (RelevantDeviceForLocalServer) data.getResult();
                LocalServerData serverData =
                        new LocalServerData(device.getCustomerId(), device.getLocalServerIP(), null);
                writeLocalData(serverData);
            }
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    public void writeLocalData(LocalServerData localServerData) {
        writeLocalData(localServerData, false);
    }

    public void writeLocalData(LocalServerData localServerData, boolean forceSave) {
        if (!forceSave && this.localServerData.isDataComplete()) {
            // don't have to write if data is already complete
            return;
        }
        logger.debug("writing local data to json file");
        if (localServerData.getCustomerId() != null) {
            this.localServerData.setCustomerId(localServerData.getCustomerId());
        }
        if (localServerData.getLocalServerIP() != null) {
            this.localServerData.setLocalServerIP(localServerData.getLocalServerIP());
        }
        if (localServerData.getCurrentLocalServerBuildVersion() != null) {
            this.localServerData.setCurrentLocalServerBuildVersion(
                    localServerData.getCurrentLocalServerBuildVersion());
        }
        try {
            String localServerDataStr = objectMapper.writeValueAsString(this.localServerData);
            Files.write(Paths.get(localServerDataDir,
                            LocalServerSercurityConfig.LOCAL_SERVER_DATA_JSON_FILENAME),
                    localServerDataStr.getBytes());
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
