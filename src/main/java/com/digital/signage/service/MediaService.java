package com.digital.signage.service;



import com.digital.signage.dto.MediaDetailDto;
import com.digital.signage.dto.MediaVersionDTO;
import com.digital.signage.exceptions.InvalidJwtException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface MediaService extends BaseService {
    MediaDetailDto mediaDetail(Long mediaDetailId, int mediaVersion);
    MediaVersionDTO mediaLatestVersion(Long mediaDetailId);
    void mediaFile(Long mediaDetailId, String encryptedCustomerId, Boolean download, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws InvalidJwtException, IOException;

}
