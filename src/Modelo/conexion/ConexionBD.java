
package Modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConexionBD {
    private static final String URL="jdbc:sqlserver://localhost:1433;"
            + "databaseName=BDprestamos;"+"integratedSecurity=true;"+
            "encrypt=true;trustServerCertificate=true";
    
    private static ConexionBD instancia=null;
    private static Connection conex=null;
    
      private ConexionBD(){} //Constructor privado para evitar crear instancias
      
      public  Connection conectar(){
          try{
              //usando driver y cadena de conexion para conectar BD
              conex=DriverManager.getConnection(URL);
              System.out.println("Conexion Establecida");
              return conex;
              
          }catch(SQLException e){
              System.out.println("Error de conexion:"+ e);
                 
          }
              return conex;
      }
    public void cerrarConexion() throws SQLException {
        try{
            conex.close();
            System.out.println("Conexion cerrada");
        }catch(SQLException e){
             System.out.println("Error al cerrar conexion:"+ e);
             conex.close();
        }finally{
            conex.close();
        }
    }
          //creaer una unica instancia de conexion
          public static ConexionBD getInstance(){
              if(instancia==null){
                  instancia=new ConexionBD();
              }
              return instancia;
          }
}

   

