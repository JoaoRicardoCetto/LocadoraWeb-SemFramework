/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author LEDS
 */
@Entity
public class Ator implements Serializable {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private int id;
    
    @Column(nullable = false)
    private String nome;

    public Ator() {
    }

    public Ator(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
