$(document).ready(function() {
    const urlParams = new URLSearchParams(window.location.search);
    const userID = urlParams.get('userNic');
    $("#UserNIC").text(userID)
    console.log(userID);
getAllCarCards();
    


});

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
                                <button id="viewMore" class="btn btn-primary" data-reg="${car.regNo}">view more</button>
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

$(document).on('click', '#viewMore', function() {
    let regNo = $(this).data('reg'); // Get car registration number from button's data attribute
    let Nic = $("#UserNIC").text();
    alert("Please Register First !")
    // window.location.href = `../pages/CustomerCarPageDetails.html?regNo=${regNo}&Nic=${Nic}`; // Navigate to carReservation page with regNo as a parameter
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