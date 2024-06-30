package com.example.enviodetareasservice.controller;

import com.example.enviodetareasservice.modal.Envio;
import com.example.enviodetareasservice.modal.UserDto;
import com.example.enviodetareasservice.service.EnvioService;
import com.example.enviodetareasservice.service.TareaService;
import com.example.enviodetareasservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private UserService userService;

    @Autowired
    private TareaService tareaService;

    @PostMapping()
    public ResponseEntity<Envio>enviarTarea(
            @RequestParam Long tareas_id,
            @RequestParam String github_link,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Envio envio=envioService.enviarTarea(tareas_id,github_link, user.getId(), jwt);
        return new ResponseEntity<>(envio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio>getEnvioById(
            @PathVariable Long id,
             @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Envio envio=envioService.getEnvioTareaById(id);
        return new ResponseEntity<>(envio, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<Envio>>getAllEnvios(
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        List<Envio> envios=envioService.getAllEnvioTarea();
        return new ResponseEntity<>(envios, HttpStatus.CREATED);
    }


    @GetMapping("/tarea/{tareasId}")
    public ResponseEntity<List<Envio>>getAllEnvios(
            @PathVariable Long tareasId,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        List<Envio> envios=envioService.getEnvioTareaByTareaId(tareasId);
        return new ResponseEntity<>(envios, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Envio>aceptarORechazarEnvio(
            @PathVariable Long id,
            @RequestParam("estado") String estado,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Envio envio=envioService.aceptarRechazarEnvio(id, estado);
        return new ResponseEntity<>(envio, HttpStatus.CREATED);
    }
}
