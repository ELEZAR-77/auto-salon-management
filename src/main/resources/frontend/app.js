const API = "http://localhost:8080";
let authHeader = "";

function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    authHeader = "Basic " + btoa(username + ":" + password);
    localStorage.setItem("auth", authHeader);

    window.location.href = "cars.html";
}

async function loadCars() {
    const auth = localStorage.getItem("auth");

    const response = await fetch(API + "/cars/get-all", {
        headers: {
            "Authorization": auth
        }
    });

    const cars = await response.json();
    const tbody = document.getElementById("cars");
    tbody.innerHTML = "";

    cars.forEach(car => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${car.id}</td>
            <td>${car.brand}</td>
            <td>${car.model}</td>
            <td>${car.price}</td>
            <td>${car.rentPricePerDay}</td>
            <td>${car.status}</td>
            <td>
                <button onclick="rentCar(${car.id})">Аренда</button>
                <button onclick="sellCar(${car.id})">Продать</button>
            </td>
        `;

        tbody.appendChild(row);
    });
}

async function rentCar(carId) {
    const auth = localStorage.getItem("auth");

    await fetch(API + "/deals/rent", {
        method: "POST",
        headers: {
            "Authorization": auth,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            carId: carId,
            endDate: "2026-05-01"
        })
    });

    alert("Аренда оформлена");
    loadCars();
}

async function sellCar(carId) {
    const auth = localStorage.getItem("auth");

    await fetch(API + "/deals/sale", {
        method: "POST",
        headers: {
            "Authorization": auth,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            carId: carId
        })
    });

    alert("Машина продана");
    loadCars();
}

if (window.location.pathname.includes("cars.html")) {
    loadCars();
}