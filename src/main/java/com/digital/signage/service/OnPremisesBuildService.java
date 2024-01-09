package com.digital.signage.service;

import org.springframework.stereotype.Service;

@Service
public interface OnPremisesBuildService {
    int FROM_JENKINS = 1;
    int FROM_USER = 2;
    int FROM_ON_PREMISE_SERVER = 3;

}
