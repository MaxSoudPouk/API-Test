package com.example.etlkycsimregisterapi.global;

import java.util.HashMap;

public class GlobalParameter {


    public static HashMap<String, String> hMapTransaction = new HashMap<String, String>();


    public static HashMap<String, String> hMapOTP_store = new HashMap<String, String>();

    //hMapLoginFailSession   mobile number = countFailLastFail|date time new allow


    public static String lotto_patch_cancel_bill = "/home/kycimages/";


    public static boolean flageProcessState = true;
    public static boolean flageProcessInsertLoginHistoryState = true;
    public static boolean flageProcessSendNotificationState = true;
    public static String homePatchLogPatchLogCDR;
    public static String homePatchLogPatchLogCDRQuery;
    public static String homePatchLogSQLfail;
    public static String homePatchLogProcessDerail;

    public static String jwt_sec_key = "9c5f1181fd9341ea08885dd5574be63a51545342e69b6e561301c2858d1abb03";
    public static String sign_key = "36b128b5dada223c04779bd27687b3c17b375a5d19a2ee735507922af63bcaad";


    ///===================================================================================
    public static String error_continues = "100";
    public static String error_continues_msg = "Continue";

    public static String error_switching_protocols = "101";
    public static String error_switching_protocols_msg = "Switching Protocols";

    public static String error_processing = "102";
    public static String error_processings_msg = "Processing (WebDAV; RFC 2518)";

    public static String error_early_hints = "103";
    public static String error_early_hints_msg = "Early Hints";

    public static String error_ok_success = "200";
    public static String error_ok_success_msg = "OK, success";

    public static String error_created = "201";
    public static String error_created_msg = "Created";

    public static String error_accepted = "202";
    public static String error_accepted_msg = "Accepted";

    public static String error_non_authoritative = "203";
    public static String error_non_authoritative_msg = "Non-Authoritative Information ";

    public static String error_non_authoritative_sign = "2033";
    public static String error_non_authoritative_msg_sign = "Non-Authoritative, invalid sign";


    public static String error_non_authoritative_sign_wrong_lengt = "2034";
    public static String error_non_authoritative_sign_wrong_lengt_msg = "Non-Authoritative, invalid sign lenth";

    public static String error_no_content = "203";
    public static String error_no_content_msg = "No Content";


    public static String error_reset_content = "205";
    public static String error_reset_content_msg = "Reset Content";

    public static String error_partial_content = "206";
    public static String error_partial_content_msg = "Partial Content";

    public static String error_multi_status = "207";
    public static String error_multi_status_msg = "Multi-Status";

    public static String error_already_reported = "208";
    public static String error_already_reported_msg = "Already Reported";


//    226 IM Used (RFC 3229)
//    3xx redirection
//    300 Multiple Choices
//    301 Moved Permanently
//    302 Found (Previously "Moved temporarily")
//    303 See Other (since HTTP/1.1)
//    304 Not Modified (RFC 7232)
//    305 Use Proxy (since HTTP/1.1)
//    306 Switch Proxy
//    307 Temporary Redirect (since HTTP/1.1)
//    308 Permanent Redirect (RFC 7538)
//    4xx client errors
//
//    404 error on Wikimedia
//    400 Bad Request
//    401 Unauthorized (RFC 7235)
//    402 Payment Required
//
//    403 Forbidden
//    404 Not Found
//    405 Method Not Allowed
//    406 Not Acceptable


    public static String error_not_acceptable = "406";
    public static String error_not_acceptable_msg = "parameter not acceptable";

    //    407 Proxy Authentication Required (RFC 7235)
//    408 Request Timeout
//    409 Conflict
//    410 Gone
//    411 Length Required
//    412 Precondition Failed (RFC 7232)
//    413 Payload Too Large (RFC 7231)
//    414 URI Too Long (RFC 7231)
//    415 Unsupported Media Type (RFC 7231)
//    416 Range Not Satisfiable (RFC 7233)
//    417 Expectation Failed
//    418 I'm a teapot (RFC 2324, RFC 7168)
//    421 Misdirected Request (RFC 7540)
//    422 Unprocessable Entity (WebDAV; RFC 4918)
//    423 Locked (WebDAV; RFC 4918)
//    424 Failed Dependency (WebDAV; RFC 4918)
//    425 Too Early (RFC 8470)
//    Indicates that the server is unwilling to risk processing a request that might be replayed.
    public static String error_Upgrade_Required = "426";
    public static String error_Upgrade_Required_msg = "upgrade required not acceptable";
//    426 Upgrade Required
//    The client should switch to a different protocol such as TLS/1.3, given in the Upgrade header field.[56]
//    428 Precondition Required (RFC 6585)
//    429 Too Many Requests (RFC 6585)
//    431 Request Header Fields Too Large (RFC 6585)

    public static String error_unavailable = "451";
    public static String error_unavailable_msg = "Unavailable For Legal Reasonsd";
    //
//    451 Unavailable For Legal Reasons (RFC 7725)
//
//    500 Internal Server Error
//    501 Not Implemented
//    502 Bad Gateway
//    503 Service Unavailable
//    504 Gateway Timeout
//    505 HTTP Version Not Supported
//    506 Variant Also Negotiates (RFC 2295)
//    507 Insufficient Storage (WebDAV; RFC 4918)
//    508 Loop Detected (WebDAV; RFC 5842)
//    510 Not Extended (RFC 2774)
//    511 Network Authentication Required (RFC 6585)
//    419 Page Expired (Laravel Framework)
//    420 Method Failure (Spring Framework)
//    420 Enhance Your Calm (Twitter)
//
//    430 Request Header Fields Too Large (Shopify)
//    450 Blocked by Windows Parental Controls (Microsoft)
//    498 Invalid Token (Esri)
    public static String error_invalidtoken = "498";
    public static String error_invalidtoken_msg = "Invalid Token";
//    499 Token Required (Esri)

//    509 Bandwidth Limit Exceeded (Apache Web Server/cPanel)
//    529 Site is overloaded
//    530 Site is frozen
//    598 (Informal convention) Network read timeout error
//    599 Network Connect Timeout Error
//    440 Login Time-out
//    449 Retry With
//    451 Redirect
//    444 No Response
//    494 Request header too large
//    495 SSL Certificate Error
//    496 SSL Certificate Required
//    497 HTTP Request Sent to HTTPS Port
//    499 Client Closed Request
//    520 Web Server Returned an Unknown Error
//    521 Web Server Is Down
//    522 Connection Timed Out
//    523 Origin Is Unreachable
//    524 A Timeout Occurred
//    525 SSL Handshake Failed
//    526 Invalid SSL Certificate
//    527 Railgun Error
//    530 is returned along with a 1xxx error.[
//    460 Client closed the connection 
//    110 Response is Stale
//    111 Revalidation Failed
//    112 Disconnected Operation


//    113 Heuristic Expiration
//    199 Miscellaneous Warning
//    214 Transformation Applied
//    299 Miscellaneous Persistent Warning

}
