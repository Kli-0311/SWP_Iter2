package controllerLabManager;

import dal.EvaluationDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.GradeForInterns;
import models.Projects;
import java.io.InputStream;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GradeManage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        request.setAttribute("gradeManage", "Yes");
        EvaluationDAO edao = new EvaluationDAO();
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            List<GradeForInterns> list;
            String selectedProject = request.getParameter("selectedProject");
            String selectedType = request.getParameter("selectedType");
            request.setAttribute("selectedProject", selectedProject);
            request.setAttribute("selectedType", selectedType);
            List<Projects> listProject = edao.getAllProject();
            request.setAttribute("projects", listProject);
            if (selectedProject != null && !selectedProject.isEmpty() && !selectedProject.equals("all")
                    && selectedType != null && !selectedType.isEmpty() && !selectedType.equals("all")) {
                list = edao.getAllGradeForInternByProjectCodeAndType(selectedProject, selectedType);
            } else if (selectedProject != null && !selectedProject.isEmpty() && !selectedProject.equals("all")) {
                list = edao.getAllGradeForInternByProjectCode(selectedProject);
            } else if (selectedType != null && !selectedType.isEmpty() && !selectedType.equals("all")) {
                list = edao.getAllGradeForInternByType(selectedType);
            } else {
                list = edao.getAllGradeForIntern();
            }
            request.setAttribute("listGrade", list);
            request.getRequestDispatcher("ViewGradeInternByMentor.jsp").forward(request, response);
        }

        if (service.equals("exportExcel")) {
            String selectedProject = request.getParameter("selectedProject");
            String selectedType = request.getParameter("selectedType");

            // Tải template Excel
            String templatePath = getServletContext().getRealPath("/templates/Example_Final.xlsx");
            InputStream inputStream = new FileInputStream(templatePath);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = wb.getSheetAt(0);

            // Lấy danh sách điểm theo project và type đã chọn
            List<GradeForInterns> list;
            if (selectedProject != null && !selectedProject.isEmpty() && !selectedProject.equals("all")
                    && selectedType != null && !selectedType.isEmpty() && !selectedType.equals("all")) {
                list = edao.getAllGradeForInternByProjectCodeAndType(selectedProject, selectedType);
            } else if (selectedProject != null && !selectedProject.isEmpty() && !selectedProject.equals("all")) {
                list = edao.getAllGradeForInternByProjectCode(selectedProject);
            } else if (selectedType != null && !selectedType.isEmpty() && !selectedType.equals("all")) {
                list = edao.getAllGradeForInternByType(selectedType);
            } else {
                list = edao.getAllGradeForIntern();
            }

            // Bắt đầu ghi dữ liệu vào Excel từ hàng 2 (hàng 1 là tiêu đề)
            int startRow = 13; // Hàng bắt đầu ghi
            int rowNo = startRow;

            for (GradeForInterns g : list) {
                Row row = sheet.createRow(rowNo++);
                int cellnum = 0;

                row.createCell(cellnum++).setCellValue(g.getStt());
                row.createCell(cellnum++).setCellValue(g.getRollNumber());
                row.createCell(cellnum++).setCellValue(g.getInternId());
                row.createCell(cellnum++).setCellValue(g.getFullName());
                row.createCell(cellnum++).setCellValue(g.getPositionName());
                row.createCell(cellnum++).setCellValue(g.getMentorId());

                row.createCell(cellnum++);

//                row.createCell(cellnum++).setCellValue(g.getType());
                row.createCell(cellnum++).setCellValue(g.getComment());
                row.createCell(cellnum++).setCellValue(g.getAttitude_score());
                row.createCell(cellnum++).setCellValue(g.getSoft_skills_score());
                row.createCell(cellnum++).setCellValue(g.getTechnical_skills_score());
                row.createCell(cellnum++).setCellValue(g.getTotal_score());
            }

            // Thiết lập thuộc tính cho phản hồi
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=EvaluationOJT.xlsx");

            // Ghi workbook vào output stream
            wb.write(response.getOutputStream());
            wb.close();
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
    }
}
