## Index

1. [Model](#model)
2. [Common Success Response](#common-success-response)
3. [Common Actions for Multiple Actions](#common-actions-for-multiple-actions)
4. [Common Error Response](#common-error-response)
5. [Auth](#auth)
6. [Logout](#logout)
7. [My Profile](#my-profile)
8. [User](#user)
9. [Password](#update-password)
10. [Customer](#customer)
11. [Update Point of Contact (PoC)](#update-point-of-contact-poc)
12. [Roles](#role)
13. [Location Heirarchy](#location-hierarchy)
14. [Device Config](#device-config)
15. [Device Group](#device-group)
16. [Content](#content)
17. [Bandwidth & Storage](#bandwidth-storage)
18. [Campaign States Enum](#campaignlayout-states-enum)
19. [Templates and Layouts](#templates-and-layouts)
20. [Layout Strings](#layout-string-campaign-string)
21. [Device Management](#device-management)
22. [Panel](#panel)
23. [Data collection](#data-collection)
24. [Animations](#animations)
25. [Device Add local server](#device-add-local-server)
26. [Push](#push)
27. [Device Onboarding](#device-onboarding)
28. [Customer onboarding](#set-customer-is-onboarded-api)
29. [Work flow](#set-customer-work-flow)
30. [Set Role level mapping for customer](#set-role-level-mapping-for-a-customer)
31. [Upload device snapshots](#upload-device-snapshots)
32. [Planogram](#create-new-planogram)
33. [Get devices for planogram](#get-devices-for-planogram)
34. [Get supported content formats for file upload](#get-supported-content-formats-for-file-upload)
35. [Save location-wise content playlist for layout](#save-location-wise-content-playlist-for-layout)
36. [Get location-wise content playlist for layout](#get-location-wise-content-playlist-for-layout)
37. [Is email valid for point of contact](#is-email-valid-for-point-of-contact)
38. [Update panel status (Devices only)](#update-panel-statuses-per-deviceid-devices-only)
39. [Get panel status by deviceId](#get-panel-status-by-deviceid)
40. [Device error reporting](#device-error-reporting-devices-only)
41. [Set planogram priorities](#set-planogram-priorities)
42. [Layout clone](#layout-clone)
43. [Associate Panasonic Customer Representative to customers](#associate-panasonic-customer-representative-to-customers)
44. [Get Panasonic Customer Representative Associations with customers](#get-panasonic-customer-representatives-customer-associations)
45. [Does user have access to these locations in a given layout](#does-user-have-access-to-these-locations-in-a-given-layout)
46. [Panel Update by deviceId](#panel-update-by-deviceid)
47. [Panel Update by deviceId validations](#panel-update-by-deviceid-validations-api)
48. [USB Download](#usb-download)
49. [Get device (to be called by devices only)](#get-device-to-be-called-by-devices-only)
50. [Get location hierarchy with user editable flags](#get-location-hierarchy-with-user-editable-flags)
51. [Get user's own profile](#get-users-own-profile)
52. [Get planogram by individual device Id](#planogram-by-individual-device-id)
53. [Get layout for device](#get-layout-for-device)
54. [Planogram bulk status update (active, inactive, delete)](#planogram-bulk-status)
55. [Planogram device logic delete](#planogram-device-logic-delete)
56. [Device basic search](#device-basic-search)
57. [Get all customers which are not associated with given PAN REP user](#get-all-customers-which-are-not-associated-with-given-pan_rep-user)
58. [Device - add self](#device-add-self-to-be-called-by-devices-only)
59. [Get list of unregistered devices](#get-list-of-unregistered-devices)
60. [Approval Flow](#approval-flow)
61. [Layout submit](#layout-submit)
62. [Layout approve](#layout-approve)
63. [Layout reject](#layout-reject)
64. [Layout string submit](#layout-string-submit)
65. [Layout string approve](#layout-string-approve)
66. [Layout string reject](#layout-string-reject)
67. [Planogram submit](#planogram-submit)
68. [Planogram approve](#planogram-approve)
69. [Planogram reject](#planogram-reject)
70. [Get devices which are ungrouped and under a locationId](#get-devices-which-are-ungrouped-and-under-a-locationid)
71. [Get my role](#get-my-role)
72. [Get states](#get-states)
73. [Pending for approvals list](#pending-for-approval-list)
74. [Layouts pending for my approval](#layouts-pending-for-my-approval)
75. [Layout strings pending for my approval](#layout-strings-pending-for-my-approval)
76. [Planograms pending for my approval](#planogram-pending-for-my-approval)
77. [Delete panel](#delete-panel)
78. [Get current time in milliseconds](#get-current-time-in-milliseconds)
79. [Notify devices for app upgrade](#notify-devices-of-a-client-app-upgrade)
80. [Download latest app](#to-download-latest-app-package)
81. [Web push registration](#web-push-registration)
82. [Web push mark as read](#web-push-mark-as-read)
83. [Web push get all keys](#web-push-get-all-keys)
84. [Web push get all notifications](#web-push-get-all-notifications)
85. [Reports](#reports)
86. [Get devices by location for reports](#get-devices-by-location-for-reports)
87. [Unscheduled media player report](#unscheduled-media-player-report)
88. [Get modules for user activity report](#get-modules-for-user-activity-report)
89. [User activity report](#user-activity-report)
90. [Start and end download (Device only)](#start-end-download-api)
91. [Intermediate download position (Device only)](#intermediate-download-position-api)
92. [Content playback analytics](#content-playback-analytics)
93. [Listen to device and panel changes](#listen-to-device-and-panel-changes)
94. [Media player on off logs](#media-player-on-off-logs)
95. [Statistics](#statistics)
96. [Media player on off logs %age](#media-player-on-off-percentage)
97. [Current download](#current-download)
98. [Previous download report](#previous-download-report)
99. [Bandwidth report (For panasonic users only)](#bandwidth-report-for-panasonic-users-only)
100. [Bandwidth report per customer](#bandwidth-report-per-customer)
101. [Panel on off report](#panel-on-off-report)
102. [Panel on off percentage report](#panel-on-off-percentage)
103. [Content playback report (Actuals)](#content-playback-report-actuals)
104. [Planogram expected report](#planogram-expected-report)
105. [Get user API for advance search filter](#get-user-api-for-advance-search-filter)
106. [Dashboard APIs](#dashboard-apis)
107. [User dashboard API](#user-dashboard)
108. [Panasonic dashboard API](#panasonic-dashboard)
109. [Panasonic dashboard per customer API](#panasonic-dashboard-per-customer)
110. [Customer admin dashboard API](#customer-admin-dashboard)
111. [Recently added planos](#recently-added-planograms)
112. [Recently added contents](#recently-added-contents)
113. [Recently added layouts](#recently-added-layouts)
114. [Recently added layout strings](#recently-added-layouts-strings)
115. [Recently added devices](#recently-added-devices)
116. [RecPush Json to be sent to deviceently inactive devicePush Json to be sent to devices](#recently-inactive-devices)
117. [Recently added customers](#recently-added-customers)
118. [Recent planogram published but not running](#recent-planogram-published-but-not-running)
119. [Recent planogram published and playing right now](#recent-planogram-published-and-playing-right-now)
120. [Recently approved and rejected layouts](#recently-approved-and-rejected-layouts)
121. [Recently approved and rejected layout strings](#recently-approved-and-rejected-layout-strings)
122. [Recently approved and rejected planograms](#recently-approved-and-rejected-planograms)
123. [Country code](#get-country-code-api)
124. [Customer default planogram landscape image upload](#customer-default-planogram-landscape-image-upload)
125. [Customer default planogram landscape image download](#customer-default-planogram-landscape-image-download)
126. [Customer default planogram portrait image upload](#customer-default-planogram-portrait-image-upload)
127. [Customer default planogram portrait image download](#customer-default-planogram-portrait-image-download)
128. [Delete customer default planogram portrait image](#delete-customer-default-planogram-portrait-image)
129. [Delete customer default planogram landscape image](#delete-customer-default-planogram-landscape-image)
130. [Customer config](#customer-config)
131. [Customer update config](#customer-update-config)
132. [Localization](#localization)
133. [Immediate snapshot](#immediate-snapshot)
134. [Request for a current snapshot if a device](#request-for-a-current-snapshot-if-a-device)
135. [Upload a snapshot of current timestamp](#upload-a-snapshot-of-current-timestamp)
136. [Download current image snapshot](#download-current-image-snapshot)
137. [Fetch these files on local server](#fetch-these-files-on-local-server)
138. [Get all planogram titles of a customer regardless of they are expired or not](#get-all-planogram-titles-of-a-customer-regardless-of-they-are-expired-or-not)
139. [Can send password reset email to this PoC user?](#can-send-password-reset-email-to-this-poc-user)
140. [Fetch Panasonic config](#fetch-panasonic-config)
141. [Update Panasonic config](#update-panasonic-config)
142. [Facebook Access Token for devices](#facebook-access-token-for-devices)

## Setting Customer ID for Panasonic Users

When a panasonic user wants use an API on behalf of a customer then set the following Header

Header

    customerId: 45

If customerId is not sent for Panasonic users

## API List

## Status of entity

In each entity there is an integer status it's values are as shown below:
1 = `ACTIVE`
2 = `INACTIVE`
3 = `DELETED`

## Model

#### UserModel

```json
{
  "userId": 32,
  "fullName": "Jane Doe",
  "email": "scarlett.johansson@impressico.com",
  "profileImage": "/assets/img/app/profile/Scarlett-Johansson.jpg",
  "mobile": "9876543210",
  "countryCode": "+91",
  "status": 1,
  "userType": "SUPER", // (possible values are "SUPER", "CUST_REP_ADMIN" and null) // Note: for server team: if user is a panasonic user then set the value as SUPER otherwise if role is PANASONIC_CUST_REP then CUST_REP_ADMIN
  "address": "Noida"
  "createdOn": "1517824357000",  // unix epoc in millis
  "modifiedOn": "1517824357000", // unix epoc in millis
  "roles": [
    {
      "roleId": 1,
      "roleName": "PANASONIC_ADMIN"
    }
  ],
  "customerId": 738,
  "canAddAndEditMasterLayouts": true,      // true if user is not location restricted and can edit layouts, false otherwise
  "locations": [                        // optional could be null if user is not associated with any location
    {
      "locationId": 123,
      "locationName": "Delhi"
    },
    {
      "locationId": 33,
      "locationName": "Noida"
    }
  ]
}
```

#### CustomerModel

```json
{
  "customerId": 32,
  "custName": "Maruti Suzuki",
  "customerUId": "MARUTISUZUKI",
  "contactNo": "9876543210",
  "pointOfContact": 24,                                 // userId in customer.dfltUserId field (this will only be available in GET)
  "pointOfContactEmail": "abc@impressico.com",          // email of PoC user (this will only be available in GET)
  "pointOfContactName": "Jane Doe",                     // name of PoC (this will only be available in GET)
  "pointOfContactStatus": 1,                            // status of PoC (this will only be available in GET)
  "countryCode": "+91",                                 // country code of PoC's number
  "mobile": "9898989898"                                // mobile number of PoC
  "address": "1089/A, Old Palam Gurgaon Road, Chakkarpur, Gurugram, Haryana 122001",
  "status": 1,
  "canPansonicAdminAccessCMS":1
  "numberOfApproverLevel":1,                            // required, integer, min:1, max:3
  "createdOn": "1517824357000",                         // unix epoc in millis
  "modifiedOn": "1517824357000",                        // unix epoc in millis
  "numberOfDevices":50,
  "consumedNumberOfDevices": 34,                        // number of consumed devices
  "licenceStartDate":"1517824357000",                   // unix epoc in millis
  "licenceEndDate":"1517824357000",                     // unix epoc in millis
  "approvalWorkFlow": "LAYOUT | LAYOUT_STRING | PLANOGRAM | PLANOGRAM_AND_LAYOUT | NONE", // one of the values of this enum (optional)
  "bandwidth": 50.3,                                    // this consumable should be for last date only
  "bandwidthUnit": "GB|MB|KB",                          // enum (GB, MB or KB)
  "storage": 50.3,                                      // this consumable should be for last date only
  "storageUnit": "GB|MB|KB",                            // enum (GB, MB or KB)
  "consumableDate": 1517824357000,                      // unix epoc in millis
  "isCustomerOnboarded": true,                          // defines if customer is onboarded
  "alternatePhoneNumber": "3424234",
  "contactNoCountryCode": "+91",
  "alternateNumberCountryCode": "+91",
  "totalMonthlyLimitedBandwidth": "100",
  "totalMonthlyLimitedBandwidthUnit": "GB|TB",
  "consumedMonthlyBandwidth": 3,
  "consumedMonthlyBandwidthUnit": "GB|TB",
  "availableMonthlyBandwith": 32,
  "availableMonthlyBandwithUnit": "GB|TB",
  "uniqueCustomerIdMask": "AB6C3",
  "customerType": "BASIC",                             // possible values, BASIC, ADVANCE
  "panNumber": "ABC333AAA33",
  "dateOfPurchaseOfPanel": "12-12-2012",
  "enableDemographic": true,                           // true or false
  "leftNavLogoUrl": "http://ssss",                     // available only in customer get and not in POST and PUT
  "centerLogoUrl": "http://sss"                        // available only in customer get and not in POST and PUT
  "leftNavLogoUrlThumb": "http://sss",                 // null if not present
  "centerLogoUrlThumb": "http://sss",                  // null if not present
  "isAdvertisementEnabled": true                       // or false
}
```

#### LocationHierarchyModel

```json
{
  "locationId": 3,
  "locationName": "Delhi NCR",
  "childNode":[
    {
      "locationId": 12,
      "locationName": "Delhi",
      "childNode":[
        {
          "locationId": 20,
          "locationName": "Connaught Place",
          "childNode":[]
        },
        ...
      ]
    },
    {
      "locationId": 13,
      "locationName": "Noida",
      "childNode":[
        {
          "locationId": null,
          "locationName": "Secter 18",
          "childNode":[]
        },
        ...
      ]
    },
    ...
  ]
}

```

#### DeviceModel on pidev and prod etc..

```json
{
  "deviceId": 143,
  "clientGeneratedDeviceIdentifier": "77eaea49-a768-43e1-9446-e4ec76457c2f",
  "deviceName": "Cool Device",
  "deviceGroupId": 129,
  "deviceGroupName": "Cafeteria",
  "locationId": 82,
  "location": {
    "locationId": 82,
    "locationName": "Noida",
    "locationsWithoutLeafNode": [ "Noida", "Uttar Pradesh", "India"]
  },
  "deviceOs": "ANDROID|WINDOWS|LINUX",
  "deviceWifiMacAddress": "fc:67:36:f6:a3:7e",
  "deviceEthernetMacAddress": "ac:e5:36:f6:7e:a3",
  "isAudioEnabled": "ON",  // possible values "ON", "OFF" and "PENDING"
  "localServerIP": "192.168.0.54"    // to be used only in GET API not in PUT / POST API
  "status": 1,
  "lastSyncTime": 156834993032,  // unix epoc timestamp
  "lastAccess": 156834993032,  // unix epoc timestamp
  "aspectRatioId": 12,
  "isManuallyAdded": true,  // true if user was added manually using old add device method and false if added using new method
  "panels": [
    {PanelModel},
    {PanelModel}
  ],
  "deviceConnectivity": "CONNECTED", // possible values CONNECTED, DISCONNECTED, null
  "timeOfDeviceStatus": 156834993032, // unix epoch
  "inActiveTime": 156834993032, // time the device went inactive in unix epoch
  "appVersion": "2.5443.53",
  "camera": {
    "cameraId" : 12,
    "deviceId": 143,
    "cameraType": "USB",            // possible values : IP, USB, NO_CAM, NOT_APPLICABLE
    "cameraIp": "192.168.23.55",    // required only when camera is IP otherwise not required
    "cameraPurpose": "ONLY_COLLECT_DATA"     // possible values : ONLY_COLLECT_DATA, COLLECT_DATA_AND_RUN_DEMOGRAPHIC_CAMPAIGNS
  },
  "latitude": 28.535517, // null if not present
  "longitude": 77.391029, // null if not present
  "latitudeDMS": "28° 32' 7.8612'' N", // null if not present
  "longitudeDMS": "77° 23' 27.7044'' E" // null if not present
}
```

#### DeviceModel New (Only on pidev2)

```json
{
  "deviceId": 143,
  "clientGeneratedDeviceIdentifier": "77eaea49-a768-43e1-9446-e4ec76457c2f",
  "deviceName": "Cool Device",
  "deviceGroupId": 129,
  "deviceGroupName": "Cafeteria",
  "locationId": 82,
  "location": {
    "locationId": 82,
    "locationName": "Noida",
    "commaSeparatedStringWithoutLeafNode": "Noida, Uttar Pradesh, India"
  },
  "deviceOs": "ANDROID|WINDOWS|LINUX",
  "deviceWifiMacAddress": "fc:67:36:f6:a3:7e",
  "deviceEthernetMacAddress": "ac:e5:36:f6:7e:a3",
  "isAudioEnabled": "ON",  // possible values "ON", "OFF" and "PENDING"
  "localServerIP": "192.168.0.54"    // to be used only in GET API not in PUT / POST API
  "status": 1,
  "lastSyncTime": 156834993032,  // unix epoc timestamp
  "lastAccess": 156834993032,  // unix epoc timestamp
  "aspectRatioId": 12,
  "isManuallyAdded": true,  // true if user was added manually using old add device method and false if added using new method
  "panels": [
    {PanelModel},
    {PanelModel}
  ],
  "deviceConnectivity": "CONNECTED", // possible values CONNECTED, DISCONNECTED, null
  "timeOfDeviceStatus": 156834993032, // unix epoch
  "inActiveTime": 156834993032, // time the device went inactive in unix epoch
  "appVersions": [
     {
       "buildOs": "WINDOWS",
       "version": "1.34.52"
     },
     {
       "buildOs": "ANDROID",
       "version": "1.34.52"
     },
     {
       "buildOs": "ANDROID_WATCH_DOG",
       "version": "1.34.52"
     },
     {
       "buildOs": "DESKTOP_APP_SERVER",
       "version": "1.34.52"
     },
     {
       "buildOs": "DESKTOP_APP_CLIENT",
       "version": "1.34.52"
     },
     {
       "buildOs": "DESKTOP_APP_NATIVE",
       "version": "1.34.52"
     }
  ],
  "camera": {
    "cameraId" : 12,
    "deviceId": 143,
    "cameraType": "USB",            // possible values : IP, USB, NO_CAM, NOT_APPLICABLE
    "cameraIp": "192.168.23.55",    // required only when camera is IP otherwise not required
    "cameraPurpose": "ONLY_COLLECT_DATA"     // possible values : ONLY_COLLECT_DATA, COLLECT_DATA_AND_RUN_DEMOGRAPHIC_CAMPAIGNS
  },
  "latitude": 28.535517, // null if not present
  "longitude": 77.391029, // null if not present
  "latitudeDMS": "28° 32' 7.8612'' N", // null if not present
  "longitudeDMS": "77° 23' 27.7044'' E" // null if not present
}
```

#### PanelModel

```json
{
  "panelId": 132,
  "isAudioEnabled": "OFF",   // OFF, ON, PENDING
  "panelStatus": "ON_HDMI_CONNECTED",
  "panelIp": "192.168.0.56",
  "deviceId": 323,
  "panelName": "First floor lobby",
  "panelSerialNumber": "SR445X4454",
  "status": 1,     // 1 = ACTIVE, 2 = INACTIVE and 3 = DELETED
  "timeOfPanelStatus": 156834993032, // unix epoch
  "panelActivityStatus": "ON", // Represents the panel status. Possible values ON, OFF, DISCONNECTED, null (unknown), PENDING
  "hdmiActivityStatus": "CONNECTED", // Represents if HDMI cable is CONNECTED, DISCONNECTED, null (unknown), PENDING
  "panelControl": "RJ45" // Possible values: RS232_RM_HDMI_ONLY, PANEL_OTHERS, RJ45. These are all enum values, actual display strings are part of the GET /localization API
}
```

Values for Panel Statues:

1. `ON_HDMI_CONNECTED`,
2. `ON_HDMI_DISCONNECTED`,
3. `OFF_HDMI_CONNECTED`,
4. `OFF_HDMI_DISCONNECTED`,
5. `DISCONNECTED_HDMI_CONNECTED`,
6. `DISCONNECTED_HDMI_DISCONNECTED`
7. `DATA_NOT_AVAILABLE`

Values for `panelControl`:

1. `RS232_RM_HDMI_ONLY`
2. `PANEL_OTHERS`
3. `RJ45`

## Common Success Response

#### GetListWithPagination

```json
{
  "result": [
    {EntityModel},
    ...
  ],
  "pagination": {
    "numPerPage": 20,      // the number used for calulating a page size (will be same as the one sent in request. If not sent then it will have a default value we have set. If the value sent is larger than the default value set then also we will set this with default value)
    "currentPage": 1,      // current page number of the response
    "pageCount": 2,        // count of total number of pages
    "previousPage": null,  // previous page null if current page is page 1 otherwise it will be the next value
    "nextPage": 2,         // next page null is current page is last page else it will be next page number
    "firstItemNumber": 1,  // index of first item of this page
    "lastItemNumber": 20,  // index of the last item of this page
    "totalItemCount": 30   // actual total items on this page
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### SuccessfullySaved

```json
{
  "result": {EntityModel},
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

#### SuccessfullyUpdated

```json
{
  "result": {EntityModel},
  "name": "SuccessfullyUpdated",
  "code": 30,
  "message": "Record Updated Successfully"
}
```

#### SuccessfullyDeleted

```json
{
  "result": null,
  "name": "SuccessfullyDeleted",
  "code": 22,
  "message": "Record Deleted Successfully"
}
```

#### SuccessfullyMultipleSaved

## Common Actions for Multiple Actions

RESPONSE - code - 200 // always send 200

```json
{
  "result": {
    "success": [
      1,
      54,
      767
    ],
    "badRequest": [ // should be used for specific business cases on per API basis
      {
        "id": 1,
        "message": "Already in use. Cannot delete" // this is an example
      }
    ],
    "notFound": [
      9,
      67,
      24
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

## Common Error Response

#### ValidationError

RESPONSE - code - 400

Validation Error on the time of insert or update API call.

```json
{
  "result": [
    {
      "field" : "email",
    },
    ...
  ],
  "name": "ValidationError",
  "code": 8,
  "message": "Invalid Request Parameter"
}
```

#### Unauthorized

RESPONSE - code - 401

Send this response when: 1. the apiToken sent by client is incorrect. 2. apiToken is not present in the header 3.
apiToken has already expired

```json
{
  "result": null,
  "name": "Unauthorized",
  "code": 6,
  "message": "Unauthorized."
}
```

#### Not Found

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "NotFound",
  "code": 1, // some int value
  "message": "Message to explain what was not found. Example: content not found, customer not found"
}
```

#### UnknownError

RESPONSE - code - 500

Server should send this response if it is down or for some reason they are not able to access their database or any
unexpected error they could not handle.

```json
{
  "result": null,
  "name": "UnknownError",
  "code": 5,
  "message": "Something went wrong. Please try again later."
}
```

## CMS Access

When a PANASONIC_ADMIN user attempts to access any CMS APIs of a customer whose CMS access is disabled then they will
get the following response with HTTP status code `403`

```json
{
  "result": "CMS Access Not Allowed",
  "name": "CMSAccessNotAllowed",
  "code": 1007,
  "message": "You cannot access CMS because CMS access is disabled by the customer"
}
```

## Auth

Everything about your Authentication and Authorization.

#### Login

    /login [POST]

HEADER

    Content-Type:application/json

BODY

```json
{
  "email":"string",
  "password":"string"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "token": <authToken>,
    "rabbitUsername" : "rabbitMQusername",
    "rabbitMqServerUrl": "rabbitMqServerUrl",
    "shouldResetPassword": true|false,
    "user": {UserModel}
  },
  "name": null,
  "code": null,
  "message": null
}
```

Result (object): This object will have content apiToken, user, customerId.

apiToken (string) - 200 to 400 length of string that will use further API calls in header.
This token should be unique and server should store it for 24 hrs (can be changed) or if user logout or user account is
suspended.

user (User object) - User object with basic info to be returned if the login was successful. This can be used in
subsequent APIs calls to represent the user and display in header section.

customerId (Integer) - customer id of the user to be returned if the login was successful. Should be `null` in case of
all types of panasonic users.
This can be used in subsequent APIs calls to represent the user.

shouldResetPassword (Boolean) - User should reset password after login

`customer` object returned as part of the user object in login API won't have licencing information.

name, code, message : these are for error or success messages.

Clients(i.e panasonic web admin app) should save this apiToken locally in persistent storage. They should clear this
data when user logs out or if any auth protected API returns 401 Unauthorized response

RESPONSE - code - 400

Validation errors will be sent if the email format is incorrect or either email for password is missing.

```json
{
  ValidationError;
}
```

RESPONSE - code - 401

```json
{
  "result": null,
  "name": "InvalidEmail",
  "code": 1,
  "message": "Email is not register yet. Please signup."
}
```

RESPONSE - code - 401

```json
{
  "result": null,
  "name": "UserInactive",
  "code": 4,
  "message": "User is in inactive state. Please contact to administrator."
}
```

#### Logout

    /logout [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "registrationId": "asdkwnrogjoewir34f3j90fj340"   // web push registration ID given from FCM server. Json key is required but value can be null if registrationId is not there
}
```

RESPONSE - code - 200

```json
{
  "result": true,
  "Name": null,
  "code": null,
  "message": null
}
```

## My profile

Manage own user profile

Details : **Panasonic Digital Signage - Profile API** file in this
location https://drive.google.com/open?id=19d15LC5r1F7f3cnBMMnXJRTzzT8Laxs_6N8HEheoEjg

#### Update user

    /user/profile  [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>    // pick the user Id from here for profile update

BODY

```json
{
  "fullName": "Jane Doe",
  "profileImage": "string",
  "mobile": "string",
  "countryCode": "+91",
  "address": "Noida"
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

RESPONSE - code - 400

```json
{
  ValidationError;
}
```

## User

Manage user by add, edit, delete, list etc.

#### Create new user

    /user  [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "fullName": "Jane Doe",
  "email": "string", // required and should be validated
  "mobile": "string",
  "countryCode": "+91",
  "status": 1,       // 1 or 2
  "address": "Noida",
  "roleIds": [1, 3],       // should be a valid roleIds
  "locationIds": [1232, 45]  // optional
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "insertedId": 0,
    "entity": {UserModel}
  },
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 400

```json
{
  "result": {
    "name": "AlreadyExists",
    "message": "Email already exist."
  },
  "name": "AlreadyExists",
  "code": 41,
  "message": "Email already exist."
}
```

```json
{
  ValidationError;
}
```

```json
{
  "result": null,
  "name": "LocationError",
  "code": 51,
  "message": "Both child and parent locationIds are provided"
}
```

#### Update user

    /user/<userId>  [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "fullName": "Jane Doe",
  "profileImage": "string",
  "mobile": "string",
  "countryCode": "+91",
  "address": "Noida",
  "status": 1,
  "locationIds": [1232, 45],
  "roleIds": [1, 43]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

RESPONSE - code - 400

```json
{
  ValidationError;
}
```

#### User can be edit status by one or multiple userId.

    /user  [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ],
  "status": 1    // 1 or 2
}
```

RESPONSE - code - 200

Resonse - Common Actions for Multiple Actions

RESPONSE - code - 400

```json
{
  ErrorInSave;
}
```

#### User can be delete by one or multiple userId.

    /user  [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

Resonse - Common Actions for Multiple Actions

### Get user list with filter data and pagination.

    /user  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}  // if the user is a panasonic user then
                                  // the returned user list should contain
                                  // users belonging to all customers
                                  // else if the user belongs to a customer
                                  // then return only users belonging to a
                                  // customer

QUERY

    email=<string>                          Optional and only accept string.
    fullName=<string>                       Optional and only accept string.
    currentPage=1                           Optional
    numPerPage=20                           Optional - the number that show items per page.
    customerId=4                            Optional - to be used by PANSONIC_ADMIN only. If the customerId is not set
    userType=SUPER|CUST_REP_ADMIN           Optional - if SUPER is sent then only pan admin users are filter, if CUST_REP_ADMIN is sent the only cust rep users are sent

Note: For panasonic user customerId can be passed in header for filtering

RESPONSE - code - 200

```json
{
  "result": [
    {UserModel},
    ...
  ],
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  },
  "name": null,
  "code": null,
  "message": null
}
```

### User Advance Search.

    /user?q={<query parameters>}  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}  // if the user is a panasonic user then
                                  // the returned user list should contain
                                  // users belonging to all customers
                                  // else if the user belongs to a customer
                                  // then return only users belonging to a
                                  // customer

QUERY

    One can use operand(s) (lt-less than, gt-greater than, eq-equal to, lte-less than or equal to, gte-greater than or equal to, lk-like:applicable for text types)
    on following fields in json format:
    email (supports lk and eq)
    fullname (supports lk and eq)
    created_date
    role  (roleId)
    location_access  (takes location Id, returns the user with Karntka access, if searched for bangalore. vice versa not true)
    status (enum ACTIVE, INACTIVE)
    (All above filters are optional, all filters apply using a logical AND i.e. with each extra filter dataset should reduce or remain same
    operands apart from eq, lk can be used only for date and numeric (not Ids) filters)

EXAMPLE

    /user?q={"email":{"eq": "abc@gmail.com"},"created_date":{"lt":1576616761, "gt":17678675656}, "status":{"eq":"ACTIVE"}}

    Type ahead search example : /user?q={"fullname":{"lk":"John"}}

Note: For panasonic user customerId can be passed in header for filtering

RESPONSE - code - 200

```json
{
  "result": [
    {UserModel},
    ...
  ],
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get single user by userId

    /user/<userId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {UserModel},
  "name": null,
  "code": null,
  "message": null
}
```

#### Update Password after forgot password

    /password  [POST]

HEADER

    Content-Type:application/json
    // not auth token required

BODY

```json
{
  "password": "my cool password",
  "emailToken": "asfkwehfoewfowjf"
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyUpdated;
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "emailToken",
      "message": "Invalid email token"
    },
    {
      "field": "password",
      "message": "Password is not complex"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Forgot Password

    /password-forgot  [POST]

HEADER

    Content-Type:application/json
    // not auth token required

QUERY

    email-type=forgot-password|resend-set-password  // (optional) possible values forgot-password and resend-set-password. This is optional param, if not sent the default value will be 'forgot-password'

BODY

```json
{
  "email": "sample@domain.in"     // required
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": ResetPasswordLinkSentToEmail,
  "code": 1,
  "message": "Reset password link send to user's email"
}
```

Note: Send email to user with a link. That link will have a token as part of a
URL query param. The URL will be of the angular web app. The encryted token should
have information about what the user's email address and a flag that user has sent
this token as part of forgot password API.

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "InvalidEmail",
  "code": 1,
  "message": "Invalid email"
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "UserNotExist",
  "code": 1,
  "message": "User not exist."
}
```

#### Change Password

    /changepassword  [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer <Token>

BODY

```json
{
  "oldPassword": "sdasdsadsad",     // required
  "newPassword": "sadaspods32e"
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "PassowrdUpdationSuccess",
  "code": 200,
  "message": "Password updation successful"
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "oldPassword",
      "message": "Old password in invalid"
    },
    {
      "field": "newPassword",
      "message": "Password complexity error"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## Update Point Of Contact (PoC)

    /point-of-contact/{customerId} [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

QUERY

    append-customer-created-error = true | false (optional)  // if this flag is sent as true then if there is an error in the request then in the error a message will be appended that the customer was created successfully.

BODY

```json
{
  "email": "hello@world.com",
  "fullName": "Jane Doe",
  "countryCode": "+91",
  "mobile": "9898989898"
}
```

Server Logic:

```json
user = getUserDetailsByEmail('hello@world.com')

if (isExist(user)) {
  if (isActive(user)) {
    if (isUserConnectedWithCusromer(user, body.customerId)) {
      connectToCustomer(user.userId, body.customerId)
      updateUserName(user, body.name)
    }
    else {
      return Error - User connected to other customer. Please choose another email.
    }
  } else {
    return Error - User is inactive. please choose another email.
  }

}
els e{
  user = createNewUser(email, fullName)
  connectToCustomer(user.userId, body.customerId)
}

createNewUser(){
  // create new user and send mail to user
}

updateUserName(){
  // update name by userid
}

connectToCustomer(){
  // make user to "CUSTOMER_ADMIN"
  // assign user "POC_User" to Customer
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "UserNotExist",
  "code": 1,
  "message": "User not exist."
}
```

RESPONSE - code - 200

```json
{
  "result": "result": {
    "userId": 1874 // userId of user who was updated
  },
  "name": "UserUpdated",
  "code": 1,
  "message": "User updated"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "userId": 1874 // userId of user whose role was promoted to CUSTOMER_ADMIN
  },
  "name": "UserRoleUpdated",
  "code": 2,
  "message": "User role updated"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "userId": 1875 // userId of newly created user
  },
  "name": "UserCreatedAndEmailSent",
  "code": 3,
  "message": "User created, Email sent"
}
```

## Customer

Manage Customer by add, edit, delete, list etc.

#### Create new customer

    /customer  [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "custName": "string",
  "contactNo": "string",
  "address": "string",
  "status": 1,
  "numberOfDevices": "number",            // required and only accept valid number
  "licenceStartDate": 1516887551000,      // unix epoch and only accept valid date
  "licenceEndDate": 1516887551000,        // unix epoch and only accept valid date
  "alternatePhoneNumber": "3423432432",     // optional
  "contactNoCountryCode": "+91",
  "alternateNumberCountryCode": "+91"
}
```

Customer should be created with default approval levels as `null`.

Customer's approval level needs to be explicitly set to a value during the onboarding process till then this will be
returned as `null` in GET APIs.

RESPONSE - code - 200

```json
{
  "result": {
    "insertedId": 0,
    "entity": {CustomerModel}
  },
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 400

```json
{
  ValidationError;
}
```

Note: if user is CUSTOMER_ADMIN ROLE then they cannot add a customer

RESPONSE - code - 403

```json
{
  "result": null,
  "name": "CannotAddACustomer",
  "code": 1,
  "message": "You do not have privileges to add a customer"
}
```

#### Update customer

    /customer/<customerId>  [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "custName": "string",
  "contactNo": "string",
  "address": "string",
  "status": 1,
  "canPansonicAdminAccessCMS": 1,
  "numberOfDevices": "number",
  "licenceStartDate": 1517845854000,
  "licenceEndDate": 1517845854000,
  "alternatePhoneNumer": "3423432432",
  "contactNoCountryCode": "+91",
  "alternateNumberCountryCode": "+91"
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

RESPONSE - code - 400

```json
{
  ValidationError;
}
```

Note: if user is CUSTOMER_ADMIN ROLE then they will get this error if they update licence count or licence dates

RESPONSE - code - 403

```json
{
  "result": null,
  "name": "CannotUpdateLicence",
  "code": 1,
  "message": "You cannot update licencing information"
}
```

#### Customer can be delete by one or multiple customerId.

    /customer  [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyMultipleDeleted;
}
```

RESPONSE - code - 200

```json
{
  ErrorInMultipleDelete;
}
```

### Get customer list with filter data and pagination.

    /customer  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    custName:<string>                       Optional and only accept string.
    currentPage:1                           Optional
    numPerPage:20                           Optional - the number that show items per page.
    status:1                                Optional - 1 for ACTIVE and 2 for INACTIVE

RESPONSE - code - 200

```json
{
  GetListWithPagination;
}
```

### Customer advance search

    /customer?q={<query parameters>}  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    One can use operand(s) (lt-less than, gt-greater than, eq-equal to, lte-less than or equal to, gte-greater than or equal to, lk-like:applicable for text types)
    on following fields in json format:
    cust_name
    created_date
    cms_access (true/false)
    license_remaining (number)
    license_expiry_date
    status (enum : ACTIVE,INACTIVE)
    csutomerType (BASIC, ADVANCE)

    (All above filters are optional, all filters apply using a logical AND i.e. with each extra filter dataset should reduce or remain same
    operands apart from eq, lk can be used only for date and numeric (not ids) filters)

EXAMPLE

    /customer?q={"license_remaining":{"lt":5}, "cms_access":{"eq":true}, "status":{"eq":"ACTIVE"}}, "customer_type"{"eq":"BASIC"}

RESPONSE - code - 200

```json
{
  GetListWithPagination;
}
```

### Get single customer by customerId

    /customer/<customerId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {customerModel},
  "name": null,
  "code": null,
  "message": null
}
```

#### Customer can be edit status by one or multiple customerId.

    /customer  [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ],
  "status": 2
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyMultipleSaved;
}
```

RESPONSE - code - 400

```json
{
  ErrorInMultipleSave;
}
```

## Role

Add role

    /role  [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "roleName": "Cool role",
  "isApproverRole": true,             // defines if this role should act as a approver role
  "roleDescription":"test description",
  "resourceActionPairs": [
    {
      "resourceName": "PLANOGRAM",
      "actions": [
        "VIEW",
        "ADD",
        "EDIT",
        "DELETE"
      ]
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "ResourceActionPairDoNotMatch",
  "code": 1,
  "message": "Resource {resorceName} and {actionName} do not match"
}
```

Update Role

    /role/<roleId>  [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

```json
{
  "roleName": "Cool role",
  "isApproverRole": true,             // defines if this role should act as a approver role
  "roleDescription":"test description",
  "resourceActionPairs": [
    {
      "resourceName": "PLANOGRAM",
      "actions": [                    // approve should not be part of the actions
        "VIEW",
        "ADD",
        "EDIT",
        "DELETE"
      ]
    }
  ]
}
```

Note: This will update the resourceActionPairs for the given role and not append to the existing pairs.

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "ResourceActionPairDoNotMatch",
  "code": 1,
  "message": "Resource {resourceName} and {actionName} do not match"
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "approverLevel",
      "message": "Customer does not have those many approver levels"
    }
  ],
  "name": "ValidationError",
  "code": 8,
  "message": "Invalid Request Parameter"
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "actions",
      "message": "Approve should not be part of the actions"
    }
  ],
  "name": "ValidationError",
  "code": 8,
  "message": "Invalid Request Parameter"
}
```

RESPONSE - code - 400

```json
{
  ValidationError;
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "RoleNotExist",
  "code": 1,
  "message": "Role does not exist"
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "isApproverRole",
      "message": "Cannot convert this approver role to a non-approver one as it is being used at level {X} in approver level mapping"
    }
  ],
  "name": "ValidationError",
  "code": 8,
  "message": "Invalid Request Parameter"
}
```

Delete Role

    /role/<roleId>  [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

    NA

RESPONSE - code - 200
{
"result": null,
"name": "DeletedSuccessfully",
"code": null,
"message": "Role deleted successfully"
}

Get Roles

    /role  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

    N/A

RESPONSE - code - 200

```json
{
  "roles": [
    {
      "roleId": 1,
      "roleName": "CUSTOMER_ADMIN",
      "isCustomRole": false,
      "isRoleUsedInApproverLevelMapping": false, // this key will be present only on custom roles and not on system roles. If false then it means that the custom role is not used in any approver level mapping. If true then it means it is used in approver level mapping
      "isApproverRole": false,
      "roleDescription":"test description",
      "isLocationRestricted": false,
      "resourceActionPairs": [
        {
          "resourceName": "PLANOGRAM",
          "actions": [
            "VIEW",
            "ADD",
            "EDIT",
            "DELETE"
          ]
        },
        {
          "resourceName": "DEVICE",
          "actions": [
            "VIEW",
            "ADD",
            "EDIT",
            "DELETE"
          ]
        }
      ]
    },
    {
      "roleId": 2,
      "roleName": "VIEW_ONLY",
      "isCustomRole": false,
      "isApproverRole": false,
      "roleDescription":"test description",
      "isLocationRestricted": true,
      "resourceActionPairs": [
        {
          "resourceName": "PLANOGRAM",
          "actions": [
            "VIEW"
          ]
        },
        {
          "resourceName": "DEVICE",
          "actions": [
            "VIEW"
          ]
        }
      ]
    }
  ],
  "name": "GetRole",
  "code": null,
  "message": "Get Role Successfully"
}
```

Get Role

    /role/<roleId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "roleId": 1,
    "roleName": "CUSTOMER_ADMIN",
    "isCustomRole": false,
    "isRoleUsedInApproverLevelMapping": false,  // this key will be present only on custom roles and not on system roles. If false then it means that the custom role is not used in any approver level mapping. If true then it means it is used in approver level mapping
    "roleDescription":"test description",
    "isApproverRole": false,
    "isLocationRestricted": true,
    "resourceActionPairs": [
      {
        "resourceName": "PLANOGRAM",
        "actions": [
          "VIEW",
          "ADD",
          "EDIT",
          "DELETE"
        ]
      },
      {
        "resourceName": "DEVICE",
        "actions": [
          "VIEW",
          "ADD",
          "EDIT",
          "DELETE"
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

## List of Resources

    /resources [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

    NA

RESPONSE - code - 200

```json
{
  "resources": [
    "DEVICE",
    "PLANOGRAM",
    "LAYOUT",
    "TEMPLATE",
    "CUSTOMER",
    "REPORTS",
    "LOCATION",
    "USER",
    "DOWNLOAD",
    "CUSTOMER_SETUP",
    "APP_CONSTANTS"
  ]
}
```

## List of Actions

    /actions [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    "VIEW",
    "ADD",
    "EDIT",
    "DELETE"
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## List of possible Resource and Actions combinations

    /resourceActions [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "groupName": "CMS",
      "resourceName": "CONTENT",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": true,
        "DELETE": true
      }
    },
    {
      "resourceName": "DEVICE",
      "groupName": "Device",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": false,
        "DELETE": true
      }
    },
    {
      "resourceName": "DOWNLOAD",
      "groupName": "Device",
      "actions": {
        "VIEW": true,
        "ADD": false,
        "EDIT": false,
        "DELETE": false
      }
    },
    {
      "resourceName": "LAYOUT",
      "groupName": "CMS",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": true,
        "DELETE": true
      }
    },
    {
      "resourceName": "LOCATION",
      "groupName" : "Admin",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": true,
        "DELETE": true
      }
    },
    {
      "resourceName": "PLANOGRAM",
      "groupName": "CMS",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": true,
        "DELETE": true
      }
    },
    {
      "resourceName": "REPORTS",
      "groupName": "Others",
      "actions": {
        "VIEW": true,
        "ADD": false,
        "EDIT": false,
        "DELETE": true
      }
    },
    {
      "groupName": "CMS",
      "resourceName": "TEMPLATE",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": true,
        "DELETE": true
      }
    },
    {
      "resourceName": "USER",
      "groupName" : "Admin",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": true,
        "DELETE": true
      }
    }
    {
      "resourceName": "LAYOUT_GROUP",
      "groupName": "CMS",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": true,
        "DELETE": true
      }
    },
    {
      "resourceName": "DEVICE_GROUP",
      "groupName": "Device",
      "actions": {
        "VIEW": true,
        "ADD": true,
        "EDIT": false,
        "DELETE": true
      }
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## Location Hierarchy

Here location can add, edit, delete and list depending upon customer requirement.

Eg.

- India [EditTitle, ChildAdd, Delete]
    - North
        - Noida
        - Gurugram
    - South [EditTitle, ChildAdd, Delete]
        - Bangalore
        - Hyderabad
    - East
        - Kolkata [EditTitle, ChildAdd, Delete]
        - Guwahati
    - West
        - Mumbai
        - Pune

#### Save Location Hierarchy. (Insert and Update)

By this API multiple location can create or update inside server side.
If location module don't have id, then create new, else update that location.

    /location-hierarchy  [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer <_token_>

BODY Example: first hit for creating locations

```json
{
  "hierarchyLevelCount": 3,
  "hierarchyName": "Cool hierarchy",
  "hierarchyLevels": [
    {
      "order": 1,
      "title": "Country"
    },
    {
      "order": 2,
      "title": "State"
    },
    {
      "order": 3,
      "title": "City"
    }
  ],
  "hierarchy": {
    "locationId": null,
    "locationName": "Delhi NCR",
    "childNode": [
      {
        "locationId": null,
        "locationName": "Delhi",
        "childNode": [
          {
            "locationId": null,
            "locationName": "Kondli",
            "childNode": null
          }
        ]
      },
      {
        "locationId": null,
        "locationName": "Noida",
        "childNode": [
          {
            "locationId": null,
            "locationName": "Sector 18",
            "childNode": null
          }
        ]
      }
    ]
  }
}
```

RESPONSE - code - 200

```json
{
  "result": {LocationHierarchyModel},
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 400

```json
{
  ValidationError;
}
```

BODY Example 1: subsequent hits for updating location hierarchy

```json
{
  "hierarchyLevelCount": 3,
  "hierarchyName": "Cool heirarchy",
  "hierarchyLevels": [
    {
      "order": 1,
      "title": "Country"
    },
    {
      "order": 2,
      "title": "State"
    },
    {
      "order": 3,
      "title": "City"
    }
  ],
  "hierarchy": {
    "locationId": 12,
    "locationName": "Delhi NCR",
    "childNode": [
      {
        "locationId": 123,
        "locationName": "Delhi",
        "childNode": [
          {
            "locationId": 145,
            "locationName": "Kondli",
            "childNode": [
              {
                "locationId": null,                // Note: this is a new child node being added
                "locationName": "New Kondli",
                "childNode": null
              }
            ]
          }
        ]
      },
      {
        "locationId": 18,
        "locationName": "Noida",
        "childNode": [
          {
            "locationId": 166,
            "locationName": "Sector 18",
            "childNode": null
          }
        ]
      }
    ]
  }
}
```

BODY Example 2: subsequent hits for updating location hierarchy

```json
{
  "hierarchyLevelCount": 3,
  "hierarchyName": "Cool heirarchy",
  "hierarchyLevels": [
    {
      "order": 1,
      "title": "Country"
    },
    {
      "order": 2,
      "title": "State"
    },
    {
      "order": 3,
      "title": "City"
    }
  ],
  "hierarchy": {
    "locationId": 12,
    "locationName": "Delhi NCR",
    "childNode": [
      {
        "locationId": 123,
        "locationName": "Delhi",
        "childNode": []
      },
      {
        "locationId": 18,
        "locationName": "Noida",
        "childNode": [
          {
            "locationId": 166,
            "locationName": "Sector 18",
            "childNode": null
          },
          {
            "locationId": 145,                     // This was under locationId 123 Delhi now the node has been moved from there to here
            "locationName": "Kondli",
            "childNode": []
          }
        ]
      }
    ]
  }
}
```

### Get Hierarchy per customer

    /location-hierarchy  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "hierarchyId": 23,
    "hierarchyLevelCount": 3,
    "hierarchyName": "Cool heirarchy",
    "hierarchyLevels": [
      {
        "order": 1,
        "title": "Country"
      },
      {
        "order": 2,
        "title": "State"
      },
      {
        "order": 3,
        "title": "City"
      }
    ],
    "hierarchy": {
      "locationId": 3,
      "locationName": "Delhi NCR",
      "childNode": [
        {
          "locationId": 15,
          "locationName": "Delhi",
          "childNode": [
            {
              "locationId": 19,
              "locationName": "Delhi",
              "childNode": null
            }
          ]
        },
        {
          "locationId": 124,
          "locationName": "Noida",
          "childNode": [
            {
              "locationId": 45,
              "locationName": "Sector 18",
              "childNode": null
            }
          ]
        }
      ]
    }
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get LocationHierarchy list

    /location-hierarchy/predefined-list  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "hierarchyLevelCount": 3,
      "hierarchyName": "India",
      "hierarchyLevels": [
        {
          "order": 1,
          "title": "Country"
        },
        {
          "order": 2,
          "title": "State"
        },
        {
          "order": 3,
          "title": "City"
        }
      ],
      "hierarchy": {
        "locationName": "India",
        "childNode": [
          {
            "locationName": "Andaman and Nicobar Islands"
          },
          {
            "locationName": "Andhra Pradesh"
          },
          {
            "locationName": "Arunachal Pradesh"
          },
          {
            "locationName": "Assam"
          },
          {
            "locationName": "Bihar"
          },
          {
            "locationName": "Chandigarh"
          },
          {
            "locationName": "Chhattisgarh"
          },
          {
            "locationName": "Dadra and Nagar Haveli"
          },
          {
            "locationName": "Daman and Diu"
          },
          {
            "locationName": "Delhi"
          },
          {
            "locationName": "Goa"
          },
          {
            "locationName": "Gujarat"
          },
          {
            "locationName": "Haryana"
          },
          {
            "locationName": "Himachal Pradesh"
          },
          {
            "locationName": "Jammu and Kashmir"
          },
          {
            "locationName": "Jharkhand"
          },
          {
            "locationName": "Karnataka"
          },
          {
            "locationName": "Kerala"
          },
          {
            "locationName": "Lakshadweep"
          },
          {
            "locationName": "Madhya Pradesh",
            "childNode": [
              {
                "locationName": "Bhopal"
              },
              {
                "locationName": "Gwalior"
              },
              {
                "locationName": "Jabalpur"
              },
              {
                "locationName": "Indore"
              }
            ]
          },
          {
            "locationName": "Maharashtra",
            "childNode": [
              {
                "locationName": "Mumbai"
              },
              {
                "locationName": "Pune"
              },
              {
                "locationName": "Nagpur"
              }
            ]
          },
          {
            "locationName": "Manipur"
          },
          {
            "locationName": "Meghalaya"
          },
          {
            "locationName": "Mizoram"
          },
          {
            "locationName": "Nagaland"
          },
          {
            "locationName": "Orissa"
          },
          {
            "locationName": "Pondicherry"
          },
          {
            "locationName": "Punjab",
            "childNode": [
              {
                "locationName": "Ludhiana"
              },
              {
                "locationName": "Ambala"
              },
              {
                "locationName": "Patiala"
              }
            ]
          },
          {
            "locationName": "Rajasthan"
          },
          {
            "locationName": "Sikkim"
          },
          {
            "locationName": "Tamil Nadu",
            "childNode": [
              {
                "locationName": "Chennai"
              },
              {
                "locationName": "Coimbatore"
              },
              {
                "locationName": "Salem"
              },
              {
                "locationName": "Madurai"
              }
            ]
          },
          {
            "locationName": "Telangana"
          },
          {
            "locationName": "Tripura"
          },
          {
            "locationName": "Uttaranchal"
          },
          {
            "locationName": "Uttar Pradesh"
          },
          {
            "locationName": "West Bengal"
          }
        ]
      }
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Get list of locations under a location

    /location-hierarchy/<locationId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "locationId": 3,
    "locationName": "Delhi NCR",
    "childNode": [
      {
        "locationId": 15,
        "locationName": "Delhi",
        "childNode": [
          {
            "locationId": 19,
            "locationName": "Delhi",
            "childNode": null
          }
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get list of locations where location name is like (to be used in typeahead)

    /location-hierarchy?q={"name":{"lk":"Bang"}}  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    [
          { "locationId": 3,
        "locationName": "Bangalore",
        "childNode" null },
          { "locationId": 5,
        "locationName": "Bangladesh",
        "childNode" null },
          { "locationId": 13,
         "locationName": "Bangkok",
         "childNode" null },
     ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

Not Applicable for this model

## Approval workflow & Approval Levels _(for Customer only)_

Minimum 4 type of **Role** will generate on each time of customer creted.

- CustomerAdmin
- Approver
- Maker
- Guest (User)

Customer can add multiple level for Approver.

Like Approver-L1, Approver-L2, Approver-L3.

So there is a config for Customer `numberOfApproverLevel` where he can add level morethan 1, and upto 4 . _(Default: 1)_

In this way there will be add other roles according to it.

eg. If _numberOfApproverLevel = 3_ then roles will be.

Roles

- CustomerAdmin
- ApproverLevel-1
- ApproverLevel-2
- ApproverLevel-3
- Maker
- Guest (User)

Each Planogram / Layout will pass through below steps/process before publish.

1. Create the Planogram / Layout for future use _(By Maker)_
2. Submit/ProcessForNextStep the created Planogram / Layout, so that it can be approve by other user _(By Maker)_
3. Authorise user will access the Submited Planogram / Layout, and review its design and content. _(By Approver)_
4. Review the design and content of Planogram / Layout by Authorise user. _(By Approver)_
5. Submit/ProcessForNextStep/Publish the Planogram / Layout, so that it will publish or go for approval of next user _(
   By Approver)_

## Device Config

Everything about your Device Config APIs.

Details : **Panasonic Digital Signage - Config APIs** file in this
location https://docs.google.com/document/d/12Wnd81sB37X4Wmpl5s7jfQqdBwTuXjpfxv3OJ20B0U4/edit

Get device config

    /deviceConfig [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}     // send deviceToken for devices and user token for users

QUERY

    deviceIds=12,4345,545           (required) // comma separated deviceIds

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "deviceId": 12,
      "config": {
        "logUploadStartTime": "02:00:00",
        "planogramSyncStartTime": "02:30:00",
        "planogramSyncIntervalInMinutes": 30,
        "screenCaptureIntervalTime": 30,
        "errorUpdateIntervalTime": 1440,
        "displayLogIntervalTime": 1440,
        "panelOnTime": "09:00:00",
        "panelOffTime": "18:00:00",
        "businessOnTime": "09:00:00",
        "businessOffTime": "18:00:00",
        "logUpdateIntervalTime": 35,
        "touchScreenWebViewUrl": "http://www.google.com",
        "touchScreenWebViewNoActionTimeoutInSeconds": 30,
        "touchScreenWebViewUrlSchema": "http",
        "touchScreenWebViewUrlPath": "www.google.com",
        "screenshotUploadTime": "03:00:00",
        "enableProofOfPlaySnapshots": true, // or false (default value is false)
        "twitterSDKClientKey": "ewiofj34f454hg034g4305hgh3409g5h349g5h34g59h34509h3409g534958",  // to be sent to deivces only and not users
        "twitterSDKConsumerKey": "2r34r409tu3409gu39j340jg0349jg", // to be sent to deivces only and not users
        "contentPlaybackSyncInterval": 15, // in seconds
        "facebookKeyUpdateTimestamp": 392847329472398298,
        "rabbitMqHost": "my.rabbit.mq.com",
        "alwaysUseRabbitMqForOnPremise": false, // in case of on premise server, if this value is always set to false then use Firebase/WNS for push notifications. If set to true then don't use Firebase & WNS
        "weekOffs": [
          "MON",
          "WED"
        ],
        "baseServerUrlForDevice": "http://abc.com",
        "customerId": 12,
        "customerCode": "UAZ35",
        "uploadDemographicDataIntervalInMins": 15,
        "showUploadDemographicDataInterval": true, // or false
        "stopAutoLaunchingApp": true,
        "durationOfAppUpgradeMessageInSec": 60,  // in seconds
        "zoomPercentForWebview": 67 // int value between 0 to 100, 0 means no zoom
      }
    }
  ],
  "pagination": null,
  "name": null,
  "code": 200,
  "message": "config details 1/1 fetch successfully."
}
```

#### Save / update config

    /deviceSetting [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}     // for user

QUERY

    N/A

BODY

```json
{
  "deviceIds": [
    1,
    34,
    212
  ],
  "config": {
    "logUploadStartTime": "13:30:00",
    "logUpdateIntervalTime": 30,
    "planogramSyncStartTime": "13:30:00",
    "planogramSyncIntervalInMinutes": 100,
    "panelOnTime": "09:00:00",
    "panelOffTime": "21:30:00",
    "businessOnTime": "06:30:00",
    "businessOffTime": "23:30:00",
    "screenCaptureIntervalTime": 30,
    "errorUpdateIntervalTime": 40,
    "displayLogIntervalTime": 50,
    "touchScreenWebViewUrl": "http://www.google.com",
    "touchScreenWebViewNoActionTimeoutInSeconds": 30,
    "enableProofOfPlaySnapshots": true, // or false (default value is false)
    "frequencyOfPanelStatusUpdate": 15, // in seconds
    "frequencyOfSnapshotsUpdate": 15, //  in seconds
    "frequencyOfDataCollectionUpdate": 20, // in seconds
    "contentPlaybackSyncInterval": 15, // in seconds
    "weekOffs": [
      "MON",
      "WED"
    ],
    "uploadDemographicDataIntervalInMins": 15, // in mins
    "stopAutoLaunchingApp": true,
    "durationOfAppUpgradeMessageInSec": 60  // in seconds
    "zoomPercentForWebview": 67 // int value between 0 to 100, 0 means no zoom
  }
}
```

RESPONSE - code - 200

```json
{
  "result": "SuccessfullySaved",
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 200

```json
{
  "result": [
    {
      "field": "planogramSyncStartTime",
      "message": "Invalid time format"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Save / update config

    /deviceSetting/<deviceId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}     // for user

QUERY

    N/A

BODY

```json
{
  "logUploadStartTime": "13:30:00",  // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "logUpdateIntervalTime": 30, // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "planogramSyncStartTime": "13:30:00", // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "planogramSyncIntervalInMinutes": 100, // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "panelOnTime": "09:00:00", // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "panelOffTime": "21:30:00", // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "businessOnTime": "06:30:00", // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "businessOffTime": "23:30:00", // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "screenCaptureIntervalTime": 30, // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "errorUpdateIntervalTime": 40, // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "displayLogIntervalTime": 50, // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "touchScreenWebViewUrl": "http://www.google.com", // send null or do not send if you do not want to update the value. Send empty string to delete value on server.
  "touchScreenWebViewNoActionTimeoutInSeconds": 30,  // send null or do not send if you do not want to update the value. Send -1 to delete value on server.
  "enableProofOfPlaySnapshots": true, // or false (default value is false)  // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "frequencyOfPanelStatusUpdate": 15, // in seconds // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "frequencyOfSnapshotsUpdate": 15, //  in seconds // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "frequencyOfDataCollectionUpdate": 20, // in seconds // send null or do not send if you do not want to update the value. Cannot be deleted as some value is always required.
  "weekOffs": [
    "MON",
    "WED"
  ],
  "stopAutoLaunchingApp": true,
  "durationOfAppUpgradeMessageInSec": 60 // in seconds
  "zoomPercentForWebview": 67 // int value between 0 to 100, 0 means no zoom
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "logUploadStartTime": "13:30:00",
    "logUpdateIntervalTime": 30,
    "planogramSyncStartTime": "13:30:00",
    "planogramSyncIntervalInMinutes": 100,
    "panelOnTime": "09:00:00",
    "panelOffTime": "21:30:00",
    "businessOnTime": "06:30:00",
    "businessOffTime": "23:30:00",
    "screenCaptureIntervalTime": 30,
    "errorUpdateIntervalTime": 40,
    "displayLogIntervalTime": 50,
    "touchScreenWebViewUrl": "http://www.google.com",
    "touchScreenWebViewNoActionTimeoutInSeconds": 30,
    "enableProofOfPlaySnapshots": true, // or false (default value is false)
    "frequencyOfPanelStatusUpdate": 15, // in seconds
    "frequencyOfSnapshotsUpdate": 15, //  in seconds
    "frequencyOfDataCollectionUpdate": 20, // in seconds
    "weekOffs": [
      "MON",
      "WED"
    ],
    "stopAutoLaunchingApp": true,
    "durationOfAppUpgradeMessageInSec": 60 // in seconds
    "zoomPercentForWebview": 67 // int value between 0 to 100, 0 means no zoom
  },
  "name": null,
  "code": null,
  "message": "Device config Id 9  updated successfully for device 11."
}
```

#### Save global config

---

    /globalDeviceConfig [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "panelOnTime": "00:00:00",
  "panelOffTime": "05:00:00",
  "businessOnTime": "00:00:00",
  "businessOffTime": "05:00:00",
  "screenCaptureIntervalTime": "30",
  "errorUpdateIntervalTime": "40",
  "displayLogIntervalTime": "50",
  "logUpdateIntervalTime": "30",
  "planogramSyncIntervalInMinutes":30,
  "touchScreenWebViewUrl": "http://www.google.com",
  "touchScreenWebViewNoActionTimeoutInSeconds": 30,
  "enableProofOfPlaySnapshots": true, // or false (default value is false)
  "frequencyOfPanelStatusUpdate": 15, // in seconds
  "frequencyOfSnapshotsUpdate": 15, //  in seconds
  "frequencyOfDataCollectionUpdate": 20, // in seconds
  "contentPlaybackSyncInterval": 15, // in seconds
  "weekOffs": [
    "MON",
    "WED"
  ],
  "durationOfAppUpgradeMessageInSec": 60  // in seconds
  "zoomPercentForWebview": 67 // int value between 0 to 100, 0 means no zoom
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "deviceId": null,
    "config": {
      "logUploadStartTime": null,
      "planogramSyncStartTime": null,
      "planogramSyncIntervalInMinutes": 30,
      "screenCaptureIntervalTime": 30,
      "errorUpdateIntervalTime": 40,
      "displayLogIntervalTime": 50,
      "panelOnTime": "00:00:00",
      "panelOffTime": "05:00:00",
      "businessOnTime": "00:00:00",
      "businessOffTime": "05:00:00",
      "logUpdateIntervalTime": 30,
      "touchScreenWebViewUrl": "http://www.google.com",
      "touchScreenWebViewNoActionTimeoutInSeconds": 30,
      "enableProofOfPlaySnapshots": true,
      "frequencyOfPanelStatusUpdate": 15, // in seconds
      "frequencyOfSnapshotsUpdate": 15, //  in seconds
      "frequencyOfDataCollectionUpdate": 20, // in seconds
      "contentPlaybackSyncInterval": 15, // in seconds
      "weekOffs": [
        "MON",
        "WED"
      ],
      "stopAutoLaunchingApp": true,
      "durationOfAppUpgradeMessageInSec": 60 // in seconds
      "zoomPercentForWebview": 67 // int value between 0 to 100, 0 means no zoom
    }
  },
  "pagination": null,
  "name": null,
  "code": null,
  "message": null
}
```

#### Get global device config

---

    /globalDeviceConfig [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "deviceId": null,
    "config": {
      "logUploadStartTime": null,
      "planogramSyncStartTime": null,
      "planogramSyncIntervalInMinutes": 30,
      "screenCaptureIntervalTime": 30,
      "errorUpdateIntervalTime": 40,
      "displayLogIntervalTime": 50,
      "panelOnTime": "00:00:00",
      "panelOffTime": "05:00:00",
      "businessOnTime": "00:00:00",
      "businessOffTime": "05:00:00",
      "logUpdateIntervalTime": 30,
      "touchScreenWebViewUrl": "http://www.google.com",
      "touchScreenWebViewNoActionTimeoutInSeconds": 30,
      "contentPlaybackSyncInterval": 15, // in seconds
      "weekOffs": [
        "MON",
        "WED"
      ],
      "durationOfAppUpgradeMessageInSec": 60 // in seconds
      "zoomPercentForWebview": 67 // int value between 0 to 100, 0 means no zoom
    }
  },
  "pagination": null,
  "name": null,
  "code": null,
  "message": null
}
```

## Device Group

Add a Device Group:

    /deviceGroup [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "deviceGroupName": "lobby"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "deviceGroupId": 123,
    "deviceGroupName": "lobby"
  },
  "name": null,
  "code": null,
  "message": null
}
```

Delete a Device Group:

    /deviceGroup [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "deviceGroupId": 123
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyDeleted;
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "DeviceGroupNotFound",
  "code": 1,
  "message": "Device Group not found"
}
```

Update a Device Group:

    /deviceGroup [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "deviceGroupId": 123,
  "deviceGroupName": "lobby"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "DeviceGroupNotFound",
  "code": 1,
  "message": "Device Group not found"
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyUpdated;
}
```

Get device groups

    /deviceGroup [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "result": [
    {
      "deviceGroupId": 123,
      "deviceGroupName": "lobby"
    },
    {
      "deviceGroupId": 124,
      "deviceGroupName": "cafeteria"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## Content

Get content types

    /contentTypes  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "contentTypes": [
      {
        "contentType": "VIDEO",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "AUDIO",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "IMAGE",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "PDF",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "DOC",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "PPT",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "TEXT",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "HTML",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "RSS",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "URL",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "TWITTER",
        "isMicroblogContentType": true,
        "isReadyForUse": true
      },
      {
        "contentType": "FACEBOOK",
        "isMicroblogContentType": true,
        "isReadyForUse": true
      },
      {
        "contentType": "STREAM_URL",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      },
      {
        "contentType": "APPOINTMENT",
        "isMicroblogContentType": false,
        "isReadyForUse": true
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

## Get widgets types

    /widgetTypes [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "widgetType": [
      "CLOCK",
      "WEATHER",
      "CALENDAR",
      "ADVERTISEMENT",
      "HDMI-IN"
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

## Add Content

    /content  [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY (for content json)

```json
{
  "contentType": "VIDEO",
  "defaultDurationInSeconds": "600",
  "access": "PUBLIC|PRIVATE",
  "displayMode": "NORMAL|STRETCH-TO-FIT",
  "name": "My video",
  "contentDescription": "Awesome video",
  "mediaDimension": "1920x1080",   // not for PUT and POST. Only available in GET
  "tags": [
    {
      "contentTag": "hello" // if a tag already exists then map it to this content otherwise create the tag and map it to this content
    },
    {
      "contentTag": "test"
    }
  ]
}
```

For a file upload use url

    /content/file [POST]

Body should be multi-part and the file should send using key `file`.

Note: When new content is added just add the content version as 1 in the `contentVersion` column

MULTI-PART BODY (Example image)

key = `file` for actual file

key = `content` for the JSON payloar

```json
{
  "contentType": "IMAGE",
  "defaultDurationInSeconds": "600",
  "access": "PUBLIC|PRIVATE",
  "displayMode": "NORMAL|STRETCH-TO-FIT",
  "name": "Cool image",
  "contentDescription": "Awesome image",
  "tags": [
    {
      "contentTag": "hello"    // if a tag already exists then map it to this content otherwise create the tag and map it to this content
    },
    {
      "contentTag": "test"
    }
  ]
}
```

Followed by the actual image file as multi-part upload

Similar multi-part requests will be used for ppt, doc, pdf, audio

BODY (Example: RSS)

```json
{
  "contentType": "RSS",
  "autoScrollDurationInSeconds": 10,
  "numberOfItems": 10,
  "defaultDurationInSeconds": "600",
  "access": "PUBLIC|PRIVATE",
  "name": "Cool rss title",
  "thumbnailUrl": "http://thumbnail.com/abc.jpg",
  "contentDescription": "Awesome description",
  "rssUrl": "http://www.thehindubusinessline.com/?service=rss",
  "tags": [
    {
      "contentTag": "hello"
    },
    {
      "contentTag": "test" // this is a new tag therefore it does not have Id
    }
  ]
}
```

BODY (Example: twitter)

```json
{
  "contentType": "TWITTER",
  "autoScrollDurationInSeconds": 10,
  "numberOfItems": 10,
  "defaultDurationInSeconds": "600",
  "access": "PUBLIC|PRIVATE",
  "thumbnailUrl": "http://thumbnail.com/abc.jpg",
  "name": "twitter title",
  "contentDescription": "Awesome description",
  "twitterHandle": "@google",  // https://www.twitter.com/@google/
  "tags": [
    {
      "contentTag": "hello"
    },
    {
      "contentTag": "test" // this is a new tag therefore it does not have Id
    }
  ]
}
```

BODY (Example: facebook)

```json
{
  "contentType": "FACEBOOK",
  "defaultDurationInSeconds": "600",
  "thumbnailUrl": "http://thumbnail.com/abc.jpg",
  "autoScrollDurationInSeconds": 10,
  "numberOfItems": 10,
  "access": "PUBLIC|PRIVATE",
  "name": "facebook title",
  "contentDescription": "Awesome description",
  "facebookPageId": "Google", // https://www.facebook.com/Google/
  "tags": [
    {
      "contentTag": "hello"
    },
    {
      "contentTag": "test"
    }
  ]
}
```

BODY (Example: TEXT)

```json
{
  "contentType": "TEXT",
  "defaultDurationInSeconds": "600",
  "thumbnailUrl": "http://thumbnail.com/abc.jpg",
  "access": "PUBLIC|PRIVATE",
  "name": "Text title",
  "marqueeDirection": "up", // possible values : up, left, right, down and noani
  "backgroundColor": "#FF0000",
  "isBackgroundColor": true, // or false
  "marqueeScrollAmount": 2, // integer
  "marqueeBehavior": "alternate", //  possible values null or alternate
  "marqueeAlignment": "CENTER", // possible values : CENTER, TOP, LEFT
  "contentDescription": "Awesome description",
  "html": "<html>...</html>",
  "htmlForMobile": "<html>...</html>",
  "marquee": "<marquee direction="up">",   // not required in PUT and POST. This will be there in GET only for devices
  "tags": [
    {
      "contentTag": "hello"
    },
    {
      "contentTag": "test"
    }
  ]
}
```

BODY (Example: HTML)

```json
{
  "contentType": "HTML",
  "defaultDurationInSeconds": "600",
  "access": "PUBLIC|PRIVATE",
  "thumbnailUrl": "http://thumbnail.com/abc.jpg",
  "name": "Text title",
  "contentDescription": "Awesome description",
  "html": "<html>...</html>",
  "htmlForMobile": "<html>...</html>",
  "marqueeDirection": "up", // possible values : up, left, right, down and noani
  "marqueeScrollAmount": 2, // integer
  "marquee": "<marquee direction="up">",  // not required in PUT and POST. This will be there in GET only for devices
  "tags": [
    {
      "contentTag": "hello"
    },
    {
      "contentTag": "test" // this is a new tag therefore it does not have Id
    }
  ]
}
```

BODY (Example: URL)

```json
{
  "contentType": "URL",
  "defaultDurationInSeconds": "600",
  "access": "PUBLIC|PRIVATE",
  "thumbnailUrl": "http://thumbnail.com/abc.jpg",
  "name": "Text title",
  "contentDescription": "Awesome description",
  "url": "http://www.mycoolwebsite.com",
  "zoomPercentForWebview": 67, // int value between 0 to 100, 0 means no zoom
  "tags": [
    {
      "contentTag": "hello"
    },
    {
      "contentTag": "test" // this is a new tag therefore it does not have Id
    }
  ]
}
```

BODY (Example: STREAM_URL)

```json
{
  "contentType": "STREAM_URL",
  "defaultDurationInSeconds": "600",
  "access": "PUBLIC|PRIVATE",
  "name": "My cool streaming url",
  "contentDescription": "Awesome description",
  "streamUrl": "http://www.mycoolwebsite.com/video/master.m3u8",
  "tags": [
    {
      "contentTag": "hello"
    },
    {
      "contentTag": "test" // this is a new tag therefore it does not have Id
    }
  ]
}
```

BODY (Example: APPOINTMENT)

```json
{
  "contentType": "APPOINTMENT",
  "defaultDurationInSeconds": "600",
  "access": "PUBLIC|PRIVATE",
  "thumbnailUrl": "http://thumbnail.com/abc.jpg",
  "name": "Text title",
  "contentDescription": "Awesome description",
  "appointment": {
    "appointmentId": 12,
    "background": "BACKGROUND_IMAGE", // possible values : BACKGROUND_IMAGE or BACKGROUND_COLOR
    "bgImageContentId": 133,
    "bgColour": "#FF0000",  // hex color code
    "appointmentHeader": "Welcome to MG Motor service center",
    "alignmentEnum": "CENTRE", // possible values : CENTRE, RIGHT, LEFT
    "headerFontId": 1,
    "headerFontSizeInPixels": 12,
    "headerFontColor": "#FF0000",  // hex color code
    "headerBgColor": "#0000FF",  // hex color code
    "tableHeaderFontId": 16,
    "tableHeaderFontSizeInPixels": 56,
    "tableHeaderFontColor": "#FF0000",  // hex color code
    "tableHeaderBgColor": "#FF0000",  // hex color code
    "tableRowFontId": 12,
    "tableRowFontSizeInPixels": 64,
    "tableRowFontColor": "#FF0000",  // hex color code
    "tableRowBgColor": "#FF0000",  // hex color code
    "appointmentColumns": [
      {
        "appointmentColumnId": 45,
        "columnJsonKey": "myKey",
        "columnName": "My Key",
        "columnType": "TEXT", // possible values : IMAGE, TEXT
        "imageSource": null, // in case of columnType TEXT imageSource should be null or should not be sent
        "columnWidthInPercentage": 50
        "alignmentEnum": "CENTRE", // possible values : CENTRE, RIGHT, LEFT
        "dynamicImageKeyValueMappingId": null // in case of columnType TEXT dynamicImageKeyValueMappingId should be null or should not be sent
      },
      {
        "appointmentColumnId": 46,
        "columnJsonKey": null, // in case of column type IMAGE columnJsonKey should be null or should not be sent
        "columnName": "Your Key",
        "columnType": "IMAGE", // possible values : IMAGE, TEXT
        "imageSource": "KEY_VALUES", possible values : KEY_VALUES or JSON
        "columnWidthInPercentage": 50
        "alignmentEnum": "CENTRE", // possible values : CENTRE, RIGHT, LEFT
        "dynamicImageKeyValueMappingId": 15  // can be null in case of imageSource = JSON
      },
    ]
  },
  "tags": [
    {
      "contentTag": "hello"
    },
    {
      "contentTag": "test" // this is a new tag therefore it does not have Id
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

RESPONSE - code - 400

```json
{
  ValidationError;
}
```

## Update content

    /content/{contentId} [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY (Example: URL)

    Body is same as Add content. Just increment the content version by 1 in database
    And send push notification to devices.

RESPONSE - code - 200

```json
{
  SuccessfullyUpdated;
}
```

RESPONSE - code - 403

Send this error if the content creator user is not same as the user modifying the content and the content's access =
private

```json
{
  "result": null,
  "name": "Forbidden",
  "code": 1,
  "message": "You cannot modify this content."
}
```

## Update content file

    /content/file/{contentId} [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

MULTI-PART FORM BODY

Body should be multi-part and the file should send using key `file`.

## Update bulk content

    /content [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY (Example: URL)

```json
[
  {
    contentId: 220,
    name: "amit12",
    displayMode: "NORMAL",
    twitterHandle: "Test",
    rssUrl: "Test",
    url: "Test",
    facebookPageId: "Test",
    html: "Test1",
    tags: [
      {
        contentTag: "hello",
      },
      {
        contentTag: "test",
      },
    ],
  },
  {
    contentId: 629,
    name: "amit12",
    displayMode: "NORMAL",
    twitterHandle: "Test",
    rssUrl: "Test",
    url: "Test",
    facebookPageId: "Test",
    html: "Test1",
    tags: [
      {
        contentTag: "hello",
      },
      {
        contentTag: "test",
      },
    ],
  },
];
```

RESPONSE - code - 200

```json
{
  "result": {
    "success": [
      220,
      400,
      548
    ],
    "notFound": [
      629,
      48
    ],
    "badRequest": [
      {
        "id": 1561,
        "errors": [
          {
            "field": "name",
            "message": "Number of characters should be less than 50"
          },
          {
            "field": "contentDescription",
            "message": "Number of characters should be less than 255"
          }
        ]
      },
      {
        "id": 1541,
        "errors": [
          {
            "field": "name",
            "message": "Number of characters should be less than 50"
          }
        ]
      }
    ]
  },
  "pagination": null,
  "name": null,
  "code": null,
  "message": null
}
```

Get content

    /content/{contentId}/{contentVersion}  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // use user token for user and deviceToken for devices

QUERY

    N/A

RESPONSE - code - 404

```json
{
  "result": {contentModel}, // based on content-Type
  "name": null,
  "code": 1223,
  "message": "Content not found"
}
```

RESPONSE - code - 200

```json
{
  "result": {contentModel}, // based on content-Type
  "name": null,
  "code": null,
  "message": null
}
```

Example of GET video content

```json
{
  "result": {
    "contentId": 121,
    "contentVersion": 3,
    "isFileReadyForPlay": true,     // if file is not a supported type like flv then it will need to be converted to mp4 before playing. And that is done asynchronously. If the file is not converted yet then this value will be false.
    "contentType": "VIDEO",
    "defaultDurationInSeconds": "600",
    "access": "PUBLIC|PRIVATE",
    "displayMode": "NORMAL|STRETCH-TO-FIT",
    "name": "My video",
    "contentDescription": "Awesome video",
    "thumbnailUrl": "http://www.thumbnail.com/test.jpg",
    "videoUrl": "http://www.myhosting.com/videos/121",
    "fileExtension": "mp4",
    "tags": [
      {
        "contentTag": "hello"
      },
      {
        "contentTag": "test"
      }
    ],
    "createdBy": 1,
    "createdByFullName": "Hello"
  },
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 400

```json
{
  "result": {
    "contentVersion": 7 // gives you the correct content version for that content.
  },
  "name": null,
  "code": 1,
  "message": "The content version is incorrect. "
}
```

RESPONSE - code - 403

Send this error if the content creator user is not same as the user requesting the content and the content's access =
PRIVATE

```json
{
  "result": null,
  "name": "Forbidden",
  "code": 1,
  "message": "You cannot access this content."
}

```

Similary, for replace `videoUrl` with following keys in different contentTypes

1. IMAGE: `imageUrl`
2. RSS: `rssUrl`
3. TEXT: `html`
4. HTML: `html`
5. AUDIO: `audioUrl`
6. FACEBOOK: `facebookPageId`
7. TWITTER: `twitterHandle`
8. DOC: `docUrl`
9. PDF: `pdfUrl`
10. PPT: `pptUrl`
11. URL: `url`
12. STREAM_URL: `streamUrl`

Also there will be a `mediaUrl` for the benefit of angular team which will have the values of audioUrl, videoUrl,
imageUrl, rssUrl, docUrl, pdfUrl, pptUrl and url depending on the contentType

Also in `HTML` and `TEXT` contentType you will get one additional json key of `String` type named `marquee` having value
like `<marquee direction="up">`.

Example:

```json
"marquee": "<marquee direction="up">",
```

On client side teams will append this to the top of the html received as well as add a marquee end tag at the end of the
html.

For local servers: Devices using local server should use different keys named below:

1. IMAGE: `localImageUrl`
2. AUDIO: `localAudioUrl`
3. DOC: `localDocUrl`
4. PDF: `localPdfUrl`
5. PPT: `localPptUrl`
6. VIDEO: `localVideoUrl`

Also, `localMediaUrl` can be used a replacement for `mediaUrl`

RESPONSE - code - 404

```json
{
  ErrorNotFound;
}
```

Get contents

    /content [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // in case of user

QUERY

    contentType=VIDEO|AUDIO etc. (optional)
    contentName=xxx (optional)
    uploadedBy=userId (optional)
    sharingMode=PUBLIC|PRIVATE (optional)
    page=1 (optional)
    pageSize=15 (optional)

RESPONSE - code - 200

Get only those contents which user are private to the requesting user + all public content

```json
{
  "result": [
    {ContentModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Content Advance search

    /content?q={<query parameters>} [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // in case of user

QUERY

    One can use operand(s) (lt-less than, gt-greater than, eq-equal to, lte-less than or equal to, gte-greater than or equal to, lk-like:applicable for text types)
    on following fields in json format:
    content_type (VIDEO|AUDIO)
    content_name
    uploaded_by (userId)
    sharing_mode (PUBLIC|PRIVATE)
    created_date
    tag
    file_size
    duration_in_seconds
    (All above filters are optional, all filters apply using a logical AND i.e. with each extra filter dataset should reduce or remain same
    operands apart from eq, lk can be used only for date and numeric(not ids) filters)

EXAMPLE

    /content?q={"content_type":{"eq": "VIDEO"},"created_date":{"lt":1576616761, "gt":17678675656}, "duration_in_seconds":{"gte":15}, "sharing_mode":{"eq": "PUBLIC"}}

RESPONSE - code - 200

Get only those contents which user are private to the requesting user + all public content

```json
{
  "result": [
    {ContentModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

Get content tags

    /contentTags [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // in case of user send user token and in case od device send device token

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
      "contentTags": [
        {
          "contentTag": "hello"
        },
        {
          "contentTag": "test"
        },
        {
          "contentTag": "bye"
        }
      ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "BadRequest",
  "code": 1, // some unique code
  "message": "The request was incorrect"
}
```

Delete content

    /content [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // in case of user

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyMultipleDeleted;
}
```

RESPONSE - code - 200

```json
{
  ErrorInMultipleDelete;
}
```

## Bandwidth & Storage:

Get Bandwidth per device:

    /bandwidthUsage/{customerId}?deviceId={deviceId}?startDate=1517824357000&endDate=1517824357000 [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    deviceId=xxx (optional) // if not sent then the bandwidthUsage will be sent for the entire customer
    startDate=1517824357000 // unix epoc in millis
    endDate=1517824357000   // unix epoc in millis

Note: Response will include both start and end dates data also.

RESPONSE - code - 200

```json
{
  "result": [
    {
      "dateTime": 1517824357000,
      "bandwidthUsage": "500",
      "bandwidthUnits": "GB|MB"
    },
    {
      "dateTime": 1517824357000,
      "bandwidthUsage": "500",
      "bandwidthUnits": "GB|MB"
    },
    {
      "dateTime": 1517824357000,
      "bandwidthUsage": "500",
      "bandwidthUnits": "GB|MB"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": null,
  "code": 1,
  "message": "Customer not found"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": null,
  "code": 2,
  "message": "Device not found"
}
```

Get Storage per customer:

    /storageUsage/{customerId}?startDate=1517824357000&endDate=1517824357000 [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    startDate=1517824357000   // unix epoc in millis
    endDate=1517824357000     // unix epoc in millis

Note: Response will include both start and end dates data also.

RESPONSE - code - 200

```json
{
  "result": [
    {
      "dateTime": 1517824357000,
      "storageUsage": "500",
      "storageUnits": "GB|MB"
    },
    {
      "dateTime": 1517824357000,
      "storageUsage": "500",
      "storageUnits": "GB|MB"
    },
    {
      "dateTime": 1517824357000,
      "storageUsage": "500",
      "storageUnits": "GB|MB"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": null,
  "code": 1,
  "message": "Customer not found"
}
```

## Campaign(Layout) States Enum:

`DRAFT`,
`SUBMITTED`,
`APPROVED`,
`PUBLISHED`

## Templates and Layouts

### Fetch all supported aspectRatios:

---

    /aspectRatio [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "aspectRatioId": 12123,
      "aspectRatio": "16:9",
      "defaultWidthInPixel": 864,
      "defaultHeightInPixel": 486,
      "status": 1,
      "isEditable": false,
      "isDefaultAspectRatio": true,
      "actualWidthInPixel": null,
      "actualHeightInPixel": null
    },
    {
      "aspectRatioId": 4895,
      "aspectRatio": "4:3",
      "defaultWidthInPixel": 800,
      "defaultHeightInPixel": 600,
      "status": 1,
      "isEditable": false,
      "isDefaultAspectRatio": true,
      "actualWidthInPixel": null,
      "actualHeightInPixel": null
    },
    {
      "aspectRatioId": 5452,
      "aspectRatio": "9:16",
      "defaultWidthInPixel": 486,
      "defaultHeightInPixel": 864,
      "status": 1,
      "isEditable": false,
      "isDefaultAspectRatio": true,
      "actualWidthInPixel": null,
      "actualHeightInPixel": null
    },
    {
      "aspectRatioId": 7483,
      "aspectRatio": "3:4",
      "defaultWidthInPixel": 600,
      "defaultHeightInPixel": 800,
      "status": 1,
      "isEditable": false,
      "isDefaultAspectRatio": true,
      "actualWidthInPixel": null,
      "actualHeightInPixel": null
    },
    {
      "aspectRatioId": 7489,
      "aspectRatio": "89:23",
      "defaultWidthInPixel": 712,
      "defaultHeightInPixel": 184,
      "status": 1,
      "isEditable": true,
      "isDefaultAspectRatio": false,
      "actualWidthInPixel": 7120,
      "actualHeightInPixel": 1840
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Fetch aspect ratio by ID:

---

    /aspectRatio/<aspectRatioId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "aspectRatioId": 12123,
    "aspectRatio": "16:9",
    "defaultWidthInPixel": 864,
    "defaultHeightInPixel": 486,
    "status": 1,
    "isEditable": false,           // false if this aspect ratio is not one of the default ones, and in use in template, campaign or in planogram. Otherwise it will be true
    "isDefaultAspectRatio": true,  // true if this is a default aspect ratio i.e. one of the 4 default aspect ratios that come added with system
    "actualWidthInPixel": null,
    "actualHeightInPixel": null
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Delete aspect ratio by IDs

---

    /aspectRatio/{aspectRatioId} [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": null,
  "name": null,
  "code": null,
  "message": "Aspect ratio deleted successfully"
}
```

### Delete aspect ratio by IDs

---

    /aspectRatio [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "aspectRatioIds": [ 12, 44, 56 ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "success": [
      1,
      54,
      767
    ],
    "badRequest": [
      {
        "id": 1,
        "message": "Already in use. Cannot delete"
      }
    ],
    "notFound": [
      9,
      67,
      24
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Create new template

---

    /template [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "templateName": "My cool layout", // name should be unique for the given customer
  "templateDescription": "My description",
  "aspectRatioId": 12123,
  "state": "DRAFT|SUBMITTED",      // one of the values of
  "backgroundColor": "#888888", // HEX color code
  "backgroundImageContentId": 2389,
  "transparencyInPercentage": 50,
  "regions": [
    {
      "templateRegionName": "my region 1",
      "widthInPixel": 500,
      "heightInPixel": 650,
      "topLeftCoordinateXInPixel": 200,
      "topLeftCoordinateYInPixel": 300,
      "zIndex": 12
    },
    {
      "templateRegionName": "my region 2",
      "widthInPixel": 300,
      "heightInPixel": 354,
      "topLeftCoordinateXInPixel": 789,
      "topLeftCoordinateYInPixel": 989,
      "zIndex": 4
    }
  ],
  "layoutTags": [
    { "layoutTag" : "hello"},
    { "layoutTag" : "test"},
    { "layoutTag" : "world"}
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "templateId": 34
  },
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "SameZIndexError",
  "code": 1, // if regions in the layout have same z-index then send this error.
  // Please refer https://www.geeksforgeeks.org/find-two-rectangles-overlap/
  "message": "Bad Request. Two regions cannot have same z-index"
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "LayoutIdsDoesNotExist",
  "code": 2,
  "message": "LayoutIds {comma separated layoutIds} do not exist"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "ContentNotFound",
  "code": 1,
  "message": "Content ID given for background image not found"
}
```

Get template

    /template/<templateId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

Server logic:

```java
Long customerId = TenantContext.getCustomerId();
User user = "user who is calling the API".
Template template = get Template From DB by templateID and where clause for customerId and status in (1, 2);
if (tempalte == null) {
    // send HTTP status code 404
}
if (isUserPanasonicAdmin(user)) {
    if (canPanasonicAdminUserAccessCMS(user)) {
      // send template
    } else {
      // send HTTP status code 403
    }
} else if (template.state == DRAFT) {
    if (doesUserRoleHaveAddOrEditPrivilege(user)) {
        // send template
    } else {
        // send HTTP status code 403
    }
} else {
    // send template
}


boolean isUserPanasonicAdmin(User user) {

}

boolean canPanasonicAdminUserAccessCMS(User user) {

}

boolean doesUserRoleHaveAddOrEditPrivilege(User user) {

}
```

RESPONSE - code - 200

```json
{
  "result": {
    "templateId": 7573,
    "state": "DRAFT|SUBMITTED",
    "status": 1,                        // one of the values 1 (active) or 2 (inactive)
    "templateName": "My cool layout",
    "templateDescription": "My description",
    "aspectRatio": {
      "aspectRatioId": 12123,
      "aspectRatio": "16:9",
      "defaultWidthInPixel": 864,
      "defaultHeightInPixel": 486,
      "status": 1
    },
    "backgroundImageContentId": 1632,
    "createdByUserId": 15,
    "createdByFullName": "Abc Xyz",
    "backgroundColor": "#888888", // HEX color code
    "transparencyInPercentage": 50,
    "regions": [
      {
        "templateRegionId": 32723,
        "templateRegionName": "my region 1",
        "widthInPixel": 50,
        "heightInPixel": 30,
        "topLeftCoordinateXInPixel": 13,
        "topLeftCoordinateYInPixel": 18,
        "zIndex": 12
      },
      {
        "templateRegionId": 7842,
        "templateRegionName": "my region 2",
        "widthInPixel": 50,
        "heightInPixel": 30,
        "topLeftCoordinateXInPixel": 13,
        "topLeftCoordinateYInPixel": 18,
        "zIndex": 4
      }
    ],
    "layoutTags": [
      { "layoutTag" : "hello"},
      { "layoutTag" : "test"},
      { "layoutTag" : "world"}
    ]
  },
  "name": null,
  "code": 0,
  "message": null
}
```

#### Get templates (Note: don't send deleted templates)

    /template [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    aspectRatio=<aspectRatioId>

RESPONSE - code - 200

```json
{
  "result": [
    {TemplateBody}
  ],
  "name": null,
  "code": 0,
  "message": null
}
```

If the tempalteID is missing in DB or it's status is deleted (3)

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "TemplateNotFound",
  "code": 0,
  "message": "Temaplate not found"
}
```

RESPONSE - code - 403

```json
{
  "result": null,
  "name": "Forbidden",
  "code": 0,
  "message": "You do not have access to this resource"
}
```

Get layout tags

    /layoutTags [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // in case of user send user token and in case of device send device token

QUERY

    N/A

Note for server: Please send data for a given customer only, which you can take from `TenantContext`
Return only those tags which are active and inactive. Please skip deleted ones

RESPONSE - code - 200

```json
{
  "result": [
    { "layoutTag" : "hello"},
    { "layoutTag" : "test"},
    { "layoutTag" : "world"}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "BadRequest",
  "code": 1, // some unique code
  "message": "The request was incorrect"
}
```

Update template

    /template/<templateId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "templateName": "My cool layout", // name should be unique for the given customer
  "templateDescription": "My description",
  "templateId": 322342,
  "aspectRatioId": 12123,
  "state": "DRAFT|SUBMITTED",
  "status": 1,                        // one of the values 1 (active), 2 (inactive), or 3 (deleted)
  "backgroundColor": "#A9888888", // HEX color code
  "transparencyInPercentage": 50, //
  "backgroundImageContentId": 2389,
  "regions": [
    {
      "templateRegionId": 336 // this ID should be present if an existing region is modified
      "templateRegionName": "my region 1",
      "widthInPixel": 50,
      "heightInPixel": 30,
      "topLeftCoordinateXInPixel": 13,
      "topLeftCoordinateYInPixel": 18,
      "zIndex": 12
    },
    {
      // if region ID is missing that means a new region is to be added
      "templateRegionName": "my region 2",
      "widthInPixel": 50,
      "heightInPixel": 30,
      "topLeftCoordinateXInPixel": 13,
      "topLeftCoordinateYInPixel": 18,
      "zIndex": 4
    }
  ],
  "layoutTags": [
    { "layoutTag" : "hello"},
    { "layoutTag" : "test"},
    { "layoutTag" : "world"}
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "templateId": 34
  },
  "name": null,
  "code": null,
  "message": "Successfully updated template"
}
```

Search templates

    /template/search [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY (atlease one param should be sent)

    templateName=<string>           // optional
    createdBy=<string>              // optional created By user
    tag=<string>                    // optional tag (keyword). Refers to tagTitle in layoutTag table
    aspectRatio=<aspectRatioId>     // optional
    status=1|2                      // optional (1 = active, 2 = inactive)

At least one of the optional param should be provided for search

RESPONSE - code - 200

```json
{
  "result": [
    {TemplateBody}
  ],
  "name": null,
  "code": 0,
  "message": null
}
```

Delete template

    /template [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

[Common Actions for Multiple Actions](#common-actions-for-multiple-actions)

## Layout

Add layout

    /layout [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    submit-validations=false | true (optional)   // if true then performs submit level validations

BODY

```json
{
  "templateId": 1111,           // required (Using this templateId layout will be copied and also regions will be copied)
  "layoutName": "Cool Layout",                   // required
  "layoutDescription": "Layout description",        // optional
  "backgroundColor": "#888888",     // (optional) background color
  "backgroundImageContentId": 38,   // (optional) contentId of backgroundImage
  "transparencyInPercentage": 50,
  "audioStartBasedOnLayoutDurationInSeconds": 120,      // optional
  "audioEndBasedOnLayoutDurationInSeconds": 480,        // optional
  "audios": [
    {
      "contentId": 12,                          // required
      "order": 1                               // required
    },
    {
      "contentId": 16,
      "order": 2
    }
  ],
  "status": 1,                         // required
  "state": "DRAFT|SUBMITTED",          // required
  "regions": [
    {
      "templateRegionId": 7267,                // required
      "layoutRegionName": "My cool region",
      "regionTransparencyInPercentage": 15,
      "isAudioEnabled": true,
      "zIndex" : 12,
      "locationIds": [    // optinal
        1,
        3,
        67
      ],
      "globalRegionContentPlaylistContents": [         // this is a global playlist
        {
          "contentId": 323,              // required
          "order": 2,                    // required
          "durationInSeconds": 600,
          "transparencyInPercentage": 0,
          "entryAnimationId": 329,
          "exitAnimationId": 329,
          "displayMode": "NORMAL|STRETCH-TO-FIT"
        },
        {
          "contentId": 84,               // required
          "order": 1,                    // required
          "durationInSeconds": 800,
          "transparencyInPercentage": 30,
          "entryAnimationId": 329,
          "exitAnimationId": 329,
          "displayMode": "NORMAL|STRETCH-TO-FIT"
        },
        {
          "contentId": 5,               // required
          "order": 3,                   // required
          "durationInSeconds": 200,
          "transparencyInPercentage": 45,
          "entryAnimationId": 329,
          "exitAnimationId": 329,
          "displayMode": "NORMAL|STRETCH-TO-FIT"
        },
        {
          "widgetType": "CLOCK",              // required (note this is a widget type of content)
          "order": 6,                   // required
          "durationInSeconds": 200,
          "transparencyInPercentage": 45,
          "entryAnimationId": 329,
          "exitAnimationId": 329,
          "displayMode": "NORMAL|STRETCH-TO-FIT"
        }
      ]
    }
  ],
  "layoutTags": [
    { "layoutTag" : "hello"},
    { "layoutTag" : "test"},
    { "layoutTag" : "world"}
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "layoutId": 1232
  },
  "name": null,
  "code": 0,
  "message": "Successfully Saved"
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "ContentsWithSameOrder",
  "code": 1,
  "message": "Contents in region playlist cannot have same order"
}
```

RESPONSE - code - 400

```json
{
  "result": {
      "field": "",
      "message": ""
  },
  "name": "ValidationError",
  "code": 2,
  "message": "Contents in region playlist cannot have same order"
}
```

RESPONSE - code - 403

```json
{
  "result": null,
  "name": "PermissionError", // either user has not provided auth token, or the person adding / updating layout is neither having MAKER nor ADMIN role
  "code": 1,
  "message": "You do not have permission to add/update layout"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "ContentNotFound",
  "code": 1,
  "message": "Content ID given for background image not found"
}
```

RESPONSE - code - 404

```json
{
  "result": {
    "contentIds": [46, 43]  // contentIds of audio files which were not found
  },
  "name": "ContentNotFound",
  "code": 2,
  "message": "Audio ID given for audios not found"
}
```

Update layout

    /layout/<layoutId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    submit-validations=false | true (optional)   // if true then performs submit level validations

BODY

```json
{
  "layoutName": "Cool Layout",
  "layoutDescription": "Layout description",
  "backgroundColor": "#888888",
  "backgroundImageContentId": 38,
  "transparencyInPercentage": 50,
  "audioStartBasedOnLayoutDurationInSeconds": 120,
  "audioEndBasedOnLayoutDurationInSeconds": 480,
  "audios": [
    {
      "contentId": 12,
      "order": 1
    },
    {
      "contentId": 16,
      "order": 2
    }
  ],
  "status": 1,
  "state": "DRAFT|SUBMITTED",
  "regions": [
    {
      "layoutRegionId": 7267,
      "layoutRegionName": "my cool region",
      "regionTransparencyInPercentage": 15,
      "zIndex" : 12,
      "locationIds": [
        1,
        3,
        67
      ],
      "globalRegionContentPlaylistContents": [
        {
          "contentId": 323,
          "order": 2,
          "durationInSeconds": 600,
          "transparencyInPercentage": 0,
          "entryAnimationId": 329,
          "exitAnimationId": 329,
          "displayMode": "NORMAL|STRETCH-TO-FIT"
        },
        {
          "contentId": 84,
          "order": 1,
          "durationInSeconds": 800,
          "transparencyInPercentage": 30,
          "entryAnimationId": 329,
          "exitAnimationId": 329,
          "displayMode": "NORMAL|STRETCH-TO-FIT"
        },
        {
          "contentId": 5,
          "order": 3,
          "durationInSeconds": 200,
          "transparencyInPercentage": 45,
          "entryAnimationId": 329,
          "exitAnimationId": 329,
          "displayMode": "NORMAL|STRETCH-TO-FIT"
        },
        {
          "widgetType": "CLOCK",
          "order": 6,
          "durationInSeconds": 200,
          "transparencyInPercentage": 45,
          "entryAnimationId": 329,
          "exitAnimationId": 329,
          "displayMode": "NORMAL|STRETCH-TO-FIT"
        }
      ]
    }
  ],
  "layoutTags": [
    { "layoutTag" : "hello"},
    { "layoutTag" : "test"},
    { "layoutTag" : "world"}
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "layoutId": 1232
  },
  "name": null,
  "code": 0,
  "message": "Successfully Updated"
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "ContentsWithSameOrder",
  "code": 1,
  "message": "Contents in region playlist cannot have same order"
}
```

RESPONSE - code - 400

```json
{
  "result": {
      "field": "",
      "message": ""
  },
  "name": "ValidationError",
  "code": 2,
  "message": "Contents in region playlist cannot have same order"
}
```

RESPONSE - code - 401

```json
{
  "result": null,
  "name": "Unauthorized", // either user has not provided auth token, or the person adding / updating layout is neither having MAKER nor ADMIN role
  "code": 1,
  "message": "You do not have permission to add/update layout"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "ContentNotFound",
  "code": 1,
  "message": "Content ID given for background image not found"
}
```

RESPONSE - code - 404

```json
{
  "result": {
    "contentIds": [46, 43]  // contentIds of audio files which were not found
  },
  "name": "ContentNotFound",
  "code": 2,
  "message": "Audio ID given for audios not found"
}
```

Search layouts

    /layout/search [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY (atleast one optional param should be sent)

    layoutName=<string>             // optional
    createdBy=<string>              // optional created By user
    tag=<string>                    // optional tag (keyword)
    aspectRatio=<aspectRatioId>     // optional
    status=1|2                      // optional (1 = active, 2 = inactive)
    state=APPROVED,DRAFT            // optional (at least one state should be sent, more states can be sent with comma separation)

At least one of the optional param should be provided for search

### Layout Advance Search

    /layout?q={<query parameters>} [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    One can use operand(s) (lt-less than, gt-greater than, eq-equal to, lte-less than or equal to, gte-greater than or equal to, lk-like:applicable for text types)
    on following fields in json format:
    layout_name
    created_by
    layout_tag (string)
    aspect_ratio
    status  (enum : ACTIVE, INACTIVE)
    state (enum : DRAFT, SUBMITTED, APPROVED, REJECTED, PUBLISHED)
    number_of_regions
    duration_in_seconds
    has_audio (true/false)

    (All above filters are optional, all filters apply using a logical AND i.e. with each extra filter data set should reduce or remain same
    operands apart from eq, lk can be used only for date and numeric (non ids) filters)

EXAMPLE

    /layout?q={"layout_tag":{"eq":"pepsi"}, "number_of_regions":{"gt":2},"status": {"eq": "ACTIVE"}}

RESPONSE - code - 200

```json
{
  "result": [
    {LayoutModelAsSentInGetLayoutById}
  ],
  "name": null,
  "code": 0,
  "message": null
}
```

## Delete layout

    /layout [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

Common Actions for Multiple Actions

Approve layout

    /layout/approve/<layoutId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "state": "APPROVED"
}
```

RESPONSE - code - 200

```json
{
  SuccessResponseModel;
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "ResourceAlreadyApproved",
  "code": 1, // some unique code
  "message": "The resoruce is already approved"
}
```

```json
{
  "result": null,
  "name": "ThereIsNoApprovalFlowForThisResource",
  "code": 2, // some unique code
  "message": "The is no approval flow for the given resource"
}
```

RESPONSE - code - 403

```json
{
  "result": null,
  "name": "PermissionError",
  "code": 1, // some unique code
  "message": "Your user role does not have approve permission for current level of approval"
}

```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "LayoutNotFound",
  "code": 1,
  "message": "Layout not found"
}
```

Get layout

    /layout/<layoutId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "layoutId": 1232,
    "layoutName": "Cool Layout",
    "canEditLocationBasedPlaylist": true,       // or false. This indicates that the calling user can edit this layout for location based playlist
    "isPendingForApporval": true,               // true if layout is pending for approval, false otherwise
    "isUsed": true,                             // true if used in a planogram or layout string or in rule association false otherwise. If true then the campaign cannot be deleted or edited
    "isUsedInRuleAssociation": true,            // or false
    "layoutDescription": "Layout description",
    "layoutType" : "NORMAL",                    // or "ADVERTISEMENT"
    "aspectRatio": {
      "aspectRatioId": 12123,
      "aspectRatio": "16:9",
      "defaultWidthInPixel": 864,
      "defaultHeightInPixel": 486,
      "status": 1
    },
    "totalDurationOfCampaignInSeconds": 1000,   // to calculate this on server-side using the total value of region playlist and use the largest one
    "createdByUserId": 15,
    "createdByFullName": "Abc Xyz",
    "associatedWithCampaignStrings": [          // will be empty in case of layout list and search APIs
      {
        "layoutStringId": 13,
        "layoutStringName": "Cool layout String 1"
      },
      {
        "layoutStringId": 15,
        "layoutStringName": "Cool layout String 2"
      }
    ],
    "backgroundImageContentId": 878,    // optional
    "backgroundImageContentVersion": 12
    "backgroundColor": "#888888",       // optional
    "transparencyInPercentage": 50,     // optional
    "doesLayoutContainHDMIIn": true,
    "doesLayoutContainDocOrPpt": false,
    "audioStartBasedOnLayoutDurationInSeconds": 120,
    "audioEndBasedOnLayoutDurationInSeconds": 480,
    "audios": [                     // will be empty in case of layout list and search apis
      {
        "contentId": 12,
        "contentVersion": 15
        "order": 1
      },
      {
        "contentId": 16,
        "contentVersion": 3
        "order": 2
      }
    ],
    "state": "DRAFT|SUBMITTED|APPROVED|PUBLISHED",    // one of these values to be sent
    "status": 1,                 // 1 (active) or 2 (inactive)
    "canApprove": false,         // should be true if the user requesting layout is MAKER or ADMIN role
    "regions": [                 // will be empty in case of layout list and search apis
      {
        "layoutRegionId": 7267,
        "layoutRegionName": "My cool region",
        "regionTransparencyInPercentage": 15,
        "isAudioEnabled": true,
        "zIndex" : 12,
        "locations": [
          {
            "locationId": 12,
            "locationName": "Noida",
          },
          {
            "locationId": 122,
            "locationName": "Delhi",
          }
        ],
        "widthInPercentage": 50,
        "heightInPercentage": 30,
        "topLeftCoordinateXInPercentage": 13,
        "topLeftCoordinateYInPercentage": 18,
        "widthInPixel": 531,
        "heightInPixel": 260,
        "topLeftCoordinateXInPixel": 189,
        "topLeftCoordinateYInPixel": 694,
        "globalRegionContentPlaylistId": 123,
        "globalRegionContentPlaylistContents": [    // if caller is device then find if the device falls under a given locations ids and then only pass those playlist which belong to that device
          {
            "contentId": 323,
            "contentVersion": 1
            "order": 2,
            "durationInSeconds": 600,
            "transparencyInPercentage": 0,
            "entryAnimationId": 329,
            "exitAnimationId": 329,
            "displayMode": "NORMAL|STRETCH-TO-FIT"
          },
          {
            "contentId": 84,
            "contentVersion": 19
            "order": 1,
            "durationInSeconds": 800,
            "transparencyInPercentage": 30,
            "entryAnimationId": 329,
            "exitAnimationId": 329,
            "displayMode": "NORMAL|STRETCH-TO-FIT"
          },
          {
            "contentId": 5,
            "contentVersion": 17
            "order": 3,
            "durationInSeconds": 200,
            "transparencyInPercentage": 45,
            "entryAnimationId": 329,
            "exitAnimationId": 329,
            "displayMode": "NORMAL|STRETCH-TO-FIT"
          },
          {
            "widgetType": "CLOCK",
            "order": 6,
            "durationInSeconds": 200,
            "transparencyInPercentage": 45,
            "entryAnimationId": 329,
            "exitAnimationId": 329,
            "displayMode": "NORMAL|STRETCH-TO-FIT"
          }
        ]
      }
    ],
    "layoutTags": [
      { "layoutTag" : "hello"},
      { "layoutTag" : "test"},
      { "layoutTag" : "world"}
    ],
    "workFlowActivity": [               // will be empty in case of /layout/search APIs
      {
        "workFlowActivityType": "REJECT",
        "userId": 12,
        "fullName": "Pratap Patil",
        "reason": "Background color was not good. Please change to yellow.",
        "approverLevel": 2,
        "roles": [
          {
            "roleId": 1,
            "roleName": "CUSTOMER_ADMIN"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L1 Pratap Patil"
      },
      {
        "workFlowActivityType": "APPROVE",
        "userId": 18,
        "fullName": "Desh Deepak",
        "approverLevel": 1,
        "roles": [
          {
            "roleId": 5,
            "roleName": "APPROVER"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L2 Desh Deepak"
      },
      {
        "workFlowActivityType": "SUBMIT",
        "userId": 18,
        "fullName": "Hamzah Jamal",
        "approverLevel": null,
        "roles": [
          {
            "roleId": 4,
            "roleName": "MAKER"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L2 Desh Deepak"
      }
    ],
  },
  "name": null,
  "code": 0,
  "message": null
}
```

RESPONSE - code - 404

```json
{
  "result": {LayoutModel},
  "name": null,
  "code": 1223,
  "message": "Campaign not found"
}
```

## Layout String (Campaign String)

Add layout string:

    /layoutString [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "layoutStringName": "Cool layout string name",
  "aspectRatioId": 12,
  "layouts": [
    {
      "layoutId": 112,
      "numberOfLoops": 1,
      "displayOrder": 3
    },
    {
      "layoutId": 656,
      "numberOfLoops": 1,
      "displayOrder": 5
    },
    {
      "layoutId": 234,
      "numberOfLoops": 1,
      "displayOrder": 6
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "LayoutsAspectRatioMismatch",
  "code": 1,
  "message": "A campaign group cannot have layouts with different aspect ratios"
}
```

Update layout string:

    /layoutString/<layoutStringId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "layoutStringName": "Cool layout string name",
  "aspectRatioId": 12,
  "status": 1,
  "layouts": [
    {
      "layoutId": 112,
      "numberOfLoops": 1,
      "displayOrder": 3
    },
    {
      "layoutId": 656,
      "numberOfLoops": 1,
      "displayOrder": 5
    },
    {
      "layoutId": 234,
      "numberOfLoops": 1,
      "displayOrder": 6
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "LayoutsAspectRatioMismatch",
  "code": 1,
  "message": "A campaign string cannot have layouts with different aspect ratios"
}
```

Get layout string:

    /layoutString/<layoutStringId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "layoutStringId": 892,
    "layoutStringName": "Cool layout string name",
    "isPendingForApporval": true, // true if layoutString is pending for approval, false otherwise
    "isUsed": true, // true if used in a planogram, false otherwise. If true then the campaign string cannot be deleted or edited
    "createdByUserId": 15,
    "createdByFullName": "Abc Xyz",
    "aspectRatio":  {
      "aspectRatioId": 12123,
      "aspectRatio": "16:9",
      "defaultWidthInPixel": 864,
      "defaultHeightInPixel": 486,
      "status": 1
    },
    "status": 1,
    "displayDurationInSeconds": 6000, //this will be sum of the durations of all the layout's durations
    "layouts": [
      {
        "layoutId": 112,
        "layoutName": "My Layout 1"
        "displayOrder": 3,
        "status": 1,
        "numberOfLoops": 1,
        "displayDurationInSeconds": 8900,
        "totalDurationInSeconds": 8900
      },
      {
        "layoutId": 656,
        "layoutName": "My Layout 2",
        "displayOrder": 5,
        "status": 1,
        "numberOfLoops": 1,
        "displayDurationInSeconds": 8900,
        "totalDurationInSeconds": 8900
      },
      {
        "layoutId": 234,
        "layoutName": "My Layout 3",
        "displayOrder": 6,
        "status": 1,
        "numberOfLoops": 1,
        "displayDurationInSeconds": 8900,
        "totalDurationInSeconds": 8900
      }
    ],
    "workFlowActivity": [
      {
        "workFlowActivityType": "REJECT",
        "userId": 12,
        "fullName": "Pratap Patil",
        "reason": "Background color was not good. Please change to yellow.",
        "approverLevel": 2,
        "roles": [
          {
            "roleId": 1,
            "roleName": "CUSTOMER_ADMIN"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L1 Pratap Patil"
      },
      {
        "workFlowActivityType": "APPROVE",
        "userId": 18,
        "fullName": "Desh Deepak",
        "approverLevel": 1,
        "roles": [
          {
            "roleId": 5,
            "roleName": "APPROVER"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L2 Desh Deepak"
      },
      {
        "workFlowActivityType": "SUBMIT",
        "userId": 18,
        "fullName": "Hamzah Jamal",
        "approverLevel": null,
        "roles": [
          {
            "roleId": 4,
            "roleName": "MAKER"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L2 Desh Deepak"
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

Get layout strings:

    /layoutString [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    aspectRatioId=<aspectRatioId>    (optional)
    state=DRAFT,SUBMITTED            (optional)

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {LayoutStringModelAsReceivedInGetLayoutStringById}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

Delete layout strings:

    /layoutString [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

[Common Actions for Multiple Actions](#common-actions-for-multiple-actions)

## Device Management

Add device

    /device [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "clientGeneratedDeviceIdentifier": "77eaea49-a768-43e1-9446-e4ec76457c2f", // a UUID generated on client-side, should be validated in DB to be unique
  "deviceName": "Cool Device", // (required) should be unique
  "deviceGroupId": 129, // (optional) refers to foreign key of the deviceGroup table
  "locationId": 82, // (required) refers to foreign key of location table
  "deviceOs": "ANDROID|WINDOWS", // (required) enum: ANDROID or WINDOWS
  "deviceWifiMacAddress": "fc:67:36:f6:a3:7e", // optional
  "deviceEthernetMacAddress": "ac:e5:36:f6:7e:a3", // optional
  "unregisteredDeviceId": 12  // if null this is offline device POST if a value is there then device should be moved from `unregisteredDevice` table to `device` table. Add a default panel to the device.
}
```

Note:
Check the consumed licence count of a device in device. `consumedNumberOfDevices` in the `customerLicence` table.
If the `consumedNumberOfDevices` == `numberOfDevices` then send 400 error with error code 4

RESPONSE - code - 200

```json
{
  "result": {DeviceModel},
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "ClientGeneratedDeviceIdentifierAlreadyExists",
  "code": 1,
  "message": "Device is already added"
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "DeviceNameAlreadyInUse",
  "code": 2,
  "message": "Please choose another device name as the {deviceName} is already in use" // server should replace the deviceName part from the POST form
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "",
      "message": ""
    }
  ],
  "name": "ValidationError",
  "code": 3,
  "message": "Suitable validation error message"
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "LicensesAlreadyConsumed",
  "code": 4,
  "message": "Licenses are already consumed"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "LocationIdNotFound",
  "code": 1,
  "message": "Submitted location does not exists"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "DeviceGroupNotFound",
  "code": 2,
  "message": "Submitted device group does not exists"
}
```

Replace device

    /device/replace [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "clientGeneratedDeviceIdentifier": "77eaea49-a768-43e1-9446-e4ec76457c2f", // a UUID generated on client-side, should be validated in DB to be unique
  "oldDeviceId": 12,
  "deviceName": "Cool Device", // (required) should be unique
  "deviceGroupId": 129, // (optional) refers to foreign key of the deviceGroup table
  "locationId": 82, // (required) refers to foreign key of location table
  "deviceOs": "ANDROID|WINDOWS", // (required) enum: ANDROID or WINDOWS
  "deviceWifiMacAddress": "fc:67:36:f6:a3:7e", // optional
  "deviceEthernetMacAddress": "ac:e5:36:f6:7e:a3",
  "unregisteredDeviceId": 12
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyUpdated;
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "ClientGeneratedDeviceIdentifierAlreadyExists",
  "code": 1,
  "message": "Device is already added"
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "",
      "message": ""
    }
  ],
  "name": "ValidationError",
  "code": 3,
  "message": "Suitable validation error message"
}
```

RESPONSE - code - 400

```json
{
   "result": null,
   "pagination": null,
   "name": "ClientGeneratedDeviceIdentifierAlreadyExists",
   "code": 2,
   "message": "Device is already added"
}
```

RESPONSE - code - 404

```json
{
   "result": null,
   "pagination": null,
   "name": "OldDeviceIDNotFound",
   "code": 400,
   "message": "no device found for device ID:150"
}
```

Update device

    /device/<deviceId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "deviceName": "Cool Device",
  "deviceGroupId": 129,     // send -1 to remove device's association with device group
  "locationId": 82,
  "deviceWifiMacAddress": "fc:67:36:f6:a3:7e",
  "deviceEthernetMacAddress": "ac:e5:36:f6:7e:a3",
  "isAudioEnabled": true,
  "status": 1,
  "panelStatus": "ON", // OR OFF
  "deviceOs": "ANDROID|WINDOWS|LINUX"
}
```

Notes:

1. Following things should not change therefore will not be supported in update API
    1. `clientGeneratedDeviceIdentifier`
    2. `deviceOs`
2. Technically mac addresses should also not change but atleast one of them is required in add device API there should
   to add another one we support it in the update API
3. If status is set to `3` (DELETED) then reduce the `consumedNumberOfDevices` in DB table `customerLicence`.

RESPONSE - code - 200

```json
{
  SuccessfullyUpdated;
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "DeviceNameAlreadyInUse",
  "code": 2,
  "message": "Please choose another device name as the {deviceName} is already in use" // server should replace the deviceName part from the POST form
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "",
      "message": ""
    }
  ],
  "name": "ValidationError",
  "code": 3,
  "message": "Suitable validation error message"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "LocationIdNotFound",
  "code": 1,
  "message": "Submitted location does not exists"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "DeviceGroupNotFound",
  "code": 2,
  "message": "Submitted device group does not exists"
}
```

Get devices by deviceGroup

    /devicesByGroup [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "deviceGroupId": 1,
      "deviceGroupName": "Lobby",
      "device": [
        {DeviceModel},
        {DeviceModel},
        {DeviceModel}
      ]
    },
    {
      "deviceGroupId": 2,
      "deviceGroupName": "Cafeteria",
      "device": [
        {DeviceModel},
        {DeviceModel},
        {DeviceModel}
      ]
    },
    {
      "deviceGroupId": 3,
      "deviceGroupName": "Welcome",
      "device": [
        {DeviceModel},
        {DeviceModel},
        {DeviceModel}
      ]
    },
    {
      "deviceGroupId": null,
      "deviceGroupName": null,
      "isUngroupedDevices": true,
      "device": [
        {DeviceModel},
        {DeviceModel},
        {DeviceModel}
      ]
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

Get devices

    /device [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    deviceIds=143,45,37 // comma separated deviceIds (optional)

BODY

    N/A

If deviceIds are not sent then send all the devices of that customer as a response.

RESPONSE - code - 200

```json
{
    "result": {
        "availableDevices": 23,
        "numberOfDevices": 30,
        "consumedNumberOfDevices": 7,
        "devices": [
            {DeviceModel},
            {DeviceModel},
            {DeviceModel}
        ],
        "notFound": [45, 37]
    },
    "pagination": null,
    "name": null,
    "code": 200,
    "message": "Device details 8/8 fetch successfully."
}
```

Update Devices status

    /device/status [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "ids": [
    120,
    121
  ],
  "status":2,
  "panelStatus": "ON", // or OFF
  "isAudioEnabled": true
}
```

Note: If status is set to `3` (DELETED) then reduce the `consumedNumberOfDevices` in DB table `customerLicence`.

RESPONSE - code - 200

```json
{Common Actions for Multiple Actions}
```

Device Search

    /device/search?keyword=xxx&deviceGroup=cafeteria [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    keyword=xxx
    deviceGroup=cafeteria

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {DeviceModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Device Advance Search

    /device?q={<query parameters>} [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    One can use operand(s) (lt-less than, gt-greater than, eq-equal to, lte-less than or equal to, gte-greater than or equal to, lk-like:applicable for text types)
    on following fields in json format:
    location (location id) this should be comma separated locationIds ex: 23,465,5765
    device_group_id (device group id)
    device_status  (enum : ACTIVE,INACTIVE)
    panel_status    (enum : ACTIVE,INACTIVE)
    created_date

    (All above filters are optional, all filters apply using a logical AND i.e. with each extra filter dataset should reduce or remain same
    operands apart from eq, lk can be used only for date and numeric(non ids) filters)

EXAMPLE

    /device?q={"location":{"eq":112}, "panel_status":{"eq":"ACTIVE"}, "location":{"in", "23,465,5765"}}

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {DeviceModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## Assign devices to a deviceGroup

    /assign-devices/<deviceGroupId>  [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "deviceIds": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyMultipleSaved;
}
```

## Remove devices from deviceGroup

    /assign-devices/<deviceGroupId>  [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

```json
{
  "deviceIds": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyMultipleSaved;
}
```

## Panel

Add panel

    /panel [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "panelIp": "192.168.0.56", // (required) neet not be unique
  "deviceId": 323, // (optional) foreign key for device table
  "panelName": "First floor lobby", // (required) should be unique after converting to lower case
  "panelSerialNumber": "SR445X4454",
  "aspectRatioId": 15,
  "isAudioEnabled": false,
  "isPanelOn": true,
  "status": 1
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyAdded;
}
```

Update panel

    /panel/<panelId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "panelIp": "192.168.0.56",
  "deviceId": 323,
  "panelName": "First floor lobby",
  "panelSerialNumber": "SR445X4454",
  "aspectRatioId": 15,
  "status": 1   // 1, 2 or 3
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyAdded;
}
```

Get panels list

    /panel [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    device-id=13      // optional
    panel-name=hello  // optional
    panel-status      // optional - possible values ON, OFF, DISCONNECTED
    sort=ALPHABETICALLY

BODY

    N/A

Note: server don't send panels with status = 3 (deleted)

RESPONSE - code - 200

```json
{
  "result": [
    {PanelModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

Get panel

    /panel/<panelId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {PanelModel},
  "name": null,
  "code": null,
  "message": null
}
```

## Device Login

Device Login

    /device/login [POST]

HEADER

    Content-Type:application/json

QUERY

    N/A

BODY

```json
{
  "deviceId" : 1213,
  "clientGeneratedDeviceIdentifier": "ca3c2e13-e049-46ec-b3e7-20ad2f44b95b",
  "customerId" : 15
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "deviceAuthToken": "asdnkjahlwehfiu394hf3hf8934hf934f",
    "device": {DeviceModel}
  },
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 401

```json
{
  "result": null,
  "name": "Unauthorized",
  "code": 2,     // 2 = device is INACTIVE, 3 = device is DELETED, 4 = DEVICE NOT FOUND, 5 = CUSTOMER IS INACTIVE, 6 = CUSTOMER NOT FOUND, 7 = CUSTOMER DELETED
  "message": null
}
```

## Data Collection v2 (for pidev, prod etc..)

Send data (to be called by devices only):

    /dataCollection/v2 [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {device token}

QUERY

    N/A

BODY

```json
[
  {
    lastSyncTime: 1518092809000, // last time the sycning was started (planogram, layout, content downloading was done)
    ipAddress: "192.168.0.19", // your local IP address
    deviceEncounteredError: true, // for any reason the API did
    isDeviceAudioEnabled: true, // true or false
    timeOfStatus: 1518092809000, // time when status was collected
    isDeviceDown: true, // true if device is device down, false otherwise. However, during panel off time this value should be set to null
    currentBuildVersion: "2.5.33", // deprecated in v3 but works in v2
    timeZone: "+05:30", // device's timeZone at the time of collecting data
    deviceAdditionalInfo: "WEEK_OFF", // can be null. Possible values = WEEK_OFF, AFTER_ONBOARDING
    latitude: 28.613939,
    longitude: 77.209023,
    locationFetchingErrors: "LOCATION_OFF,WIFI_OFF", // comma separted enums : LOCATION_PERMISSION_NOT_GRANTED, LOCATION_OFF, WIFI_OFF, PLAY_SERVICES_NOT_UPDATED, ACCURACY_LOW
  },
];
```

RESPONSE - code - 200

```json
{
  SuccessfullyAdded;
}
```

## Data Collection v3 (currently on pidev2 only. Will be merged with pidev2)

Send data (to be called by devices only):

    /dataCollection/v3 [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {device token}

QUERY

    N/A

BODY

```json
[
  {
    lastSyncTime: 1518092809000, // last time the sycning was started (planogram, layout, content downloading was done)
    ipAddress: "192.168.0.19", // your local IP address
    deviceEncounteredError: true, // for any reason the API did
    isDeviceAudioEnabled: true, // true or false
    timeOfStatus: 1518092809000, // time when status was collected
    isDeviceDown: true, // true if device is device down, false otherwise. However, during panel off time this value should be set to null
    currentBuildVersions: [
      // current version of the build
      {
        buildOs: "WINDOWS",
        version: "1.34.52",
      },
      {
        buildOs: "ANDROID",
        version: "1.34.52",
      },
      {
        buildOs: "ANDROID_WATCH_DOG",
        version: "1.34.52",
      },
      {
        buildOs: "DESKTOP_APP_SERVER",
        version: "1.34.52",
      },
      {
        buildOs: "DESKTOP_APP_CLIENT",
        version: "1.34.52",
      },
      {
        buildOs: "DESKTOP_APP_NATIVE",
        version: "1.34.52",
      },
    ],
    timeZone: "+05:30", // device's timeZone at the time of collecting data
    deviceAdditionalInfo: "WEEK_OFF", // can be null. Possible values = WEEK_OFF, AFTER_ONBOARDING
    latitude: 28.613939,
    longitude: 77.209023,
    locationFetchingErrors: "LOCATION_OFF,WIFI_OFF", // comma separted enums : LOCATION_PERMISSION_NOT_GRANTED, LOCATION_OFF, WIFI_OFF, PLAY_SERVICES_NOT_UPDATED, ACCURACY_LOW
  },
];
```

RESPONSE - code - 200

```json
{
  SuccessfullyAdded;
}
```

## Data Collection - data deleted

Send data (to be called by devices only):

    /dataCollection/deleted-data [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {device token}

QUERY

    N/A

BODY

```json
{
  "deletedDataStartDate" : "2019-07-22",   // start date of deletion
  "deletedDataEndDate" : "2019-07-30",     // end date of deletion
  "panelOnTime": "10:00:00",
  "panelOffTime": "18:00:00",
  "weekOffs": [ "MON", "WED" ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyAdded;
}
```

## Data Collection - data deleted multiple

Send data (to be called by devices only):

    /dataCollection/deleted-data/all [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {device token}

QUERY

    N/A

BODY

```json
[
  {
    deletedDataStartDate: "2019-07-22", // start date of deletion
    deletedDataEndDate: "2019-07-30", // end date of deletion
    panelOnTime: "10:00:00",
    panelOffTime: "18:00:00",
    weekOffs: ["MON", "WED"],
  },
];
```

RESPONSE - code - 200

```json
{
  SuccessfullyAdded;
}
```

## Panel status - data deleted

Send data (to be called by devices only):

    /panel/deleted-data/<panelId> [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {device token}

QUERY

    N/A

BODY

```json
{
  "deletedDataStartDate" : "2019-07-22",   // start date of deletion
  "deletedDataEndDate" : "2019-07-30",     // end date of deletion
  "panelOnTime": "10:00:00",
  "panelOffTime": "18:00:00",
  "weekOffs": [ "MON", "WED" ]
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyAdded;
}
```

## Panel deleted data - data deleted multiple

Send data (to be called by devices only):

    /panel/deleted-data/all/<panelId> [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {device token}

QUERY

    N/A

BODY

```json
[
  {
    deletedDataStartDate: "2019-07-22", // start date of deletion
    deletedDataEndDate: "2019-07-30", // end date of deletion
    panelOnTime: "10:00:00",
    panelOffTime: "18:00:00",
    weekOffs: ["MON", "WED"],
  },
];
```

RESPONSE - code - 200

```json
{
  SuccessfullyAdded;
}
```

## Animations:

Get animations:

    /animations [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "animationId": 1,
      "animationName": "Left to Right"
    },
    {
      "animationId": 2,
      "animationName": "Right to Left"
    },
    {
      "animationId": 3,
      "animationName": "No Animations"
    },
    {
      "animationId": 4,
      "animationName": "Top to bottom"
    },
    {
      "animationId": 5,
      "animationName": "Bottom to top"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## Device Add local server:

Add local server

    /device/set-local-server [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer {deviceToken}       // send device token

QUERY

    N/A

BODY

```json
{
  "localServerIP": "192.168.0.54"
}
```

## Push:

Device registration and unregistration for push (To be called by devices)

    /push/v2/device [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer {deviceToken}     // send device token if present
    DeviceId: deviceId     // send if token is not present
    ClientGeneratedDeviceIdentifier: 34328jf34f439fh4389hf // send if token is not present

QUERY

    N/A

Note: For unregistration send the `pushRegsitrationId` as `null`

BODY

```json
{
  "pushRegsitrationId": "aasdkjhf3iu4f34f3nvio43nf934nfip34f043f04jf", // received from google or windows. Can also be empty, if sent empty then delete the value in column
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "PushRegsitrationIdUpdated",
  "code": 200,
  "message": "Successfully updated push registration ID"
}
```

Send push to devices (To be called by CMS users)

    /push/devices/messages [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
 "deviceIds": [1, 34, 67, 980],
 "pushIds": [ 1 ],
 "instantAppUpgrade": true // false (only to be used in case of PushId 2)
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "success": [
      1,
      54,
      767
    ],
    "notFound": [
      9,
      67,
      24
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

List of Push Ids

```java
public interface PushIds {
  int ID_CLIENT_DELETE_CONTENT = 1;               //: instruct client to delete its content
  int ID_CLIENT_UPDATE_APP = 2;                   //: instruct client to check for app update
  //int ID_CLIENT_TURN_ON_TV_PANEL = 3; (deprecated / removed)            //: instruct client to turn ON their TV panel
  //int ID_CLIENT_TURN_OFF_TV_PANEL = 4;  (deprecated / removed)          //: instruct client to turn OFF their TV panel
  int ID_CLIENT_DISABLE = 5;                      //: instruct client that you are disabled
  int ID_CLIENT_REMOVE = 6;                       //: instruct client that you are removed
 // int ID_CLIENT_REFRESH = 7; (deprecated / removed)//: instruct client that you are refresh
  int ID_CLIENT_TURN_OFF_AUDIO = 8;               //: instruct client to turn off it's audio
  int ID_CLIENT_FETCH_SCHEDULE_AGAIN = 9;         //: instruct client to fetch it's schedule again
  int ID_CLIENT_REDOWNLOAD_CONFIG = 10;           //: instruct client to re-download config
  int ID_CLIENT_TURN_ON_AUDIO = 11;               //: instruct client to turn on it's audio
  int ID_CLIENT_ENABLE = 12;                      //: instruct client that you are enabled (new)
  int ID_CLIENT_ALL_PANELS_OF_DEVICES_ON = 13;    //: instruct client to turn all your panels on (new)
  int ID_CLIENT_ALL_PANELS_OF_DEVICES_OFF = 14;   //: instruct client to turn all your panels off (new)
  int ID_CLIENT_TURN_ON_ALL_PANEL_AUDIO = 15;     //: instruct client to turn all panel audio on (new)
  int ID_CLIENT_TURN_OFF_ALL_PANEL_AUDIO = 16;    //: instruct client to turn all panel audio off (new)
  int ID_CLIENT_REBOOT = 17;                      //: instruct client to reboot (new)
  int ID_CLIENT_INDIVIDUAL_PANELS_OF_DEVICES_ON = 18;    //: instruct client to turn individual panel on (new)
  int ID_CLIENT_INDIVIDUAL_PANELS_OF_DEVICES_OFF = 19;   //: instruct client to turn individual panel off (new)
  int ID_CLIENT_TURN_ON_INDIVIDUAL_PANEL_AUDIO = 20;     //: instruct client to turn individual panel audio on (new)
  int ID_CLIENT_TURN_OFF_INDIVIDUAL_PANEL_AUDIO = 21;    //: instruct client to turn individual panel audio off (new)
  int ID_CLIENT_UPDATE_YOUR_DEVICE_MODEL = 22;     // instruct client to update your device and panel model (new)
  int ID_CLIENT_START_SENDING_DOWNLOAD_PROGRESS = 23;   // instruct client to start sending download progress (new)
  int ID_CLIENT_STOP_SENDING_DOWNLOAD_PROGRESS = 24;    // instruct client to start sending download progress (new)
  int ID_CLIENT_START_SENDING_PANEL_STATUS = 25;   // instruct client to start sending panel status
  int ID_CLIENT_STOP_SENDING_PANEL_STATUS = 26;    // instruct client to start sending panel status
  int ID_CLIENT_CUSTOMER_DEFAULT_IMAGE_UPLOADED = 27; // instruct client to start sending push
  int ID_CLIENT_SEND_A_SNAPSHOT_NOW = 28;             // instruct client to send a snapshot immediately
  int ID_CLIENT_LOCAL_SERVER_HAS_DOWNLOADED_FILES = 29;  // instruct client to send download files from local server
  int ID_CLIENT_REDOWNLOAD_CONTENT_ID = 30;     // instruct client to re-download content ID mentioned in payload
  int ID_CLIENT_CUSTOMER_DEFAULT_IMAGE_LOCAL_SERVER_DOWNLOADED = 31; // instruct client to send download files from local server
  int ID_CLIENT_UPLOAD_LOGS_NOW = 32; // instruct client to upload logs right now
  int ID_CLIENT_TPA_UPDATE = 33; // instruct client that a new upgrade is available for a tpapp
  int ID_CLIENT_DEMOGRAPHY_RULE_CHANGED = 34; // instruct client that a demography rules has changed
  int ID_LOCAL_SERVER_BUILD_UPDATED = 35;  // instruct local server that local server build has updated
}
```

Send push to panels via devices (To be called by CMS users)

    /push/panels/messages [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
 "panelIds": [1, 34, 67, 980],
 "pushIds": [ 1 ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "success": [
      1,
      54,
      767
    ],
    "notFound": [
      9,
      67,
      24
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

## Push acknowledgement

    /push/acknowledge [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    N/A

BODY

```json
{
 "messageId": 132323,   // unique message Id from DB
 "deviceId" 12,
 "pushId": 1,
 "acknowledgement" : "ACK"  // possible values RECEIVED, DONE, NOT_DONE
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "Acknowledged",
  "code": 200,
  "message": "Acknowledged that push was received"
}
```

#### Push Json to be sent to devices / local server as push payload

---

```json
{
  "messageId": 123,      // unique messageId generated by server and sent by server
  "pushId":1,            // required
  "deviceId":67,         // required
  "panelId": 45,         // optional
  "timeOfSendingPush": 156473784384, // unix epoch in milliseconds
  "expiryInMilliseconds": 3600000,   // expiry of push in milliseconds. (null is for Lifetime)
  "uniqueRequestId": "3cc531b3-39d1-4df0-95ee-2f141d9c1b6f",  // only for pushId ID_CLIENT_SEND_A_SNAPSHOT_NOW
  "batchId": "e4354353-39d1-4df0-95ee-2f141d9c1b6f", // batch ID which was downloaded. only for ID_CLIENT_LOCAL_SERVER_HAS_DOWNLOADED_FILES
  "contentId": 23232,    // optional use for push ID (ID_CLIENT_REDOWNLOAD_CONTENT_ID=30)
  "instantAppUpgrade": true // used with APP_UPGRADE pushId
}
```

## Get time API from Local server

Get time API

Note: this API is for local server only.

    /time [GET]

HEADER (no token required)

    Content-Type:application/json

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "currentTime": 13091284038402
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Device Onboarding

Is device added API

    /device/onboarding/<clientGeneratedDeviceIdentifier> [GET]

HEADER (no token required)

    Content-Type:application/json

QUERY

    deviceOs=ANDROID   // optional - possible values WINDOWS, LINUX and ANDROID. If passed and device's saved OS is different then it will be updated

BODY

    N/A

Response when device is not onboarded

RESPONSE - code - 200

```json
{
  "result": {
    "isDeviceOnboarded": false,
    "unregisteredPostNeedsToBeCalled": false  // if it is true then you need to call the ungresitered POST API
  },
  "name": null,
  "code": null,
  "message": null
}
```

Response when device is onboarded

RESPONSE - code - 200

```json
{
  "result": {
    "isDeviceOnboarded": true,
    "deviceId": 13,
    "unregisteredPostNeedsToBeCalled": false
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Device logs

---

Upload device logs:

    /device-logs [POST]

HEADER

    Content-Type: multipart/form-data
    Authorization: Bearer {DeviceToken}   // send deviceToken

QUERY

    N/A

MULTIPART BODY

ContentType json with key `values`

```json
{
  "deviceId": 123,
  "logFileName": "someFileName",
  "logFileStartTime": 126123293901230, // unix epoch
  "logFileEndTime": 126123293901230 // unix epoch
}
```

Also pass the acutal log zip file with key `file`

RESPONSE - BODY

```json
{
  "result": null,
  "pagination": null,
  "name": "FileUploadSucessfully",
  "code": 200,
  "message": "files uploaded sucessfully"
}
```

Get logs API

    /device-logs [GET]

HEADER

    Content-Type: multipart/form-data
    Authorization: Bearer {token}

QUERY

    deviceId = <deviceId>
    logFileStartTime = 189213912839
    logFileEndTime = 123821322912

RESPONSE - code - 200

```json
{
  "result": [
    {
      "logFileName": "someFileName",
      "logFileUrl": "http://fileurl"
    },
    {
      "logFileName": "someFileName",
      "logFileUrl": "http://fileurl"
    },
    {
      "logFileName": "someFileName",
      "logFileUrl": "http://fileurl"
    }
  ],
  "pagination": null,
  "name": "UrlFoundSuccess",
  "code": 200,
  "message": "download files"
}
```

#### Set customer is onboarded API

    /customer/onboarding/<customerId> [PUT]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}
    customerId : <customerId>

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "isCustomerOnboarded": true
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "CustomerNotFound",
  "code": null,
  "message": "Customer not found",
  "pagination": null
}
```

#### Set customer work flow

    /customer/work-flow/<customerId> [PUT]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

BODY

```json
{
  "workFlow": "LAYOUT | LAYOUT_STRING | PLANOGRAM | PLANOGRAM_AND_LAYOUT | NONE"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "workFlow": "LAYOUT | LAYOUT_STRING | PLANOGRAM | PLANOGRAM_AND_LAYOUT | NONE"
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

#### Set role level mapping for a customer

    /role-level-mapping/<customerId> [POST]

BODY

```json
{
  "mapping": [
    {
      "level": 1,
      "roleId": 3
    },
    {
      "level": 2,
      "roleId": 21
    },
    {
      "level": 3,
      "roleId": 12
    },
    {
      "level": 4,
      "roleId": 11
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessFullySaved",
  "code": 20,
  "message": "Record Saved Successfully",
  "pagination": null
}
```

#### Upload device snapshots

Upload device snapshots:

    /device-snapshots [POST]

HEADER

    Content-Type: multipart/form-data
    Authorization: Bearer {DeviceToken}     // send deviceToken

QUERY

    N/A

MULTIPART BODY

ContentType json with key `values`

```json
{
  "deviceId": 123,
  "snapshots": [
    {
      "snapshotFileName": "device15-2018-11-12-10-00-00.png",
      "snapshotTime": 126123293901230,
      "snapshotType": "START"
    },
    {
      "snapshotFileName": "device15-2018-11-12-10-30-00.png",
      "snapshotTime": 126123293901230,
      "snapshotType": "END"
    },
    {
      "snapshotFileName": "device15-2018-11-12-11-00-00.png",
      "snapshotTime": 126123293901230,
      "snapshotType": null
    },
    {
      "snapshotFileName": "device15-2018-11-12-11-30-00.png",
      "snapshotTime": 126123293901230,
      "snapshotType": null
    },
    {
      "snapshotFileName": "device15-2018-11-12-12-00-00.png",
      "snapshotTime": 126123293901230,
      "snapshotType": null
    }
  ]
}
```

Also pass the actual log zip file with key `file`

RESPONSE - BODY

```json
{
  "result": null,
  "pagination": null,
  "name": "FileUploadSucessfully",
  "code": 200,
  "message": "files uploaded sucessfully"
}
```

#### GET device snapshots

Get device snapshots API

    /device-snapshots [GET]

HEADER

    Content-Type: multipart/form-data
    Authorization: Bearer {token}

QUERY

    deviceId = <deviceId>
    snapshotStartTime = 189213912839
    snapshotEndTime = 123821322912

RESPONSE - code - 200

```json
{
  "result": [
    {
      "snapshotTime": 126123293901230,
      "snaphotUrl": "http://fileurl",
      "snapshotThumbnailUrl": "http://thumburl",
      "snapshotType": null
    },
    {
      "snapshotTime": 126123293901230,
      "snaphotUrl": "http://fileurl",
      "snapshotThumbnailUrl": "http://thumburl",
      "snapshotType": "START"
    },
    {
      "snapshotTime": 126123293901230,
      "snaphotUrl": "http://fileurl",
      "snapshotThumbnailUrl": "http://thumburl",
      "snapshotType": "END"
    }
  ],
  "pagination": null,
  "name": "UrlFoundSuccess",
  "code": 200,
  "message": "download files"
}
```

#### Planogram

---

#### Create new planogram

    /planogram/schedule [POST]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

BODY

```json
{
  "isPriorityPlanogram": false,
  "title": "My title",
  "planogramColor": "#777887", // if not present save a default color. Let the default color be: #FF0000
  "recurrence": {
    "repeatEvent": true,
    "recurrenceType": "HOURLY|WEEKLY|DAILY|MONTHLY|MINUTE",
    "weekly": [
      "MON",
      "WED"
    ],
    "monthly": "END_OF_MONTH",   // only 3 possible values: END_OF_MONTH, START_OF_MONTH, MIDDLE_OF_MONTH
    "repeatHours": 2,
    "repeatWeeks": 4,
    "repeatMinutes": 2
  },
  "startDate": "2017-12-10",
  "endDate": "2018-02-03",
  "startTime": "01:30",
  "endTime": "13:30",
  "aspectRatioId": 1
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "planogramId": 15
  },
  "name": "SuccessFullySaved",
  "code": 200,
  "message": "Record Saved Successfully",
  "pagination": null
}
```

#### Update planogram

    /planogram/schedule/<planogramId> [PUT]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

BODY

```json
{
  "isPriorityPlanogram": false,
  "state": "DRAFT|SUBMITTED|APPROVED|PUBLISHED",
  "title": "My title",
  "planogramColor": "#777887", // if not present save a default color. Let the default color be: #FF0000
  "recurrence": {
    "repeatEvent": true,
    "recurrenceType": "HOURLY|WEEKLY|DAILY|MONTHLY",
    "weekly": [
      "MON",
      "WED"
    ],
    "monthly": "END_OF_MONTH",   // only 3 possible values: END_OF_MONTH, START_OF_MONTH, MIDDLE_OF_MONTH
    "repeatHours": 2,
    "repeatWeeks": 4
  },
  "startDate": "2017-12-10",
  "endDate": "2017-02-03",
  "startTime": "01:30",
  "endTime": "13:30"
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessFullyUpdated",
  "code": 200,
  "message": "Record Saved Updated",
  "pagination": null
}
```

#### Planogram layout and layout string

    /planogram/layout-and-layout-strings/<planogramId>    [PUT]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

BODY

```json
{
  "layoutAndLayoutStrings": [
    {
      "layoutId": 1,
      "order": 2
    },
    {
      "layoutStringId": 1,
      "order": 1
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessFullyUpdated",
  "code": 200,
  "message": "Record Saved Updated",
  "pagination": null
}
```

#### Planogram Add a device logic

    /planogram/device-logic/<planogramId> [POST]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

QUERY

    N/A

BODY (only devices body)

```json
{
  "deviceLogic": {
    "key": "DEVICES",
    "deviceIds": [       // empty array is allowed it means no devices selected
      1,
      2,
      3,
      4
    ]
  }
}
```

BODY (only locations body)

```json
{
  "deviceLogic": {
    "key": "LOCATIONS",
    "locationIds": [    // empty array is allowed it means no locations selected
      1,
      3,
      4,
      5,
      6
    ]
  }
}
```

BODY (only deviceGroup body)

```json
{
  "deviceLogic": {
    "key": "DEVICE_GROUPS",
    "deviceGroupIds": [    // empty array is allowed it means no locations selected
      1,
      3,
      4,
      5,
      6
    ]
  }
}
```

BODY (only locations and device groups body)

```json
{
  "deviceLogic": {
    "key": "LOCATIONS_AND_DEVICE_GROUPS",
    "locations": [            // empty array is allowed it means no combination selected
      {
        "locationId": 1,
        "deviceGroupIds": [
          1,
          2
        ]
      },
      {
        "locationId": 3,
        "deviceGroupIds": [
          7,
          24
        ]
      },
      {
        "locationId": 13,
        "deviceGroupIds": [
          12,
          200
        ]
      }
    ]
  }
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "deviceLogicId": 852
  },
  "name": "SuccessFullyUpdated",
  "code": 200,
  "message": "Record Saved Updated",
  "pagination": null
}
```

### Planogram update a device Logic

    /planogram/device-logic/<planogramId>/<deviceLogicId> [PUT]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

QUERY

    N/A

BODY (only devices body)

```json
{
  "deviceLogic": {
    "key": "DEVICES",
    "overwrite": true,   // if true then replace old deviceIds, if false then append these new deviceIds
    "deviceIds": [
      1,
      2,
      3,
      4
    ]
  }
}
```

BODY (only locations body)

```json
{
  "deviceLogic": {
    "key": "LOCATIONS",
    "overwrite": true,   // if true then replace old locationIds, if false then append these new locationIds
    "locationIds": [
      1,
      3,
      4,
      5,
      6
    ]
  }
}
```

BODY (only deviceGroup body)

```json
{
  "deviceLogic": {
    "key": "DEVICE_GROUPS",
    "deviceGroupIds": [    // empty array is allowed it means no locations selected
      1,
      3,
      4,
      5,
      6
    ]
  }
}
```

BODY (only locations and device groups body)

```json
{
  "deviceLogic": {
    "key": "LOCATIONS_AND_DEVICE_GROUPS",
    "overwrite": true,   // if true then replace old location + deviceGroup combinations, if false then append to the old combination: example:
                         // If locationId 1 existied then add the new deviceGroupIds to it. If a new locationId is received then add that to existing set of combination
    "locations": [
      {
        "locationId": 1,
        "deviceGroupIds": [
          1,
          2
        ]
      },
      {
        "locationId": 3,
        "deviceGroupIds": [
          7,
          24
        ]
      },
      {
        "locationId": 13,
        "deviceGroupIds": [
          12,
          200
        ]
      }
    ]
  }
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "deviceLogicId": 852
  },
  "name": "SuccessFullyUpdated",
  "code": 200,
  "message": "Record Saved Updated",
  "pagination": null
}
```

### Planogram Get a device Logic by its ID

    /planogram/device-logic/<planogramId>/<deviceLogicId> [GET]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

Example if `deviceLogic` is for devices

```json
{
  "result": {
    "key": "DEVICES",
    "devices": [
      {DeviceModel},
      {DeviceModel}
    ]
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

RESPONSE - code - 200

Example if `deviceLogic` is for locations

```json
{
  "result": {
    "key": "LOCATIONS",
    "locations": [
      {LocationModel},
      {LocationModel}
    ]
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

```json
{
  "result": {
    "key": "DEVICE_GROUPS",
    "deviceTags": [
      {DeviceGroupModel},
      {DeviceGroupModel}
    ]
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

RESPONSE - code - 200

Example if `deviceLogic` is for locations and device groups

```json
{
  "result": {
    "key": "LOCATIONS_AND_DEVICE_GROUPS",
    "locations": [
      {
        "locationId": 1,
        "locationName": "Delhi",
        "deviceGroups": [
          {
            "deviceGroupId": 3,
            "deviceGroupName": "cafeteria"
          },
          {
            "deviceGroupId": 18,
            "deviceGroupName": "reception"
          }
        ]
      },
      {
        "locationId": 3,
        "locationName": "Noida",
        "deviceGroups": [
          {
            "deviceGroupId": 3,
            "deviceGroupName": "cafeteria"
          },
          {
            "deviceGroupId": 18,
            "deviceGroupName": "reception"
          }
        ]
      },
      {
        "locationId": 13,
        "locationName": "Bangalore",
        "deviceGroups": [
          {
            "deviceGroupId": 3,
            "deviceGroupName": "cafeteria"
          },
          {
            "deviceGroupId": 18,
            "deviceGroupName": "reception"
          }
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

### Planogram Get all device logics

    /planogram/device-logic/<planogramId> [GET]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "deviceLogic": [
      {
        "deviceLogicId": 852,
        "key": "DEVICES",
        "devices": [
          {DeviceModel},
          {DeviceModel}
        ]
      },
      {
        "deviceLogicId": 853,
        "key": "LOCATIONS",
        "locations": [
          {LocationModel},
          {LocatoinModel}
        ]
      },
      {
        "deviceLogicId": 859,
        "key": "DEVICE_GROUPS",
        "deviceTags": [
          {DeviceGroupModel},
          {DeviceGroupModel}
        ]
      },
      {
        "deviceLogicId": 854,
        "key": "LOCATIONS_AND_DEVICE_GROUPS",
        "locations": [
          {
            "locationId": 1,
            "locationName": "Delhi",
            "deviceGroups": [
              {
                "deviceGroupId": 3,
                "deviceGroupName": "cafeteria"
              },
              {
                "deviceGroupId": 18,
                "deviceGroupName": "reception"
              }
            ]
          },
          {
            "locationId": 3,
            "locationName": "Noida",
            "deviceGroups": [
              {
                "deviceGroupId": 3,
                "deviceGroupName": "cafeteria"
              },
              {
                "deviceGroupId": 18,
                "deviceGroupName": "reception"
              }
            ]
          },
          {
            "locationId": 13,
            "locationName": "Bangalore",
            "deviceGroups": [
              {
                "deviceGroupId": 3,
                "deviceGroupName": "cafeteria"
              },
              {
                "deviceGroupId": 18,
                "deviceGroupName": "reception"
              }
            ]
          }
        ]
      },
      {
        "deviceLogicId": 855,
        "key": "LOCATIONS",
        "locations": [
          {LocationModel},
          {LocatoinModel}
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get single planogram by planogramId

    /planogram/<planogramId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "planogramId": 23,
    "createdBy": 709,
    "createdByName": "Pratap Maker",
    "isPriorityPlanogram": false,
    "isPendingForApporval": true, // true if planogram is pending for approval, false otherwise
    "priority": 1,
    "state": "DRAFT|SUBMITTED|APPROVED|PUBLISHED",
    "title": "My title",
    "planogramColor": "#777887", // if not present save a default color. Let the default color be: #FF0000
    "recurrence": {
      "repeatEvent": true,
      "recurrenceType": "HOURLY|WEEKLY|DAILY|MONTHLY",
      "weekly": [
        "MON",
        "WED"
      ],
      "monthly": "END_OF_MONTH",   // only 3 possible values: END_OF_MONTH, START_OF_MONTH, MIDDLE_OF_MONTH
      "repeatHours": 2
    },
    "startDate": "2017-12-10",   // unix epoch in milliseconds, make sure that the millis formed by using a UTC date
    "endDate": "2017-02-03",  // unix epoch in milliseconds, make sure that the millis formed by using a UTC date
    "startTime": "01:30",
    "endTime": "13:30",
    "aspectRatio": {
      "aspectRatioId": 1,
      "aspectRatio": "16:9",
      "defaultWidthInPixel": 864,
      "defaultHeightInPixel": 486,
      "status": 1
    },
    "layoutAndLayoutStrings": [
      {
        "layoutId": 1,
        "layoutName": "my string",
        "order": 2,
        "durationInSeconds": 15 //seconds
      },
      {
        "layoutStringId": 1,
        "layoutStringName": "my string",
        "order": 1
        "durationInSeconds": 145 //seconds
      }
    ],
    "deviceLogic": [
      {
        "deviceLogicId": 852,
        "key": "DEVICES",
        "devices": [
          {DeviceModel},
          {DeviceModel}
        ]
      },
      {
        "deviceLogicId": 853,
        "key": "LOCATIONS",
        "locations": [
          {LocationModel},
          {LocatoinModel}
        ]
      },
      {
        "deviceLogicId": 854,
        "key": "LOCATIONS_AND_DEVICE_GROUPS",
        "locations": [
          {
            "locationId": 1,
            "locationName": "Delhi",
            "deviceGroups": [
              {
                "deviceGroupId": 3,
                "deviceGroupName": "cafeteria"
              },
              {
                "deviceGroupId": 18,
                "deviceGroupName": "reception"
              }
            ]
          },
          {
            "locationId": 3,
            "locationName": "Noida",
            "deviceGroups": [
              {
                "deviceGroupId": 3,
                "deviceGroupName": "cafeteria"
              },
              {
                "deviceGroupId": 18,
                "deviceGroupName": "reception"
              }
            ]
          },
          {
            "locationId": 13,
            "locationName": "Bangalore",
            "deviceGroups": [
              {
                "deviceGroupId": 3,
                "deviceGroupName": "cafeteria"
              },
              {
                "deviceGroupId": 18,
                "deviceGroupName": "reception"
              }
            ]
          }
        ]
      },
      {
        "deviceLogicId": 855,
        "key": "LOCATIONS",
        "locations": [
          {LocationModel},
          {LocatoinModel}
        ]
      }
    ],
    "workFlowActivity": [
      {
        "workFlowActivityType": "REJECT",
        "userId": 12,
        "fullName": "Pratap Patil",
        "reason": "Background color was not good. Please change to yellow.",
        "approverLevel": 2,
        "roles": [
          {
            "roleId": 1,
            "roleName": "CUSTOMER_ADMIN"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L1 Pratap Patil"
      },
      {
        "workFlowActivityType": "APPROVE",
        "userId": 18,
        "fullName": "Desh Deepak",
        "approverLevel": 1,
        "roles": [
          {
            "roleId": 5,
            "roleName": "APPROVER"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L2 Desh Deepak"
      },
      {
        "workFlowActivityType": "SUBMIT",
        "userId": 18,
        "fullName": "Hamzah Jamal",
        "approverLevel": null,
        "roles": [
          {
            "roleId": 4,
            "roleName": "MAKER"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L2 Desh Deepak"
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Planogram Advance Search

    /planogram?q={<query parameters>}  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    One can use operand(s) (lt-less than, gt-greater than, eq-equal to, lte-less than or equal to, gte-greater than or equal to, lk-like:applicable for text types)
    on following fields in json format:
    from_date  (support eq only)   // string "2018-12-02"
    to_date  (support eq only)     // string "2018-12-02"
    from_time  (support eq only)   // string "22:50"
    to_time  (support eq only)     // string "22:50"
    duration_in_seconds
    status (enum : ACTIVE, INACTIVE)
    state (enum : DRAFT, SUBMITTED, APPROVED, REJECTED, PUBLISHED)
    location (location id) - location where planogram is applicable
      (planogram with location Bangalore will be returned if searched by Karnataka,
      also true for vice versa coz planogram with location id of Karnataka would already be applied to Bangalore)
    device_group_id
    device_id
    aspect_ratio

    One can sort the resultset obtained from above query on below fields alongwith sortmethod (asc/desc - by default the result set is sorted
    on entityId descending)
    priority

    (All above filters are optional, all filters apply using a logical AND i.e. with each extra filter dataset should reduce or remain same,
    operands apart from eq,lk can be used only for date and numeric(non ids) filters)

EXAMPLE

    /planogram?q={"from_date":{"eq":"2018-12-13"}, "to_date":{"eq":"2019-02-05"}, "device_group_id":{"eq":21}, "status": {"eq": "ACTIVE"},"state": {"eq": "DRAFT"},"aspect_ratio": {"eq": 1}}&sort={"priority":"desc"}

RESPONSE - code - 200

```json
{
  "result": [
    {
      "planogramId": 213,
      "isPriorityPlanogram": false,
      "priority": 1,
      "state": "DRAFT|SUBMITTED|APPROVED|PUBLISHED",
      "planogramColor": "#777887", // if not present save a default color. Let the default color be: #FF0000
      "title": "My title",
      "recurrence": {
        "repeatEvent": true,
        "recurrenceType": "HOURLY|WEEKLY|DAILY|MONTHLY",
        "weekly": [
          "MON",
          "WED"
        ],
        "monthly": "END_OF_MONTH",   // only 3 possible values: END_OF_MONTH, START_OF_MONTH, MIDDLE_OF_MONTH
        "repeatHours": 2
      },
      "startDate": "2018-09-15",
      "endDate": "2018-10-10",
      "startTime": "01:30",
      "endTime": "13:30",
      "aspectRatio": {
        "aspectRatioId": 1,
        "aspectRatio": "16:9",
        "defaultWidthInPixel": 864,
        "defaultHeightInPixel": 486,
        "status": 1
      },
      "layoutAndLayoutStrings": [
        {
          "layoutId": 1,
          "layoutName": "my string",
          "order": 2,
          "durationInSeconds": 12
        },
        {
          "layoutStringId": 1,
          "layoutStringName": "my string",
          "order": 1,
          "durationInSeconds": 125
        }
      ],
      "deviceLogic": [
        {
          "deviceLogicId": 852,
          "key": "DEVICES",
          "devices": [
            {DeviceModel},
            {DeviceModel}
          ]
        },
        {
          "deviceLogicId": 853,
          "key": "LOCATIONS",
          "locations": [
            {LocationModel},
            {LocationModel}
          ]
        },
        {
          "deviceLogicId": 854,
          "key": "LOCATIONS_AND_DEVICE_GROUPS",
          "locations": [
            {
              "locationId": 1,
              "locationName": "Delhi",
              "deviceGroups": [
                {
                  "deviceGroupId": 3,
                  "deviceGroupName": "cafeteria"
                },
                {
                  "deviceGroupId": 18,
                  "deviceGroupName": "reception"
                }
              ]
            },
            {
              "locationId": 3,
              "locationName": "Noida",
              "deviceGroups": [
                {
                  "deviceGroupId": 3,
                  "deviceGroupName": "cafeteria"
                },
                {
                  "deviceGroupId": 18,
                  "deviceGroupName": "reception"
                }
              ]
            },
            {
              "locationId": 13,
              "locationName": "Bangalore",
              "deviceGroups": [
                {
                  "deviceGroupId": 3,
                  "deviceGroupName": "cafeteria"
                },
                {
                  "deviceGroupId": 18,
                  "deviceGroupName": "reception"
                }
              ]
            }
          ]
        },
        {
          "deviceLogicId": 855,
          "key": "LOCATIONS",
          "locations": [
            {LocationModel},
            {LocationModel}
          ]
        }
      ]
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Get schedule by deviceId (to be called by Devices)

    /schedule/<deviceId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} //  device Token.

QUERY

    start-date=2018-12-30     // required
    end-date=2019-01-15       // required
    app-version=1.0.255       // optional

RESPONSE - code - 200

```json
{
  "result": {
    "deviceSchedule": {
      "deviceId": 1,
      "layouts": [
        {
          "layoutId": 1,
          "startDateTime": "2018-12-30 11:30:00",
          "endDateTime": "2018-12-30 13:30:00",
          "planogramId": 15,
          "isPriorityPlanogram" : true,
          "offsetInSeconds": 23
        },
        {
          "layoutId": 2,
          "startDateTime": "2018-12-30 14:30:00",
          "endDateTime": "2018-12-30 15:30:00",
          "isPriorityPlanogram" : false,
          "offsetInSeconds": 0
        }
      ],
      "startDateTime": "2018-12-30",
      "endDateTime": "2019-01-15"
    }
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get single planogram by planogramId

    /planogram/schedule/<planogramId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "isPriorityPlanogram": false,
    "state": "DRAFT|SUBMITTED|APPROVED|PUBLISHED",
    "title": "My title",
    "planogramColor": "#777887", // if not present save a default color. Let the default color be: #FF0000
    "recurrence": {
      "repeatEvent": true,
      "recurrenceType": "HOURLY|WEEKLY|DAILY|MONTHLY",
      "weekly": [
        "MON",
        "WED"
      ],
      "monthly": "END_OF_MONTH",   // only 3 possible values: END_OF_MONTH, START_OF_MONTH, MIDDLE_OF_MONTH
      "repeatHours": 2,
      "repeatWeeks": 4
    },
    "startDate": "2018-01-01",
    "endDate": "2018-02-15",
    "startTime": "01:30",
    "endTime": "13:30",
    "aspectRatioId": 1,
    "pastDateMessage" : "The planogram you are trying to edit is updated to reflect start date as today as the selected date was a past date" // optional - if sent then angular should show a messge on UI
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get planogram layout and layout strings by planogramId

    /planogram/layout-and-layout-strings/<planogramId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "layoutAndLayoutStrings": [
      {
        "layoutId": 1,
        "layoutName": "my string",
        "order": 2,
        "duration":12, //seconds
        "doesLayoutContainHDMIIn": true,
        "doesLayoutContainDocOrPpt": true
      },
      {
        "layoutStringId": 1,
        "layoutStringName": "my string",
        "order": 1,
        "duration":123, //seconds
        "doesLayoutContainHDMIIn": false,
        "doesLayoutContainDocOrPpt": false
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get planogram logic by planogramId

    /planogram/device-logic/<planogramId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "deviceLogic": [
      {
        "key": "DEVICES",
        "devices": [
          {
            "deviceId": 1,
            "deviceName": "Hello"
          },
          {
            "deviceId": 2,
            "deviceName": "Hello1"
          }
        ]
      },
      {
        "key": "LOCATIONS",
        "locations": [
          {
            "locationId": 1,
            "locationName": "Location"
          },
          {
            "locationId": 3,
            "locationName": "Location1"
          }
        ]
      },
      {
        "key": "LOCATIONS_AND_DEVICE_GROUPS",
        "locations": [
          {
            "locationId": 1,
            "locationName": "Location1"
          },
          {
            "locationId": 3,
            "locationName": "Location2"
          }
        ],
        "deviceGroups": [
          {
            "deviceGroupId": 1,
            "deviceGroupName": "group1"
          },
          {
            "deviceGroupId": 2,
            "deviceGroupName": "group2"
          }
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get devices for planogram

## Get device groups by location

    /deviceGroup/planogram [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    locationIds = 1,4,89   (optional)  // if not sent then do send all the device groups of the customer

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "deviceGroupId": 123,
      "deviceGroupName": "lobby"
    },
    {
      "deviceGroupId": 124,
      "deviceGroupName": "cafeteria"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## Get devices by location & deviceGroups

    /device/planogram [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    locationIds = 1,4,89   (optional)
    deviceGroupIds = 12,455,776,7 (optional)

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {DeviceModel},
    {DeviceModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Get supported content formats for file upload

    /content/supported-formats [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "contentType": "AUDIO",
      "supportedFormats": [
        "mp3"
      ]
    },
    {
      "contentType": "VIDEO",
      "supportedFormats": [
        "mp4",
        "wmv"
      ]
    },
    {
      "contentType": "IMAGE",
      "supportedFormats": [
        "png",
        "jpg",
        "JPEG",
        "JPG"
      ]
    },
    {
      "contentType": "PDF",
      "supportedFormats": [
        "pdf"
      ]
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## Save Location-wise content playlist for layout

    /layout/content-playlist/<layoutId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "layoutRegionId": 13,
  "locationId": 15,
  "localRegionContentPlaylistContents": [
    {
      "contentId": 323,
      "order": 2,
      "durationInSeconds": 600,
      "transparencyInPercentage": 0,
      "entryAnimationId": 329,
      "exitAnimationId": 329,
      "displayMode": "NORMAL|STRETCH-TO-FIT"
    },
    {
      "contentId": 84,
      "order": 1,
      "durationInSeconds": 800,
      "transparencyInPercentage": 30,
      "entryAnimationId": 329,
      "exitAnimationId": 329,
      "displayMode": "NORMAL|STRETCH-TO-FIT"
    },
    {
      "contentId": 5,
      "order": 3,
      "durationInSeconds": 200,
      "transparencyInPercentage": 45,
      "entryAnimationId": 329,
      "exitAnimationId": 329,
      "displayMode": "NORMAL|STRETCH-TO-FIT"
    },
    {
      "widgetType": "CLOCK",
      "order": 6,
      "durationInSeconds": 200,
      "transparencyInPercentage": 45,
      "entryAnimationId": 329,
      "exitAnimationId": 329,
      "displayMode": "NORMAL|STRETCH-TO-FIT"
    }
  ]
}
```

Server-side logic

```java
// Note `targetLocationId` is same as the `locationId` sent in the request.
if (validate(targetLocationId, layoutRegionId)) {
    User loggedInUser = TenantContext.getLoggedInUser(); // logged in user
    if (loggedInUser.getIsPanasonicUser()
        || loggedInUser.getIsCustomerAdmin()
        || loggedInUser.getLocationIds() == null
        || loggedInUser.getLocationIds().isEmpty()) {
        // loggedInUser is not limited to a location
        saveTheContentPlaylist();
    } else {
        // loggedInUser is limited to some locations
        boolean canSave = false;
        for (Long locationId : loggedInUser.getLocationIds()) {
            if (isLocationIdDirectOrIndirectChildOfGivenLocationId(locationId, targetLocationId)) {
                canSave = true;
                break;
            }
        }
        if (canSave) {
            saveTheContentPlaylist();
        } else {
            // ForbiddenResponse() -> user does not have rights to store content playlist
            // for given region
        }
    }
} else {
    // send validation error the `layoutRegionId` is not configured to
    // accept content playlist for the `targetLocationId`
}

boolean (long targetLocationId, long layoutRegionId) {
    // check if the locationId and layoutRegionId exists in the `layoutRegionLocationRel` table and is active
    // return true if exists, false otherwise.
}

void saveTheContentPlaylist() {
    // save the content playlist also save the locationId as the foreign key
}

boolean isLocationIdDirectOrIndirectChildOfGivenLocationId(long givenLocationId, long targetLocationId) {
    // check if `targetLocationId` is child of the `givenLocationId` or not
    // return true if it is, false otherwise
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "LocationIdAndRegionIdCombinationDoesNotExist",
  "code": 1,
  "message": "The combination of given region, and location have not been configure in the layout."
}
```

RESPONSE - code - 403

```json
{
  "result": null,
  "name": "Forbidden",
  "code": 403,
  "message": "You do not have rights to modify this content playlist"
}
```

## Get Location-wise content playlist for layout

    /layout/content-playlist/<layoutId>/<layoutRegionId>/<locationId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "result": {
    "localRegionContentPlaylistContents": [
      {
        "contentId": 323,
        "order": 2,
        "durationInSeconds": 600,
        "transparencyInPercentage": 0,
        "entryAnimationId": 329,
        "exitAnimationId": 329,
        "displayMode": "NORMAL|STRETCH-TO-FIT"
      },
      {
        "contentId": 84,
        "order": 1,
        "durationInSeconds": 800,
        "transparencyInPercentage": 30,
        "entryAnimationId": 329,
        "exitAnimationId": 329,
        "displayMode": "NORMAL|STRETCH-TO-FIT"
      },
      {
        "contentId": 5,
        "order": 3,
        "durationInSeconds": 200,
        "transparencyInPercentage": 45,
        "entryAnimationId": 329,
        "exitAnimationId": 329,
        "displayMode": "NORMAL|STRETCH-TO-FIT"
      },
      {
        "widgetType": "CLOCK",
        "order": 6,
        "durationInSeconds": 200,
        "transparencyInPercentage": 45,
        "entryAnimationId": 329,
        "exitAnimationId": 329,
        "displayMode": "NORMAL|STRETCH-TO-FIT"
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Is email valid for Point of contact

---

    /point-of-contact/{customerId} [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    email = abc@abc.com

RESPONSE - code - 200

```json
{
  "result": {
    "canEmailBeUsedForPoc": true  // or false
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Update panel statuses per deviceId (Devices only)

    /panel/v2/status [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    N/A

BODY

```json
{
  "panels": [
    {
      "panelId": 12,
      "panelStatuses": [
        {
          "panelStatus": "ON_HDMI_CONNECTED",   // required
          "panelAdditionalInfo": null,   // optional - can be null. Possible values = WEEK_OFF, AFTER_ONBOARDING
          "isAudioEnabled": true,  // or false   - optional (null)
          "timestamp": 1522631372434,   // required
          "timeZone": "+05:30"   // required
        },
        {
          "panelStatus": "OFF_HDMI_CONNECTED",
          "panelAdditionalInfo": "WEEK_OFF",
          "isAudioEnabled": true,  // or false
          "timestamp": 1522731372434,
          "timeZone": "+05:30"
        },
        {
          "panelStatus": "DISCONNECTED_HDMI_CONNECTED",
          "panelAdditionalInfo": null,
          "timestamp": 1522831372434,
          "timeZone": "-08:00"
        },
        {
          "panelStatus": "DATA_NOT_AVAILABLE",
          "panelAdditionalInfo": null,
          "timestamp": 1522831372434,
          "timeZone": "+05:30"
        }
      ]
    },
    {
      "panelId": 112,
      "panelStatuses": [
        {
          "panelStatus": "ON_HDMI_DISCONNECTED",
          "timestamp": 1522631372434,
          "panelAdditionalInfo": null,
          "timeZone": "+05:30"
        }
      ]
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Records Saved Successfully"
}
```

#### Get panel status By deviceId

    /panel/status/<deviceId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "deviceId": 12,
  "panels": [
    {
      "panelId": 132,
      "panelStatus": "ON",  // latest status of the panel
      "isAudioEnabled": true,  // or false
    },
    {
      "panelId": 139,
      "panelStatus": "OFF",   // latest status of the panel
      "isAudioEnabled": true,  // or false
    },
    {
      "panelId": 157,
      "panelStatus": "DISCONNECTED"  // latest status of the panel
    }
  ]
}
```

#### Device Error reporting (Devices only)

    /device/error [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    N/A

BODY

```json
{
  "deviceErrors": [
    {
      "errorCode": 1,
      "errorDescription": "Lorem ipsum",
      "timestamp": 1522631372434
    },
    {
      "errorCode": 2,
      "errorDescription": "Lorem ipsum",
      "timestamp": 1522731372434,
      "url": "http://mymediafile.com/47" // url of the API failed
    },
    {
      "errorCode": 1,
      "errorDescription": "Lorem ipsum",
      "timestamp": 1522831372434
    },
    {
      "errorCode": 9,
      "errorDescription": "Lorem ipsum",
      "timestamp": 1522831372434,
      "contentId": 47 // content id
    },
    {
      "errorCode": 6,
      "errorDescription": "Lorem ipsum",
      "timestamp": 1522831372434,
      "panelId": 15 // panel id
    }
  ]
}
```

List of valid error codes:

    errorCode = 1 // Other error
    errorCode = 2 // Any API giving 500 errors
    errorCode = 3 // Device storage full
    errorCode = 4 // Out of memory error
    errorCode = 5 // Internet not available
    errorCode = 6 // Any TV panel connectivity issues
    errorCode = 7 // Local server connectivity issues
    errorCode = 8 // USB file encryption issues
    errorCode = 9 // Content download failed

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Records Saved Successfully"
}
```

#### Set planogram priorities

---

    /planogram/priorities [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "planogramPriorities": [
    { "planogramId": 12, "priority": 1 },
    { "planogramId": 18, "priority": 2 },
    { "planogramId": 92, "priority": 3 }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullyUpdated",
  "code": 20,
  "message": "Records Updated Successfully"
}
```

#### Layout clone

---

    /layout/clone/<layoutId> [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "clonedLayoutId": 15
  },
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Records saved Successfully"
}
```

#### Layout String clone

---

    /layoutString/clone/<layoutStringId> [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "clonedLayoutStringId": 15
  },
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Records saved Successfully"
}
```

#### Associate Panasonic customer representative to customer(s)

    /user/associations [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "userId": 14,
  "customerIds": [
    1,
    5,
    18
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Records saved Successfully"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "UserNotFound",
  "code": 1,
  "message": "User not found"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "CustomersNotFound",
  "code": 2,
  "message": "CustomerId 1, 5 not found"
}
```

#### Get Panasonic customer representative's customer associations

    /user/associations/<userId> [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "customers": [
      { "customerId": 1, "custName": "Maruti" },
      { "customerId": 5, "custName": "KFC" },
      { "customerId": 18, "custName": "Mc Donalds" }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "UserNotFound",
  "code": 1,
  "message": "User not found"
}
```

#### Does user have access to these locations in a given layout

    /layout/location-access/<layoutId>  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "locationId": 4,
      "locationName": "Abc",
      "canEdit": true
    },
    {
      "locationId": 6,
      "locationName": "Xyz",
      "canEdit": false
    },
    {
      "locationId": 89,
      "locationName": "Mno",
      "canEdit": true
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "UserNotFound",
  "code": 1,
  "message": "User not found"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "LocationNotFound",
  "code": 1,
  "message": "Location IDs 4, 89 not found"
}
```

#### Panel update by deviceId

---

    /panel [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "aspectRatioId": 12,
  "deviceId": 323,
  "panels": [
    {
      "panelId": 132,     // existing panel
      "panelIp": "192.168.0.56",
      "panelName": "First floor lobby",
      "panelSerialNumber": "SR445X4454",
      "panelControl": "RJ45" // check the panel model for more details
    },
    {      // panel IP missing which means this panel is connected via RS-232
      "panelId": 133,    // existing panel
      "panelName": "First floor lobby",
      "panelSerialNumber": "SR445X4455",
      "panelControl": "RS232_RM_HDMI_ONLY" // check the panel model for more details
    },
    {                    // panel Id missing therefore a new panel which will be added
      "panelIp": "192.168.0.56",
      "panelName": "First floor lobby",
      "panelSerialNumber": "SR445U54656",
      "panelControl": "OTHERS" // check the panel model for more details
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullyUpdated",
  "code": 30,
  "message": "Panels updated successfully"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "PanelsNotFound",
  "code": 30,
  "message": "PanelIds 1, 16, 8 not found"
}
```

#### Panel update by deviceId, validations API

---

    /panel/validation [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

BODY

```json
{
  "aspectRatioId": 24,
  "deviceId": 323,
  "panels": [
    {
      "panelId": 132,     // existing panel
      "panelIp": "192.168.0.56",
      "panelName": "First floor lobby",
      "panelSerialNumber": "SR445X4454"
    },
    {      // panel IP missing which means this panel is connected via RS-232
      "panelId": 133,    // existing panel
      "panelName": "First floor lobby",
      "panelSerialNumber": "SR445X4455"
    },
    {                    // panel Id missing therefore a new panel which will be added
      "panelIp": "192.168.0.56",
      "panelName": "First floor lobby",
      "panelSerialNumber": "SR445U54656"
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "AllDataIsGood",
  "code": 1,
  "message": "All data is good you can update the the panels"
}
```

RESPONSE - code - 400

```json
{
  "result": [
    {
      "field": "panelSerialNumber",
      "message": "Panel serial number SR445U54656 is already in use in device named {deviceName}. We will replace the panel in that device. Do you want to proceed?"
    }
  ],
  "name": null,
  "code": 1,
  "message": null
}
```

#### USB Download

---

    /download-all  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    start-date=2018-12-30
    end-date=2019-01-15
    device-ids=1,4,678   // comma separated

RESPONSE - code - 200

```json
{
  "result": {
    "fileDownloadUrl": "http://192.168.0.91:9001/offline-content-file/145",
    "SHA-1": "cf23df2207d99a74fbe169e3eba035e633b65d94",
    "MD5": "a8f5f167f44f4964e6c998dee827110c",
    "textFileDownloadUrl": "http://my-download-site.com/md5AndSha.txt",
    "encryptedFilePassword": "78T4"
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get device (to be called by devices only)

---

    /device/me [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

RESPONSE - code - 200

```json
{
  "result": {DeviceModel},
  "name": null,
  "code": 1,
  "message": null
}
```

Note: DeviceModel will have a boolean flag: "doesEnvironmentHaveS3" : true / false

#### Get location hierarchy with user editable flags

---

    /location-hierarchy/user-access [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "hierarchyLevelCount": 3,
    "hierarchy": {
      "locationId": 3,
      "locationName": "Delhi NCR",
      "childNode": [
        {
          "locationId": 15,
          "locationName": "Delhi",
          "childNode": [
            {
              "locationId": 19,
              "locationName": "Delhi",
              "userHasAccess": true,
              "childNode": null
            }
          ]
        },
        {
          "locationId": 124,
          "locationName": "Noida",
          "childNode": [
            {
              "locationId": 45,
              "userHasAccess": true,
              "locationName": "Sector 18",
              "childNode": null
            }
          ]
        }
      ]
    }
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get location hierarchy with user editable flags

---

    /location-hierarchy/user-access-devices [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "hierarchyLevelCount": 3,
    "hierarchy": {
      "locationId": 3,
      "locationName": "Delhi NCR",
      "deviceCount": 7,
      "childNode": [
        {
          "locationId": 15,
          "locationName": "Delhi",
          "deviceCount": 6,
          "childNode": [
            {
              "locationId": 19,
              "locationName": "Delhi",
              "userHasAccess": true,
              "deviceCount": 6,
              "childNode": null
            }
          ]
        },
        {
          "locationId": 124,
          "locationName": "Noida",
          "deviceCount": 1,
          "childNode": [
            {
              "locationId": 45,
              "userHasAccess": true,
              "deviceCount": 1
              "locationName": "Sector 18",
              "childNode": null
            }
          ]
        }
      ]
    }
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get user's own profile

---

    /user/profile  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {UserModel},
  "name": null,
  "code": null,
  "message": null
}
```

#### Planogram by individual device ID

---

    /planogram/device/<deviceId>?start-date=2018-12-30&end-date=2019-01-15  [GET]

Note: this API will not be called by devices but will be called by users

RESPONSE - code - 200

```json
{
  "result": {
    "planograms": [
      {
        "planogramId": 1,
        "title": "Abc",
        "planogramColor": "#888888",
        "startDateTime": "2018-12-30 11:30:00",
        "endDateTime": "2018-12-30 13:30:00"
      },
      {
        "planogramId": 2,
        "title": "Xyz",
        "planogramColor": "#888888",
        "startDateTime": "2018-12-30 14:30:00",
        "endDateTime": "2018-12-30 15:30:00"
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get layout for device

    /layout/device/<layoutId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken} // please pass device token

QUERY

    N/A

RESPONSE - code - 200

```json
{
  "result": {LayoutModel},
  "name": null,
  "code": 0,
  "message": null
}
```

#### Planogram bulk status

    /planogram/status [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // please pass user token

QUERY

    N/A

BODY

```json
{
  "status": 2,  // 1 or 3
  "ids": [1,56,67]
}
```

RESPONSE - code - 200

Common Multiple Actions response

#### Planogram device logic delete

    /planogram/device-logic/<planogramId>/<deviceLogicId> [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // please pass user token

QUERY

    N/A

BODY (only devices body)

```json
{
  "deviceLogic": {
    "key": "DEVICES",
    "deviceIds": [       // empty array is allowed it means no devices selected
      1,
      2,
      3,
      4
    ]
  }
}
```

BODY (only locations body)

```json
{
  "deviceLogic": {
    "key": "LOCATIONS",
    "locationIds": [    // empty array is allowed it means no locations selected
      1,
      3,
      4,
      5,
      6
    ]
  }
}
```

BODY (only deviceGroup body)

```json
{
  "deviceLogic": {
    "key": "DEVICE_GROUPS",
    "deviceGroupIds": [    // empty array is allowed it means no locations selected
      1,
      3,
      4,
      5,
      6
    ]
  }
}
```

BODY (only locations and device groups body)

```json
{
  "deviceLogic": {
    "key": "LOCATIONS_AND_DEVICE_GROUPS",
    "locations": [            // empty array is allowed it means no combination selected
      {
        "locationId": 1,
        "deviceGroupIds": [
          1,
          2
        ]
      },
      {
        "locationId": 3,
        "deviceGroupIds": [
          7,
          24
        ]
      },
      {
        "locationId": 13,
        "deviceGroupIds": [
          12,
          200
        ]
      }
    ]
  }
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullyUpdated",
  "code": 30,
  "message": "Records Updated Successfully"
}
```

#### Device basic search

    /device/search

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // please pass user token

QUERY

    keyword=xxx
    deviceGroup=yyy

RESPONSE - code - 400

```json
{
  "result": [
    {DeviceModel},
    {DeviceModel},
    {DeviceModel}
  ],
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

#### Get all customers which are not associated with given PAN_REP user

    /customer/association [GET]

Note: to be called by PAN Admin user only

HEADER

    Content-Type:application/json
    Authorization:Bearer {user token} // please pass user token

QUERY

    not-associated-with=15   // (required) where 15 is the userId of the cust rep user

RESPONSE - code - 200

```json
{
  "result": [
    {CustomerModel},
    {CustomerModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Device Add self (to be called by devices only)

    /unregistered-device [POST]

Note: device login not required. This is an open API.

HEADER

    Content-Type:application/json
    customerId: <customerId>

QUERY

    N/A

BODY (only devices body)

```json
{
  "clientGeneratedDeviceIdentifier": "77eaea49-a768-43e1-9446-e4ec76457c2f",
  "deviceOs": "ANDROID|WINDOWS|LINUX",
  "deviceWifiMacAddress": "fc:67:36:f6:a3:7e",
  "deviceEthernetMacAddress": "ac:e5:36:f6:7e:a3"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "unregisteredDeviceId": 15
  },
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

RESPONSE - code - 400 // send this error if the clientGeneratedDeviceIdentifier is in use in device table

```json
{
  "result": {
    "field": "clientGeneratedDeviceIdentifier",
    "message": "Device already added."
  },
  "name": null,
  "code": 400,
  "message": null
}
```

RESPONSE - code - 400 // send this error if the clientGeneratedDeviceIdentifier unregistered device table

```json
{
  "result": {
    "field": "clientGeneratedDeviceIdentifier",
    "message": "Device is already waiting to added."
  },
  "name": null,
  "code": 400,
  "message": null
}
```

RESPONSE - code - 400 // if the customer is inactive

```json
{
  "result": null,
  "name": "NoSuchActiveCustomer",
  "code": 400,
  "message": "There is no such active customer"
}
```

RESPONSE - code - 400 // if the customer is not found

```json
{
  "result": null,
  "name": "NoSuchCustomer",
  "code": 400,
  "message": "There is no such customer"
}
```

RESPONSE - code - 400 // if the customer is of basic tier

```json
{
  "result": null,
  "name": "BasicCustomerError",
  "code": 8000,
  "message": "Contact Panasonic Admin to upgrade from basic tier to advance tier. Once the Panasonic admin moves you to advance tier, then click the retry button."
}
```

#### Get list of unregistered devices

    /unregistered-device [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // please pass user token

QUERY

clientGeneratedDeviceIdentifier=xxx (optional) // not use like based search

RESPONSE - code - 200

```json
{
  "result": [
    {
      "clientGeneratedDeviceIdentifier": "77eaea49-a768-43e1-9446-e4ec76457c2f",
      "unregisteredDeviceId": 129,
      "deviceOs": "ANDROID|WINDOWS|LINUX",
      "deviceWifiMacAddress": "fc:67:36:f6:a3:7e",
      "deviceEthernetMacAddress": "ac:e5:36:f6:7e:a3"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Delete planogram

    /planogram [DELETE]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

```json
{
  "ids": [      // one or multiple ids can pass in array.
    41,
    43,
    543
  ]
}
```

RESPONSE - code - 200

Common Actions for Multiple Actions

# Approval Flow

---

#### Layout submit

---

    /layout/<layoutId>/submit [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {LayoutModel},
  "name": "SuccessFullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

RESPONSE - code - 200

```json
{
  "result": {LayoutModel},
  "name": "SuccessFullySaved",
  "code": 21,
  "message": "Approvers not present at level 1"
}
```

#### Layout approve

---

    /layout/<layoutId>/approve [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {LayoutModel},
  "name": "SuccessFullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

RESPONSE - code - 200

```json
{
  "result": {LayoutModel},
  "name": "SuccessFullySaved",
  "code": 21,
  "message": "Approvers not present at level X"
}
```

#### Layout reject

---

    /layout/<layoutId>/reject [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

```json
{
  "comment": "Color of background does not look good."
}
```

RESPONSE - code - 200

```json
{
  "result": {LayoutModel},
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Layout string submit

---

    /layout_string/<layoutStringId>/submit [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {LayoutStringModel},
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Layout string approve

---

    /layout_string/<layoutStringId>/approve [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {LayoutStringModel},
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Layout string reject

---

    /layout_string/<layoutStringId>/reject [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

```json
{
  "comment": "Please move the order of the Introduction layout to 1."
}
```

RESPONSE - code - 200

```json
{
  "result": {LayoutStringModel},
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Planogram submit

---

    /planogram/<planogramId>/submit [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {PlanogramModel},
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Planogram approve

---

    /planogram/<planogramId>/approve [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {PlanogramModel},
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Planogram reject

---

    /planogram/<planogramId>/reject [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

```json
{
  "comment": "Please remove the device Bangalore device logic."
}
```

RESPONSE - code - 200

```json
{
  "result": {PlanogramModel},
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Get devices which are ungrouped and under given locationIds

---

    /device/ungrouped [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    location-ids=1,548,56

BODY

    NA

Note for server team: this will have all the devices which are under a given locationIds as passed in URL

RESPONSE - code - 200

```json
{
  "result": [
    {DeviceModel},
    {DeviceModel},
    {DeviceModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Get my role

---

Gets user's role. In addition to that if user is location restricted it does not send
LAYOUT -> ADD, EDIT, DELETE. When more location based restrictions will come then this API
will be modified to fulfil the use cases.

    /role/my-role [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "roleId": 1,
    "roleName": "CUSTOMER_ADMIN",
    "isCustomRole": false,
    "isApproverRole": false,
    "isRoleUsedInApproverLevelMapping": false,  // this key will be present only on custom roles and not on system roles. If false then it means that the custom role is not used in any approver level mapping. If true then it means it is used in approver level mapping
    "isLocationRestricted": true,
    "resourceActionPairs": [
      {
        "resourceName": "PLANOGRAM",
        "actions": [
          "VIEW",
          "ADD",
          "EDIT",
          "DELETE"
        ]
      },
      {
        "resourceName": "DEVICE",
        "actions": [
          "VIEW",
          "ADD",
          "EDIT",
          "DELETE"
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get states

---

    /state [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [ "DRAFT", "SUBMITTED", "APPROVED", "REJECTED", "PUBLISHED" ],
  "name": null,
  "code": null,
  "message": null
}
```

## Pending for Approval list

#### Layouts pending for my approval

    /layout/waiting-for-approval [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

```json
{
  "result": [
    {LayoutModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Layout Strings pending for my approval

    /layoutString/waiting-for-approval [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

```json
{
  "result": [
    {LayoutStringModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Planogram pending for my approval

    /planogram/waiting-for-approval [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

```json
{
  "result": [
    {PlanogramModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Delete panel

---

    /panel/<deviceId>/<panelId> [DELETE]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "DeleteSuccess",
  "code": 200,
  "message": "Panel Deleted Successfully"
}
```

#### Get current time in milliseconds

---

Get current time in milliseconds from server

    /currentMilliSeconds [GET]

HEADER

    Content-Type:application/json

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": 1525268964313,
  "name": null,
  "code": null,
  "message": null
}
```

#### Notify devices of a client app upgrade

---

API to notify the devices about a client app upgrade, via push notifications

    /app-upgrade/notify [POST]

HEADER

    Content-Type:application/json
    Authorization : Bearer <jenkins token> // <tp app user token>

QUERY

    NA

BODY

```json
{
  "deviceOs": "ANDROID|ANDROID_WATCH_DOG|ANDROID_TV|WINDOWS|LINUX_SERVER|LINUX_CLIENT|LINUX_NATIVE",  // required
  "buildDownloadUrl": "http://builddownloadurl", // either s3ObjectKey or this key is required
  "oldUrlForFileName": "http://oldrbuilddownloadurl", // required
  "s3ObjectKey": "android/build/123", // either buildDownloadUrl or this key is required
  "onPremisesId": 334 // optional - if present the the build being notified is of on-premises otherwise it is main server build
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved.",
  "code": 20,
  "message": "Notification Sent Successfully to Upgrade Devices"
}
```

#### To download latest app package for devices

---

Can be called by devices as well as user. Returns multipart file (apk of appx) in response.
if isGeneric=true is supplied, it returns non-customer specific app package bundle (one which has customerId burnt in
it)
else the package return will have no customerId burnt, will be a generic one.

    /app-upgrade/download2 [GET]

REQUEST HEADERS

    Content-Type:application/json
    Authorization : Bearer <device token>

QUERY

    os=windows|android|android_watch_dog|android_tv|desktop_app_server|desktop_app_client|desktop_app_native  // required - both upper and lower case values will work

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "downloadLink": "https://piqa1server.impressicocrm.com/build-file?encrypted-code=eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJiMzRhNTM0Yy0yOWJmLTQyMTUtOTgyZi02NzA2ZTIzMzE3OTIiLCJpYXQiOjE1NjY1Njc2MDYsInN1YiI6IntcImRldmljZUlkXCI6MTk3NyxcImlzT25QcmVtaXNlU2VydmVyXCI6ZmFsc2V9IiwiaXNzIjoiRGlnaXRhbCBTaWduYWdlIFNlcnZlciIsImV4cCI6MTU2NjU2OTQwNn0.gYx6aZbQi_2BZWd_dUsCEWJJgqzeETBvPNXpb3nZmtl0d2A3tFTpmtOCUJTEXQQgN3AdCTOXxGhJ3-VoZ53b0A&device-os=WINDOWS",
    "md5Hash": "D29429478EB8E59FA28557B76D403B45",
    "appVersion": "1.5.6.0",
    "fileName": "PanasonicDigitalSignage_1.5.6.0_Test.zip",
    "buildOs": "WINDOWS",
    "isBuildPresent": true,
    "androidFileName": null,   // only for old windows and andorid apps
    "windowsFileName": "PanasonicDigitalSignage_1.5.6.0_Test.zip"  // only for old windows and andorid apps
  },
  "pagination": null,
  "name": "BuildIsPresent",
  "code": 20,
  "message": "Build URL generated successfully"
}
```

#### To download latest device app package from angular

---

Can be called by devices as well as user. Returns multipart file (apk of appx) in response.
if isGeneric=true is supplied, it returns non-customer specific app package bundle (one which has customerId burnt in
it)
else the package return will have no customerId burnt, will be a generic one.

    /app-upgrade/angular/download [GET]

REQUEST HEADERS

    Authorization: Bearer <user token>

QUERY

    os=windows|android|android_watch_dog|android_tv|desktop_app_server|desktop_app_client|desktop_app_native  // required - both upper and lower case values will work

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "downloadLink": "https://piqa1server.impressicocrm.com/build-file?encrypted-code=eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJiMzRhNTM0Yy0yOWJmLTQyMTUtOTgyZi02NzA2ZTIzMzE3OTIiLCJpYXQiOjE1NjY1Njc2MDYsInN1YiI6IntcImRldmljZUlkXCI6MTk3NyxcImlzT25QcmVtaXNlU2VydmVyXCI6ZmFsc2V9IiwiaXNzIjoiRGlnaXRhbCBTaWduYWdlIFNlcnZlciIsImV4cCI6MTU2NjU2OTQwNn0.gYx6aZbQi_2BZWd_dUsCEWJJgqzeETBvPNXpb3nZmtl0d2A3tFTpmtOCUJTEXQQgN3AdCTOXxGhJ3-VoZ53b0A&device-os=WINDOWS",
    "md5Hash": "D29429478EB8E59FA28557B76D403B45",
    "appVersion": "1.5.6.0",
    "fileName": "PanasonicDigitalSignage_1.5.6.0_Test.zip",
    "buildOs": "WINDOWS",
    "isBuildPresent": true,
    "androidFileName": null,   // only for old windows and andorid apps
    "windowsFileName": "PanasonicDigitalSignage_1.5.6.0_Test.zip"  // only for old windows and andorid apps
  },
  "pagination": null,
  "name": "BuildIsPresent",
  "code": 20,
  "message": "Build URL generated successfully"
}
```

#### To download TPA app upgrades

---

Only TPAs which have an app stored on server will be sent in this API

    /app-upgrade/tpa [GET]

REQUEST HEADERS

    Content-Type:application/json
    Authorization : Bearer <device token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "tpappId": 12,
      "downloadLink": "http//:download/",
      "md5Hash": "f970e2767d0cfe75876ea857f92e319b",
      "appVersion": "4.52.188",
      "updateType": "DELETE_AND_UPDATE|ONLY_UPDATE",
      "tpAppBuildId" 122
    },
    {
      "tpappId": 13,
      "downloadLink": "http//:download/",
      "md5Hash": "f970e2767d0cfe75876ea857f92e319b",
      "appVersion": "4.52.188",
      "updateType": "DELETE_AND_UPDATE|ONLY_UPDATE",
      "tpAppBuildId" 177
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Web push registration

---

    /push/web [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token
    customerId : 23    // (optional) to be sent in header

QUERY

    NA

BODY

```json
{
  "registrationId": "adjheif3h43490fj340f3940fj304fj3409"
}
```

RESPONSE - code - 200

```json
{
  "result": "adjheif3h43490fj340f3940fj304fj3409",   //registrationId
  "name": "SuccessfullySaved.",
  "code": 20,
  "message": "registrationId saved"
}
```

## Note for server team:

1. Customer Id may or may not be present in header
2. It should be considered only for customer rep users
3. If the user is cust rep and the header has customer Id then store the `customerId` in `userFcmRegistrationIdMapping`
   table if customerId is not present then also don't save it
4. While sending push to cust rep

#### Web push mark as read

---

    /push/web/read [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

```json
{
  "messageId": 123,  "markAllAsRead": true        // either one of the the two json keys are required
}
```

RESPONSE - code - 200

```json
{
  "result": 123,   // messageId
  "name": "SuccessfullySaved.",
  "code": 1,
  "message": "Message read"
}
```

#### Web push get all keys

---

    /push/web/keys [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "MAKERS_ENTITY_APPROVED": 1,
    "MAKERS_ENTITY_REJECTED": 2,
    "ENTITY_WAITING_FOR_APPROVAL": 3,
    "PLANO_READY_TO_GO_LIVE": 4,
    "PLANO_SUBMITTED_OR_APPROVED": 5,
    "CONTENT_DOWNLOAD_FAILURE": 6,
    "LICENSE_RENEWAL": 7,
    "PANEL_UPTIME": 8,
    "PANEL_DOWNTIME": 9,
    "DEVICE_UPTIME": 10,
    "DEVICE_DOWNTIME": 11,
    "LAYOUT_OR_LAYOUT_STRING_DELETED": 12,
    "PLANO_DELETED": 13,
    "CUSTOMER_SETUP_PENDING": 14,
    "CUSTOMER_ONBOARDED": 15,
    "MARK_ALL_MESSAGES_AS_READ": 16,
    "PANEL_UPDATES": 17,
    "DEVICE_UPDATES": 18,
    "CURRENT_DOWNLOAD_UPDATES": 19,
    "DEVICE_UP_DOWN": 20,
    "STOP_DEVICE_AND_PANEL_UPDATES": 21,
    "DEVICE_MUTE_UNMUTED": 22,
    "LIVE_SNAPSHOT_AVAILABLE_OR_ERRORED": 23,
    "STOP_CURRENT_DOWNLOAD_UPDATES": 24
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Web push get all notifications

---

    /push/web/notifications [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer {token}  // user token

QUERY

    pageNumber=12
    numPerPage=20

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "unreadCount": 15,
    "notifications" : [
      {
        "messageId": 21321,
        "messageType": 14,
        "messageTitle": "subject",
        "messageString": "message",
        "customerId": 144,
        "entityType": "layout",
        "entityId": 12,
        "entityPage": null,
        "sentOn": 1527155499000,
        "read": true // true if read, false otherwise
      },
      {
        "messageId": 21321,
        "messageType": 14,
        "messageTitle": "subject",
        "messageString": "message",
        "customerId": 144,
        "entityType": "layout",
        "entityId": 12,
        "entityPage": null,
        "sentOn": 1527155499000,
        "read": true // true if read, false otherwise
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

## Reports

---

#### Get devices by location for reports

---

    /device/reports [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    locationIds = 1,4,89 (optional)
    sort=ALPHABETICALLY

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {DeviceModel},
    {DeviceModel}
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Unscheduled media player report

---

    /reports/unscheduled-media-player [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {        // required
        "gte": "2018-12-13"
      },
      "to_date": {          // required
        "lte": "2019-02-05"
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "17,48,556,8989,44"
      }
    }
    sort={ // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "deviceGroup", "DESC",
      "locationName": "ASC",
      "date": "DESC",
      "emptySlotDuration": "DESC"
    }

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to reuse for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "deviceGroupId": 234,
        "deviceGroup": "cool group",
        "locationId": 154,
        "locationName": "Delhi",
        "date": "25/05/2018",
        "emptySlotStartTime": "13:00",
        "emptySlotEndTime": "13:15",
        "emptySlotDuration": "00:00:15"
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "deviceGroupId": 234,
        "deviceGroup": "cool group",
        "locationId": 154,
        "locationName": "Delhi",
        "date": "25/05/2018",
        "emptySlotStartTime": "13:00",
        "emptySlotEndTime": "16:00",
        "emptySlotDuration": "03:00:00"
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Get modules for user activity report

---

    /reports/user-activity/modules [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": [
    {
      "key": "CONTENT",  // values are for display in dropdown and keys for sending to server
      "name": "Content"
    },
    {
      "key": "TEMPLATE",
      "name": "Template"
    },
    {
      "key": "LAYOUT",
      "name": "Campaign"
    },
    {
      "key": "LAYOUT_STRING",
      "name": "Campaign String"
    },
    {
      "key": "PLANOGRAM",
      "name": "Planogram"
    },
    {
      "key": "DEVICE",
      "name": "Media Player"
    },
    {
      "key": "USER",
      "name": "User"
    },
    {
      "key": "ROLE",
      "name": "Role"
    },
    {
      "key": "OTHER",
      "name": "Others"
    },
    {
      "key": "DEVICE_GROUP",
      "name": "Device Group"
    },
    {
      "key": "DEVICE_TO_DEVICE_GROUP",
      "name": "Device to device group"
    },
    {
      "key": "LOCATION",
      "name": "Location"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### User activity report

---

    /reports/user-activity [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // required
        "lte": "2019-02-05"
      },
      "role": {              // optional
        "eq": 1
      },
      "user": {              // optional
        "eq": 12
      },
      "device": {            // optional
        "in": "16,34,6,3,7667"
      },
      "module": {            // optional
        "eq": "CONTENT"
      },
      "comments": {         // optional
        "lk": "hello"
      }
    }
    sort={ // only one parameter can be sorted at a time more than one are used here for demonstration only
      "userName": "ASC",
      "date": "ASC",
      "module": "ASC",
      "action", "DESC",
      "entityId": "ASC",
      "entityName": "DESC"
    }

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "data": [
      {
        "userId": 123,
        "userName": "Abc",
        "date": "25/05/2018",
        "time": "15:00",
        "module": "CONTENT",
        "moduleDisplayName": "Content",
        "action": "Added",
        "roleId": 1,
        "roleName": "PANASONIC_ADMIN",
        "comments": "Hello",
        "entityId": 12,
        "entityName": "Cool video",
        "internalIP": "192.168.0.91",
        "externalIP": "122.75.56.57"
      },
      {
        "userId": 123,
        "userName": "Abc",
        "date": "25/05/2018",
        "time": "15:00",
        "module": "CONTENT",
        "moduleDisplayName": "Content",
        "action": "Added",
        "roleId": 1,
        "roleName": "PANASONIC_ADMIN",
        "comments": "Hello",
        "entityId": 12,
        "entityName": "Cool video",
        "internalIP": "192.168.0.91",
        "externalIP": "122.75.56.57"
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Content download analytics start / end API version 1

---

Note: devices should call this API to inform server that their batch download has started or ended

    /analytics/content-download [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    NA

BODY

```json
[
  {
    clientGeneratedRandomIdForTheBatch: "jhi43f4309fj9034", // clients should generate this randomly so that when their batch download fails then sever can know. Better use UUID
    event: "START", // event enum values: START, END_SUCCESS, END_FAILURE, IN_PROGRESS
    contentIds: [345, 565, 768, 3],
    reasonForFailure: "", // string - send only if event = END_FAILURE
    percentageCompleteInCaseOfFailure: null, // send only if event = END_FAILURE
  },
  {
    clientGeneratedRandomIdForTheBatch: "jhi43f4309fj9034", // clients should generate this randomly so that when their batch download fails then sever can know. Better use UUID
    event: "END_SUCCESS", // event enum values: START, END_SUCCESS, END_FAILURE, IN_PROGRESS
    contentIds: [345, 565, 768, 3],
    reasonForFailure: "", // string - send only if event = END_FAILURE
    percentageCompleteInCaseOfFailure: null, // send only if event = END_FAILURE
  },
  {
    clientGeneratedRandomIdForTheBatch: "jhi43f4309fj9034", // clients should generate this randomly so that when their batch download fails then sever can know. Better use UUID
    event: "START", // event enum values: START, END_SUCCESS, END_FAILURE, IN_PROGRESS
    contentIds: [345, 565, 768, 3],
    reasonForFailure: "", // string - send only if event = END_FAILURE
    percentageCompleteInCaseOfFailure: null, // send only if event = END_FAILURE
  },
  {
    clientGeneratedRandomIdForTheBatch: "jhi43f4309fj9034", // clients should generate this randomly so that when their batch download fails then sever can know. Better use UUID
    event: "END_FAILURE", // event enum values: START, END_SUCCESS, END_FAILURE, IN_PROGRESS
    isLastBatchBeforeStoppingRetries: true, // or false
    allContentsWithProgress: [
      {
        contentId: 345,
        progressPercent: 12,
      },
      {
        contentId: 565,
        progressPercent: 100,
      },
      {
        contentId: 768,
        progressPercent: 100,
      },
      {
        contentId: 3,
        progressPercent: 50,
      },
    ],
    reasonForFailure: "Internet stopped working.", // string - send only if event = END_FAILURE
    percentageCompleteInCaseOfFailure: 23, // send only if event = END_FAILURE
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

#### Content download analytics start / end API version 2

---

Note: devices should call this API to inform server that their batch download has started or ended

    /analytics/content-download/v2 [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    NA

BODY

```json
[
  {
    clientGeneratedRandomIdForTheBatch: "jhi43f4309fj9034", // clients should generate this randomly so that when their batch download fails then sever can know. Better use UUID
    event: "START", // event enum values: START, END_SUCCESS, END_FAILURE, IN_PROGRESS
    contentIds: [345, 565, 768, 3],
    timestampOfEvent: 1630307495345,
    percentageCompleteInCaseOfFailure: null, // send only if event = END_FAILURE
  },
  {
    clientGeneratedRandomIdForTheBatch: "jhi43f4309fj9034", // clients should generate this randomly so that when their batch download fails then sever can know. Better use UUID
    event: "END_SUCCESS", // event enum values: START, END_SUCCESS, END_FAILURE, IN_PROGRESS
    contentIds: [345, 565, 768, 3],
    timestampOfEvent: 1630307495345,
    percentageCompleteInCaseOfFailure: null, // send only if event = END_FAILURE
  },
  {
    clientGeneratedRandomIdForTheBatch: "jhi43f4309fj9034", // clients should generate this randomly so that when their batch download fails then sever can know. Better use UUID
    event: "START", // event enum values: START, END_SUCCESS, END_FAILURE, IN_PROGRESS
    contentIds: [345, 565, 768, 3],
    percentageCompleteInCaseOfFailure: null, // send only if event = END_FAILURE
  },
  {
    clientGeneratedRandomIdForTheBatch: "jhi43f4309fj9034", // clients should generate this randomly so that when their batch download fails then sever can know. Better use UUID
    event: "END_FAILURE", // event enum values: START, END_SUCCESS, END_FAILURE, IN_PROGRESS
    isLastBatchBeforeStoppingRetries: true, // or false
    timestampOfEvent: 1630307495345,
    allContentsWithProgress: [
      {
        contentId: 345,
        progressPercent: 12,
        reasonForFailure: "test",
      },
      {
        contentId: 565,
        progressPercent: 100,
        reasonForFailure: "test",
      },
      {
        contentId: 768,
        progressPercent: 100,
        reasonForFailure: "test",
      },
      {
        contentId: 3,
        progressPercent: 50,
        reasonForFailure: "test",
      },
    ],
    percentageCompleteInCaseOfFailure: 23, // send only if event = END_FAILURE
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

#### Content download append content API

---

Note: devices should call this API to inform server that their batch download has started or ended

    /analytics/content-download/append [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    NA

BODY

```json
{
  "clientGeneratedRandomIdForTheBatch": "jhi43f4309fj9034",   // use the same batch that you want to append to
  "contentIds": [345,565,768,3]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

#### Intermediate download position API

---

    /analytics/content-download/progress [PUT]

Note:

1. devices should call this API to inform server how much their download has progressed
2. Call this API only when you get a push notification to do so (ID_CLIENT_START_SENDING_DOWNLOAD_PROGRESS = 23)
3. When you get push ID 24 (ID_CLIENT_STOP_SENDING_DOWNLOAD_PROGRESS = 23) then stop calling this API.
4. If you get push ID 23 and you have nothing to download or all your downloads are complete by then then also don't
   call this API

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    NA

BODY

```json
{
  "clientGeneratedRandomIdForTheBatch": "jhi43f4309fj9034",
  "event": "IN_PROGRESS",
  "internetSpeedInBitsPerSeconds": 10000000,      // example: 10 Mbps
  "contentProgress": [
    {
      "contentId": 12,
      "progressPercent": 18
    },
    {
      "contentId": 22,
      "progressPercent": 89
    },
    {
      "contentId": 546,
      "progressPercent": 56
    },
    {
      "contentId": 67,
      "progressPercent": 100
    },
    {
      "contentId": 5,
      "progressPercent": 0
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Record Saved Successfully"
}
```

#### Content playback analytics

---

    /analytics/content-playback/v2 [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    NA

BODY

```json
[
  {
    startTimeOfCampaign: 1587989797997, // unix epoch in milliseconds
    endTimeOfCampaign: 1587989797998, // unix epoch in milliseconds
    planogramId: 15,
    campaignId: 84,
    regionId: 243, // can be null in case of audio playlist
    contentId: 56,
    playbackStatus: "YES|NO",
    contentLastPlayed: 1587989797998, // unix epoch in milliseconds
    reasonForFailure: "",
    widgetType: null, // possible values: CLOCK, WEATHER, CALENDAR, ADVERTISEMENT, HDMI-IN
  },
  {
    startTimeOfCampaign: 1587989797997, // unix epoch in milliseconds
    endTimeOfCampaign: 1587989797998, // unix epoch in milliseconds
    planogramId: 15,
    campaignId: 84,
    regionId: 243, // can be null in case of audio playlist
    contentId: 56,
    playbackStatus: "YES|NO",
    contentLastPlayed: 1587989797998, // unix epoch in milliseconds
    reasonForFailure: "",
    widgetType: null, // possible values: CLOCK, WEATHER, CALENDAR, ADVERTISEMENT, HDMI-IN
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Content playback saved, minId = 2323, maxId = 5883"
}
```

#### Content playback analytics error

---

    /analytics/content-playback/error [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    NA

BODY

```json
[
  {
    startTimeOfFailure: 1587989797997, // unix epoch in milliseconds
    endTimeOfFailure: 1587989797998, // unix epoch in milliseconds
    reasonForFailure: "Media player down",
  },
  {
    startTimeOfFailure: 1587989797997, // unix epoch in milliseconds
    endTimeOfFailure: 1587989797998, // unix epoch in milliseconds
    reasonForFailure: "Tpa was playing",
  },
  {
    startTimeOfFailure: 1587989797997, // unix epoch in milliseconds
    endTimeOfFailure: 1587989797998, // unix epoch in milliseconds
    reasonForFailure: "Touch URL xyz was playing",
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Saved"
}
```

#### Content playback analytics error

---

    /analytics/tpa-playback [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {deviceToken}

QUERY

    NA

BODY

```json
[
  {
    tpAppId: 11,
    tpaStartTime: 1587989797997, // unix epoch in milliseconds
    tpaEndTime: 1587989797997, // unix epoch in milliseconds
  },
  {
    tpAppId: 11,
    tpaStartTime: 1587989797997, // unix epoch in milliseconds
    tpaEndTime: 1587989797997, // unix epoch in milliseconds
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Saved"
}
```

#### Listen to device and panel changes

---

    /device/listen [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "key": "START",   // possible values  "START". "STOP" is out of scope for now as we are going to auto stop in 30 mins
  "deviceIds": [1,55,887,23,7],
  "firebaseRegistrationId": "232394hfr83he03fh489fh394fh4"
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "All updates related to these devices will be pushed to you"
}
```

#### Media player On / Off logs

---

    /reports/device-on-off-logs [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "19,676,434,687"
      },
      "deviceStatus": {     // optional
        "eq": "ON"          // possible values "ON", OFF", "NOT_APPLICABLE", "DATA_NOT_AVAILABLE", "WEEK_OFF", "INACTIVE" and "DATA_DELETED"
      }
    }
    sort={ // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "locationName": "ASC",
      "date": "DESC",
      "duration": "ASC",
      "deviceStatus": "ASC"
    }

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "isReportSentOnEmail": false,  // or true
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "date": "25/05/2018",     // this will be common for many rows. look at the possibility of merging rows
        "locationId": 979,
        "locationName": "Vizag",
        "startTime": "16:00",
        "endTime": "16:09",
        "duration": "00:00:09",
        "deviceStatus": "OFF",    // possible values "ON", "OFF", "NOT_APPLICABLE", "DATA_NOT_AVAILABLE", "WEEK_OFF", "DATA_DELETED" and "INACTIVE"
        "reasonForOffOrDisconnection": "No power",   // if deviceStatus is ON then this value will be null
        "scheduledPlayerUpTime": "13 hours",   // this will be common for many rows
        "totalSummary": "Total On time = 10:00, Total Off time = 2:00, total week off time = 2:00, Total not applicable time = 11:00, Total data not available time = 3:00, total data deleted time = 2:00"
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "date": "25/05/2018",     // this will be common for many rows. look at the possibility of merging rows
        "locationId": 979,
        "locationName": "Vizag",
        "startTime": "16:00",
        "endTime": "16:09",
        "duration": "00:00:09",
        "deviceStatus": "OFF",     // possible values "ON", "OFF", "NOT_APPLICABLE", "DATA_NOT_AVAILABLE", "WEEK_OFF", "DATA_DELETED" and "INACTIVE"
        "reasonForOffOrDisconnection": "No power",   // if deviceStatus is ON then this value will be null
        "scheduledPlayerUpTime": "13 hours",   // this will be common for many rows
        "totalSummary": "Total On time = 10:00, Total Off time = 2:00, total week off time = 2:00, Total not applicable time = 11:00, Total data not available time = 3:00, total data deleted time = 2:00"
      }
    ]
  },
  "message": "Reason for sending report via email",
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Statistics

---

    /reports/statistics [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {          // required
        "gte": "2018-12-13"   // only future dates are allowed not past ones
      },
      "to_date": {            // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"   // only future dates are allowed not past ones
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "19,676,434,687"
      },
      "planogram": {     // optional
        "eq": 158
      }
    }
    sort={ // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "locationName": "ASC",
      "deviceGroupName": "ASC",
      "date": "DESC",
      "planogramName": "ASC",
      "campaignName": "ASC",
      "contentName":
    }

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "isReportCompleted": true, // or false - indicates if the full report is ready
    "isReportSentOnEmail": false, // or true
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 979,
        "locationName": "Vizag",
        "deviceGroupId": 548,
        "deviceGroupName": "hello",
        "date": "25/05/2018",     // this will be common for many rows. look at the possibility of merging rows
        "startTime": "16:00",
        "endTime": "16:09",
        "planogramId": 43,
        "planogramName": "cool",
        "layoutId": 166,
        "layoutName": "my layout",
        "contentId": 434,
        "contentName": "my video",
        "contentType": "VIDEO"
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 979,
        "locationName": "Vizag",
        "deviceGroupId": 548,
        "deviceGroupName": "hello",
        "date": "25/05/2018",     // this will be common for many rows. look at the possibility of merging rows
        "startTime": "16:00",
        "endTime": "16:09",
        "planogramId": 43,
        "planogramName": "cool",
        "layoutId": 166,
        "layoutName": "my layout",
        "contentId": 434,
        "contentName": "my video",
        "contentType": "VIDEO"
      }
    ]
  },
  "message": "Reason for sending report via email",
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Media player On / Off percentage

---

    /reports/device-on-off-percentage [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"   // only future dates are allowed not past ones
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"   // only future dates are allowed not past ones
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "19,676,434,687"
      }
    }
    sort={    // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "locationName": "DESC",
      "date": "ASC",
      "scheduledPlayerUpTime": "DESC"
    }

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "isReportSentOnEmail" : false, // or true
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 979,
        "locationName": "Vizag",
        "deviceGroupId": 23,
        "deviceGroupName": "Lobby",
        "date": "25/05/2018",     // this will be common for many rows. look at the possibility of merging rows
        "scheduledPlayerUpTime": "13 hours"   // this will be common for many rows
        "onHours": "16:00",
        "offHours": "16:09",
        "dataDeletedHours": "02:00",
        "notApplicableHours": "02:00",
        "dataNotAvailableHours": "02:00",
        "weekOffHours": "02:00",
        "inActiveHours": "11:00",
        "onPercentage": 10,
        "offPercentage": 10,
        "dataDeletedPercentage": 10,
        "notApplicablePercentage": 10,
        "dataNotAvailablePercentage": 10,
        "weekOffPercentage": 40,
        "inActivePercentage": 10
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 979,
        "locationName": "Vizag",
        "deviceGroupId": null,
        "deviceGroupName": null,
        "date": "25/05/2018",     // this will be common for many rows. look at the possibility of merging rows
        "scheduledPlayerUpTime": "13 hours"   // this will be common for many rows
        "onHours": "16:00",
        "offHours": "16:09",
        "dataDeletedHours": "02:00",
        "notApplicableHours": "02:00",
        "dataNotAvailableHours": "02:00",
        "weekOffHours": "02:00",
        "inActiveHours": "11:00",
        "onPercentage": 10,
        "offPercentage": 10,
        "dataDeletedPercentage": 10,
        "notApplicablePercentage": 10,
        "dataNotAvailablePercentage": 10,
        "weekOffPercentage": 40,
        "inActivePercentage": 10
      }
    ]
  },
  "message": "Reason for sending report via email",
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Current Download

---

    /reports/current-download [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}
    FirebasePushRegistrationId: dheidhe893hf94f893h4f804fh4

QUERY

    q={
      "device": {             // required
        "in": "19,676,434,687"
      }
    }

BODY

    NA

Note: there is limited data in this response. Actual data from files will come via push notification

RESPONSE - code - 200

```json
{
  "result": {
    "total" : {
      "totalNumberOfDevices": 25,
      "startTime": "15:30",        // initially will be null
      "estimatedEndTime": "19:00", // initially will be null
      "totalNumberOfFiles": 100,   // initially will be null
      "percentageProgress": 20     // initially will be null
    },
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 979,
        "locationName": "Vizag",
        "startTime": "15:30",         // initially will be null
        "estimatedEndTime":"16:30",   // initially will be null
        "numberOfFiles": 18,          // initially will be null
        "downloadSizeInMB": 156,      // initially will be null
        "percentageProgress": 65      // initially will be null
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 979,
        "locationName": "Vizag",
        "startTime": "15:30",         // initially will be null
        "estimatedEndTime":"16:30",   // initially will be null
        "numberOfFiles": 18,          // initially will be null
        "downloadSizeInMB": 156,      // initially will be null
        "percentageProgress": 65      // initially will be null
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Previous download report

---

    /reports/previous-download [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

BODY

    NA

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"   // only future dates are allowed not past ones
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"   // only future dates are allowed not past ones
      },
      "device": {           // optional
        "in": "19,676,434,687"
      }
    }
    sort={  // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "date": "DESC",
      "numberOfFilesDownloaded": "ASC",
      "downloadSizeInBytes": "DESC",
      "downloadStatusInPercentage": "ASC"
    }

BODY

    N/A

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "date": "25/05/2018",     // this will be common for many rows. look at the possibility of merging rows
        "downloadStartTime": "15:30",
        "downloadEndTime": "23:30",
        "numberOfFilesDownloaded": 15,
        "downloadSizeInBytes": 1564564,
        "downloadStatusInPercentage": 59,
        "contents": [
          {
            "contentId": 12,
            "contentName": "Hello file",
            "progressPercent": 18
          },
          {
            "contentId": 18,
            "contentName": "Hello file",
            "progressPercent": 0
          },
          {
            "contentId": 16,
            "contentName": "Hello file",
            "progressPercent": 100
          }
        ]
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "date": "25/05/2018",     // this will be common for many rows. look at the possibility of merging rows
        "downloadStartTime": "15:30",
        "downloadEndTime": "23:30",
        "numberOfFilesDownloaded": 15,
        "downloadSizeInBytes": 1564564,
        "downloadStatusInPercentage": 59,
        "contents": [
          {
            "contentId": 12,
            "contentName": "Hello file",
            "progressPercent": 18
          },
          {
            "contentId": 18,
            "contentName": "Hello file",
            "progressPercent": 0
          },
          {
            "contentId": 16,
            "contentName": "Hello file",
            "progressPercent": 100
          }
        ]
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Bandwidth report (For panasonic users only)

---

    /reports/all-customer-bandwidth [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "bandwidthInBytes": {         // optional // only one operator will be sent. More are added here for demonstration only
        "eq": 112,
        "lt": 112,
        "gt": 112,
        "lte": 112,
        "gte": 112
      }
    }
    sort={  // only one parameter can be sorted at a time more than one are used here for demonstration only
      "customerName": "ASC",
      "bandwidthConsumed": "ASC",
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "data": [
      {
        "customerId": 123,
        "customerName": "Abc",
        "startDate": "25/05/2018",
        "endDate": "28/05/2018",
        "bandwidthConsumed": "15 GB",
        "perDay": [
          {
            "customerId": 123,
            "customerName": "Abc",
            "date": "25/05/2018",
            "bandwidthConsumed": "15 GB"
          },
          {
            "customerId": 123,
            "customerName": "Abc",
            "date": "26/05/2018",
            "bandwidthConsumed": "15 GB"
          }
        ]
      },
      {
        "customerId": 45,
        "customerName": "Xyz",
        "startDate": "25/05/2018",
        "endDate": "28/05/2018",
        "bandwidthConsumed": "15 GB",
        "perDay": [
          {
            "customerId": 45,
            "customerName": "Xyz",
            "date": "25/05/2018",
            "bandwidthConsumed": "15 GB"
          },
          {
            "customerId": 45,
            "customerName": "Xyz",
            "date": "26/05/2018",
            "bandwidthConsumed": "15 GB"
          }
        ]
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Bandwidth report per customer

---

    /reports/customer-bandwidth [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "bandwidthInBytes": {         // optional // only one operator will be sent. More are added here for demonstration only
        "eq": 112,
        "lt": 112,
        "gt": 112,
        "lte": 112,
        "gte": 112
      },
      "device": {         // optional
        "in": "12,58"
      }
    }
    sort={  // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "date": "DESC",
      "bandwidthConsumed": "ASC"
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "totalBandwidthConsumed": "20 GB"
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "startDate": "25/05/2018",
        "endDate": "26/05/2018",
        "bandwidthConsumed": "15 GB",
        "perDay": [
          {
            "deviceId": 123,
            "deviceName": "Abc",
            "date": "25/05/2018",
            "bandwidthConsumed": "15 GB"
          },
          {
            "deviceId": 123,
            "deviceName": "Abc",
            "date": "26/05/2018",
            "bandwidthConsumed": "15 GB"
          }
        ]
      },
      {
        "deviceId": 45,
        "deviceName": "Xyz",
        "startDate": "25/05/2018",
        "endDate": "26/05/2018",
        "bandwidthConsumed": "15 GB",
        "perDay": [
          {
            "deviceId": 45,
            "deviceName": "Xyz",
            "date": "25/05/2018",
            "bandwidthConsumed": "15 GB"
          },
          {
            "deviceId": 45,
            "deviceName": "Xyz",
            "date": "26/05/2018",
            "bandwidthConsumed": "15 GB"
          }
        ]
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Panel on off report

---

    /reports/panel-on-off [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "19,676,434,687"
      },
      "panelId": {         // optional
        "eq": 13
      },
      "panelStatus": {     // optional
        "eq": "ON"         // possible values ON, OFF, DISCONNECTED, WEEK_OFF, DATA_DELETED, NOT_APPLICABLE, DATA_NOT_AVAILABLE and INACTIVE
      },
      "panelIp": {     // optional
        "eq": "192.168.30.222"
      }
    }
    sort={  // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "locationName": "DESC",
      "date": "DESC",
      "duration": "ASC",
      "panelName": "ASC",
      "panelStatus": "ASC"
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "isReportSentOnEmail" : false, // or true
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 23,
        "locationName": "Noida",
        "date": "25/05/2018",
        "scheduledPanelUpTime": "00:10:00",
        "startTime": "15:30",
        "endTime": "23:30",
        "duration": "00:00:50",
        "panelId": 13,
        "panelName": "Abc",
        "panelIp": "192.168.0.56",  // null if no IP
        "panelStatus": "DISCONNECTED", // possible values ON, OFF, DISCONNECTED, WEEK_OFF, DATA_DELETED, NOT_APPLICABLE, DATA_NOT_AVAILABLE and INACTIVE
        "reasonForFailure": [
          "XXXXXX",
          "YYYYYY"
        ],
        "totalSummary" : ""
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 23,
        "locationName": "Noida",
        "date": "25/05/2018",
        "scheduledPanelUpTime": "00:10:00",
        "startTime": "15:30",
        "endTime": "23:30",
        "duration": "00:00:50",
        "panelId": 13,
        "panelName": "Abc",
        "panelIp": "192.168.0.56",  // null if no IP
        "panelStatus": "ON",        // possible values ON, OFF, DISCONNECTED, WEEK_OFF, DATA_DELETED, NOT_APPLICABLE, DATA_NOT_AVAILABLE and INACTIVE
        "reasonForFailure": null,
        "totalSummary" : ""
      }
    ]
  },
  "message": "Reason for sending report via email",
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Panel On-Off Percentage

---

    /reports/panel-on-off-percentage [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "19,676,434,687"
      },
      "panelId": {         // optional
        "eq": 13
      },
      "panelIp": {     // optional
        "eq": "192.168.30.222"
      }
    }
    sort={  // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "locationName": "DESC",
      "scheduledPanelUpTime": "DESC",
      "panelName": "ASC",
      "panelIp": "ASC",
      "onHrs": "ASC",
      "offHrs": "DESC",
      "dataDeletedHrs": "DESC",
      "notApplicableHrs": "DESC",
      "dataNotAvailableHrs": "ASC",
      "weekOffHrs": "DESC",
      "onPercentage": "DESC",
      "offPercentage": "ASC",
      "dataDeletedPercentage": "ASC",
      "notApplicablePercentage": "ASC",
      "dataNotAvailablePercentage": "ASC",
      "weekOffPercentage": "DESC",
      "diconnectedPercentage": "ASC"
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "isReportSentOnEmail" : false, // or true
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 18,
        "locationName": "Noida",
        "date": "21/12/2018",
        "scheduledPanelUpTime": "12:00:00",
        "panelId": 23,
        "panelName": "Hello",
        "panelIp": "192.168.0.91",
        "onHrs": "18:00:15",
        "offHrs": "12:22:00",
        "dataDeletedHrs": "02:00",
        "notApplicableHrs": "02:00",
        "dataNotAvailableHrs": "02:00",
        "weekOffHrs": "02:00",
        "inActiveHrs": "01:00",
        "disconnectedHrs": "01:00",
        "onPercentage": 10,
        "offPercentage": 10,
        "dataDeletedPercentage": 10,
        "notApplicablePercentage": 10,
        "dataNotAvailablePercentage": 10,
        "weekOffPercentage": 40,
        "inActivePercentage": 10,
        "diconnectedPercentage": 10
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 18,
        "locationName": "Noida",
        "date": "21/12/2018",
        "scheduledPanelUpTime": "12:00:00",
        "panelId": 23,
        "panelName": "Hello",
        "panelIp": "192.168.0.91",
        "onHrs": "18:00:15",
        "offHrs": "12:22:00",
        "dataDeletedHrs": "02:00",
        "notApplicableHrs": "02:00",
        "dataNotAvailableHrs": "02:00",
        "weekOffHrs": "02:00",
        "inActiveHrs": "01:00",
        "disconnectedHrs": "01:00",
        "onPercentage": 10,
        "offPercentage": 10,
        "dataDeletedPercentage": 10,
        "notApplicablePercentage": 10,
        "dataNotAvailablePercentage": 10,
        "weekOffPercentage": 50,
        "inActivePercentage": 10,
        "diconnectedPercentage": 10
      }
    ]
  },
  "message": "Reason for sending report via email",
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Content playback report (actuals)

---

    /reports/content-playback [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "19,676,434,687"
      },
      "planogramId": {      // optional
        "eq": 13
      },
      "isPlayed": {         // optional (refers to playback status in the report, if Yes then true if No then false)
        "eq": true
      },
      "deviceGroupId": {    // optional
        "eq": 178
      },
      "layoutId": {         // optional
        "eq": 556
      }
    }
    sort={  // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "locationName": "DESC",
      "deviceGroupName": "ASC",
      "date": "ASC",
      "planogramName": "ASC",
      "planogramId": "ASC",
      "campaignName": "DESC",
      "contentName": "DESC",
      "contentType": "ASC",
      "isPlayed": "ASC"                // refers to playback status in report if asc then send false ones first and if desc then send true ones first
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "isReportSentOnEmail": false, // or true
    "data": [
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 18,
        "locationName": "Noida",
        "deviceGroupId": 23,
        "deviceGroupName": "world",
        "date": "21/12/2018",
        "startTime": "18:00:15",
        "endTime": "12:22:00",
        "planogramId": 13,
        "planogramName": "cool plano",
        "campaignId": 323,
        "campaignName": "cool campaign",
        "regionId": 221,      // can be null in case of audio playlist
        "regionName": 212,  // can be null in case of audio playlist
        "contentId": 23,
        "contentName": "my video",
        "contentType": "VIDEO",
        "panelIp": "192.168.0.91",
        "isPlayed": false,               // refers to playback status in report
        "contentLastPlayed": "15:00:15",
        "reasonForFailure": "Hard drive space was full",
        "snapshotStartTime": null
        "snapshotEndTime": null
      },
      {
        "deviceId": 123,
        "deviceName": "Abc",
        "locationId": 18,
        "locationName": "Noida",
        "deviceGroupId": 23,
        "deviceGroupName": "world",
        "date": "21/12/2018",
        "startTime": "18:00:15",
        "endTime": "12:22:00",
        "planogramId": 13,
        "planogramName": "cool plano",
        "campaignId": 323,
        "campaignName": "cool campaign",
        "regionId": 221,      // can be null in case of audio playlist
        "regionName": 212,  // can be null in case of audio playlist
        "contentId": 23,
        "contentName": "my video",
        "contentType": "VIDEO",
        "panelIp": "192.168.0.91",
        "isPlayed": true,               // refers to playback status in report
        "contentLastPlayed": "16:00:15",
        "reasonForFailure": null,
        "snapshotStartTime": 189213912839
        "snapshotEndTime": 123821322912
      }
    ]
  },
  "message": "Reason for sending report via email",
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Planogram expected report

---

    /reports/planogram-expected [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "19,676,434,687"
      },
      "state": {         // optional
        "eq": "DRAFT"     // possible values (all states)
      },
      "createdBy": {      // optional (refers to playback status in the report, if Yes then true if No then false)
        "eq": 23        // user Id
      },
      "approvedBy": {       // optional
        "eq": 45           // user Id
      },
      "deviceGroup": {     // optional
        "eq": "cafeteria"
      }
    }
    sort={  // only one parameter can be sorted at a time more than one are used here for demonstration only
      "planogramName": "ASC",
      "planogramId": "ASC",
      "createdByName": "ASC",
      "startDate": "ASC",
      "endDate": "DESC",
      "duration": "ASC",
      "state": "ASC",
      "approvedByName": "ASC"
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "data": [
      {
        "planogramId": 13,
        "planogramName": "cool plano",
        "createdById": 23,
        "createdByName": "hello world",
        "startDate": "23/08/2018",
        "endDate": "14/11/2018",
        "startTime": "18:00:15",
        "endTime": "12:22:00",
        "duration": "15:00:18",
        "state": "APPROVED",
        "approvedById": 23,
        "approvedByName": "hello world"
      },
      {
        "planogramId": 13,
        "planogramName": "cool plano",
        "createdById": 23,
        "createdByName": "hello world",
        "startDate": "23/08/2018",
        "endDate": "14/11/2018",
        "startTime": "18:00:15",
        "endTime": "12:22:00",
        "duration": "15:00:18",
        "state": "APPROVED",
        "approvedById": 23,
        "approvedByName": "hello world"
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Get user API for advance search filter

---

    /user-filter-for-advance-search [GET]

Note:

1. customer id is required in header
2. returns all Pan admins, all cust rep associated with that customer and all users of that customer
3. user object will have only 2 values: userId and fullName

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    role-id=1  // optional and only one role Id at at time

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "userId": 1,
      "fullName": "Abc"
    },
    {
      "userId": 2,
      "fullName": "xyz"
    }
  ],
  "message": null,
  "code": null,
  "name": null
}
```

## Dashboard APIs

---

#### User dashboard

---

    /dashboard/user [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "recentAddedContents": [{ContentModel}],  // same as /dashboard/recent-contents [GET]
    "recentlyAddedLayouts": [{LayoutModel}],  // same as /dashboard/recent-layouts [GET]
    "recentlyAddedLayoutStrings": [{LayoutStringModel}],   // same as /dashboard/recent-layout-strings [GET]
    "recentlyAddedPlanograms": [{PlanogramModel}],   // same as /dashboard/recent-planograms [GET]
    "recentlyRegisteredDevices": [{DeviceModel}],   // same as /dashboard/recent-devices [GET]
    "recentlyInactiveDevices": [{DeviceModel}],   // same as /dashboard/recent-inactive-devices [GET]
    "showPendingForApprovalLayoutsLink": true,
    "showPendingForApprovalLayoutStringLink": true,
    "showPendingForApprovalPlanogramLink": false,
    "layoutsPendingForApprovalCount": 1,
    "layoutStringsPendingForApprovalCount": 1,
    "planogramsPendingForApprovalCount": 1
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Panasonic dashboard

---

    /dashboard/panasonic [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "activeCustomers": 10,
    "inactiveCustomers": 20,
    "totalCustomers": 30,
    "recentlyAddedCustomers": [{CustomerModel}] // same as /dashboard/recent-customers [GET]
    "perCustomerBandwidthConsumption": []  // same array as in /dashboard/panasonic-per-customer [GET]
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Panasonic dashboard per customer

---

    /dashboard/panasonic-per-customer [GET]

Note: send last day's bandwidth consumption

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "bandwidthUnit": "MB",
    "storageUnit": "MB",
    "customers": [
      {
        "customerId": 15,
        "custName": "Hello",
        "bandwidthUsedByThisCustomer": 15,
        "storageUsedByByThisCustomers": 18
      },
      {
        "customerId": 16,
        "custName": "Hello",
        "bandwidthUsedByThisCustomer": 15,
        "storageUsedByByThisCustomers": 18
      },
      {
        "customerId": 17,
        "custName": "Hello",
        "bandwidthUsedByThisCustomer": 15,
        "storageUsedByByThisCustomers": 18
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Customer Admin dashboard

---

    /dashboard/customer [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "consumedLicences": 84,
    "remainingLicences": 16,
    "totalLicences": 100,
    "activeSites": 19,
    "totalSites": 20,
    "lastFewDaysPanelStatus": [    // same as /dashboard/last-few-days-panel this will contain last 6 days array
      {
        "dateString": "6-Dec",   //this is a presentable data directly on UI. This should not be converted or transformed
        "panelOnPercentage": 95,
        "panelOffPercentage": 3
      },
      {
        "dateString": "7-Dec",   //this is a presentable data directly on UI. This should not be converted or transformed
        "panelOnPercentage": 95,
        "panelOffPercentage": 5
      },
      {
        "dateString": "8-Dec",   //this is a presentable data directly on UI. This should not be converted or transformed
        "panelOnPercentage": 95,
        "panelOffPercentage": 5
      }
    ],
    "planogramPublishedButNotRunning": [{PlanogramModel}],   // same as /dashboard/recent-published-planograms-but-not-running [GET]
    "planogramPublishedAndPlayingRightNow": [{PlanogramModel}],  // /dashboard/recent-published-planograms-and-playing-right-now [GET]
    "deviceOnOffStatus": [   //  same as /dashboard/last-few-days-device
      {
        "dateString": "18-Dec",
        "deviceOnPercentage": 95,
        "deviceOffPercentage": 5
      },
      {
        "dateString": "17-Dec",
        "deviceOnPercentage": 95,
        "deviceOffPercentage": 5
      },
      {
        "dateString": "19-Dec",
        "deviceOnPercentage": 95,
        "deviceOffPercentage": 5
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently added planograms

---

    /dashboard/recent-planograms [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {PlanogramModel}  // all planogram data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently added contents

---

    /dashboard/recent-contents [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {ContentModel}  // all content data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently added layouts

---

    /dashboard/recent-layouts [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {LayoutModel}  // all layout data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently added layout strings

---

    /dashboard/recent-layout-strings [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {LayoutStringModel}  // all layoutString data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently added devices

---

    /dashboard/recent-devices [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {DeviceModel}  // all device data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently inactive devices

---

    /dashboard/recent-inactive-devices [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {DeviceModel}  // all device data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently added customers

---

    /dashboard/recent-customers [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {CustomerModel}  // all device data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recent planogram published but not running

---

    /dashboard/recent-published-planograms-but-not-running [GET]

Note for `server: where planogram state = published AND startDate > System.currentMillis();`

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {PlanogramModel}  // all planogram data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recent planogram published and playing right now

---

    /dashboard/recent-published-planograms-and-playing-right-now [GET]

Note for server: where planogram state = published AND playing System.currentMillis() lies in the planogram's running
time frame

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {PlanogramModel}  // all planogram data may not be present in this
  ],
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently approved and rejected layouts

---

    /dashboard/recent-approved-rejected-layouts [GET]

Note: limit the result to 25 items

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  {
    "result": [
      {
        "layoutName": "camp1",
        "state": "APPROVED"
      },
      {
        "layoutName": "Camp2",
        "state": "REJECTED"
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently approved and rejected layout strings

---

    /dashboard/recent-approved-rejected-layout-strings [GET]

Note: limit the result to 25 items

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  {
    "result": [
      {
        "layoutStringName": "cs1",
        "state": "APPROVED"
      },
      {
        "layoutStringName": "cs2",
        "state": "REJECTED"
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Recently approved and rejected planograms

---

    /dashboard/recent-approved-rejected-planograms [GET]

Note: limit the result to 25 items

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  {
    "result": [
      {
        "title": "camp1",
        "state": "APPROVED"
      },
      {
        "title": "Camp2",
        "state": "REJECTED"
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Get Country code API

---

    /country-code [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "code": "+93",
      "name": "Afghanistan"
    },
    {
      "code": "+355",
      "name": "Albania"
    },
    {
      "code": "+213",
      "name": "Algeria"
    },
    {
      "code": "+263",
      "name": "Zimbabwe"
    }
  ],
  "pagination": null,
  "name": null,
  "code": null,
  "message": null
}
```

#### Customer default planogram landscape image upload

---

    /customer/default-planogram-image [POST]

HEADER

    Content-Type: text/plain; charset="utf-8"
    Authorization:Bearer {token}

BODY

    data:image/png;base64,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

Note: this is base64 image as a string

RESPONSE - code - 200

```json
{
  "result": "saved successfully",
  "message": null,
  "code": null,
  "name": null
}
```

#### Customer default planogram landscape image upload

---

    /customer/default-planogram-image/v2 [POST]

HEADER

    Authorization:Bearer {token}

Multipart BODY

    key = `file` for actual file

RESPONSE - code - 200

```json
{
  "result": "saved successfully",
  "message": null,
  "code": null,
  "name": null
}
```

#### Customer default planogram landscape image download

---

    /customer/v2/default-planogram-image?download=true [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    download = true // true if images needs to be downloaded by default, false if image needs to be rendered on browser

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "url": "http://downloadurlofimage.com/abc.png",
    "currentImageVersion": 5
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Customer default planogram portrait image upload

---

    /customer/default-planogram-portrait-image [POST]

HEADER

    Authorization:Bearer {token}

Multipart BODY

    key = `file` for actual file

RESPONSE - code - 200

```json
{
  "result": "saved successfully",
  "message": null,
  "code": null,
  "name": null
}
```

#### Customer default planogram portrait image upload

---

    /customer/default-planogram-portrait-image/v2 [POST]

HEADER

    Content-Type: text/plain; charset="utf-8"
    Authorization:Bearer {token}

BODY

    data:image/png;base64,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

Note: this is base64 image as a string

RESPONSE - code - 200

```json
{
  "result": "saved successfully",
  "message": null,
  "code": null,
  "name": null
}
```

#### Customer default planogram portrait image download

---

    /customer/v2/default-planogram-portrait-image?download=true [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    download = true // true if images needs to be downloaded by default, false if image needs to be rendered on browser

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "url": "http://downloadurlofimage.com/abc.png",
    "currentImageVersion": 5
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Delete customer default planogram portrait image

---

    /customer/default-planogram-portrait-image [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": "Deleted image successfully",
  "message": null,
  "code": null,
  "name": null
}
```

#### Delete customer default planogram landscape image

---

    /customer/default-planogram-image [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": "Deleted image successfully",
  "message": null,
  "code": null,
  "name": null
}
```

#### Customer config:

---

    /customer-config [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}
    customerId: 15   // required

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "encryptedTwitterSDKClientKey": "958",     // will be null if not set
    "encryptedTwitterSDKConsumerKey": "9jg",   // will be null if not set
    "encryptedFacebookAppId": "340",           // will be null if not set
    "encryptedFacebookSecret": "504",          // will be null if not set
    "planogramPublishingTime":48,              //in Hours
    "dailyDMBReportToEmail": "hello@world.com, world@hello.com", // in case of more than one email use comma to split the emails.
    "dailyDMBReportCCEmail": "cc@world.com, cc@hello.com" // in case of more than one email use comma to split the emails.
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Customer update config:

---

    /customer-config [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}
    customerId: 15   // required

QUERY

    NA

BODY

```json
{
  "encryptedTwitterSDKClientKey": "ewiofj34f454hg034g4305hgh3409g5h349g5h34g59h34509h3409g534958", // send null or do not send if you do not want to update the value. Send empty string if you want to delete the existing key.
  "encryptedTwitterSDKConsumerKey": "2r34r409tu3409gu39j340jg0349jg", // send null or do not send if you do not want to update the value. Send empty string if you want to delete the existing key.
  "encryptedFacebookAppId": "23302r340fj439fh340", // send null or do not send if you do not want to update the value. Send empty string if you want to delete the existing key.
  "encryptedFacebookSecret": "rti3jf09r3jv03jv904vj504", // send null or do not send if you do not want to update the value. Send empty string if you want to delete the existing key.
  "planogramPublishingTime": 48, //in Hours // send null or do not send if you do not want to update the value. Cannot be deleted as it is a required item.
  "dailyDMBReportToEmail": "hello@world.com, world@hello.com", // in case of more than one email use comma to split the emails. To delete send it as empty string to server
  "dailyDMBReportCCEmail": "cc@world.com, cc@hello.com" // in case of more than one email use comma to split the emails. To delete send it as empty string to server
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "encryptedTwitterSDKClientKey": "958", // last 3 chars
    "encryptedTwitterSDKConsumerKey": "9jg", // last 3 chars
    "encryptedFacebookAppId": "340", // last 3 chars
    "encryptedFacebookSecret": "504" , // last 3 chars
    "planogramPublishingTime":48, //in Hours
    "dailyDMBReportToEmail": "hello@world.com, world@hello.com",
    "dailyDMBReportCCEmail": "cc@world.com, cc@hello.com"
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Localization

---

    /localization [GET]

HEADER

    NA

QUERY

    language=en  // required

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "ADD": "ADD",
    "FACEBOOK": "FACEBOOK",
    "LOCATIONS": "LOCATIONS",
    "VIDEO": "VIDEO",
    "TEXT": "TEXT",
    "HTML": "HTML",
    "ON_HDMI_DISCONNECTED": "ON HDMI DISCONNECTED",
    "VIEW": "VIEW",
    "DASH": "DASHBOARD",
    "AUDIO": "AUDIO",
    "CUSTOMER_SETUP": "CUSTOMER SETUP",
    "ON_HDMI_CONNECTED": "ON HDMI CONNECTED",
    "DEVICE_GROUPS": "MEDIA PLAYER GROUPS",
    "IMAGE": "IMAGE",
    "REPORTS": "REPORTS",
    "DEVICE_ONBOARD": "DEVICE ONBOARD",
    "CUSTOMER": "CUSTOMER",
    "DOC": "DOC",
    "TEMPLATE": "TEMPLATE",
    "DEVICE_GROUP": "DEVICE GROUP",
    "OFF_HDMI_CONNECTED": "OFF HDMI CONNECTED",
    "Device": "Device",
    "DEVICE": "DEVICE",
    "CMS": "CMS",
    "EDIT": "EDIT",
    "APPROVE": "APPROVE",
    "FLASH": "FLASH",
    "RSS": "RSS",
    "LOCATION": "LOCATION",
    "DEVICE_SETTING": "DEVICE SETTING",
    "LAYOUT": "CAMPAIGN",
    "DOWNLOAD": "DOWNLOAD",
    "OFF_HDMI_DISCONNECTED": "OFF HDMI DISCONNECTED",
    "URL": "URL",
    "ROLE": "ROLE",
    "DELETE": "DELETE",
    "PDF": "PDF",
    "PPT": "PPT",
    "PLANOGRAM": "PLANOGRAM",
    "CONTENT": "MEDIA",
    "DISCONNECTED_HDMI_DISCONNECTED": "DISCONNECTED HDMI DISCONNECTED",
    "DATA_NOT_AVAILABLE": "DATA NOT AVAILABLE",
    "Others": "Others",
    "LAYOUT_GROUP": "CAMPAIGN STRING",
    "USER": "USER",
    "Admin": "Admin",
    "DISCONNECTED_HDMI_CONNECTED": "DISCONNECTED HDMI CONNECTED",
    "DEVICES": "MEDIA PLAYER",
    "LOCATIONS_AND_DEVICE_GROUPS": "LOCATIONS AND MEDIA PLAYER GROUPS",
    "TWITTER": "TWITTER",
    "RS232_RM_HDMI_ONLY": "RS-232 - RM / HDMI only",
    "PANEL_OTHERS": "Others",
    "RJ45": "RJ-45"
  },
  "pagination": null,
  "name": "TranslateString",
  "code": 1,
  "message": "Translate en String."
}
```

## Immediate snapshot

---

#### Request for a current snapshot if a device

---

    /device-snapshots/now/push [POST]

HEADER

    Authorization: Bearer {userToken} // user token

QUERY

    NA

BODY

```json
{
  "deviceId": 23,
  "firebaseRegistrationId": "232394hfr83he03fh489fh394fh4"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "deviceId": 23,
    "uniqueRequestId": "3cc531b3-39d1-4df0-95ee-2f141d9c1b6f"
  },
  "name": null,
  "code": 200,
  "message": "We will send you a push when image is uploaded by the device"
}
```

#### Upload a snapshot of current timestamp

---

    /device-snapshots/now/upload [POST]

HEADER

    Authorization: Bearer {deviceToken} // device token not user token

QUERY

    NA

BODY

    Multipart key: snapshot=image file
    Multipart key: timestamp=23902840123123 // unix epoch of the time of taking the snapshot
    Multipart key: uniqueRequestId=3cc531b3-39d1-4df0-95ee-2f141d9c1b6f

RESPONSE - code - 200

```json
{
  "result": "Uploaded image successfully",
  "name": null,
  "code": 200,
  "message": "Uploaded image successfully"
}
```

#### Upload a snapshot of current timestamp

---

    /device-snapshots/now/error   [POST]

HEADER

    Authorization: Bearer {deviceToken} // device token not user token

QUERY

    NA

BODY

```json
{
  "uniqueRequestId" : "3cc531b3-39d1-4df0-95ee-2f141d9c1b6f",
  "errorCode" : "AD_PLAYING",  // possible values AD_PLAYING, TPA_PLAYING, HDMI_NOT_CONNECTED

  /*
    possible values for reason:
    "Cannot upload image as advertisement app is playing"
    "Cannot upload image as third party app, {tpaName}, is playing"
    "Cannot upload image as HDMI is not connected"
  */
  "reason": "Cannot upload image as advertisement app is playing"
}
```

RESPONSE - code - 200

```json
{
  "result": "Uploaded reason successfully",
  "name": null,
  "code": 200,
  "message": "Uploaded reason successfully"
}
```

#### Download current image snapshot

---

    /device-snapshots/now/<uniqueRequestId> [GET]

Note: `uniqueRequestId` is same as the one sent in the response of `/device-snapshots/now/push [POST]`

HEADER

    Authorization: Bearer {userToken} // user token

QUERY

    NA

BODY

    image file

#### Fetch these files on local server

---

Note: this is API is only available for device from local server but not main server

    /fetch-files [GET]

HEADER

    Authorization: Bearer {deviceToken} // device token not user token

QUERY

    content-ids=1,45,7,898
    unique-batch-id=sudih94h834f9h9h
    clientGeneratedDeviceIdentifier=ca3c2e13-e049-46ec-b3e7-20ad2f44b95b
    deviceId=12

BODY

```json
{
  "result": null,
  "name": null,
  "code": 1,
  "message": "Fetching files. You will be notified files are downloaded."
}
```

#### Get all planogram titles of a customer regardless of they are expired or not

---

    /planogram/all [GET]

HEADER

    Authorization: Bearer {userToken} // user token

QUERY

    NA

BODY

```json
{
  "result": [
    {
      "planogramId": 12,
      "title": "Hello world"
    }
  ],
  "name": null,
  "code": 1,
  "message": "Records fetched"
}
```

#### Can send password reset email to this PoC user?

---

    /poc-can-send-email [GET]

HEADER

    Authorization: Bearer {userToken} // user token
    customerId: 34

QUERY

    email=abc@gmail.com // required

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "canSendPasswordResetEmail": true,
    "status": 1 // status of the user if the user exists and in the customer otherwise null
  },
  "name": null,
  "code": 20,
  "message": null
}
```

#### Fetch Panasonic config

---

    /pan-config [GET]

HEADER

    Authorization: Bearer {userToken} // user token

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "firebaseWebPushTimeToLiveInSeconds": 19,
    "timeToRedirectUserOnTimeoutInSeconds": 65,
    "revertToDisconnectedInMinutes": 8,
    "sessionExpiringPopInterval": 600,  // in seconds
    "deviceDetailRefreshInterval":1800, // in seconds
    "timeOutSpecificForSomeApis": [
      {
        "id": 1,
        "urlname": "/content/file",
        "timeOutTimeInSec": 5,
        "method": "POST"
      },
      {
        "id": 2,
        "urlname": "/content/{encryptedCustomerId}/{contentId}",
        "timeOutTimeInSec": 5,
        "method": "GET"
      },
      {
        "id": 3,
        "urlname": "/reports/panel-on-off",
        "timeOutTimeInSec": 5,
        "method": "GET"
      },
      {
        "id": 4,
        "urlname": "/customer/default-planogram-image",
        "timeOutTimeInSec": 5,
        "method": "GET"
      },
      {
        "id": 5,
        "urlname": "/customer/default-planogram-portrait-image",
        "timeOutTimeInSec": 5,
        "method": "GET"
      },
      {
        "id": 6,
        "urlname": "/customer/default-planogram-image",
        "timeOutTimeInSec": 5,
        "method": "POST"
      },
      {
        "id": 7,
        "urlname": "/customer/default-planogram-portrait-image",
        "timeOutTimeInSec": 5,
        "method": "POST"
      },
      {
        "id": 8,
        "urlname": "/reports/panel-on-off-percentage",
        "timeOutTimeInSec": 5,
        "method": "GET"
      },
      {
        "id": 9,
        "urlname": "/reports/unscheduled-media-player",
        "timeOutTimeInSec": 5,
        "method": "GET"
      },
      {
        "id": 10,
        "urlname": "/reports/device-on-off-logs",
        "timeOutTimeInSec": 5,
        "method": "GET"
      },
      {
        "id": 11,
        "urlname": "/reports/device-on-off-percentage",
        "timeOutTimeInSec": 5,
        "method": "GET"
      },
      {
        "id": 12,
        "urlname": "/offline-content-file/{encryptedCustomerId}/{offlineDownloadId}",
        "timeOutTimeInSec": 900,
        "method": "GET"
      }
    ]
  },
  "pagination": null,
  "name": null,
  "code": 20,
  "message": "Pan config fetch successfully"
}
```

#### Update Panasonic config

---

    /pan-config [PUT]

HEADER

    Authorization: Bearer {userToken} // user token

QUERY

    NA

BODY

```json
{
  "firebaseWebPushTimeToLiveInSeconds" : 15,  // send null or do not send if you do not want to update the value. Cannot be deleted as it is a required item.
  "timeToRedirectUserOnTimeoutInSeconds": 60, // send null or do not send if you do not want to update the value. Cannot be deleted as it is a required item.
  "revertToDisconnectedInMinutes": 2, // send null or do not send if you do not want to update the value. Cannot be deleted as it is a required item.
  "sessionExpiringPopInterval": 600,  // in seconds // send null or do not send if you do not want to update the value. Cannot be deleted as it is a required item.
  "deviceDetailRefreshInterval": 1800 // in seconds // send null or do not send if you do not want to update the value. Cannot be deleted as it is a required item.
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "firebaseWebPushTimeToLiveInSeconds" : 15,
    "timeToRedirectUserOnTimeoutInSeconds": 60,
    "revertToDisconnectedInMinutes": 2,
    "sessionExpiringPopInterval": 600,  // in seconds
    "deviceDetailRefreshInterval": 1800 // in seconds
     },
  "name": null,
  "code": 20,
  "message": null
}
```

#### Facebook Access Token for devices

---

    /facebook/access-token [POST]

HEADER

    Authorization: Bearer {deviceToken} // device token
    customerId: 12

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "access_token": "545675685867867|Iieeiuh33z-cyyuUJHake_-IIHIho",
    "token_type": "bearer"
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get unique customerId form uniqueCustomerIdMask

---

    /customer/id-from-mask [POST]

HEADER

    NA (No authToken Required)

QUERY

    NA

BODY

```json
{
  "uniqueCustomerIdMask" : "ABX34"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "customerId": 122
  },
  "name": null,
  "code": 20,
  "message": null
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "NotFound",
  "code": 8,
  "message": "Customer not found for the given mask"
}
```

## Is Poc Data valid for customer POST

    /point-of-contact [GET]

QUERY

    email=abc@gmail.com
    mobile-number=9877798777
    country-code=+91

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "canEmailBeUsedForPoc": true,  // or false
    "isPhoneNumberCombinationValid": true  // or false
  },
  "name": null,
  "code": 20,
  "message": null
}
```

#### Last 6 days days device on off percentage report

---

    /dashboard/last-few-days-device [GET]

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "dateString": "2019-11-24",
      "deviceOnPercentage": 10,
      "deviceOffPercentage": 10,
      "deviceDataDeletedPercentage": 10,
      "deviceNotApplicablePercentage": 10,
      "deviceDataNotAvailablePercentage": 10,
      "deviceWeekOffPercentage": 40,
      "deviceInActivePercentage": 10
    },
    {
      "dateString": "2019-11-23",
      "deviceOnPercentage": 10,
      "deviceOffPercentage": 10,
      "deviceDataDeletedPercentage": 10,
      "deviceNotApplicablePercentage": 10,
      "deviceDataNotAvailablePercentage": 10,
      "deviceWeekOffPercentage": 40,
      "deviceInActivePercentage": 10
    },
    {
      "dateString": "2019-11-22",
      "deviceOnPercentage": 10,
      "deviceOffPercentage": 10,
      "deviceDataDeletedPercentage": 10,
      "deviceNotApplicablePercentage": 10,
      "deviceDataNotAvailablePercentage": 10,
      "deviceWeekOffPercentage": 40,
      "deviceInActivePercentage": 10
    }
  ],
  "name": null,
  "code": 20,
  "message": null
}
```

#### Last 6 days days panel on off percentage report

---

    /dashboard/last-few-days-panel [GET]

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [    // this will contain last 6 days array
    {
      "dateString": "2019-11-23",   //this is a presentable data directly on UI. This should not be converted or transformed
      "panelOnPercentage": 10,
      "panelOffPercentage": 10,
      "panelDataDeletedPercentage": 10,
      "panelNotApplicablePercentage": 10,
      "panelDataNotAvailablePercentage": 10,
      "panelWeekOffPercentage": 40,
      "panelInActivePercentage": 10,
      "panelDisconnectedPercentage": 10
    },
    {
      "dateString": "2019-11-22",   //this is a presentable data directly on UI. This should not be converted or transformed
      "panelOnPercentage": 10,
      "panelOffPercentage": 10,
      "panelDataDeletedPercentage": 10,
      "panelNotApplicablePercentage": 10,
      "panelDataNotAvailablePercentage": 10,
      "panelWeekOffPercentage": 40,
      "panelInActivePercentage": 10,
      "panelDisconnectedPercentage": 10
    },
    {
      "dateString": "2019-11-21",   //this is a presentable data directly on UI. This should not be converted or transformed
      "panelOnPercentage": 10,
      "panelOffPercentage": 10,
      "panelDataDeletedPercentage": 10,
      "panelNotApplicablePercentage": 10,
      "panelDataNotAvailablePercentage": 10,
      "panelWeekOffPercentage": 40,
      "panelInActivePercentage": 10,
      "panelDisconnectedPercentage": 10
    }
  ],
  "name": null,
  "code": 20,
  "message": null
}
```

#### Get Customers for PDN

---

    /pdn/customer [GET]

QUERY

    status=1          // optional 1 for ACTIVE and 2 for INACTIVE
    Authorization : Bearer <token>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {CustomerModel},
    {CustomerModel}
  ],
  "name": null,
  "code": 20,
  "message": null
}
```

#### Get Devices for PDN

---

    /pdn/device [GET]

QUERY

    status=1         // optional 1 for ACTIVE and 2 for INACTIVE

HEADER

    Authorization : Bearer <token>
    customerId : <customerId>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {DeviceModel},
    {DeviceModel}
  ],
  "name": null,
  "code": 20,
  "message": null
}
```

#### Get Layout by ID for PDN

---

    /pdn/layout/<layoutId> [GET]

QUERY

    NA

HEADER

    Authorization : Bearer <token>
    customerId : <customerId>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "layoutId": 1232,
    "layoutName": "Cool Layout",
    "canEditLocationBasedPlaylist": true,       // or false. This indicates that the calling user can edit this layout for location based playlist
    "isPendingForApporval": true, // true if layout is pending for approval, false otherwise
    "isUsed": true,   // true if used in a planogram or layout string or in rule association false otherwise. If true then the campaign cannot be deleted or edited
    "isUsedInRuleAssociation": true, // or false
    "layoutDescription": "Layout description",
    "aspectRatio": {
      "aspectRatioId": 12123,
      "aspectRatio": "16:9",
      "defaultWidthInPixel": 864,
      "defaultHeightInPixel": 486,
      "status": 1
    },
    "totalDurationOfCampaignInSeconds": 1000,   // to calculate this on server-side using the total value of region playlist and use the largest one
    "createdByUserId": 15,
    "createdByFullName": "Abc Xyz",
    "associatedWithCampaignStrings": [
      {
        "layoutStringId": 13,
        "layoutStringName": "Cool layout String 1"
      },
      {
        "layoutStringId": 15,
        "layoutStringName": "Cool layout String 2"
      }
    ],
    "backgroundImageContentId": 878,    // optional
    "backgroundImageContentVersion": 12
    "backgroundColor": "#888888",       // optional
    "transparencyInPercentage": 50,     // optional
    "audioStartBasedOnLayoutDurationInSeconds": 120,
    "audioEndBasedOnLayoutDurationInSeconds": 480,
    "audios": [
      {
        "contentId": 12,
        "contentVersion": 15
        "order": 1
      },
      {
        "contentId": 16,
        "contentVersion": 3
        "order": 2
      }
    ],
    "state": "DRAFT|SUBMITTED|APPROVED|PUBLISHED",    // one of these values to be sent
    "status": 1,                 // 1 (active) or 2 (inactive)
    "canApprove": false,         // should be true if the user requesting layout is MAKER or ADMIN role
    "regions": [
      {
        "layoutRegionId": 7267,
        "layoutRegionName": "My cool region",
        "regionTransparencyInPercentage": 15,
        "isAudioEnabled": true,
        "zIndex" : 12,
        "locations": [
          {
            "locationId": 12,
            "locationName": "Noida",
          },
          {
            "locationId": 122,
            "locationName": "Delhi",
          }
        ],
        "widthInPercentage": 50,
        "heightInPercentage": 30,
        "topLeftCoordinateXInPercentage": 13,
        "topLeftCoordinateYInPercentage": 18,
        "widthInPixel": 531,
        "heightInPixel": 260,
        "topLeftCoordinateXInPixel": 189,
        "topLeftCoordinateYInPixel": 694,
        "globalRegionContentPlaylistId": 123,
        "globalRegionContentPlaylistContents": [    // if caller is device then find if the device falls under a given locations ids and then only pass those playlist which belong to that device
          {
            "contentId": 323,
            "contentVersion": 1,
            "contentType": "ADVERTISEMENT",  // PDN network should look for this content type
            "order": 2,
            "durationInSeconds": 600,
            "transparencyInPercentage": 0,
            "entryAnimationId": 329,
            "exitAnimationId": 329,
            "displayMode": "NORMAL|STRETCH-TO-FIT"
          },
          {
            "contentId": 84,
            "contentVersion": 19,
            "contentType": "VIDEO",
            "order": 1,
            "durationInSeconds": 800,
            "transparencyInPercentage": 30,
            "entryAnimationId": 329,
            "exitAnimationId": 329,
            "displayMode": "NORMAL|STRETCH-TO-FIT"
          }
        ]
      }
    ],
    "layoutTags": [
      { "layoutTag" : "hello"},
      { "layoutTag" : "test"},
      { "layoutTag" : "world"}
    ],
    "workFlowActivity": [
      {
        "workFlowActivityType": "REJECT",
        "userId": 12,
        "fullName": "Pratap Patil",
        "reason": "Background color was not good. Please change to yellow.",
        "approverLevel": 2,
        "roles": [
          {
            "roleId": 1,
            "roleName": "CUSTOMER_ADMIN"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L1 Pratap Patil"
      },
      {
        "workFlowActivityType": "APPROVE",
        "userId": 18,
        "fullName": "Desh Deepak",
        "approverLevel": 1,
        "roles": [
          {
            "roleId": 5,
            "roleName": "APPROVER"
          }
        ],
        "timestampOfActivity": 158446989797,
        "displayString": "L2 Desh Deepak"
      },
      {
        "workFlowActivityType": "SUBMIT",
        "userId": 18,
        "fullName": "Hamzah Jamal",
        "approverLevel": null,
        "roles": [
          {
            "roleId": 4,
            "roleName": "MAKER"
          }
        ],
        "timestampOfActivity": 158446989797,ex
        "displayString": "L2 Desh Deepak"
      }
    ],
  },
  "name": null,
  "code": 0,
  "message": null
}
```

### On Premises and app distribution support

---

#### Add On premises environment

---

    /premises [POST]

QUERY

    NA

HEADER

    Authorization : Bearer <token>

Multipart post body

    windowsStoreCertificateFile=<wns file to be uploaded>   // optional
    serverFirebaseJson=<server firebase json file>
    angularFirebaseJson=<angular firebase json file>
    androidFirebaseJson=<android firebase json file>
    packageXmlFile=<Package.xml file>
    packageStoreAssociationXmlFile=<Package.StoreAssociation.xml file>
    packageAppXManifestFile=<Package.appxmanifest file>
    serverLaunchConfigJson=<server launch config json file>
    onPremiseJson=<see below>
    angularBuildFile=<angular build zip file>
    serverBuildFile=<server build jar file>
    serverSeedFile=<server seed sql file>
    sslCertificatesZipFile=<ssl certificate>

onPremiseJson

```json
{
  "baseUrl": "http://abc.com/",
  "onPremisesPublicIP": "114.56.240.210",
  "status": 1, // (1, 2 and 3)
  "wnsClientId": "2324d4asdasd23y8",  // optional
  "wnsClientSecret" : "a32r324rsdasd", // optional
  "angularDomain": "angular.domain.com",
  "serverDomain": "angular.domain.com",
  "serverDomainForDevice": "server.domain.for.device.com",
  "isOnboarded": true, // or false
  "onPremisesEnvironmentName": "hello world",
  "onPremisesEnvironmentAddress": "301, Noida - 201301",
  "mailSeverHost": "smtp.gmail.com",
  "mailServerPort": 587,
  "mailServerUsername": "abc@abc.com",
  "mailDefaultSenderEmail": "abc@abc.com",
  "mailServerEmailPassword": "passwd@123",
  "mailEnableTLS": true,
  "isS3Enabled": false,
  "rabbitMqServerIp": "10.10.10.10",
  "mysqlServerDomain": "localhost",
  "faDatabaseName": "fa_db",
  "primaryPostgresServerDomain": "some.IP.Or.URL",
  "dsFadbDatasourceUsername": "my.cool.db.user",
  "dsFadbDatasourcePassword": "my.cool.db.password"
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

#### Update On premises environment

---

    /premises/<onPremiseId> [PUT]

QUERY

    NA

HEADER

    Authorization : Bearer <token>

Multipart post body

    windowsStoreCertificateFile=<wns file to be uploaded>               // editable after on boarding
    serverFirebaseJson=<server firebase json file>
    angularFirebaseJson=<angular firebase json file>
    androidFirebaseJson=<android firebase json file>
    packageXmlFile=<Package.xml file>                                   // editable after on boarding
    packageStoreAssociationXmlFile=<Package.StoreAssociation.xml file>  // editable after on boarding
    packageAppXManifestFile=<Package.appxmanifest file>                 // editable after on boarding
    serverLaunchConfigJson=<server launch config json file>             // editable after on boarding
    onPremiseJson=<see below>
    angularBuildFile=<angular build zip file>
    serverBuildFile=<server build jar file>
    serverSeedFile=<server seed sql file>
    sslCertificatesZipFile=<ssl certificate>

onPremiseJson

```json
{
  "baseUrl": "http://abc.com/",
  "onPremisesPublicIP": "114.56.240.210", // editable after on boarding
  "status": 1, // (1, 2 and 3)
  "wnsClientId": "2324d4asdasd23y8",      // editable after on boarding
  "wnsClientSecret" : "a32r324rsdasd",    // editable after on boarding
  "angularDomain": "angular.domain.com",
  "serverDomain": "angular.domain.com",
  "serverDomainForDevice": "server.domain.for.device.com",
  "isOnboarded": true, // or false
  "onPremisesEnvironmentName": "hello world",  // editable after on boarding
  "onPremisesEnvironmentAddress": "301, Noida - 201301",  // editable after on boarding
  "mailSeverHost": "smtp.gmail.com",          // editable after on boarding
  "mailServerPort": 587,                      // editable after on boarding
  "mailServerUsername": "abc@abc.com",        // editable after on boarding
  "mailDefaultSenderEmail": "abc@abc.com",    // optional, editable after on boarding
  "mailServerEmailPassword": "passwd@123",    // editable after on boarding
  "mailEnableTLS": true,                      // editable after on boarding
  "isS3Enabled": false,
  "rabbitMqServerIp": "10.10.10.10",
  "mysqlServerDomain": "localhost",
  "faDatabaseName": "fa_db",
  "primaryPostgresServerDomain": "some.IP.Or.URL",
  "dsFadbDatasourceUsername": "my.cool.db.user",
  "dsFadbDatasourcePassword": "my.cool.db.password"
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

#### Update On premises environment (json only)

---

    /premises/json/<onPremiseId> [PUT]

QUERY

    NA

HEADER

    Authorization : Bearer <token>

BODY

```json
{
  "baseUrl": "http://abc.com/",
  "onPremisesPublicIP": "114.56.240.210", // editable after on boarding
  "status": 1, // (1, 2 and 3)
  "wnsClientId": "2324d4asdasd23y8",       // editable after on boarding
  "wnsClientSecret" : "a32r324rsdasd",      // editable after on boarding
  "angularDomain": "angular.domain.com",
  "serverDomain": "angular.domain.com",
  "serverDomainForDevice": "server.domain.for.device.com",
  "mailServerSenderEmail": "abc@abc.com",  // editable after on boarding
  "mailServerEmailPassword": "passwd@123",  // editable after on boarding
  "isOnboarded": true, // or false
  "onPremisesEnvironmentName": "hello world",  // editable after on boarding
  "onPremisesEnvironmentAddress": "301, Noida - 201301",  // editable after on boarding
  "mailSeverHost": "smtp.gmail.com",
  "mailServerPort": 587,
  "mailServerUsername": "abc@abc.com",
  "mailDefaultSenderEmail": "abc@abc.com",    // optional
  "mailServerEmailPassword": "passwd@123",
  "mailEnableTLS": true,
  "isS3Enabled": false,
  "rabbitMqServerIp": "10.10.10.10",
  "mysqlServerDomain": "localhost",
  "faDatabaseName": "fa_db",
  "primaryPostgresServerDomain": "some.IP.Or.URL",
  "dsFadbDatasourceUsername": "my.cool.db.user",
  "dsFadbDatasourcePassword": "my.cool.db.password"
}
```

RESPONSE - code - 200

```json
{
  SuccessfullySaved;
}
```

#### Get all on premises environments (for angular app)

---

Note: only for angular user (PAN_ADMIN only)

    /premises [GET]

QUERY

    NA

HEADER

    Authorization : Bearer <token>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "onPremisesId": 1,
      "baseUrl": "http://abc1.com/",
      "onPremisesPublicIP": "114.56.240.210",
      "status": 1, // (1, 2 and 3)
      "serverFirebaseJson": "http://url",
      "angularFirebaseJson": "http://url",
      "androidFirebaseJson": "http://url",
      "wnsClientId": "2324d4asdasd23y8",
      "wnsClientSecret" : "a32r324rsdasd",
      "windowsStoreCertificateFile" : "http://abc1.com/windows-certification",
      "angularDomain": "angular.domain.com",
      "serverDomain": "angular.domain.com",
      "serverDomainForDevice": "server.domain.for.device.com",
      "isOnboarded": true, // or false
      "onPremisesEnvironmentName": "hello world",
      "onPremisesEnvironmentAddress": "301, Noida - 201301",
      "mailSeverHost": "smtp.gmail.com",
      "mailServerPort": 587,
      "mailServerUsername": "abc@abc.com",
      "mailDefaultSenderEmail": "abc@abc.com",    // optional
      "mailEnableTLS": true,
      "isS3Enabled": false,
      "packageXmlFile": "http://url",
      "packageStoreAssociationXmlFile": "http://url",
      "packageAppXManifestFile": "http://url",
      "rabbitMqServerIp": "10.10.10.10",
      "mysqlServerDomain": "localhost",
      "serverLaunchConfigJson": "http://url",
      "faDatabaseName": "fa_db",
      "primaryPostgresServerDomain": "some.IP.Or.URL",
      "dsFadbDatasourceUsername": "my.cool.db.user",
      "dsFadbDatasourcePassword": "my.cool.db.password",
      "angularBuildUrl": "http://url",
      "serverBuildUrl": "http://url",
      "serverSeedUrl": "http://url",
      "sslCertificatesZipFileUrl": "http://url"
    },
    {
      "onPremisesId": 2,
      "baseUrl": "http://abc2.com/",
      "onPremisesPublicIP": "114.56.240.210",
      "status": 1, // (1, 2 and 3)
      "serverFirebaseJson": "http://url",
      "angularFirebaseJson": "http://url",
      "androidFirebaseJson": "http://url",
      "wnsClientId": "2324d4asdasd23y8",
      "wnsClientSecret" : "a32r324rsdasd",
      "windowsStoreCertificateFile" : "http://abc2.com/windows-certification",
      "angularDomain": "angular.domain.com",
      "serverDomain": "angular.domain.com",
      "serverDomainForDevice": "server.domain.for.device.com",
      "isOnboarded": true, // or false
      "onPremisesEnvironmentName": "hello world",
      "onPremisesEnvironmentAddress": "301, Noida - 201301",
      "mailSeverHost": "smtp.gmail.com",
      "mailServerPort": 587,
      "mailServerUsername": "abc@abc.com",
      "mailDefaultSenderEmail": "abc@abc.com",    // optional
      "mailServerEmailPassword": "passwd@123",
      "mailEnableTLS": true,
      "isS3Enabled": false,
      "packageXmlFile": "http://url",
      "packageStoreAssociationXmlFile": "http://url",
      "packageAppXManifestFile": "http://url",
      "rabbitMqServerIp": "10.10.10.10",
      "mysqlServerDomain": "localhost",
      "serverLaunchConfigJson": "http://url",
      "faDatabaseName": "fa_db",
      "primaryPostgresServerDomain": "some.IP.Or.URL",
      "dsFadbDatasourceUsername": "my.cool.db.user",
      "dsFadbDatasourcePassword": "my.cool.db.password",
      "angularBuildUrl": "http://url",
      "serverBuildUrl": "http://url",
      "serverSeedUrl": "http://url",
      "sslCertificatesZipFileUrl": "http://url"
    }
  ],
  "name": null,
  "code": 0,
  "message": null
}
```

#### Get one onpremises environment (for angular app)

---

Note: only for angular user (PAN_ADMIN only)

    /premises/<onPremiseId> [GET]

QUERY

    NA

HEADER

    Authorization : Bearer <token>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "onPremisesId": 1,
    "baseUrl": "http://abc1.com/",
    "onPremisesPublicIP": "114.56.240.210",
    "status": 1, // (1, 2 and 3)
    "serverFirebaseJson": "http://url",
    "angularFirebaseJson": "http://url",
    "androidFirebaseJson": "http://url",
    "wnsClientId": "2324d4asdasd23y8",
    "wnsClientSecret" : "a32r324rsdasd",
    "windowsStoreCertificateFile" : "http://abc1.com/windows-certification",
    "angularDomain": "angular.domain.com",
    "serverDomain": "angular.domain.com",
    "serverDomainForDevice": "server.domain.for.device.com",
    "isOnboarded": true, // or false
    "onPremisesEnvironmentName": "hello world",
    "onPremisesEnvironmentAddress": "301, Noida - 201301",
    "mailSeverHost": "smtp.gmail.com",
    "mailServerPort": 587,
    "mailServerUsername": "abc@abc.com",
    "mailDefaultSenderEmail": "abc@abc.com",    // optional
    "mailServerEmailPassword": "passwd@123",
    "mailEnableTLS": true,
    "isS3Enabled": false,
    "packageXmlFile": "http://url",
    "packageStoreAssociationXmlFile": "http://url",
    "packageAppXManifestFile": "http://url",
    "rabbitMqServerIp": "10.10.10.10",
    "mysqlServerDomain": "localhost",
    "serverLaunchConfigJson": "http://url",
    "faDatabaseName": "fa_db",
    "primaryPostgresServerDomain": "some.IP.Or.URL",
    "dsFadbDatasourceUsername": "my.cool.db.user",
    "dsFadbDatasourcePassword": "my.cool.db.password",
    "angularBuildUrl": "http://url",
    "serverBuildUrl": "http://url",
    "serverSeedUrl": "http://url",
    "sslCertificatesZipFileUrl": "http://url"
  },
  "name": null,
  "code": 0,
  "message": null
}
```

#### Update status of premises environment (for angular app)

---

Note: only for angular user (PAN_ADMIN only)

    /premises/status [PUT]

QUERY

    NA

HEADER

    Authorization : Bearer <token>

BODY

```json
{
  "ids" : [1, 4, 88] // on premise Ids
  "status : 1   // or 2, 3
}
```

RESPONSE - code - 200

```json
{
  SuccessfullyMultipleSaved;
}
```

#### Get all on premises environments (for jenkins app)

---

Note: only for jenkins user

    /premises/jenkins [GET]

QUERY

    NA

HEADER

    Authorization : Bearer <token>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "onPremisesId": 1,          // primary key of onpremises table on server. This should be used by jenkins app should use this ID to indentify a onpremises environment uniquely
      "baseUrl": "http://abc1.com/",      // base url for the on premises environment
      "onPremisesPublicIP": "114.56.240.210",   // public IP of the onPremises evn machine to which ssh can be done
      "status": 1, // (1, 2 and 3)      // status as 1 = ACTIVE, 2 = INACTIVE, 3 = DELETED  (not required for jenkins app)
      "serverFirebaseJson": "http://url",    // to be stored anywhere on the server machine and change the value of launch command -Dfirebase.json.path=/path/of/file
      "angularFirebaseJson": "http://url",   // angular firebase json. How to use : please refer to angular team
      "androidFirebaseJson": "http://url",   // android firebase json. How to use : please refer to android team
      "wnsClientId": "2324d4asdasd23y8",     // use add to server launch command -Dwns.client.id=2324d4asdasd23y8
      "wnsClientSecret" : "a32r324rsdasd",   // use add to server launch command -Dwns.client.secret=a32r324rsdasd
      "windowsStoreCertificateFile" : "http://abc1.com/windows-certification",   // WNS certificate. How to use : please refer to windows team
      "angularDomain": "https://angular.domain.com",  // domain name to be used for angular app, in server launch command use -Dangular.base.url=https://angular.domain.com
      "serverDomain": "https://server.domain.com",   // domain name to be used for server app, in server launch command use -Dangular.base.url=https://sever.domain.com
      "serverDomainForDevice": "https://server.domain.for.device.com", // domain name to be used for devices, in server launch command use -Ddevice.base.url=server.domain.for.device.com
      "isOnboarded": true, // or false              // does not matter for jenkins app
      "onPremisesEnvironmentName": "hello world",     // does not matter for jenkins app
      "onPremisesEnvironmentAddress": "301, Noida - 201301",    // does not matter for jenkins app
      "mailSeverHost": "smtp.gmail.com"           // change server launch command -Dspring.mail.host=10.85.90.89
      "mailServerPort": 587,                      // change server launch command -Dspring.mail.port=587
      "mailServerUsername": "abc@abc.com",        // change server launch command -Dspring.mail.username=abc@abc.com
      "mailDefaultSenderEmail": "abc@abc.com",    // (optional) change server launch command -Ddefault.mail.sender=abc@abc.com
      "mailServerEmailPassword": "passwd@123",    // change server launch command -Dspring.mail.password=passwd@123
      "mailEnableTLS": true,                      // change sever launch command to -Dspring.mail.properties.mail.smtp.starttls.enable=true
      "isS3Enabled": false,                       // change sever launch command to -Dis.s3.enabled=false
      "packageXmlFile": "http://url",                  // Package.xml file. How to use : please refer to windows team
      "packageStoreAssociationXmlFile": "http://url",  // Package.StoreAssociation.xml file. How to use : please refer to windows team
      "packageAppXManifestFile": "http://url",         // Package.appxmanifest file. How to use : please refer to windows team
      "rabbitMqServerIp": "10.10.10.10",              //change sever launch command to -Dpush.rebbitMq.server.ip=10.10.10.10
      "mysqlServerDomain": "localhost",               //change sever launch command to -Dmysql.server.domain=localhost
      "serverLaunchConfigJson": "http://url",         // download this file and store it @ /panasonicdata/config/server-launch-config.json
      "faDatabaseName": "fa_db",
      "primaryPostgresServerDomain": "some.IP.Or.URL",
      "dsFadbDatasourceUsername": "my.cool.db.user",
      "dsFadbDatasourcePassword": "my.cool.db.password",
      "angularBuildUrl": "http://url",
      "serverBuildUrl": "http://url",
      "serverSeedUrl": "http://url",
      "sslCertificatesZipFileUrl": "http://url"
    },
    {
      "onPremisesId": 2,
      "baseUrl": "http://abc2.com/",
      "onPremisesPublicIP": "114.56.240.210",
      "status": 1, // (1, 2 and 3)
      "serverFirebaseJson": "http://url",
      "angularFirebaseJson": "http://url",
      "androidFirebaseJson": "http://url",
      "wnsClientId": "2324d4asdasd23y8",
      "wnsClientSecret" : "a32r324rsdasd",
      "windowsStoreCertificateFile" : "http://abc2.com/windows-certification",
      "angularDomain": "angular.domain.com",
      "serverDomain": "angular.domain.com",
      "serverDomainForDevice": "https://server.domain.for.device.com",
      "isOnboarded": true, // or false
      "onPremisesEnvironmentName": "hello world",
      "onPremisesEnvironmentAddress": "301, Noida - 201301",
      "mailSeverHost": "smtp.gmail.com",
      "mailServerPort": 587,
      "mailServerUsername": "abc@abc.com",
      "mailDefaultSenderEmail": "abc@abc.com",
      "mailServerEmailPassword": "passwd@123",
      "mailEnableTLS": true,
      "isS3Enabled": false,
      "packageXmlFile": "http://url",
      "packageStoreAssociationXmlFile": "http://url",
      "packageAppXManifestFile": "http://url",
      "rabbitMqServerIp": "10.10.10.10",
      "mysqlServerDomain": "localhost",
      "serverLaunchConfigJson": "http://url",
      "faDatabaseName": "fa_db",
      "primaryPostgresServerDomain": "some.IP.Or.URL",
      "dsFadbDatasourceUsername": "my.cool.db.user",
      "dsFadbDatasourcePassword": "my.cool.db.password",
      "angularBuildUrl": "http://url",
      "serverBuildUrl": "http://url",
      "serverSeedUrl": "http://url",
      "sslCertificatesZipFileUrl": "http://url"
    }
  ],
  "name": null,
  "code": 0,
  "message": null
}
```

#### Get on premise build

---

Note: This API will be called by the on premises servers.

    /premises/build [GET]

QUERY

    os=WINDOWS|ANDROID  // os (required)
    on-premise-id=12   // on premise ID (required)

HEADER

    Authorization : Bearer <token>

RESPONSE HEADERS

    MD5-Hash: f970e2767d0cfe75876ea857f92e319b
    App-Version: 4.52.188

BODY

    NA

RESPONSE - code - 200

    actual apk file or the zip file in case of windows

#### Get version of latest builds

---

Note: This API will be called by the on premises servers.

    /premises/build/version [GET]

QUERY

    on-premise-id=12 // (required)

RESPONSE - code - 200

```json
{
  "result": {
    "latestAndroidVersion": "1.35.67",
    "latestWindowsVersion": "5.23.1"
  },
  "name": null,
  "code": 0,
  "message": null
}
```

#### Get version of latest version for devices

---

Note: This API will be called by the on premises servers.

    /app-upgrade/build/version [GET]

QUERY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "latestAndroidVersion": "1.35.67",
    "latestWindowsVersion": "5.23.1"
  },
  "name": null,
  "code": 0,
  "message": null
}
```

#### Onboard a on premise server

---

Note: This API will be called by the on premise server to the CMS server

    /premises/id [GET]

QUERY

    base-url=http://abc.com

RESPONSE - code - 200

```json
{
  "result": {
    "premiseid": 21,
    "fcmServerKey": "asfgroih54ogj45gj45gjk-45g0-54j-054-054"
  },
  "name": null,
  "code": 0,
  "message": null
}
```

#### Stop a planogram

---

    /planogram/stop [PUT]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

BODY

```json
{
  "planogramId": 43
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessFullySaved",
  "code": 200,
  "message": "Record Saved Successfully",
  "pagination": null
}
```

#### Add advertisement layout

---

    /layout-advertisement [POST]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

BODY

```json
{
  "duration": 15,
  "layoutName": "My cool advertisement layout",
  "aspectRatioId": 12123
}
```

RESPONSE - code - 200

```json
{
  "result": {LayoutModel},
  "name": "SuccessFullySaved",
  "code": 200,
  "message": "Record Saved Successfully",
  "pagination": null
}
```

#### Update advertisement layout

---

    /layout-advertisement/<layoutId> [PUT]

HEADER

    Content-Type: application/json
    Authorization: Bearer {token}

BODY

```json
{
  "duration": 15,
  "layoutName": "My cool advertisement layout",
  "aspectRatioId": 12123
}
```

RESPONSE - code - 200

```json
{
  "result": {LayoutModel},
  "name": "SuccessFullyUpdaed",
  "code": 200,
  "message": "Layout updated successfully",
  "pagination": null
}
```

#### Is advertisement enabled

---

    /advertisement/enabled [GET]

HEADER

    Content-Type: application/json

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "isPDNEnabled" : true, // or false
    "PDNUrl" : "http://www.pdn.com"
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

#### Get schedule by deviceId (to be called by PDN server)

---

    /pdn/schedule/<deviceId>?start-date=2018-12-30&end-date=2019-01-15  [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}
    customerId: <customerId>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "deviceSchedule": {
      "deviceId": 1,
      "layouts": [
        {
          "layoutId": 1,
          "layoutType" : "NORMAL",
          "startDateTime": "2018-12-30 11:30:00",
          "endDateTime": "2018-12-30 13:30:00",
          "planogramId": 15,
          "planogramName" : "Weekend Sale planogram",
          "offsetInSeconds": 23
        },
        {
          "layoutId" : 2,
          "layoutType" : "ADVERTISEMENT",
          "startDateTime": "2018-12-30 14:30:00",
          "endDateTime": "2018-12-30 15:30:00",
          "planogramId": 26,
          "planogramName" : "All food and bevrages planogram",
          "offsetInSeconds": 0
        }
      ],
      "startDateTime": "2018-12-30",
      "endDateTime": "2019-01-15"
    }
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Send panel on / off timings as per device every day

---

    /dataCollection/v2/config [POST]

HEADER

    Authorization: Bearer {deviceToken}
    Content-Type:application/json

BODY

```json
{
  "configDate": 1544490081000,
  "timeZone": "+05:30",
  "panelOnTime": "01:01:21",
  "panelOffTime": "08:01:21",
  "weekOffs": [
    "MON",
    "WED"
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "deviceBusinessConfigId": 32044,
    "deviceId": 556,
    "configDate": 1544490081000,
    "businessOnTime": "01:01:21",
    "businessOffTime": "08:01:21"
  },
  "pagination": null,
  "name": null,
  "code": 200,
  "message": "SuccessFullySaved"
}
```

#### Send panel on / off timings as per device every day (send all data together)

---

    /dataCollection/v2/config/all [POST]

HEADER

    Authorization: Bearer {deviceToken}
    Content-Type:application/json

BODY

```json
[
  {
    configDate: 1544490081000,
    timeZone: "+05:30",
    panelOnTime: "01:01:21",
    panelOffTime: "08:01:21",
    weekOffs: ["MON", "WED"],
  },
  {
    configDate: 1544490081000,
    timeZone: "+05:30",
    panelOnTime: "01:01:21",
    panelOffTime: "08:01:21",
    weekOffs: ["MON", "WED"],
  },
];
```

RESPONSE - code - 200

```json
{
  "result": [
    {
      "deviceBusinessConfigId": 32044,
      "deviceId": 556,
      "configDate": 1544490081000,
      "businessOnTime": "01:01:21",
      "businessOffTime": "08:01:21"
    },
    {
      "deviceBusinessConfigId": 32044,
      "deviceId": 556,
      "configDate": 1544490081000,
      "businessOnTime": "01:01:21",
      "businessOffTime": "08:01:21"
    }
  ],
  "pagination": null,
  "name": null,
  "code": 200,
  "message": "SuccessFullySaved"
}
```

### Dashboard layout waiting for approval

---

Note: This API needs to be called only on the dashboard

    /dashboard/layout/waiting-for-approval [GET]

HEADER

    Authorization: Bearer {userToken}
    Content-Type:application/json

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "layoutId": 47,
      "layoutName": "hello"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Reset a device's overridden config back to customer config

---

    /deviceSetting/reset [PUT]

HEADER

    Authorization: Bearer {userToken}
    Content-Type:application/json

BODY

```json
{
  "deviceId": 34
}
```

RESPONSE - code - 200

```json
{
  "result": ,
  "name": null,
  "code": null,
  "message": null
}
```

### Add third party app

---

    /tpapp [POST]

HEADER

    Authorization: Bearer {userToken}   // only PAN ADMIN and CUST AMDIN and CUST REP
    Content-Type:application/json
    customerId : 1

BODY

```json
{
  "tpappName": "string", // required - unique
  "tpappDescription": "string", // optional
  "appOs": "ANDROID|WINDOWS", // required
  "deeplinkSchema": "myapp", // required - only text chars allowed like 'abc' and it should be unique in DB
  "windowsDirectoryToDownloadTPABuilds": "C:\\Program Files\\New Folder", // required only if OS is windows
  "androidAppApplicationId": "com.example.app" // required only is OS is android
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "tpappId": 1,
    "tpappName": "string",
    "tpappDescription": "string",
    "appOs": "ANDROID|WINDOWS",
    "deeplinkSchema": "myapp",
    "windowsDirectoryToDownloadTPABuilds": "C:\\Program Files\\New Folder",
    "androidAppApplicationId": "com.example.app"
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Update third party app

---

    /tpapp/<tpappId> [PUT]

HEADER

    Authorization: Bearer {userToken}   // only PAN ADMIN and CUST ADMIN and CUST REP
    Content-Type:application/json
    customerId : 1

BODY

```json
{
  "tpappName": "string",
  "tpappDescription": "string",
  "appOs": "ANDROID|WINDOWS",
  "deeplinkSchema": "myapp",
  "windowsDirectoryToDownloadTPABuilds": "C:\\Program Files\\New Folder",
  "androidAppApplicationId": "com.example.app"
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "tpappId": 34, // long
    "tpappName": "string",
    "tpappDescription": "string",
    "appOs": "ANDROID|WINDOWS",
    "deeplinkSchema": "myapp",
    "windowsDirectoryToDownloadTPABuilds": "C:\\Program Files\\New Folder",
    "androidAppApplicationId": "com.example.app"
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get all third party apps

---

    /tpapp [GET]

HEADER

    Authorization: Bearer {userToken}   // only PAN ADMIN and CUST AMDIN and CUST REP
    Content-Type:application/json
    customerId : 1

QUERY

    appOs=ANDROID|WINDOWS

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "tpappId": 34, // long
      "tpappName": "string",
      "tpappDescription": "string",
      "appOs": "ANDROID|WINDOWS",
      "deeplinkSchema": "myapp",
      "windowsDirectoryToDownloadTPABuilds": "C:\\Program Files\\New Folder",
      "androidAppApplicationId": "com.example.app"
    },
    {
      "tpappId": 35, // long
      "tpappName": "string",
      "tpappDescription": "string",
      "appOs": "ANDROID|WINDOWS",
      "deeplinkSchema": "myapp",
      "windowsDirectoryToDownloadTPABuilds": "C:\\Program Files\\New Folder",
      "androidAppApplicationId": "com.example.app"
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Get a third party app

---

    /tpapp/<tpappId> [GET]

HEADER

    Authorization: Bearer {userToken}   // only PAN ADMIN and CUST AMDIN and CUST REP
    Content-Type:application/json
    customerId : 1

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "tpappId": 34, // long
    "tpappName": "string",
    "tpappDescription": "string",
    "appOs": "ANDROID|WINDOWS",
    "deeplinkSchema": "myapp",
    "windowsDirectoryToDownloadTPABuilds": "C:\\Program Files\\New Folder",
    "androidAppApplicationId": "com.example.app"
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Delete third party app

---

    /tpapp/<tpappId> [DELETE]

HEADER

    Authorization: Bearer {userToken}   // only PAN ADMIN and CUST AMDIN and CUST REP
    Content-Type:application/json
    customerId : 1

QUERY

    do-validation=true|false  // optional - if not specified then it is treated as true.

Note: If validation is performed then tpa is not deleted if it is being used in
tpa server or in device config. If validation is not performed then the TPA is deleted
and associated tpa servers are deleted and if any device configs had this tpa as
touch url then the touch urls are cleared and a re-download config push is sent to those
devices.

BODY

    NA

RESPONSE - code - 200

```json
{
    "result": null,
    "pagination": null,
    "name": "SuccessFullyDeleted",
    "code": 22,
    "message": "Record Deleted Successfully"
}
```

RESPONSE - code - 400

```json
{
    "result": null,
    "pagination": null,
    "name": "TpaValidationError",
    "code": 2,
    "message": "Error message on why the TPA could not be deleted"
}
```

### Validate TPA for deletion

---

    /tpapp/validate/<tpappId> [POST]

HEADER

    Authorization: Bearer {userToken}   // only PAN ADMIN and CUST AMDIN and CUST REP
    Content-Type:application/json
    customerId : 1

BODY

    NA

RESPONSE - code - 200

```json
{
    "result": {
      "isValidationSuccess": true,
      "validationFailureMessage" : null
    },
    "name": "TPAValidation",
    "code": 1,
    "message": ""
}
```

RESPONSE - code - 200

```json
{
    "result": {
      "isValidationSuccess": false,
      "validationFailureMessage" : "validation message"
    },
    "name": "TPAValidation",
    "code": 2,
    "message": "validation message"
}
```

### Get commands for a TPA

---

    /tpapp/schemas [GET]

HEADER

    Authorization: Bearer {userToken}
    Content-Type:application/json
    customerId: 1

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "schema": "tpappx",
      "isPathLimited": true,
      "allowedPaths": [
        "launch"
      ]
    },
    {
      "schema": "tpappy",
      "isPathLimited": true,
      "allowedPaths": [
        "launch"
      ]
    },
    {
      "schema": "http",
      "isPathLimited": false,
      "allowedPaths": null
    },
    {
      "schema": "https",
      "isPathLimited": false,
      "allowedPaths": null
    },
    {
      "schema": "local",
      "isPathLimited": false,
      "allowedPaths": null
    },
  ],
  "name": null,
  "code": null,
  "message": null
}
```

## IFrame APIs

These APIs can be used to add a new iframe based to CMS webapp. Each iframe will be shown by the CMS web app on a
separate reports page. `iframeUrl` is the URL which be added in the `src` attribute of the `iframe` tag on the CMS web
app

### Add a iframe url

---

Use this API add an iframe url on the CMS app

    /iframe-url [POST]

HEADER

    Authorization: Bearer {serverToken or admin user token}
    Content-Type:application/json
    TpappId: 12   // required for user but not for tpa server

BODY

```json
{
  "iframeUrl": "string", // required
  "iframeTitle": "string",  // required - max 100 chars
  "iframeDescription": "string", // required - max 250 chars
  "order": 22 // integer
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "iframeUrlId": 1, // long
    "iframeUrl": "string",
    "iframeTitle": "string",
    "iframeDescription": "string",
    "order": 22,
    "tpAppId": 15
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Update a iframe url

---

Use this API update an existing iframe url on the CMS app

    /iframe-url/<iframeUrlId> [PUT]

HEADER

    Authorization: Bearer {userToken}
    Content-Type:application/json

BODY

```json
{
  "iframeUrl": "string",
  "iframeTitle": "string",
  "iframeDescription": "string",
  "order": 22,
  "tpAppId": 15
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "iframeUrlId": 1, // long
    "iframeUrl": "string",
    "iframeTitle": "string",
    "iframeDescription": "string",
    "order": 22,
    "tpAppId": 15
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Get all iframe urls

---

Fetches all the iframe urls used on the CMS app

    /iframe-url [GET]

HEADER

    Authorization: Bearer {userToken}
    Content-Type:application/json

BODY

    NA

QUERY

    tpapp-id=16     // optional

RESPONSE - code - 200

```json
{
  "result": [
    {
      "iframeUrlId": 1, // long
      "iframeUrl": "string",
      "iframeTitle": "string",
      "iframeDescription": "string",
      "order": 22,
      "tpAppId": 16
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Get a iframe url by id

---

Fetches an individual iframe url on the CMS app

    /iframe-url/<iframeUrlId> [GET]

HEADER

    Authorization: Bearer {userToken}
    Content-Type:application/json

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "iframeUrlId": 1, // long
    "iframeUrl": "string",
    "iframeTitle": "string",
    "iframeDescription": "string",
    "order": 22,
    "tpAppId": 16
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Delete a iframe url

---

Use this API to delete an iframe url from the CMS app

    /iframe-url/<iframeUrlId> [DELETE]

HEADER

    Authorization: Bearer {userToken}
    Content-Type:application/json

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullyDeleted",
  "code": null,
  "message": "Succcessfully deleted"
}
```

### Get schemas for contentType = URL

---

    /url-schemas [GET]

HEADER

    Authorization: Bearer {userToken}
    Content-Type:application/json
    customerId: 1

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    "http",
    "https",
    "tpax",
    "tpay"
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### Async Content Download

---

User / devices can download content asynchronously.
For any content file when download API is hit then that file gets decrypted first on server
then it starts downloading. However, when the file is too big the decryption takes time which can cause
timeouts on clients. To overcome this problem devices and users can use the async file download APIs below

First the client requests for a content file download using the `POST /content/download` API which returns immediately
with a `downloadRequestId`, which the client can poll for and check if the download is ready using:
`GET /content/download/file/{downloadRequestId}` which will return a boolean flag indicating when the downloaded file is
ready(decrypted) for download

When the flag is true another key will provide content file download url

#### Request to download content file

---

    /content/download [POST]

HEADER

    Authorization: Bearer {userToken|deviceToken}
    Content-Type:application/json
    customerId: 1   // only for cust rep and pan admin users

BODY

```json
{
  "contentId": 122    // id of the content to be downloaded
}
```

RESPONSE - code - 200

```json
{
  "result": "downloadRequestId",  // string - download request ID. Use this for polling in next API to find out if the file is ready for download
  "name": null,
  "code": null,
  "message": null
}
```

#### Check if the content is ready (decrypted) for download

---

Poll this API to check if file is ready for download

    /content/download/{downloadRequestId} [GET]

HEADER

    Authorization: Bearer {userToken|deviceToken}
    Content-Type:application/json
    customerId: 1   // only for cust rep and pan admin users

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "isReadyForDownload" : true,  // or false
    "downloadUrl" : "http://download.com/111" // download url
  },
  "name": null,
  "code": 200,
  "message": "File ready for download"
}
```

#### TPA Server Login API

---

Third party servers will use this API to get a Auth Bearer Token

    /tpa/login [POST]

HEADER

    Content-Type:application/json

QUERY

    NA

BODY

```json
{
  "tpaKey": "asdasd",  // required
  "serverId": "number" // required - example: 13 - we will provide this value and it will remain fixed
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
  },
  "name": "SuccessfullySaved.",
  "code": 20,
  "message": "Notification Sent Successfully to Upgrade Devices"
}
```

#### TPA Server Notify app upgrade

---

TPA server calls this API to notify app upgrade

    /tpa/app-upgrade/notify [POST]

HEADER

    Content-Type:application/json
    Authorization : Bearer <token> // tpa server auth token

QUERY

    NA

BODY

```json
{
  "deviceOs": "ANDROID|WINDOWS|LINUX",  // required - possible values ANDROID or WINDOWS
  "buildDownloadUrl": "http://builddownloadurl", // required - this is the link to download the zip file of your application
  "tpappId": 123, // required - third party app id (CMS team will provide one ID which will be final and you can always use that one
  "tpappVersion": "x.y.z", // required - this is the version of the app that you are updating to
  "md5checksum": "1fc27e02805368bff99ae27ce62cca21",
  "deviceIds": [    // required - specify on which device you want to send this build
    23,
    43,
    28
  ],
  "updateType": "DELETE_AND_UPDATE|ONLY_UPDATE"   // required for windows and not required and irrelevant for Android - possible values DELETE_AND_UPDATE or ONLY_UPDATE
}
```

Parameters explained:

1. `deviceOs` : can be one of the following enums `ANDROID` or `WINDOWS` or `LINUX`
2. `tpappVersion` : version of the app that is being updated
3. `1fc27e02805368bff99ae27ce62cca21` : MD5 checksum of the zip file
4. `deviceIds` : CMS app device IDs on which the build should be updated
5. `updateType` : Not required and is irrelevant for Android. One of the following values:
    1. `DELETE_AND_UPDATE` : this will trigger following action: delete all the files in the pre-defined folder and the
       move the new contents of the zip file to that folder
    2. `ONLY_UPDATE` : this will trigger following action:
        1. If files are present both zip and in the pre-defined folder, then files in pre-defined folder will be
           replaced by zip files
        2. Files which are new and present in the zip but not in the pre-defied folder are copied to the pre-defined
           folder
        3. File which are there in the pre-defined folder but are not there in the zip are kept as it is in the
           pre-defined folder

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved.",
  "code": 20,
  "message": "Notification Sent Successfully to Upgrade Devices"
}
```

#### Add a TPA server

---

For TPA servers to operate we need to provide a server key to them.
To provide this key need to to add a new TPA server.

    /tpa/server [POST]

HEADER

    Content-Type:application/json
    Authorization : Bearer <token> // cust admina and pan admin

QUERY

    NA

BODY

```json
{
  "tpaServerName": "Cool name",  // required
  "tpaServerDescription": "some description",  // optional
  "tpAppId": 13     // required
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "tpaServerId": 12,
    "tpaServerName": "Cool name",
    "tpaServerDescription": "some description",
    "tpaServerKey": "54ff61e3-c9f9-4306-b427-182b9f225c7e"
  },
  "name": "SuccessfullySaved.",
  "code": 20,
  "message": "Notification Sent Successfully to Upgrade Devices"
}
```

#### Reset server key

---

    /tpa/server/reset-key/{tpaServerId} [PUT]

HEADER

    Content-Type:application/json
    Authorization : Bearer <token> // cust admina and pan admin

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "tpaServerId": 12,
    "tpaServerName": "Cool name",
    "tpaServerDescription": "some description",
    "tpaServerKey": "54ff61e3-c9f9-4306-b427-182b9f225c7e"
  },
  "name": "SuccessfullySaved.",
  "code": 20,
  "message": "Notification Sent Successfully to Upgrade Devices"
}
```

#### TPA reports menu API

---

    /tpa/reports [GET]

HEADER

    Content-Type:application/json
    Authorization : Bearer <token> // cust admin and pan admin

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "shouldShowTpaReportsMenu": true|false,
    "toBeShownTpaReportMenuItems": [
      {
        "tpAppId": 22,
        "tpAppName": "Cool TPA"
      },
      {
        "tpAppId": 44,
        "tpAppName": "Nice TPA"
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

For non-admins you will get forbidden response

RESPONSE - code - 403

```json
{
  "result": null,
  "name": "Forbidden",
  "code": 403,
  "message": "You do not have access"
}
```

#### Save TPA app versions

---

    /tpa/versions [PUT]

HEADER

    Content-Type:application/json
    Authorization : Bearer <deviceToken> // only device tokens accepted

QUERY

    NA

BODY

```json
[
  {
    tpAppId: 12,
    tpAppVersion: "1.4.88",
  },
  {
    tpAppId: 13,
    tpAppVersion: "1.4.85",
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Successfully saved"
}
```

#### Get TPA app versions

---

    /reports/tpa-versions [GET]

HEADER

    Content-Type:application/json
    Authorization : Bearer <userToken> // only users can access this

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "tpAppId": {          // optional
        "eq": 12
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "17,48,556,8989,44"
      }
    }
    sort={ // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "tpAppName", "DESC",
      "locationName": "ASC"
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result":{
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83/xls",
    "downloadAsPdf": "http://abc.com/asdsads83/pdf",
    "data": [
      {
        "deviceId": 17,
        "deviceName": "My windows device",
        "locationId": 334,
        "locationName": "Noida",
        "dsAppVersion": "2.11.44",
        "deviceOs": "WINDOWS",
        "tpAppId": 12,
        "tpAppName": "Cool tpa",
        "tpAppVersion": "1.4.88"
      },
      {
        "deviceId": 17,
        "deviceName": "My android device",
        "locationId": 334,
        "locationName": "Noida",
        "dsAppVersion": "2.11.44",
        "deviceOs": "WINDOWS",
        "tpAppId": 13,
        "tpAppName": "Cool tpa",
        "tpAppVersion": "1.4.88"
      }
    ]
  },
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Successfully saved",
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Dashboard customer tiers

---

    /dashboard/customer-tiers [GET]

HEADER

    Content-Type:application/json
    Authorization : Bearer <userToken> // only users can access this

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result":{
    "basicTierCustomersCount": 15,
    "advanceTierCustomersCount": 15,
    "totalCustomersCount": 30
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Report - Basic To Advance switch report

---

    /reports/customer-tiers-switch [GET]

HEADER

    Content-Type:application/json
    Authorization : Bearer <userToken> // only users can access this

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "customerId": {          // optional
        "in": "12,33,55"
      },
      "upgradeOrDowngrade": {    // optional
        "eq": "UPGRADE"          // Enum: possible values: UPGRADE,DOWNGRADE
      }
    }
    sort={ // only one parameter can be sorted at a time more than one are used here for demonstration only
      "date": "ASC",            // default sort = date ASC
      "customerName": "ASC",
      "upgradeOrDowngrade", "DESC"
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result":{
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83/xls",
    "downloadAsPdf": "http://abc.com/asdsads83/pdf",
    "data": [
      {
        "dateTimeOfSwitch": 1554376343383,
        "dateOfSwitch": "21-12-2019",
        "timeOfSwitch": "13:22",
        "customerId": 24,
        "customerName": "Cool customer 1",
        "upgradeOrDowngrade": "DOWNGRADE"
      },
      {
        "dateTimeOfSwitch": 1554376343383,
        "dateOfSwitch": "21-12-2019",
        "timeOfSwitch": "13:22",
        "customerId": 25,
        "customerName": "Cool customer 2",
        "upgradeOrDowngrade": "UPGRADE"
      }
    ]
  },
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Successfully saved",
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### Report - Third party app playback

---

    /reports/tpapp-content-playback [GET]

HEADER

    Content-Type:application/json
    Authorization : Bearer <userToken> // only users can access this

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date 10 days from start date
        "lte": "2019-02-05"
      },
      "device": {           // optional
        "in": "19,676,434,687"
      },
      "tpAppId": {         // optional
        "eq": 13
      }
    }
    sort={  // only one parameter can be sorted at a time more than one are used here for demonstration only
      "deviceName": "ASC",
      "deviceGroupName": "ASC",
      "date": "ASC",
      "tpAppName": "ASC"
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",   // to re-use for paging and sorting
    "downloadAsXls": "http://abc.com/asdsads83",
    "data": [
      {
        "contentPlaybackTpAppId": 123,
        "deviceId": 3444,
        "deviceName": "Abc",
        "deviceGroupId": 23,
        "deviceGroupName": "world",
        "date": "21/12/2018",
        "startTime": "18:00:15",
        "endTime": "12:22:00",
        "tpAppId": 13,
        "tpAppName": "cool tpa"
      },
      {
        "contentPlaybackTpAppId": 123,
        "deviceId": 3444,
        "deviceName": "Abc",
        "deviceGroupId": 23,
        "deviceGroupName": "world",
        "date": "21/12/2018",
        "startTime": "18:00:15",
        "endTime": "12:22:00",
        "tpAppId": 13,
        "tpAppName": "cool tpa"
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

#### TPA build status for TPA team

---

    /tpa/builds/status [GET]

HEADER

    Content-Type:application/json
    Authorization : Bearer <tpaToken> // only tpa servers can access this API

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "tpappId": 1,
      "md5checksum": "E73A1A78A60152FC9D4DFE13A03096CC",
      "updateType": "DELETE_AND_UPDATE",
      "deviceOs": "WINDOWS",
      "buildDownloadUrl": "http://download.com/1",
      "tpaBuildState": "NOTIFIED",  // possible values: TO_BE_DOWNLOADED = DS server is yet to download the build,
                                    //                  DOWNLOADED_BUT_TO_BE_NOTIFIED = DS server has downloaded the build but is yet to notfify the devices,
                                    //                  NOTIFIED = DS server has downloaded the build and notified the devices,
                                    //                  MD5_CHECKSUM_MISMATCH = DS server has downloaded the build but there is a mismatch of MD5 checksum,
                                    //                  DOWNLOAD_FAILING = DS server could not download the build from the given url,
                                    //                  SAVING_BUILD_FAILED = DS server downloaded the build, the MD5 also matched but DS server is unable to save the build file in desired directory or S3 bucket
      "buildDate": "2019-05-15 09:46 UTC",
      "listOfDeviceIds": [
        175, 899
      ]
    },
    {
      "tpappId": 1,
      "md5checksum": "E73A1A78A60152FC9D4DFE13A03096AA",
      "updateType": "DELETE_AND_UPDATE",
      "deviceOs": "WINDOWS",
      "buildDownloadUrl": "http://download.com/2",
      "tpaBuildState": "NOTIFIED",
      "buildDate": "2019-05-15 09:44 UTC",
      "listOfDeviceIds": [
        453
      ]
    },
    {
      "tpappId": 1,
      "md5checksum": "E73A1A78A60152FC9D4DFE13A03096UU",
      "updateType": "DELETE_AND_UPDATE",
      "deviceOs": "WINDOWS",
      "buildDownloadUrl": "http://download.com/3",
      "tpaBuildState": "NOTIFIED",
      "buildDate": "2019-05-15 09:02 UTC",
      "listOfDeviceIds": [
        8989, 175
      ]
    }
  ],
  "pagination": null,
  "name": null,
  "code": null,
  "message": null
}
```

#### SSO - Generate a randomId and token pair

---

    /token-pairing [POST]

HEADER

    Content-Type:application/json
    Authorization : Bearer <userToken> // only DS angular app user token allowed

QUERY

    NA

BODY

```json
{
  "randomId": "0072cf3d-5344-42d7-b9b7-bda9dc8aca19" // required field. Also this should be at-least 36 chars long
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "RandomIdSaved",
  "code": 200,
  "message": "Random ID saved successfully"
}
```

#### SSO - Get PDN app authToken

---

To be called by PDN web-app

    /token-pairing [GET]

HEADER

    Content-Type:application/json

QUERY

    random-id=0072cf3d-5344-42d7-b9b7-bda9dc8aca19  // make sure this is URL encoded

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "authToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
    "customerIdToNavigateTo": 44, // can be null
    "tokenExpiryTime": 1559891743000,   // this is Unix Epoch in milliseconds. Exactly at this time the auth expires
    "user": {
      "userId": 7,
      "fullName": "MS Dhoni",
      "email": "ms.dhoni@gmail.com",
      "roleIds": [
        1
      ],
      "roles": [
        {
          "roleId": 1,
          "roleName": "PANASONIC_ADMIN"
        }
      ],
      "customers": [
        {
          "custName": "Dev",
          "customerId": 12
        },
        {
          "custName": "OK",
          "customerId": 13
        }
      ]
    }
  },
  "name": "LoginSuccess",
  "code": 200,
  "message": "Successful login"
}
```

#### SSO - Get PDN app authToken

---

To be called by PDN web-app

    /who-am-i [GET]

HEADER

    Content-Type:application/json
    Authorization : Bearer <authToken>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "userId": 7,
    "fullName": "MS Dhoni",
    "email": "ms.dhoni@gmail.com",
    "roleIds": [
      1
    ],
    "roles": [
      {
        "roleId": 1,
        "roleName": "PANASONIC_ADMIN"
      }
    ],
    "customers": [
      {
        "custName": "Dev",
        "customerId": 12
      },
      {
        "custName": "OK",
        "customerId": 13
      }
    ]
  },
  "name": "WhoAmIData",
  "code": 200,
  "message": "who am I data sent"
}
```

#### Customer Self Registration

---

To be called by PDN web-app. No Authentication required

    /customer/self-registration [POST]

HEADER

    Content-Type:application/json

QUERY

    NA

BODY

```json
{
  "custName": "string",                   // required
  "contactNo": "string",                  // required
  "address": "string",                    // optional
  "alternatePhoneNumber": "3423432432",   // optional
  "contactNoCountryCode": "+91",          // optional
  "alternateNumberCountryCode": "+91",    // optional
  "panNumber": "ATALL7887L",              // required
  "numberOfDevices": 12,                  // required
  "pocName": "Cool name",                 // required
  "pocEmail": "abc@gmail.com",            // required
  "pocPhoneNumber": "9899998999",         // required
  "pocNoCountryCode": "+91",              // required
  "dateOfPurchaseOfPanel": "12-12-2012"   // optional
}
```

RESPONSE - code - 200

```json
{
  "result": "Customer registration successful",
  "name": "CustomerRegistration",
  "code": 200,
  "message": "Customer registration successful"
}
```

#### Self Registered customers list

---

Self registered customers list

    /customer/self-registration [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <userToken>  // pan admin token only

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "selfRegisteredCustomerId" : 123
      "custName": "Cool customer",
      "address": "string",
      "alternatePhoneNumber": "3423432432",
      "contactNoCountryCode": "+91",
      "alternateNumberCountryCode": "+91",
      "panNumber": "ATALL7887L",
      "numberOfDevices": 12,
      "pocName": "Cool name",
      "pocEmail": "abc@gmail.com",
      "pocPhoneNumber": "9899998999",
      "pocNoCountryCode": "+91",
      "dateOfPurchaseOfPanel": "12-12-2012",
      "actionTaken": "PENDING",                 // possible values PENDING, REJECTED, APPROVED, ON_HOLD
      "timeOfRequest": 89329827982475  // unix epoch in milliseconds
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Self Registered customers updation

---

Self registered customers list

    /customer/self-registration/<selfRegistredCustomerId> [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer <userToken>  // pan admin token only

QUERY

    NA

BODY

```json
{
  "custName": "Cool customer",
  "address": "string",
  "alternatePhoneNumber": "3423432432",
  "contactNoCountryCode": "+91",
  "alternateNumberCountryCode": "+91",
  "panNumber": "ATALL7887L",
  "numberOfDevices": 12,
  "licenceStartDate":"1517824357000",      // unix epoc in millis
  "licenceEndDate":"1517824357000",        // unix epoc in millis
  "pocName": "Cool name",
  "pocEmail": "abc@gmail.com",
  "pocPhoneNumber": "9899998999",
  "pocNoCountryCode": "+91",
  "dateOfPurchaseOfPanel": "12-12-2012",
  "actionTaken": "PENDING"                 // possible values PENDING, REJECTED, APPROVED, ON_HOLD
}
```

RESPONSE - code - 200

```json
{
  "result": [
    {
      "selfRegisteredCustomerId" : 123
      "custName": "Cool customer",
      "address": "string",
      "alternatePhoneNumber": "3423432432",
      "contactNoCountryCode": "+91",
      "alternateNumberCountryCode": "+91",
      "panNumber": "ATALL7887L",
      "numberOfDevices": 12,
      "pocName": "Cool name",
      "pocEmail": "abc@gmail.com",
      "pocPhoneNumber": "9899998999",
      "pocNoCountryCode": "+91",
      "dateOfPurchaseOfPanel": "12-12-2012",
      "actionTaken": "PENDING",                 // possible values PENDING, REJECTED, APPROVED, ON_HOLD
      "timeOfRequest": 89329827982475  // unix epoch in milliseconds
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Get Reports URL for PDN

---

Self registered customers list

    /reports/pdn-url [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <userToken>  // pan admin token only
    customerId: 12  // optional for customer Admins & Panasonic users should include it if they are inside a customer

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": "https://reports.abc.com/aaaa",
  "name": "ReportsURL",
  "code": 200,
  "message": "Reports url received"
}
```

#### Panasonic dashboard basic vs advance

---

    /dashboard/basic-vs-advance [GET]

Note: Only PAN admin can see this graph

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "numberOfBasicCustomers": 10,
    "numberOfAdvanceCustomers": 20,
    "totalNumberOfCustomers": 30
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Panasonic dashboard basic to advance conversion

---

    /dashboard/basic-to-advance-conversion [GET]

Note: Only PAN admin can see this graph

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "basicToAdvanceConvertedCustomersCount": 10,
    "restOfTheCustomersCount": 20,
    "totalNumberOfCustomersThatStartedAsBasic": 30
  },
  "message": null,
  "code": null,
  "name": null
}
```

#### Upload local server JAR

---

    /app-upgrade/local-server/notify [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // pan admin only

QUERY

    NA

BODY

Muiltipart form
key: build = <build file>
key: payload = see json below

```json
{
  "md5checksum": "lksjiofr3j309fj3490jg03",
  "version": "1.1.1"
}
```

Note: This API will be called only on prod server and not on onpremises servers.
Prod server should save the build file on file system / S3 and update reocrd in DB.
After this server should send two pushes on RabbitMQ, one for onpremise server and
another for local servers.

RESPONSE - code - 200

```json
{
  "result": null,
  "message": null,
  "code": null,
  "name": "Build saved"
}
```

#### Local server build version

---

    /app-upgrade/local-server [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token} // pan admin only

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "md5checksum": "lksjiofr3j309fj3490jg03",
    "version": "1.1.1",
    "buildDownloadUrl": "http://downloadurl"
  },
  "message": null,
  "code": null,
  "name": "Build saved"
}
```

## Local server APIs: Request for a file download

    /localserver/file-download-request [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "downloadFileType" : "TPA_BUILD",                              // possible values DEVICE_BUILD, CONTENT and TPA_BUILD
  "deviceOs" : "ANDROID",                                        // required for TPA_BUILD possible values WINDOWS, ANDROID and LINUX
  "buildOs" : "ANDROID_WATCH_DOG",                               // required for DEVICE_BUILD possible values WINDOWS, ANDROID, ANDROID_WATCH_DOG, DESKTOP_APP_SERVER, DESKTOP_APP_CLIENT and DESKTOP_APP_NATIVE
  "tpappId" : 12,                                                // required for TPA_BUILD
  "appVersion" : "1.4.33",                                       // required for DEVICE_BUILD and TPA_BUILD
  "tpaBuildId" : 1232,                                           // required for TPA_BUILD
  "downloadLink" : "http://mydownload.link",                     // required for DEVICE_BUILD and TPA_BUILD
  "md5sum" : "ab1620ff579d81b01449c7034a0c7b1a",                 // required for DEVICE_BUILD and TPA_BUILD
  "downloadRequestId" : "71f1f0c3-a6dc-41cf-945c-6db680506aad"   // required for CONTENT, DEVICE_BUILD and TPA_BUILD
}
```

RESPONSE - 200 - OK

```json
{
  "result": null,
  "name" : null,
  "code": 1,
  "message" : "Fetching files"
}
```

Once the download is success a push notification for file download completion will be sent having PUSH ID
ID_CLIENT_LOCAL_SERVER_HAS_DOWNLOADED_FILES

## Local server APIs: File download

    /localserver/file-download [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "downloadFileType" : "TPA_BUILD",                              // possible values DEVICE_BUILD, CONTENT and TPA_BUILD
  "deviceOs" : "ANDROID",                                        // required for TPA_BUILD possible values WINDOWS, ANDROID and LINUX
  "buildOs" : "ANDROID_WATCH_DOG",                               // required for DEVICE_BUILD possible values WINDOWS, ANDROID, ANDROID_WATCH_DOG, DESKTOP_APP_SERVER, DESKTOP_APP_CLIENT and DESKTOP_APP_NATIVE
  "tpappId" : 12,                                                // required for TPA_BUILD
  "appVersion" : "1.4.33",                                       // required for DEVICE_BUILD and TPA_BUILD
  "tpaBuildId" : 1232,                                           // required for TPA_BUILD
  "downloadLink" : "http://mydownload.link",                     // required for DEVICE_BUILD and TPA_BUILD
  "md5sum" : "ab1620ff579d81b01449c7034a0c7b1a",                 // required for DEVICE_BUILD and TPA_BUILD
  "downloadRequestId" : "71f1f0c3-a6dc-41cf-945c-6db680506aad"   // required for CONTENT, DEVICE_BUILD and TPA_BUILD
}
```

RESPONSE - code - 200

```json
File bytes
```

#### Panel control

---

    /panel-control [GET]

HEADER

    NA

QUERY

    language=en   // optional - if not present then en is default

RESPONSE - CODE - 200

```json
{
  "result": {
    "RJ45": "RJ45",
    "RJ45_QM": "RJ45 - QM",
    "PANEL_OTHERS": "RS-232 - Others",
    "PROJECTOR_RZ660": "RS-232 - Projector RZ-670",
    "PROJECTOR_RZ660_WITH_ARDUINO": "RS-232 - Projector RZ-670 With Arduino",
    "PROJECTOR_TX410D": "RS-232 - Projector TX410D",
    "PROJECTOR_TX410D_WITH_ARDUINO": "RS-232 - Projector TX410D With Arduino",
    "RS232_QM": "RS-232 - QM",
    "RS232_RM_HDMI_ONLY": "RS-232 - RM/HDMI only",
    "RS232_UMP": "RS-232 - UMP",
    "RS232_VIDEO_WALL": "RS-232 - Video Wall"
  },
  "pagination": null,
  "name": null,
  "code": null,
  "message": null
}
```

#### Add master key

---

    /master-key [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "masterKeyName": "My cool key",
  "masterValues": [
    {
      "masterKeyId" : null, // null or do not send
      "masterValue": "My cool value 1"
    },
    {
      "masterKeyId" : null, // null or do not send
      "masterValue": "My cool value 2"
    }
  ]
}
```

RESPONSE - CODE - 200

```json
{
  "result": {MasterKeyModel},
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Edit master key

---

    /master-key/<masterKeyId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "masterKeyName": "My cool key",
  "masterValues": [  // missing values in this array will be deleted
    {
      "masterValueId" : 1,
      "masterValue": "My new cool value 1" // changing new value
    },
    {
      "masterValueId" : null,
      "masterValue": "My cool value 3"  // adding a new value
    }
  ]
}

```

RESPONSE - CODE - 200

```json
{
  "result": {MasterKeyModel},
  "name": "SuccessfullyUpdated",
  "code": 1,
  "message": "Updated Successfully"
}
```

#### Delete a master key

---

    /master-key/<masterKeyId> [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": null,
  "name": "SuccessFullyDeleted",
  "code": 22,
  "message": "Record Deleted Successfully"
}
```

#### Get all master keys

---

    /master-key [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "masterKeyId": 1,
      "masterKeyName": "My cool key 1",
      "masterValues": [
        {
          "masterValueId" : 1,
          "masterValue": "My new cool value 1"
        },
        {
          "masterValueId" : 2,
          "masterValue": "My cool value 3"
        }
      ]
    },
    {
      "masterKeyId": 2,
      "masterKeyName": "My cool key 2",
      "masterValues": [
        {
          "masterValueId" : 3,
          "masterValue": "My new cool value 1"
        },
        {
          "masterValueId" : 4,
          "masterValue": "My cool value 3"
        }
      ]
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Get master key by ID

---

    /master-key/<masterKeyId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": {
    "masterKeyId": <masterKeyId>,   // long
    "masterKeyName": "My cool key 1",
    "masterValues": [
      {
        "masterValueId" : 1,
        "masterValue": "My new cool value 1"
      },
      {
        "masterValueId" : 2,
        "masterValue": "My cool value "
      }
    ]
  }
  "name": null,
  "code": null,
  "message": null
}
```

#### Add dynamic image key value mapping

---

    /dynamic-image-key-value-mapping [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "title": "Cool keys",
  "defaultImageContentId": 14
}
```

RESPONSE - code - 200

```json
{
  "result": "SuccessfullySaved",
  "name": "SuccessFullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

#### Edit dynamic image key value mapping

---

    /dynamic-image-key-value-mapping/<id> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "title": "Cool keys",
  "defaultImageContentId": 14
}
```

RESPONSE - code - 200

```json
{
  "result": "SuccessfullyUpdated",
  "name": "SuccessfullyUpdated",
  "code": 1,
  "message": "Record Updated Successfully"
}
```

#### Delete dynamic image key value mapping

---

    /dynamic-image-key-value-mapping/<id> [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": "SuccessfullyDeleted",
  "name": "SuccessfullyDeleted",
  "code": 1,
  "message": "Record Deleted Successfully"
}
```

#### Get dynamic image key value mapping by ID

---

    /dynamic-image-key-value-mapping/<id> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "dynamicImageKeyValueMappingId": 12,
    "defaultImageContentId": 14,
    "title": "Cool keys",
    "status": 1
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get dynamic image key value mapping list

---

    /dynamic-image-key-value-mapping [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "dynamicImageKeyValueMappingId": 12,
      "title": "Cool keys",
      "defaultImageContentId": 14,
      "status": 1
    },
    {
      "dynamicImageKeyValueMappingId": 34,
      "title": "My keys",
      "defaultImageContentId": 14,
      "status": 1
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Get dynamic image key value mapping keys

---

    /dynamic-image-key-value-mapping-key [GET]

QUERY

    dynamicImageKeyValueMappingId=12 // required

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "dynamicImageKeyValueMappingId": 12,
    "masterKeyIds": [
      1,
      2,
      4
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Add dynamic image key

---

    /dynamic-image-key-value-mapping-key [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "dynamicImageKeyValueMappingId": 12,
  "key": {
    "masterKeyId": 1, // master key id
    "valuesForExistingRecords": [
      {
        "id" : 2,
        "masterValueId": 18
      },
      {
        "id" : 3,
        "masterValueId": 87
      },
      {
        "id" : 4,
        "masterValueId": 43
      }
    ]
  }
}
```

RESPONSE - code - 200

```json
{
  "result": "SuccessfullyAdded",
  "name": "SuccessfullyAdded",
  "code": 1,
  "message": "Key Added Successfully"
}
```

#### Delete a dynamic image key

---

    /dynamic-image-key-value-mapping-key [DELETE]

This API can be used for changing the name of the key

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "dynamicImageKeyValueMappingId": 12,
  "key": {
    "masterKeyId": 4,
    "idsOfDuplicateRecordsToBeDeleted": [
      4,
      5,
      9
    ]
  }
}
```

RESPONSE - code - 200

```json
{
  "result": "SuccessfullyDeleted",
  "name": "SuccessfullyDeleted",
  "code": 1,
  "message": "Key Deleted Successfully"
}
```

#### Add dynamic image key value mapping Value(s)

---

    /dynamic-image-key-value-mapping-value [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "dynamicImageKeyValueMappingId" : 12,  // required
  "dynamicImageKeyValueMappingValues": [   // required
    {
      "1" : 12,  // required - json key is the master Key Id and json value is the master Value
      "2" : 18,  // required - json key is the master Key Id and json value is the master Value
      "18": 1233,  // required - json key is the master Key Id and json value is the master Value
      "imageContentId": 632 // required
    },
    {
      "1" : 17,  // required
      "2" : 118,  // required
      "18": 190,  // required
      "imageContentId": 656 // required
    },
    {
      "1" : 14,  // required
      "2" : 114,  // required
      "18": 1237,  // required
      "imageContentId": 892 // required
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "dynamicImageKeyValueMappingId": 12,
    "dynamicImageKeyValueMappingValues": [
      { // existing
        "id": 90,
        "1" : 199,
        "2" : 148,
        "18": 2398,
        "imageContentId": 67
      },
      {  // just added
        "id": 91,
        "1" : 12,
        "2" : 18,
        "18": 1233,
        "imageContentId": 632
      },
      { // just added
        "id": 92,
        "1" : 17,
        "2" : 118,
        "18": 190,
        "imageContentId": 656
      },
      { // just added
        "id": 93,
        "1" : 14,
        "2" : 114,
        "18": 1237,
        "imageContentId": 892
      }
    ]
  },
  "name": "SuccessfullySaved",
  "code": 1,
  "message": "Record Saved Successfully"
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "name": "KeysMismatch",
  "code": 1,
  "message": "Keys do not match"
}
```

RESPONSE - code - 404

```json
{
  "result": null,
  "name": "DynamicImageKeyValueMappingIdNotFound",
  "code": 1,
  "message": "dynamicImageKeyValueMappingId not found"
}
```

#### Get dynamic image key value mapping Values

---

    /dynamic-image-key-value-mapping-value [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    dynamicImageKeyValueMappingId=12

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "dynamicImageKeyValueMappingId": 12,
    "dynamicImageKeyValueMappingValues": [
      {
        "id": 90, // id
        "1" : 199,  // json key is the master Key Id and json value is the master Value
        "2" : 148,  // json key is the master Key Id and json value is the master Value
        "18": 2398, // json key is the master Key Id and json value is the master Value
        "imageContentId": 67
      },
      {
        "id": 91,
        "1" : 12,
        "2" : 18,
        "18": 1233,
        "imageContentId": 632
      },
      {
        "id": 92,
        "1" : 17,
        "2" : 118,
        "18": 190,
        "imageContentId": 656
      },
      {
        "id": 93,
        "1" : 14,
        "2" : 114,
        "18": 1237,
        "imageContentId": 892
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Update dynamic image key value mapping Values

---

    /dynamic-image-key-value-mapping-value [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "dynamicImageKeyValueMappingId" : 12,  // required
  "dynamicImageKeyValueMappingValues": [
    {
      "id": 1,   // required
      "1" : 14,  // required
      "2" : 114, // required
      "18": 1237,  // required
      "imageContentId": 1233 // required
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": {Result will be same as POST api},
  "name": "DataUpdated",
  "code": 1,
  "message": "Data updated"
}
```

#### Delete dynamic image key value mapping Values

---

    /dynamic-image-key-value-mapping-value [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "dynamicImageKeyValueMappingId" : 12,  // required
  "valueIdsToBeDeleted": [ 1, 18, 92 ]   //required
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "ValuesDeleted",
  "code": 1,
  "message": "Values deleted"
}
```

### Combined APIs

---

#### Dynamic Key Value Pair Add / Update / Delete

---

    /combined/dynamic-image-key-value-mapping [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "dynamicImageKeyValueMappingId": 12,
  "defaultImageContentId": 14,
  "title": "Cool keys",
  "keyOperations": [
    {
      "masterKeyId": 1,
      "operation": "ADD",
      "valuesForExistingRecords": [
        {
          "id": 2,
          "masterValueId": 18
        },
        {
          "id": 3,
          "masterValueId": 87
        },
        {
          "id": 4,
          "masterValueId": 43
        }
      ]
    },
    {
      "masterKeyId": 4,
      "operation": "DELETE",
      "idsOfDuplicateRecordsToBeDeleted": [
        4,
        5,
        9
      ]
    }
  ],
  "valuesOperations": [
    {
      "1": 12,
      "2": 18,
      "18": 1233,
      "id": null,
      "operation": "ADD",
      "imageContentId": 632
    },
    {
      "1": 14,
      "2": 114,
      "18": 1237,
      "id": 31,
      "operation": "EDIT",
      "imageContentId": 1233
    },
    {
      "id": 35,
      "operation": "DELETE"
    }
  ]
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "dynamicImageKeyValueMappingId": 12,
    "defaultImageContentId": 14,
    "title": "Cool keys",
    "status": 1,
    "masterKeyIds": [
      1,
      2,
      4
    ],
    "dynamicImageKeyValueMappingValues": [
      {
        "1": 199,
        "2": 148,
        "18": 2398,
        "id": 90,
        "imageContentId": 67
      },
      {
        "1": 12,
        "2": 18,
        "18": 1233,
        "id": 91,
        "imageContentId": 632
      },
      {
        "1": 17,
        "2": 118,
        "18": 190,
        "id": 92,
        "imageContentId": 656
      },
      {
        "1": 14,
        "2": 114,
        "18": 1237,
        "id": 93,
        "imageContentId": 892
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get dynamic key value mapping combined

---

    /combined/dynamic-image-key-value-mapping/<dynamicImageKeyValueMappingId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "dynamicImageKeyValueMappingId": 12,
    "defaultImageContentId": 14,
    "title": "Cool keys",
    "status": 1,
    "masterKeyIds": [
      1,
      2,
      4
    ],
    "dynamicImageKeyValueMappingValues": [
      {
        "1": 199,
        "2": 148,
        "18": 2398,
        "id": 90,
        "imageContentId": 67
      },
      {
        "1": 12,
        "2": 18,
        "18": 1233,
        "id": 91,
        "imageContentId": 632
      },
      {
        "1": 17,
        "2": 118,
        "18": 190,
        "id": 92,
        "imageContentId": 656
      },
      {
        "1": 14,
        "2": 114,
        "18": 1237,
        "id": 93,
        "imageContentId": 892
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get key values for device

---

    /combined/device/dynamic-image-key-value-mapping/<dynamicImageKeyValueMappingId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "dynamicImageKeyValueMappingId": 12,
    "defaultImageContentId": 14,
    "title": "Cool keys",
    "dynamicImageKeyValueMappingValues": [
      {
        "keyName1": "valueName1",
        "keyName2": "valueName2",
        "keyName3": "valueName3",
        "id": 90,
        "imageContentId": 67
      },
      {
        "keyName1": "valueName1",
        "keyName2": "valueName2",
        "keyName3": "valueName3",
        "id": 91,
        "imageContentId": 632
      },
      {
        "keyName1": "valueName1",
        "keyName2": "valueName2",
        "keyName3": "valueName3",
        "id": 92,
        "imageContentId": 656
      },
      {
        "keyName1": "valueName1",
        "keyName2": "valueName2",
        "keyName3": "valueName3",
        "id": 93,
        "imageContentId": 892
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Welcome Message

---

#### Get Welcome message By ID

---

    /welcome-message/<welcomeMessageId> [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "welcomeMessageId": 12,
    "welcomeMessageName": "Welome Ashok Kumar",
    "welcomeMessageValue" : "Welcome {{name}} to MG motors {{location}} showroom",
    "welcomeTemplate": "NO_IMAGE", // POSSIBLE VALUES : IMAGE_LEFT_OF_TEXT, IMAGE_RIGHT_OF_TEXT, IMAGE_ON_TOP_OF_TEXT, IMAGE_BOTTOM_OF_TEXT
    "background": "BACKGROUND_IMAGE", // POSSIBLE VALUES : BACKGROUND_COLOR or BACKGROUND_IMAGE
    "bgColor": "#FF0000", // hex code for color
    "bgImageContentId": 13, // contentId of an image from media lib
    "fontId": 12,
    "fontSizeInPixels": 15,
    "fontColor": "#0000FF",  // hex code for color
    "alignment": "LEFT", // possible values: CENTRE, LEFT, RIGHT
    "imageSource": "KEY_VALUES", // possible values: KEY_VALUES, JSON
    "dynamicImageKeyValueMappingId": 15
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Get all Welcome messages

---

    /welcome-message [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "welcomeMessageId": 12,
      "welcomeMessageName":"Welome Ashok Kumar",
      "welcomeMessageValue" : "Welcome {{name}} to MG motors {{location}} showroom",
      "welcomeTemplate": "NO_IMAGE", // POSSIBLE VALUES : IMAGE_LEFT_OF_TEXT, IMAGE_RIGHT_OF_TEXT, IMAGE_ON_TOP_OF_TEXT, IMAGE_BOTTOM_OF_TEXT
      "background": "BACKGROUND_IMAGE", // POSSIBLE VALUES : BACKGROUND_COLOR or BACKGROUND_IMAGE
      "bgColor": "#FF0000", // hex code for color
      "bgImageContentId": 13, // contentId of an image from media lib
      "fontId": 12,
      "fontSizeInPixels": 15,
      "fontColor": "#0000FF",  // hex code for color
      "alignment": "LEFT", // possible values: CENTRE, LEFT, RIGHT
      "imageSource": "KEY_VALUES", // possible values: KEY_VALUES, JSON
      "dynamicImageKeyValueMappingId": 15
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Add new Welcome message

---

    /welcome-message [POST]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "welcomeMessageName":"Welome Ashok Kumar",
  "welcomeMessageValue" : "Welcome {{name}} to MG motors {{location}} showroom",
  "welcomeTemplate": "NO_IMAGE", // POSSIBLE VALUES : IMAGE_LEFT_OF_TEXT, IMAGE_RIGHT_OF_TEXT, IMAGE_ON_TOP_OF_TEXT, IMAGE_BOTTOM_OF_TEXT
  "background": "BACKGROUND_IMAGE", // POSSIBLE VALUES : BACKGROUND_COLOR or BACKGROUND_IMAGE
  "bgColor": "#FF0000", // hex code for color
  "bgImageContentId": 13, // contentId of an image from media lib
  "fontId": 12,
  "fontSizeInPixels": 15,
  "fontColor": "#0000FF",  // hex code for color
  "alignment": "LEFT", // possible values: CENTRE, LEFT, RIGHT
  "imageSource": "KEY_VALUES", // possible values: KEY_VALUES, JSON
  "dynamicImageKeyValueMappingId": 15
}
```

RESPONSE - code - 200

```json
{
  "result": {WelcomeMessageModel},
  "name": "SuccessfullyAdded",
  "code": 1,
  "message": "Welcome message successfully added"
}
```

#### Update Welcome message

---

    /welcome-message/<welcomeMessageId> [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

```json
{
  "welcomeMessageName":"Welome Ashok Kumar",
  "welcomeMessageValue" : "Welcome {{name}} to MG motors {{location}} showroom",
  "welcomeTemplate": "NO_IMAGE", // POSSIBLE VALUES : IMAGE_LEFT_OF_TEXT, IMAGE_RIGHT_OF_TEXT, IMAGE_ON_TOP_OF_TEXT, IMAGE_BOTTOM_OF_TEXT
  "background": "BACKGROUND_IMAGE", // POSSIBLE VALUES : BACKGROUND_COLOR or BACKGROUND_IMAGE
  "bgColor": "#FF0000", // hex code for color
  "bgImageContentId": 13, // contentId of an image from media lib
  "fontId": 12,
  "fontSizeInPixels": 15,
  "fontColor": "#0000FF",  // hex code for color
  "alignment": "LEFT", // possible values: CENTRE, LEFT, RIGHT
  "imageSource": "KEY_VALUES", // possible values: KEY_VALUES, JSON
  "dynamicImageKeyValueMappingId": 15
}
```

RESPONSE - code - 200

```json
{
  "result": {WelcomeMessageModel},
  "name": "SuccessfullyUpdated",
  "code": 1,
  "message": "Welcome message successfully updated"
}
```

#### Delete Welcome message

---

    /welcome-message/<welcomeMessageId> [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": "SuccessfullyDeleted",
  "name": "SuccessfullyDeleted",
  "code": 1,
  "message": "Welcome message successfully deleted"
}
```

#### Onboarding a camera

---

    /camera [PUT]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}  // user token

QUERY

    NA

BODY

```json
{
  "deviceId": 12,      // either deviceId
  "cameraId": 144,     // or cameraId is required
  "cameraType": "USB", // possible values USB or IP or NO_CAM
  "demographicPlayback": true
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "cameraId": 144,
    "deviceId": 12,
    "cameraType": "USB", // possible values USB or IP, or NO_CAM
    "demographicPlayback": true
  },
  "name": "SuccessfullyAdded",
  "code": 1,
  "message": "Camera added"
}
```

#### Get a camera by deviceId or cameraId

---

    /camera [GET]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}  // device token or user token

QUERY

    cameraId=12   either one is required
    deviceId=13   either one is required

BODY

NA

RESPONSE - code - 200

```json
{
  "result": {
    "cameraId": 12,
    "cameraType": "USB", // possible values USB or IP or NO_CAM
    "demographicPlayback": true
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Delete a camera by deviceId or cameraId

---

    /camera [DELETE]

HEADER

    Content-Type:application/json
    Authorization:Bearer {token}  // user token

QUERY

    NA

BODY

```json
{
  "cameraId": 12,   // either one is required
  "deviceId": 124   // either one is required
}
```

RESPONSE - code - 200

```json
{
  "result": "Camera deleted",
  "name": "SuccessfullyDeleted",
  "code": 1,
  "message": "Camera deleted"
}
```

#### Get environment level properties

---

    /environment-properties [GET]

Note: No authentication required

HEADER

    Content-Type:application/json

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "enableDemographic" : true, // or false
    "enableDesktopRegistration": true // or false
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

#### Get devices having cameras per customer

---

    /devices-with-camera [GET]

Note: No authentication required

HEADER

    Content-Type:application/json
    customerId: 123

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "deviceId": 12,
      "deviceName": "hello"
    },
    {
      "deviceId": 13,
      "deviceName": "world"
    }
  ],
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

#### Get devices having camera playback

---

    /devices-with-camera-playback [GET]

Note: No authentication required

HEADER

    Content-Type:application/json
    customerId: 123

QUERY

    locationIds=74,454,566   // optional

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "deviceId": 12,
      "deviceName": "hello"
    },
    {
      "deviceId": 13,
      "deviceName": "world"
    }
  ],
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

### Demography API version

---

    /demography/lib-version [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <device token>  // only for devices

BODY

```json
{
  "libraryName": "PANASONIC",
  "dsDefinedVersion": "1"
}
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "",
  "code": 20,
  "message": "Saved successfully"
}
```

### Demography

---

    /demography [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <device token>  // only for devices

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "demographyName": "AGE",  // appropriate display name can be found in localization API
      "isRange": true,
      "demographyValues": [
        "0-5",
        "6-10",
        "11-15"
      ]
    },
    {
      "demographyName": "GENDER",
      "isRange": false,
      "demographyValues": [
        "MALE",
        "FEMALE",
        "UNKNOWN"
      ]
    },
    {
      "demographyName": "EMOTION",
      "isRange": false,
      "demographyValues": [
        "HAPPY",
        "SAD",
        "ANGRY"
      ]
    }
  ],
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

### Demography

---

    /demography/web [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>  // only for admin users only

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "demographyId": 1,
      "demographyName": "AGE",  // appropriate display name can be found in localization API
      "demographyValues": [
        {
          "id": 1,
          "value": "0-5"
        },
        {
          "id": 2,
          "value": "6-10"
        },
        {
          "id": 3,
          "value": "11-15"
        }
      ]
    },
    {
      "demographyId": 2,
      "demographyName": "GENDER",
      "demographyValues": [
        {
          "id": 1,
          "value": "MALE"
        },
        {
          "id": 1,
          "value": "FEMALE"
        },
        {
          "id": 1,
          "value": "UNKNOWN"
        }
      ]
    },
    {
      "demographyId": 3,
      "demographyName": "EMOTION",
      "demographyValues": [
        {
          "id": 1,
          "value": "HAPPY"
        },
        {
          "id": 1,
          "value": "SAD"
        },
        {
          "id": 1,
          "value": "ANGRY"
        }
      ]
    }
  ],
  "name": null,
  "code": null,
  "message": null,
  "pagination": null
}
```

#### Save demographic data error data

---

    /analytics/demography/error [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <device token>

QUERY

    NA

BODY

```json
[
  {
    cameraId: 12,
    downtimeStart: 1571038823123,
    downtimeEnd: 1571038823777,
    reasonForDownTime: "COULD_NOT_FETCH_DATA_FROM_CAMERA", // possible values COULD_NOT_FETCH_DATA_FROM_CAMERA, NO_PERSON_STANDING
  },
  {
    cameraId: 12,
    downtimeStart: 1571038823123,
    downtimeEnd: 1571038823777,
    reasonForDownTime: "NO_PERSON_STANDING", // possible values COULD_NOT_FETCH_DATA_FROM_CAMERA, NO_PERSON_STANDING
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Successfully saved"
}
```

#### Demography content playback

---

    /analytics/demography/content-playback [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <device token>

QUERY

    NA

BODY

```json
[
  {
    startTimeOfCampaign: 1587989797997, // unix epoch in milliseconds
    endTimeOfCampaign: 1587989797998, // unix epoch in milliseconds
    ruleId: 15, // due to which this was triggered
    campaignId: 84,
    regionId: 243, // can be null in case of audio playlist
    contentId: 56,
    playbackStatus: "YES|NO",
    contentLastPlayed: 1587989797998, // unix epoch in milliseconds
    reasonForFailure: "",
    widgetType: null, // possible values: CLOCK, WEATHER, CALENDAR, ADVERTISEMENT, HDMI-IN
    ageActual: 19.6,
    emotion: 4,
    gender: "MALE",
    emotionBeforeCampaignStart: 3,
    //  -1 : no detection
    //  0 : anger
    //  1 : disgusting
    //  2 : fear
    //  3 : happy
    //  4 : neutral
    //  5 : sad
    //  6 : surprise
    emotionAfterCampaignEnd: 3,
    //  -1 : no detection
    //  0 : anger
    //  1 : disgusting
    //  2 : fear
    //  3 : happy
    //  4 : neutral
    //  5 : sad
    //  6 : surprise
  },
  {
    startTimeOfCampaign: 1587989797997, // unix epoch in milliseconds
    endTimeOfCampaign: 1587989797998, // unix epoch in milliseconds
    ruleId: 15,
    campaignId: 84,
    regionId: 243, // can be null in case of audio playlist
    contentId: 56,
    playbackStatus: "YES|NO",
    contentLastPlayed: 1587989797998, // unix epoch in milliseconds
    reasonForFailure: "",
    widgetType: null, // possible values: CLOCK, WEATHER, CALENDAR, ADVERTISEMENT, HDMI-IN
    ageActual: 19.6,
    emotion: 4,
    gender: "MALE",
    emotionBeforeCampaignStart: 3,
    //  -1 : no detection
    //  0 : anger
    //  1 : disgusting
    //  2 : fear
    //  3 : happy
    //  4 : neutral
    //  5 : sad
    //  6 : surprise
    emotionAfterCampaignEnd: 3,
    //  -1 : no detection
    //  0 : anger
    //  1 : disgusting
    //  2 : fear
    //  3 : happy
    //  4 : neutral
    //  5 : sad
    //  6 : surprise
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Successfully saved"
}
```

#### Save demographic data data

---

    /analytics/demography/raw [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <device token>

QUERY

    NA

BODY

```json
[
  {
    "timeOfData": 1571041367192,
    "ID": 1,  // some int value
    "age": 19.6,
    "emotion": 3  // -1 : no detection
                  //  0 : anger
                  //  1 : disgusting
                  //  2 : fear
                  //  3 : happy
                  //  4 : neutral
                  //  5 : sad
                  //  6 : surprise,
    "gender": "FEMALE",  // or MALE, UNKNOWN
    "arousal": 0.9, // possible values: -1.0 to 1.0; need no check meeting audio file
    "valence": -1.0, // possible values: -1.0 to 1.0; need no check meeting audio file
    "pitch": 3.4,
    "yaw": 9.4,
    "roll": 29.42,
    "concentrationLevel": 78.33, // possible values 0 - 100 (in percentage)
    "rightGazeStartPointX": 48.44,
    "rightGazeStartPointY": -90.44,
    "rightGazeEndPointX": 48.44,
    "rightGazeEndPointY": -90.44,
    "leftGazeStartPointX": 408.44,
    "leftGazeStartPointY": -990.44,
    "leftGazeEndPointX": 408.44,
    "leftGazeEndPointY": -990.44
  },
  {
    "timeOfData": 1571041367192,
    "ID": 1,  // some int value
    "age": 19.6,
    "emotion": 3  // -1 : no detection
                  //  0 : anger
                  //  1 : disgusting
                  //  2 : fear
                  //  3 : happy
                  //  4 : neutral
                  //  5 : sad
                  //  6 : surprise,
    "gender": "FEMALE",  // or MALE, UNKNOWN
    "arousal": 0.9, // possible values: -1.0 to 1.0; need no check meeting audio file
    "valence": -1.0, // possible values: -1.0 to 1.0; need no check meeting audio file
    "pitch": 3.4,
    "yaw": 9.4,
    "roll": 29.42,
    "concentrationLevel": 78.33, // possible values 0 - 100 (in percentage)
    "rightGazeStartPointX": 48.44,
    "rightGazeStartPointY": -90.44,
    "rightGazeEndPointX": 48.44,
    "rightGazeEndPointY": -90.44,
    "leftGazeStartPointX": 408.44,
    "leftGazeStartPointY": -990.44,
    "leftGazeEndPointX": 408.44,
    "leftGazeEndPointY": -990.44
  }
]
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Successfully saved"
}
```

#### Save demographic data data

---

    /analytics/demography [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <device token>

QUERY

    NA

BODY

```json
[
  {
    startTimestamp: 1575891754000,
    endTimestamp: 1575891754444,
    gender: "MALE",
    actualAge: 76.5,
    dwellTimeInMillis: 55000,
    layoutIds: [12, 34],
    emotions: [
      {
        count: 148,
        emotion: 1,
      },
      {
        count: 128,
        emotion: 2,
      },
      {
        count: 182,
        emotion: -1,
      },
      {
        count: 418,
        emotion: 6,
      },
    ],
  },
  {
    startTimestamp: 1575891754000,
    endTimestamp: 1575891754444,
    gender: "MALE",
    actualAge: 76.5,
    dwellTimeInSeconds: 55,
    emotions: [
      {
        count: 148,
        emotion: 1,
      },
      {
        count: 128,
        emotion: 2,
      },
      {
        count: 182,
        emotion: -1,
      },
      {
        count: 418,
        emotion: 6,
      },
    ],
  },
  {
    startTimestamp: 1575891754000,
    endTimestamp: 1575891754444,
    gender: "MALE",
    actualAge: 76.5,
    dwellTimeInSeconds: 55,
    emotions: [
      {
        count: 148,
        emotion: 1,
      },
      {
        count: 128,
        emotion: 2,
      },
      {
        count: 182,
        emotion: -1,
      },
      {
        count: 418,
        emotion: 6,
      },
    ],
  },
];
```

RESPONSE - code - 200

```json
{
  "result": null,
  "name": "SuccessfullySaved",
  "code": 20,
  "message": "Successfully saved"
}
```

#### Demographic dashboard

---

    /dashboard/demography [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>
    customerId: 12

QUERY

    q={
      "from_date": {         // required
        "gte": "2018-12-13"
      },
      "to_date": {           // optional - if no date is provided then pick a date X days from start date
        "lte": "2019-02-05"
      },
      "location": {         // optional
        "eq": 112
      },
      "device": {           // optional
        "in": "19,676,434,68"7
      },
      "deviceGroup": {      // optional
        "eq": 12
      }
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "demographyId": 12,
      "demographyName": "AGE",
      "demographyData" : [
        {
          "id": 1,
          "demographyValue": "0-5",
          "demographyPercentage": 12
        },
        {
          "id": 3,
          "demographyValue": "11-15",
          "demographyPercentage": 88
        }
      ]
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Demography Rule complete

---

    /demography/rule/complete [POST]

HEADER

    NA

QUERY

    NA

BODY

```json
{
  "ruleId": 15
}
```

RESPONSE - code - 200

```json
{
  "result": "Rule completed",
  "message": "Rule completed",
  "code": null,
  "name": null,
  "pagination": null
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "message": "Rule could not be completed. Please add atleast one rule association",
  "code": 1,
  "name": "RuleNotCompleted",
  "pagination": null
}
```

#### Demographic rules for devices

---

    /demography/rule/devices [GET]

Note: This API is for devices only

HEADER

    Content-Type:application/json
    Authorization: Bearer <device token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "ruleId": 22,
      "ruleName": "hello",
      "rulePriority": 12,    // lower the value higher the priority
      "ruleDemography": {
        "minAge": 15,
        "maxAge": 20,
        "gender": "MALE", // or FEMALE, UNKNOWN
        "emotion": 3  // -1 : no detection
                      //  0 : anger
                      //  1 : disgusting
                      //  2 : fear
                      //  3 : happy
                      //  4 : neutral
                      //  5 : sad
                      //  6 : surprise
      },
      "ruleSchedule": [
        {
          "ruleId": 22,
          "numberOfLoops": 3,
          "startDate": "2019-04-12",
           "endDate": "2019-04-30",
           "startTime": "17:00:00",
           "endTime": "17:15:00",
           "daysOfWeek": [
              "MON",
              "SAT",
              "SUN"
           ],
           "key": 1,
           "associatedLayoutId": 45
        },
        {
          "ruleId": 22,
          "numberOfLoops": 1,
          "startDate": "2019-04-12",
           "endDate": "2019-04-30",
           "startTime": "17:16:00",
           "endTime": "17:30:00",
           "daysOfWeek": [
              "MON",
              "SAT",
              "FRI"
           ],
            "key": 2,
           "associatedLayoutId": 43
        }
      ]
    }
  ],
  "name": null,
  "code": null,
  "message": null
}
```

#### Temp demography save

---

    /demography/temp [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

```json
{
  "male": 39, // layout ID - if wants to reset existing campaign then send -1, if no change then send null
  "female": 47 // layout ID - if wants to reset existing campaign then send -1, if no change then send null
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "male": 39,
    "female": 47
  },
  "name": null,
  "code": null,
  "message": null
}
```

#### Temp demography get

---

    /demography/temp [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "male": 39,   // or null if nothing is set
    "female": 47  // or null if nothing is set
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Emotion graph report

---

    /reports/fa/emotion-graph [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    ln=EN   // optional - defaults to EN if not provided
    q={
      "from_date": {        // required
        "gte": "2019-12-13"
      },
      "to_date": {          // required
        "lte": "2019-12-14"
      },
      "from_time": {        // optional
        "gte": "10:00"
      },
      "to_time": {          // optional
        "lte": "12:00"
      },
      "deviceOne": {        // required
        "eq": 12,
      },
      "deviceTwo": {
        "eq": 44,           // required
      },
      "layoutId": {      // optional
        "eq": 18
      },
      "gender": {
        "in": "MALE,FEMALE,UNKNOWN"
      },
      "emotion": {
        "in": "HAPPY,SAD"     // possible values NO_DETECTION, ANGER, DISGUSTING, FEAR, HAPPY, NEUTRAL, SAD, SURPRISE
      }
      "age": {
        "in": "AGE_26_30,AGE_46_50"       //  comma separated
      },
      "durationType": {     // required
        "eq" : "DAILY"   // HOURLY, DAILY, WEEKLY, MONTHLY
      }
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "willDataBeAsync": false,        // if false then rest of the data except reportToken will be null
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "reportToken": "2hfh34fjwpoefj9",
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "duration": "2019-12-13",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "devices": [
          {
            "deviceId": 12,
            "deviceName": "Hello",
            "ANGER": 143943033,
            "NO_DETECTION": 124343,
            "DISGUSTING": 124343,
            "HAPPY": 124343,
            "FEAR": 472,
            "NEUTRAL": 3778,
            "SAD": 48349,
            "SURPRISE": 0
          },
          {
            "deviceId": 44,
            "deviceName": "World",
            "ANGER": 143943033,
            "NO_DETECTION": 124343,
            "DISGUSTING": 124343,
            "HAPPY": 124343,
            "FEAR": 472,
            "NEUTRAL": 3778,
            "SAD": 48349,
            "SURPRISE": 0
          }
        ]
      },
      {
        "duration": "2019-12-14",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "devices": [
          {
            "deviceId": 12,
            "ANGER": 143943033,
            "NO_DETECTION": 124343,
            "DISGUSTING": 124343,
            "HAPPY": 124343,
            "FEAR": 472,
            "NEUTRAL": 3778,
            "SAD": 48349,
            "SURPRISE": 0
          },
          {
            "deviceId": 44,
            "ANGER": 143943033,
            "NO_DETECTION": 124343,
            "DISGUSTING": 124343,
            "HAPPY": 124343,
            "FEAR": 472,
            "NEUTRAL": 3778,
            "SAD": 48349,
            "SURPRISE": 0
          }
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Async report - check if it is ready

---

    /reports/async/<reportToken> [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE CODE - 200

```json
{
  "result": {
    "isDataReady": true // or false
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Emotion graph report - ready prepared async data

---

    /reports/fa/emotion-graph/ready [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    report-token=2hfh34fjwpoefj9
    pageNumber=12
    numPerPage=20
    ln=EN   // optional - defaults to EN if not provided
    // q param is not required in ready API becuase the report is ready for an already given q. If user intends to do another query then they should call the without /ready API

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "duration": "2019-12-13",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "devices": [
          {
            "deviceId": 12,
            "deviceName": "Hello",
            "ANGER": 143943033,
            "NO_DETECTION": 124343,
            "DISGUSTING": 124343,
            "HAPPY": 124343,
            "FEAR": 472,
            "NEUTRAL": 3778,
            "SAD": 48349,
            "SURPRISE": 0
          },
          {
            "deviceId": 44,
            "deviceName": "World",
            "ANGER": 143943033,
            "NO_DETECTION": 124343,
            "DISGUSTING": 124343,
            "HAPPY": 124343,
            "FEAR": 472,
            "NEUTRAL": 3778,
            "SAD": 48349,
            "SURPRISE": 0
          }
        ]
      },
      {
        "duration": "2019-12-14",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "devices": [
          {
            "deviceId": 12,
            "ANGER": 143943033,
            "NO_DETECTION": 124343,
            "DISGUSTING": 124343,
            "HAPPY": 124343,
            "FEAR": 472,
            "NEUTRAL": 3778,
            "SAD": 48349,
            "SURPRISE": 0
          },
          {
            "deviceId": 44,
            "ANGER": 143943033,
            "NO_DETECTION": 124343,
            "DISGUSTING": 124343,
            "HAPPY": 124343,
            "FEAR": 472,
            "NEUTRAL": 3778,
            "SAD": 48349,
            "SURPRISE": 0
          }
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Gender Demography report

---

    /reports/fa/gender-report [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    q={
      "from_date": {        // required
        "gte": "2019-02-12"
      },
      "to_date": {          // required
        "lte": "2019-04-30"
      },
      "from_time": {        // optional
        "gte": "10:00"
      },
      "to_time": {          // optional
        "lte": "12:00"
      },
      "device": {           // optional
        "in": "19,676,434,68"
      },
      "location": {         // optional
        "eq": 12
      },
      "deviceGroup": {      // optional
        "eq": 16
      },
      "layoutId": {      // optional
        "eq": 18
      },
      "gender": {
        "in": "MALE,FEMALE,UNKNOWN"
      },
      "emotion": {
        "in": "HAPPY,SAD"     // possible values NO_DETECTION, ANGER, DISGUSTING, FEAR, HAPPY, NEUTRAL, SAD, SURPRISE
      }
      "age": {
        "in": "AGE_26_30,AGE_46_50"        // comma separated
      },
      "durationType": {        // required
        "eq" : "DAILY"   // HOURLY, DAILY, WEEKLY, MONTHLY
      }
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "willDataBeAsync": false,     // if false then rest of the data except reportToken will be null
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "reportToken": "2hfh34fjwpoefj9",
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "duration": "FEB-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      },
      {
        "duration": "MAR-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      },
      {
        "duration": "APR-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      }
    ],
    "percentageData": {
      "FEMALE": 67.33,
      "MALE": 2.33,
      "UNKNOWN": 30.34
    },
    "ageAndGenderWiseCount" : {
      "MALE": {
        "AGE_6_10": 323,
        "AGE_11_15": 34,
        "AGE_16_20": 11,
        "AGE_21_25": 566,
        "AGE_26_30": 3545,
        "AGE_31_35": 674,
        "AGE_36_40": 76979,
        "AGE_40_45": 888,
        "AGE_46_50": 3567,
        "AGE_51_55": 968,
        "AGE_55_60": 990,
        "AGE_61_65": 334,
        "AGE_66_70": 86565,
        "AGE_71_75": 5166,
        "AGE_76_80": 378,
        "AGE_81_85": 9784,
        "AGE_86_90": 984,
        "AGE_91_95": 1155,
        "AGE_96_100": 866
      },
      "FEMALE": {
        "AGE_6_10": 323,
        "AGE_11_15": 34,
        "AGE_16_20": 11,
        "AGE_21_25": 566,
        "AGE_26_30": 3545,
        "AGE_31_35": 674,
        "AGE_36_40": 76979,
        "AGE_40_45": 888,
        "AGE_46_50": 3567,
        "AGE_51_55": 968,
        "AGE_55_60": 990,
        "AGE_61_65": 334,
        "AGE_66_70": 86565,
        "AGE_71_75": 5166,
        "AGE_76_80": 378,
        "AGE_81_85": 9784,
        "AGE_86_90": 984,
        "AGE_91_95": 1155,
        "AGE_96_100": 866
      }
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Gender Demography report- ready prepared async data

---

    /reports/fa/gender-report/ready [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    report-token=2hfh34fjwpoefj9
    pageNumber=12
    numPerPage=20
    ln=EN   // optional - defaults to EN if not provided
    // q param is not required in ready API becuase the report is ready for an already given q. If user intends to do another query then they should call the without /ready API

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "duration": "FEB-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      },
      {
        "duration": "MAR-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      },
      {
        "duration": "APR-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      }
    ],
    "percentageData": {
      "FEMALE": 67.33,
      "MALE": 2.33,
      "UNKNOWN": 30.34
    },
    "ageAndGenderWiseCount" : {
      "MALE": {
        "AGE_6_10": 323,
        "AGE_11_15": 34,
        "AGE_16_20": 11,
        "AGE_21_25": 566,
        "AGE_26_30": 3545,
        "AGE_31_35": 674,
        "AGE_36_40": 76979,
        "AGE_40_45": 888,
        "AGE_46_50": 3567,
        "AGE_51_55": 968,
        "AGE_55_60": 990,
        "AGE_61_65": 334,
        "AGE_66_70": 86565,
        "AGE_71_75": 5166,
        "AGE_76_80": 378,
        "AGE_81_85": 9784,
        "AGE_86_90": 984,
        "AGE_91_95": 1155,
        "AGE_96_100": 866
      },
      "FEMALE": {
        "AGE_6_10": 323,
        "AGE_11_15": 34,
        "AGE_16_20": 11,
        "AGE_21_25": 566,
        "AGE_26_30": 3545,
        "AGE_31_35": 674,
        "AGE_36_40": 76979,
        "AGE_40_45": 888,
        "AGE_46_50": 3567,
        "AGE_51_55": 968,
        "AGE_55_60": 990,
        "AGE_61_65": 334,
        "AGE_66_70": 86565,
        "AGE_71_75": 5166,
        "AGE_76_80": 378,
        "AGE_81_85": 9784,
        "AGE_86_90": 984,
        "AGE_91_95": 1155,
        "AGE_96_100": 866
      }
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Age Demography report

---

    /reports/fa/age-report [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20,
    ln=EN,           // optional - if not provided then default value is EN (english)
    q={
      "from_date": {        // required
        "gte": "2019-12-01"
      },
      "to_date": {          // required
        "lte": "2019-12-31"
      },
      "from_time": {        // optional
        "gte": "10:00"
      },
      "to_time": {          // optional
        "lte": "12:00"
      },
      "device": {           // optional
        "in": "19,676,434,68"
      },
      "location": {         // optional
        "eq": 12
      },
      "deviceGroup": {      // optional
        "eq": 16
      },
      "layoutId": {      // optional
        "eq": 18
      },
      "gender": {
        "in": "MALE,FEMALE,UNKNOWN"
      },
      "emotion": {
        "in": "HAPPY,SAD"     // possible values NO_DETECTION, ANGER, DISGUSTING, FEAR, HAPPY, NEUTRAL, SAD, SURPRISE
      }
      "age": {
        "in": "AGE_26_30,AGE_46_50"        // comma separated
      },
      "durationType": {        // required
        "eq" : "DAILY"   // HOURLY, DAILY, WEEKLY, MONTHLY
      }
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "willDataBeAsync": false,        // if false then rest of the data except reportToken will be null
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "reportToken": "2hfh34fjwpoefj9",
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "duration": "WEEK-1-to-7",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      },
      {
        "duration": "WEEK-8-to-14",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      },
      {
        "duration": "WEEK-15-to-21",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      },
      {
        "duration": "WEEK-22-to-28",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      },
      {
        "duration": "WEEK-29-to-31",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      }
    ],
    "percentageData": {
      "AGE_1_5": 10,
      "AGE_6_10": 10,
      "AGE_11_15": 4,
      "AGE_16_20": 5,
      "AGE_21_25": 5,
      "AGE_26_30": 6,
      "AGE_31_35": 5,
      "AGE_36_40": 5,
      "AGE_46_50": 7,
      "AGE_56_55": 3,
      "AGE_61_65": 8,
      "AGE_66_70": 6,
      "AGE_71_75": 3,
      "AGE_76_80": 8,
      "AGE_81_85": 9,
      "AGE_86_90": 1,
      "AGE_91_95": 2.5,
      "AGE_96_100": 2.5
    },
    "ageAndGenderWiseCount" : {
      "MALE": {
        "AGE_6_10": 323,
        "AGE_11_15": 34,
        "AGE_16_20": 11,
        "AGE_21_25": 566,
        "AGE_26_30": 3545,
        "AGE_31_35": 674,
        "AGE_36_40": 76979,
        "AGE_40_45": 888,
        "AGE_46_50": 3567,
        "AGE_51_55": 968,
        "AGE_55_60": 990,
        "AGE_61_65": 334,
        "AGE_66_70": 86565,
        "AGE_71_75": 5166,
        "AGE_76_80": 378,
        "AGE_81_85": 9784,
        "AGE_86_90": 984,
        "AGE_91_95": 1155,
        "AGE_96_100": 866
      },
      "FEMALE": {
        "AGE_6_10": 323,
        "AGE_11_15": 34,
        "AGE_16_20": 11,
        "AGE_21_25": 566,
        "AGE_26_30": 3545,
        "AGE_31_35": 674,
        "AGE_36_40": 76979,
        "AGE_40_45": 888,
        "AGE_46_50": 3567,
        "AGE_51_55": 968,
        "AGE_55_60": 990,
        "AGE_61_65": 334,
        "AGE_66_70": 86565,
        "AGE_71_75": 5166,
        "AGE_76_80": 378,
        "AGE_81_85": 9784,
        "AGE_86_90": 984,
        "AGE_91_95": 1155,
        "AGE_96_100": 866
      }
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Age Demography report- ready prepared async data

---

    /reports/fa/age-report/ready [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    report-token=2hfh34fjwpoefj9
    pageNumber=12
    numPerPage=20
    ln=EN   // optional - defaults to EN if not provided
    // q param is not required in ready API becuase the report is ready for an already given q. If user intends to do another query then they should call the without /ready API

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "duration": "WEEK-1-to-7",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      },
      {
        "duration": "WEEK-8-to-14",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      },
      {
        "duration": "WEEK-15-to-21",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      },
      {
        "duration": "WEEK-22-to-28",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      },
      {
        "duration": "WEEK-29-to-31",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5": 12434,
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 243,
        "AGE_21_25": 56,
        "AGE_26_30": 564,
        "AGE_31_35": 477,
        "AGE_36_40": 243,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 8
      }
    ],
    "percentageData": {
      "AGE_1_5": 10,
      "AGE_6_10": 10,
      "AGE_11_15": 4,
      "AGE_16_20": 5,
      "AGE_21_25": 5,
      "AGE_26_30": 6,
      "AGE_31_35": 5,
      "AGE_36_40": 5,
      "AGE_46_50": 7,
      "AGE_56_55": 3,
      "AGE_61_65": 8,
      "AGE_66_70": 6,
      "AGE_71_75": 3,
      "AGE_76_80": 8,
      "AGE_81_85": 9,
      "AGE_86_90": 1,
      "AGE_91_95": 2.5,
      "AGE_96_100": 2.5
    },
    "ageAndGenderWiseCount" : {
      "MALE": {
        "AGE_6_10": 323,
        "AGE_11_15": 34,
        "AGE_16_20": 11,
        "AGE_21_25": 566,
        "AGE_26_30": 3545,
        "AGE_31_35": 674,
        "AGE_36_40": 76979,
        "AGE_40_45": 888,
        "AGE_46_50": 3567,
        "AGE_51_55": 968,
        "AGE_55_60": 990,
        "AGE_61_65": 334,
        "AGE_66_70": 86565,
        "AGE_71_75": 5166,
        "AGE_76_80": 378,
        "AGE_81_85": 9784,
        "AGE_86_90": 984,
        "AGE_91_95": 1155,
        "AGE_96_100": 866
      },
      "FEMALE": {
        "AGE_1_5": 323,
        "AGE_6_10": 323,
        "AGE_11_15": 34,
        "AGE_16_20": 11,
        "AGE_21_25": 566,
        "AGE_26_30": 3545,
        "AGE_31_35": 674,
        "AGE_36_40": 76979,
        "AGE_40_45": 888,
        "AGE_46_50": 3567,
        "AGE_51_55": 968,
        "AGE_55_60": 990,
        "AGE_61_65": 334,
        "AGE_66_70": 86565,
        "AGE_71_75": 5166,
        "AGE_76_80": 378,
        "AGE_81_85": 9784,
        "AGE_86_90": 984,
        "AGE_91_95": 1155,
        "AGE_96_100": 866
      }
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Emotion Demography report

---

    /reports/fa/emotion-report [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20,
    ln=EN,           // optional - if not provided then default value is EN (english)
    q={
      "from_date": {        // required
        "gte": "2019-12-01"
      },
      "to_date": {          // required
        "lte": "2019-12-01"
      },
      "from_time": {        // optional
        "gte": "10:00"
      },
      "to_time": {          // optional
        "lte": "12:00"
      },
      "device": {           // optional
        "in": "19,676,434,68"
      },
      "location": {         // optional
        "eq": 12
      },
      "deviceGroup": {      // optional
        "eq": 16
      },
      "layoutId": {      // optional
        "eq": 18
      },
      "gender": {
        "in": "MALE,FEMALE,UNKNOWN"
      },
      "emotion": {
        "in": "HAPPY,SAD"     // possible values NO_DETECTION, ANGER, DISGUSTING, FEAR, HAPPY, NEUTRAL, SAD, SURPRISE
      },
      "age": {
        "in": "AGE_26_30,AGE_46_50"        // comma separated
      },
      "durationType": {        // required
        "eq" : "DAILY"   // HOURLY, DAILY, WEEKLY, MONTHLY
      }
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "willDataBeAsync": false,        // if false then rest of the data except reportToken will be null
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "reportToken": "2hfh34fjwpoefj9",
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "duration": "00:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "02:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "04:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "06:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "08:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "10:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "12:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "14:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "16:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "18:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "20:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "22:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      }
    ],
    "percentageData": {
      "HAPPY": 10.1,
      "SAD": 10.2,
      "ANGER": 10.3,
      "DISGUSTING": 10.4,
      "FEAR": 10.5,
      "NEUTRAL": 10.6,
      "SURPRISE": 10.7,
      "NO_DETECTION": 27.2
    },
    "emotionAndGenderWiseCount": {
      "MALE": {
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      "FEMALE": {
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      }
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Emotion Demography report

---

    /reports/fa/emotion-report/ready [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20,
    ln=EN,           // optional - if not provided then default value is EN (english)
    // q param is not required in ready API becuase the report is ready for an already given q. If user intends to do another query then they should call the without /ready API

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "duration": "00:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "02:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "04:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "06:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "08:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "10:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "12:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "14:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "16:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "18:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "20:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      {
        "duration": "22:00",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      }
    ],
    "percentageData": {
      "HAPPY": 10.1,
      "SAD": 10.2,
      "ANGER": 10.3,
      "DISGUSTING": 10.4,
      "FEAR": 10.5,
      "NEUTRAL": 10.6,
      "SURPRISE": 10.7,
      "NO_DETECTION": 27.2
    },
    "emotionAndGenderWiseCount": {
      "MALE": {
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      },
      "FEMALE": {
        "HAPPY": 12434,
        "SAD": 4434,
        "ANGER": 477,
        "DISGUSTING": 243,
        "FEAR": 56,
        "NEUTRAL": 564,
        "SURPRISE": 477,
        "NO_DETECTION": 243
      }
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Emotion Demography report

---

    /demography/age-enum [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    "AGE_1_5",
    "AGE_6_10",
    "AGE_11_15",
    "AGE_16_20",
    "AGE_21_25",
    "AGE_26_30",
    "AGE_31_35",
    "AGE_36_40",
    "AGE_40_45",
    "AGE_46_50",
    "AGE_51_55",
    "AGE_55_60",
    "AGE_61_65",
    "AGE_66_70",
    "AGE_71_75",
    "AGE_76_80",
    "AGE_81_85",
    "AGE_86_90",
    "AGE_91_95",
    "AGE_96_100"
  ],
  "message": null,
  "code": null,
  "name": null
}
```

### Dwell age-wise

---

    /reports/fa/dwell-age-wise [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    q={
      "from_date": {        // required
        "gte": "2019-02-12"
      },
      "to_date": {          // required
        "lte": "2019-04-30"
      },
      "from_time": {        // optional
        "gte": "10:00"
      },
      "to_time": {          // optional
        "lte": "12:00"
      },
      "device": {           // optional
        "in": "19,676,434,68"
      },
      "location": {         // optional
        "eq": 12
      },
      "deviceGroup": {      // optional
        "eq": 16
      },
      "layoutId": {      // optional
        "eq": 18
      },
      "gender": {
        "in": "MALE,FEMALE,UNKNOWN"
      },
      "durationType": {        // required
        "eq" : "DAILY"   // HOURLY, DAILY, WEEKLY, MONTHLY
      }
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",
    "willDataBeAsync": false,        // if false then rest of the data except reportToken will be null
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "dwellTimeUnit": "milliseconds", // or seconds
    "data": [
      {
        "duration": "WEEK-1-to-7",
        "date": "2020-02-02",         // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4
        "AGE_60_ABOVE_count": 4434
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-8-to-14",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4
        "AGE_60_ABOVE_count": 4434
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-15-to-21",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4
        "AGE_60_ABOVE_count": 4434
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-22-to-28",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4
        "AGE_60_ABOVE_count": 4434
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-29-to-31",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4
        "AGE_60_ABOVE_count": 4434
        "AGE_60_ABOVE_dwell_avg": 477.2
      }
    ],
    "totalCountByAge": [
      {
        "dwellTime": 2,   // this will be the higer end of the dwell time band. Suppose this data is for dwell time band 1.2 sec to 3.6 sec then the value in this key will be 3.6 sec
        "AGE_1_5": 400,     // count
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 24,
        "AGE_21_25": 76,
        "AGE_26_30": 56,
        "AGE_31_35": 47,
        "AGE_36_40": 24,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 80,

        "AGE_60_AND_ABOVE": 46
      },
      {
        "dwellTime": 4,   // this will be the higer end of the dwell time band. Suppose this data is for dwell time band 1.2 sec to 3.6 sec then the value in this key will be 3.6 sec
        "AGE_1_5": 400,     // count
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 24,
        "AGE_21_25": 76,
        "AGE_26_30": 56,
        "AGE_31_35": 47,
        "AGE_36_40": 24,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 80,

        "AGE_60_AND_ABOVE": 46
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Dwell age-wise

---

    /reports/fa/dwell-age-wise/ready [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    reportToken=<reportToken>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "dwellTimeUnit": "milliseconds", // or seconds
    "data": [
      {
        "duration": "WEEK-1-to-7",
        "date": "2020-02-02",         // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4,
        "AGE_60_ABOVE_count": 4434,
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-8-to-14",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4,
        "AGE_60_ABOVE_count": 4434,
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-15-to-21",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4,
        "AGE_60_ABOVE_count": 4434,
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-22-to-28",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4,
        "AGE_60_ABOVE_count": 4434,
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-29-to-31",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,  // in sec
        "AGE_1_5_count": 4434,        // count
        "AGE_1_5_dwell_avg": 477.2,   // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4,
        "AGE_60_ABOVE_count": 4434,
        "AGE_60_ABOVE_dwell_avg": 477.2
      }
    ],
    "totalCountByAge": [
      {
        "dwellTime": 2,   // this will be the higer end of the dwell time band. Suppose this data is for dwell time band 1.2 sec to 3.6 sec then the value in this key will be 3.6 sec
        "AGE_1_5": 400,     // count
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 24,
        "AGE_21_25": 76,
        "AGE_26_30": 56,
        "AGE_31_35": 47,
        "AGE_36_40": 24,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 80,

        "AGE_60_AND_ABOVE": 46
      },
      {
        "dwellTime": 4,   // this will be the higer end of the dwell time band. Suppose this data is for dwell time band 1.2 sec to 3.6 sec then the value in this key will be 3.6 sec
        "AGE_1_5": 400,     // count
        "AGE_6_10": 4434,
        "AGE_11_15": 477,
        "AGE_16_20": 24,
        "AGE_21_25": 76,
        "AGE_26_30": 56,
        "AGE_31_35": 47,
        "AGE_36_40": 24,
        "AGE_46_50": 56,
        "AGE_56_55": 564,
        "AGE_61_65": 93948,
        "AGE_66_70": 3948,
        "AGE_71_75": 3948,
        "AGE_76_80": 3248,
        "AGE_81_85": 93948,
        "AGE_86_90": 948,
        "AGE_91_95": 148,
        "AGE_96_100": 80,

        "AGE_60_AND_ABOVE": 46
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Dwell gender-wise

---

    /reports/fa/dwell-gender-wise [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    q={
      "from_date": {        // required
        "gte": "2019-02-12"
      },
      "to_date": {          // required
        "lte": "2019-04-30"
      },
      "from_time": {        // optional
        "gte": "10:00"
      },
      "to_time": {          // optional
        "lte": "12:00"
      },
      "device": {           // optional
        "in": "19,676,434,68"
      },
      "location": {         // optional
        "eq": 12
      },
      "deviceGroup": {      // optional
        "eq": 16
      },
      "layoutId": {      // optional
        "eq": 18
      },
      "age": {
        "in": "AGE_26_30,AGE_46_50"        // comma separated
      },
      "durationType": {        // required
        "eq" : "DAILY"   // HOURLY, DAILY, WEEKLY, MONTHLY
      }
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "willDataBeAsync": false,     // if false then rest of the data except reportToken will be null
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "reportToken": "2hfh34fjwpoefj9",
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "dwellTimeUnit": "milliseconds", // or seconds
    "data": [
      {
        "duration": "FEB-2019",
        "date": "2020-02-02",       // to be used on only in case of HOURLY
        "MALE_dwell_avg": 477.2,    // in sec
        "MALE_dwell_max": 4434.4,   // in sec
        "MALE_count": 4434,         // count
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434,
      },
      {
        "duration": "MAR-2019",
        "date": "2020-02-02",       // to be used on only in case of HOURLY
        "MALE_dwell_avg": 477.2,    // in sec
        "MALE_dwell_max": 4434.4,   // in sec
        "MALE_count": 4434,         // count
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434,
      },
      {
        "duration": "APR-2019",
        "date": "2020-02-02",       // to be used on only in case of HOURLY
        "MALE_dwell_avg": 477.2,    // in sec
        "MALE_dwell_max": 4434.4,   // in sec
        "MALE_count": 4434,         // count
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434,
      }
    ],
    "totalCountByGenderAndDwellTime": [
      {
        "dwellTime": 2,   // this will be the higer end of the dwell time band. Suppose this data is for dwell time band 1.2 sec to 3.6 sec then the value in this key will be 3.6 sec
        "MALE": 400,     // count
        "FEMALE": 4434,
        "UNKNOWN": 477
      },
      {
        "dwellTime": 4,   // this will be the higer end of the dwell time band. Suppose this data is for dwell time band 1.2 sec to 3.6 sec then the value in this key will be 3.6 sec
        "MALE": 400,     // count
        "FEMALE": 4434,
        "UNKNOWN": 477
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Dwell gender-wise ready

---

    /reports/fa/dwell-gender-wise/ready [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    reportToken=<reportToken>

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "reportToken": "2hfh34fjwpoefj9",
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "dwellTimeUnit": "milliseconds", // or seconds
    "data": [
      {
        "duration": "FEB-2019",
        "date": "2020-02-02",       // to be used on only in case of HOURLY
        "MALE_dwell_avg": 477.2,    // in sec
        "MALE_dwell_max": 4434.4,   // in sec
        "MALE_count": 4434,         // count
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434,
      },
      {
        "duration": "MAR-2019",
        "date": "2020-02-02",       // to be used on only in case of HOURLY
        "MALE_dwell_avg": 477.2,    // in sec
        "MALE_dwell_max": 4434.4,   // in sec
        "MALE_count": 4434,         // count
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434,
      },
      {
        "duration": "APR-2019",
        "date": "2020-02-02",       // to be used on only in case of HOURLY
        "MALE_dwell_avg": 477.2,    // in sec
        "MALE_dwell_max": 4434.4,   // in sec
        "MALE_count": 4434,         // count
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434,
      }
    ],
    "totalCountByGenderAndDwellTime": [
      {
        "dwellTime": 2,   // this will be the higer end of the dwell time band. Suppose this data is for dwell time band 1.2 sec to 3.6 sec then the value in this key will be 3.6 sec
        "MALE": 400,     // count
        "FEMALE": 4434,
        "UNKNOWN": 477
      },
      {
        "dwellTime": 4,   // this will be the higer end of the dwell time band. Suppose this data is for dwell time band 1.2 sec to 3.6 sec then the value in this key will be 3.6 sec
        "MALE": 400,     // count
        "FEMALE": 4434,
        "UNKNOWN": 477
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### Demography Raw data Report - new request

---

    /reports/fa/raw-data [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <token>

QUERY

    q={
      "from_date": {        // required
        "gte": "2019-02-12"
      },
      "to_date": {          // required
        "lte": "2019-04-30"
      },
      "from_time": {        // optional
        "gte": "10:00"
      },
      "to_time": {          // optional
        "lte": "12:00"
      },
      "device": {           // optional
        "in": "19,676,434,68"
      },
      "location": {         // optional
        "eq": 12
      },
      "deviceGroup": {      // optional
        "eq": 16
      },
      "layoutId": {      // optional
        "eq": 18
      },
      "age": {
        "in": "AGE_26_30,AGE_46_50"    // comma separated
      },
      "gender": {
        "in": "MALE,FEMALE,UNKNOWN"
      },
      "emotion": {
        "in": "HAPPY,SAD"     // possible values NO_DETECTION, ANGER, DISGUSTING, FEAR, HAPPY, NEUTRAL, SAD, SURPRISE
      }
    }

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "reportRequestId": 12,
      "requestTimestamp": 15646734939434,
      "reportGeneratedTimestamp": 15646734939435,
      "q": [
        {
          "from_date": "2019-02-12"
        },
        {
          "to_date": "2019-02-12"
        },
        {
          "from_time": "11:00:00"
        },
        {
          "to_time": "12:00:00"
        },
        {
          "device": "DeviceOne, Device two"  // comma separated device names instead of device Ids
        },
        {
          "location": "Noida"
        },
        {
          "deviceGroup": "Garden"
        },
        {
          "age": "AGE_26_30,AGE_46_50"
        },
        {
          "gender": "MALE,FEMALE,UNKNOWN"
        },
        {
          "emotion": "HAPPY,SAD"
        }
      ],
      "reportStatus": "GENERATING",  // possible values: GENERATING, GENERATED
      "reportPDFLink": "http://reporturl",
      "reportXLSLink": "http://reporturl"
    }
  ],
  "message": null,
  "code": null,
  "name": null
}
```

RESPONSE - code - 400

```json
{
  "result": null,
  "message": "Request not accepted. A request with same parameters is present is already there. Please try again after, 14:30",
  "code": null,
  "name": "RequestNotAccepted"
}
```

### Demography Raw data Report - Fetch

---

    /reports/fa/raw-data [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [
    {
      "reportRequestId": 12,
      "requestTimestamp": 15646734939434,
      "reportGeneratedTimestamp": 15646734939435,
      "q": [
        {
          "from_date": "2019-02-12"
        },
        {
          "to_date": "2019-02-12"
        },
        {
          "from_time": "11:00:00"
        },
        {
          "to_time": "12:00:00"
        },
        {
          "device": "DeviceOne, Device two"  // comma separated device names instead of device Ids
        },
        {
          "location": "Noida"
        },
        {
          "deviceGroup": "Garden"
        },
        {
          "age": "AGE_26_30,AGE_46_50"
        },
        {
          "gender": "MALE,FEMALE,UNKNOWN"
        },
        {
          "emotion": "HAPPY,SAD"
        }
      ],
      "reportStatus": "GENERATING",  // possible values: GENERATING, GENERATED
      "reportPDFLink": "http://reporturl",
      "reportXLSLink": "http://reporturl"
    }
  ],
  "message": null,
  "code": null,
  "name": null
}
```

### Emotion dashboard percentage graph

---

    /dashboard/fa/emotion [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    how_many_days=LAST_ONE_DAY  // required - possible values LAST_ONE_DAY, LAST_SEVEN_DAY or LAST_ONE_MONTH

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "percentageData": {
      "HAPPY": 10.1,
      "SAD": 10.2,
      "ANGER": 10.3,
      "DISGUSTING": 10.4,
      "FEAR": 10.5,
      "NEUTRAL": 10.6,
      "SURPRISE": 10.7,
      "NO_DETECTION": 27.2
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Gender based dashboard number of views

---

    /dashboard/fa/gender-views [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    how_many_days=LAST_ONE_DAY  // required - possible values LAST_ONE_DAY, LAST_SEVEN_DAY or LAST_ONE_MONTH

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "percentageData": {
      "MALE_count": 1212
      "MALE": 10.1,         // percentage
      "FEMALE_count": 1212
      "FEMALE": 10.2,       // percentage
      "UNKNOWN_count": 1212
      "UNKNOWN": 10.3       // percentage
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Gender dashboard percentage bar graph

---

    /dashboard/fa/gender [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    how_many_days=LAST_ONE_DAY  // required - possible values LAST_ONE_DAY, LAST_SEVEN_DAY or LAST_ONE_MONTH

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "data": [
      {
        "duration": "FEB-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      },
      {
        "duration": "MAR-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      },
      {
        "duration": "APR-2019",
        "date": "2020-02-02",   // to be used on only in case of HOURLY
        "MALE": 12434,
        "FEMALE": 4434,
        "UNKNOWN": 477,
        "femaleRatioToTotal": 0.33
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Age dashboard percentage graph

---

    /dashboard/fa/age [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    how_many_days=LAST_ONE_DAY  // required - possible values LAST_ONE_DAY, LAST_SEVEN_DAY or LAST_ONE_MONTH

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "percentageData": {
      "AGE_1_5": 10,
      "AGE_6_10": 10,
      "AGE_11_15": 4,
      "AGE_16_20": 5,
      "AGE_21_25": 5,
      "AGE_26_30": 6,
      "AGE_31_35": 5,
      "AGE_36_40": 5,
      "AGE_46_50": 7,
      "AGE_56_55": 3,
      "AGE_61_65": 8,
      "AGE_66_70": 6,
      "AGE_71_75": 3,
      "AGE_76_80": 8,
      "AGE_81_85": 9,
      "AGE_86_90": 1,
      "AGE_91_95": 2.5,
      "AGE_96_100": 2.5,

      "AGE_60_AND_ABOVE": 4.6
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### FA Device Features

---

    /dashboard/fa/devices-with-camera [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "deviceWithCamera": 100,
    "deviceWithoutCamera": 150,
    "totalDevices": 250,
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### FA Dashboard dwell gender wise

---

    /dashboard/fa/dwell-gender-wise [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    how_many_days=LAST_ONE_DAY  // required - possible values LAST_ONE_DAY, LAST_SEVEN_DAY or LAST_ONE_MONTH

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "dwellTimeUnit": "milliseconds", // or seconds
    "data": [
      {
        "duration": "FEB-2019",
        "date": "2020-02-02",
        "MALE_dwell_avg": 477.2,
        "MALE_dwell_max": 4434.4,
        "MALE_count": 4434,
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434
      },
      {
        "duration": "MAR-2019",
        "date": "2020-02-02",
        "MALE_dwell_avg": 477.2,
        "MALE_dwell_max": 4434.4,
        "MALE_count": 4434,
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434
      },
      {
        "duration": "APR-2019",
        "date": "2020-02-02",
        "MALE_dwell_avg": 477.2,
        "MALE_dwell_max": 4434.4,
        "MALE_count": 4434,
        "FEMALE_dwell_avg": 477.2,
        "FEMALE_dwell_max": 4434.4,
        "FEMALE_count": 4434,
        "UNKNOWN_dwell_avg": 477.2,
        "UNKNOWN_dwell_max": 4434.4,
        "UNKNOWN_count": 4434
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### FA Dashboard dwell age wise

---

    /dashboard/fa/dwell-age-wise [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    how_many_days=LAST_ONE_DAY  // required - possible values LAST_ONE_DAY, LAST_SEVEN_DAY or LAST_ONE_MONTH

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "isDataFullyEmpty": true, // or false
    "dwellTimeUnit": "milliseconds", // or seconds
    "data": [
      {
        "duration": "WEEK-1-to-7",
        "date": "2020-02-02",           // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,    // in sec
        "AGE_1_5_count": 4434,          // count
        "AGE_1_5_dwell_avg": 477.2,     // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4,
        "AGE_60_ABOVE_count": 4434,
        "AGE_60_ABOVE_dwell_avg": 477.2
      },
      {
        "duration": "WEEK-8-to-14",
        "date": "2020-02-02",               // to be used on only in case of HOURLY
        "AGE_1_5_dwell_max": 4434.4,        // in sec
        "AGE_1_5_count": 4434,              // count
        "AGE_1_5_dwell_avg": 477.2,         // in sec
        "AGE_6_10_dwell_max": 4434.4,
        "AGE_6_10_count": 4434,
        "AGE_6_10_dwell_avg": 477.2,
        "AGE_11_15_dwell_max": 4434.4,
        "AGE_11_15_count": 4434,
        "AGE_11_15_dwell_avg": 477.2,
        "AGE_16_20_dwell_max": 4434.4,
        "AGE_16_20_count": 4434,
        "AGE_16_20_dwell_avg": 477.2,
        "AGE_21_25_dwell_max": 4434.4,
        "AGE_21_25_count": 4434,
        "AGE_21_25_dwell_avg": 477.2,
        "AGE_26_30_dwell_max": 4434.4,
        "AGE_26_30_count": 4434,
        "AGE_26_30_dwell_avg": 477.2,
        "AGE_31_35_dwell_max": 4434.4,
        "AGE_31_35_count": 4434,
        "AGE_31_35_dwell_avg": 477.2,
        "AGE_36_40_dwell_max": 4434.4,
        "AGE_36_40_count": 4434,
        "AGE_36_40_dwell_avg": 477.2,
        "AGE_46_50_dwell_max": 4434.4,
        "AGE_46_50_count": 4434,
        "AGE_46_50_dwell_avg": 477.2,
        "AGE_56_55_dwell_max": 4434.4,
        "AGE_56_55_count": 4434,
        "AGE_56_55_dwell_avg": 477.2,
        "AGE_61_65_dwell_max": 4434.4,
        "AGE_61_65_count": 4434,
        "AGE_61_65_dwell_avg": 477.2,
        "AGE_66_70_dwell_max": 4434.4,
        "AGE_66_70_count": 4434,
        "AGE_66_70_dwell_avg": 477.2,
        "AGE_71_75_dwell_max": 4434.4,
        "AGE_71_75_count": 4434,
        "AGE_71_75_dwell_avg": 477.2,
        "AGE_76_80_dwell_max": 4434.4,
        "AGE_76_80_count": 4434,
        "AGE_76_80_dwell_avg": 477.2,
        "AGE_81_85_dwell_max": 4434.4,
        "AGE_81_85_count": 4434,
        "AGE_81_85_dwell_avg": 477.2,
        "AGE_86_90_dwell_max": 4434.4,
        "AGE_86_90_count": 4434,
        "AGE_86_90_dwell_avg": 477.2,
        "AGE_91_95_dwell_max": 4434.4,
        "AGE_91_95_count": 4434,
        "AGE_91_95_dwell_avg": 477.2,
        "AGE_96_100_dwell_max": 4434.4,
        "AGE_96_100_count": 4434,
        "AGE_96_100_dwell_avg": 477.2,

        "AGE_60_ABOVE_dwell_max": 4434.4,
        "AGE_60_ABOVE_count": 4434,
        "AGE_60_ABOVE_dwell_avg": 477.2
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules GET for admin app

---

    /demography/rule [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": [   // sort by latest rule at the top
    {
      "ruleId": 122,
      "ruleName": "Cool Rule",
      "createdBy": "Ashok Kumar",
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)",
      "gender": "FEMALE",
      "emotion": "ANGER",
      "ageBand": "AGE_21_25",
      "isComplete": true // or false
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules get by ID

---

    /demography/rule/<ruleId> [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "ruleId": 122,
    "ruleName": "Cool Rule",
    "createdBy": "Ashok Kumar",
    "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)",
    "gender": "FEMALE",
    "emotion": "ANGER",
    "ageBand": "AGE_21_25",
    "priority": 9,
    "isComplete": true // or false
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rule add

---

    /demography/rule [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

```json
{   // note for dev: Rule priority should not be set using this API
  "ruleName": "Cool Rule",
  "gender": "FEMALE",        // any one of the the three are required
  "emotion": "ANGER",        // any one of the the three are required
  "ageBand": "AGE_21_25"     // any one of the the three are required
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "ruleId": 145,
    "ruleName": "Cool Rule",
    "createdBy": "Ashok Kumar",
    "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)",
    "gender": "FEMALE",
    "emotion": "ANGER",
    "ageBand": "AGE_21_25",
    "priority": null,
    "isComplete": true // or false
  },
  "message": "Successfully Saved",
  "code": 20,
  "name": null,
  "pagination": null
}
```

### Demography rule edit

---

    /demography/rule/<ruleId> [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

```json
{   // note for dev: Rule priority should not be set using this API
  "ruleName": "Cool Rule",
  "gender": "FEMALE",        // to remove some thing set it as null
  "emotion": "ANGER",        // to remove some thing set it as null
  "ageBand": "AGE_21_25"     // to remove some thing set it as null
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "ruleId": 128,
    "ruleName": "Cool Rule",
    "createdBy": "Ashok Kumar",
    "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)",
    "gender": "FEMALE",
    "emotion": "ANGER",
    "ageBand": "AGE_21_25",
    "priority": null,
    "isComplete": true // or false
  },
  "message": "Successfully Saved",
  "code": 20,
  "name": null,
  "pagination": null
}
```

### Demography rule fetch associations

---

    /demography/rule/association/<ruleId> [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {
    "DEVICES": [
      {
        "ruleAssociationId": 1243,
        "deviceId": 12,
        "deviceName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "layoutId": 44,
        "layoutName": "my layout",
        "numberOfLoops": 5,
        "totalDurationOfCampaignInSeconds": 18,
        "key": "DEVICES",  // possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
      }
    ],
    "DEVICE_GROUP": [
      {
        "ruleAssociationId": 1243,
        "deviceGroupId": 12,
        "deviceGroupName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "layoutId": 44,
        "layoutName": "my layout",
        "numberOfLoops": 5,
        "totalDurationOfCampaignInSeconds": 18,
        "key": "DEVICE_GROUP",  // possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
      }
    ],
    "LOCATION": [
      {
        "ruleAssociationId": 1243,
        "locationId": 12,
        "locationName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "layoutId": 44,
        "layoutName": "my layout",
        "numberOfLoops": 5,
        "totalDurationOfCampaignInSeconds": 18,
        "key": "LOCATION",  // possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
      }
    ],
    "LOCATION_WITH_DEVICE_GROUP": [
      {
        "ruleAssociationId": 1243,
        "locationId": 12,
        "locationName": "hello",
        "deviceGroupId": 13,
        "deviceGroupName": "world",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "layoutId": 44,
        "layoutName": "my layout",
        "numberOfLoops": 5,
        "totalDurationOfCampaignInSeconds": 18,
        "key": "LOCATION_WITH_DEVICE_GROUP",  // possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules association add only no update

---

    /demography/rule/association/<ruleId> [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

```json
{
  "deviceIds": [ 12, 22 ],          // if key is DEVICES then this is required
  "locationIds": [ 22, 477 ],       // if key is LOCATION then this is required
  "deviceGroupIds": [ 76, 589 ],    // if key is DEVICE_GROUP then this is required
  "locationWithDeviceGroup": [      // if key is LOCATION_WITH_DEVICE_GROUP then this is required
    {
      "locationId": 44,
      "deviceGroupIds": [ 76, 589 ]
    },
    {
      "locationId": 92,
      "deviceGroupIds": [ 7486, 3772 ]
    }
  ],
  "startDate": "2019-20-21",    // required
  "endDate": "2019-20-21",      // required
  "startTime": "10:22:44",      // required
  "endTime": "14:22:44",        // required
  "weekDays": [                 // optional - if not sent then the rule is applicable for all days of week
    "MON",
    "TUE"
  ],
  "key": "DEVICES",  // required - possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
  "layoutId": 44,               // required
  "numberOfLoops": 5            // required
}
```

RESPONSE - code - 200

```json
{
  "result": {same as the get all association API},
  "message": "Successfully saved",
  "code": null,
  "name": "SuccessfullySaved",
  "pagination": null
}
```

### Demography rule delete

---

    /demography/rule/<ruleId> [DELETE]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": "Deleted successfully",
  "message": "Deleted successfully",
  "code": null,
  "name": "DeletedSuccessfullySaved",
  "pagination": null
}
```

### Delete Demography rules association

---

    /demography/rule/association/<ruleAssociationId> [DELETE]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - code - 200

```json
{
  "result": {same as the get all association API},
  "message": "Successfully deleted",
  "code": null,
  "name": "SuccessfullyDeleted",
  "pagination": null
}
```

### Demography rules priority - Global GET

---

    /demography/rule/priorites/global [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    filter=ALL_RULES // possible values ALL_RULES, RULES_HAVING_ATLEAST_ONE_ASSOCIATION

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "ruleId": 45,
      "ruleName": "hello",
      "priority": 9,
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)"
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules priority - Global GET - as perceived by device

---

    /demography/rule/priorites/global/device-perceived [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    filter=ALL_RULES // possible values ALL_RULES, RULES_HAVING_ATLEAST_ONE_ASSOCIATION

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "ruleId": 45,
      "ruleName": "hello",
      "priority": 9,
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)"
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules priority - Device GET

---

    /demography/rule/priorites/device/{deviceId} [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "ruleId": 45,
      "ruleName": "hello",
      "priority": 9,
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)"
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules priority - Device GET - as perceived by device

---

    /demography/rule/priorites/device/device-perceived/{deviceId} [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "ruleId": 45,
      "ruleName": "hello",
      "priority": 9,
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)"
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules priority - Device Group GET

---

    /demography/rule/priorites/device-group/{deviceGroupId} [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "ruleId": 45,
      "ruleName": "hello",
      "priority": 9,
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)"
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules priority - Device Group GET - as perceived by device

---

    /demography/rule/priorites/device-group/device-perceived/{deviceGroupId} [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "ruleId": 45,
      "ruleName": "hello",
      "priority": 9,
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)"
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules priority reorder

---

    /demography/rule/priorites [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

```json
{
  "deviceId": 42,           // optional
  "deviceGroupId": 445,     // optional
  "pirorities": [
    {
      "ruleId": 45,
      "priority": 9
    }
  ]
}
```

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "ruleId": 45,
      "ruleName": "hello",
      "priority": 9,
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)"
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography rules priority reorder - as perceived by device

---

    /demography/rule/priorites/reorder-as-device-perceived [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

```json
{
  "pirorities": [
    {
      "ruleId": 45,
      "priority": 9
    }
  ]
}
```

RESPONSE - CODE - 200

```json
{
  "result": [
    {
      "ruleId": 45,
      "ruleName": "hello",
      "priority": 9,
      "ruleDisplayString": "Cool Rule (Age: 61 - 65, Gender: F, Emotion: ANGER)"
    }
  ],
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography - get all rules association for a layout ID

---

    /demography/rule/layout/<layoutId> [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    NA

BODY

    NA

RESPONSE - CODE - 200

```json
{
  "result": {
    "DEVICES": [
      {
        "ruleAssociationId": 1243,
        "ruleName": "Cool rule",
        "ruleDisplayString": "Rule display string",
        "deviceId": 12,
        "deviceName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "numberOfLoops": 5,
        "key": "DEVICES"
      }
    ],
    "DEVICE_GROUP": [
      {
        "ruleAssociationId": 1243,
        "ruleName": "Cool rule",
        "ruleDisplayString": "Rule display string",
        "deviceGroupId": 12,
        "deviceGroupName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "numberOfLoops": 5,
        "key": "DEVICE_GROUP"
      }
    ],
    "LOCATION": [
      {
        "ruleAssociationId": 1243,
        "ruleName": "Cool rule",
        "ruleDisplayString": "Rule display string",
        "locationId": 12,
        "locationName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "numberOfLoops": 5,
        "key": "LOCATION"
      }
    ],
    "LOCATION_WITH_DEVICE_GROUP": [
      {
        "ruleAssociationId": 1243,
        "ruleName": "Cool rule",
        "ruleDisplayString": "Rule display string",
        "locationId": 12,
        "locationName": "hello",
        "deviceGroupId": 13,
        "deviceGroupName": "world",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "numberOfLoops": 5,
        "key": "LOCATION_WITH_DEVICE_GROUP"
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Demography - replace layouts in existing rules PUT

---

    /demography/rule/layout/<layoutId> [PUT]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token>

QUERY

    get-response-required=true   // true if then send the GET API response for the old layout ID, if flase then just send successfully updated message

BODY

```json
{
  "ruleAssociationIds": [
    11,
    344,
    4,
    666,
    1
  ],
  "newLayoutId": 47
}
```

Note: Send this result only if is get-response-required=false

RESPONSE - CODE - 200

```json
{
  "result": null,
  "message": "Successufully updated",
  "code": null,
  "name": null,
  "pagination": null
}
```

Note: Send this result only if is get-response-required=true

RESPONSE - CODE - 200

```json
{
  "result": {
    "DEVICES": [
      {
        "ruleAssociationId": 1243,
        "deviceId": 12,
        "deviceName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "numberOfLoops": 5,
        "key": "DEVICES",  // possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
      }
    ],
    "DEVICE_GROUP": [
      {
        "ruleAssociationId": 1243,
        "deviceGroupId": 12,
        "deviceGroupName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "numberOfLoops": 5,
        "key": "DEVICE_GROUP",  // possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
      }
    ],
    "LOCATION": [
      {
        "ruleAssociationId": 1243,
        "locationId": 12,
        "locationName": "hello",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "numberOfLoops": 5,
        "key": "LOCATION",  // possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
      }
    ],
    "LOCATION_WITH_DEVICE_GROUP": [
      {
        "ruleAssociationId": 1243,
        "locationId": 12,
        "locationName": "hello",
        "deviceGroupId": 13,
        "deviceGroupName": "world",
        "startDate": "2019-20-21",
        "endDate": "2019-20-21",
        "startTime": "10:22:44",
        "endTime": "14:22:44",
        "weekDays": [
          "MON",
          "TUE"
        ],
        "numberOfLoops": 5,
        "key": "LOCATION_WITH_DEVICE_GROUP",  // possible values: DEVICES, LOCATION, DEVICE_GROUP, LOCATION_WITH_DEVICE_GROUP
      }
    ]
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Customer Branding fetch

---

    /customer/branding/<customerId> [GET]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token> // pan admin only

QUERY

    NA

BODY

    NA

logo type : CUSTOM_IMAGE = user provided image
NO_IMAGE = no image provided
DEFAULT_IMAGE = default panasonic image

RESPONSE - code - 200

```json
{
  "result": {
    "pageTitle": "My cool title",
    "signedgeBrandUrl": "https://helloworld.com",
    "footerText": "My cool footer",
    "sdn": {
      "adPlatformBrandUrl": "https://helloworld-sdn.com",
      "nameOfCustomer": "hello world",
      "defaultAdPlatformBrandUrl": "https://default.url"
    },
    "smtp" : {
       "host": "10.55.144.64",
       "port": 25,
       "senderEmailAddress": "abc@abc.com",
       "useTLS": false, // or true
    },
    "leftNavLogo": {
      "logoType": "CUSTOM_IMAGE",       // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://ssss",         // null if not present
      "thumbUrl": "http://ssss"         // null if not present
    },
    "favIconLogo": {
      "logoType": "NO_IMAGE",           // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": null,                  // null if not present
      "thumbUrl": null                  // null if not present
    },
    "centerLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoCenter": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoTopRight": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "sdnLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Customer Branding logo upload

---

    /customer/branding/image/<customerId> [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token> // pan admin only

QUERY

    NA

BODY

    MULTI-PART BODY
    key = leftNavLogo
    key = centerLogo
    key = favicon
    key = loginLogoCenter
    key = loginLogoTopRight
    key = sdnLogo

logo type : CUSTOM_IMAGE = user provided image
NO_IMAGE = no image provided
DEFAULT_IMAGE = default panasonic image

RESPONSE - code - 200

```json
{
  "result": {
    "pageTitle": "My cool title",
    "signedgeBrandUrl": "https://helloworld.com",
    "footerText": "My cool footer",
    "sdn": {
      "adPlatformBrandUrl": "https://helloworld-sdn.com",
      "nameOfCustomer": "hello world",
      "defaultAdPlatformBrandUrl": "https://default.url"
    },
    "smtp" : {
       "host": "10.55.144.64",
       "port": 25,
       "senderEmailAddress": "abc@abc.com",
       "useTLS": false, // or true
    },
    "leftNavLogo": {
      "logoType": "CUSTOM_IMAGE",       // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://ssss",         // null if not present
      "thumbUrl": "http://ssss"         // null if not present
    },
    "favIconLogo": {
      "logoType": "NO_IMAGE",           // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": null,                  // null if not present
      "thumbUrl": null                  // null if not present
    },
    "centerLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoCenter": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoTopRight": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "sdnLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Customer Branding text

---

    /customer/branding/text/<customerId> [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token> // pan admin only

QUERY

    NA

BODY

```json
{
  "pageTitle": "My Cool Title",            // set to empty string to delete from server
  "signedgeBrandUrl": "https://helloworld.com",
  "footerText": "My cool footer",
  "smtp" : {
    "host": "10.55.144.64",                // set to empty string to delete from server
    "port": 25,                            // set to -1 to delete from server
    "senderEmailAddress": "abc@abc.com",   // set to empty string to delete from server
    "password": "password",                // optional because some mail servers may not have password - set to empty string to delete from server
    "useTLS": false // or true             // set to null to delete from server
  }
}
```

logo type : CUSTOM_IMAGE = user provided image
NO_IMAGE = no image provided
DEFAULT_IMAGE = default panasonic image

RESPONSE - code - 200

```json
{
  "result": {
    "pageTitle": "My cool title",
    "signedgeBrandUrl": "https://helloworld.com",
    "footerText": "My cool footer",
    "sdn": {
      "adPlatformBrandUrl": "https://helloworld-sdn.com",
      "nameOfCustomer": "hello world",
      "defaultAdPlatformBrandUrl": "https://default.url"
    },
    "smtp" : {
       "host": "10.55.144.64",
       "port": 25,
       "senderEmailAddress": "abc@abc.com",
       "useTLS": false, // or true
    },
    "leftNavLogo": {
      "logoType": "CUSTOM_IMAGE",       // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://ssss",         // null if not present
      "thumbUrl": "http://ssss"         // null if not present
    },
    "favIconLogo": {
      "logoType": "NO_IMAGE",           // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": null,                  // null if not present
      "thumbUrl": null                  // null if not present
    },
    "centerLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoCenter": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoTopRight": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "sdnLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Customer Branding text

---

    /customer/branding/text/sdn/<customerId> [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token> // pan admin only

QUERY

    NA

BODY

```json
{
  "adPlatformBrandUrl": "https://helloworld-sdn.com",  // if provided url is same as default url then delete the entry in DB
  "nameOfCustomer": "hello world"   // to delete this send it as empty string (not null)
}
```

logo type : CUSTOM_IMAGE = user provided image
NO_IMAGE = no image provided
DEFAULT_IMAGE = default panasonic image

RESPONSE - code - 200

```json
{
  "result": {
    "pageTitle": "My cool title",
    "signedgeBrandUrl": "https://helloworld.com",
    "footerText": "My cool footer",
    "sdn": {
      "adPlatformBrandUrl": "https://helloworld-sdn.com",
      "nameOfCustomer": "hello world",
      "defaultAdPlatformBrandUrl": "https://default.url"
    },
    "smtp" : {
       "host": "10.55.144.64",
       "port": 25,
       "senderEmailAddress": "abc@abc.com",
       "useTLS": false, // or true
    },
    "leftNavLogo": {
      "logoType": "CUSTOM_IMAGE",       // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://ssss",         // null if not present
      "thumbUrl": "http://ssss"         // null if not present
    },
    "favIconLogo": {
      "logoType": "NO_IMAGE",           // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": null,                  // null if not present
      "thumbUrl": null                  // null if not present
    },
    "centerLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoCenter": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoTopRight": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "sdnLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Customer Branding logo delete

---

    /customer/branding/image/<customerId> [DELETE]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token> // pan admin only

QUERY

    NA

BODY

```json
{
  "item": "LEFT_NAV_LOGO" // Use for deleting an uploaded image. possible values : LEFT_NAV_LOGO, CENTER_LOGO, FAV_ICON_LOGO, LOGIN_LOGO_CENTER, LOGIN_LOGO_TOP_RIGHT
  "useLogoType": "NO_IMAGE"  // or "DEFAULT_IMAGE"
}
```

logo type : CUSTOM_IMAGE = user provided image
NO_IMAGE = no image provided
DEFAULT_IMAGE = default panasonic image

RESPONSE - code - 200

```json
{
  "result": {
    "pageTitle": "My cool title",
    "signedgeBrandUrl": "https://helloworld.com",
    "footerText": "My cool footer",
    "sdn": {
      "adPlatformBrandUrl": "https://helloworld-sdn.com",
      "nameOfCustomer": "hello world"
    },
    "smtp" : {
       "host": "10.55.144.64",
       "port": 25,
       "senderEmailAddress": "abc@abc.com",
       "useTLS": false, // or true
    },
    "leftNavLogo": {
      "logoType": "CUSTOM_IMAGE",       // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://ssss",         // null if not present
      "thumbUrl": "http://ssss"         // null if not present
    },
    "favIconLogo": {
      "logoType": "NO_IMAGE",           // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": null,                  // null if not present
      "thumbUrl": null                  // null if not present
    },
    "centerLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoCenter": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoTopRight": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "sdnLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Customer Branding SMTP test API

---

    /customer/branding/email/test [POST]

HEADER

    Content-Type:application/json
    Authorization: Bearer <user token> // pan admin only

QUERY

    NA

BODY

```json
{
  "host": "smtp.abc.com",
  "port": 587,
  "password":"ashiuehw",
  "senderEmailAddress": "abc@xyz.com",
  "receiverEmailAddress": "abc@mno.com",
  "useTLS": false
}
```

RESPONSE - code - 400

```json
{
  "result": {
    "isSuccess": false,
    "errorMessage": "MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 530 5.7.0 Must issue a STARTTLS command first. f8sm55024140pfn.2 - gsmtp\n",
    "fullStackTrace": "org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 530 5.7.0 Must issue a STARTTLS command first. f8sm55024140pfn.2 - gsmtp\n; message exception details (1) are:\nFailed message 1:\ncom.sun.mail.smtp.SMTPSendFailedException: 530 5.7.0 Must issue a STARTTLS command first. f8sm55024140pfn.2 - gsmtp\n\n\tat com.sun.mail.smtp.SMTPTransport.issueSendCommand(SMTPTransport.java:2267)\n\tat com.sun.mail.smtp.SMTPTransport.mailFrom(SMTPTransport.java:1758)\n\tat com.sun.mail.smtp.SMTPTransport.sendMessage(SMTPTransport.java:1257)\n\tat org.springframework.mail.javamail.JavaMailSenderImpl.doSend(JavaMailSenderImpl.java:448)\n\tat org.springframework.mail.javamail.JavaMailSenderImpl.send(JavaMailSenderImpl.java:345)\n\tat org.springframework.mail.javamail.JavaMailSenderImpl.send(JavaMailSenderImpl.java:340)\n\tat digital.signage.service.impl.EmailTestServiceImpl.sendEmail(EmailTestServiceImpl.kt:84)\n\tat digital.signage.service.impl.EmailTestServiceImpl.testEmailService(EmailTestServiceImpl.kt:41)\n\tat digital.signage.service.impl.EmailTestServiceImpl$$FastClassBySpringCGLIB$$fd7d375a.invoke(<generated>)\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n\tat org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor.invoke(MethodBeforeAdviceInterceptor.java:52)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n\tat org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:52)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n\tat org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n\tat org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\n\tat digital.signage.service.impl.EmailTestServiceImpl$$EnhancerBySpringCGLIB$$7f38e283.testEmailService(<generated>)\n\tat digital.signage.controllers.EmailTestController.testEmailService(EmailTestController.kt:28)\n\tat digital.signage.controllers.EmailTestController$$FastClassBySpringCGLIB$$85c2eeb1.invoke(<generated>)\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n\tat org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor.invoke(MethodSecurityInterceptor.java:69)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\n\tat digital.signage.controllers.EmailTestController$$EnhancerBySpringCGLIB$$8470c322.testEmailService(<generated>)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n\tat org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.boot.web.filter.ApplicationContextHeaderFilter.doFilterInternal(ApplicationContextHeaderFilter.java:55)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.boot.actuate.trace.WebRequestTraceFilter.doFilterInternal(WebRequestTraceFilter.java:111)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:320)\n\tat org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:127)\n\tat org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:91)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:137)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:111)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:170)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat digital.signage.config.JWTAuthorizationFilter.doFilterInternal(JWTAuthorizationFilter.java:269)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:200)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:116)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:66)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:105)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:56)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:334)\n\tat org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:215)\n\tat org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:178)\n\tat org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\n\tat org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.boot.actuate.autoconfigure.MetricsFilter.doFilterInternal(MetricsFilter.java:106)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n\tat org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:650)\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790)\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1468)\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\n\tat java.lang.Thread.run(Thread.java:748)\n"
  },
  "pagination": null,
  "name": null,
  "code": null,
  "message": "Error while sending mail."
}
```

RESPONSE - code - 200

```json
{
  "result": {
    "isSuccess": true,
    "errorMessage": null,
    "fullStackTrace": null
  },
  "pagination": null,
  "name": null,
  "code": null,
  "message": "Email sent."
}
```

### Customer branding get details for non-loggedin user

---

    /customer/branding/non-logged-in [GET]

HEADER

    NA

QUERY

    origin=https://pidev.impressicocrm.com

BODY

    NA

Server will send response based on the value of query param origin

logo type : CUSTOM_IMAGE = user provided image
NO_IMAGE = no image provided
DEFAULT_IMAGE = default panasonic image

RESPONSE - code - 200

```json
{
  "result": {
    "pageTitle": "My cool title",
    "signedgeBrandUrl": "https://helloworld.com",
    "footerText": "My cool footer",
    "sdn": {
      "adPlatformBrandUrl": "https://helloworld-sdn.com",
      "nameOfCustomer": "hello world"
    },
    "smtp" : {
       "host": "10.55.144.64",
       "port": 25,
       "senderEmailAddress": "abc@abc.com",
       "useTLS": false, // or true
    },
    "leftNavLogo": {
      "logoType": "CUSTOM_IMAGE",       // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://ssss",         // null if not present
      "thumbUrl": "http://ssss"         // null if not present
    },
    "favIconLogo": {
      "logoType": "NO_IMAGE",           // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": null,                  // null if not present
      "thumbUrl": null                  // null if not present
    },
    "centerLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoCenter": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "loginLogoTopRight": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    },
    "sdnLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    }
  },
  "message": null,
  "code": null,
  "name": null,
  "pagination": null
}
```

### Rule Association copy

---

    /demography/rule/association/copy [POST]

HEADER

    NA

QUERY

    NA

BODY

```json
{
  "ruleIdToCopyAssociationsFrom": 355,
  "ruleIdToCopyAssociationsTo": 466
}
```

RESPONSE - 200 - OK

```json
{Same as Rule association GET for the new rule}
```

RESPONSE - 400 - Bad request

```json
{
  "result": null,
  "code": 1,
  "name": "RuleAssocaitionsNotEmpty",
  "message": "Rule Id 466 already has associations in it"
}
```

### Aspect ratio calculation

---

    /aspectRatio/calculate [POST]

HEADER

    NA

QUERY

    NA

BODY

```json
{
  "width": 5120,
  "height": 2880
}
```

RESPONSE - 200 - OK

```json
{
  "result": "16:9",
  "code": 1,
  "name": "AspectRatioCalculated",
  "message": "Aspect Ratio calculated"
}
```

### Add custom Aspect ratio

---

    /aspectRatio [POST]

HEADER

    Authorization : Bearer <userToken>

QUERY

    NA

BODY

```json
{
  "aspectRatio": "16:9",
  "actualWidthInPixel": 8640,
  "actualHeightInPixel": 4860
}
```

RESPONSE - 200 - OK

```json
{
  "result": null,
  "name": null,
  "code": null,
  "message": "Aspect ratio added"
}
```

### Add custom Aspect ratio

---

    /aspectRatio/{aspectRatioId} [PUT]

HEADER

    Authorization : Bearer <userToken>

QUERY

    NA

BODY

```json
{
  "aspectRatio": "16:9",
  "actualWidthInPixel": 8640,
  "actualHeightInPixel": 4860
}
```

RESPONSE - 200 - OK

```json
{
  "result": null,
  "name": null,
  "code": null,
  "message": "Aspect ratio edited"
}
```

RESPONSE - 400 - BadRequest

```json
{
  "result": null,
  "name": "AlreadyInUse",
  "code": 1,
  "message": "Aspect ratio cannot be edited as it is in use. Please create a new one"
}
```

### Branding for PDN

---

    /pdn/customer/branding [GET]

HEADER

    Authorization : Bearer <userToken> // PDN server token only

QUERY

    NA

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": {
    "adPlatformBrandUrl": "https://helloworld-sdn.com",
    "nameOfCustomer": "hello world",
    "sdnLogo": {
      "logoType": "DEFAULT_IMAGE",             // possible values CUSTOM_IMAGE, NO_IMAGE, DEFAULT_IMAGE
      "logoUrl": "http://panasocicimage",      // null if not present
      "thumbUrl": "http://panasocicimage"      // null if not present
    }
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Android TV only - spec for API handling Non-RTC issue

---

    /rtc-issues [POST]

HEADER

    Authorization : Bearer <deviceToken> // Device token only

QUERY

    NA

BODY

```json
[
  {
    configOnTime: "13:00:00",
    configOffTime: "17:00:00",
    rtcIssueStartTimestamp: 154748902114,
    rtcIssueEndTimestamp: 154748942934,
    timeZone: "+05:30",
    weekOffs: ["MON", "WED"],
  },
];
```

RESPONSE - 200 - OK

```json
{
  "result": "Data saved",
  "name": null,
  "code": null,
  "message": "Data saved"
}
```

### Device OS

---

    /deviceOs [GET]

HEADER

    Authorization: Bearer <user token>
    customerId: <customerId>

QUERY

    NA

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": [
    "ANDROID",
    "WINDOWS",
    "LINUX",
    "ANDROID_TV"
   ],
  "name": null,
  "code": null,
  "message": "Data saved"
}
```

### Device OS

---

    /buildOs [GET]

HEADER

    NA

QUERY

    NA

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": [
    "ANDROID",
    "ANDROID_WATCH_DOG",
    "WINDOWS",
    "DESKTOP_APP_SERVER",
    "DESKTOP_APP_CLIENT",
    "DESKTOP_APP_NATIVE",
    "ANDROID_TV"
   ],
  "name": null,
  "code": null,
  "message": "Data saved"
}
```

### Content Archive API

---

    /content/archive [POST]

HEADER

    NA

QUERY

    NA

BODY

```json
{
  "ids": [ 1, 34, 43, 55 ]
}
```

RESPONSE - 200 - OK

```json
{
  "result": {
    "success": [  // if success is not empty then notFound will be empty
      1,
      54,
      767
    ],
    "notFound": [ // if notFound is not empty then success will be empty
      9,
      67,
      24
    ]
  },
  "name": null,
  "code": null,
  "message": "message"
}
```

### Content Un-archive API

---

    /content/unarchive [POST]

HEADER

    NA

QUERY

    NA

BODY

```json
{
  "ids": [ 1, 34, 43, 55 ]
}
```

RESPONSE - 200 - OK

```json
{
  "result": {
    "success": [  // if success is not empty then notFound & badRequest will be empty
      1,
      54,
      767
    ],
    "badRequest": [  // if bad request is not empty then success will be empty
      1,4
    ]
    "notFound": [ // if notFound is not empty then success will be empty
      9,
      67,
      24
    ]
  },
  "name": null,
  "code": null,
  "message": "message"
}
```

### Content archive fetch list API

---

    /content/archive [GET]

HEADER

    NA

QUERY

    NA

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result" : [
    {ContentModel}
  ],
  "name": null,
  "code": null,
  "message": "message"
}
```

### Layout archive fetch list API

---

    /layout/archive [GET]

HEADER

    NA

QUERY

    NA

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result" : [
    {LayoutModel}
  ],
  "name": null,
  "code": null,
  "message": "message"
}
```

### Layout Archive API

---

    /layout/archive [POST]

HEADER

    NA

QUERY

    NA

BODY

```json
{
  "ids": [ 1, 34, 43, 55 ]
}
```

RESPONSE - 200 - OK

```json
{
  "result": {
    "success": [  // if success is not empty then notFound will be empty
      1,
      54,
      767
    ],
    "notFound": [ // if notFound is not empty then success will be empty
      9,
      67,
      24
    ]
  },
  "name": null,
  "code": null,
  "message": "message"
}
```

### Layout un-archive API

---

    /layout/unarchive [POST]

HEADER

    NA

QUERY

    NA

BODY

```json
{
  "ids": [ 1, 34, 43, 55 ]
}
```

RESPONSE - 200 - OK

```json
{
  "result": {
    "success": [  // if success is not empty then notFound & badRequest will be empty
      1,
      54,
      767
    ],
    "badRequest": [  // if bad request is not empty then success will be empty
      1,4
    ]
    "notFound": [ // if notFound is not empty then success will be empty
      9,
      67,
      24
    ]
  },
  "name": null,
  "code": null,
  "message": "message"
}
```

### Show on-premises customer licence information

---

    /premises/customer [GET]

HEADER

    NA

QUERY

    q={
      "from_date": {
        "gte": "2018-12-13"
      },
      "to_date": {
        "lte": "2019-02-05"
      }
    }

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": [
    {
      "onPremisesId": 1,
      "onPremisesEnvironmentName": "pidev3",
      "customerId": 12,
      "custName": "hello",
      "numberOfDevices": 50,
      "consumedNumberOfDevices": 34,
      "availableNumberOfDevices": 16,
      "licenceStartDate": "1517824357000",
      "licenceEndDate": "1517824357000",
      "status": 1
    },
    {
      "onPremisesId": 2,
      "onPremisesEnvironmentName": "SDN UAT",
      "customerId": 12,
      "custName": "hello",
      "numberOfDevices": 50,
      "consumedNumberOfDevices": 34,
      "availableNumberOfDevices": 16,
      "licenceStartDate": "1517824357000",
      "licenceEndDate": "1517824357000",
      "status": 1
    }
  ],
  "name": null,
  "code": null,
  "message": "message"
}
```

### Show on-premises customer licence information by customer ID

---

    /premises/customer/<onPremiseId>/<customerId> [GET]

HEADER

    NA

QUERY

    NA

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": [
    {
      "onPremisesId": 1,
      "onPremisesEnvironmentName": "pidev3",
      "customerId": 12,
      "custName": "hello",
      "numberOfDevices": 50,
      "consumedNumberOfDevices": 34,
      "availableNumberOfDevices": 16,
      "licenceStartDate": "1517824357000",
      "licenceEndDate": "1517824357000",
      "status": 1
    }
  ],
  "name": null,
  "code": null,
  "message": "message"
}
```

### Show on-premises customer licence information

---

    /premises/customer/history/<onPremiseId>/<customerId> [GET]

HEADER

    NA

QUERY

    NA

BODY

    NA

```json
{
  "result": [
    {
      "customerId": 21,
      "custName": "hello",
      "licenceChangeActivityDate": 1517824355000,
      "changedByUserId": 123,
      "changedByUserFullname": "Cool Admin",
      "beforeNumberOfDevices": 23,
      "afterNumberOfDevices": 55,
      "beforeLicenceStartDate": 1517824355000,
      "beforeLicenceEndDate": 1517824357000,
      "afterLicenceStartDate": 1517824355000,
      "afterLicenceEndDate": 1517824357000
    }
  ],
  "name": null,
  "code": null,
  "message": "message"
}
```

### TPA OS

---

    /tpapp/os [GET]

HEADER

    NA

QUERY

    NA

BODY

    NA

```json
{
  "result": [
    "ANDROID",
    "WINDOWS",
    "LINUX"
  ],
  "name": null,
  "code": null,
  "message": null
}
```

### DG content playback - summary

---

    /reports/fa/dg-content-playback-summary [GET]

HEADER

    NA

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    ln=EN   // optional - defaults to EN if not provided
    q={
      "from_date": {        // required
        "gte": "2019-12-13"
      },
      "to_date": {          // required
        "lte": "2019-12-14"
      },
      "location": {        // optional
        "eq": 12
      },
      "device": {
        "eq": 44           // optional
      },
      "durationType": {     // required
        "eq" : "DAILY"   // DAILY, WEEKLY, MONTHLY  (HOURLY is not there for this report
      }
    }

BODY

    NA

RESPONSE - 200 - OK

```json

{
    "result": {
        "reportToken": "2hfh34fjwpoefj9",
        "isDataFullyEmpty": false, // if for given search criteria there is not data then this flag will be false
        "downloadAsXls": "http://abc.com/asdsads83",
        "downloadAsPdf": "http://abc.com/asdsads83",
        "layoutNameHeaders":  [
            {
              "layoutId": 12,
              "layoutName": "l1"
            },
            {
              "layoutId": 13,
              "layoutName": "l2"
            },
            {
              "layoutId": 14,
              "layoutName": "l3"
            }
        ],
        "data": [
            {
                "date": "26-04-2019",
                "layoutCount": [
                  6,
                  7,
                  8
                ]
            },
            {
                "date": "27-04-2019",
                "layoutCount": [
                  6,
                  7,
                  8
                ]
            },
            {
                "date": "28-04-2019",
                "layoutCount": [
                  6,
                  7,
                  8
                ]
            }
        ],
        "totalNoOfRuns": [
            30,
            67,
            97
        ],
        "totalDurationInMin": [
            12.2,
            54,
            6.8
        ]
    },
    "name": null,
    "code": null,
    "message": null
}
//Note:- sort order of layoutNameHeaders,totalDurationInMin,totalNoOfRuns as layout names.
```

### DG content playback - detail

---

    /reports/fa/dg-content-playback-detail [GET]

HEADER

    NA

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    pageNumber=12
    numPerPage=20
    ln=EN   // optional - defaults to EN if not provided
    q={
      "from_date": {        // required
        "gte": "2019-12-13"
      },
      "to_date": {          // required
        "lte": "2019-12-14"
      },
      "location": {        // optional
        "eq": 12
      },
      "device": {
        "eq": 44           // optional
      },
      "from_time": {        // optional
        "gte": "10:00"
      },
      "to_time": {          // optional
        "lte": "12:00"
      },
      "layoutId": {      // optional (for DG campaign name)
        "eq": 18
      }
    }

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",
    "isDataFullyEmpty": false,       // if for given search criteria there is not data then this flag will be false
    "isReportCompleted": true,
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "date": "25-04-2019",
        "startTime": "20:00:38",
        "endTime": "21:00:38",
        "layoutId": 324,
        "layoutName": "Camp 1",
        "dgProperty": "M, M 30", // comma separated dg props
        "deviceId": 15,
        "deviceName": "hello device",
        "duration": "00:10:13"
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null,
  "pagination": {
    "numPerPage": 20,
    "currentPage": 1,
    "pageCount": 2,
    "previousPage": null,
    "nextPage": 2,
    "firstItemNumber": 1,
    "lastItemNumber": 20,
    "totalItemCount": 30
  }
}
```

### DG property data

---

    /reports/fa/dg-property-data [GET]

HEADER

    NA

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    ln=EN   // optional - defaults to EN if not provided
    q={
      "from_date": {        // required
        "gte": "2019-12-13"
      },
      "to_date": {          // required
        "lte": "2019-12-14"
      },
      "location": {        // optional
        "eq": 12
      },
      "device": {
        "eq": 44           // optional
      },
      "durationType": {     // required
        "eq" : "DAILY"   // DAILY, WEEKLY, MONTHLY  (HOURLY is not there for this report
      },
      "layoutId": {     // required
        "eq" : 18
      }
    }

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": {
     "dgPropertyData": "M 16, F 45"
  },
  "name": null,
  "code": null,
  "message": null,
}
```

### Get builds signed URL for S3 object key

---

    /app-upgrade/builds-signed-url [POST]

HEADER

    Authorization: Bearer <jenkins token>

BODY

```
{
  "s3ObjectKey": "my/object/key"
}
```

RESPONSE - 200 - OK

```json
{
  "result": {
     "signedUrl": "https://s3signedurl"
  },
  "name": null,
  "code": null,
  "message": null,
}
```

### Device storage analytics

---

    /device-storage-analytics [POST]

HEADER

    Authorization: Bearer <device token>

BODY

```json
{
  "availableSpaceOnDeviceInBytes": 190182,
  "totalSpaceOnDeviceInBytes": 190182,
  "isThresholdReached": true
}
```

RESPONSE - 200 - OK

```json
{
  "result": null,
  "name": "Saved",
  "code": 20,
  "message": "Data saved successfully",
}
```

### Device S3 analytics

---

    /device-s3-analytics [POST]

HEADER

    Authorization: Bearer <device token>

BODY

```json
[
  {
    s3ConsumedDataInBytes: 19012380,
    date: "2020-08-18",
  },
  {
    s3ConsumedDataInBytes: 19012380,
    date: "2020-08-19",
  },
];
```

RESPONSE - 200 - OK

```json
{
  "result": null,
  "name": "Saved",
  "code": 20,
  "message": "Data saved successfully",
}
```

### App upgrade reason for failed data collection

---

    /app-upgrade/failure-reasons [POST]

HEADER

    Authorization: Bearer <device token>

BODY

```json
[
  {
    reasonForFailure: "App Download failed due to MD5 mismatch",
    timestampOfFailureEvent: 156376188121,
  },
  {
    reasonForFailure: "App Download failed due to MD5 mismatch",
    timestampOfFailureEvent: 156376188121,
  },
];
```

RESPONSE - 200 - OK

```json
{
  "result": null,
  "name": "Saved",
  "code": 20,
  "message": "Data saved successfully",
}
```

### Number of times checksum and encryption has failed

---

    /analytics/checksum-encryption-failure [POST]

HEADER

    Authorization: Bearer <device token>

BODY

```json
[
  {
    failureType: "CHECKSUM_FAILURE", // possible values: CHECKSUM_FAILURE, ENCRYPTION_FAILURE
    timestampOfFailureEvent: 156376188121,
    contentId: 12312,
  },
  {
    failureType: "ENCRYPTION_FAILURE", // possible values: CHECKSUM_FAILURE, ENCRYPTION_FAILURE
    timestampOfFailureEvent: 156376188121,
    contentId: 12313,
  },
];
```

RESPONSE - 200 - OK

```json
{
  "result": null,
  "name": "Saved",
  "code": 20,
  "message": "Data saved successfully",
}
```

### DMB Report

---

    /reports/dmb-report [GET]

HEADER

    Authorization: Bearer <token>
    customerId: <customerId>

QUERY

    report-token=2hfh34fjwpoefj9  // if sent in request then server should reuse the json from cache and operate on it
    ln=EN   // optional - defaults to EN if not provided
    q={
      "from_date": {        // required
        "gte": "2019-12-13"
      },
      "location": {        // optional
        "eq": 12
      }
    }

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": {
    "reportToken": "2hfh34fjwpoefj9",
    "isDataFullyEmpty": false,
    "isReportCompleted": true,
    "downloadAsXls": "http://abc.com/asdsads83",
    "downloadAsPdf": "http://abc.com/asdsads83",
    "data": [
      {
        "type": "OFF",
        "numberOfHrs": null,
        "count": 99,
        "percentage": 8,
        "devices": [
          {
            "deviceId" : 12,
            "deviceName" : "Cool device 12"
          }
        ]
      },
      {
        "type": "ON",
        "numberOfHrs": 2,
        "count": 0,
        "percentage": 8,
        "devices": [
          {
            "deviceId" : 12,
            "deviceName" : "Cool device 12"
          }
        ]
      },
      {
        "type": "ON",
        "numberOfHrs": 3,
        "count": 0,
        "percentage": 8,
        "devices": [
          {
            "deviceId" : 12,
            "deviceName" : "Cool device 12"
          }
        ]
      },
      {
        "type": "ON",
        "numberOfHrs": 24,
        "count": 367,
        "percentage": 84,
        "devices": [
          {
            "deviceId" : 12,
            "deviceName" : "Cool device 12"
          }
        ]
      },
      {
        "type": "TOTAL",
        "numberOfHrs": null,
        "count": 190,
        "percentage": 100
      }
    ]
  },
  "code": 1,
  "name": "",
  "message": ""
}
```

### Unavailable media players report

---

    /reports/unavailable-devices [GET]

HEADER

    Authorization: Bearer <token>
    customerId: <customerId>

QUERY

    ln=EN   // optional - defaults to EN if not provided
    date-range=PREVIOUS_DAY // required - allowed parameters : LAST_7_DAYS LAST_15_DAYS LAST_1_MONTH LAST_3_MONTHS

BODY

    NA

RESPONSE - 200 - OK

```json
{
  "result": {
    "locationId": 893,
    "locationName": "Delhi NCR",
    "unavailableDevicesCount": 3,
    "childNode": [
      {
        "locationId": 5461,
        "locationName": "Delhi",
        "unavailableDevicesCount": 2,
        "childNode": [
          {
            "locationId": 3903,
            "locationName": "Kondli",
            "unavailableDevicesCount": 2,
            "unavailableDevices": [
              {
                "deviceId": 91,
                "deviceName": "Cool media player 1",
                "deviceGroupId": null,
                "deviceGroupName": null
              },
              {
                "deviceId": 92,
                "deviceName": "Cool media player 2",
                "deviceGroupId": 12,
                "deviceGroupName": "Cafeteria"
              }
            ],
            "childNode": null
          }
        ]
      },
      {
        "locationId": 5909,
        "locationName": "Noida",
        "unavailableDevicesCount": 1,
        "childNode": [
          {
            "locationId": 904038,
            "locationName": "Sector 18",
            "unavailableDevicesCount": 1,
            "unavailableDevices": [
              {
                "deviceId": 93,
                "deviceName": "Cool media player 2",
                "deviceGroupId": 12,
                "deviceGroupName": "Cafeteria"
              }
            ],
            "childNode": null
          }
        ]
      },
      {
        "locationId": 5909,
        "locationName": "Gurgaon",
        "unavailableDevicesCount": 0,
        "childNode": [
          {
            "locationId": 382789,
            "locationName": "Sector 49",
            "unavailableDevicesCount": 0,
            "unavailableDevices": [],
            "childNode": null
          }
        ]
      }
    ]
  },
  "name": null,
  "code": null,
  "message": null
}
```

### Video playback codec error reporting
---

To be called every hour by devices.

    /analytics/video-codec-errors [POST]

HEADER

    Authorization: Bearer <device token>

QUERY

    NA

BODY

```json
[
  {
    "timeOfError": 1666336166000, // unix epoch time when the error occurred, in milliseconds
    "contentId": 1282, // content Id which errored 
    "codecErrorMessage": "string" // max 500 characters
  },
  {
    "timeOfError": 1666336466000, // unix epoch time when the error occurred, in milliseconds
    "contentId": 1284, // content Id which errored 
    "codecErrorMessage": "string" // max 500 characters
  }
]
```

RESPONSE - 200 - OK

```json
{
  "result": null,
  "name": null,
  "code": null,
  "message": "Data saved successfully"
}
```

RESPONSE - 400 - BAD REQUEST

```json
{
  "result": null,
  "name": null,
  "code": null,
  "message": "Error saving data. Error messages can be of maximum 500 characters long"
}
```