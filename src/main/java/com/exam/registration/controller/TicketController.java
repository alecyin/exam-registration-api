package com.exam.registration.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.registration.model.*;
import com.exam.registration.service.*;
import com.exam.registration.util.MsgUtils;
import com.exam.registration.util.ResCode;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yhf
 * @classname TicketDownloadController
 **/
@RequestMapping("/tickets")
@Controller
public class TicketController {

    @Value("${ticket.create.path}")
    private String ticketPath;
    @Value("${ticket.font.path}")
    private String fontPath;
    @Value("${photo.host.ip}")
    private String photoIp;
    @Value("${photo.upload.suffix}")
    private String suffix;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ExamService examService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ExamSubjectService examSubjectService;
    @Autowired
    private ExamineeNoteController examineeNoteController;

    @RequestMapping(path = "/detail/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public String ticketDetail(@PathVariable("orderId") long orderId,
                               HttpServletRequest request) {
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        Order order = orderService.getOrderByPrimaryKey(orderId);
        if (order.getStudentId() != orderId) {
            return MsgUtils.fail("改准考证不属于你");
        }
        Exam exam = examService.getExamByPrimaryKey(order.getExamId());
        Site site = siteService.getSiteByPrimaryKey(exam.getSiteId());
        Major major = majorService.getMajorByPrimaryKey(exam.getMajorId());
        List<ExamSubject> examSubjectList = examSubjectService.listExamSubjectsByExamId(exam.getId());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        JSONObject jsonObject1 = new JSONObject();
        student.setPassword("");
        jsonObject1.put("student", student);
        jsonObject1.put("major", major.getName());
        jsonObject1.put("note", examineeNoteController.getExamineeNote());
        JSONArray jsonArray = new JSONArray();
        for (ExamSubject examSubject : examSubjectList) {
            JSONObject jsonObject11 = new JSONObject();
            Subject subject = subjectService.getSubjectByPrimaryKey(examSubject.getSubjectId());
            jsonObject11.put("subjectName", subject.getName());
            jsonObject11.put("address", examSubject.getAddress());
            jsonObject11.put("startTime", examSubject.getStartTime());
            jsonObject11.put("endTime", examSubject.getEndTime());
            jsonArray.add(jsonObject11);
        }
        jsonObject1.put("subjects", jsonArray);
        jsonObject.put("data", jsonObject1);
        return jsonObject.toJSONString();
    }

