package com.ldq.appnhac.Server;

public class APIServer {
    private static String base_url="https://quoc08510.000webhostapp.com/Server/";

    public static Dataserver getServer(){
        return APIRetrofitClient.getClinet(base_url).create(Dataserver.class);
    }
}
