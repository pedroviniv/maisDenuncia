/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.control;

import br.edu.ifpb.bdnc.maisdenuncia.entities.Endereco;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Usuario;
import br.edu.ifpb.bdnc.maisdenuncia.exceptions.EmailAlreadyExistsException;
import br.edu.ifpb.bdnc.maisdenuncia.model.UsuarioBo;
import br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class RegisterUserControl extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        //getting parameters
        String name = request.getParameter("name");
        LocalDate birthdate = DateUtils.fromBrazilPattern(request.getParameter("birthdate"));
        char gender = request.getParameter("sex").charAt(0);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        String state = request.getParameter("state");
        String city = request.getParameter("city");
        String neighborhood = request.getParameter("neighborhood");
        String street = request.getParameter("street");
        int number = Integer.parseInt(request.getParameter("number"));
        
        
        //Endereço
        Endereco endereco = new Endereco(country,state,city,neighborhood,street,number);
        
        Usuario usuario = new Usuario(name,birthdate,gender,email,password);
        usuario.setEndereco(endereco);
        
        UsuarioBo bo = new UsuarioBo();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp");
        
        try {
            
            bo.cadastrarUsuario(usuario);
            request.setAttribute("success", true);
            
        } catch(EmailAlreadyExistsException ex) {
            
            request.setAttribute("success", false);
            request.setAttribute("errorMsg", ex.getMessage());
            
        } finally {
            dispatcher.forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
