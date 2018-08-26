package com.example.bucket.service;

import com.example.bucket.model.Inventory;
import com.example.bucket.model.Persona;
import com.example.bucket.model.User;
import com.example.bucket.model.UserRequest;
import com.example.bucket.repository.InventoryRepository;
import com.example.bucket.repository.PersonaRepository;
import com.example.bucket.repository.UserRepository;
import com.example.bucket.repository.UserExtraRepository;
import com.example.bucket.utils.*;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Service
public class UserService implements UserExtraRepository {

    private static Logger logger = LogManager.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired PersonaRepository repository;
    @Autowired UserRepository repositoryUser;
    @Autowired InventoryRepository repositoryInventory;

    @Override
    public @ResponseBody JsonObject login(UserRequest userRequest) {
        logger.info("#--------------INICIO DE SERVICIO login--------------#");
        JsonObject jsonResponse = null;
        UserRequest data = null;
        try {
            data = (UserRequest) em.createNativeQuery(" select us.id_usuario, us.id_rol, pe.id_persona, pe.nombres, pe.apellidos, pe.direccion, pe.telefono_contacto,  "+
                    " pe.dni, pe.correo, us.usuario, us.clave, us.estado, pe.imagen, us.codigo_app, us.pedidos_cancelados, us.tipo_registro, inv.total_fichas "+
                    " from t_persona pe "+
                    " inner join t_usuario us on pe.id_persona = us.id_persona"+
                    " inner join t_inventario inv on us.id_persona = inv.id_usuario "+
                    " where us.usuario = '"+ userRequest.getUsuario()+"' and us.clave = '"+ userRequest.getClave()+"' ", UserRequest.class).getSingleResult();
        }catch (Exception e){
            logger.info("Exception: "+e);
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, "user");
        }
        logger.info("#--------------FIN DE SERVICIO--------------#");
        return jsonResponse;
    }

    @Override
    public JsonObject getUserById(int id_usuario) {
        logger.info("#--------------INICIO DE SERVICIO getUserById--------------#");
        JsonObject jsonResponse = null;
        UserRequest data = null;
        try {
            data = (UserRequest) em.createNativeQuery(" select us.id_usuario, us.id_rol, pe.id_persona, pe.nombres, pe.apellidos, pe.direccion, pe.telefono_contacto,  "+
                    " pe.dni, pe.correo, us.usuario, us.clave, us.estado, pe.imagen, us.codigo_app, us.pedidos_cancelados, us.tipo_registro, inv.total_fichas "+
                    " from t_persona pe "+
                    " inner join t_usuario us on pe.id_persona = us.id_persona"+
                    " inner join t_inventario inv on us.id_persona = inv.id_usuario "+
                    " where us.id_usuario = '"+id_usuario+"' ", UserRequest.class).getSingleResult();
        }catch (Exception e){
            logger.info("Exception: "+e);
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, "user");
        }
        logger.info("#--------------FIN DE SERVICIO--------------#");
        return jsonResponse;
    }

    @Override
    @Transactional(/*readOnly = true, */noRollbackFor = EmptyResultDataAccessException.class)
    public JsonObject registerUser(UserRequest userRequest) {
        logger.info("#--------------INICIO DE SERVICIO registerUser--------------#");
        JsonObject jsonResponse = null;

        try {
            logger.info("--- try: ");

            logger.info("#-----> obteniendo id_persona");
            Query query = em.createNativeQuery("select COALESCE(max(id_persona)+1, 1) as id_persona from t_persona; ");
            List<BigInteger> resultList = query.getResultList();
            int id_persona = Integer.parseInt(resultList.get(0).toString());
            logger.info("#--id_persona: "+id_persona+"--------------#");

            Persona persona = new Persona();
            persona.setId_persona(id_persona);
            persona.setNombres(userRequest.getNombres());
            persona.setApellidos(userRequest.getApellidos());
            persona.setDireccion(userRequest.getDireccion());
            persona.setDni(userRequest.getDni());
            persona.setImagen(userRequest.getImagen());
            persona.setCorreo(userRequest.getCorreo());
            persona.setTelefono_contacto(userRequest.getTelefono_contacto());
            repository.save(persona);

            Query query_user = em.createNativeQuery("select COALESCE(max(id_usuario)+1, 1) as id_usuario from t_usuario; ");
            List<BigInteger> resultUser = query_user.getResultList();
            int id_usuario = Integer.parseInt(resultUser.get(0).toString());
            logger.info("#--id_usuario: "+id_usuario+"--------------#");

            User user = new User();
            user.setId_usuario(id_usuario);
            user.setUsuario(userRequest.getUsuario());
            user.setClave(userRequest.getClave());
            user.setEstado("A");
            user.setId_rol(userRequest.getId_rol());
            user.setId_persona(id_persona);
            user.setPedidos_cancelados(0);
            user.setCodigo_app(UUIDHelper.generateString());
            user.setTipo_registro(Constants.REGISTER_APP);
            repositoryUser.save(user);

            Query query_inventory = em.createNativeQuery("select COALESCE(max(id_inventario)+1, 1) as id_inventario from t_inventario; ");
            List<BigInteger> resultInventory = query_inventory.getResultList();
            int id_inventory = Integer.parseInt(resultInventory.get(0).toString());
            logger.info("#--id_inventory: "+id_inventory+"--------------#");

            Inventory inventory = new Inventory();
            inventory.setId_inventario(id_inventory);
            inventory.setId_usuario(id_usuario);
            inventory.setTotal_fichas(Constants.START_COINS);
            inventory.setEstado("A");
            repositoryInventory.save(inventory);

            userRequest.setId_persona(id_persona);
            userRequest.setId_usuario(id_usuario);

        }catch (Exception e){
            logger.info("Exception: "+e);
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(userRequest, "user");
        }
        logger.info("#--------------FIN DE SERVICIO registerUser--------------#");
        return jsonResponse;
    }

    @Override
    public @ResponseBody JsonObject getUsers() {
        logger.info("#--------------INICIO DE SERVICIO login--------------#");
        JsonObject jsonResponse = null;
        List<UserRequest> data = null;
        try {
            data = (List<UserRequest>) em.createNativeQuery(" select us.id_usuario, us.id_rol, pe.id_persona, pe.nombres, pe.apellidos, pe.direccion, pe.telefono_contacto,  "+
                    " pe.dni, pe.correo, us.usuario, us.clave, us.estado, pe.imagen, us.codigo_app, us.pedidos_cancelados, us.tipo_registro, inv.total_fichas "+
                    " from t_persona pe "+
                    " inner join t_usuario us on pe.id_persona = us.id_persona"+
                    " inner join t_inventario inv on us.id_persona = inv.id_usuario "+
                    "  ", UserRequest.class).getResultList();
        }catch (Exception e){
            logger.info("Exception: "+e);
            data = null;
        }finally {
            ResponseHelper helper = new ResponseHelper();
            jsonResponse = helper.buildResponseObject(data, "usuario");
        }
        logger.info("#--------------FIN DE SERVICIO--------------#");
        return jsonResponse;
    }

}

