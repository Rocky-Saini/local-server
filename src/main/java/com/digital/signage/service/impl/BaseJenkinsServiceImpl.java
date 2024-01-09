package com.digital.signage.service.impl;

import com.digital.signage.configuration.ApplicationProperties;
import com.digital.signage.models.LatestBuildVersionsByBuildOs;
import com.digital.signage.repository.LatestBuildVersionsByBuildOsRepository;
import com.digital.signage.service.OnPremisesBuildService;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.BuildOs;
import com.digital.signage.util.NullAwareBeanUtilsBean;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public abstract class BaseJenkinsServiceImpl extends BaseServiceWithServerLaunchConfigImpl {
    public static final String BUILDS_DIR_NAME = "builds";

    //public static final String ANDROID_BUILDS_DIR_NAME = "android";
    //
    //public static final String WINDOWS_BUILDS_DIR_NAME = "windows";
    //
    //public static final String DESKTOP_BUILDS_CLIENT_DIR_NAME = "desktop-client";
    //
    //public static final String DESKTOP_BUILDS_NATIVE_DIR_NAME = "desktop-native";
    //
    //public static final String DESKTOP_BUILDS_SERVER_DIR_NAME = "desktop-server";

    public static final String ON_PREMISE_DIR_NAME = "on-premise";

    public static final String MAIN_SERVER_DIR_KEY_FOR_S3 = "builds/{{deviceOs}}";

    public static final String ON_PREMISES_DIR_KEY_FOR_S3 = "builds/{{premisesId}}/{{deviceOs}}";

    public static final String REPLACE_DEVICE_OS_KEY = "{{deviceOs}}";

    public static final String REPLACE_ON_PRIMISES_KEY = "{{premisesId}}";

    private static final Logger logger = LoggerFactory.getLogger(BaseJenkinsServiceImpl.class);

    NullAwareBeanUtilsBean nullAwareBeanUtilsBean = new NullAwareBeanUtilsBean();

    @Value("${spring.http.multipart.location}")
    protected String tempDir;

    @Value("${is.pionpremises.server}")
    protected Boolean isOnPremisesServer;

    @Autowired
    private ApplicationProperties properties;

    boolean isJenkinsRequestAuthorized(HttpServletRequest httpServletRequest) {
        return requestAuthorized(httpServletRequest, OnPremisesBuildService.FROM_JENKINS);
    }

    boolean isOnPremiseServerRequestAuthorized(HttpServletRequest httpServletRequest) {
        return requestAuthorized(httpServletRequest, OnPremisesBuildService.FROM_ON_PREMISE_SERVER);
    }

    private boolean requestAuthorized(HttpServletRequest httpServletRequest, int fromWhom) {
        String bearerToken = httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION);
        if (bearerToken != null) {
            bearerToken = bearerToken.replace("Bearer ", "");
            if (fromWhom == OnPremisesBuildService.FROM_JENKINS) {
                return bearerToken.equals(ApplicationConstants.JENKINS_BEARER_TOKEN);
            } else if (fromWhom == OnPremisesBuildService.FROM_ON_PREMISE_SERVER) {
                return bearerToken.equals(ApplicationConstants.ON_PREMISE_SERVER_BEARER_TOKEN);
            }
        }
        return false;
    }

    Path getGetMainServerBuildsPath(String rootStorageDir, @NonNull BuildOs buildOs) {
        Path p = Paths.get(rootStorageDir, BUILDS_DIR_NAME, buildOs.getBuildDirName());
        p.toFile().mkdirs();
        return p;
    }

    Path getOnPremiseServerBuildsPath(
            String rootStorageDir,
            long onPremiseId,
            @NonNull BuildOs buildOs
    ) {
        Path p = Paths.get(
                rootStorageDir,
                BUILDS_DIR_NAME,
                String.valueOf(onPremiseId),
                buildOs.getBuildDirName()
        );
        p.toFile().mkdirs();
        return p;
    }

    Path getOnPremiseDir(String rootStorageDir, long onPremiseId) {
        Path p = Paths.get(rootStorageDir, ON_PREMISE_DIR_NAME, String.valueOf(onPremiseId));
        p.toFile().mkdirs();
        return p;
    }

    Path getBuildFilePath(Path buildDirPath) {
        File[] files = buildDirPath.toFile().listFiles();
        if (files != null) {
            logger.debug("files.length() = {}", files.length);
            for (File file : files) {
                logger.debug("file.getName() = {}", file.getName());
                String fileExtension = FilenameUtils.getExtension(file.getName());
                if ("ZIP".equalsIgnoreCase(fileExtension) || "APK".equalsIgnoreCase(fileExtension)) {
                    return Paths.get(file.getPath());
                }
            }
        } else {
            logger.debug("buildDirPath.toFile().listFiles() is null for App upgrade");
        }
        return null;
    }

    /**
     * @param onPremiseId if null the get main server's build; If specified then get the on premise
     * build
     * @param buildOs either {@link BuildOs#ANDROID} or {@link BuildOs#WINDOWS}
     * @return {@link String} versionName of the build
     */
    String getSavedBuildVersion(
            @Nullable Long onPremiseId,
            @NonNull BuildOs buildOs,
            LatestBuildVersionsByBuildOsRepository latestBuildVersionsByBuildOsRepository
    ) {
        Optional<LatestBuildVersionsByBuildOs> optionalLatestBuildVersionsByBuildOs;
        if (onPremiseId == null) {
            optionalLatestBuildVersionsByBuildOs =
                    latestBuildVersionsByBuildOsRepository.getForMainServerByBuildOs(buildOs.getDbValue());
        } else {
            optionalLatestBuildVersionsByBuildOs =
                    latestBuildVersionsByBuildOsRepository.getByOnPremisesIdAndBuildOs(onPremiseId,
                            buildOs.getDbValue());
        }
        return optionalLatestBuildVersionsByBuildOs
                .map(LatestBuildVersionsByBuildOs::getVersion)
                .orElse(null);
    }

    /**
     * @param repository {@link LatestBuildVersionsByBuildOsRepository}
     * @param premisesId {@link Long}
     * @return list or empty list
     */
    List<LatestBuildVersionsByBuildOs> getLatestBuildVersionsByBuildOsFromDb(
            @NonNull LatestBuildVersionsByBuildOsRepository repository,
            @Nullable Long premisesId
    ) {
        return premisesId == null ? repository.getForMainServer() :
                repository.getByOnPremisesId(premisesId);
    }

    /**
     * @param repository {@link LatestBuildVersionsByBuildOsRepository}
     * @param premisesId {@link Long}
     * @return null if no record exists in DB
     */
    Optional<LatestBuildVersionsByBuildOs> getLatestBuildVersionsByBuildOsFromDb(
            @NonNull LatestBuildVersionsByBuildOsRepository repository,
            @Nullable Long premisesId,
            @NonNull BuildOs buildOs
    ) {
        return premisesId == null ?
                repository.getForMainServerByBuildOs(buildOs.getDbValue())
                : repository.getByOnPremisesIdAndBuildOs(premisesId, buildOs.getDbValue());
    }

    public void deleteFilesFromDirIfExists(String directory) throws IOException {
        File dir = new File(directory);
        File[] dir_contents = dir.listFiles();
        if (dir_contents != null) {
            for (File dir_content : dir_contents) {
                Files.delete(dir_content.toPath());
            }
        }
    }

    boolean deleteFileIfExists(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            Files.delete(file.toPath());
        }
        return false;
    }

   /* void copyDownloadBuildFromDbToDTO(
            BuildOs buildOs,
            Long onPremiseId,
            LatestBuildVersionsByBuildOsRepository repository,
            JenkinsBuildVersionResponseDTO jenkinsBuildVersionResponseDTO
    ) {
        Optional<LatestBuildVersionsByBuildOs> latestBuildVersionsByBuildOsFromDb =
                getLatestBuildVersionsByBuildOsFromDb(repository, onPremiseId, buildOs);
        if (latestBuildVersionsByBuildOsFromDb.isPresent()) {
            jenkinsBuildVersionResponseDTO.setIsBuildPresent(true);
            jenkinsBuildVersionResponseDTO.setFileName(
                    latestBuildVersionsByBuildOsFromDb.get().getFilename(isS3Enabled()));
            jenkinsBuildVersionResponseDTO.setAppVersion(
                    latestBuildVersionsByBuildOsFromDb.get().getVersion());
            jenkinsBuildVersionResponseDTO.setMd5Hash(
                    latestBuildVersionsByBuildOsFromDb.get().getBuildMd5Checksum());
            jenkinsBuildVersionResponseDTO.setBuildOs(buildOs);
        } else {
            jenkinsBuildVersionResponseDTO.setIsBuildPresent(false);
            jenkinsBuildVersionResponseDTO.setDownloadLink(null);
        }
    }*/
}


