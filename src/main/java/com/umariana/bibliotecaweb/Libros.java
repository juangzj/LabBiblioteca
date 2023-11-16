/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.bibliotecaweb;

import java.io.Serializable;

/**
 * Clase libros implementa Serializable
 * @author JuanJoseGoyes/ JhonBolanos / GermanAndrade / JosepDavid
 */
public class Libros implements Serializable {
    
   // Variables de instancia
    String titulo;   // Título del libro
    String autor;    // Autor del libro
    String anio;     // Año de publicación del libro
    String foto;     // URL o ruta de la imagen del libro
    String prestado; // Estado de préstamo del libro
    String  dueño;  // Dueño del libro
     
    /**
     * Constructor 
     * @param titulo
     * @param autor
     * @param anio
     * @param foto
     * @param prestado
     * @param dueño 
     */
    public Libros(String titulo, String autor, String anio, String foto, String prestado, String dueño) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.foto = foto;
        this.prestado = prestado;
        this.dueño = dueño;
    }
    // Constructor sin parámetros
    public Libros() {
    }
    /**
     * Getter and Setter variables
     * @return 
     */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPrestado() {
        return prestado;
    }

    public void setPrestado(String prestado) {
        this.prestado = prestado;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }
    
     
}
