package com.digital.signage.service;

import com.digital.signage.dto.CurrentSnapshotRequestDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.digital.signage.models.Response;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Service
public interface CaptureLogService extends BaseService{


    Response<?> getSnapShots(Long deviceId, Long snapshotStartTime, Long snapshotEndTime)
            throws JsonParseException, ParseException, IOException;

    Response<?> captureLogs(@Nullable MultipartFile file, @Nullable String str,
                            HttpServletRequest request);
    Response<?> downloadLogByURL(Long deviceId, Long logFileStartTime, Long logFileEndTime,
                                 HttpServletRequest request, HttpServletResponse response)
            throws JsonParseException, ParseException, IOException;
    Response<?> uploadSnapShot(MultipartFile file, String str, HttpServletRequest request)
            throws IOException;
    void downloadThumbnail(String encryptedCustomerId, Long snapshotId, Boolean download,
                           HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException;
    void downloadSnapShot(String encryptedCustomerId, Long snapshotId, Boolean download,
                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException;
    void downloadLogFile(String encryptedCustomerId, Long logId, Boolean download,
                         HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException;
    Response<?> pushDeviceCurrentSnapShot(CurrentSnapshotRequestDTO currentSnapshotRequestDTO);
    Response<?> downloadCurrentImageSnapshotThumb(String uniqueRequestId,
                                                  HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException;
    Response<?> downloadCurrentImageSnapshot(String uniqueRequestId,
                                             HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException;


}