    /**
     * 生成pdf，以考生的身份证图片文件名称命名文件
     */
    @RequestMapping(path = "/download", method = RequestMethod.POST)
    @ResponseBody
    public String createTicket(@RequestBody Map<String, Object> map, HttpServletRequest request) throws Exception {
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        Map<String, Object> pdf = new HashMap<>();
        pdf.put("pdfPath", student.getIdCardPic() + ".pdf");
        if (new File(ticketPath + student.getIdCardPic() + ".pdf").exists()) {
            return MsgUtils.success(pdf);
        }
        Long orderId = Long.valueOf(String.valueOf(map.get("orderId")));
        Order order = orderService.getOrderByPrimaryKey(orderId);
        Exam exam = examService.getExamByPrimaryKey(order.getExamId());
        Site site = siteService.getSiteByPrimaryKey(exam.getSiteId());
        Major major = majorService.getMajorByPrimaryKey(exam.getMajorId());
        List<ExamSubject> examSubjectList = examSubjectService.listExamSubjectsByExamId(exam.getId());
        String fileName = student.getIdCardPic();
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(ticketPath + fileName + ".pdf"));
        document.open();
        //中文字体,解决中文不能显示问题
//        String fontPath = this.getClass().getClassLoader().getResource("font/simsun.ttf").getPath().substring(6);
        BaseFont bfChinese = BaseFont.createFont(fontPath + "simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font titleFont = new Font(bfChinese);
        titleFont.setSize(20);
        titleFont.setStyle(Font.BOLD);
        document.addSubject("准考证");
        document.addTitle(site.getName() + "-" + major.getName() + "-" + student.getName());
        document.addCreationDate();

        // 生成表格
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Paragraph paragraph = new Paragraph(year + "年某学院艺术准考证", titleFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(80); // 宽度100%填充
        table.setSpacingBefore(20f); // 前间距
        table.setSpacingAfter(10f); // 后间距
        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = {1f, 2f, 1f};
        table.setWidths(columnWidths);

        //行1
        PdfPCell cells1[] = new PdfPCell[3];
        PdfPRow row1 = new PdfPRow(cells1);

        // 单元格
        Font contentFont = new Font(bfChinese);
//        String fontPath = Thread.currentThread()
//                .getContextClassLoader().getResource("font/simsun.ttf")
//                .getPath().substring(1);

//        contentFont.setFamily();
        cells1[0] = new PdfPCell(new Paragraph("专业", contentFont));//单元格内容
//        cells1[0].setPaddingLeft(20);//左填充20
        cells1[0].setHorizontalAlignment(Element.ALIGN_RIGHT);//水平靠左
        cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

        cells1[1] = new PdfPCell(new Paragraph(major.getName(), contentFont));
        cells1[1].setHorizontalAlignment(Element.ALIGN_LEFT);

        Image image = Image.getInstance(photoIp + student.getIdCardPic() + suffix);
//        image.setAbsolutePosition(100f, 550f);
        image.scaleAbsolute(80, 100);
        cells1[2] = new PdfPCell();
        cells1[2].setRowspan(5);
        cells1[2].setPadding(10);
        cells1[2].setHorizontalAlignment(Element.ALIGN_CENTER);
        cells1[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
        cells1[2].addElement(image);
        cells1[2].addElement(new Paragraph(student.getAddress().split("\\|")[0] + "普通高招", contentFont));

        // 行2
        PdfPCell cells2[] = new PdfPCell[3];
        PdfPRow row2 = new PdfPRow(cells2);
        cells2[0] = new PdfPCell(new Paragraph("姓名", contentFont));;
        cells2[0].setHorizontalAlignment(Element.ALIGN_RIGHT);
        cells2[0].setVerticalAlignment(Element.ALIGN_MIDDLE);

        cells2[1] = new PdfPCell(new Paragraph(student.getName(), contentFont));
        cells2[1].setHorizontalAlignment(Element.ALIGN_LEFT);


        // 行3
        PdfPCell cells3[] = new PdfPCell[3];
        PdfPRow row3 = new PdfPRow(cells3);
        cells3[0] = new PdfPCell(new Paragraph("准考证号", contentFont));;
        cells3[0].setHorizontalAlignment(Element.ALIGN_RIGHT);
        cells3[0].setVerticalAlignment(Element.ALIGN_MIDDLE);

        cells3[1] = new PdfPCell(new Paragraph(order.getExamineeNumber(), contentFont));
        cells3[1].setHorizontalAlignment(Element.ALIGN_LEFT);


        // 行4
        PdfPCell cells4[] = new PdfPCell[3];
        PdfPRow row4 = new PdfPRow(cells4);
        cells4[0] = new PdfPCell(new Paragraph("身份证号", contentFont));;
        cells4[0].setHorizontalAlignment(Element.ALIGN_RIGHT);
        cells4[0].setVerticalAlignment(Element.ALIGN_MIDDLE);

        cells4[1] = new PdfPCell(new Paragraph(student.getIdCardNumber(), contentFont));
        cells4[1].setHorizontalAlignment(Element.ALIGN_LEFT);


        // 行5
        PdfPCell cells5[] = new PdfPCell[3];
        PdfPRow row5 = new PdfPRow(cells5);
        cells5[0] = new PdfPCell(new Paragraph("省统考号", contentFont));;
        cells5[0].setHorizontalAlignment(Element.ALIGN_RIGHT);
        cells5[0].setVerticalAlignment(Element.ALIGN_MIDDLE);

        cells5[1] = new PdfPCell(new Paragraph(student.getProvincialExamineeNumber(), contentFont));
        cells5[1].setHorizontalAlignment(Element.ALIGN_LEFT);
        cells5[0].setVerticalAlignment(Element.ALIGN_MIDDLE);


        //把第一行添加到集合
        listRow.add(row1);
        listRow.add(row2);
        listRow.add(row3);
        listRow.add(row4);
        listRow.add(row5);

        PdfPTable table2 = new PdfPTable(3);
        table2.setWidthPercentage(80); // 宽度100%填充
        table2.setSpacingBefore(10f); // 前间距
        table2.setSpacingAfter(10f); // 后间距
        List<PdfPRow> listRow2 = table2.getRows();
        //设置列宽
        float[] columnWidths2 = {1f, 2f, 2f};
        table2.setWidths(columnWidths2);

        // 单元格
        //行1
        PdfPCell siteCells[] = new PdfPCell[3];
        PdfPRow siteRow1 = new PdfPRow(siteCells);
        siteCells[0] = new PdfPCell(new Paragraph("考试科目", contentFont));//单元格内容
        siteCells[0].setHorizontalAlignment(Element.ALIGN_MIDDLE);
        siteCells[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
        siteCells[1] = new PdfPCell(new Paragraph("考试地点", contentFont));
        siteCells[1].setHorizontalAlignment(Element.ALIGN_MIDDLE);
        siteCells[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
        siteCells[2] = new PdfPCell(new Paragraph("考试时间", contentFont));
        siteCells[2].setHorizontalAlignment(Element.ALIGN_MIDDLE);
        siteCells[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
        listRow2.add(siteRow1);
        for (ExamSubject examSubject : examSubjectList) {
            PdfPCell cells[] = new PdfPCell[3];
            PdfPRow row = new PdfPRow(cells);
            Subject subject = subjectService.getSubjectByPrimaryKey(examSubject.getSubjectId());
            cells[0] = new PdfPCell(new Paragraph(subject.getName(), contentFont));
            cells[0].setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cells[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
            cells[1] = new PdfPCell(new Paragraph(examSubject.getAddress(), contentFont));
            cells[1].setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cells[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            cells[2] = new PdfPCell(new Paragraph(sdf.format(examSubject.getStartTime()) + "-" +
                                        sdf.format(examSubject.getEndTime()), contentFont));
            cells[2].setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cells[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
            listRow2.add(row);
        }

        //把表格添加到文件中
        document.add(table);
        Font siteFont = new Font(bfChinese);
        Paragraph siteTite = new Paragraph("考试时间地点安排", siteFont);
        siteTite.setAlignment(Element.ALIGN_CENTER);
        document.add(siteTite);
        document.add(table2);
        document.close();
        writer.close();
        return MsgUtils.success(pdf);
    }
}
