$(document).ready(function() {
    const urlParams = new URLSearchParams(window.location.search);
    const userID = urlParams.get('userNic');
    $("#UserNIC").text(userID)
    console.log(userID);
});



$("#viewMore").click(function () {
    window.location.href = "../pages/CustomerCarPageDetails.html";
});


$("#btnSignUp").click(function () {
    window.location.href = "../pages/CustomerSignInPage.html";
});
$("#btnLogin").click(function () {
    window.location.href = "../pages/Loging.html";
});

const urlParams = new URLSearchParams(window.location.search);
const userID = urlParams.get('userNic');
console.log(userID);