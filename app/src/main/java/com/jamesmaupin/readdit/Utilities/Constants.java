package com.jamesmaupin.readdit.Utilities;

import java.util.UUID;

/**
 * Created by jmsgears on 1/3/16.
 */
public final class Constants {
    public final static String DEVICE_ID                = UUID.randomUUID().toString();
    public final static String BASE_URL                 = "https://www.reddit.com/api/v1/";
    public final static String OAUTH_BASE_URL           = "https://oauth.reddit.com/";
    public final static String CLIENT_ID                = "";
    public final static String CREDENTIALS              = CLIENT_ID + ":" + "";
    public final static String REDIRECT_URI             = "http://localhost";
    public final static String GRANT_TYPE_REFRESH       = "refresh_token";
    public final static String GRANT_TYPE_AUTHORIZE     = "authorization_code";
    public final static String GRANT_TYPE_USERLESS      = "https://oauth.reddit.com/grants/installed_client";
    public final static String DURATION                 = "permanent";
    public final static String SCOPE                    = "read";
}
