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
        url: "http://localhost:8080/BackEnd_war/customer",
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            console.log(data)
            $('#customergetAll').empty();  // clear the content

            data.data.forEach(function(customer) {
                let customerCard = `
                        <div class="customer-card">
                            <img src="${customer.nicImage}" alt="NIC Image">
                            <img src="${customer.drivingLicenseImage}" alt="Driving License Image">
                            <p>Name: ${customer.name}</p>
                            <p>Email: ${customer.emailAddress}</p>
                            <p>Contact: ${customer.contactNumber}</p>
                            <p>Address: ${customer.address}</p>
                        </div>`;

                $('#customergetAll').append(customerCard);
            });
        },
        error: function(err) {
            console.log(err);
        }
    });
});