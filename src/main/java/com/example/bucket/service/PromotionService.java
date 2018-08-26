package com.example.bucket.service;

import com.example.bucket.model.*;
import com.example.bucket.repository.PromotionExtraRepository;
import com.example.bucket.repository.PromotionProductoRepository;
import com.example.bucket.repository.PromotionRepository;
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
public class PromotionService implements PromotionExtraRepository {

    private static Logger logger = LogManager.getLogger(PromotionService.class);
    @Autowired PromotionRepository repository;
    @Autowired PromotionProductoRepository repositoryPromotionProduct;
    @PersistenceContext private EntityManager em;

    public @ResponseBody JsonObject registerPromotion(PromotionRequest request){
        logger.info("#--------------INICIO DE SERVICIO registerPromotion()--------------#");
        JsonObject jsonResponse = null;

        Query query = em.createNativeQuery("select COALESCE(max(id_promocion)+1, 1) as id_promocion from t_promocion; ");
        List<BigInteger> resultList = query.getResultList();
        int id_promocion = Integer.parseInt(resultList.get(0).toString());
        logger.info("#--id_promocion: "+id_promocion+"--------------#");

        Promotion data = new Promotion();

        try {
            data.setId_promocion(id_promocion);
            data.setNombre(request.getPromocion().getNombre());
            data.setDescripcion(request.getPromocion().getDescripcion());
            data.setEstado("A");
            data.setImagen(request.getPromocion().getImagen());
            data.setPrecio(request.getPromocion().getPrecio());
            data.setPrecio_fichas(request.getPromocion().getPrecio_fichas());
            repository.save(data);

            logger.info("size: "+request.getProductos().size());
            if (request.getProductos() != null || request.getProductos().isEmpty()) {
                PromotionProducto promotionProducto = null;
                PromotionIdentity identity = null;
                for (int i=0; i<request.getProductos().size(); i++){
                    logger.info("count: "+i);
                    logger.info("id_producto: "+request.getProductos().get(i).getId_producto());
                    promotionProducto = new PromotionProducto();

                    identity = new PromotionIdentity();
                    identity.setId_promocion(id_promocion);
                    identity.setId_producto(request.getProductos().get(i).getId_producto());

                    promotionProducto.setId(identity);
                    promotionProducto.setEstado("A");

                    repositoryPromotionProduct.save(promotionProducto);
                }
            }

        }catch (Exception e){
            data = null;
            logger.info("Exception: "+e);
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, null);
        }

        logger.info("#--------------FIN DE SERVICIO registerPromotion()--------------#");
        return jsonResponse;

    }

    public @ResponseBody JsonObject getPromotions(){
        logger.info("#--------------INICIO DE SERVICIO getProductos()--------------#");
        JsonObject jsonResponse = null;
        List<Promotion> data = null;
        try {
            data = (List<Promotion>) repository.findAll();
        }catch (Exception e){
            logger.info("Exception: "+e);
            e.printStackTrace();
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, "promociones");
        }
        logger.info("#--------------FIN DE SERVICIO getProductos()--------------#");
        return jsonResponse;
    }

    @Override
    public JsonObject getPromotionsModified() {
        logger.info("#--------------INICIO DE SERVICIO login--------------#");
        JsonObject jsonResponse = null;
        List<Promotion> data = null;
        try {
            data = (List<Promotion>) em.createNativeQuery(" select pr.id_promocion, pr.nombre, pr.descripcion, pr.estado, pr.precio, pr.precio_fichas, pr.imagen, "+
                    " (select count(*) from t_promocion_producto pp " +
                    " where pp.id_promocion = pr.id_promocion) as cantidad_productos  "+
                    " from t_promocion pr ", Promotion.class).getResultList();
        }catch (Exception e){
            logger.info("Exception: "+e);
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, "promociones");
        }
        logger.info("#--------------FIN DE SERVICIO--------------#");
        return jsonResponse;
    }
}
