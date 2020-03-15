package com.unsplash.apihelper;

import com.unsplash.utils.Props;

public class ApiEndpoint {

    public static String GET_USER_COLLECTION = Props.getProp("apiBaseUrl") + "users/" +
            Props.getProp("userProfile") + "/collections";
    public static String DELETE_COLLECTION = Props.getProp("apiBaseUrl") + "collections/";
}
