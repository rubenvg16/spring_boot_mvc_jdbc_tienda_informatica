package org.iesvegademijas.tienda_informatica.controlador;

import java.util.List;

import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Producto;
import org.iesvegademijas.tienda_informatica.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//Para utilizar una base de url, pero se visualiza mejor con toda la ruta en los métodos
//@RequestMapping("/fabricantes"

// )
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public String listar(Model model) {

        List<Producto> listAllProds =  productoService.listAll();
        model.addAttribute("listaProductos", listAllProds);

        return "productos";

    }

    @GetMapping("/productos/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Producto producto = productoService.one(id);
        model.addAttribute("producto", producto);

        return "detalle-producto";

    }

    @GetMapping("/productos/crear")
    public String crear(Model model) {

        Producto producto = new Producto();
        model.addAttribute("producto", producto);

        return "crear-producto";

    }

    @PostMapping("/productos/crear")
    public RedirectView submitCrear(@ModelAttribute("producto") Producto producto) {

        productoService.newProducto(producto);

        return new RedirectView("/productos") ;

    }

    @GetMapping("/productos/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Producto producto = productoService.one(id);
        model.addAttribute("producto", producto);

        return "editar-producto";

    }


    @PostMapping("/producto/editar/{id}")
    public RedirectView submitEditar(@ModelAttribute("producto") Producto producto) {

        productoService.replaceProducto(producto);

        return new RedirectView("/productos");
    }

    @PostMapping("/productos/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        productoService.deleteProducto(id);

        return new RedirectView("/productos");
    }


}
