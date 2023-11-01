$(document).ready(function () {

    // // Assuming you have a way to get the regNo, for this example, let's say it's from the URL.
    // const regNo = window.location.search.split('=')[1];  // assuming your URL is like ...?regNo=XYZ123
    // const nic = window.location.search.split('=')[2];  // assuming your URL is like ...?regNo=XYZ123

    const params = new URLSearchParams(window.location.search);
    const regNo = params.get('regNo');
     const nic = params.get('Nic');

     console.log(regNo)

    let Car;
    $.ajax({
        url: `http://localhost:8080/BackEnd_war/Car/${regNo}`,  // Modify this URL if needed.
        type: 'GET',
        success: function (car) {
            Car = car;

            // Set the values for car details
            $('#vehicleBrand').val(car.vehicleBrand);
            $('#vehicleType').val(car.vehicleType);
            $('#nmbOfPsngr').val(car.numberOfPassengers);
            $('#transmission').val(car.transmissionType);
            $('#dailyRentalPrice').val(car.dailyRentalPrice);
            $('#monthlyRentalPrice').val(car.monthlyRentalPrice);
            $('#fuelType').val(car.fuelType);
            $('#freeMilage').val(car.freeMileage);
            $('#priceForExtra').val(car.priceForExtraKM);
            $('#registerNmb').val(car.regNo);
            $('#vehicleColor').val(car.VehicleColor);

            // ... similarly, set values for other fields using their IDs ...

            // Set images to carousel
            const carouselInner = $(".carousel-inner");
            carouselInner.empty(); // Clear existing items first

            car.imagePaths.forEach((path, index) => {
                const filename = getFilenameFromPath(path);
                const imagePath = `http://localhost:8080/BackEnd_war/Car/image/${filename}`;

                const isActive = index === 0 ? 'active' : '';

                const carouselItem = `
                    <div class="carousel-item ${isActive}">
                        <img src="${imagePath}" class="d-block w-100" alt="...">
                    </div>
                `;

                carouselInner.append(carouselItem);
            });

            // Re-initialize the carousel after adding images
            $('#carouselExampleAutoplaying').carousel();
        },
        error: function (error) {
            console.error('Failed to fetch car details', error);
        }
    });

    function getFilenameFromPath(path) {
        return path.split('\\').pop().split('/').pop();
    }



        function validateForm() {
            const pickupDate = new Date($('#pickupDate').val());
            const returnDate = new Date($('#returnDate').val());

            if (!pickupDate || !returnDate) {
                alert('Please select both pickup and return dates.');
                return false;
            }

            if (pickupDate >= returnDate) {
                alert('Return date should be after the pickup date.');
                return false;
            }

            const bankReciept = $('#bankReciept')[0].files[0];
            if (!bankReciept) {
                alert('Please attach the bank receipt.');
                return false;
            }

            // You can add more validation checks as needed...

            return true;
        }

        $('#btnReserveOrder').click(function (e) {
            e.preventDefault(); // Prevent default form submission

            if (!validateForm()) {
                return; // Exit the function if the form is not valid
            }
            var currentDate = new Date();
            var year = currentDate.getFullYear();
            var month = currentDate.getMonth() + 1; // Months are zero-based (0 - 11)
            var day = currentDate.getDate();

            var formattedDate = year + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;


            const formData = new FormData();
            formData.append('customerID', nic)
            formData.append('date', formattedDate)
            formData.append('bankReciept', $('#bankReciept')[0].files[0]);

            const orderDetails = {
                pickupDate: $('#pickupDate').val(),
                returnDate: $('#returnDate').val(),
                pickupLocation: $('#pickupLocation').val(),
                returnLocation: $('#returnLocation').val(),
                driverStatus: $('#driverStatus').val(),
                loseDamage: $('#loseDamage').val(),
                orderStatus: $('#OrderStatus').val(),
                carRegNo: regNo
            }
            formData.append('orderDetailsDTO', JSON.stringify(orderDetails))


            $.ajax({
                url: 'http://localhost:8080/BackEnd_war/Reservation',
                type: 'POST',
                data: formData,
                processData: false, // Important when sending FormData
                contentType: false, // Important when sending FormData
                success: function (response) {
                    alert('Reservation made successfully!');
                    // Handle further logic on success, like clearing the form or navigating the user.
                },
                error: function (error) {
                    console.error('Failed to make a reservation', error);
                    alert('Failed to make a reservation. Please try again.');
                }
            });
        });


});
