package com.itaohome.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

/**
 * 通用上传servlet
 */
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //打印对象
        PrintWriter out = response.getWriter();
        //上传图片服务器访问路径
        //String servicePath = "http://192.168.5.105:8080/itaohome/resources/images/upload/" + (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        String servicePath = "http://itaohome.duapp.com/resources/images/upload/" + (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        //判断提交过来的表单是否为文件上传
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            //构造一个文件上传处理对象
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            Iterator items;
            try {
                //解析表单中提交的所有文件内容
                items = upload.parseRequest(request).iterator();
                while (items.hasNext()) {
                    FileItem item = (FileItem) items.next();
                    if (!item.isFormField()) {
                        //取出上传文件的文件名称
                        String name = item.getName();
                        //取得上传文件以后的存储路径
                        String fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());
                        //创建上传目录路径
                        String uploadPath = request.getSession().getServletContext().getRealPath("/") + "resources\\images\\upload\\" + (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(new Date());//上传路径
                        File uploadPathFile = new File(uploadPath);
                        if (!uploadPathFile.exists()) uploadPathFile.mkdirs();
                        //构建图片上传路径
                        String filePath = uploadPath + "/" + fileName;

                        //上传文件
                        File uploaderFile = new File(filePath);
                        item.write(uploaderFile);
                        //打印上传成功信息
                        response.setContentType("text/html");
                        //response.setCharacterEncoding("GB2312");

                        //返回图片访问路径
                        String fileServicePath = servicePath + "/" + fileName;
                        System.out.println("文件路径为:" + fileServicePath);
                        out.print(fileServicePath);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.print(e.getMessage());
            }


        }
    }
}
