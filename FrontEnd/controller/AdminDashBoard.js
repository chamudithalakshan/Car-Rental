$(document).ready(function () {
    // Fetch the customers data
    getAllCarCards();
     LoadNotVerifyCustomerTOCard()
     loadUnverifiedCustomers()
    loadDrivers();
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
                    tr += '<td style="display: none;">' + nicImageUrl + '</td>';
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
        $("#btnVerify").data("nic", nic);
    });

}

$("#btnVerify").on("click", function() {
    let currentNic = $("#cardNIC").text();  // Assuming the NIC value is shown in the card
    updateCustomerStatus(currentNic, 'Verify');
});

function updateCustomerStatus(nic, status) {
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/customer/updateStatus',
        type: 'PUT',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            nicNumber: nic,
            activeStatus: status
        }),
        success: function (response) {
            console.log(response);
            alert('Status updated successfully!');
        },
        error: function (error) {
            console.error("Error updating status:", error);
        }
    });
}

function getAllCarCards() {
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Car',
        type: 'GET',
        success: function(cars) {
            // Assuming cars is an array of Car objects
            cars.forEach(car => {
                const filename = getFilenameFromPath(car.imagePaths[0]);
                const imagePath = `http://localhost:8080/BackEnd_war/Car/image/${filename}`;
                const carHtml = `
                    <div class="col">
                        <div class="card shadow" style="width: 18rem;">
                            <img src="${imagePath}" class="card-img-top" height="166px">
                            <div class="card-body">
                                <h5 class="card-title">${car.vehicleBrand}</h5>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${car.vehicleType}</li>
                                    <li class="list-group-item">${car.transmissionType}</li>
                                    <li class="list-group-item">${car.fuelType}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                `;
                $('#carCard').append(carHtml);
            });
        },
        error: function(error) {
            console.error('Failed to fetch cars', error);
        }
    });
}


function getFilenameFromPath(path) {
    return path.split('\\').pop().split('/').pop();
}



$("#customerVerifyTableRefresh").click(function () {
    loadUnverifiedCustomers()
});

$("#btnAddDriver").on('click',function () {
    let formData = $("#DriverForm").serialize();

    $.ajax({
        url:'http://localhost:8080/BackEnd_war/Driver',
        method: "post",
        headers:{
            Auth:"user=admin,pass=admin"
        },
        data: formData,
        success: function (res) {
            alert("successFully Added !");

        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });


})
$("#btnDeleteDriver").on('click',function () {
    let driverId = $("#driverID").val();

    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Driver?DriverId=' + driverId,
        method: 'delete',
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success: function (resp) {
            alert(resp.message);
            return true;
        },
        error: function (error) {
            alert(error.responseJSON.message);
            return false;
        }
    });
});

function searchDriver(DriverId) {

    let resp = false;
    $.ajax({
        url:'http://localhost:8080/BackEnd_war/Driver',
        dataType: "json",
        headers:{
            Auth:"user=admin,pass=admin"
        },
        async: false,
        success: function (response) {
            let customers = response.data;
            resp = customers.find(function (driver) {
                //if the search id match with customer record
                //then return that object
                return driver.driverId == DriverId;
            });

        },
        error: function (error) {
            resp=false;
            alert(error.responseJSON.message);
        }
    });
    return resp;


}

function updateDriver(id) {
    if (searchDriver(id) == undefined) {
        alert("No such Customer..please check the ID");
    } else {
        let consent = confirm("Do you really want to update this customer.?");
        if (consent) {
            let driver = searchDriver(id)[0];
            //if the customer available can we update.?
            console.log(driver);
            let DriverName = $("#driverName").val();
            let DriverTel = $("#driverTel").val();
            let DriverReservationStatus = $("#driverReserveStatus").val();
            driver.driverId = id;
            driver.driverName = DriverName;
            driver.driverTel = DriverTel;
            driver.driverReserveStatus = DriverReservationStatus;

            $.ajax({
                url:'http://localhost:8080/BackEnd_war/Driver/update',
                method: 'put',
                headers:{
                    Auth:"user=admin,pass=admin"
                },
                contentType: "application/json",
                data: JSON.stringify(driver),
                success: function (resp) {
                    alert(resp.message);

                },
                error: function (error) {
                    alert(error.responseJSON.message);
                }
            });
        }
    }

}
$("#btnUpdateDriver").click(function (event) {
    event.preventDefault(); // Prevent default form submission

    // Serialize the form data
    let formData = $("#DriverForm").serializeArray();
    let dataObject = {};
    $(formData).each(function(index, obj){
        dataObject[obj.name] = obj.value;
    });

    // Send an AJAX request to update the driver
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Driver/update',
        method: 'PUT',
        contentType: "application/json",
        data: JSON.stringify(dataObject),
        success: function (response) {
            alert(response.message);
            // You might also want to close the modal and refresh your list of drivers here.
            $('#exampleModal').modal('hide'); // Close modal
            // ... refresh driver list if needed
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
});


function loadDrivers() {
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Driver',
        method: 'GET',
        success: function(drivers) {
            // Assuming 'drivers' is an array of driver objects
            let tableBody = $('#tblDriverTbody');
            tableBody.empty();  // Clear existing rows

            drivers.forEach(driver => {
                const driverRow = `
                    <tr>
                        <th scope="row">${driver.driverId}</th>
                        <td>${driver.driverName}</td>
                        <td>${driver.driverTel}</td>
                        <td>${driver.driverReserveStatus}</td>
                    </tr>
                `;
                tableBody.append(driverRow);
            });
        },
        error: function(error) {
            alert('Failed to fetch drivers', error);
        }
    });
}


$('#tblDriverTbody').on('click', 'tr', function() {
    // Fetching values from the clicked row
    const driverId = $(this).find('th').text().trim();
    const driverName = $(this).find('td:nth-child(2)').text().trim();
    const driverTel = $(this).find('td:nth-child(3)').text().trim();
    const driverReserveStatus = $(this).find('td:nth-child(4)').text().trim();

    // Setting the values to the respective input fields
    $('#driverID').val(driverId);
    $('#driverName').val(driverName);
    $('#driverTel').val(driverTel);
    $('#driverReserveStatus').val(driverReserveStatus);

});