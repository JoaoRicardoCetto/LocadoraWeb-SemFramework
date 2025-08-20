/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.application;

import java.util.ArrayList;
import java.util.List;
import util.PersistenceUtil;
import model.domain.Ator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LEDS
 */
public class AtorApplication {
    public static int inserirAtor(String nome) {
		
        if ((nome == null) || nome.trim().isEmpty())
                return 1;

        Ator a = new Ator();
        a.setNome(nome);

        Session s = PersistenceUtil.getSession();
        Transaction t = null;

        try {
            t = s.beginTransaction();
            s.save(a);
            t.commit();
            return 0;
            
        }catch(Exception e) {

            if(t != null){
               try { t.rollback(); } 
               catch (Exception ex) { System.out.println(ex.getMessage() + e.getMessage());}
            }

            return 2;

        } finally {
            if (s != null && s.isOpen()) {
                try { s.close(); } catch (HibernateException ex) { System.out.println(ex.getMessage()); }
            }
        }
		
    }
    
    public static Ator buscarPorId(int id){
        Session s = null;
        Transaction t = null;
        
        try{
            s = PersistenceUtil.getSession();
            t = s.beginTransaction();
            
            return s.get(Ator.class, id);
        } catch (Exception ex){ 
            System.out.println(ex.getMessage());
            return null;
        } finally {
            if (s != null && s.isOpen()) {
                try { s.close(); } 
                catch (HibernateException ex) { System.out.println(ex.getMessage());}
            }
        }
        
    }
    
    public static List<Ator> listarAtores() {
        Session s = null;
        Transaction t = null;
        List<Ator> lista = new ArrayList<>();

        try {
            s = PersistenceUtil.getSession();
            t = s.beginTransaction();

            lista = s.createQuery("from Ator", Ator.class).list();

            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (s != null) s.close();
        }
    
        return lista;
    }
    
    public static int atualizarAtor(Integer id, String nome) {
        if (id == null || id <= 0) return 1;
        if (nome == null || nome.trim().isEmpty()) return 1;

        Session s = null;
        Transaction t = null;
        try {
            s = PersistenceUtil.getSession();
            t = s.beginTransaction();

            Ator a = s.get(Ator.class, id);
            if (a == null) {
                if (t != null) { 
                    try { t.rollback(); } 
                    catch (Exception ex) {System.out.println(ex.getMessage());} 
                }
                return 3;
            }

            a.setNome(nome.trim());
            s.update(a);

            t.commit();
            return 0;
        } catch (Exception e) {
            if (t != null) {
                try { t.rollback(); } 
                catch (Exception ex) { System.out.println(ex.getMessage()); }
            }
            e.printStackTrace();
            return 2;
        } finally {
            if (s != null && s.isOpen()) {
                try { s.close(); } 
                catch (HibernateException ex) { System.out.println(ex.getMessage()); }
            }
        }
    }
    
    public static int excluirAtor(Integer id) {
		
        if(id == null || id < 0)
            return 1;
        
        Session s = PersistenceUtil.getSession();
        Transaction t = null;
        
        try {
            t = s.beginTransaction();
            Ator a = s.get(Ator.class, id);
            if(a == null){
                // não encontrou
                if (t != null) {
                    try {t.rollback();}
                    catch(Exception ex) {System.out.println(ex.getMessage());} 
                }
                return 3;
            }
            s.delete(a);
            t.commit();
            return 0;
            
        } catch(Exception e) {
            if(t != null){
               try { t.rollback(); } 
               catch (Exception ex) { System.out.println(ex.getMessage() + e.getMessage());}
            }
            
            return 2;

        } finally {
            if (s != null && s.isOpen()) {
                try { s.close(); } 
                catch (HibernateException ex) { System.out.println(ex.getMessage()); }
            }
        }
		
    }
}
