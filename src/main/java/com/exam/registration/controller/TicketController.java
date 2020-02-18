package com.exam.registration.controller;

import com.exam.registration.model.*;
import com.exam.registration.service.*;
import com.exam.registration.util.MsgUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author yhf
 * @classname TicketDownloadController
 **/
@RequestMapping("/tickets")
public class TicketController {

    @Value("${photo.upload.path}")
    private String path;
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
    /**
     * 生成pdf，以考生的身份证图片文件名称命名文件
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String createTicket(@RequestBody Map<String, Object> map, HttpServletRequest request) throws Exception {
        request.setAttribute("studentId", 54);
        Student student = studentService
                .getStudentByPrimaryKey(Long.valueOf((String) request.getAttribute("studentId")));
        Long orderId = Long.valueOf(String.valueOf(map.get("orderId")));
        Order order = orderService.getOrderByPrimaryKey(orderId);
        Exam exam = examService.getExamByPrimaryKey(order.getExamId());
        Site site = siteService.getSiteByPrimaryKey(exam.getSiteId());
        Major major = majorService.getMajorByPrimaryKey(exam.getMajorId());
        List<Subject> subjectList = subjectService.listSubjectsByMajorId(major.getId());
        String fileName = student.getIdCardPic();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\yhf\\Desktop\\"+fileName+".pdf"));
        document.open();
        document.addSubject("准考证");
        document.addTitle(site.getName() + "-" + major.getName() + "-" + student.getName());
        document.addCreationDate();
        document.add(new Paragraph("Some content here"));
        document.close();
        writer.close();
        return MsgUtils.success();
    }
}
