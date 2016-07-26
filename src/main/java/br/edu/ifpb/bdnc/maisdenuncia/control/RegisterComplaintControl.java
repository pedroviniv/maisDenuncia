/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.control;

import br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Usuario;
import br.edu.ifpb.bdnc.maisdenuncia.enums.TipoDenuncia;
import br.edu.ifpb.bdnc.maisdenuncia.enums.TipoDenunciante;
import br.edu.ifpb.bdnc.maisdenuncia.model.DenunciaBo;
import br.edu.ifpb.bdnc.maisdenuncia.utils.DateUtils;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kieckegard
 */
public class RegisterComplaintControl extends HttpServlet
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
        
        String descricao = request.getParameter("complaintDescription");
        int tipoDenuncia = Integer.valueOf(request.getParameter("complaintType"));
        int tipoDenunciante = Integer.valueOf(request.getParameter("userType"));
        
        String checkedEhAnonimo = request.getParameter("ehAnonimo");
        
        System.out.println("!!!!!"+checkedEhAnonimo+"!!!!!");
        
        boolean ehAnonimo = true;
        
        if(checkedEhAnonimo == null)
            ehAnonimo = false;
        
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        
        String wktPoint = "POINT("+lat+" "+lng+")";
        
        WKTReader reader = new WKTReader();
        
        RequestDispatcher rd = request.getRequestDispatcher("registerComplaint.jsp");
            
        try {
            
            Usuario loggedUser = (Usuario) request.getSession().getAttribute("loggedUser");
            LocalDate data = DateUtils.fromBrazilPattern(request.getParameter("complaintDate"));
            Point p = (Point) reader.read(wktPoint);     
            
            Denuncia d = new Denuncia(descricao,TipoDenuncia.get(tipoDenuncia),loggedUser,TipoDenunciante.get(tipoDenunciante),p,data,ehAnonimo);
            
            DenunciaBo bo = new DenunciaBo();
            
            bo.registraDenuncia(d);       
            
            request.setAttribute("success",true);
        }
        catch (ParseException | DateTimeParseException ex) {
            request.setAttribute("success",false);
        }
        finally {
            rd.forward(request, response);
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
