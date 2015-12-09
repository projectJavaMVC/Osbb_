package ua.kiev.prog.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * Created by mbro8_000 on 09.12.2015.
 */
public class PDFCreate {
    public void createPDF(){
        try {
            Document document = new Document(PageSize., 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\ITextTest.pdf"));
            document.open();
            PdfPTable t = new PdfPTable(3);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("Header1"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Header3"));
            t.addCell(c3);
            t.addCell("1.sssssssssssssssssssssssssssssssssssssssssssssssadasdddddddddddddddddddd");
            t.addCell("1.2");
            t.addCell("1.3");
            t.addCell("sssssssssssssssssssssssssssssssssssssssssssssssadasdddddddddddddddddddd");

            document.add(t);
            document.close();
        } catch (Exception e ){
            e.printStackTrace();
        }
}
}
