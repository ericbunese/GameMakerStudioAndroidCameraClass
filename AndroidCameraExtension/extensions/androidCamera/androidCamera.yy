{
    "id": "bcb1ac81-881f-4e66-990a-44217d1b5fe8",
    "modelName": "GMExtension",
    "mvc": "1.0",
    "name": "androidCamera",
    "IncludedResources": [
        
    ],
    "androidPermissions": [
        "android.permission.CAMERA",
        "android.permission.WRITE_EXTERNAL_STORAGE",
        "android.permission.READ_EXTERNAL_STORAGE"
    ],
    "androidProps": true,
    "androidactivityinject": "",
    "androidclassname": "AndroidCameraClass",
    "androidinject": "",
    "androidmanifestinject": "<uses-feature android:name=\"android.hardware.camera\" android:required=\"false\" \/>\\u000a\\u000d",
    "androidsourcedir": "",
    "author": "",
    "classname": "",
    "copyToTargets": 8,
    "date": "2018-31-22 08:11:02",
    "description": "",
    "extensionName": "",
    "files": [
        {
            "id": "e5ca40ab-c388-4b0a-8591-f0cf3972b350",
            "modelName": "GMExtensionFile",
            "mvc": "1.0",
            "ProxyFiles": [
                
            ],
            "constants": [
                
            ],
            "copyToTargets": 8,
            "filename": "androidCamera.ext",
            "final": "",
            "functions": [
                {
                    "id": "197fb0f7-2331-49a5-978c-280a7388840c",
                    "modelName": "GMExtensionFunction",
                    "mvc": "1.0",
                    "argCount": 0,
                    "args": [
                        1
                    ],
                    "externalName": "androidCamera_init",
                    "help": "androidCamera_init(pictures saving path)",
                    "hidden": false,
                    "kind": 4,
                    "name": "androidCamera_init",
                    "returnType": 2
                },
                {
                    "id": "0f67a80b-f997-44ac-a3d5-d60023789d9e",
                    "modelName": "GMExtensionFunction",
                    "mvc": "1.0",
                    "argCount": 0,
                    "args": [
                        1
                    ],
                    "externalName": "androidCamera_open_camera",
                    "help": "androidCamera_open_camera(string filename)",
                    "hidden": false,
                    "kind": 4,
                    "name": "androidCamera_open_camera",
                    "returnType": 2
                },
                {
                    "id": "96e33201-2322-4f80-8e72-40cf4494af63",
                    "modelName": "GMExtensionFunction",
                    "mvc": "1.0",
                    "argCount": 0,
                    "args": [
                        
                    ],
                    "externalName": "androidCamera_request_permissions",
                    "help": "androidCamera_request_permissions()",
                    "hidden": false,
                    "kind": 4,
                    "name": "androidCamera_request_permissions",
                    "returnType": 2
                }
            ],
            "init": "",
            "kind": 4,
            "order": [
                "197fb0f7-2331-49a5-978c-280a7388840c",
                "0f67a80b-f997-44ac-a3d5-d60023789d9e",
                "96e33201-2322-4f80-8e72-40cf4494af63"
            ],
            "origname": "",
            "uncompress": false
        }
    ],
    "gradleinject": "android {\\u000a\\u000d    lintOptions {\\u000a\\u000d        abortOnError false\\u000a\\u000d    }\\u000a\\u000d}\\u000a\\u000d\\u000a\\u000d",
    "helpfile": "",
    "installdir": "",
    "iosProps": false,
    "iosSystemFrameworkEntries": [
        
    ],
    "iosThirdPartyFrameworkEntries": [
        
    ],
    "iosplistinject": "",
    "license": "",
    "maccompilerflags": "",
    "maclinkerflags": "",
    "macsourcedir": "",
    "packageID": "",
    "productID": "",
    "sourcedir": "",
    "tvosProps": false,
    "tvosSystemFrameworkEntries": [
        
    ],
    "tvosThirdPartyFrameworkEntries": [
        
    ],
    "tvosclassname": "",
    "tvosmaccompilerflags": "",
    "tvosmaclinkerflags": "",
    "tvosplistinject": "",
    "version": "1.0.0"
}