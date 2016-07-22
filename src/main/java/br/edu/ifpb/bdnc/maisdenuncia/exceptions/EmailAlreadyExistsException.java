/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.exceptions;

/**
 *
 * @author kieckegard
 */
public class EmailAlreadyExistsException extends RuntimeException
{
    public EmailAlreadyExistsException(String msg){
        super(msg);
    }
}
