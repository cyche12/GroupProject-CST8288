<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>

<div class="register-container">
    <h2>Register</h2>
    
    <form action="RegistrationServlet" method="post">
        <input type="text" name="first_name" placeholder="First Name" required>
        <input type="text" name="last_name" placeholder="Last Name" required>
        <input type="email" name="user_email" placeholder="Email" required>
        <input type="password" name="user_password" placeholder="Password" required>
        <select name="user_type" required>
            <option value="" disabled selected>Select User Type</option>
            <option value="RETAIL">Retail</option>
            <option value="CONSUMER">Consumer</option>
            <option value="CHARITY">Charity</option>
        </select>
        
        <input type="date" name="join_date" required>
        <textarea name="foodPreferences" placeholder="Food Preferences"></textarea>
        <div>
            <label>Subscribe to alerts:</label>
            <input type="radio" name="subscribe" value="Yes" id="subscribe-yes">
            <label for="subscribe-yes">Yes</label>
            <input type="radio" name="subscribe" value="No" id="subscribe-no">
            <label for="subscribe-no">No</label>
        </div>
        <div id="notification-method" style="display:none;">
            <input type="checkbox" name="smsNotification" value="SMS" id="sms-notification">
            <label for="sms-notification">SMS Notification</label>
            <br>
            <input type="checkbox" name="emailNotification" value="Email" id="email-notification">
            <label for="email-notification">Email Notification</label>
        </div>
        <input type="submit" value="Register">
        <input type="button" value="Back to Home" onclick="window.location.href='index.jsp'">
    </form>
    
</div>

<script>
    document.querySelectorAll('input[name="subscribe"]').forEach(radio => {
        radio.addEventListener('change', function() {
            if (this.value === 'Yes') {
                document.getElementById('notification-method').style.display = 'block';
            } else {
                document.getElementById('notification-method').style.display = 'none';
            }
        });
    });
</script>

</body>
</html>