/*

Query query_person = em.createNativeQuery("INSERT INTO t_persona (id_persona, nombres, apellidos, direccion, dni, imagen, correo, telefono_contacto) VALUES ( (SELECT coalesce(MAX(pe.id_persona)+1, 1 ) FROM t_persona pe), ?, ?, ?, ?, ?, ?, ?) ");
            query_person.setParameter(1, userRequest.getNombres());
            query_person.setParameter(2, userRequest.getApellidos());
            query_person.setParameter(3, userRequest.getDireccion());
            query_person.setParameter(4, userRequest.getDni());
            query_person.setParameter(5, userRequest.getImagen());
            query_person.setParameter(6, userRequest.getCorreo());
            query_person.setParameter(7, userRequest.getTelefono_contacto());
            query_person.executeUpdate();
            em.getEntityManagerFactory();
            em.persist(userRequest);
            em.refresh(userRequest);

BigInteger id = (BigInteger) query_person.getSingleResult();
            long id_persona = id.longValue();
            logger.info("--- id_persona: "+id_persona);

            Query query_user = em.createNativeQuery("INSERT INTO t_usuario (id_usuario, usuario, clave, estado, id_rol, id_persona, pedidos_cancelados, codigo_app, tipo_registro)"+
                    "VALUES ( (SELECT coalesce(MAX(us.id_persona)+1, 1 ) FROM t_usuario us),"+
                    " '"+user.getUsuario()+"', '"+user.getClave()+"', 'A', "+user.getId_rol()+", "+id_persona+", 0, '"+UUIDHelper.generateString() +"' ");
            List<BigInteger> resultUser = query_user.getResultList();
            id_usuario = Integer.parseInt(resultUser.get(0).toString());
            logger.info("--- id_usuario: "+id_usuario);

            Query query_inventory = em.createNativeQuery("INSERT INTO t_inventario (id_inventario, total_fichas, estado, id_usuario)"+
                    "VALUES ( (SELECT coalesce(MAX(inv.id_inventario)+1, 1 ) FROM t_inventario inv),"+
                    " "+Constants.START_COINS+" , 'A', "+id_usuario+" ");
            List<BigInteger> resultInventory = query_inventory.getResultList();
            int id_inventario = Integer.parseInt(resultInventory.get(0).toString());
            logger.info("--- id_inventario: "+id_inventario);
 */
