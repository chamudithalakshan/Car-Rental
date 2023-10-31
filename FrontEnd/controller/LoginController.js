$("#btnLogin").click(function (event) {
    event.preventDefault();

    let formData = $("#loginForm").serialize();
    let userType = $("#userT").val();
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Login',
        method: "post",
        headers: {
            Auth: "user=admin,pass=admin"
        },
        data: formData,
        success: function (res) {
            let nicNumber = res.nicNumber;
            if (userType === "Customer") {
                window.location.href = "../pages/RegisterCustomerDashBoard.html?userNic=" + nicNumber;
            }else if (userType==="Admin"){
                window.location.href = "../pages/AdminDashboard.html";

            }

            console.log(res);
        },
        error: function (error) {
            // alert(error.responseJSON.message);
        }
    });
});

