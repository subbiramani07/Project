import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DisplayImageServlet")
public class DisplayImageServlet extends HttpServlet {
	  DBOperation dboperation= new DBOperation();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int imageId = 1; // You may need to change this based on your application logic

        try {
        	HttpSession session =request.getSession();
			String email= (String) session.getAttribute("email");
         
            Connection connection = dboperation.getConnection();
            String sql = "SELECT image_data, image_type FROM images WHERE email = ? ORDER BY id DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String imageType = resultSet.getString("image_type");
                InputStream inputStream = resultSet.getBinaryStream("image_data");

                response.setContentType(imageType);

                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
                inputStream.close();
            } else {
                response.getWriter().write("Image not found.");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
        }
    }
}
