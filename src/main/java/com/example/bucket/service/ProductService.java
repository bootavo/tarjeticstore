package com.example.bucket.service;

import com.example.bucket.model.Product;
import com.example.bucket.repository.ProductRepository;
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
public class ProductService {

    private static Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;
    @PersistenceContext
    private EntityManager em;

    public @ResponseBody JsonObject getProducts() {
        logger.info("#--------------INICIO DE SERVICIO getProductos()--------------#");
        JsonObject jsonResponse = null;
        List<Product> data = null;
        try {
            data = (List<Product>) repository.findAll();
        }catch (Exception e){
            logger.info("Exception: "+e);
            e.printStackTrace();
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, "productos");
        }
        logger.info("#--------------FIN DE SERVICIO getProductos()--------------#");
        return jsonResponse;
    }

    public @ResponseBody JsonObject registerProduct(Product request){
        logger.info("#--------------INICIO DE SERVICIO getProductos()--------------#");
        JsonObject jsonResponse = null;

        Query query = em.createNativeQuery("select COALESCE(max(id_producto)+1, 1) as id_producto from t_producto; ");
        List<BigInteger> resultList = query.getResultList();
        int id_producto = Integer.parseInt(resultList.get(0).toString());
        logger.info("#--id_producto: "+id_producto+"--------------#");

        Product data = new Product();
        data.setId_producto(id_producto);
        data.setNombre(request.getNombre());
        data.setDescripcion(request.getDescripcion());
        data.setPrecio(request.getPrecio());
        data.setPrecio_fichas(request.getPrecio_fichas());
        data.setEstado("A");
        data.setStock(request.getStock());
        data.setImagen(request.getImagen());
        data.setId_categoria(request.getId_categoria());

        try {
            repository.save(data);
        }catch (Exception e){
            data = null;
            logger.info("Exception: "+e);
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, null);
        }

        logger.info("#--------------FIN DE SERVICIO getProductos()--------------#");
        return jsonResponse;
    }

}
