package com.byron.resources.controller;

import com.byron.resources.dto.Mensaje;
import com.byron.resources.entity.Producto;
import com.byron.resources.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    @GetMapping("/list")
    public ResponseEntity<List<Producto>> list(){
        List<Producto>list=productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);

    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto>getById(@PathVariable("id") int id){
        if(!productoService.existeById(id)) {
            return new ResponseEntity(new Mensaje("no exists"), HttpStatus.NOT_FOUND);
        }else{
            Producto producto = productoService.getOne(id).get();
            return new ResponseEntity(producto, HttpStatus.OK);
        }
    }
}
