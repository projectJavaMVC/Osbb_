package ua.kiev.prog.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.kiev.prog.entity.UserEntity;

import java.io.FileOutputStream;

/**
 * Created by mbro8_000 on 09.12.2015.
 */
public class PDFCreate {
    public void createPDF(UserEntity userEntity){
        try {
            Document document = new Document(PageSize.A4.rotate(), 12, 12, 12, 12);
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("E:\\Test.pdf"));
            final String FONT = "c:/windows/fonts/arial.ttf";
            BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, true);
            Font font = new Font(baseFont);
            document.open();
            PdfPTable t = new PdfPTable(2);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("Квитанция на оплату услуг по сервису: ", font));
            c1.setColspan(2);
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Платник: \r\n"+userEntity.getUserInfo().getFirstName()+" "
                    +userEntity.getUserInfo().getLastName()+""+userEntity.getBuildsEntity().getStreet()+""+
                    userEntity.getBuildsEntity().getBuildNum(), font));
            PdfPCell c3 = new PdfPCell(new Phrase("Начислено за период:\r\n", font));
            t.addCell(c2);
            PdfPTable inclTable = new PdfPTable(4);
            inclTable.setSpacingBefore(0);
            inclTable.setSpacingAfter(0);
            PdfPCell c11IN = new PdfPCell(new Phrase("Предыдущие: \r\n", font));
            PdfPCell c12IN = new PdfPCell(new Phrase("Текущие: \r\n", font));
            PdfPCell c13IN = new PdfPCell(new Phrase("Тариф: \r\n", font));
            PdfPCell c14IN = new PdfPCell(new Phrase("Итого: \r\n", font));
            PdfPCell c21IN = new PdfPCell(new Phrase("\r\n", font));
            PdfPCell c22IN = new PdfPCell(new Phrase(": \r\n", font));
            PdfPCell c23IN = new PdfPCell(new Phrase(": \r\n", font));
            PdfPCell c24IN = new PdfPCell(new Phrase(": \r\n", font));
            inclTable.addCell(c11IN);
            inclTable.addCell(c12IN);
            inclTable.addCell(c13IN);
            inclTable.addCell(c14IN);
            inclTable.addCell(c21IN);
            inclTable.addCell(c22IN);
            inclTable.addCell(c23IN);
            inclTable.addCell(c24IN);
            c3.addElement(inclTable);
            t.addCell(c3);
            document.add(t);
            document.close();
        } catch (Exception e ){
            e.printStackTrace();
        }
    }
}
