package com.example.bucket.controller;

import com.example.bucket.model.UserRequest;
import com.example.bucket.service.AwsService;
import com.example.bucket.service.UserService;
import com.example.bucket.utils.UUIDHelper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api") //
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AwsService awsService;

    @PostMapping(path= {"/login", "/login/"})
    public @ResponseBody JsonObject login (@RequestBody UserRequest userRequest) {
        return service.login(userRequest);
    }

    @GetMapping(path= {"/user/{id_usuario}", "/user/{id_usuario}/"})
    public @ResponseBody JsonObject getUserById(@PathVariable(value="id_usuario") int id_usuario){
        return service.getUserById(id_usuario);
    }

    @GetMapping(path= {"/user", "/user/"})
    public @ResponseBody JsonObject getUsers(){
        return service.getUsers();
    }

    @PostMapping(path= {"/user", "/user/"})
    public @ResponseBody JsonObject registerUser(@RequestBody UserRequest userRequest){
        return service.registerUser(userRequest);
    }

    @PostMapping(value = {"/user/aws","/user/aws/"})
    public @ResponseBody JsonObject registerUserWithImage(@Nullable @RequestPart(value = "nombres") String nombres,
                                                          @Nullable @RequestPart(value = "apellidos") String apellidos,
                                                          @RequestPart(value = "usuario") String usuario,
                                                          @RequestPart(value = "clave") String clave,
                                                          @Nullable @RequestPart(value = "direccion") String direccion,
                                                          @Nullable @RequestPart(value = "dni") String dni,
                                                          @Nullable @RequestPart(value = "telefono_contacto") String telefono_contacto,
                                                          @Nullable @RequestPart(value = "correo") String correo,
                                                          @RequestPart(value = "id_rol") String id_rol,
                                                          @RequestPart(value = "tipo_registro") String tipo_registro,
                                                          @Nullable @RequestPart(value = "imagen") MultipartFile file){

        UserRequest userRequest = new UserRequest();
        if (file != null){
            String image = awsService.uploadFile(file, usuario);
            if( image != null){
                userRequest.setImagen(image);
            }
        }

        userRequest.setUsuario(usuario);
        userRequest.setClave(clave);
        userRequest.setDireccion(direccion);
        userRequest.setDni(dni);
        userRequest.setTelefono_contacto(telefono_contacto);
        userRequest.setCorreo(correo);
        userRequest.setId_rol(Integer.parseInt(id_rol));
        userRequest.setNombres(nombres);
        userRequest.setApellidos(apellidos);
        userRequest.setTipo_registro(tipo_registro);
        String codigo_app = UUIDHelper.generateString();
        userRequest.setCodigo_app(codigo_app);

        return service.registerUserWithImage(userRequest);
    }

}
