package manejosesion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManejoSesion extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String attributeNew = request.getParameter("parametro");
        String value = request.getParameter("valor");
        String action = request.getParameter("accion");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManejoSesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Contenido de la sesion</h1>");
            
            HttpSession session = request.getSession();
            
            if(action.equals("aniadir")){
                session.setAttribute(attributeNew, value);
                
                out.println("<ul>");
                Enumeration<String> namesAttributes = session.getAttributeNames();
                
                String name;
                String parameter;
                while(namesAttributes.hasMoreElements()){
                   name = namesAttributes.nextElement();
                   parameter = (String)session.getAttribute(name);
                   out.println("<li>"+name+": "+parameter+"</li><br/>");
                }
                out.println("</ul>");
                
                out.println("<a href=/SessionServelt/>Volver</a>");
                
            } else{
                session.invalidate();
                out.println("Sesion Invalidada");
            }
            out.println("</body>");
            out.println("</html>");
        } finally{
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
