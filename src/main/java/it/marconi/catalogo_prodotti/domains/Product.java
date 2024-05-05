package it.marconi.catalogo_prodotti.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String codice;
    private String nome;
    private String categoria;
    private String annoProduzione;
    private String quantità;
}
