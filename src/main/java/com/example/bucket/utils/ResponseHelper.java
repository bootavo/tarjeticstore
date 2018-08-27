package com.example.bucket.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class ResponseHelper {

    private static Logger logger = LogManager.getLogger(ResponseHelper.class);

    public @ResponseBody JsonObject buildResponseObject(Object object, String name){

        JsonObject jsonResponse = new JsonObject();
        int status = 0;

        try {
            if (object == null) {
                status = Constants.ERROR_CODE;
                logger.error("objetct: null");
            }else {
                status = Constants.SUCCESS_GET_CODE;
                logger.info("objetct: no null");
            }

            jsonResponse.add("status", new Gson().toJsonTree(StatusHelper.getStatus(status)));

            if (name != null){
                JsonObject jsonResults = new JsonObject();
                jsonResults.add(name, new Gson().toJsonTree(object));

                jsonResponse.add("results", jsonResults);
            }

            String prettyJson = PrettyJson.toPrettyFormat(jsonResponse.toString());
            System.out.println(prettyJson);

        }catch (Exception e){
            logger.error("Exception: "+e);
        }

        return jsonResponse;
    }

    public @ResponseBody JsonObject buildResponseList(List<Object> mList, String name){
        JsonObject jsonResponse = new JsonObject();
        int status = 0;

        try {

            if (mList == null) {
                status = Constants.ERROR_CODE;
            }else {
                status = Constants.SUCCESS_GET_CODE;
            }

            jsonResponse.add("status", new Gson().toJsonTree(StatusHelper.getStatus(status)));

            JsonObject jsonResults = new JsonObject();
            jsonResults.add(name, new Gson().toJsonTree(mList));

            jsonResponse.add("results", jsonResults);

            String prettyJson = PrettyJson.toPrettyFormat(jsonResponse.toString());
            System.out.println(prettyJson);

        }catch (Exception e) {
            logger.error("Exception: "+e);
        }

        return jsonResponse;
    }

}
