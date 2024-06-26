package it.marconi.catalogo_prodotti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.marconi.catalogo_prodotti.domains.Product;
import it.marconi.catalogo_prodotti.services.ProductService;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView Home() {
        return new ModelAndView("homepage");
    }

    @GetMapping("/prodotto/deleted")
    public ModelAndView deletedAll() {
        return new ModelAndView("homepage").addObject("deleteAll", productService.deleteAll());
    }

    @GetMapping("/prodotto/nuovo")
    public ModelAndView newProduct() {
        return new ModelAndView("nuovoProdotto").addObject("newProduct", new Product());
    }

    @PostMapping("/prodotto/nuovo")
    public ModelAndView handlerNewProduct(@ModelAttribute Product o) {
        productService.addProduct(o);

        String name = o.getNome();
        return new ModelAndView("redirect:/prodotto/" + name);
    }

    @GetMapping("/prodotto/{codice}")
    public ModelAndView userDetail(@PathVariable("codice") String codice) {
        Optional<Product> product = productService.getUserByCode(codice);
        return new ModelAndView("dettagli-prodotto").addObject("product", product.get());

    }

    @GetMapping("/prodotti/elimina/{codice}")
    public ModelAndView deleteContact(
            @PathVariable("codice") String codice,
            RedirectAttributes attr) {
        productService.deleteById(codice);

        // aggiunto un boolean agli attributi del redirect
        attr.addFlashAttribute("deleted", true);
        return new ModelAndView("redirect:/contacts");
    }

}
