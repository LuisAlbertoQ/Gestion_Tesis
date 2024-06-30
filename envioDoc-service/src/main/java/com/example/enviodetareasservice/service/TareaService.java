package com.example.enviodetareasservice.service;

import com.example.enviodetareasservice.modal.TareaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "servicio-tareas", path = "/api/tareas")
public interface TareaService {

    @GetMapping("/{id}")
    public TareaDto getTareaById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/{id}/complete")
    public TareaDto completeTareas(
            @PathVariable Long id) throws Exception;

}
