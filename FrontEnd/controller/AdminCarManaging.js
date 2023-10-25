$("#btnAddCar").click(function(event) {
    var formData = new FormData($("#CarForm")[0]);


    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Car',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(data) {
            alert("Car details saved successfully!");
        },
        error: function(error) {
            alert("Error saving car details.");
        }
    });
});


$("#btnDeleteCar").click(function() {
    var regNo = $("#txtregisterNmb").val();
    if (regNo) {
        deleteCarByRegNo(regNo);
    } else {
        console.error("Registration number is empty or not provided.");
    }
});

function deleteCarByRegNo(regNo) {
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Car?regNo=' + regNo,
        method: 'DELETE',
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success: function (response) {
            console.log(response);
            alert("Car deleted successfully");
        },
        error: function (error) {
            console.error("Error deleting car:", error);
            alert("Failed to delete car");
        }
    });
}

$("#btnUpdate").click(function(event) {
    event.preventDefault();  // To prevent form submission since we'll use AJAX

    var carDetails = {
        regNo: $("#txtregisterNmb").val(),
        vehicleBrand: $("#vehicleBrand").val(),
        vehicleType: $("#vehicleType").val(),
        numberOfPassengers: parseInt($("#txtNmbOfPassenger").val(), 10), // Convert string input to integer
        transmissionType: $("#txtTransmissionType").val(),
        dailyRentalPrice: parseFloat($("#dailyRentalPrice").val()), // Convert string input to float
        monthlyRentalPrice: parseFloat($("#txtmonthlyRentalPrice").val()), // Convert string input to float
        fuelType: $("#txtfuelType").val(),
        freeMileage: parseFloat($("#txtfreeMilage").val()), // Convert string input to float
        priceForExtraKM: parseFloat($("#txtpriceForExtra").val()), // Convert string input to float
        VehicleColor: $("#vehicleColor").val(),
        reservedStatus: $("#txtReserveStatus").val(),
        maintains: $("#txtAddMaintance").val()
        // If you want to handle file uploads, you'll need to add more logic for "imagePaths"
    };

    var regNo = $("#txtregisterNmb").val();

    updateCarDetails(regNo, carDetails);
});


function updateCarDetails(regNo, carDetails) {
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Car/update',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(carDetails),
        success: function(response) {
            alert("Car details updated successfully!");
        },
        error: function(error) {
            alert("Failed to update car details!");
        }
    });
}
