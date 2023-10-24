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

$("#btnUpdate").click(function(event) {  // Assuming you give your "Update" button the id `btnUpdate`.
    event.preventDefault();  // To prevent form submission since we'll use AJAX

    var carDetails = {
        vehicleBrand: $("#vehicleBrand").val(),
        vehicleType: $("#vehicleType").val(),
        numberOfPassenger: $("#txtNmbOfPassenger").val(),
        transmissionType: $("#txtTransmissionType").val(),
        dailyRentalPrice: $("#dailyRentalPrice").val(),
        monthlyRentalPrice: $("#txtmonthlyRentalPrice").val(),
        fuelType: $("#txtfuelType").val(),
        freeMilage: $("#txtfreeMilage").val(),
        priceForExtraKM: $("#txtpriceForExtra").val(),
        Vehiclecolor: $("#vehicleColor").val(),
        // ... gather all other input values similarly
    };

    var regNo = $("#txtregisterNmb").val();

    updateCarDetails(regNo, carDetails);
});

function updateCarDetails(regNo, carDetails) {
    $.ajax({
        url: 'http://localhost:8080/BackEnd_war/Car/' + regNo,
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
