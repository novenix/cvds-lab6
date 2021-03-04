package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;

import java.io.IOException;
import java.io.Writer;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;
import java.util.ArrayList;


@WebServlet(
    urlPatterns = "/servletExtension"
)
public class ServletExtension extends HttpServlet{
    static final long serialVersionUID = 35L;
    
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String res = "";
       Writer responseWriter = resp.getWriter();
       
         try {
            Optional<String> ResponseId = Optional.ofNullable(req.getParameter("id"));
            Integer id = (Integer.parseInt(ResponseId.get()));
            Todo info = Service.getTodo(id);           
            ArrayList<Todo> infoToDo = new ArrayList<>();
            infoToDo.add(info);
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(Service.todosToHTMLTable(infoToDo));

            responseWriter.flush();              
        }
        
        catch (NumberFormatException e){
            
             resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
             res= "Requerimiento Inválido: debe ingresar un numero";
        }
        
        
        catch (MalformedURLException e){
           res="Error interno en el Servidor ";
           resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        

        catch (Exception e){
            res="no existe un item con el identificador dado";
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
        finally{
            responseWriter.write(res);
        }  
    }
     @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String res = "";
       Writer responseWriter = resp.getWriter();
       
         try {
            Optional<String> ResponseId = Optional.ofNullable(req.getParameter("id"));
            Integer id = (Integer.parseInt(ResponseId.get()));
            Todo info = Service.getTodo(id);           
            ArrayList<Todo> infoToDo = new ArrayList<>();
            infoToDo.add(info);
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(Service.todosToHTMLTable(infoToDo));
            responseWriter.flush();              
        }
        
        catch (NumberFormatException e){
            
             resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
             res= "Requerimiento Inválido: debe ingresar un numero";
        }
        
        
        catch (MalformedURLException e){
           res="Error interno en el Servidor ";
           resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        

        catch (Exception e){
            res="no existe un item con el identificador dado";
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
        finally{
            responseWriter.write(res);
        }  
    }
}
