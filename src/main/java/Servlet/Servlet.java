package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/Agregar")
public class Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Obtener los parámetros del formulario
        String nombre = req.getParameter("nombre");
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        double valorUnitario = Double.parseDouble(req.getParameter("valor"));

        // Calcular el total
        double total = cantidad * valorUnitario;

        // Obtener la sesión o crear una nueva si no existe
        HttpSession session = req.getSession();

        // Obtener la lista de productos de la sesión o crear una nueva si no existe
        List<String> carrito = null;

        if (session.getAttribute("carrito") == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        } else {
            carrito = (List<String>) session.getAttribute("carrito");
        }

        // Agregar el producto al carrito
        String producto = nombre + " - Cantidad: " + cantidad + " - Valor unitario: $" + valorUnitario + " - Total: $" + total;
        carrito.add(producto);

        // Redireccionar al servlet que muestra el carrito
        resp.sendRedirect("MostrarCarrito");
    }
}
