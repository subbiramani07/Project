import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UploadImageServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // Max file size = 5MB
public class UploadImageServlet extends HttpServlet {
    DBOperation dboperation= new DBOperation();
   

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "";
        try {
            Part part = request.getPart("imageFile");
            if (part != null) {
                InputStream inputStream = part.getInputStream();

                try (Connection connection =dboperation.getConnection()) {
                	HttpSession session =request.getSession();
        			String email= (String) session.getAttribute("email");
                    String sql = "INSERT INTO images (email, image_data, image_type,image_name) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, email);
                    preparedStatement.setBlob(2, inputStream);
                    preparedStatement.setString(3, part.getContentType());

                    preparedStatement.setString(4, part.getName());

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        message = "Image uploaded successfully!";
                    } else {
                        message = "Image upload failed.";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    message = "Database error: " + e.getMessage();
                }
            } else {
                message = "Image upload failed. Please select an image.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Image upload failed: " + e.getMessage();
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
