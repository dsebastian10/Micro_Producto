package com.gestion.productos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.productos.modelo.Producto;
import com.gestion.productos.servicio.ProductoServicio;

@RestController
public class ProductoControlador {
	
	@Autowired
	private ProductoServicio servicio;
	
	@GetMapping("/productos")
	public List<Producto> listaMisProductos(){
		return servicio.listarProductos();
	}
	
	@PostMapping("/productos")
	public void guardarMisProductos(@RequestBody Producto producto){
		 servicio.guardarProducto(producto);
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> obtenerMiProductoId(@PathVariable Integer id){
		 try {
			 Producto pro =servicio.obtenerProductoPorId(id);
			 return new ResponseEntity<Producto>(pro,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto){
	
		 try {
				Producto productoNuevo = servicio.obtenerProductoPorId(id);
				
				productoNuevo.setNombre(producto.getNombre());
				productoNuevo.setPrecio(producto.getPrecio());
				 servicio.guardarProducto(productoNuevo);
				
				 return new ResponseEntity<Producto>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/productos/{id}")
	public void eliminarMiProductoPorId(@PathVariable Integer id) {
		 servicio.eliminarProducto(id);
	}
	

}
