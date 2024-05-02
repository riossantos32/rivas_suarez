
package ModeloD;

import Modelo.conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DAOAutor {
    //crear una instancia de conexion
    //Hace llamado al metodo getInstance
    ConexionBD conectar=ConexionBD.getInstance();
   
    //Método para seleccionar todos los registros de la tabla
public List ObtenerDatos() throws SQLException {
//Nombre del procedimiento almacenado
String proced = "listarAutores()";
//Llama a método Listar de DataBase java, se le pasa el nombre del proc.
List<Map> registros = new DataBase () .Listar ( proced) ;
List<Autor> autores = new ArrayList (); //Arreglo de Autores
//Ciclo que recorre cada registro y los agrega al arreglo autores
for (Map registro : registros) {
  Autor aut = new Autor ((int) registro.get ("id_autor"),
           (String) registro.get("nombres"),
           (String) registro.get("apellidos") ,
           (String) registro.get("email"),
           (String) registro.get( "cedula"),
           (Date) registro.get( "fechaNac"));
    autores.add ( aut) ;
}
return autores; //Retorna todos los autores ubicados en la tabla de BD
  }  

public int Insertar(Autor aut) throws SQLException{
    try{ //Para manejar errores al realizar la conexion y transaccion BD
        
        //llama a proceo almacenado de SQLserver
        CallableStatement st=conectar.conectar().
               prepareCall("{CALL insertarAutor(?,?,?,?,?)}");
        st.setString(1, aut.getNombre());
        st.setString(2, aut.getApellido());
        st.setString(3, aut.getEmail());
        st.setString(4, aut.getCedula());
        st.setDate(5, aut.getFechaNac());
        st.executeUpdate();
       
    }catch(SQLException e){
        System.out.println(e+"Error");
        conectar.cerrarConexion();
        return -1;
    }

conectar.cerrarConexion();
  return 0;   
}
  }


     

