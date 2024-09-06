package com.craftycat.servletClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            
            String fileName = (String) request.getParameter("fileName");
            System.out.println(" fileName printed from download servlet at line 25: " + fileName);
            String filePath = getServletContext().getRealPath("/" + "PDF" + File.separator + fileName);
            System.out.println(filePath);
            response.setContentType("APPLICATION/OCTET-STREAM");
            String hkey = "Content-Disposition";
            String hvalue = String.format("attachment; filename=\"%s\"", fileName);
            response.setHeader(hkey, hvalue);
            try (FileInputStream ins = new FileInputStream(filePath)) {
                int i;
                
                while ((i = ins.read()) != -1) {
                    out.write(i);
                }
                ins.close();
            }
        }
    }
}
