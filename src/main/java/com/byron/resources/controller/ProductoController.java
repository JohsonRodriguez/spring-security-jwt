package com.byron.resources.controller;

import com.byron.resources.dto.Mensaje;
import com.byron.resources.dto.ProductoDto;
import com.byron.resources.entity.Producto;
import com.byron.resources.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
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
    @GetMapping("/lista")
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

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto>getByNombre(@PathVariable("nombre") String nombre){
        if(!productoService.existeByNombre(nombre)) {
            return new ResponseEntity(new Mensaje("no exists"), HttpStatus.NOT_FOUND);
        }else{
            Producto producto = productoService.getByNombre(nombre).get();
            return new ResponseEntity(producto, HttpStatus.OK);
        }
    }
@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
        if(StringUtils.isBlank(productoDto.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(productoDto.getPrecio()<0){
            return new ResponseEntity(new Mensaje("El precio debe ser mayo a 0"), HttpStatus.BAD_REQUEST);
        }
        if (productoService.existeByNombre(productoDto.getNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("Producto creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductoDto productoDto){
        if(!productoService.existeById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(productoService.existeByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() == id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existeById(id)){
            return new ResponseEntity(new Mensaje("no exists"), HttpStatus.NOT_FOUND);
        }else{
            productoService.delete(id);
            return new ResponseEntity(new Mensaje("Producto eliminado"), HttpStatus.OK);
        }


    }

}
