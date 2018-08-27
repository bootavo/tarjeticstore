package com.example.bucket.controller;

import com.example.bucket.model.CustomLogin;
import com.example.bucket.model.UserRequest;
import com.example.bucket.repository.UserRepository;
import com.example.bucket.service.AwsService;
import com.example.bucket.service.ProductService;
import com.example.bucket.service.UserService;
import com.example.bucket.utils.UUIDHelper;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/")
public class AwsController {

    private AwsService awsService;

    @Autowired
    AwsController(AwsService awsService) {
        this.awsService = awsService;
    }

    @Autowired
    private UserService service;

    private Logger logger = LogManager.getLogger(AwsController.class);

    @PostMapping(value = {"/user/aws2","/user/aws2/"})
    public JsonObject uploadFile(@ModelAttribute CustomLogin customLogin) {
        logger.info("---------------> Inicio Data User");
        logger.info(customLogin.getUsuario());
        logger.info(customLogin.getClave());
        logger.info(customLogin.getDireccion());
        logger.info(customLogin.getDni());
        logger.info(customLogin.getTelefono_contacto());
        logger.info(customLogin.getCorreo());
        logger.info(customLogin.getId_rol());
        logger.info(customLogin.getNombres());
        logger.info(customLogin.getApellidos());
        logger.info(customLogin.getTipo_registro());
        logger.info("---------------> Fin Data User");

        UserRequest userRequest = new UserRequest();
        if (customLogin.getImagen() != null){
            String image = awsService.uploadFile(customLogin.getImagen(), customLogin.getUsuario());
            logger.info("---------------> imagen: "+image);
            if( image != null){
                logger.info("---------------> success");
                userRequest.setImagen(image);
            }
        }

        userRequest.setUsuario(customLogin.getUsuario());
        userRequest.setClave(customLogin.getClave());
        userRequest.setDireccion(customLogin.getDireccion());
        userRequest.setDni(customLogin.getDni());
        userRequest.setTelefono_contacto(customLogin.getTelefono_contacto());
        userRequest.setCorreo(customLogin.getCorreo());
        userRequest.setId_rol(Integer.parseInt(customLogin.getId_rol()));
        userRequest.setNombres(customLogin.getNombres());
        userRequest.setApellidos(customLogin.getApellidos());
        userRequest.setTipo_registro(customLogin.getTipo_registro());
        String codigo_app = UUIDHelper.generateString();
        userRequest.setCodigo_app(codigo_app);
        logger.info(userRequest.toString());

        return service.registerUser(userRequest);
    }

    /*@RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String uploadFile(
            @RequestPart(value = "usuario") String usuario,
            @RequestPart(value = "clave") String clave,
            @RequestPart(value = "direccion") String direccion,
            @RequestPart(value = "dni") String dni,
            @RequestPart(value = "telefono_contacto") String telefono_contacto,
            @RequestPart(value = "imagen") MultipartFile imagen,
            @RequestPart(value = "correo") String correo,
            @RequestPart(value = "id_rol") int id_rol,
            @RequestPart(value = "nombres") String nombres,
            @RequestPart(value = "apellidos") String apellidos,
            @RequestPart(value = "tipo_registro") String tipo_registro) {

        logger.info("---------------> Inicio Data User");
        logger.info(usuario);
        logger.info(clave);
        logger.info(direccion);
        logger.info(telefono_contacto);
        logger.info(correo);
        logger.info(id_rol);
        logger.info(nombres);
        logger.info(apellidos);
        logger.info(tipo_registro);
        logger.info("---------------> Fin Data User");

        String image = awsService.uploadFile(imagen, usuario);
        UserRequest userRequest = null;

        if( image != null || !image.equals("")){
            userRequest = new UserRequest();
            userRequest.setUsuario(usuario);
            userRequest.setClave(clave);
            userRequest.setDireccion(direccion);
            userRequest.setDni(dni);
            userRequest.setTelefono_contacto(telefono_contacto);
            userRequest.setImagen(image);
            userRequest.setCorreo(correo);
            userRequest.setId_rol(id_rol);
            userRequest.setNombres(nombres);
            userRequest.setApellidos(apellidos);
            userRequest.setTipo_registro(tipo_registro);
            logger.info(userRequest.toString());
            //service.registerUser(userRequest);
        }else {
            System.out.println("fail");
        }

        return image;
    }*/

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.awsService.deleteFileFromS3Bucket(fileUrl);
    }

}
