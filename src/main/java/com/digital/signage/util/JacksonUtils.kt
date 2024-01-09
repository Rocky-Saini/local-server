package com.digital.signage.util

import com.digital.signage.models.Response
import com.fasterxml.jackson.core.type.TypeReference

/**
 * @author -Ravi Kumar created on 1/17/2023 4:50 PM
 * @project - Digital Sign-edge
 */

val typeRefOfMapOfStringToObject: TypeReference<Map<String, Map<String, Any>>> = object :
    TypeReference<Map<String, Map<String, Any>>>() {}

val typeRefOfMapOfStringToMapOfStringToObject: TypeReference<HashMap<String, HashMap<String, Any>>> = object :
    TypeReference<HashMap<String, HashMap<String, Any>>>() {}

val typeRefOfMapOfStringToString: TypeReference<HashMap<String, String>> = object :
    TypeReference<HashMap<String, String>>() {}

val typeRefOfResponseOfOutAny = object : TypeReference<Response<out Any>>() {}