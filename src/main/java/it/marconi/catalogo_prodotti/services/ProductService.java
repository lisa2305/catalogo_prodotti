package it.marconi.catalogo_prodotti.services;

import java.util.ArrayList;
import java.util.Optional;


import org.springframework.stereotype.Service;

import it.marconi.catalogo_prodotti.domains.Object;

@Service
public class ProductService {

    private ArrayList<Object> objects = new ArrayList<>();

    public ArrayList<Object> getUsers() {
        return objects;
    }

    public void addProduct(Object newObjects) {
        objects.add(newObjects);
    }

    public Optional<Object> getUserByCode(String code) {

        for(Object u : objects) {
            if(u.getCodice().equals(code)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    //da verificare
    public void deleteById(Object codice) {
       
    }
}
