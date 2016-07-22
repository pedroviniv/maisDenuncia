/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.entities;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class Usuario extends Pessoa implements Serializable
{
    private String email;
    private String senha;
    
    public Usuario(String nome, LocalDate dataNascimento, char sexo, String email, String senha)
    {
        super(nome, dataNascimento, sexo);
        this.email = email;
        this.senha = senha;
    }
    
    public Usuario(String nome, LocalDate dataNascimento, char sexo, Endereco endereco, String email, String senha)
    {
        super(nome, dataNascimento, sexo, endereco);
        this.email = email;
        this.senha = senha;
    }

    public String getEmail()
    {
        return email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    @Override
    public String toString()
    {
        return "Usuario{" + "email=" + email + ", senha=" + senha + '}';
    }
}
