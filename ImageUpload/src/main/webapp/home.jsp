<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Upload and Display</title>
    
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <style>
        .tab-content {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Image Upload and Display</h1>
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="showTab('upload-tab')">Upload Image</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="showTab('display-tab')">Display Image</a>
            </li>
        </ul>
        
        <!-- Tab 1: Upload Image -->
        <div class="tab-content mt-3" id="upload-tab">
            <form action="UploadImageServlet" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="file" class="form-control-file" name="imageFile" accept="image/*">
                </div>
                <button type="submit" class="btn btn-primary">Upload</button>
            </form>
        </div>
        
        <!-- Tab 2: Display Image -->
        <div class="tab-content mt-3" id="display-tab">
            <img src="DisplayImageServlet" alt="Uploaded Image" class="img-fluid">
        </div>
    </div>
    
    <!-- Include Bootstrap JS (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <script>
        function showTab(tabId) {
            var tabs = document.getElementsByClassName('tab-content');
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].style.display = 'none';
            }
            document.getElementById(tabId).style.display = 'block';
        }
    </script>
</body>
</html>
