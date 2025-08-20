/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.application.AtorApplication;
import model.domain.Ator;

/**
 *
 * @author LEDS
 */
@WebServlet("/ator")
public class AtorController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // GET: listar, mostrar form em modo edição (action=edit), excluir via action=delete (faz PRG)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            // Excluir
            excluir(request);

            //redireciona para listar
            response.sendRedirect(request.getContextPath() + "/ator");
            return;
        } else if (action != null && action.equals("edit")) {
            editar(request);

            request.getRequestDispatcher("/ator.jsp").forward(request, response);
            return;
        }

        // Sem action: listar
        List<Ator> lista = AtorApplication.listarAtores();
        request.setAttribute("listaAtores", lista);

        Object msg = request.getSession().getAttribute("msg");
        if (msg != null) {
            request.setAttribute("msg", msg);
            request.getSession().removeAttribute("msg");
        }

        request.getRequestDispatcher("/ator.jsp").forward(request, response);
    }

    // POST: inserir ou atualizar (se id presente)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String nome = request.getParameter("txt_nome");

        Integer id;
        try { id = (idStr != null && !idStr.trim().isEmpty()) ? Integer.valueOf(idStr) : null; }
        catch (NumberFormatException e) { id = null; }

        if (id == null) inserir(request, nome);
        else atualizar(request, id, nome);

        response.sendRedirect(request.getContextPath() + "/ator");
    }
    
    
    private void inserir(HttpServletRequest request, String nome){
        int resultado = AtorApplication.inserirAtor(nome);
            switch (resultado) {
                case 0 -> request.getSession().setAttribute("msg", "Usuário incluído com sucesso!");
                case 1 -> request.getSession().setAttribute("msg", "Nome inválido.");
                default -> request.getSession().setAttribute("msg", "Erro ao incluir usuário.");
            }
    }
    
    
    private void atualizar(HttpServletRequest request, int id, String nome){
        int resultado = AtorApplication.atualizarAtor(id, nome);
            switch (resultado) {
                case 0 -> request.getSession().setAttribute("msg", "Usuário atualizado com sucesso!");
                case 1 -> request.getSession().setAttribute("msg", "Dados inválidos para atualização.");
                case 3 -> request.getSession().setAttribute("msg", "Ator não encontrado para atualização.");
                default -> request.getSession().setAttribute("msg", "Erro ao atualizar usuário.");
            }
    }
    
    private void excluir(HttpServletRequest request){
        String idStr = request.getParameter("id");
            Integer id;
            try { id = Integer.valueOf(idStr); } catch (NumberFormatException e) { id = null; }

            int res = AtorApplication.excluirAtor(id);
            switch (res) {
                case 0 -> request.getSession().setAttribute("msg", "Ator excluído com sucesso!");
                case 1 -> request.getSession().setAttribute("msg", "ID inválido para exclusão.");
                case 3 -> request.getSession().setAttribute("msg", "Ator não encontrado.");
                default -> request.getSession().setAttribute("msg", "Erro ao excluir ator.");
            }
    }
    
    private void editar(HttpServletRequest request){
        String idStr = request.getParameter("id");
            Integer id;
            try { id = Integer.valueOf(idStr); } catch (NumberFormatException e) { id = null; }

            Ator ator = null;
            if (id != null) {
                ator = AtorApplication.buscarPorId(id);
            }
            request.setAttribute("ator", ator); // pode ser null se não encontrado (JSP deve tratar)
            // também carrega lista para mostrar
            List<Ator> lista = AtorApplication.listarAtores();
            request.setAttribute("listaAtores", lista);

            Object msg = request.getSession().getAttribute("msg");
            if (msg != null) {
                request.setAttribute("msg", msg);
                request.getSession().removeAttribute("msg");
            }
    }
}