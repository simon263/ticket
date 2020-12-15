/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entity.Etat;
import Entity.Ticket;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jbuffeteau
 */
public class FonctionsMetier implements IMetier
{
    @Override
    public User GetUnUser(String login, String mdp)
            
    {
            User  user = null;
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idUser ,nomUser, prenomUser ,StatutUser from users where loginUser ='"+login+"'and pwdUser='"+mdp+"'");
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            rs.next();
            user =  new User(rs.getInt("idUser"),rs.getString("nomUser"),rs.getString("prenomUser"),rs.getString("StatutUser"));
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user ;
    }

    @Override
    public ArrayList<Ticket> GetAllTickets()
    {   
        
            
        
        return null;
    }

    @Override
    public ArrayList<Ticket> GetAllTicketsByIdUser(int idUser)
    {           
             ArrayList<Ticket>mesTick = new  ArrayList<>();
            
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idTicket ,nomTicket, dateTicket ,nomEtat from tickets,etats where numUser="+idUser+" and idEtat=numEtat");
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            while (rs.next()){
             
            Ticket tic   =  new Ticket(rs.getInt("idTicket"),rs.getString("nomTicket"),rs.getString("dateTicket"),rs.getString("nomEtat"));
            mesTick.add(tic);
            
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mesTick;
    }

    @Override
    public void InsererTicket(int idTicket, String nomTicket, String dateTicket, int idUser, int idEtat) 
    {
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("insert into tickets values("+idTicket+",'"+nomTicket+"','2020-12-15',"+idUser+","+idEtat+")");
            ps.executeUpdate();
            System.out.println(ps);
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void ModifierEtatTicket(int idTicket, int idEtat) 
    {
        try {
            PreparedStatement stm;
            Connection cnx = ConnexionBDD.getCnx();
            stm =cnx.prepareStatement("UPDATE bddticket.tickets SET  numEtat="+idEtat+" where idTicket= "+idTicket+"");
            
            
            
            System.out.println(stm);
            System.out.println("le UPDATE  a réussi.");
            stm.executeUpdate();
            stm.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public ArrayList<User> GetAllUsers()
    { 
        ArrayList<User> mesUser = new ArrayList<>();
        try {
            
            
            
            
            
            
            
            
            
            
            PreparedStatement stm;
            ResultSet rs;
            Connection Cnx = ConnexionBDD.getCnx();
            stm = Cnx.prepareStatement("select idUser,nomUser,prenomUser,statutUser from users");
            rs = stm.executeQuery();
         
            while(rs.next()){
                User u = new User(rs.getInt(1),rs.getString(2),rs.getString("prenomUser"),rs.getString("statutUser"));
                mesUser.add(u);
                
                
            }   } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mesUser;
    }

    @Override
    public int GetLastIdTicket()
    {
        
           int idtik=0;
        try {
            PreparedStatement stm;
            ResultSet rs;
            Connection Cnx = ConnexionBDD.getCnx();
            stm = Cnx.prepareStatement("select count(idTicket) from tickets");
            rs = stm.executeQuery();
            
            rs.next();
            idtik = rs.getInt(1)+1;
            rs.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
         return idtik;
    }

    @Override
    public int GetIdEtat(String nomEtat)
            
    {       
        int id =0;
        try {
            
            PreparedStatement stm;
            ResultSet rs;
            Connection Cnx = ConnexionBDD.getCnx();
            
            stm = Cnx.prepareStatement("select idEtat from etats  where nomEtat ='"+nomEtat+"'");
            System.out.println(stm);
            rs = stm.executeQuery();
            rs.next();
            id = rs.getInt(1);
            
            rs.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public ArrayList<Etat> GetAllEtats()
    {  
        ArrayList<Etat> et = new ArrayList<>();
        try {
            
            PreparedStatement stm;
            ResultSet rs;
            Connection Cnx = ConnexionBDD.getCnx();
            stm = Cnx.prepareStatement("select nomEtat from etats");
            rs = stm.executeQuery();
            while (rs.next()){
            Etat eta = new Etat(0,rs.getString(1));
            et.add(eta);
            }
            
            rs.close();
            
              
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return et;  
    }
}
