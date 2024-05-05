package it.marconi.catalogo_prodotti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.marconi.catalogo_prodotti.services.ProductService;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping
    public ModelAndView Home() {
        return new ModelAndView("homepage");
    }

    @GetMapping("/prodotto/nuovo")
    public ModelAndView newProduct(){
        return new ModelAndView("nuovoProdotto").addObject("newProduct",new Object());
    }

    @PostMapping("/prodotto/nuovo")
    public ModelAndView handlerNewProduct(@ModelAttribute Object o){
        productService.addProduct(o); 

        //String name = o.getName(); 
        //return new ModelAndView("redirect:/user/" + name);
    }

     @GetMapping("/prodotto/{codice}")
    public  ModelAndView userDetail(@PathVariable("codice") String codice) {

        Optional<Object> product = productService.getUserByCode(codice);

        if (product.isPresent())
            return new ModelAndView("dettagli-prodotto").addObject("product", product.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "prodotto non trovato!");
    }

      @GetMapping("/prodotti/elimina/{codice}") 
    public ModelAndView deleteContact(
        @PathVariable("codice") Object codice,
        RedirectAttributes attr
    ) {
        productService.deleteById(codice); 

        // aggiunto un boolean agli attributi del redirect
        attr.addFlashAttribute("deleted", true);
        return new ModelAndView("redirect:/contacts");
    }
    
}