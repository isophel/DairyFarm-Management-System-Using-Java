/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;

/**
 *
 * @author hp
 */
public class pdfs {
    Connection con;
    PreparedStatement statement;
    Statement st;
    String cs;
    boolean next;
    String password;
    String fname,lastname;
    String query;
    ResultSet rs;
    ResultSet rs2;
    ResultSet rs3;
    String user;
    String query1;
    String query2;
    String name;
     public pdfs(){
     
     con = null;
        st = null;
        statement = null;
        next = false;
        cs = "jdbc:mysql://localhost:3306/dairy_project";

        password = "";
        user = "root";
     }
    

public void printreport(java.awt.event.MouseEvent evt) {                                      
       try{
           final String RESULT = "Customers.pdf";
    Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        document.open();
         Paragraph para1 = new Paragraph("Quality Organic Dairy", FontFactory.getFont(FontFactory.TIMES_BOLD, 36, Font.BOLD, BaseColor.GREEN));
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para1);
                Image img = Image.getInstance("logo.png");
                img.scaleToFit(90,90);
                img.setAlignment(Element.ALIGN_CENTER);
                document.add(img);
                Paragraph para2 = new Paragraph("P.O Box 2314 Mbarara", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.ITALIC, BaseColor.BLACK));
                para2.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para2);
                Paragraph para3 = new Paragraph("Plot 43 Nkokonjeru Kakoba Division Mbarara Municipality ", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.ITALIC, BaseColor.BLACK));
                para3.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para3);
                Paragraph para4 = new Paragraph("Email: info@qualityorganics.com ", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.ITALIC, BaseColor.BLACK));
                para4.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para4);
                Paragraph para5 = new Paragraph("www.qualityorganics.com", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.ITALIC, BaseColor.BLACK));
                para5.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para5);
                Paragraph para7 = new Paragraph("_________________________________________________________________\n",FontFactory.getFont(FontFactory.TIMES_BOLD,16, Font.BOLD, BaseColor.GREEN));
                para7.setAlignment(Paragraph.ALIGN_CENTER);
                
                document.add(para7);
                
                
                 Chunk glue2 = new Chunk(new VerticalPositionMark());
                    Paragraph head = new Paragraph("Our Registered customers\n",FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.ITALIC, BaseColor.GREEN));
                    head.add(new Chunk(glue2));
                    head.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(head);
                    
                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Calendar calobj = Calendar.getInstance();
                String cal1 = df.format(calobj.getTime()).toString();
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
      
          Class.forName("com.mysql.jdbc.Driver");//registers drivers we configured
        con=DriverManager.getConnection(cs,user,password);
        st=con.createStatement();
         

       
            query=" select * from seller ";
           // query1= "select Fname,Lname from register where username = '"+name+"'";
             rs2=st.executeQuery(query);
             //rs = st.executeQuery(query1);
          PdfPCell fn = new PdfPCell(new Paragraph("SellerId", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.PLAIN, BaseColor.BLUE)));
                        fn.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        table.addCell(fn);
                        
                        PdfPCell ln = new PdfPCell(new Paragraph("Customer Name", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.PLAIN, BaseColor.BLUE)));
                        ln.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        table.addCell(ln);
                        
                        PdfPCell un = new PdfPCell(new Paragraph("Phone Number", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.PLAIN, BaseColor.BLUE)));
                        un.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        table.addCell(un);
                        
                        PdfPCell gen = new PdfPCell(new Paragraph("Address", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.PLAIN, BaseColor.BLUE)));
                        gen.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        table.addCell(gen);
                        
            

           
       while(rs2.next()){
         
        //table.addCell(rs2.getByte("profile"));
        //table.addCell(new Phrase((Phrase) profile.getIcon()));
        table.addCell(new Phrase(rs2.getString("sellerId")));
        table.addCell(new Phrase(rs2.getString("Name")));
        table.addCell(new Phrase(rs2.getString("PhoneNo")));
        table.addCell(new Phrase(rs2.getString("Address")));
        /*byte[] imageData = rs2.getBytes("profile");
         ImageIcon image = new ImageIcon(imageData);
                    Image img1 = Image.getInstance(imageData);
                    
                    PdfPCell cell2 = new PdfPCell(img1, false);
                       cell2.setPadding(10);
             table.addCell(cell2);
              */
     }
       document.add(table);
       
       Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Paragraph ptot1 = new Paragraph("Generated by: " + fname + " "+lastname+"",FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.ITALIC, BaseColor.BLACK));
                    ptot1.add(new Chunk(glue1));
                    ptot1.add("Issued on: " + cal1 + "");
                    document.add(ptot1);
         
       //document.addCreator("QOD-STAFF");
       // document.addAuthor("Natwijuka Isophel");
        //document.addTitle("Quality Organic Dairy");
       //document.add(table);
              if(Desktop.isDesktopSupported()){
                  try{
                    File myfile2 = new File(RESULT);
                    Desktop.getDesktop().open(myfile2);
                  }catch(IOException ex){
        ex.printStackTrace();
        }
                  
       }
              document.close();
       }      
              catch(Exception ex){
        ex.printStackTrace();
             }
       
       // TODO add your handling code here:
    }
}