//
// $("#btnSignUp").on('click', function(event) {
//     // Prevent the form from submitting the default way
//     event.preventDefault();
//
//     // Serialize form data
//     let formData = $("#customerForm").serialize();
//
//     // For debugging purposes, show the form data in an alert (this can be removed later)
//     console.log("Serialized Form Data:", formData);
//
//     // Send the form data to the backend using AJAX
//     $.ajax({
//         url: 'http://localhost:8080/BackEnd_war/customer', // Using a relative URL
//         method: 'POST',
//         data: formData,
//         headers: {
//             'Auth': "user=admin,pass=admin"  // As previously set; consider changing this if needed
//         },
//         success: function(response) {
//             // Notify the user of the successful submission
//             alert("Form submitted successfully!");
//
//             // Optionally, you can redirect the user to another page or update the UI in another manner.
//             // window.location.href = '/successPage';
//         },
//         error: function(jqXHR, textStatus, errorThrown) {
//             // Provide a user-friendly error message
//             alert("There was an issue submitting your form. Please try again later.");
//
//             // For debugging purposes, log the error details (this can be removed later)
//             console.error("Error submitting form:", errorThrown);
//         }
//     });
// });




$("#btnSignUp").click(function(event) {
    event.preventDefault();

    // Serialize form data and append file data
    let formData = new FormData($("#customerForm")[0]);

    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/customer',  // Change to your backend endpoint
        method: 'post',
        data: formData,
        processData: false,
        contentType: false,
        success: function(data) {
            alert('Registration successful!');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Error during registration. Please try again.');
            console.error("Error:", textStatus, errorThrown,jqXHR);
            alert('Error during registration. Please check the console for more details.');
        }
    });
});



$('#btnget').click(function(e) {
    e.preventDefault();

    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/customer',
        type: 'GET',
        dataType: 'json',
        success: function(customers) {
            if (customers && Array.isArray(customers) && customers.length > 0) {
                console.log(customers);

                customers.forEach(customer => {
                    // Replace backslashes with forward slashes for image paths
                    let nicImageUrl = customer.nicImage.replace(/\\/g, "/");
                    let drivingLicenseImageUrl = customer.drivingLicenseImage.replace(/\\/g, "/");

                    // Display the images (for example, append them to a specific div)
                    $('#customergetAll').append('<img src="' + nicImageUrl + '" alt="NIC Image">');
                    $('#customergetAll').append('<img src="' + drivingLicenseImageUrl + '" alt="Driving License Image">');

                    // ... do other things with the data
                });
            } else {
                console.error('No customers data received.');
            }
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });


});
