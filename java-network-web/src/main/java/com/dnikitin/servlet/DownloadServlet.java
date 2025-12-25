package com.dnikitin.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    //===================DOWNLOADING FILE FROM SERVER======================
    //to download file -> header Content-Disposition, attachment, filename
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get parameter with the type of file
        String fileId = req.getParameter("fileId");

        //setting mime type
        String mimeType = this.getServletContext().getMimeType(fileId);
        if(mimeType == null) {
            mimeType = "application/octet-stream";
        }


        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileId + "\"");
        resp.setContentType(mimeType);
        resp.setCharacterEncoding("UTF-8");

        try (ServletOutputStream outputStream = resp.getOutputStream();
             //to get file from resourses by name
             InputStream resourceAsStream = DownloadServlet.class.getClassLoader().getResourceAsStream(fileId)) {

            if (resourceAsStream != null) {
                resourceAsStream.transferTo(outputStream);
            }
        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
