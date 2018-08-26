package com.example.bucket.service;

import com.example.bucket.model.Category;
import com.example.bucket.repository.CategoriaRepository;
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
import java.util.List;

@Service
public class CategoryService {

    private static Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private CategoriaRepository repository;

    @PersistenceContext private EntityManager em;

    public @ResponseBody JsonObject registerCategory(Category request){

        logger.info("#--------------INICIO DE SERVICIO login--------------#");
        JsonObject jsonResponse = null;
        Category data = null;

        try {

            Query query = em.createNativeQuery("select COALESCE(max(id_categoria)+1, 1) as id_categoria from t_categoria; ");
            List<BigInteger> resultList = query.getResultList();
            int id_category = Integer.parseInt(resultList.get(0).toString());

            data = new Category();
            data.setId_categoria(id_category);
            data.setNombre(request.getNombre());
            data.setDescripcion(request.getDescripcion());
            data.setEstado("A");
            repository.save(data);

        }catch (Exception e){
            logger.info("Exception: "+e);
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, null);
        }

        return jsonResponse;

    }

    public @ResponseBody JsonObject getCategories(){
        logger.info("#--------------INICIO DE SERVICIO getCategories()--------------#");
        JsonObject jsonResponse = null;
        List<Category> data = null;

        try{
            data = (List<Category>) repository.findAll();
        }catch (Exception e){
            logger.info("Exception: "+e);
            e.printStackTrace();
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, "categorias");
        }
        logger.info("#--------------FIN DE SERVICIO getCategories()--------------#");
        return jsonResponse;
    }

}
