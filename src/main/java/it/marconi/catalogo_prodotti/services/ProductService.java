package it.marconi.catalogo_prodotti.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.marconi.catalogo_prodotti.domains.Product;

@Service
public class ProductService {

    private ArrayList<Product> objects = new ArrayList<>();

    public ArrayList<Product> getUsers() {
        return objects;
    }

    public void addProduct(Product newObjects) {
        objects.add(newObjects);
    }

    public Optional<Product> getUserByCode(String code) {

        for (Product u : objects) {
            if (u.getCodice().equals(code)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    // metodo che permette l'eliminazione di tutto il catalogo
    public ArrayList<Product> deleteAll() {
        objects.clear();
        return objects;
    }

    public ArrayList<Product> deleteById(String codice) {
        for (int index = 0; index < objects.size(); index++) {
            if(objects.get(index).getCodice().equals(codice)){
                objects.remove(index);
            }
        }
        return objects;
    }
}
