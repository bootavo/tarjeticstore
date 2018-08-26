package com.example.bucket.service;

import com.example.bucket.model.Category;
import com.example.bucket.model.Rol;
import com.example.bucket.model.User;
import com.example.bucket.repository.RolRepository;
import com.example.bucket.utils.PrettyJson;
import com.example.bucket.utils.ResponseHelper;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class RolService {

    private static Logger logger = LogManager.getLogger(RolService.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    RolRepository repository;

    public @ResponseBody JsonObject getRols(){

        logger.info("#--------------INICIO DE SERVICIO login--------------#");
        JsonObject jsonResponse = null;
        List<Rol> data = null;

        try {
            data = new ArrayList<>();
            data = (List<Rol>) repository.findAll();
        }catch (Exception e){
            logger.info("Exception: "+e);
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, "roles");
        }
        logger.info("#--------------FIN DE SERVICIO--------------#");
        return jsonResponse;

    }

    public @ResponseBody JsonObject registerRol(Rol rolRquest){
        logger.info("#--------------INICIO DE SERVICIO login--------------#");
        JsonObject jsonResponse = null;
        Rol data = null;

        try {

            Query query = em.createNativeQuery("select COALESCE(max(id_rol)+1, 1) as id_rol from t_rol; ");
            List<BigInteger> resultList = query.getResultList();
            int id_rol = Integer.parseInt(resultList.get(0).toString());

            data = new Rol();
            data.setId_rol(id_rol);
            data.setNombre(rolRquest.getNombre());
            data.setEstado("A");
            repository.save(data);

        }catch (Exception e){
            logger.info("Exception: "+e);
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, null);
        }
        logger.info("#--------------FIN DE SERVICIO--------------#");
        return jsonResponse;
    }

}
