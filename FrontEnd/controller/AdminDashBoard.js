$(document).ready(function () {
    // Fetch the customers data

    LoadNotVerifyCustomerTOCard()
    loadUnverifiedCustomers()
    // $("#notVerifyCustomersTbody").on('click', 'tr', function() {
    //     alert("Row clicked!");
    // });

});

$("#carManaging").click(function () {
    window.location.href = "../pages/AdminCarManaging.html";
});


function loadUnverifiedCustomers() {
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/customer', // change to your API endpoint
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            // Empty the table body first
            $('#notVerifyCustomersTbody').empty();

            // Iterate over each customer in the returned data
            $.each(data, function (index, customer) {
                if (customer.activeStatus == "notVerify") {
                    let nicImageUrl = customer.nicImage.replace(/\\/g, "/");
                    // Create a new table row and populate it with the customer's data
                    var tr = '<tr data-nic-image="' + nicImageUrl + '" data-name="' + customer.name + '" data-nic="' + customer.nicNumber + '">';
                    tr += '<td>' + customer.nicNumber + '</td>';
                    tr += '<td>' + customer.name + '</td>';
                    tr += '<td>' + customer.address + '</td>';
                    tr += '<td>' + customer.contactNumber + '</td>';
                    tr += '<td>' + nicImageUrl + '</td>';
                    tr += '</tr>';

                    // Append the table row to the table body
                    $('#notVerifyCustomersTbody').append(tr);
                }
            });
        },
        error: function (err) {
            console.error('Failed to fetch unverified customers:', err);
        }
    });
}


function LoadNotVerifyCustomerTOCard() {
    $("#notVerifyCustomersTbody").on('click', 'tr', function() {
        // Get the data attributes from the clicked row
        let nicImage = $(this).data('nic-image');
        let name = $(this).data('name');
        let nic = $(this).data('nic');

        // Set the data to the card elements
        $("#cardImage").attr("src", nicImage);
        $("#cardName").text(name);
        $("#cardNIC").text(nic);
    });

}


$("#customerVerifyTableRefresh").click(function () {
    loadUnverifiedCustomers()
});